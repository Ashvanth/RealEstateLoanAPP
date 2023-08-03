import {
    aws_ec2 as ec2,
    aws_elasticloadbalancingv2 as elb2,
    aws_iam as iam,
    aws_rds as rds,
    CfnOutput,
    Duration,
    RemovalPolicy,
    Stack
} from 'aws-cdk-lib';
import {Construct} from 'constructs';
import {StringParameter} from "aws-cdk-lib/aws-ssm";
import {RetentionDays} from "aws-cdk-lib/aws-logs";
import {Port} from "aws-cdk-lib/aws-ec2";
import { access } from 'fs';

export interface PostgresConstructProps {
    /**
     * The name of the application
     */
    applicationName: string;
    /**
     * The name of the database/schema. Used by AWS to create a schema in the DB given by the parameter
     * instanceIdentifier. Also defines the jdbc url:
     * jdbc:postgresql://INSTANCE_IDENTIFIER.csdmasog5gei.eu-west-1.rds.amazonaws.com:5432/DATABASE_NAME
     */
    databaseName: string;
    /**
     * Unique name for the DB that is created.
     */
    instanceIdentifier: string;

    /**
     * The name of the created securityGroup that controls access to the created DB. Used by applications that
     * require access. Defaults to "/gs/PostgresStack-applicationNameDB-sg " if not set (and usually suffices). Added for backward compatibility and will be removed
     */
    securityGroupName?: string;
}

export class PostgresConstruct extends Construct {
    constructor(scope: Construct, id: string, props: PostgresConstructProps) {
        super(scope, id);

        

        const vpc = ec2.Vpc.fromLookup(this, 'DefaultVpc', { isDefault: true });
        //const vpc = ec2.Vpc.fromLookup(this, 'CoreVPC', { vpcName: 'core-vpc' });

        const dbSecurityGroup = new ec2.SecurityGroup(this,"SecurityGroup",{
            vpc: vpc,
            description: ` LoanApplication DB`,
            allowAllOutbound: false
        });


        /**
         * Changed DB from PRIVATE_WITH_NAT to PRIVATE_ISOLATED as we got error on gitLab
         *
         * Error: There are no 'Private' subnet groups in this VPC. Available types: Isolated,Deprecated_Isolated
         at LookedUpVpc.selectSubnetObjectsByType (/builds/dnb/gsf/infra/common-infra-aws/node_modules/aws-cdk-lib/aws-ec2/lib/vpc.js:1:4862)
         at LookedUpVpc.selectSubnetObjects (/builds/dnb/gsf/infra/common-infra-aws/node_modules/aws-cdk-lib/aws-ec2/lib/vpc.js:1:3641)
         at LookedUpVpc.selectSubnets (/builds/dnb/gsf/infra/common-infra-aws/node_modules/aws-cdk-lib/aws-ec2/lib/vpc.js:1:1374)
         at new SubnetGroup (/builds/dnb/gsf/infra/common-infra-aws/node_modules/aws-cdk-lib/aws-rds/lib/subnet-group.js:1:628)
         at new DatabaseInstanceNew (/builds/dnb/gsf/infra/common-infra-aws/node_modules/aws-cdk-lib/aws-rds/lib/instance.js:1:4755)
         at new DatabaseInstanceSource (/builds/dnb/gsf/infra/common-infra-aws/node_modules/aws-cdk-lib/aws-rds/lib/instance.js:1:9252)
         at new DatabaseInstance (/builds/dnb/gsf/infra/common-infra-aws/node_modules/aws-cdk-lib/aws-rds/lib/instance.js:1:12194)
         at new PostgresConstruct (/builds/dnb/gsf/infra/common-infra-aws/constructs/postgres-construct.ts:16:28)
         at new PostgresStack (/builds/dnb/gsf/infra/common-infra-aws/lib/postgres-stack.ts:13:34)
         at Object.<anonymous> (/builds/dnb/gsf/infra/common-infra-aws/bin/common-infra-aws.ts:99:1)
         Subprocess exited with error 1
         Cleaning up project directory and file based variables
         */
        const dbInstance = new rds.DatabaseInstance(this, 'LoanApplicationInstance', {
            instanceIdentifier: props.instanceIdentifier,
            vpc,
            vpcSubnets: {
                subnetType: ec2.SubnetType.PRIVATE_ISOLATED,
            },
            engine: rds.DatabaseInstanceEngine.postgres({
                version: rds.PostgresEngineVersion.VER_13_4,
            }),
            instanceType: ec2.InstanceType.of(
                ec2.InstanceClass.BURSTABLE3,
                ec2.InstanceSize.MICRO,
            ),
            credentials: rds.Credentials.fromGeneratedSecret('admin'),
            multiAz: false,
            allocatedStorage: 20,
            //maxAllocatedStorage: 20,
            allowMajorVersionUpgrade: false,
            autoMinorVersionUpgrade: true,
            backupRetention: Duration.days(0),
            deleteAutomatedBackups: true,
            removalPolicy: RemovalPolicy.DESTROY,
            deletionProtection: false,
            databaseName: props.databaseName,
            publiclyAccessible: false,
            storageEncrypted:true,
            cloudwatchLogsExports:["postgresql","upgrade"],
           // cloudwatchLogsRetention: RetentionDays.ONE_WEEK,//Somehow this causes lambda to be created and stack deploy fails. Must be fixed manually for now
            securityGroups: [dbSecurityGroup],

        });


        const boundary = iam.ManagedPolicy.fromManagedPolicyName(this, 'Boundary', 'Core-PermissionBoundaryPolicy');
        iam.PermissionsBoundary.of(this).apply(boundary);

        const stack = Stack.of(this);
        new StringParameter(this, 'StringParameter', {
            parameterName: props.securityGroupName? props.securityGroupName : `/gs/PostgresStack-${props.applicationName}DB-sg`,
            stringValue: dbSecurityGroup.securityGroupId
        })
        new CfnOutput(this, 'SecurityGroupOut', { value: dbSecurityGroup.securityGroupId, exportName: stack.stackName + `-${props.applicationName}DB-sg`} );

    }
}
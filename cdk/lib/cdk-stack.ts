import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import { Function,Runtime,Code } from 'aws-cdk-lib/aws-lambda';
import { Duration } from 'aws-cdk-lib';
import * as apigateway from 'aws-cdk-lib/aws-apigateway';
import { DatabaseInstance, DatabaseInstanceEngine,DatabaseInstanceProps } from 'aws-cdk-lib/aws-rds';
import { aws_rds as rds } from 'aws-cdk-lib';
import * as ec2 from 'aws-cdk-lib/aws-ec2';
import { PostgresConstruct } from './PostgresConstruct';
import * as s3 from 'aws-cdk-lib/aws-s3';

// import * as sqs from 'aws-cdk-lib/aws-sqs';

export class loanApplication extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    const loanApplicationFunction = new Function(this, 'Function', {
      runtime: Runtime.JAVA_17,
      handler: 'com.aws.dnb.handler.LambdaHandler::handleRequest',
      code: Code.fromAsset
      ('C:\\Users\\AB73660\\SourceCode\\SpringbootDemo\\DNB_Projects\\HomeLoanAPI\\target\\HomeLoanAPI-0.0.1-SNAPSHOT-aws.jar'),
      timeout: Duration.minutes(10),
    });

    const bucket = s3.Bucket.fromBucketName(this, 'AuthorizerBucket', 'authorizerjscode');
   
    const authorizerFunction = new Function(this, 'AuthorizerFunctionJS', {
      runtime: Runtime.NODEJS_18_X,
      handler: 'authScript.handler',
      code: Code.fromBucket(bucket,'authScript.zip'), 
    });


    const api = new apigateway.RestApi(this, 'loanApplicationAPI', {
      deployOptions: {
        stageName: 'v1',
      },
    });

    const loanAPIResource = api.root;

    const requestBodyModel = api.addModel('RequestBodyModel', {
      contentType: 'application/json',
      schema: {
        schema: apigateway.JsonSchemaVersion.DRAFT4,
        type: apigateway.JsonSchemaType.OBJECT,
        properties: {
          customerSSN: { type: apigateway.JsonSchemaType.NUMBER },
          fullName: { type: apigateway.JsonSchemaType.STRING },
          loanAmount: { type: apigateway.JsonSchemaType.NUMBER },
          salaryAmount: { type: apigateway.JsonSchemaType.NUMBER },
          equityAmount: { type: apigateway.JsonSchemaType.NUMBER }
        },
        required: ['customerSSN', 'fullName', 'loanAmount','salaryAmount','equityAmount'],
      },
    });

    const postMethod = loanAPIResource.addMethod('POST', new apigateway.LambdaIntegration(loanApplicationFunction), {
      /*requestParameters: {
        'method.request.path.value1': true,
        'method.request.path.value2': true,
        'method.request.path.operator': true,
      }, */
      requestModels: {
        'application/json': requestBodyModel, // Reference to the request model you defined
      },
    
    
    });

   
  }

  }


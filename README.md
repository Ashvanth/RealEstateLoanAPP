**Applicaion Description**

Scenario : Your task is to build a simplified solution for customers to apply for a real estate loan. The solution should consist of a frontend and a backend where the frontend is optional. The Backend should consist of a database with a REST API handling customers loan applications and should contain data such as: *CustomerSSN, FullName, LoanAmount, EquityAmount and SalaryAmount

1)	Spring boot v 3.0.8 is used to implement the backend service which uses SPRING dependencies and annotations providing endpoints over a REST controller .
two endpoints are created .
2) Application is connected using MySQL database for the entity layer
3) CDK folder contains lib and bin folder which holds files for IaC .

To Run the springboot appliacation in local 

1) git clone
2) start the application from main class and use the below endpoints to test 

http://localhost:8080/application/v1/allApplications -> List all the applications submitted

http://localhost:8080/application/v1/
{
   "customerSSN": 1048,
  "fullName": "ECS",
  "loanAmount": 5000000,
  "salaryAmount": 800000,
  "equityAmount": 760009
}

**TO SETUP INFRASTUCTURE IN AWS **

run cdk deploy from cdk folder provided you have required user with IAM permissions and aws configure is setup

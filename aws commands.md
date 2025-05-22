### Step 1: Package the application
mvn clean package

### Step 2: Create an S3 bucket (if not already created)
aws s3 mb s3://internal.job.list

### Step 3: Upload the JAR file to S3
aws s3 cp /Users/kalakshetra2/Documents/maya/snowflake/github/2/target/2-1.0-lambda.zip s3://internal.job.list/2-1.0-lambda.zip

### Step 4: Create a trust policy for Lambda to assume the role
cat > trust-policy.json <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Service": "lambda.amazonaws.com"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
EOF

### Step 5: Create the IAM role
aws iam create-role \
    --role-name InternalJobApiLambdaApiGatewayRole \
    --assume-role-policy-document file:///Users/kalakshetra2/Documents/maya/snowflake/github/2/lambda-trust-policy.json

### Step 6: Attach the necessary policies to the role
aws iam attach-role-policy \
    --role-name InternalJobApiLambdaApiGatewayRole \
    --policy-arn arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole

aws iam attach-role-policy \
    --role-name InternalJobApiLambdaApiGatewayRole \
    --policy-arn arn:aws:iam::aws:policy/AmazonAPIGatewayInvokeFullAccess

aws iam get-role --role-name InternalJobApiLambdaApiGatewayRole

aws iam list-attached-role-policies --role-name InternalJobApiLambdaApiGatewayRole

### Step 7: Create the Lambda function
aws lambda create-function \
    --function-name InternalJobApiLambda \
    --runtime java21 \
    --role arn:aws:iam::385243611616:role/InternalJobApiLambdaApiGatewayRole \
    --handler com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler \
    --code S3Bucket=internal.job.list,S3Key=2-1.0-lambda.zip \
    --timeout 30 \
    --memory-size 2024

// 

aws lambda list-functions

### Step 8 (Optional): Create an API Gateway to expose the Lambda function
aws apigatewayv2 create-api \
    --name "InternalJobLambdaAPI" \
    --protocol-type HTTP \
    --target "arn:aws:lambda:us-east-1:385243611616:function:InternalJobApiLambda" \
    --route-key "ANY /{proxy+}"

#### output

| **CreateApi**                   |                                                        |
| ---------------------------------------- |--------------------------------------------------------|
| ApiEndpoint | https://2u23d9ppu5.execute-api.us-east-1.amazonaws.com |
| ApiId                      | 2u23d9ppu5                                             |
| ApiKeySelectionExpression  | $request.header.x-api-key                              |
| CreatedDate                | 2025-05-17T16:29:09+00:00                              
| DisableExecuteApiEndpoint  | False                                                  |
| Name                       | InternalJobLambdaAPI                                   |
| ProtocolType               | HTTP                                                   |
| RouteSelectionExpression   | \$request.method \$request.path                        |


### Step 9: Create a VPC Link
aws apigatewayv2 create-vpc-link \
    --name "InternalVPCLink" \
    --subnet-ids subnet-8fa052a3 subnet-6bf15e23 \
    --security-group-ids sg-be8b4ac0

aws apigatewayv2 get-vpc-links

### Step 10: Update the API to use the VPC Link
aws apigatewayv2 update-integration \
    --api-id 2u23d9ppu5 \
    --integration-id 7sujgv1 \
    --integration-type HTTP_PROXY \
    --connection-type VPC_LINK \
    --connection-id pol6v1

### Step 11: Deploy the API
aws apigatewayv2 create-deployment \
    --api-id 2u23d9ppu5 \
    --stage-name "internal"

### Step 12: Update lambda handler name

aws lambda update-function-configuration \
--function-name InternalJobApiLambda \
--handler cafe.snowflake.app2.SnowflakeLambda

### Step 13: Update Lambda functionality

aws s3 cp /Users/kalakshetra2/Documents/maya/snowflake/github/2/target/2-1.0-lambda.zip s3://internal.job.list/2-1.0-lambda.zip

aws lambda update-function-code \
--function-name InternalJobApiLambda \
--s3-bucket internal.job.list \
--s3-key /Users/kalakshetra2/Documents/maya/snowflake/github/2/target/2-1.0-lambda.zip


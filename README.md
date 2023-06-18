POST /arthimaticOperation

Description
Performs arithmetic operations on two numbers based on the provided arithmetic object.

{
  "first_number": 10,
  "second_number": 5,
  "operation": "ADD"
}

first_number (integer): The first number for the arithmetic operation.
second_number (integer): The second number for the arithmetic operation.
operation (string): The arithmetic operation to be performed. Possible values: "ADD", "SUBTRACT", "MULTIPLY", "DIVIDE".
Responses

200 OK

Description: The arithmetic operation was successful.
Body: The result of the arithmetic operation as a string.
400 Bad Request

Description: The request was invalid or missing required parameters.
Body: Error message indicating the cause of the error.

curl --location 'https://lz8qsj49nl.execute-api.eu-west-1.amazonaws.com/v3/' \
--header 'Content-Type: application/json' \
--data '{ 
    "first_number": 100,
    "second_number": 1000, 
    "operation": "ADD" 
}'

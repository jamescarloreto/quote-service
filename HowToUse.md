# How To Use

## Step 1

Open the quote-service in VS Code
Run QuoteApplication in Run and Debug on left panel of the VS Code

### Step 2

When the application is up and running
User can create or use provided user

Provided User Credentials:
admin
password

Access this url in postman

- POST http://localhost:8080/users/generate-token

and in Body -> raw -> JSON have this as a value

{
"username": "admin",
"password": "password"
}

this will generate token that will be use in next steps

or user can create new users by accessing

- POST http://localhost:8080/users/create

paste this url in postman and change the method into POST
and in Body -> raw -> JSON
have this as a vaue

{
"username": "admin1",
"password": "password",
"role": "ROLE_USER"
}

to create new users. And follow the generation of token above to generate token for newly created user.

#### Step 3

Access this url in postman

- GET http://localhost:8080/symbol/FB/quote/latest

to request the ask and bid for the FB symbol. Can change FB to MSFT or GOOG to request for any symbol.

- GET http://localhost:8080/symbol/quote/highestAsk/2020-01-05

User can get the symbol of the highest ask in the given day. (YYYY-MM-DD) format
ex. 2020-01-04 to get symbol for that date

- Remember to paste token in Authorization -> Bearer Token(Dropdown) and paste token in Token field.

##### Step 4

To test application using jUnit, go to src -> test/ ... /quoteservice -> service and open QuoteServiceTest.java in the class name besides line number there is a green button, user can test run the whole service.

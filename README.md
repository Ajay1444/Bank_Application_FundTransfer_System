# Bank_Application_FundTransfer_System

FundTransfer API

This project is based on the springboot 2.7.6 version using mySQL as backend DB. We are using JWT token for authenticate the api for security. In this api we perform business operation like Create account ,Delete account, Update account, Delete all acocunt and Fund transfer from one account to different.

Get Token:

Endpoint: POST

URL:http://localhost:8080/token

Request Payload:
  
  {
    "username":"AjayGaikwad",
    "password":"ajay1444"
  }


1. Create Account
   
Endpoint: POST

URL: http://localhost:8080/account

Headers:

Authorization: {Brearer {Token}}

Request Payload:

    {
    "accountNumber":"12386",
    "accountType":"Current",
    "branchName":"Phase 2",
    "ifscCode":"234567",
    "balance":"6500",
    "customer":{
        "customerName":"Ajay Gaikwad",
        "address":[{
            "addressType":"Current",
            "buildingName":"Apurva Hills",
            "street":"Phase 2",
            "city":"Pune",
            "postalCode":"345673"
        },
        {
            "addressType":"Permanent",
            "buildingName":"Ram Nivas",
            "street":"Barshi Road",
            "city":"Latur",
            "postalCode":"413512"
        }],
        "mobileNumber":"9899897456"
    }
    }


3. Get All Account details
   
Endpoint: GET

URL: http://localhost:8080/accounts

Headers:

Authorization: {Brearer {Token}}


3.Get Account Details based on Account Number

Endpoint: GET

URL: http://localhost:8080/account/{Account_Number}

Headers:

Authorization: {Brearer {Token}}


4.Update Account Details based on Account Number

Endpoint: PUT

URL: http://localhost:8080/account/{Account_Number}

Headers:

Authorization: {Brearer {Token}}

Request Payload:

     {
      "accountNumber":"12386",
      "accountType":"Current",
      "branchName":"Phase 2",
      "ifscCode":"234567",
      "balance":"6500",
      "customer":{
          "custId" :"1",
          "customerName":"Ajay Gaikwad",
          "address":[{
              "addressId":"1",
              "addressType":"Current",
              "buildingName":"Apurva Hills",
              "street":"Phase 2",
              "city":"Pune",
              "postalCode":"345673"
          },
          {
              "addressId":"2",
              "addressType":"Permanent",
              "buildingName":"Ram Nivas",
              "street":"Barshi Road",
              "city":"Latur",
              "postalCode":"413512"
          }],
          "mobileNumber":"9899897456"
      }
    }


5. Delete Account in the Bank
   
Endpoint: DELETE

URL: http://localhost:8080/account/{Account_Number}

Headers:

Authorization: {Brearer {Token}}


6. Delete All Account in the Bank
   
Endpoint: DELETE

URL: http://localhost:8080/accounts

Headers:

Authorization: {Brearer {Token}}


7. Fund Transfer from one account to Different account
   
Endpoint: PUT

URL: http://localhost:8080/fundTransfer/{From_Account}/{To_Account}/{AMOUNT}

Headers:

Authorization: {Brearer {Token}}


	Application Setup and Run
		- Set the port and DB details in applicaiton.properties
		- Run the application as spring boot application

	Service End points
		- Authenticate [POST]
			-	http://<ip:port>/authenticate
			-   Request
					{
    					"username":"admin",
    					"password":"admin"
					}
			- Response
					{ "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYyMDk0Mzc0NywiaWF0IjoxNjIwOTI1NzQ3fQ.ULuiTwd04-_IsVN3LF56-NEw2hK4fpAJqhyPi8-6rhgSgIFy_cYkSdIEAPFEpq90nZoclUxW8xfVBOA1BrVq2A" }
					
		- Search [POST]
			- http://<ip:port>/searchAccount
			- Please set authorization token in header as 'bearer <token>'
			- Request 
					{     "accountID": "3",    "startDate": "",    "endDate": "",    "startAmountRange": "",    "endAmountRange": ""}
			- Response
					[
    				{
        				"id": 24,
        				"accountVO": {
          				"id": 3,
        			    "accountType": "current",
            			"accountNumber": "d5cb8df5c2159f5c9cde22e733ab5376"
        				},
        				"date": "24.01.2021",
        				"amount": "564.982890505824"
    					},
    					{
        				"id": 25,
        				"accountVO": {
            				"id": 3,
           				"accountType": "current",
            				"accountNumber": "d5cb8df5c2159f5c9cde22e733ab5376"
        				},
        				"date": "29.11.2020",
        				"amount": "350.793682741483"
    				}


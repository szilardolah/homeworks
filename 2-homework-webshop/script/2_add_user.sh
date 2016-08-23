curl --verbose --write-out '\n' --data '{
			"username" : "Septumpolenta12345",
			"password" : "..<edUard>..",
			"email": "jeff.torres59@example.com",
			"firstname": "Jeff",
			"lastname": "Torres",
			"address": "6685 Hill Rd",
			"phone": "+36259364863",			
			"sex": "MALE",
			"dateOfBirth": "1974-08-11",
			"admin": false
		}' --header "Content-Type: application/json"  --cookie cart-cookie.txt --cookie-jar cart-cookie.txt --request POST 'http://localhost:8080/MiniWebshop-web-1.0-SNAPSHOT/rest/user'


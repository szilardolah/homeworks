curl --verbose --write-out '\n' --data '{
	"username" : "admin",
	"password" : "admin",
	"email": "samel.ross@example.com",
	"firstname": "Samel",
	"lastname": "Ross",
	"address": "5655 Oregon Santa Ana",
	"phone": "+36102365478",			
	"sex": "MALE",
	"dateOfBirth": "1980-07-07",
	"admin": true
}' --header "Content-Type: application/json" --cookie cart-cookie.txt --cookie-jar cart-cookie.txt --request POST 'http://localhost:8080/MiniWebshop-web-1.0-SNAPSHOT/rest/user/login'


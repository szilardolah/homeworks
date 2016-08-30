curl --verbose --write-out '\n' --data '{
			"state" : "REST",
			"money" : 10000,
			"age" : 18
		}' --header "Content-Type: application/json"  --cookie cart-cookie.txt --cookie-jar cart-cookie.txt --request POST 'http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/guest'


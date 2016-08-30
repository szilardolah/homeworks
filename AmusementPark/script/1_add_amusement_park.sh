curl --verbose --write-out '\n' --data '{
			"name" : "RollerCoaster",
			"address" : {
				"postalCode" : "H-8200",
				"country" : "Hungary",
				"city" : "Budapest",
				"street" : "Fehervari",
				"houseNumber" : "3/A"
			},
			"fund": 10000000,
			"area": 3000,
			"ticketPrice" : 1500
		}' --header "Content-Type: application/json"  --cookie cart-cookie.txt --cookie-jar cart-cookie.txt --request POST 'http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/park/'


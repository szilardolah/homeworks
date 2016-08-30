curl --verbose --write-out '\n' --data '{
			"name" : "RollerCoaster100",
			"address" : {
				"postalCode" : "H-8200",
				"country" : "Hungary",
				"city" : "Veszprem",
				"street" : "Fehervari",
				"houseNumber" : "3/A"
			},
			"fund": 10000000,
			"areaInKm": 3000
		}' --header "Content-Type: application/json"  --cookie cart-cookie.txt --cookie-jar cart-cookie.txt --request PUT 'http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/park/1'


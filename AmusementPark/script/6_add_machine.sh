curl --verbose --write-out '\n' --data '{
			"alias" : "heaven",
			"size" : 300,
			"ticketPrice" : 800,
			"machinePrice" : 500000,
			"seats" : 20,
			"type" : "DODGEM",
			"minAge" : 14
		}' --header "Content-Type: application/json"  --cookie cart-cookie.txt --cookie-jar cart-cookie.txt --request POST 'http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/machine/'


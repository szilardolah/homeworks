curl --verbose --write-out '\n' --data '{
			"text" : "It was amazing! Oszi"
		}' --header "Content-Type: application/json"  --cookie cart-cookie.txt --cookie-jar cart-cookie.txt --request POST 'http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/book/1/3'


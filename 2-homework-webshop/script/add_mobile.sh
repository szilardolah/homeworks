curl --verbose --write-out '\n' --data '{
	"id":"",
	"type":"p100 lite","manufacturer":"HUAWEI",
	"price":79490,"currency":"HUF",
	"color":"WHITE"
}' --header "Content-Type: application/json" --cookie-jar cart-cookie.txt --cookie cart-cookie.txt --request POST 'http://localhost:8080/MiniWebshop-web-1.0-SNAPSHOT/rest/mobile/add'


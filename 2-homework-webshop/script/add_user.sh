curl --verbose --write-out '\n' --data '{
        "id": "a6e38624-12cf-43d0-987d-ecd6e8c231c4",
        "type": "KK8",
        "manufacturer": "ONEPLUS",
        "price": 37990,
        "currency": "HUF",
        "color": "WHITE"
    }' --header "Content-Type: application/json"  --cookie cart-cookie.txt --cookie-jar cart-cookie.txt --request POST 'http://localhost:8080/MiniWebshop-web-1.0-SNAPSHOT/rest/cart/add'


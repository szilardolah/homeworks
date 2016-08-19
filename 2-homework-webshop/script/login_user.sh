curl --verbose --write-out '\n' --data '{
        "username": "oilericsson4",
        "password": "DiggEr42..,",
        "firstname": "Vivan",
        "lastname": "Cook",
        "address": "3880 W Lone Mountain Rd",
        "phone": "+36142637489",
        "email": "vivan.cook74@example.com",
        "sex": "FEMALE",
        "registrationDate": 1471617031404,
        "dateOfBirth": 265507200000,
        "admin": false
    }' --header "Content-Type: application/json" --cookie cart-cookie.txt --cookie-jar cart-cookie.txt --request POST 'http://localhost:8080/MiniWebshop-web-1.0-SNAPSHOT/rest/user/login'


# **Amusement park _(Szilard Olah)_**

###### About project:

  - Used server: GlassFish 4.0
  - sonar [link](http://sonar.codingmentor.xyz/overview?id=com.szilardolah%3AAmusementPark)
  - example scripts attached

### **Content**
###### Base steps
* Add an amusement park
* add a machine
* add a guest

###### Next steps
* Add a machine to a park
* Add a guest to a park
* Add a guest to a machine in a park (machine must be open!)
* write a note to a park's guestbook

---
# _**Base steps**_
### Add an amusement park
Example JSON:
```json
{
	"name" : "National",
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
} 
```
| HTTP  |  | JSON required|
|---	|---	| ---|
| **POST**  | http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/park/         | true  |
| PUT   | http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/park/{park_id}| true  |
| DELETE| http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/park/{park_id}| --- |
|GET	| http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/park/{park_id}| --- |
|GET    | http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/park/         | --- |
### Add a machine
Example JSON:
```json
{
	"alias" : "alias_name",
	"size" : 300,
	"ticketPrice" : 800,
	"machinePrice" : 500000,
	"seats" : 20,
	"type" : "DODGEM",
	"minAge" : 14
}
```
| HTTP  |  | JSON required|
|---	|---	| ---|
| **POST**  | http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/machine/        | true  |
| DELETE| http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/machine/{machine_id}| --- |
|GET	| http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/machine/{machine_id}| --- |
|GET    | http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/machine/      | --- |
### Add a guest
Example JSON:
```json
{
	"state" : "REST",
	"money" : 10000,
    "age" : 18
}
```
| HTTP  |  | JSON required|
|---	|---	| ---|
| **POST**  | http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/guest/        | true  |
| DELETE| http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/guest/{guest_id}| --- |
|GET	| http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/guest/{guest_id}| --- |
|GET    | http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/guest/      | --- |

# _**Next steps**_
### Add a machine to a park
| HTTP  |  | JSON required|
|---	|---	| ---|
| **POST**  | http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/park/{park_id}/machine/{machine_id}        | true |
| DELETE| http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/park/{park_id}/machine/{machine_id}| --- |
### Add a guest to a park
| HTTP  |  | JSON required|
|---	|---	| ---|
| **POST**  |  http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/park/{park_id}/guest/{guest_id}        | true  |
| DELETE| http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/park/{park_id}/guest/{guest_id}| --- |
### Add a guest to a machine in a park 
Firstly, You have to `open` the selected machine, 

| HTTP  |  | JSON required|
|---	|---	| ---|
| **PUT**  |  http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/park/{park_id}/open/{machine_id} | ---  |
| PUT  |  http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/park/{park_id}/close/{machine_id} | ---  |

After that, you can add the guest to the machine.

| HTTP  |  | JSON required|
|---	|---	| ---|
| **POST**  |  http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/park/{park_id}/machine/{machine_id}/guest/{guest_id}| true  |
| DELETE| http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/park/{park_id}/machine/{machine_id}/guest/{guest_id}| --- |
### Write a note to a park’s guestbook
When you create a park, then it's _guestbook entity_ automatically creates.
```json
{
	"text" : "It was amazing! Oszi"
}
```
| HTTP  |  | JSON required|
|---	|---	| ---|
| **POST**  | http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/book/{park_id}/{guest_id}   | true  |
| GET | http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/book/{park_id}/{guest_id}| --- |
| GET | http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/book}| --- |

# _**Queries**_
| Functionality  |  |
|---	|---	|
| Get guests by state (REST) | http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/park/{park_id}/rest |
| Get guests of a machine | http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/park/{park_id}/machine/{machine_id} |
| Get notes by guest id | http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/book/note/{park_id}/{guest_id}|
| Get parks with nonzero fund | http://localhost:8080/AmusementPark-web-1.0-SNAPSHOT/rest/park/nonzero|

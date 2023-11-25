### API Contracts & JSON
* API contracts are a popular means for API providers and consumers 
* to agree upon how an API will behave.
* API contracts can be as simple as shared documentation to sophisticated contract management and validation frameworks.
* Combined with JSON contracts can be a powerful means to help API providers and consumers develop and test APIs.

Request
    URI: /cashcards/{id}
    HTTP Verb: GET
    Body: None

Response:
    HTTP Status:
    200 OK if the user is authorized and the Cash Card was successfully retrieved
    403 UNAUTHORIZED if the user is unauthenticated or unauthorized
    404 NOT FOUND if the user is authenticated and authorized but the Cash Card cannot be found
    Response Body Type: JSON

Example Response Body:
    {
        "id": 99,
        "amount": 123.45
    }

### Testing First The Red, Green, Refactor Loop
* Write a failing test
* Use TDD to test a JSON data contract
* Use TDD to test JSON deserialization

* Red: Write a failing test for the desired functionality.
* Green: Implement the simplest thing that can work to make the test pass.
* Refactor: Look for opportunities to simplify, reduce duplication, or otherwise improve the code without changing any behavior—to refactor.
* Repeat!

### REST, CRUD, and HTTP (Representational State Transfer)
* REST (Representational State Transfer)
* Data objects are called Resource Representations
* The purpose of a RESTful API is to manage the state (value) of these Resources (objects).
* HTTP: Request(Method, URI, Body)
* HTTP: Response(Status Code, Body)
* METHODS POST/CREATE, GET/READ, PUT/UPDATE, DELETE/DELETE

### The endpoint URI for Cash Card objects begins with the /cashcards
* read, update and delete require a Cash Card with the identifier of "42"
* URI endpoint will be /cashcards/42

### Implementing GET
* Write a Spring Boot test for the GET endpoint
* Create a REST Controller
* Add the GET endpoint to the Controller
* Learn about and use the @PathVariable annotation

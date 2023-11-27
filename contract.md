#### Module 1 Creating RESTful Endpoints, Start
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

### Repositories & Spring Data
* Add Spring Data to our application
* Create the CashCardRepository
* Use Dependency Injection to make our CashCardRepository available for use
* Configure an in-memory Database
* Use the CashCardRepository for data management

#### Module 1 Creating RESTful Endpoints. End

#### Module 2 Developing a Secure App, Start
### Implementing POST
* [**Implementing POST**] ([https://spring.academy/courses/building-a-rest-api-with-spring-boot/lessons/implementing-post])
## objectives
* Start with a failing test for the desired HTTP POST endpoint
* Add and implement the POST endpoint
* Learn about semantic correctness
* Understand and use CrudRepository.save

## HTTP Semantics and Content (RFC 7231)
* If one or more resources has been created on the origin server as a result of successfully processing a POST request,
* the origin server SHOULD send a 201 (Created) response containing a Location header field that provides an identifier
* for the primary resource created.

### Returning a list with GET
* [**Implementing POST**] (https://spring.academy/courses/building-a-rest-api-with-spring-boot/lessons/get-list)
* Let's go implement lists, paging, and sorting in our own Family Cash Card API
* We've made the following changes from the previous lab.
* Added a couple more Cash Card data fixtures to data.sql
* Refactored CashCardJsonTest.java to incorporate the new data fixtures.
* Renamed expected.json to single.json, and added another data contract JSON file: list.json.
* Added some imports to the Test classes, so you don't have to!
* Added the @DirtiesContext annotation to the CashCardApplicationTests class.

### objectives
* Test the new Cash Card "list" JSON data contract
* Start with a failing test for a List endpoint
* Implement a GET endpoint for a list
* Implement pagination
* Implement sorting
* Combine paging and sorting and add default behavior
* Learn about test interaction and the @DirtiesContext annotation
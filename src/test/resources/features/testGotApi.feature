Feature: the GoT resources can be retrieved

  Scenario: client makes call to GET /books
    When the client requests all books
    Then the client receives status code of 200
    And the response contains the following books:
      | name              | isbn           | author              |
      | A Game of Thrones | 978-0553103540 | George R. R. Martin |
      | A Clash of Kings  | 978-0553108033 | George R. R. Martin |

  Scenario: client makes call to GET /books/1
    When the client requests book with id 1
    Then the client receives status code of 200
    And the response contains a book with name "A Game of Thrones" and isbn "978-0553103540"

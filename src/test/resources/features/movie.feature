Feature:
  Search movie by id

  Scenario: The requested movie exists in the database
    When I request a movie with id 2
    Then I get information about the film.
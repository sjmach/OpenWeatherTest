Meta: Search using City Names

Narrative:
  In order to use the application properly
  As a potential user
  I want to be able to search for cities to get the temperature for those cities

Scenario: An error is thrown if invalid city name is entered
Given I am on home page of the application
When I enter a city name <city>
Then I should get error response
Examples:
     | city|
|sfddfgsdgg|
|Munissch|
|Donssaadadad|

Scenario: Proper results are shown if the city name entered is correct
Given I am on home page of the application
When I enter a city name <city>
Then I should get a valid response
Examples:
     | city|
|Mumbai|
|Washington|
|Munich|
|Navi Mumbai|
|Chennai|
$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/steps/ShipValidation.feature");
formatter.feature({
  "line": 1,
  "name": "Validate ships",
  "description": "In order to avoid cheating\r\nAs a player\r\nI want to be notified if my ship has an invalid placement",
  "id": "validate-ships",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 7,
  "name": "Valid ship placement",
  "description": "",
  "id": "validate-ships;valid-ship-placement",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 6,
      "name": "@Runme"
    }
  ]
});
formatter.step({
  "line": 8,
  "name": "I have a 5 ship with 5 positions",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "I check if the ship is valid",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "the result should be true",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "5",
      "offset": 9
    },
    {
      "val": "5",
      "offset": 21
    }
  ],
  "location": "ShipValidationSteps.i_have_a_size_ship_with_positions(int,int)"
});
formatter.result({
  "duration": 770372290,
  "status": "passed"
});
formatter.match({
  "location": "ShipValidationSteps.i_check_if_the_ship_is_valid()"
});
formatter.result({
  "duration": 1755276,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "true",
      "offset": 21
    }
  ],
  "location": "ShipValidationSteps.the_result_should_be(boolean)"
});
formatter.result({
  "duration": 3257877,
  "status": "passed"
});
formatter.scenario({
  "line": 13,
  "name": "Invalid ship placement",
  "description": "",
  "id": "validate-ships;invalid-ship-placement",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 12,
      "name": "@Runme"
    }
  ]
});
formatter.step({
  "line": 14,
  "name": "I have a 5 ship with 4 positions",
  "keyword": "Given "
});
formatter.step({
  "line": 15,
  "name": "I check if the ship is valid",
  "keyword": "When "
});
formatter.step({
  "line": 16,
  "name": "the result should be false",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "5",
      "offset": 9
    },
    {
      "val": "4",
      "offset": 21
    }
  ],
  "location": "ShipValidationSteps.i_have_a_size_ship_with_positions(int,int)"
});
formatter.result({
  "duration": 288769,
  "status": "passed"
});
formatter.match({
  "location": "ShipValidationSteps.i_check_if_the_ship_is_valid()"
});
formatter.result({
  "duration": 32256,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "false",
      "offset": 21
    }
  ],
  "location": "ShipValidationSteps.the_result_should_be(boolean)"
});
formatter.result({
  "duration": 105217,
  "status": "passed"
});
});
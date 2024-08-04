@tag
Feature: Calculator

    Scenario: add two numbers
        Given Two input values, 1 and 2
        When I add the two values
        Then I expect the result 3


    Scenario: calculate square root of a/b
        Given Two input values, a = 4 and b = 1
        When I calculate the square root of a/b
        Then I expect the result to be 2
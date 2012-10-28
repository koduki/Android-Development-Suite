Feature: Example feature
    Scenario: Calculate BMI Height.
        When I see "Android Example"
        And take picture
        Then I enter "172" into "height"
        And take picture
        Then I enter "70" into "weight"
        And take picture
        Then I press the "Calc BMI" button
        And take picture
        Then I press the "Post Twitter" button

    Scenario: Calculate BMI Low.
        When I see "Android Example"
        And take picture
        Then I enter "160" into "height"
        And take picture
        Then I enter "51" into "weight"
        And take picture
        Then I press the "Calc BMI" button
        And take picture
        Then I press the "Post Twitter" button

Feature: NewTest

Scenario: tc login
  Given User Launch "Google Chrome"
When User opens URL "https://www.google.com/"
  And User clic in input has xpath "/html[1]/body[1]/div[1]/div[3]/form[1]/div[2]/div[1]/div[1]/div[1]/div[2]/input[1]" and Value as "chien"
  And User clic on element has xpath "/HTML[1]/BODY[1]"
  And close browser
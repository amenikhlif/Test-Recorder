Feature: tr22

Scenario: test1 login
  Given User Launch "Google Chrome"
When User opens URL "http://51.75.85.75/#/account/login"
  And User clic on element has xpath "/HTML[1]/BODY[1]"
  And User clic in input has xpath "/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/input[1]" and Value as "aymen.almia@gmail.com"
  And User clic in input has xpath "/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/input[1]" and Value as "password"
  And User clic in button has xpath "/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/button[1]"
  And User clic in input has xpath "/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/input[1]" and Value as "aymen@gmail.com"
  And User clic in button has xpath "/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/button[1]"
  And close browserFeature: tr22

Scenario: test1 login
  Given User Launch "Google Chrome"
When User opens URL "http://51.75.85.75/#/account/login"
  And User clic on element has xpath "/HTML[1]/BODY[1]"
  And User clic in input has xpath "/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/input[1]" and Value as "aymen.almia@gmail.com"
  And User clic in input has xpath "/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/input[1]" and Value as "password"
  And User clic in button has xpath "/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/button[1]"
  And User clic in input has xpath "/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/input[1]" and Value as "aymen@gmail.com"
  And User clic in button has xpath "/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/button[1]"
  And close browser
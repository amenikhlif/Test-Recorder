Feature: END

Scenario: close
  Given User Launch "Google Chrome"
When User opens URL "http://51.75.85.75/#/account/login"
  And User clic on element has xpath "/HTML[1]/BODY[1]"
  And User clic in input has xpath "/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/input[1]" and Value as "aymen.almia@gmail.com"
  And User clic in input has xpath "/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/input[1]" and Value as "password"
  And User clic in button has xpath "/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/button[1]"
  And User clic in input has xpath "/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/input[1]" and Value as "aymenalmia@gmail.com"
  And User clic in button has xpath "/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/button[1]"
  And User clic in button has xpath "/html[1]/body[1]/div[2]/section[1]/div[1]/div[1]/div[1]/div[1]/button[1]"
  And User clic in lien has xpath "/html[1]/body[1]/div[2]/aside[1]/div[1]/nav[1]/ul[1]/li[4]/ul[1]/li[2]/a[1]"
  And User clic in button has xpath "/html[1]/body[1]/div[2]/section[1]/div[1]/div[1]/div[1]/div[1]/button[1]"
  And User clic on element has xpath "/HTML[1]/BODY[1]"
  And close browser
Feature: azerty

Scenario: qsdfgh
  Given User Launch "Google Chrome"
  When User opens URL "https://www.facebook.com/"
  And User clic in input has xpath "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/table[1]/tbody[1]/tr[2]/td[1]/input[1]" and Value as "bb@gmail.com"
  And User clic in input has xpath "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/table[1]/tbody[1]/tr[2]/td[2]/input[1]" and Value as "vvvvv"
  And User clic in input submit has xpath "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/table[1]/tbody[1]/tr[2]/td[3]/label[1]/input[1]"
  And User clic in input has xpath "/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[2]/input[1]" and Value as "99660055"
  And User clic in button has xpath "/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[3]/button[1]"
  And User clic in input has xpath "/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[2]/input[1]" and Value as "xcvbn,;741258963"
  And User clic in button has xpath "/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[3]/button[1]"
  And close browser
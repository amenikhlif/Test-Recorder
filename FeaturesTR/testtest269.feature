Feature: testtest

Scenario: aaa
  Given User Launch "Google Chrome"
When User opens URL "https://www.facebook.com/"
  And User clic in input has xpath "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/table[1]/tbody[1]/tr[2]/td[1]/input[1]" and Value as "ameni@yahoo.fr"
  And User clic in input has xpath "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/table[1]/tbody[1]/tr[2]/td[2]/input[1]" and Value as "hahahahaha"
  And User clic in input submit has xpath "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/table[1]/tbody[1]/tr[2]/td[3]/label[1]/input[1]"
  And User clic on element has xpath "/HTML[1]/BODY[1]"
  And User clic in lien has xpath "/html[1]/body[1]/div[2]/p[2]/a[1]"
  And close browser
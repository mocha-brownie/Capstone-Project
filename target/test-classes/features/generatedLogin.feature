Feature: DemoQA Login

Scenario: Login with user Amey
  Given user uses API to  enter username "Amey" and password "Nitns$344"
  Given user is on login page
  When user enters username "Amey" and password "Nitns$344"
  And clicks on login button
  And login result should be "success"
  Then I delete the user account using API

Scenario: Login with user Arjun
  Given user uses API to  enter username "Arjun" and password "Korde#123"
  Given user is on login page
  When user enters username "Arjun" and password "Korde#123"
  And clicks on login button
  And login result should be "success"
  Then I delete the user account using API

Scenario: Login with user Narendra
  Given user uses API to  enter username "Narendra" and password "Modi&234"
  Given user is on login page
  When user enters username "Narendra" and password "Modi&234"
  And clicks on login button
  And login result should be "success"
  Then I delete the user account using API

Scenario: Login with user Rahul
  Given user uses API to  enter username "Rahul" and password "Gandhi#123"
  Given user is on login page
  When user enters username "Rahul" and password "Gandhi#123"
  And clicks on login button
  And login result should be "success"
  Then I delete the user account using API

Scenario: Login with user DattuBhau
  Given user uses API to  enter username "DattuBhau" and password "YcceC#123"
  Given user is on login page
  When user enters username "DattuBhau" and password "YcceC#123"
  And clicks on login button
  And login result should be "success"
  Then I delete the user account using API

Scenario: Login with user Pepa
  Given user uses API to  enter username "Pepa" and password "Piggy%345"
  Given user is on login page
  When user enters username "Pepa" and password "Piggy%345"
  And clicks on login button
  And login result should be "success"
  Then I delete the user account using API


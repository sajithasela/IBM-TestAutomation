Feature: Credit Cards


  Scenario Outline: As a User, I should be able to visit the DBS web site and do a card comparision.
    Given launch the browser
    When open the url
    Then click cardMenu
    Then click creditCardMenu
    Then select "<card1>" and "<card2>"
    Then select compare button
    Then validate cards info "<card1>" and "<card2>"
    Then validate card benefits "<card1>" and "<card2>"

  Examples:
  |card1|card2|
  |  DBS Altitude Visa Signature Card  | DBS Black Visa Card  |







Feature: Is there Nikon D3X in the topic?
  Check if there is a Nikon D3X fraze in the topic detail of the 2nd item in the list

  Scenario: D3X in the details topic
    Given Search fraze
    When Check list of the matches in the topic
    Then It shouldn't be found

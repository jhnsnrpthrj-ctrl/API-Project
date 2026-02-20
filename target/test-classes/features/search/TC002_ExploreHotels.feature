Feature: Search Hotels in OMR Branch Hotel Application
  This feature verifies the hotel search functionality with different input combinations 
  and validates sorting, filtering, error messages, and header content.

  Background: 
    Given User is on the OMR Branch hotel page
    When User enters "sarankarthick167@gmail.com" and "Karthick@16798"
    Then User should verify success message after login "Welcome Karthick"

  @SearchAllFields
  Scenario Outline: Search hotels with all fields
    When User searches hotel "<State>", "<City>", "<Room type>", "<Check in date>", "<Check out date>", "<No of Rooms>", "<No of Adults>" and "<No of Children>"
    Then User should verify after search hotel success message "Select Hotel"

    Examples: 
      | State      | City    | Room type | Check in date | Check out date | No of Rooms | No of Adults | No of Children |
      | Tamil Nadu | Chennai | Standard  |      30       |       31       |     1       |       1      |        1       |

  @SearchMandatory
  Scenario Outline: Search hotels with only mandatory fields
    When User searches hotel "<State>", "<City>", "<Check in date>", "<Check out date>", "<No of Rooms>" and "<No of Adults>"
    Then User should verify after search hotel success message "Select Hotel"

    Examples: 
      | State      | City    | Check in date | Check out date | No of Rooms | No of Adults |
      | Tamil Nadu | Chennai |      30       |       31       |       2     |       3      |   
      
      
  @SearchWithoutFields
  Scenario: Search hotels without entering any fields
    And User clicks Search button
    Then User should verify after search hotel error message "Please select State", "Please select City", "Please select Check-in date", "Please select Check out date", "Please select no. of rooms" and "Please select no. of adults"

  @SearchSortPrice
  Scenario Outline: Search hotels and verify price sorting from low to high
    When User searches hotel "<State>", "<City>", "<Room type>", "<Check in date>", "<Check out date>", "<No of Rooms>", "<No of Adults>" and "<No of Children>"
    Then User should verify after search hotel success message "Select Hotel"
    When User clicks sort from low to high
    Then User should verify after sorting that prices are listed from low to high

    Examples: 
      | State      | City    | Room type | Check in date | Check out date | No of Rooms | No of Adults | No of Children |
      | Tamil Nadu | Chennai | Standard  |      30       |       31       |       2     |       3      |        1       |

  @SearchSortNameDesc
  Scenario Outline: Search hotels and verify name sorting in descending order
    When User searches hotel "<State>", "<City>", "<Room type>", "<Check in date>", "<Check out date>", "<No of Rooms>", "<No of Adults>" and "<No of Children>"
    Then User should verify after search hotel success message "Select Hotel"
    When User clicks sort from descending order
    Then User should verify after sorting that names are in descending order

    Examples: 
      | State      | City    | Room type | Check in date | Check out date | No of Rooms | No of Adults | No of Children |
      | Tamil Nadu | Chennai | Standard  |      30       |       31       |       2     |       3      |        1       |

  @SearchSuiteEndsWith
  Scenario Outline: Search hotels and verify room type ends with Suite
    When User searches hotel "<State>", "<City>", "<Room type>", "<Check in date>", "<Check out date>", "<No of Rooms>", "<No of Adults>" and "<No of Children>"
    Then User should verify after search hotel success message "Select Hotel"
    When User applies filter for room types ending with "Suite"
    Then User should verify that all listed room types end with "Suite"


    Examples: 
      | State      | City    | Room type | Check in date | Check out date | No of Rooms | No of Adults | No of Children |
      | Tamil Nadu | Chennai | Standard  |      30       |       31       |       2     |       3      |        1       |

  @SearchVerifyHeader
  Scenario Outline: Search hotels and verify the header contains room type
    When User searches hotel "<State>", "<City>", "<Room type>", "<Check in date>", "<Check out date>", "<No of Rooms>", "<No of Adults>" and "<No of Children>"
    Then User should verify after search hotel success message "Select Hotel"
    And User should verify the header contains "<Room type>"

    Examples: 
      | State      | City    | Room type | Check in date | Check out date | No of Rooms | No of Adults | No of Children |
      | Tamil Nadu | Chennai | Standard  |      30       |       31       |       2     |       3      |        1       |
 
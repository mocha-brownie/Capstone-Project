Feature: Performance Testing

  Scenario: Run JMeter performance test
    Given I execute JMeter test
    Then response time should be below 2500 ms

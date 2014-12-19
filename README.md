tdd-spock
=========

Test Driven Design / Development examples in Spock 

Test first
  benefit of writing tests first: focus on the external api

Know what you’re testing
  ref:   Great discussion of when you should write a test, when you shouldn’t, and what you should name your tests. Done using Spock. Shows the maturity of the TDD discipline in the Java ecosystem. http://www.javacodegeeks.com/2012/09/test-driven-traps-part-1.html (search for ‘Verify only the right thing’).

Don’t whitebox test
  General (Java) intro ref: https://bitbucket.org/bwestrich/java-tdd/wiki/Perils%20of%20Whitebox%20testing

Use Behavior oriented spock syntax: 
  use given:, not when: [todo-write spock example]
  When using where clause, put expectations at end of where clause [todo-write spock example]

Put mocks that support the test but don't test that which is supposed to be tested to the ‘given’ section [todo-write spock example]
  the 'then:' section should only contain assertions related to the functionality we intend to test
  mocking in the 'given' section should not verify number of calls, method parameters, etc., unless needed to support the test
  benefits: easier to read tests, easier to focus on what test is testing, tests are more resilient to refactoring of class under test 

Avoid test spies
  e.g. mcwest.learnspock.SpySpec
  though sometimes needed, often spies are a smell; perhaps a sign of white box testing or an indication that an object has too many responsibilities 

Write readable tests
 Write test methods that say what the software is expected to do (e.g. mcwest.NamingTestsSpec)
 Don't describe how you're testing 
 Avoid wide where clauses, look for opportunities to separate them into smaller separate test methods 

TODO: finish SmallerWhereSpec
TODO: finish rest of todo's above 
TODO: use 'asdfasdf' strings after colons
TODO: look at hamlet tests for more ideas, refer to his too
TODO: use comment as first field to explain complex where clauses 

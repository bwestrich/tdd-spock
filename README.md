#tdd-spock -- Test Driven Design Guidelines (with Spock examples)  
=========

The below guidelines (and their related code examples) will help you use Spock for Test Driven Development (TDD). Each code example is prefaced with the package name (e.g. `mcwest....`) For more on TDD, see [here](https://bitbucket.org/bwestrich/java-tdd/wiki/Home).

##Know what you’re testing
Here is a great discussion of the topic of knowing what you're testing. It discusses when to write a test, when you shouldn’t write a test, and how you should name your tests. All examples are in Spock. 

* http://www.javacodegeeks.com/2012/09/test-driven-traps-part-1.html (search for ‘Verify only the right thing’).

##Choose good test method names
 Choose test method names that say what the software is expected to do (e.g. `mcwest.NamingTestsSpec`). Your method names should not describe how you're testing.  

##Limit the size of 'where' tables  
 Avoid "wide" where tables, look for opportunities to break wide where tables into smaller separate test methods (e.g. `mcwest.SmallerWhereSpec`).

##Don’t whitebox test
Don't test the internal implementation of your classes, only test the external API. For more info, see [Perils of whitebox testing](https://bitbucket.org/bwestrich/java-tdd/wiki/Perils%20of%20Whitebox%20testing).

##Use behavior oriented spock syntax
  Use given:, not when: e.g. `mcwest.UsingGivenNotSetup`
 
##Use test sections appropriately
* Put support mocks (that only support the test) in the ‘given’ section (e.g. `mcwest.SeparateExpectationFromScaffoldingSpec`), not in the then: section. 
* The 'then:' section should only contain assertions related to the functionality we intend to test. 
* Mocking in the 'given' section should not verify number of calls, method parameters, etc., unless needed to support the test.
* Benefits include easier to read tests, better focus on what the test is supposed to test, tests more resilient to refactoring of the class under test. 

##Avoid test spies
* Spock supports use of test spies to  mock selected methods of the 'class under test' (e.g. `mcwest.learnspock.SpySpec`). 
* Though sometimes needed, spies are often a code smell; perhaps a sign of white box testing or an indication that an object has too many responsibilities. 

##Other concepts (no examples for these yet)
* TODO: when using where tables, put expected values in the rightmost column(s) of the table
* TODO: use static or @Shared variables to centralize variable initializations 
* TODO: use 'comment' strings after colons to make your tests more readable, e.g. given: 'the environment has been initialized' ......


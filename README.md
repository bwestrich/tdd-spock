#tdd-spock -- Test Driven Design Guidelines (with Spock examples)  
=========

The below guidelines (and their related code examples) will help you use Spock for Test Driven Development (TDD). Each code example is prefaced with the package name (e.g. `mcwest....`) For more on TDD, see [here](https://bitbucket.org/bwestrich/java-tdd/wiki/Home).

##Test the right thing
Here is a great discussion of the topic of testing the right thing. It discusses topics such as when to write a test, when you shouldn’t write a test, and how you should name your tests. All examples are in Spock. 

* http://www.javacodegeeks.com/2012/09/test-driven-traps-part-1.html (search for ‘Verify only the right thing’).

##Choose good test method names
 Choose test method names that say how the software is expected to function (see `mcwest.NamingTestsSpec` for more details). Your method names should not describe how you are testing.  

##Limit the size of 'where' tables  
 Avoid "wide" where tables and look for opportunities to break wide tables into smaller tables (e.g. `mcwest.SmallerWhereSpec`).

##Don’t whitebox test
Don't test the internal implementation of your classes, only test the external API. For more info, see [Perils of whitebox testing](https://bitbucket.org/bwestrich/java-tdd/wiki/Perils%20of%20Whitebox%20testing).

##Use test sections appropriately
Spock uses different sections for your test code (given/when/then/where...). Using these sections correctly can greatly improve the readability and value of your tests.  

* Put support mocks (that only support the test) in the ‘given’ section (e.g. `mcwest.SeparateExpectationFromScaffoldingSpec`), not in the then: section. 
* The 'then:' section should only contain assertions related to the functionality we intend to test. 
* Mocking in the 'given' section should not verify number of calls, method parameters, etc., unless needed to support the test.
* Benefits include easier to read tests, better focus on what the test is supposed to test, and tests that are more resilient to refactoring of the class under test. And (per Martin Fowler) easier refactoring is one of the main reasons why we write unit tests. 

##Use test section names that have behavior-oriented syntax
  Use given:, not when: e.g. `mcwest.UsingGivenNotSetupSpec`
 
##Use spies cautiously
* Spock supports use of test spies to  mock selected methods of the 'class under test' (e.g. `mcwest.learnspock.SpySpec`). 
* Though sometimes needed, spies are often a code smell; perhaps a sign of white box testing or an indication that an object has too many responsibilities. 

##Other concepts (no examples for these yet)
* TODO: example of using a closure with a mock to troubleshoot why the calling args of the class under test don't match those of the mock
* TODO: when using where tables, put expected values in the rightmost column(s) of the table
* TODO: use static or @Shared variables to centralize variable initializations 
* TODO: use 'comment' strings after colons to make your tests more readable, e.g. given: 'the environment has been initialized' ......


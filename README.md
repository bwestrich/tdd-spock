#tdd-spock -- Test Driven Design Guidelines (with Spock examples)  

*"If you want to refactor, the essential precondition is having solid tests.”* 

 -- Martin Fowler (from ‘Refactoring: Improving the Design of Existing Code’, Addison-Wesley, 1999)

The below guidelines (and their related code examples) can help you effectively use Spock for Test Driven Development (TDD). Such tests become an "Asset instead of an Afterthought". Each code example mentioned below is prefaced with the package name (e.g. `mcwest....`).
For more on TDD, including code examples and a Java/IntelliJ/Eclipse TDD cribsheet, see [here](https://bitbucket.org/bwestrich/java-tdd/wiki/Home).

##Test the right thing
* In short, only test functionality that the system under test (SUT) provides to its consumers. Do not test the internal implementation of the class. 
* Here is a great discussion of testing the right thing. It discusses topics such as when to write a test, when you shouldn’t write a test, 
and how you should name your tests. All examples are in Spock. 
http://www.javacodegeeks.com/2012/09/test-driven-traps-part-1.html (search for ‘Verify only the right thing’).

##Name your methods well
*  The name of your test method is the most important part of your test. While writing a test, this name helps you focus on testing the right thing. Later, when deciding how to enhance or fix your code, the name is the first thing you’ll refer to (since testing is the first step in both enhancing and fixing code). Lastly, the test method name helps you decide when a test is no longer needed and should be deleted. 
* Make the method name as **short** as possible while capturing the essence of the tested functionality. 
* Choose a method name that states **what** the SUT should do, **not how** you are testing it ('returns a boolean' is better than 'verify we return a boolean'). 
* **Do not use 'should', 'test', or 'verify'** in the method name, these words are self-evident and make test method names more wordy ('calculates price based on discount' is better than 'should calculate price based on discount').
* **Do not use a groovy method name**, use a text based method comment (see code examples). 
* For code examples, see `mcwest.NamingTestsSpec`. nb: Of all the code examples provided in this repo, the examples for this point need the most improvement (suggestions/pull requests welcome of course!).

##Name your test classes based on your classes under test
The name of your software test class should be {ClassUnderTest}Spec to allow for relating tests to the SUT.

##Don’t whitebox test
Don't test the internal implementation of SUT, only test the external API.
* See the next points related to how to best use different types of test doubles in your Spock tests. 
* Also see [Perils of whitebox testing](https://bitbucket.org/bwestrich/java-tdd/wiki/Perils%20of%20Whitebox%20testing).

##Remember key differences between types of test doubles
* Stubs: provide canned answers to calls (to other objects) made during a test. They allow you to write tests on an object without having to implement the objects it calls. There is no expectation on which stub methods will be called during a test. 
* Mocks: stubs that expect to receive specific calls. Part of the test outcome is to evaluate that mock expecations were met.  
Note: Spock uses the same class (Mock) to implement both Stubs and Mocks, which makes it harder to keep the distinction between Stubs and Mocks in mind. 

##Use different test double types appropriately
Spock uses different sections for your test code (given/when/then/where...). Using these sections correctly can greatly improve the readability and value of your tests. 

* Put **stubs** in the **‘given:’** section. This section should not verify number of calls, method parameters, etc. These verifications make your tests harder to read and less resilient to refactoring 

* Put **mocks** in the **'then:'** section.  

See `mcwest.StubsAndMocksSpec` for examples of using stubs and mocks appropriately. 
 
##Use behavior-oriented test section names
  Use given:, not setup: e.g. `mcwest.UsingGivenNotSetupSpec`
 
##Limit size of where tables  
 Break wide tables into smaller tables (e.g. `mcwest.SmallerWhereSpec`).

##Use spies cautiously
* Spock supports use of test spies to  mock selected methods of the 'class under test' (e.g. `mcwest.learnspock.SpySpec`). 
* Though sometimes needed, spies are often a code smell; perhaps a sign of white box testing or an indication that an object has too many responsibilities. 

##Other concepts (no examples for these yet)
* TODO: when using where tables, put expected values in the rightmost column(s) of the table
* TODO: use static or @Shared variables to centralize initialization of variables that are used in where tables 
* TODO: use thrown/notThrown to verify exception behavior
* TODO: use 'comment' strings after colons to make your tests more readable, e.g. given: 'the environment has not been initialized properly' ......

##References

### Testing

* http://www.martinfowler.com/bliki/TestDouble.html
* http://xunitpatterns.com/Mocks,%20Fakes,%20Stubs%20and%20Dummies.html

### Spock

#### Guides
* https://leanpub.com/spockframeworknotebook/read
* http://sett.ociweb.com/sett/settFeb2012.html

#### Cheatsheet
* https://github.com/craigatk/spock-mock-cheatsheet



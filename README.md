#tdd-spock -- Test Driven Design Guidelines (with Spock examples)  

*"If you want to refactor, the essential precondition is having solid tests.”* 

 -- Martin Fowler (from ‘Refactoring: Improving the Design of Existing Code’, Addison-Wesley, 1999)

The below guidelines (and their related code examples) can help you effectively use Spock for Test Driven Development (TDD) to create tests that are 
an Asset not an Afterthought". Each code example mentioned below is prefaced with the package name (e.g. `mcwest....`).
For info on using TDD with JUnit, including code examples and a Java/IntelliJ/Eclipse TDD cribsheet, see [here](https://bitbucket.org/bwestrich/java-tdd/wiki/Home).

##Only test the right thing
* Only test functionality that the class under test provides to its consumers. 
* Do not test the internal implementation of the class. 
* recommended: http://www.javacodegeeks.com/2012/09/test-driven-traps-part-1.html (search for ‘Verify only the right thing’).

##Use tests to specify the class under test
* Use the test to specify what the class under test does.
* Test all cases, listing the typical (non-edge) cases first.
* For code examples, see classes whose package names start with `mcwest.`

##Use behavior driven syntax 
* Spock allows many different syntaxes for tests.
* For all but the simplest tests, we recommend the given/when/then syntax (e.g. `mcwest.UseGivenNotSetupSpec`)
   (reason: more readable, and enforces correct operator use such as use of comparison (vs. assignment) in 'then').
* For very simple tests, use 'expect' syntax (see `mcwest.UseExpectForSimpleSpec`).

##Consider adding comments to given/when/then for complex tests
* Adding comments to given/when/then can clarify more complex tests. 
 (see `mcwest.ConsiderCommentsOnGivenWhenThenSpec`)

##Name your methods well
* The name of your test method is the most important part of your test. While writing a test, this name helps you focus on testing the right thing. Later, when deciding how to enhance or fix your code, the name is the first thing you’ll refer to (since testing is the first step in both enhancing and fixing code). Lastly, the test method name helps you decide when a test is no longer needed and should be deleted. 
* Make the method name as **short** as possible while capturing the essence of the tested functionality. 
* Choose a method name that states **what** the class under test should do, **not how** you are testing it ('returns a boolean' is better than 'verify we return a boolean'). 
* **Do not use 'should', 'test', or 'verify'** in the method name, these words are self-evident and make test method names more wordy ('calculates price based on discount' is better than 'should calculate price based on discount').
* **Do not use a groovy method name**, use a text based method comment (see code examples). 
* For code examples, see `mcwest.NamingTestsSpec`. nb: Of all the code examples provided in this repo, the examples for this point need the most improvement (suggestions/pull requests welcome of course!).

##Name your test class based on your class(es) under test
* The name of your software test class should be {ClassUnderTest}Spec to allow IDEs to relate tests to the class under test. 
* If we are testing a set of classes (some of which are package/private implementations), name the test after the public class.

##Don’t whitebox test
Don't test the internal implementation of the class under test, only test its external API.
* See the next points related to how to best use different types of test doubles in your Spock tests. 
* Also see [Perils of whitebox testing](https://bitbucket.org/bwestrich/java-tdd/wiki/Perils%20of%20Whitebox%20testing).

##Avoid Double Trouble
* Choose the right type of test double....
* Stubs: provide canned answers to calls (to other objects) made during a test. They allow you to write tests on an object without having to implement the objects it calls. There is no expectation on which stub methods will be called during a test. 
* Mocks: stubs that expect to receive specific calls. Part of the test outcome is to evaluate that mock expecations were met.  
Note: Spock uses the same class (Mock) to implement both Stubs and Mocks, which makes it harder to keep the distinction between Stubs and Mocks in mind. 
* Put **stubs** in the **‘given:’** section. This section should not verify number of calls, method parameters, etc. These verifications make your tests harder to read and less resilient to refactoring 
* Put **mocks** in the **'then:'** section.  
See `mcwest.StubsAndMocksSpec` for examples of using stubs and mocks appropriately. 
 
##Limit size of where tables  
 Break wide tables into smaller tables (e.g. `mcwest.SmallerWhereSpec`).

##Put expected values at end of where table  
 When using where tables, put expected values in the rightmost column(s) of the table, as this increases readability
(e.g. `mcwest.SmallerWhereSpec`).

##Spy cautiously
* Spock supports use of test spies to mock selected methods of the class under test (see `mcwest.learnspock.SpySpec`). 
* Though sometimes needed, spies are often a code smell; perhaps a sign of white box testing or an indication that an object has too many responsibilities. 

##Write tests for Spring web apps that work both standalone and in-container
Write controller tests out of container and then (by inheriting them) also run them in container. For more info on this approach, see README-MOCKMVC.md.

##Other concepts 
* TODO: use static or @Shared variables to centralize initialization of variables that are used in where tables.
* TODO: use thrown/notThrown to verify exception behavior.
* TODO: when writing Spock Spring tests, see if this fix (https://github.com/spockframework/spock/commit/718bf4261d3abaa6217ee059639c3aae2f8a5803) 
    replaces the workaround documented here (http://stackoverflow.com/questions/24405727/integration-test-with-spring-boot-and-spock).    

##References

### Testing

* http://www.martinfowler.com/bliki/TestDouble.html
* http://xunitpatterns.com/Mocks,%20Fakes,%20Stubs%20and%20Dummies.html

### Spock

#### Guides
* https://code.google.com/p/spock/wiki/SpockBasics: good starting place
* https://leanpub.com/spockframeworknotebook/read
* http://sett.ociweb.com/sett/settFeb2012.html
* http://odinodinblog.herokuapp.com/blog/2012/11/12/spock-and-spring 

#### Cheatsheet
* https://github.com/craigatk/spock-mock-cheatsheet

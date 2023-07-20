#tdd-spock -- Test Driven Design/Development Guidelines (with Spock examples)  

*"If you want to refactor, the essential precondition is having solid tests.”*  -- Martin Fowler (from ‘Refactoring: Improving the Design of Existing Code’, Addison-Wesley, 1999)

The below guidelines (and their related code examples) can help you effectively use 
Spock to create tests that are an "Asset not an Afterthought". 
Each code example mentioned below is prefaced with the package name (e.g. `mcwest....`). For more info, see the companion presentation at https://www.infoq.com/presentations/groovy-spock  

#Test the right thing
* Only test functionality that the class under test provides to its consumers.

##Don’t whitebox test
Don't test the internal implementation of the class under test, only test its external API.
* See the next points related to how to best use different types of test doubles in your Spock tests. 
* Also see [Perils of whitebox testing](https://bitbucket.org/bwestrich/java-tdd/wiki/Perils%20of%20Whitebox%20testing).

##Avoid Double Trouble
* To help avoid whitebox testing, choose the right type of double....
* Stubs: provide canned answers to calls (to other objects) made during a test. They allow you to write tests on an object without having to implement the objects it calls. There is no expectation on which stub methods will be called during a test. 
* Mocks: stubs that expect to receive specific calls. Part of the test outcome is to evaluate that mock expecations were met.  
Note: Spock uses the same class (Mock) to implement both Stubs and Mocks, which makes it harder to keep the distinction between Stubs and Mocks in mind. 
* Put **stubs** in the **‘given:’** section. This section should not verify number of calls, method parameters, etc. These verifications make your tests harder to read and less resilient to refactoring 
* Put **mocks** in the **'then:'** section.  
See `mcwest.StubsAndMocksSpec` for examples of using stubs and mocks appropriately. 
See http://www.martinfowler.com/bliki/TestDouble.html for definitions of doubles/stubs/mocks/fakes.....

##Don't test what's already been tested
* Don't save unit tests for code that is already unit tested elsewhere.
   For example, it's ok to write tests that test the Grails validator to learn how it works, 
   but delete such tests afterward.
* Reduce code coverage as you move from unit to integration and functional tests. 

##Fix or delete broken tests
* Immediately fix, ignore, or delete broken tests. 
* If a test needs to be temporarily broken, @Ignore it and add a // TODO: comment with your plan for un-ignoring (or deleting) it.
For example, see `mcwest.casestudies.calories.VaCalculateCaloriesFunctionalSpec`.

#Specify the tested functionality 
 
##Emphasize typical usage cases
* List the typical (non-edge) cases first, then the edge cases.
(e.g. `mcwest.SpecifyClassUnderTestSpec`).

##Name your methods well
* The name of your test method is the most important part of your test. While writing a test, this name helps you focus on testing the right thing. Later, when deciding how to enhance or fix your code, the name is the first thing you’ll refer to (since testing is the first step in both enhancing and fixing code). Lastly, the test method name helps you decide when a test is no longer needed and should be deleted. 
* Make the method name as **short** as possible while capturing the essence of the tested functionality. 
* Choose a method name that states **what** the class under test should do, **not how** you are testing it ('returns a boolean' is better than 'verify we return a boolean'). 
* **Do not use 'should', 'test', or 'verify'** in the method name, these words are self-evident and make test method names more wordy ('calculates price based on discount' is better than 'should calculate price based on discount').
* **Do not use a groovy method name**, use a text based method comment (see code examples). 
* For code examples, see `mcwest.NamingTestsSpec`. nb: Of all the code examples provided in this repo, the examples for this point need the most improvement (suggestions/pull requests welcome of course!).

##Show API test methods first
###... by sorting methods in test classes as follows: 
* happy path tests
* edge case tests
* non-test (utility) methods

##Name your test class based on your class(es) under test
* The name of your software test class should be {ClassUnderTest}Spec. This allows humans and IDEs to relate tests to the class under test. 
* If testing a set of classes (some of which are package/private implementations), name the test after the public class.


#Write readable where tables

##Always use @Unroll when using where tables  
 If you don't use @Unroll, all rows of the table are rolled up into a single report result. 
 (e.g. `mcwest.SpecifyClassUnderTestSpec`).

##Optionally use a comment column to document where tables  
 Consider adding a comment column as the first column to all tables, and printing it (via templating) in the method name
 (e.g. `mcwest.SpecifyClassUnderTestSpec`).  If you have an input variable that clearly specifies each row, you can 
 instead use that column (e.g. `mcwest.UseExpectForSimpleSpec`). 

##Put expected values (outputs) at the end of the where table  
 Put expected values in the rightmost column(s) of the table, as this increases readability
(e.g. `mcwest.SpecifyClassUnderTestSpec`).

##Separate inputs and expected outputs with a double vertical bar ('||') 
 When using where tables, put expected values in the rightmost column(s) of the table, as this increases readability
(e.g. `mcwest.SpecifyClassUnderTestSpec`). Note: IntelliJ has some minor glitches related to formatting these. 

##Limit size of where tables  
 Wide tables often mean you are testing too much in one test. 
 If possible, break wide tables into smaller tables (e.g. `mcwest.SmallerWhereSpec`).
 
##Full example of testing using tables
* See `mcwest.casestudies.calories`


#Misc. Spock tips

##Use behavior driven syntax 
* Spock allows many different syntaxes for tests.
* For all but the simplest tests, use given/when/then syntax (e.g. `mcwest.UseGivenNotSetupSpec`)
   (reason: more readable, and enforces correct operator use such as use of comparison (vs. assignment) in 'then').
* For very simple tests, use 'expect' syntax (see `mcwest.UseExpectForSimpleSpec`).

##Keep the when block as short as possible
* This makes it easy to understand what you are testing.
* Ideally, only ONE line. 

##Optionally add comments to given/when/then for complex tests
* Adding comments to given/when/then can clarify more complex tests
 (see `mcwest.ConsiderCommentsOnGivenWhenThenSpec`)

##Use closures to troubleshoot issues with double method matchers 
* If methods on your mocks or stubs are not getting matched, use closures to find out why. 
For examples using a method with single or with multiple arguments, 
see `mcwest.learnspock.UseClosureToDebugDoubleMatchers*`. 

##Spy cautiously
* Spock supports the use of test spies. 
* Spies allow some methods on the CUT to be mocked while others use their unmocked implementation
(see `mcwest.learnspock.SpySpec`). 
* Spies are usually only needed when testing legacy code or poorly designed code. 

##Write tests for Spring web apps that work both standalone and in-container
Write controller tests out of container and then (by inheriting them) also run them in container. 
This allows you to write these tests quickly (since they run fast) even though 
they eventually will also run in-container. 
For more info on this approach, see README-MOCKMVC.md.


#Concepts for which we don't have example code
* TODO: remove as much repetitive ("wide") code in where tables as possible. e.g. 
    e.g. this:   | 5 | 
    not this:    | new BigDecimal(5) |     
* TODO: use @Shared variables to centralize initialization of variables that are used in where tables.
* TODO: use thrown/notThrown to verify exception behavior.
* TODO: show example of using arguments passed into mocked methods via a closure, e.g. 
          service.getThingFromNumber(_) >> { arguments ->
              new Thing(number: arguments[0])
          }

#References

##Testing
* http://www.martinfowler.com/bliki/TestDouble.html
* http://xunitpatterns.com/Mocks,%20Fakes,%20Stubs%20and%20Dummies.html
* http://www.javacodegeeks.com/2012/09/test-driven-traps-part-1.html (search for ‘Verify only the right thing’).

##Spock

###Complete documentation
* https://code.google.com/p/spock/wiki/SpockBasics: good starting place
* Kapelonis, K (2015) Java Testing With Spock. Manning. :another good general reference
* https://leanpub.com/spockframeworknotebook/read
* http://sett.ociweb.com/sett/settFeb2012.html
* http://odinodinblog.herokuapp.com/blog/2012/11/12/spock-and-spring 

###Cheatsheet
* https://github.com/craigatk/spock-mock-cheatsheet

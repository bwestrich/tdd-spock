tdd-spock
=========

Test Driven Design / Development examples written in Spock 

Test first
  benefit of writing tests first: focus on the external api

Know what you’re testing
  ref:   Great discussion of when you should write a test, when you shouldn’t, and what you should name your tests. Done using Spock. Shows the maturity of the TDD discipline in the Java ecosystem. http://www.javacodegeeks.com/2012/09/test-driven-traps-part-1.html (search for ‘Verify only the right thing’).

Don’t whitebox test
  General (Java) intro ref: https://bitbucket.org/bwestrich/java-tdd/wiki/Perils%20of%20Whitebox%20testing
  use non-strict mock matching where possible [todo-write spock example]

Use Behavior oriented spock syntax: 
  given/when/then, not setup [todo-write spock example]
  When using where clause, put expectations at end of where clause

Move supporting mocks to ‘given’ from ’then' [todo-write spock example]
  easier to read tests
  easier to see what test is testing 

Avoid test spies [todo-write spock example]
  here is example of using them
  often they are a smell; perhaps a sign of white box testing or an indication that an object has too many responsibilities 

Write readable tests
 Write test methods that say what the software is expected to do [todo-write spock example]
 Don't describe how you're testing 
 Avoid wide where clauses, look for opportunities to separate them into smaller separate test methods 


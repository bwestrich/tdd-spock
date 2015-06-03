package mcwest.learnspock

import spock.lang.Specification

/*
This class shows how to use a closure to debug why a mock is not matching.
The closure prints out the argument values being passed to the mock,
which you can then compare to the values expected by the mock.
This technique was first suggested to me by my colleague Dave Hoffman.
 */

class UseClosureToDebugMockMatchersSpec extends Specification {

    def 'closures can be used to debug mocks whose parameter values do not match'() {

        // suggested order of use:
        // 1. failTest true, useClosureToDebug false  // test fails with null pointer exception
        // 1. failTest true, useClosureToDebug true // test passes but only because closure returns true, passed in arg value shown
        // 1. failTest false, useClosureToDebug false // test passes

        given:
        boolean failTest = false
        boolean useClosureToDebug = false
        ClassDependsOnClassUnderTest mockDependency = Mock(ClassDependsOnClassUnderTest)
        ClassUnderTest underTest = new ClassUnderTest(dependency: mockDependency)
        if (failTest) {
            // this mock does not match as param value is different than what class under test uses to call the mock,
            // leading to a null pointer exception
            mockDependency.convertString('Converted') >> "converted"
        } else {
            // this mock does match the param value, test passes
            mockDependency.convertString('Converted ') >> "converted"

            // this mock also will match, but doesn't give us insight into why the mismatch is happening
            //mockDependency.convertString(_) >> "converted"
        }
        if (useClosureToDebug) {
            mockDependency.convertString({ inputString ->
                println "'${inputString}'" // show what was actually passed into the mock.
                // This can be used to troubleshoot invalid mock setups, such as the above mock that was causing a npe.
                return true // Closure must return true, which forces matcher to match.
            }) >> "converted"
        }

        // note: if your mocked method has more than one parameter, use a separate closure for each parameter,
        // vs one parameter with multiple parameters
        //    do this: ({ p1 -> ...}, {p2 -> ...}) >> ...
        //   not this: ({ p1, p2 -> ...}) >> ...


        when:
        def actualString = underTest.convertString('Converted ')

        then:
        actualString == 'Processed string: converted'
    }

    class ClassUnderTest {
        ClassDependsOnClassUnderTest dependency

        String convertString(String s) {
            "Processed string: " + dependency.convertString(s)
        }
    }

    class ClassDependsOnClassUnderTest {
        String convertString(String s) {
            s.toLowerCase().trim()
        }
    }


}

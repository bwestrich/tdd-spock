package mcwest.learnspock

import spock.lang.Specification

/*
This class shows how to use a closure to debug why a mock is not matching.
The closure prints out the argument values being passed to the mock,
which you can then compare to the values expected by the mock.
 */

class UseClosureToDebugMockMatchersSpec extends Specification {

    def 'put scaffold mocks into given, testing mocks into then'() {

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
            mockDependency.convertString('Converted ') >> "converted"
        }
        if (useClosureToDebug) {
            // this mock does match the param value, test passes
            mockDependency.convertString({ inputString ->
                println "'${inputString}'" // show what was actually passed into the mock.
                // This can be used to troubleshoot invalid mock setups, such as the above line that was commented out.
                return true // must return true, or matcher will not match
            }) >> "converted"
        }

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

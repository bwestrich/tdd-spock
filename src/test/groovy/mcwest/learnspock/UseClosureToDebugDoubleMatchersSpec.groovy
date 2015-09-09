package mcwest.learnspock

import spock.lang.Specification

/*
This class shows how to use a closure to debug double method matching.
The closure prints out the argument values being passed to the double,
which you can then compare to the expected values.
This technique was first suggested to me by my colleague Dave Hoffman.
 */

class UseClosureToDebugDoubleMatchersSpec extends Specification {

    def 'closures can be used to debug doubles whose parameter values do not match actual calls'() {

        // suggested order of use:
        // 1. failTest true, useClosureToDebug false  // test fails
        // 1. failTest true, useClosureToDebug true // test passes but only because closure returns true, passed in arg value shown
        // 1. failTest false, useClosureToDebug false // test passes

        given:
        boolean failTest = false
        boolean useClosureToDebug = false
        ClassDependsOnClassUnderTest stubbedDependency = Stub(ClassDependsOnClassUnderTest)
        ClassUnderTest underTest = new ClassUnderTest(dependency: stubbedDependency)
        if (failTest) {
            // stub will not match,
            // as param value is different than what class under test uses to call it
            stubbedDependency.convertString('Converted?') >> "converted"
        } else {
            // stub does match param value, test passes
            stubbedDependency.convertString('Converted!') >> "converted"
        }
        if (useClosureToDebug) {
            stubbedDependency.convertString({ inputString ->
                println "'${inputString}'" // show what was actually passed to the stub.
                // This can be used to troubleshoot invalid stub setups,
                // such as the above stub that was causing a npe.
                return true // Closure returns true, which forces matcher to match.
            }) >> "converted"
        }

        // note: if your stubbed method has more than one parameter,
        // you must use a separate closure for each parameter
        // vs. one parameter with multiple parameters. That is....
        //    do this: ({ p1 -> ...}, {p2 -> ...}) >> ...
        //   not this: ({ p1, p2 -> ...}) >> ...


        when:
        def actualString = underTest.convertString('Converted!')

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

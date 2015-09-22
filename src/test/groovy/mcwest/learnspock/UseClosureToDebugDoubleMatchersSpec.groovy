package mcwest.learnspock

import spock.lang.Specification
import spock.lang.Unroll

/*
This class shows how to use a closure to debug double method matching.
The closure prints out the argument values being passed to the double,
which you can then compare to the expected values.
This technique was first suggested to me by my colleague Dave Hoffman.
 */

class UseClosureToDebugDoubleMatchersSpec extends Specification {

    @Unroll
    def 'closures can be used to debug doubles whose parameter values do not match actual calls'() {
        given:
            DependencyOfClassUnderTest stubbedDependency = Stub(DependencyOfClassUnderTest)
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
                    // such as the above stub that was causing the test error.
                    return true // Closure returns true, which forces matcher to match.
                }) >> "converted"
            }

            // see also the 2 parameter example Spec

        when:
            def actualString = underTest.convertString('Converted!')

        then:
            testFails == (actualString != 'Processed string: converted')

        where:
            comment                               | failTest | useClosureToDebug || testFails
            'fails because incorrect stub'        | true     | false             || true
            'passes because closure returns true' | true     | true              || false
            'passes because correct stub'         | false    | false             || false
    }

    class ClassUnderTest {
        DependencyOfClassUnderTest dependency

        String convertString(String s) {
            "Processed string: " + dependency.convertString(s)
        }
    }

    class DependencyOfClassUnderTest {
        String convertString(String s) {
            s.toLowerCase().trim()
        }
    }


}

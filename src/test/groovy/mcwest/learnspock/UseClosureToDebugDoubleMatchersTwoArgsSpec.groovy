package mcwest.learnspock

import spock.lang.Specification
import spock.lang.Unroll

class UseClosureToDebugDoubleMatchersTwoArgsSpec extends Specification {

    @Unroll
    def 'closures can be used to debug doubles whose parameter values do not match actual calls'() {
        given:
        DependencyOfClassUnderTest stubbedDependency = Stub(DependencyOfClassUnderTest)
        ClassUnderTest underTest = new ClassUnderTest(dependency: stubbedDependency)
        if (failTest) {
            // stub will not match,
            // as param value is different than what class under test uses to call it
            stubbedDependency.convertString('Converted?', '?') >> "converted"
        } else {
            // stub does match param value, test passes
            stubbedDependency.convertString('Converted!', '!') >> "converted"
        }
        if (useClosureToDebug) {
            stubbedDependency.convertString(_, _) >> {inputString, toAppend ->
                println "inputString: '${inputString}'"
                println "to append: '${toAppend}'"
                return "converted"
            }
        }

        when:
        def actualString = underTest.convertString('Converted!', '!')

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

        String convertString(String s, String toAppend) {
            "Processed string: " + dependency.convertString(s, toAppend)
        }
    }

    class DependencyOfClassUnderTest {
        String convertString(String s, String toAppend) {
            s.toLowerCase().trim() + toAppend
        }
    }


}

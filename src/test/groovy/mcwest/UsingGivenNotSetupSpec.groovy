package mcwest

import spock.lang.Specification

class UsingGivenNotSetupSpec extends Specification {

    // good
    def 'strings are converted to lower case and trimmed'() {
        given:
        ClassUnderTest underTest = new ClassUnderTest()

        when:
        def actualString = underTest.convertString('Hello ')

        then:
        actualString == 'hello'
    }

    // bad, uses setup
    def 'strings are converted to lower case and trimmed, uses setup'() {

        setup:
        ClassUnderTest underTest = new ClassUnderTest()

        when:
        def actualString = underTest.convertString('Hello ')

        then:
        actualString == 'hello'
    }

    class ClassUnderTest {
        String convertString(String s) {
            s.toLowerCase().trim()
        }
    }
}

package mcwest

import spock.lang.Specification

class UsingGivenNotSetupSpec extends Specification {

    // bad, uses setup, less intuitive
    def 'strings are converted to lower case and trimmed, v1'() {
        setup:
        ClassUnderTest underTest = new ClassUnderTest()

        when:
        def actualString = underTest.convertString('Hello ')

        then:
        actualString == 'hello'
    }

    // good, uses given instead of setup
    def 'strings are converted to lower case and trimmed, v2'() {
        given:
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

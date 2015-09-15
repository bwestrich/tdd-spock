package mcwest

import spock.lang.Specification

class UseGivenNotSetupSpec extends Specification {

    // bad, uses setup. Less intuitive, block comments read awkwardly.
    def 'strings are converted to lower case and trimmed, v1'() {
        setup: 'the test has been instantiated'
        ClassUnderTest underTest = new ClassUnderTest()

        when: 'the string is converted'
        def actualString = underTest.convertString('Hello ')

        then: 'the converted value is returned'
        actualString == 'hello'
    }

    // good, uses given instead of setup. Block comments read naturally.
    def 'strings are converted to lower case and trimmed, v2'() {
        given: 'the class has been instantiated'
        ClassUnderTest underTest = new ClassUnderTest()

        when: 'the string is converted'
        def actualString = underTest.convertString('Hello ')

        then: 'the converted value is returned'
        actualString == 'hello'
    }

    class ClassUnderTest {
        String convertString(String s) {
            s.toLowerCase().trim()
        }
    }
}

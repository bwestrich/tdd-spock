package mcwest

import spock.lang.Specification

class UsingGivenNotSetupSpec extends Specification {

    // good
    def 'strings are converted to lower case and trimmed'() {
        given:
        TheClassUnderTest underTest = new TheClassUnderTest()

        when:
        def actualString = underTest.convertString('Hello ')

        then:
        actualString == 'hello'
    }

    // bad, uses setup
    def 'strings are converted to lower case and trimmed, uses setup'() {

        setup:
        TheClassUnderTest underTest = new TheClassUnderTest()

        when:
        def actualString = underTest.convertString('Hello ')

        then:
        actualString == 'hello'
    }

    class TheClassUnderTest {

        String convertString(String s) {
            s.toLowerCase().trim()
        }
    }
}

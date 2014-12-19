package mcwest


class NamingTestsSpec {

    // good
    def 'strings are converted to lower case and trimmed'() {

        given:
        TheClassUnderTest underTest = new TheClassUnderTest()

        when:
        def actualString = underTest.convertString('Hello ')

        then:
        actualString == 'hello'
    }

    // bad, imperative style that 'tells' the class what to do
    def 'convert strings to lower case and trim them'() {
    }

    // bad, uses method name,l which is less readable
    def usesActualMethodName() {
        // this naming convention is less readable since can't use whitespace and puncuation
    }

    class TheClassUnderTest {

        String convertString(String s) {
            s.toLowerCase().trim()
        }
    }
}

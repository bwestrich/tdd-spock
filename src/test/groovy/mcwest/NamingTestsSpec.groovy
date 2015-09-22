package mcwest


class NamingTestsSpec {

    // good, describes what the class is expected to do
    def 'strings are converted to lower case and trimmed'() {
        given:
            ClassUnderTest underTest = new ClassUnderTest()

        when:
            def actualString = underTest.convertString('Hello ')

        then:
            actualString == 'hello'
    }

    // bad, imperative style that 'tells' the class what to do
    def 'convert strings to lower case and trim them'() {
    }

    // bad, uses 'verify' which is redundant
    def 'verify we can convert strings to lower case and trim them'() {
    }

    // bad, uses 'test' which is redundant
    def 'test we can convert strings to lower case and trim them'() {
    }

    // bad, uses 'should' which is redundant
    def 'should convert strings to lower case and trim them'() {
    }

    // bad, uses groovy method name, which is less readable
    def usesActualMethodName() {
        // this naming convention is less readable since can't use whitespace or punctuation
    }

    class ClassUnderTest {
        String convertString(String s) {
            s.toLowerCase().trim()
        }
    }
}

package mcwest

import spock.lang.Specification
import spock.lang.Unroll

class SpecifyClassUnderTestSpec extends Specification {

    /* Example of using tests to specify functionality of the class under test.
    Note that most of the test cases are edge-cases. */

    @Unroll
    def 'left pad with n zeros: #comment'() {
        when:
        def actualOutput = padLeftWithNZeros(inputString, 5)

        then:
        actualOutput == paddedValue

        where:
        comment                    | inputString || paddedValue
        'typical usage'            | 1234        || '01234'

        'no padding needed'        | 12345       || '12345'
        'max padding needed'       | 1           || '00001'
        'treat zero as null'       | 0           || null
        'return null as-is'        | null        || null
        'long strings not changed' | 123456      || '123456'
    }

    // sample Class Under Test
    def padLeftWithNZeros(Integer input, int minLength) {
        if (input == null || input == 0) {
            return null
        }
        return sprintf("%0${minLength}d", input)
    }
}
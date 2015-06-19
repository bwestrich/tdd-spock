package mcwest

import spock.lang.Specification
import spock.lang.Unroll

class SpecifySUTSpec extends Specification {

    /* Example of using tests to specify SUT.
    Note that most of the test cases are edge-cases. */

    @Unroll
    def 'left pad with n zeros: #input'() {
        when:
        def actualOutput = padLeftWithNZeros(input, 5)

        then:
        actualOutput == expectedOutput

        where:
        input  | expectedOutput
        12345  | '12345'
        1234   | '01234'
        1      | '00001'
        0      | null
        null   | null
        123456 | '123456'
    }

    // sample System Under Test
    def padLeftWithNZeros(Integer input, int minLength) {
        if (input == null || input == 0) {
            return null
        }
        return sprintf("%0${minLength}d", input)
    }
}
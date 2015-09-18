package mcwest

import spock.lang.Specification
import spock.lang.Unroll

class ConsiderCommentsOnGivenWhenThenSpec extends Specification {

    /* This test shows that comments can be used on the given/when/then blocks.
       This can help clarify the intent of more complicated tests.
    */

    def 'left pad with n zeros: #comment'() {
        given: 'one and two are defined'
        def one = 1
        def two = 2

        when: 'add one to one'
        def actualOutput = one + one

        then: 'get two'
        actualOutput == two
    }

}
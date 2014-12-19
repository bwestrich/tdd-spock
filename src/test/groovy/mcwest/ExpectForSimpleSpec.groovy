package mcwest

import spock.lang.Specification

class ExpectForSimpleSpec extends Specification {
    def "length of Spock and his friends' names"() {

        // for simple tests without a given/when/then flow, use expect:
        expect:
        name.size() == length

        where:
        name     | length
        "Jim"    | 3
        "Spock"  | 5
        "Scotty" | 6
    }
}
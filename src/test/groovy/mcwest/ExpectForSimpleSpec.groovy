package mcwest

import spock.lang.Specification

class ExpectForSimpleSpec extends Specification {
    def "name lengths are correctly calculated"() {

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
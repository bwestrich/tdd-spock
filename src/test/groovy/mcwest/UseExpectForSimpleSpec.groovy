package mcwest

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class UseExpectForSimpleSpec extends Specification {
    def "calculates name length: #name"() {

        // for simple tests without a given/when/then flow, use expect:
        // typically, tests should instead use given/when/then
        expect:
            name.size() == length

        where:
            name     || length
            "Jim"    || 3
            "Spock"  || 5
            "Scotty" || 6
    }
}
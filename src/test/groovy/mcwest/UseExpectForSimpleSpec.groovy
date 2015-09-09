package mcwest

import spock.lang.Specification

class UseExpectForSimpleSpec extends Specification {
    def "name lengths are correctly reported"() {

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
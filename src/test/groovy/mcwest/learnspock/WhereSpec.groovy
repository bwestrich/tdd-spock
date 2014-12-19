package mcwest.learnspock

import spock.lang.Specification

class WhereSpec extends Specification {
    def "length of Spock and his friends' names"() {
        expect:
        name.size() == length

        where:
        name     | length
        "Jim"    | 3
        "Spock"  | 5
        "Scotty" | 6
    }
}
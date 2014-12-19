package com.mcwest

import spock.lang.*

class HelloSpockSpec extends spock.lang.Specification {
    def "length of Spock and his friends' names"() {
        expect:
        name.size() == length

        where:
        name     | length
        "Jim"    | 3
        "Spock"  | 5
        "Kirk"   | 4
        "Scotty" | 6
    }
}
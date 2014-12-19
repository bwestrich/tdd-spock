package mcwest

class SmallerWhereSpec {

    def 'large where clause is hard to manage'() {
        given:
        int i;

        when:
        i = 3

        then: true

        where:
        someMap | someList | expectedSameSize | expectedSumOfSizes
        null    | null     | null             | null
        [:]     | null     | null             | null
        null    | []       | null             | null
        [x: 1]  | null     | null             | null
        null    | [2]      | null             | null
        [:]     | []       | true             | 0
        [x: 1]  | []       | false            | 1
        []      | [1]      | false            | 1
        [y: 2]  | [1]      | true             | 2
    }


    // TODO: add c.u.t., finish above tst, then make two tests like the one test



}

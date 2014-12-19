package mcwest

import spock.lang.Specification
import spock.lang.Unroll

class SmallerWhereSpec extends Specification {

    // bad
    @Unroll
    def 'large where clause is hard to manage: #comment'() {
        given:
        ClassUnderTest classUnderTest = new ClassUnderTest()

        when:
        def actualSameSize = classUnderTest.sameSize(someMap, someList)
        def actualSumOfSizes = classUnderTest.sumOfSizes(someMap, someList)

        then:
        expectedSameSize == actualSameSize
        expectedSumOfSizes == actualSumOfSizes

        where:
        comment          | someMap      | someList | expectedSameSize | expectedSumOfSizes
        'null'           | null         | null     | false            | 0
        'empty and null' | [:]          | null     | false            | 0
        'null and empty' | null         | []       | false            | 0
        'one and null'   | [x: 1]       | null     | false            | 1
        'null and one'   | null         | [2]      | false            | 1
        'empty'          | [:]          | []       | true             | 0
        'one and empty'  | [x: 1]       | []       | false            | 1
        'empty and one'  | []           | [1]      | false            | 1
        'one and one'    | [y: 2]       | [1]      | true             | 2
        'empty and two'  | [:]          | [1, 2]   | false            | 2
        'one and two'    | [y: 3]       | [1, 2]   | false            | 3
        'two and one'    | [y: 3, z: 4] | [1]      | false            | 3
    }

    // better
    @Unroll
    def 'smaller where clause is easier to manage (sameSize)'() {
        given:
        ClassUnderTest classUnderTest = new ClassUnderTest()

        when:
        def actualSameSize = classUnderTest.sameSize(someMap, someList)

        then:
        expectedSameSize == actualSameSize

        where:
        someMap | someList | expectedSameSize
        null    | null     | false
        [:]     | null     | false
        null    | []       | false
        [x: 1]  | null     | false
        null    | [2]      | false
        [:]     | []       | true
        [x: 1]  | []       | false
        []      | [1]      | false
        [y: 2]  | [1]      | true
    }

    // better
    @Unroll
    def 'smaller where clause is easier to manage (sumOfSizes)'() {
        given:
        ClassUnderTest classUnderTest = new ClassUnderTest()

        when:
        def actualSumOfSizes = classUnderTest.sumOfSizes(someMap, someList)

        then:
        expectedSumOfSizes == actualSumOfSizes

        where:
        someMap      | someList | expectedSumOfSizes
        null         | null     | 0
        [:]          | null     | 0
        null         | []       | 0
        [x: 1]       | null     | 1
        null         | [2]      | 1
        [:]          | []       | 0
        [x: 1]       | []       | 1
        []           | [1]      | 1
        [y: 2]       | [1]      | 2
        [:]          | [1, 2]   | 2
        [y: 3]       | [1, 2]   | 3
        [y: 3, z: 4] | [1]      | 3
    }


    class ClassUnderTest {
        boolean sameSize(Object c1, Object c2) {
            if (c1 == null || c2 == null) {
                return null;
            }
            return c1.size() == c2.size()
        }

        int sumOfSizes(Object c1, Object c2) {
            return sizeOf(c1) + sizeOf(c2)
        }

        int sizeOf(Object c) {
            if (!c) {
                0
            } else {
                c.size()
            }
        }
    }


}

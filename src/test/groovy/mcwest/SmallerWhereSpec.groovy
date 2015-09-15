package mcwest

import spock.lang.Specification
import spock.lang.Unroll

class SmallerWhereSpec extends Specification {

    // bad, large where clause is hard to manage
    @Unroll
    def 'sizes can be compared and added: #comment'() {
        given:
        ClassUnderTest classUnderTest = new ClassUnderTest()

        when:
        def actualSameSize = classUnderTest.sameSize(someMap, someList)
        def actualSumOfSizes = classUnderTest.sumOfSizes(someMap, someList)

        then:
        expectedSameSize == actualSameSize
        expectedSumOfSizes == actualSumOfSizes

        where:
        comment          | someMap      | someList | expectedSameSize || expectedSumOfSizes
        'null'           | null         | null     | false            || 0
        'empty and null' | [:]          | null     | false            || 0
        'null and empty' | null         | []       | false            || 0
        'one and null'   | [x: 1]       | null     | false            || 1
        'null and one'   | null         | [2]      | false            || 1
        'empty'          | [:]          | []       | true             || 0
        'one and empty'  | [x: 1]       | []       | false            || 1
        'empty and one'  | []           | [1]      | false            || 1
        'one and one'    | [y: 2]       | [1]      | true             || 2
        'empty and two'  | [:]          | [1, 2]   | false            || 2
        'one and two'    | [y: 3]       | [1, 2]   | false            || 3
        'two and one'    | [y: 3, z: 4] | [1]      | false            || 3
    }

    // better (part 1 of 2)
    @Unroll
    def 'size can be compared for different types of collections: #comment'() {
        given:
        ClassUnderTest classUnderTest = new ClassUnderTest()

        when:
        def actualSameSize = classUnderTest.sameSize(someMap, someList)

        then:
        expectedSameSize == actualSameSize

        where:
        comment             | someMap | someList || expectedSameSize
        'equal size'        | [y: 2]  | [1]      || true
        'map larger'        | [x: 1]  | []       || false
        'list larger'       | []      | [1]      || false

        'both null'         | null    | null     || false
        'empty list'        | [:]     | null     || false
        'empty map'         | null    | []       || false
        'map but null list' | [x: 1]  | null     || false
        'list but null map' | null    | [2]      || false
        'both empty'        | [:]     | []       || true
    }

    // better (part 2 of 2)
    @Unroll
    def 'sizes are added, using zero for nulls: #comment'() {
        given:
        ClassUnderTest classUnderTest = new ClassUnderTest()

        when:
        def actualSumOfSizes = classUnderTest.sumOfSizes(someMap, someList)

        then:
        expectedSumOfSizes == actualSumOfSizes

        where:
        comment             | someMap      | someList || expectedSumOfSizes
        'sizes are added'   | [y: 2]       | [1]      || 2
        'null list is zero' | [x: 1]       | null     || 1
        'null map is zero'  | null         | [2]      || 1

        'both null'         | null         | null     || 0
        'null list'         | [:]          | null     || 0
        'null map'          | null         | []       || 0
        'both empty'        | [:]          | []       || 0
        'empty list'        | [x: 1]       | []       || 1
        'empty map'         | []           | [1]      || 1
        'larger list'       | [y: 2]       | [1, 2]   || 3
        'larger map'        | [y: 3, z: 4] | [1]      || 3
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

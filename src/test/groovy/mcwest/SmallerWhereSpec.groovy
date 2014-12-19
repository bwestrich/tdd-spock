package mcwest

import spock.lang.Specification
import spock.lang.Unroll

class SmallerWhereSpec extends Specification {

    @Unroll
    def 'large where clause is hard to manage'() {

        given:
        ClassUnderTest classUnderTest = new ClassUnderTest()

        when:
        def actualSameSize = classUnderTest.sameSize(someMap, someList)
        def actualSumOfSizes = classUnderTest.sumOfSizes(someMap, someList)

        then:
        expectedSameSize == actualSameSize
        expectedSumOfSizes == actualSumOfSizes

        where:
        someMap | someList | expectedSameSize | expectedSumOfSizes
        null    | null     | false            | 0
        [:]     | null     | false            | 0
        null    | []       | false            | 0
        [x: 1]  | null     | false            | 0
        null    | [2]      | false            | 0
        [:]     | []       | true             | 0
        [x: 1]  | []       | false            | 1
        []      | [1]      | false            | 1
        [y: 2]  | [1]      | true             | 2
    }

    // TODO: add c.u.t., finish above tst, then make two tests like the one test
    class ClassUnderTest {
        boolean sameSize(Object c1, Object c2) {
            if (c1 == null || c2 == null) {
                return null;
            }
            return c1.size() == c2.size()
        }

        int sumOfSizes(Object c1, Object c2) {
            if (c1 == null || c2 == null) {
                return 0;
            }
            return c1.size() + c2.size()
        }
    }


}

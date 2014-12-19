package mcwest.learnspock

import spock.lang.Specification

class SpySpec extends Specification {

    def 'Spock spies can be used to mock out selective methods of the class under test'() {

        given:
        ClassUnderTest underTest = Spy(ClassUnderTest)
        underTest.getA() >> 3

        when:
        int actualSum = underTest.getAPlusB()

        then:
        actualSum == 5
    }

    class ClassUnderTest {
        def getAPlusB() {
//            println('a plus b')
            getA() + getB()
        }

        def getA() {
//            println 'a'
            1 // implemented return value, will not be used since method is mocked
        }

        def getB() {
//            println 'b'
            2
        }
    }

}
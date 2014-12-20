package mcwest

import spock.lang.Specification

class SeparateExpectationFromScaffoldingSpec extends Specification {

    // bad, all mocks are in then
    def 'string is processed, v2'() {
        given:
        ClassDependsOnClassUnderTest mockDependency = Mock(ClassDependsOnClassUnderTest)
        ClassUnderTest underTest = new ClassUnderTest(dependency: mockDependency)

        when:
        def actualString = underTest.convertString(' Converted')

        then:
        mockDependency.convertString(_) >> "converted"
        actualString == 'Processed string: converted'
    }

    // bad, scaffold mocks have expectations
    def 'string is processed, v3'() {
        given:
        ClassDependsOnClassUnderTest mockDependency = Mock(ClassDependsOnClassUnderTest)
        ClassUnderTest underTest = new ClassUnderTest(dependency: mockDependency)
        1 * mockDependency.convertString(' Converted') >> "converted"

        when:
        def actualString = underTest.convertString(' Converted')

        then:
        actualString == 'Processed string: converted'
    }

    // good, scaffold mocks are in given, and have no expectations (unless needed for test)
    def 'string is processed'() {
        given:
        ClassDependsOnClassUnderTest mockDependency = Mock(ClassDependsOnClassUnderTest)
        ClassUnderTest underTest = new ClassUnderTest(dependency: mockDependency)
        mockDependency.convertString(_) >> "converted"

        when:
        def actualString = underTest.convertString(' Converted')

        then:
        actualString == 'Processed string: converted'
    }

    class ClassUnderTest {
        ClassDependsOnClassUnderTest dependency

        String convertString(String s) {
            "Processed string: " + dependency.convertString(s)
        }
    }

    class ClassDependsOnClassUnderTest {
        String convertString(String s) {
            s.toLowerCase().trim()
        }
    }


}

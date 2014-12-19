package mcwest

import spock.lang.Specification

class SeparateExpectationFromScaffoldingSpec extends Specification {

    // good
    def 'put scaffold mocks into given, testing mocks into then'() {
        given:
        ClassDependsOnClassUnderTest mockDependency = Mock(ClassDependsOnClassUnderTest)
        ClassUnderTest underTest = new ClassUnderTest(dependency: mockDependency)
        mockDependency.convertString(_) >> "converted"

        when:
        def actualString = underTest.convertString(' Converted')

        then:
        actualString == 'Processed string: converted'
    }

    // bad
    def 'all mocks are in then'() {
        given:
        ClassDependsOnClassUnderTest mockDependency = Mock(ClassDependsOnClassUnderTest)
        ClassUnderTest underTest = new ClassUnderTest(dependency: mockDependency)

        when:
        def actualString = underTest.convertString(' Converted')

        then:
        mockDependency.convertString(_) >> "converted"
        actualString == 'Processed string: converted'
    }

    // bad
    def 'scaffold mocks have expectations'() {
        given:
        ClassDependsOnClassUnderTest mockDependency = Mock(ClassDependsOnClassUnderTest)
        ClassUnderTest underTest = new ClassUnderTest(dependency: mockDependency)
        1 * mockDependency.convertString(' Converted') >> "converted"

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

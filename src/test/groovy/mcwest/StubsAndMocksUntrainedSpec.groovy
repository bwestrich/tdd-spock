package mcwest

import spock.lang.Specification

class StubsAndMocksUntrainedSpec extends Specification {

    String propInitialValue = "hello"

    def 'untrained stubs and mocks behave differently'() {
        given:
            ServiceClass serviceClassMock = Mock(ServiceClass)
            ServiceClass serviceClassStub = Stub(ServiceClass)

        expect:
            serviceClassMock.returnClassWithProperties() == null

            serviceClassStub.returnClassWithProperties() != null
            serviceClassStub.returnClassWithProperties().toString() == 'Mock for type \'ClassWithProperties\' named \'dummy\''
    }

    class ClassWithProperties {
        String prop1 = ''
        //ClassThatIsAProperty prop1
    }

    class ClassThatIsAProperty {

    }

    class ServiceClass {
        ClassWithProperties returnClassWithProperties() {
            return new ClassWithProperties()
        }
    }

}

package mcwest

import spock.lang.Specification

class StubsAndMocksSpec extends Specification {

    String dataId = "id1"
    String rawData = "    raw data  "
    String expectedTransformedData = "data"
    DataService dataServiceStub
    LoggingService logServiceMock
    DataController controller

    def setup() {
        // defining stubs and mocks
        dataServiceStub = Stub(DataService)
        logServiceMock = Mock(LoggingService)
        controller = new DataController(dataService: this.dataServiceStub,
                logService: this.logServiceMock)
    }

    def 'data transformations are logged'() {
        given:
            // stubs are only used to supporting the test,
            // their use is not verified
            dataServiceStub.retrieveData(dataId) >> rawData

        when:
            controller.getTransformedData(dataId)

        then:
            // mocks are used to verify that test expectations are met
            1 * logServiceMock.log(dataId, rawData)
    }

    // the code in this method doesn't illustrate anything not already illustrated
    // in previous method, but the comments show more examples of using stubs.
    def 'data is correctly transformed'() {
        given:
            // DataService is a stub, as calls to it do not need to be verified
            // in order to test that the data is correctly transformed.
            // We do not specify the number of times the method must be called.
            dataServiceStub.retrieveData(dataId) >> rawData

            // Other tools for writing 'lenient' stubs are:
            //   (_ as String) to stub a method call based on parameter type (String for example)
            //   (_, _, _, ...) to stub a method call based only on the number of arguments passed to it
            //        i.e. myMethod(_, _) matches myMethod(a, b) or myMethod(a, c)
            //   (*_) to stub a method call regardless of the arguments passed to it, e.g.
            //        i.e. myMethod(*_) matches myMethod(), myMethod(a), myMethod(a, b), .....

            // note: we don't need to stub out the call to logService,
            // as spock uses lenient mocks (i.e. calls to non mocked methods return null).

        when:
            def actualData = controller.getTransformedData(dataId)

        then:
            actualData == expectedTransformedData
    }

    class DataController {
        DataService dataService
        LoggingService logService

        String getTransformedData(String id) {
            String retrievedRawData = dataService.retrieveData(id)
            logService.log(id, retrievedRawData)
            String transformedData = retrievedRawData.replace("raw", "").trim()
            transformedData
        }

    }

    // the following classes are included here for demo purposes.
    // in real coding, they would have their own class file and spec file.

    class DataService {
        String retrieveData(String id) {
            // getTransformedData data, e.g. by calling a web dataService
            throw new RuntimeException("retrieveData not yet implemented")
        }
    }

    class LoggingService {
        void log(Object key, Object value) {
            throw new RuntimeException("log not yet implemented")
        }
    }

}

package mcwest

import spock.lang.Specification

class StubsAndMocksSpec extends Specification {

    String dataId = "id1"
    String rawData = "    raw data  "
    String expectedTransformedData = "data"
    private DataService dataServiceStub
    private LoggingService logServiceMock
    private DataController controller

    def setup() {
        dataServiceStub = Mock(DataService)
        logServiceMock = Mock(LoggingService)
        controller = new DataController(dataService: this.dataServiceStub, logService: this.logServiceMock)
    }

    def 'data is correctly transformed'() {
        given:
        // dataService is a stub since calls to it do not need to be verified to test that data is correctly transformed
        dataServiceStub.retrieveData(dataId) >> rawData

        when:
        def actualData = controller.getTransformedData(dataId)

        then:
        actualData == expectedTransformedData
    }

    def 'retrieval is logged'() {
        given:
        dataServiceStub.retrieveData(dataId) >> rawData

        when:
        controller.getTransformedData(dataId)

        then:
        // logService is a mock, since calls to it must be verified to test that we are logging
        1 * logServiceMock.log(dataId, rawData)
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

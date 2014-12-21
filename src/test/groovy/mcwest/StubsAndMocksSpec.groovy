package mcwest

import spock.lang.Specification

// TODO: Add in a non-scaffolding mock
// TODO: remove the check for string content expectation from tests, this is only  cache test?

class StubsAndMocksSpec extends Specification {

    String dataId = "id1"
    String rawData = "    raw Data  "
    String expectedConvertedData = "Converted: data"

    // bad, mocks and stubs are in then
    def 'string is processed and cached, v1'() {
        given:
        DataService mockDependency = Mock(DataService)
        DataController controller = new DataController(service: mockDependency)

        when:
        def actualString = controller.retrieve(dataId)

        then:
        mockDependency.retrieveData(dataId) >> rawData
        actualString == expectedConvertedData
    }

    // bad, scaffold mocks have expectations
    def 'string is processed and cached, v2'() {
        given:
        DataService mockDependency = Mock(DataService)
        DataController controller = new DataController(service: mockDependency)
        1 * mockDependency.retrieveData(dataId) >> rawData

        when:
        def actualString = controller.retrieve(dataId)

        then:
        actualString == expectedConvertedData
    }

    // good, scaffold mocks are in given, and have no expectations (unless needed for test)
    def 'string is processed and cached, v3'() {
        given:
        DataService mockDependency = Mock(DataService)
        DataController controller = new DataController(service: mockDependency)
        mockDependency.retrieveData(dataId) >> rawData

        when:
        def actualString = controller.retrieve(dataId)

        then:
        actualString == expectedConvertedData
    }

    class DataController {
        DataService service
        Map cache  // TODO: inject, then mock as a mock needed for the test

        String retrieve(String s) {
            String retrievedRawData = service.retrieveData(s)
            retrievedRawData = retrievedRawData.toLowerCase().trim()
            retrievedRawData = retrievedRawData.replace("raw", "")
            return "Converted: " + retrievedRawData.trim()
        }

    }

    class DataService {
        String retrieveData(String s) {
            // retrieve data, e.g. by calling a web service
            throw new RuntimeException("service not yet implemented")
        }
    }

    class Transformer {
        String transformData(String s) {
            throw new RuntimeException("transformer not yet implemented")
        }
    }


}

#tdd-spock -- Easy to write yet powerful web app tests 

# Benefits
  * Leverages your existing controller tests
  * Verifies that all Spring service annotations are in place
  * Can be used to verify database operations (full access to database is available)


# How to use


 example code shown below: 
 
 
 Ancestor (mocked out controller test)
 
 class MyControllerSpec extends Specification {
 
 
     MockMvc mockMvc
     MyService myService
     def isMocked = false
 
     def setup() {
         myService = Mock(MyService)
         mockMvc = MockMvcBuilders.standaloneSetup(new MyController(myService: myService)).build()
         isMocked = true
     }
 
 
 Descendant (non-mocked controller test) 
 
 @WebIntegrationTest(['server.port=0'])
 @SpringApplicationConfiguration(classes = Application.class)
 class MyControllerWebSpec extends MyControllerSpec {
 
     @Autowired
     WebApplicationContext wac
 
     @Autowired
     MyService myService
 
     MockMvc mockMvc // despite the 'mock' name of this class, this test is in-container
 
     def setup() {
         isMocked = false
         mockMvc = MockMvcHelper.buildMockMvcFromWebAppContext(wac)
     }


NOTE: This same approach can also be used to in-container testing (service) code that is called by the controller.
When doing so, we recommend separating out this test code into 2 new test classes so that the *ControllerSpec
 classes only have responsibility for testing controller logic. Suggested names for new classes: 
 *MockIntegrationSpec: (analogous to *ControllerSpec above) 
 *IntegrationSpec: (analogous to *ControllerWebSpec above) 
 
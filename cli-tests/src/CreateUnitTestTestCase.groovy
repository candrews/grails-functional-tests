class CreateUnitTestTestCase extends AbstractCliTestCase {

    protected void setUp() {
        workDir = new File(baseWorkDir, 'app1')
    }
    
    void testCreateUnitTest() {
        execute([ "create-unit-test" ])
        enterInput "com.demo.unit.HelloWorld"
        
        assertEquals 0, waitForProcess()
        
        def appDir = new File(baseWorkDir, 'app1')
        
        assertTrue 'unit test case was not created', new File(appDir, 'test/unit/com/demo/unit/HelloWorldSpec.groovy').exists()
    }
    
    void testCreateUnitTestWithSpock() {
        // Prepare the test application that has Spock installed.
        def appSource = new File(cliTestsDir, "spock-app")
        def app = new File(baseWorkDir, "createunittest-spock-app")

        copyDir appSource, app
        workDir = app

        // TODO: This try/catch is temporary while looking into
        // why upgrade is failing.  upgrade is due to be removed
        // in 2.4 anyway.
        try {
            upgrade()
        } catch (Exception ignore) {

        }

        // Make sure the plugin is installed and compiled.
        execute([ "compile" ])
        assertEquals "Failed to compile the app", 0, waitForProcess()

        execute([ "create-unit-test" ])
        enterInput "com.demo.unit.HelloWorld"
        
        assertEquals 0, waitForProcess()
        
        def appDir = new File(baseWorkDir, 'app1')
        def expectedTestFile = new File(app, 'test/unit/com/demo/unit/HelloWorldSpec.groovy')
        
        assertTrue 'Spock unit test case was not created', expectedTestFile.exists()

        def fileContent = expectedTestFile.text
        assertTrue 'Unit test case is not a Spock test', fileContent.contains(" extends Specification")
    }
    
    void testSuffixStripping() {
        execute([ "create-unit-test" ])
        enterInput "com.demo.unit.DemoTests"
        
        assertEquals 0, waitForProcess()
        
        def appDir = new File(baseWorkDir, 'app1')
        
        assertTrue 'unit test case was not created', new File(appDir, 'test/unit/com/demo/unit/DemoSpec.groovy').exists()
        assertFalse 'test with wrong name was created', new File(appDir, 'test/unit/com/demo/unit/DemoSpecSpec.groovy').exists()
    }

    private copyDir(source, destination) {
        if (source.directory) {
            if (!destination.exists()) {
                assert destination.mkdir() : "making $destination"
            }
            source.eachFile { copyDir(it, new File(destination, it.name)) }
        } else {
            destination.createNewFile()
            destination << source.newInputStream()
        }
    }
}

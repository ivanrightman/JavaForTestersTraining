package ru.stqa.pft.mantis.tests;


import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;


import java.io.IOException;



public class TestBase {


    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite //suite (vsegda odin) - test - class - method
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

}

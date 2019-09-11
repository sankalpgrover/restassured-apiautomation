import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.testng.annotations.*;


public class TestSetup {
    ExtentTest extentTest;
    ExtentReports extent;
    String extentReportFile;

    @BeforeTest
    public void setup(){
        extentReportFile = System.getProperty("user.dir") + "/extentReportFile.html";
        extent = new ExtentReports(extentReportFile, true);
    }

    @AfterTest
    public void tearDown(){
        extent.endTest(extentTest);
        extent.flush();
    }


}

package test.java;

import main.java.controller.ApplicationController;
import org.junit.Before;
import org.testng.annotations.Test;

/**
 * Created by Arber on 11/15/2016.
 * This class's purpose is to: <DESCRIBE PURPOSE>
 */
public class ArberJUnitTests {
    private ApplicationController applicationController;

    @Before
    public void setUpApplicationController() {
        applicationController = new ApplicationController();
        //Class<?> appControllerClass = applicationController.getClass();
        //Field[] appControllerClassFields = appControllerClass.getFields();
        //applicationController.submitReportButton.isDisabled();
    }

    @Test
    public void checkProperThing() {
        applicationController.submitReportButton.isDisabled();
    }

}

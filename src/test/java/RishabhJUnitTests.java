package test.java;

import main.java.controller.ApplicationController;
import main.java.model.User;
import org.junit.Before;
import org.testng.annotations.Test;

/**
 * Created by Rishabh on 11/15/2016.
 * This class's purpose is to:
 */
public class RishabhJUnitTests {
    private ApplicationController applicationController;
    private User userType;
    private User managerType;
    private User adminType;

    @Before
    public void setupApplicationController() throws NoSuchFieldException {
        applicationController = new ApplicationController();
    }

    @Test
    public void checkSubmitReportDisabledIfUserTypeUser() {
        //applicationController.setCurrentUser();
        applicationController.submitReportButton.isDisabled();
    }

    @Test
    public void checkSubmitReportDisabledIfUserTypeAdmin() {
        //applicationController.setCurrentUser();
        applicationController.submitReportButton.isDisabled();
    }

    @Test
    public void checkViewPurityReportAndViewHistoryReportDisabledIfUserTypeManager() {
        //applicationController.setCurrentUser();
        applicationController.submitReportButton.isDisabled();
    }

    @Test
    public void checkViewPurityReportAndViewHistoryReportEnabledIfUserNotTypeManager() {
        //applicationController.setCurrentUser();
        applicationController.submitReportButton.isDisabled();
    }

    @Test
    public void checkCreateProfileDisabledIfUserHasNoProfile() {
        //applicationController.setCurrentUser();
        applicationController.submitReportButton.isDisabled();
    }
}

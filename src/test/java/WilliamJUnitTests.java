package test.java;

import main.java.controller.LoginScreenController;
import main.java.model.User;
import org.junit.Before;
import org.testng.annotations.Test;
import java.util.ArrayList;
import static junit.framework.Assert.assertTrue;

/**
 * Created by williamcheng on 11/15/16.
 * Tests isValidLogin
 * private boolean isValidLogin(User currentUser, String username, String password, ArrayList<User> userList)
 *
 */
public class WilliamJUnitTests {
    LoginScreenController c;
    @Before
    public void setUpController() {
        c = new LoginScreenController();
    }

    /**
     * checks if everything works
     */
    @Test
    public void test1() {
        c = new LoginScreenController();
        ArrayList<User> userList = new ArrayList<User>();
        User u1 = new User("William", "Cheng", "wilche", "pass", "u");
        userList.add(u1);
        System.out.print(u1);
        System.out.println(c);
        assertTrue(c.isValidLogin(u1, "wilche", "pass", userList));
    }

    /**
     * checks password failure
     */
    @Test
    public void test2() {
        c = new LoginScreenController();
        ArrayList<User> userList = new ArrayList<User>();
        User u1 = new User("William", "Cheng", "wilche", "pass", "u");
        userList.add(u1);
        assertTrue(!c.isValidLogin(u1, "wilche", "bleh", userList));
    }


    /**
     * checks arraylist with nothing in it.
     */
    @Test
    public void test3() {
        c = new LoginScreenController();
        ArrayList<User> userList = new ArrayList<User>();
        User u1 = new User("William", "Cheng", "wilche", "pass", "u");
        assertTrue(!c.isValidLogin(u1, "wilche", "pass", userList));
    }

    /**
     * checks username failure.
     */
    @Test
    public void test4() {
        c = new LoginScreenController();
        ArrayList<User> userList = new ArrayList<User>();
        User u1 = new User("William", "Cheng", "wilche", "pass", "u");
        userList.add(u1);
        assertTrue(!c.isValidLogin(u1, "pew pew", "pass", userList));
    }

}

package com.selenium.dataproviders;

import com.selenium.utils.ConfigReader;
import org.testng.annotations.DataProvider;

/**
 * Data Provider class for supplying test data
 */
public class LoginDataProvider {

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {
        return new Object[][] {
            { ConfigReader.getValidUsername(), ConfigReader.getValidPassword() }
        };
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
            { "invalid_user", "secret_sauce", "Username and password do not match" },
            { "standard_user", "invalid_password", "Username and password do not match" },
            { "", "", "Username is required" },
            { "standard_user", "", "Password is required" },
            { "", "secret_sauce", "Username is required" }
        };
    }

    @DataProvider(name = "specialCharacterData")
    public Object[][] specialCharacterData() {
        return new Object[][] {
            { "user@123", "pass@123", "Username and password do not match" },
            { "user with spaces", "password", "Username and password do not match" },
            { "user!@#$%", "pass!@#$%", "Username and password do not match" }
        };
    }

    @DataProvider(name = "lockedOutUserData")
    public Object[][] lockedOutUserData() {
        return new Object[][] {
            { "locked_out_user", "secret_sauce", "Sorry, this user has been locked out" }
        };
    }

    @DataProvider(name = "problemUserData")
    public Object[][] problemUserData() {
        return new Object[][] {
            { "problem_user", "secret_sauce" },
            { "performance_glitch_user", "secret_sauce" }
        };
    }
}

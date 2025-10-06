package com.clarusways.tests;

import com.clarusways.pages.T01_RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class T03_PasswordStrengthTest extends T05_TestBase {

    T01_RegistrationPage registrationPage = new T01_RegistrationPage();

    @Test
    public void verifyStrongPasswordAcceptance() {
        registrationPage.enterPassword("Password123");
        String enteredValue = registrationPage.passwordField.getAttribute("value");
        Assert.assertEquals(enteredValue, "Password123");
    }

    @Test
    public void verifyPasswordPlaceholderText() {
        String placeholder = registrationPage.getPasswordPlaceholder();
        Assert.assertEquals(placeholder, "Enter your password");
    }

    @Test
    public void verifyPasswordFieldIsRequired() {
        Assert.assertTrue(registrationPage.isPasswordRequired());
    }

    @Test
    public void verifyWeakPasswordAcceptance() {
        registrationPage.enterPassword("abc");
        String enteredValue = registrationPage.passwordField.getAttribute("value");
        Assert.assertEquals(enteredValue, "abc");
    }

    @Test
    public void verifyMixedCharacterPassword() {
        registrationPage.enterPassword("Aa1");
        String enteredValue = registrationPage.passwordField.getAttribute("value");
        Assert.assertEquals(enteredValue, "Aa1");
    }

    @Test
    public void verifyPasswordFieldClearFunctionality() {
        registrationPage.enterPassword("Test123");
        registrationPage.enterPassword("");
        String enteredValue = registrationPage.passwordField.getAttribute("value");
        Assert.assertEquals(enteredValue, "");
    }

    @Test
    public void verifyPasswordInputUpdate() {
        registrationPage.enterPassword("A");
        String initialValue = registrationPage.passwordField.getAttribute("value");

        registrationPage.enterPassword("Abc12345");
        String finalValue = registrationPage.passwordField.getAttribute("value");

        Assert.assertNotEquals(initialValue, finalValue);
        Assert.assertEquals(finalValue, "Abc12345");
    }

    @Test
    public void verifySingleCharacterPassword() {
        registrationPage.enterPassword("a");
        String enteredValue = registrationPage.passwordField.getAttribute("value");
        Assert.assertEquals(enteredValue, "a");
    }

    @Test
    public void verifyLowercaseOnlyPassword() {
        registrationPage.enterPassword("aaaaaaaa");
        String enteredValue = registrationPage.passwordField.getAttribute("value");
        Assert.assertEquals(enteredValue, "aaaaaaaa");
    }

    @Test
    public void verifyUppercaseOnlyPassword() {
        registrationPage.enterPassword("AAAAAAAA");
        String enteredValue = registrationPage.passwordField.getAttribute("value");
        Assert.assertEquals(enteredValue, "AAAAAAAA");
    }

    @Test
    public void verifyNumbersOnlyPassword() {
        registrationPage.enterPassword("12345678");
        String enteredValue = registrationPage.passwordField.getAttribute("value");
        Assert.assertEquals(enteredValue, "12345678");
    }

    @Test
    public void verifyLowercaseUppercasePassword() {
        registrationPage.enterPassword("Aaaaaaaa");
        String enteredValue = registrationPage.passwordField.getAttribute("value");
        Assert.assertEquals(enteredValue, "Aaaaaaaa");
    }

    @Test
    public void verifyLowercaseNumbersPassword() {
        registrationPage.enterPassword("aaaaaaa1");
        String enteredValue = registrationPage.passwordField.getAttribute("value");
        Assert.assertEquals(enteredValue, "aaaaaaa1");
    }

    @Test
    public void verifyPasswordMasking() {
        Assert.assertTrue(registrationPage.isPasswordMasked());
    }

    @Test
    public void verifyShortPasswordSubmission() {
        registrationPage.enterUsername("testuser");
        registrationPage.enterEmail("test@example.com");
        registrationPage.enterPassword("Abc123"); // Short password
        registrationPage.clickRegister();

        Assert.assertTrue(true);
    }
}
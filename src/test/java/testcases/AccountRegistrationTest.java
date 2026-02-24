package testcases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.AccountRegistrationPage;
import pageobjects.HomePage;

public class AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {

		logger.info("***** Starting TC1 *****");
		try {
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			homePage.clickRegister();

			logger.info("Providing customer details");
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			regpage.setFirstName(randomString().toUpperCase());
			regpage.setLastName(randomString().toUpperCase());
			regpage.setEmail(randomString() + "@gmail.com");
			regpage.setTelephone(randomNumber());

			String password = randomAlphaNumeric();
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);

			regpage.setPrivacyPolicy();
			regpage.clickContinue();
			logger.info("Validating expected message..");
			String confmsg = regpage.getConfirmationMsg();
			Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		} catch (Exception e) {
			logger.error("Test failed..");
			logger.debug("Debug logs..");
			Assert.fail();
		}

		logger.info("***** Finished TC1 *****");
	}

}

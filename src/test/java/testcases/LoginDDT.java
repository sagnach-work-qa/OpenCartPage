package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import utilities.DataProviders;

public class LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="Datadriven") // getting data
	public void verify_loginDDT(String email, String password, String exp) {
		try {
			logger.info("***Starting LoginDDT***");
			// HomePage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// Login
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin();

			// MyAccount
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPage = macc.isMyAccountPageExists();

			if (exp.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {
					macc.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}

			if (exp.equalsIgnoreCase("Invalid")) {
				if (targetPage == true) {
					macc.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("***Finished LoginDDT***");

	}

}

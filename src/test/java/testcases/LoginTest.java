package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;

public class LoginTest extends BaseClass {

	@Test(groups={"Sanity","Master"})
	public void verify_login() {
		logger.info("*** Starting LoginTest1 ***");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();

			MyAccountPage map = new MyAccountPage(driver);
			boolean targePage = map.isMyAccountPageExists();
			Assert.assertEquals(targePage, true, "Login failed!");
			Assert.assertTrue(targePage);
		} catch (Exception e) {
			Assert.fail();
			System.out.println("");
		}

		logger.info("*** Finished LoginTest1");
	}
}

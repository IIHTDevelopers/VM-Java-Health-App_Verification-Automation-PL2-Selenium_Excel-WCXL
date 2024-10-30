package testcases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import pages.StartupPage;
import pages.verification_page;
import testBase.AppTestBase;
import testBase.UserActions;
import testdata.LocatorsFactory;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.Test;
import coreUtilities.testutils.ApiHelper;
import coreUtilities.utils.FileOperations;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class verification_testcase extends AppTestBase {
	Map<String, String> configData;
	Map<String, String> loginCredentials;
	String expectedDataFilePath = testDataFilePath + "expected_data.xlsx";
	String loginFilePath = loginDataFilePath + "Login.xlsx";
	StartupPage startupPage;
	String randomInvoiceNumber;
	LocatorsFactory locatorsFactoryInstance;
	UserActions userActionsInstance;
	verification_page verification_pageInstance;
	

	@Parameters({ "browser", "environment" })
	@BeforeClass(alwaysRun = true)
	public void initBrowser(String browser, String environment) throws Exception {
		configData = new FileOperations().readExcelPOI(config_filePath, environment);
		configData.put("url", configData.get("url").replaceAll("[\\\\]", ""));
		configData.put("browser", browser);

		boolean isValidUrl = new ApiHelper().isValidUrl(configData.get("url"));
		Assert.assertTrue(isValidUrl,
				configData.get("url") + " might be Server down at this moment. Please try after sometime.");
		initialize(configData);
		startupPage = new StartupPage(driver);
	}

	@Test(priority = 1, groups = {
			"sanity" }, description = "Precondition: User should be logged in and on the healthapp section\n"
					+ "1. Login in the healthapp application\n" + "2. Scroll down menu till verification\n"
					+ "3. Click on the verification")
	public void verifyVerificationModule() throws Exception {
		verification_pageInstance = new verification_page(driver);

		Map<String, String> verificationExpectedData = new FileOperations().readExcelPOI(expectedDataFilePath,
				"verification");
		Map<String, String> loginData = new FileOperations().readExcelPOI(loginFilePath, "credentials");

		Assert.assertTrue(verification_pageInstance.loginToHealthAppByGivenValidCredetial(loginData),
				"Login failed, Invalid credentials ! Please check manually");
		verification_pageInstance.scrollDownAndClickVerificationTab();
		System.out.println("Verification Page url : " + verificationExpectedData.get("URL"));
		Assert.assertEquals(verification_pageInstance.verifyVerificationPageUrl(), verificationExpectedData.get("URL"));
	}

	@Test(priority = 2, groups = {
			"sanity" }, description = "Precondition: User should be logged in and on the Verification section\\n\"\r\n"
					+ "	+ \"1. Click on the Verification Module drop-down arrow\\n\"\r\n" + "	+ \"2. Click on Order")

	public void verifyVerificationSubModules() {
		try {
			verification_pageInstance = new verification_page(driver);

			Assert.assertTrue(verification_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
					verification_pageInstance.getPageBarFixedLocator("Inventory")));

			Assert.assertTrue(verification_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
					verification_pageInstance.getPageBarFixedLocator("Pharmacy")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 3, groups = {
			"sanity" }, description = "Precondition: User should be logged in and on the Verification section\\n\"\r\n"
					+ "	+ \"1. Click on the Verification Module drop-down arrow\\n\"\r\n"
					+ "	+ \"2. Click on Inventory" + "	+ \"3. Click on Requisition")

	public void verifyInventoryTabsAndButtonsAreDisplayed() throws Exception {

		verification_pageInstance = new verification_page(driver);

		Assert.assertTrue(verification_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
				verification_pageInstance.getSubNavTabLocator("Requisition")));

		Assert.assertTrue(verification_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
				verification_pageInstance.getSubNavTabLocator("Purchase Request")));

		Assert.assertTrue(verification_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
				verification_pageInstance.getSubNavTabLocator("Purchase Order")));

		Assert.assertTrue(verification_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
				verification_pageInstance.getSubNavTabLocator("GR Quality Inspection")));

		Assert.assertTrue(verification_pageInstance.highlightAndClickOnElement(
				verification_pageInstance.getSubNavTabLocator("Requisition"), "Requisition"));

		Assert.assertTrue(verification_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
				verification_pageInstance.getRadioButtonsLocator("pending")));

		Assert.assertTrue(verification_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
				verification_pageInstance.getRadioButtonsLocator("approved")));

		Assert.assertTrue(verification_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
				verification_pageInstance.getRadioButtonsLocator("rejected")));

		Assert.assertTrue(verification_pageInstance
				.highlightAndVerifyWhetherElementIsDisplayed(verification_pageInstance.getRadioButtonsLocator("all")));

		Assert.assertTrue(verification_pageInstance
				.highlightAndVerifyWhetherElementIsDisplayed(verification_pageInstance.favouriteOrStarIcon()));

		Assert.assertTrue(verification_pageInstance
				.highlightAndVerifyWhetherElementIsDisplayed(verification_pageInstance.getOkButtonLocator()));

		Assert.assertTrue(verification_pageInstance
				.highlightAndVerifyWhetherElementIsDisplayed(verification_pageInstance.searchBarId()));

		Assert.assertTrue(verification_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
				verification_pageInstance.getButtonLocatorsBytext("Print")));

		Assert.assertTrue(verification_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
				verification_pageInstance.getButtonLocatorsBytext("First")));

		Assert.assertTrue(verification_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
				verification_pageInstance.getButtonLocatorsBytext("Previous")));

		Assert.assertTrue(verification_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
				verification_pageInstance.getButtonLocatorsBytext("Next")));

		Assert.assertTrue(verification_pageInstance.highlightAndVerifyWhetherElementIsDisplayed(
				verification_pageInstance.getButtonLocatorsBytext("Last")));
	}

	@Test(priority = 4, groups = {
			"sanity" }, description = "Pre condition: User should be logged in and it is on verification module\r\n"
					+ "1. Click on the inventory  \r\n" + "2. Click on the pharmacy \r\n"
					+ "3. User should navigate to the pharmacy section from the inventory section ")

	public void verifyNavigationToAnotherSubModuleAfterOpeningTheInventorySection() throws Exception {
		verification_pageInstance = new verification_page(driver);

		Assert.assertTrue(verification_pageInstance.verifySelectedTabIsActiveOrNot(
				verification_pageInstance.getPageBarFixedLocator("Pharmacy")), "Pharmacy page is not active");
	}

	@Test(priority = 5, groups = {
			"sanity" }, description = "Pre condition: User should be logged in and it is on verification module\r\n"
					+ "1. Click on the inventory  \r\n" + "2. Click on the pharmacy \r\n"
					+ "3. User should navigate to the pharmacy section from the inventory section ")

	public void verifyNavigationOfTabs() throws Exception {
		verification_pageInstance = new verification_page(driver);

		Assert.assertTrue(verification_pageInstance.verifyNavigationOfTabs(), "Purchase Request Tab is not active");
	}

	@Test(priority = 6, groups = {
			"sanity" }, description = "Pre condition: User should be logged in and it is on Requisition section"
					+ "1. Click on the \"From\" date" + "2. Select the \"From\" date" + "3. Click on the \"To\" date"
					+ "4. Select \"To\" date" + "5. Click on \"OK\" button")
	public void verifySearchDataByDateFilter() throws Exception {
		verification_pageInstance = new verification_page(driver);

		LocalDate currentDate = LocalDate.now();
		LocalDate date7DaysAgo = currentDate.minusDays(7);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String toDate = currentDate.format(formatter);
		String fromDate = date7DaysAgo.format(formatter);
		Assert.assertTrue(
				verification_pageInstance.verifyTheResultsDateRangeIsWithinTheSelectedRange(fromDate, toDate));
	}

	@Test(priority = 7, groups = {
			"sanity" }, description = "Pre condition: User should be logged in and it is on Inventory > Requisition section \r\n"
					+ "1. Hover the mouse over the star/favourite icon. \r\n"
					+ "2. Verify that a tooltip with the text \"Remember this date\" appears when hovering over the star.")
	public void verifyToolTipText() throws Exception {
		verification_pageInstance = new verification_page(driver);

		Map<String, String> pharmacyExpectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "verification");
		Assert.assertEquals(verification_pageInstance.verifyToolTipText(), pharmacyExpectedData.get("favouriteIcon"));
	}

	@Test(priority = 8, groups = {
			"sanity" }, description = "Pre condition: User should be logged in and it is on Inventory > Requisition section \r\n"
					+ "1. Hover the mouse over the star/favourite icon. \r\n"
					+ "2. Verify that a tooltip with the text \"Remember this date\" appears when hovering over the star.")
	public void verifyDatesAreRemeberedCorrectly() throws Exception {
		verification_pageInstance = new verification_page(driver);

		LocalDate currentDate = LocalDate.now();
		LocalDate date7DaysAgo = currentDate.minusDays(50);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String toDate = currentDate.format(formatter);
		String fromDate = date7DaysAgo.format(formatter);

		System.out.println("From Date : " + fromDate + ", To Date : " + toDate);
		Assert.assertTrue(verification_pageInstance.verifyDatesAreRememberedCorrectly(fromDate, toDate));
	}

	@Test(priority = 9, groups = {
			"sanity" }, description = "Pre condition: User should be logged in and it is on verification module\r\n"
					+ "1. Click on the data range button\r\n" + "2. Select\"one week\" option from the drop down\r\n"
					+ "3. Click on \"OK\" button")
	public void verifyResultDataIsAsPerTheSelectedDateRange() throws Exception {
		verification_pageInstance = new verification_page(driver);

		verification_pageInstance.highlightAndClickOnElement(verification_pageInstance.getStarIconLocator(),
				"Tool Tip");
		Assert.assertTrue(verification_pageInstance.clickDateRangeDropdownAndSelect("Last 1 Week"));
		LocalDate currentDate = LocalDate.now();
		LocalDate date7DaysAgo = currentDate.minusDays(7);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String toDate = currentDate.format(formatter);
		String fromDate = date7DaysAgo.format(formatter);
		Thread.sleep(3000); // Waiting for data to load
		Assert.assertTrue(
				verification_pageInstance.verifyTheResultsDateRangeIsWithinTheSelectedRange(fromDate, toDate));
	}

	@Test(priority = 10, groups = {
			"sanity" }, description = "Pre condition: User should be logged in and it is on Verification module\r\n"
					+ "1. Click on the \"Pending \" Radio button from List by Verification Status\r\n"
					+ "2. Click on the \"Approved \" Radio button from List by Verification Status\r\n"
					+ "3. Click on the \"Rejected \" Radio button from List by Verification Status\r\n"
					+ "4. Click on the \"All \" Radio button from List by Verification Status")
	public void verifyAllTheRadioButtonsAreSelectable() throws Exception {
		verification_pageInstance = new verification_page(driver);

		Assert.assertTrue(verification_pageInstance.clickRadioButtonByText("pending"));
		Assert.assertTrue(verification_pageInstance.isRadioButtonSelected("pending"));
		Assert.assertTrue(verification_pageInstance.clickRadioButtonByText("approved"));
		Assert.assertTrue(verification_pageInstance.isRadioButtonSelected("approved"));
		Assert.assertTrue(verification_pageInstance.clickRadioButtonByText("rejected"));
		Assert.assertTrue(verification_pageInstance.isRadioButtonSelected("rejected"));
		Assert.assertTrue(verification_pageInstance.clickRadioButtonByText("all"));
		Assert.assertTrue(verification_pageInstance.isRadioButtonSelected("all"));
		Assert.assertTrue(verification_pageInstance.clickRadioButtonByText("pending")); // To met the pre-condition for
																						// the next test case
	}

	@Test(priority = 11, groups = {
			"sanity" }, description = "Pre condition: User should be logged in and it is on Verification module\r\n"
					+ "1. Click on Requisition Status drop down\r\n" + "2. Click on \"Active\" drop down option\r\n")
	public void verifyRecordsAreFilterdAccordingToRequisitionStatus() throws Exception {
		verification_pageInstance = new verification_page(driver);

		Assert.assertTrue(verification_pageInstance.verifyRecordsAreFilteredAccordingToRequisitionStatus("active"));
	}

	@Test(priority = 12, groups = {
			"sanity" }, description = "Pre condition: User should be logged in and it is on Verification module\r\n"
					+ "1. Select \"All\" radio button\r\n" + "2. Select \"All\"  drop down option\r\n"
					+ "3. Click on \"FROM\" and Select \"Jan 2020\"\r\n"
					+ "4. Click on \"TO\" and select \"march 2024\"\r\n" + "5. Click on \"OK\" button\r\n"
					+ "6. Click on \"View\" button from action column of specific row"
					+ "7. User should navigate to the Check and Verify Requisition page of that Selected specific row ")
	public void verifyRequisitionPageForRecord() throws Exception {
		verification_pageInstance = new verification_page(driver);

		Assert.assertTrue(verification_pageInstance.visitTab("Inventory"));
		Assert.assertTrue(verification_pageInstance.clickRadioButtonByText("all"));
		Assert.assertTrue(verification_pageInstance.selectDropdownValueByText("all"));
		Assert.assertTrue(verification_pageInstance.applyDateFilter("01-01-2024", "01-03-2024"));
		String requisitionNumberOfFirstRequisition = verification_pageInstance
				.getRequisitionNumberAndClickViewButtonForTheFirstRequisition();
		Assert.assertEquals(verification_pageInstance.getRequisitionNumberFromTheReport(),
				requisitionNumberOfFirstRequisition);
		Assert.assertTrue(verification_pageInstance.clickButtonByText("Back to Requisition List"));
	}

	@Test(priority = 13, groups = {
			"sanity" }, description = "Pre condition: User should be logged in and it is on Requisition tab in verification module \r\n"
					+ "1. Click on inventory section \r\n" + "2. Click on the \"Purchase Request\" sub-tab.\r\n"
					+ "3. Find and select the \"All\" radio button to view all records.\r\n"
					+ "4. Fetch and Verify Result Counts. \r\n"
					+ "5. The result count displayed at the bottom of the page should match the total record count.")
	public void verifyRecordCountMatches() throws Exception {
		verification_pageInstance = new verification_page(driver);

		Assert.assertTrue(verification_pageInstance.verifyRecordCountMatches());
	}

	@Test(priority = 14, groups = { "sanity" }, description = "Pre condition: User should be logged in\r\n"
			+ "1. Verify \"Pending\" radio button is visible\r\n"
			+ "2. Scroll all the way to the bottom of the page\r\n" + "3. Verify \"Previous\" button is visible\r\n"
			+ "4. Scroll all the way to the top of the page\r\n" + "5. Verify \"Pending\" radio button is visible")
	public void verifyTheUserIsAbleScrollUpAndDown() throws Exception {
		verification_pageInstance = new verification_page(driver);

		Assert.assertTrue(verification_pageInstance.isPendingRadioButtonVisible());
		Assert.assertTrue(verification_pageInstance.scrollAllTheWayDown());
		Assert.assertTrue(verification_pageInstance.isPreviousButtonVisible());
		Assert.assertTrue(verification_pageInstance.scrollAllTheWayUp());
		Assert.assertTrue(verification_pageInstance.isPendingRadioButtonVisible());
	}

	/*
	 * ----------------Verification L2 test cases starts from here
	 * (15-21)-----------------------------
	 */

	@Test(priority = 15, groups = {
			"sanity" }, description = "Precondition: User should be logged in and on the Verification module\r\n"
					+ "1. Navigate to the \"Internal\" section under Inventory.\r\n"
					+ "2. Click on \"Purchase Request\".\r\n"
					+ "3. Click on the \"Create Purchase Request\" button.\r\n" + "4. Click on the \"Request\" button.")
	public void verifyRequiredFieldErrormessage() throws Exception {
		verification_pageInstance = new verification_page(driver);
		Map<String, String> inventoryExpectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "inventory");

		Assert.assertEquals(verification_pageInstance.verifyRequiredFieldErrormessage(),
				inventoryExpectedData.get("itemNameReq"));

	}

	@Test(priority = 16, groups = {
			"sanity" }, description = "Precondition: User should be logged in and on the Inventory section.\r\n"
					+ "1. Navigate to the \"Internal\" section under Inventory.\r\n"
					+ "2. Click on \"Purchase Request\".\r\n"
					+ "3. Click on the \"Create Purchase Request\" button.\r\n"
					+ "4. Fill in the required fields to create a Purchase Request.\r\n"
					+ "5. Click on the \"Request\" button.\r\n"
					+ "6. Verify that the Purchase Request has been successfully created.")
	public void createAndverifyPurchaseRequest() throws Exception {
		verification_pageInstance = new verification_page(driver);
		Map<String, String> inventoryExpectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "inventory");

		Assert.assertEquals(verification_pageInstance.createAndverifyPurchaseRequest(inventoryExpectedData),
				inventoryExpectedData.get("purchaseRequestCreationMessage"), "Actual and Expected are not equal");
	}

	@Test(priority = 17, groups = {
			"sanity" }, description = "Precondition: User should be logged in and on the Verification section.\r\n"
					+ "1. Navigate to the \"Inventory\" section under Verification.\r\n"
					+ "2. Click on \"Purchase Request\".\r\n"
					+ "3.  Verify that the recently created Purchase Request is visible in the list. \r\n"
					+ "4.  Verify that the status of the Purchase Request is \"Pending\".")
	public void verifyPurchaseRequestInVerificationModule() throws Exception {
		verification_pageInstance = new verification_page(driver);
		Map<String, String> verificationExpectedData = new FileOperations().readExcelPOI(expectedDataFilePath,
				"verification");

		Assert.assertEquals(
				verification_pageInstance.verifyPurchaseRequestInVerificationModule(verificationExpectedData),
				verificationExpectedData.get("status_1"));
	}

	@Test(priority = 18, groups = {
			"sanity" }, description = "Precondition: User should be logged in and on the Verification section.\r\n"
					+ "1. Navigate to the \"Inventory\" section under Verification.\r\n"
					+ "2. Click on \"Purchase Request\".\r\n"
					+ "3. Find the Purchase Request with \"Pending\" status.\r\n"
					+ "4. Click on the \"View\" button under the action column for the Purchase Request.\r\n"
					+ "5. Approve the Purchase Request.\r\n"
					+ "6. Verify the success message confirming the approval.\r\n"
					+ "7. Click on the \"Approved\" radio button.\r\n"
					+ "8. Verify that the status of the Purchase Request has changed to \"Approved\".")
	public void approveThePurchaseRequestAndVerifyStatus() throws Exception {
		verification_pageInstance = new verification_page(driver);
		Map<String, String> verificationExpectedData = new FileOperations().readExcelPOI(expectedDataFilePath,
				"verification");

		Assert.assertFalse(verification_pageInstance.approveThePurchaseRequestAndVerifyStatus(verificationExpectedData)
				.contains(verificationExpectedData.get("purchaseRequestApproveMessage")));
		Assert.assertEquals(verification_pageInstance.verifyPurchaseRequestStatusInTable(verificationExpectedData,
				verificationExpectedData.get("status_2")), verificationExpectedData.get("status_2"));
	}

	@Test(priority = 19, groups = {
			"sanity" }, description = "Precondition: User should be logged in and on the Verification section.\r\n"
					+ "1. Navigate to the \"Inventory\" section under Verification.\r\n"
					+ "2. Click on \"Purchase Request\".\r\n"
					+ "3. Click on the \"View\" button under the action column for the Purchase Request.\r\n"
					+ "4. Click on \"Reject All\" button\r\n")
	public void rejectAPurchaseRequestAndVerifyThePopUpMessage() throws Exception {
		verification_pageInstance = new verification_page(driver);
		Map<String, String> verificationExpectedData = new FileOperations().readExcelPOI(expectedDataFilePath,
				"verification");

		Assert.assertTrue(
				verification_pageInstance.rejectAPurchaseRequestAndVerifyThePopUpMessage(verificationExpectedData)
						.contains(verificationExpectedData.get("remarksCompulsaryForCancellation")));
	}

	@Test(priority = 20, groups = {
			"sanity" }, description = "Precondition: User should be logged in and on the Verificationn section.\r\n"
					+ "1. Navigate to the \"Inventory\" section under Verification.\r\n"
					+ "2. Click on \"Purchase Request\".\r\n" + "3. Click on the \"Approved\" radio button.\r\n"
					+ "4. Find the previously approved Purchase Request.\r\n"
					+ "5. Click on the \"View\" button under the action column for the Purchase Request.\r\n"
					+ "6. Reject the Purchase Request.\r\n" + "7. Click on the \"Rejected\" radio button.\r\n"
					+ "8. Verify that the status of the Purchase Request has changed to \"Rejected\".")
	public void rehectThePurchaseRequestAndVerifyStatus() throws Exception {
		verification_pageInstance = new verification_page(driver);
		Map<String, String> verificationExpectedData = new FileOperations().readExcelPOI(expectedDataFilePath,
				"verification");

		Assert.assertFalse(verification_pageInstance.rejectThePurchaseRequestAndVerifyStatus(verificationExpectedData)
				.contains(verificationExpectedData.get("purchaseRequestApproveMessage")));
		Assert.assertEquals(verification_pageInstance.verifyPurchaseRequestStatusInTable(verificationExpectedData,
				verificationExpectedData.get("status_3")), verificationExpectedData.get("status_3"));
	}

	@Test(priority = 21, groups = { "sanity" }, description = "Under verification > Purchase Request module"
			+ "Take the screenshot of the current page")
	public void takingScreenshotOfCurrentPage() throws Exception {
		verification_pageInstance = new verification_page(driver);

		Assert.assertTrue(verification_pageInstance.takingScreenshotOfTheCurrentPage());
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		System.out.println("before closing the browser");
		browserTearDown();
	}

	@AfterMethod
	public void retryIfTestFails() throws Exception {
		startupPage.navigateToUrl(configData.get("url"));
	}
}

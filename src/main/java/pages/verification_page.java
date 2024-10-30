package pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class verification_page extends StartupPage {

	public By getUsernameTextfieldLocator() {
		return By.id("username_id");
	}

	public By getUsernameTextboxLocator() {
		return By.xpath("//input[@id='username_id']");
	}

	public By getPasswordTextboxLocator() {
		return By.xpath("//input[@id='password']");
	}

	public By getSignInButtonLocator() {
		return By.xpath("//button[@id='login']");
	}

	public By getVerificationLocator() {
		return By.xpath("//a[@href='#/Verification']");
	}

	public By getHomepageLogoLocator() {
		return By.cssSelector("img[class=\"logo-default\"]");
	}

	public By getPageBarFixedLocator(String navBarName) {
		return By.xpath("//ul[@class='page-breadcrumb']/li/a[@href='#/Verification/" + navBarName + "']");
	}

	public By getInventoryPageBarFixedLocator(String navBarName) {
		return By.xpath("//ul[contains(@class,'page-breadcrumb')]/li/a[@href='#/Inventory/" + navBarName + "']");
	}

	public By getSubNavTabLocator(String subNavName) {
		return By.xpath("//div[@class=\"sub-navtab\"]/ul/li/a[text()='" + subNavName + "']");
	}

	public By getButtonLocatorsBytext(String buttonName) {
		return By.xpath("//button[contains(text(),'" + buttonName + "')]");
	}

	public By getAnchorTagLocatorByText(String anchorTagName) {
		return By.xpath("//a[contains(text(),'" + anchorTagName + "')]");
	}

	public By searchBarId() {
		return By.id("quickFilterInput");
	}

	public By calendarFromDropdown() {
		return By.xpath("(//input[@id='date'])[1]");
	}

	public By calendarToDropdown() {
		return By.xpath("(//input[@id='date'])[2]");
	}

	public By getOkButtonLocator() {
		return By.xpath("//button[@class='btn green btn-success']");
	}

	public By getRadioButtonsLocator(String radioButtonName) {
		return By.xpath("//input[@value='" + radioButtonName + "']/../span");
	}

	public By getStarIconLocator() {
		return By.xpath("//i[contains(@class,'icon-favourite')]/..");
	}

	public By favouriteOrStarIcon() {
		return By.xpath("//i[contains(@class,'icon-favourite')]");
	}

	public By verifyRequisitionDropdown() {
		return By.xpath("//div[contains(text(),'Requisition Status:')]/../div/select");
	}

	public By getReqStatus() {
		return By.cssSelector("div[ref='eCenterContainer'] div[col-id='RequisitionStatus']");
	}

	public By getInventoryLocator() {
		return By.xpath("//a[@href='#/Inventory']");
	}

	public By getLocatorById(String idName) {
		return By.id(idName);
	}

	public By getInputFieldLocator(String inputFieldName) {
		return By.cssSelector("input[display-property-name='" + inputFieldName + "']");
	}

	public By getPopUpMessageText(String msgStatus, String messageText) {
		return By.xpath("//p[text()=' " + msgStatus + " ']/../p[contains(@class,'main-message') or contains(text(),'"
				+ messageText + "')]");
	}

	public By getPurchaseRequestStatus(String companyName, String status) {
		return By.xpath("(//div[@col-id='VendorName' and text()='" + companyName
				+ "']/../div[@col-id='VerificationStatus' and text()='" + status + "'])[1]");
	}

	public By getPurchaseRequestViewButton(String status) {
		return By.xpath("(//div[@col-id='VerificationStatus' and text()='" + status
				+ "']/../div/a[@danphe-grid-action='view'])[1]");
	}

	public By getVerificationRemarks() {
		return By.cssSelector("textarea[name='VerificationRemarks']");
	}

	public By getDateRangeButton() {
		return By.cssSelector("td [data-hover='dropdown']");
	}

	public By getActualRequestedOnDates() {
		return By.xpath("//div[@col-id=\"RequisitionDate\"]/span[not(contains(@class,'hidden'))]");
	}

	public By getRequisitionNumberLocatorsForAllRequisitions() {
		return By.xpath("//div[@col-id='RequisitionNo' and @role='gridcell']");
	}

	public By getRequisitionNumberLocatorFromTheReport() {
		return By.xpath("//div[text()='Requisition No:']/b");
	}

	public By getRequisitionStatusDropdownLocator() {
		return By.xpath("//div[text()=' Requisition Status: ']/..//select");
	}

	public By getItemNameRequiredMsg() {
		return By.xpath("//div[contains(text(),'Item is required')]");
	}

	public By getTotalRecordCount() {
		return By.cssSelector("span[ref='eSummaryPanel'] span[ref='lbRecordCount']");
	}

	public By getResultCountLocator() {
		return By.cssSelector("div[class='page-items']");
	}
	
	public By getFirstViewButton() {
		return By.xpath("(//a[text()=\"View\"])[1]");
	}

	public verification_page(WebDriver driver) {
		super(driver);
	}

	/**
	 * @Test1.1 about this method loginTohealthAppByGivenValidCredetial()
	 * 
	 * @param : Map<String, String>
	 * @description : fill usernameTextbox & passwordTextbox and click on sign in
	 *              button
	 * @return : Boolean
	 * @author : Yaksha
	 */
	public boolean loginToHealthAppByGivenValidCredetial(Map<String, String> expectedData) throws Exception {
		Boolean textIsDisplayed = false;
		try {
			WebElement usernametextFieldWebElement = commonEvents.findElement(getUsernameTextfieldLocator());
			commonEvents.highlightElement(usernametextFieldWebElement);
			commonEvents.sendKeys(getUsernameTextfieldLocator(), expectedData.get("username"));

			WebElement passwordtextFieldWebElement = commonEvents.findElement(getPasswordTextboxLocator());
			commonEvents.highlightElement(passwordtextFieldWebElement);
			commonEvents.sendKeys(getPasswordTextboxLocator(), expectedData.get("password"));

			WebElement signinButtonWebElement = commonEvents.findElement(getPasswordTextboxLocator());
			commonEvents.highlightElement(signinButtonWebElement);
			commonEvents.click(getSignInButtonLocator());
			textIsDisplayed = true;
		} catch (Exception e) {
			throw e;
		}
		return textIsDisplayed;
	}

	/**
	 * @Test1.2 about this method scrollDownAndClickVerificationTab()
	 * 
	 * @param : null
	 * @description : verify the pharmacy tab, scroll to it, and click it
	 * @return : String
	 * @author : YAKSHA
	 */
	public void scrollDownAndClickVerificationTab() throws Exception {
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			WebElement verificationTab = commonEvents.findElement(getVerificationLocator());
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", verificationTab);
			commonEvents.highlight(verificationTab);
			commonEvents.click(verificationTab);

			// Wait for the URL to contain "Verification/Inventory"
			commonEvents.waitForUrlContains("Verification/Inventory", 10);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test1.3 about this method verifyVerificationPageUrl()
	 * 
	 * @param : null
	 * @description : verify verification page url
	 * @return : String
	 * @author : YAKSHA
	 */
	public String verifyVerificationPageUrl() throws Exception {
		try {
			String titleToVerify = commonEvents.getCurrentUrl();
			return titleToVerify;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @Test2, 3 about this method highlightAndVerifyWhetherElementIsDisplayed
	 * 
	 * @param element : By - Locator of the element to be highlighted and verified
	 * @description : This method verifies whether an element is displayed on the
	 *              page, highlights it if displayed, and returns true if displayed.
	 * @return : boolean - true if the element is displayed, otherwise false
	 * @author : YAKSHA
	 */
	public boolean highlightAndVerifyWhetherElementIsDisplayed(By element) {
		boolean isElementDisplayed = false;
		try {
			if (commonEvents.isDisplayed(element)) {
				WebElement elementToHighlight = commonEvents.findElement(element);
				commonEvents.highlight(elementToHighlight);
				isElementDisplayed = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isElementDisplayed;
	}

	/**
	 * @Test2, @Test3 and @Test9.1 about this method highlightAndClickOnElement
	 * 
	 * @param element     : By - Locator of the element to be highlighted and
	 *                    clicked
	 * @param elementName : String - Name of the element to be clicked (used for
	 *                    logging purposes)
	 * @description : This method highlights the element and clicks on it. It
	 *              returns true if the element is clicked successfully.
	 * @return : boolean - true if the element is clicked successfully, otherwise
	 *         false
	 * @author : YAKSHA
	 */
	public boolean highlightAndClickOnElement(By element, String elementName) {
		boolean isElementDisplayed = false;
		try {
			WebElement elementToBeClicked = commonEvents.findElement(element);
			commonEvents.highlight(elementToBeClicked);
			commonEvents.click(elementToBeClicked);
			System.out.println("Clicked on " + elementName);
			isElementDisplayed = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isElementDisplayed;
	}

	/**
	 * @Test4 about this method verifySelectedTabIsActiveOrNot()
	 * @param : element - the locator of the tab to be verified
	 * @description : This method verifies if the specified tab is displayed, clicks
	 *              it, and returns whether its "class" attribute contains "active".
	 *              This can be used to determine if the tab is active based on its
	 *              class attributes.
	 * @return : boolean - true if the "class" attribute of the tab contains
	 *         "active", false otherwise
	 * @throws : Exception - if there is an issue locating, highlighting, clicking
	 *           the tab, or getting its attribute
	 * @author : YAKSHA
	 */
	public boolean verifySelectedTabIsActiveOrNot(By element) throws Exception {
		boolean isActive = false;
		try {
			if (commonEvents.isDisplayed(element)) {
				WebElement tabs = commonEvents.findElement(element);
				commonEvents.highlight(tabs);
				commonEvents.click(tabs);
				String locatorAttributeValue = commonEvents.getAttribute(tabs, "class");
				isActive = locatorAttributeValue.contains("active");
			}
		} catch (Exception e) {
			throw e;
		}
		return isActive;
	}

	/**
	 * @Test5 about this method verifyNavigationOfTabs()
	 * @param : element - the locator of the tab to be verified
	 * @description : This method verifies if the specified tab is displayed, clicks
	 *              it, and returns whether its "class" attribute contains "active".
	 *              This can be used to determine if the tab is active based on its
	 *              class attributes. It also navigates through "Inventory" and
	 *              "Requisition" tabs before verifying the "Purchase Request" tab.
	 * @return : boolean - true if the "class" attribute of the tab contains
	 *         "active", false otherwise
	 * @throws : Exception - if there is an issue locating, highlighting, clicking
	 *           the tab, or getting its attribute
	 * @author : YAKSHA
	 */
	public boolean verifyNavigationOfTabs() throws Exception {
		boolean isActive = false;
		try {
			if (commonEvents.isDisplayed(getPageBarFixedLocator("Inventory"))) {

				highlightAndClickOnElement(getPageBarFixedLocator("Inventory"), "inventory tab");
				highlightAndClickOnElement(getSubNavTabLocator("Requisition"), "inventory tab");

				WebElement purchaseReqTab = commonEvents.findElement(getSubNavTabLocator("Purchase Request"));
				commonEvents.highlight(purchaseReqTab);
				commonEvents.click(purchaseReqTab);

				String locatorAttributeValue = commonEvents.getAttribute(purchaseReqTab, "class");
				System.out.println("locatorAttributeValue > " + locatorAttributeValue);
				isActive = locatorAttributeValue.contains("active");

				highlightAndClickOnElement(getSubNavTabLocator("Requisition"), "inventory tab");
			}
		} catch (Exception e) {
			throw e;
		}
		return isActive;
	}

	/**
	 * @Test6 and @Test9.3 about this method
	 *        verifyTheResultsDateRangeIsWithinTheSelectedRange()
	 * 
	 * @param : String, String - from date - to date
	 * @description : This method verifies whether the "Requested On" dates for all
	 *              the result requisitions are within the specified date range.
	 * @return : boolean - true if the actual dates fall within the specified date
	 *         and false if they don't.
	 * @throws : Exception - if there is an issue finding the actual data
	 * @author : YAKSHA
	 */
	public boolean verifyTheResultsDateRangeIsWithinTheSelectedRange(String fromDate, String toDate) throws Exception {
		return false;
	}

	/**
	 * @Test7 about this method verifyToolTipText()
	 * @param : null
	 * @description : Verify the text of the tooltip
	 * @return : String
	 * @throws : Exception - if there is an issue finding the text
	 * @author : YAKSHA
	 */
	public String verifyToolTipText() {
		return null;
	}

	/**
	 * @Test8 about this method verifyDatesAreRememberedCorrectly()
	 * 
	 * @param fromDate - the expected "from" date in the format "dd-MM-yyyy"
	 * @param toDate   - the expected "to" date in the format "dd-MM-yyyy"
	 * @description : This method selects the "from" and "to" dates in the calendar,
	 *              clicks the OK button, navigates away to another tab, returns to
	 *              the original tab, and verifies if the dates are remembered
	 *              correctly.
	 * @return : boolean - true if the dates are remembered correctly, false
	 *         otherwise
	 * @throws : Exception - if there is an issue locating, highlighting, or
	 *           clicking elements, or if there is an issue with date selection or
	 *           verification
	 * @author : YAKSHA
	 * @throws Exception
	 */
	public boolean verifyDatesAreRememberedCorrectly(String fromDate, String toDate) throws Exception {

		return false;
	}

	/**
	 * @Test9.2 about this method clickDateRangeDropdownAndSelect()
	 * 
	 * @param : String - Text of the value to select from dropdown
	 * @description : This method clicks on the date range button and selects a
	 *              value by its text
	 * @return : boolean - true if successfully selected the intended value and
	 *         false if the value is not selected
	 * @throws : Exception - if there is an issue finding the dropdown or its values
	 * @author : YAKSHA
	 */
	public boolean clickDateRangeDropdownAndSelect(String valueToSelect) throws Exception {
		return false;
	}

	/**
	 * @Test14.1 and @Test14.5 about this method isRadioButtonIsVisible()
	 * 
	 * @param : null
	 * @description : This method verifies whether the "Pending" radio button is
	 *              visible on the screen
	 * @return : boolean - true if the "Pending" radio button is visible and false
	 *         if not visible
	 * @throws : Exception - if there is an issue locating or radio button
	 * @author : YAKSHA
	 */
	public boolean isPendingRadioButtonVisible() throws Exception {
	
		return false;
	}

	/**
	 * @Test14.2 about this method scrollAllTheWayDown()
	 * 
	 * @param : null
	 * @description : This method vertically scrolls the screen to the bottom
	 * @return : boolean - true if successfully scrolled down and false if not
	 *         scrolled
	 * @throws : Exception - if there is an issue while scrolling
	 * @author : YAKSHA
	 */
	public boolean scrollAllTheWayDown() throws Exception {

		return false;
	}

	/**
	 * @Test14.3 about this method isPreviousButtonVisible()
	 * 
	 * @param : null
	 * @description : This method verifies whether the "Previous" button is visible
	 *              on the screen
	 * @return : boolean - true if the "Previous" button is visible and false if it
	 *         is not visible
	 * @throws : Exception - if there is an issue finding the button
	 * @author : YAKSHA
	 */
	public boolean isPreviousButtonVisible() throws Exception {
		
		return false;
	}

	/**
	 * @Test14.4 about this method scrollAllTheWayUp()
	 * 
	 * @param : null
	 * @description : This method vertically scrolls the screen to the top
	 * @return : boolean - true if successfully scrolled up and false if not
	 *         scrolled
	 * @throws : Exception - if there is an issue while scrolling
	 * @author : YAKSHA
	 */
	public boolean scrollAllTheWayUp() throws Exception {
		
		return false;
	}

	/**
	 * @Test10.1 @Test10.3 @Test10.5 @Test10.7 @Test12.2 about this method
	 * clickRadioButtonByText()
	 * 
	 * @param : String - Text of the radio button.
	 * @description : This method clicks on the radio button by its text.
	 * @return : boolean - true if the intended radio button was clicked and false
	 *         if it was not clicked
	 * @throws : Exception - if there is an issue finding or clicking the radio
	 *           button
	 * @author : YAKSHA
	 */
	public boolean clickRadioButtonByText(String radioButtonText) throws Exception {
		
		return false;
	}

	/**
	 * @Test10.2 @Test10.4 @Test10.6 @Test10.8 about this method
	 * isRadioButtonSelected()
	 * 
	 * @param : String - Text of the radio button.
	 * @description : This method clicks on the radio button by its text.
	 * @return : boolean - true if the intended radio button was clicked and false
	 *         if it was not clicked
	 * @throws : Exception - if there is an issue finding or clicking the radio
	 *           button
	 * @author : YAKSHA
	 */
	public boolean isRadioButtonSelected(String radioButtonText) throws Exception {
		
		return false;
	}

	/**
	 * @Test11 about this method
	 *         verifyRecordsAreFilteredAccordingToRequisitionStatus()
	 * 
	 * @param : String - status
	 * @description : This method verifies that all records in the table are
	 *              filtered according to the "active" requisition status by
	 *              selecting "active" from a dropdown and checking the status of
	 *              each record in the table.
	 * @return : boolean - true if all the requisition statuses are "active", false
	 *         otherwise
	 * @throws : Exception - if there is an issue selecting the "active" option,
	 *           locating elements, or verifying the statuses
	 * @author : YAKSHA
	 */
	public boolean verifyRecordsAreFilteredAccordingToRequisitionStatus(String status) throws Exception {
		
		return false;
	}

	/**
	 * @Test12.1 about this method visitTab()
	 * 
	 * @param : String - Tab's text
	 * @description : This method navigates the user to the specified tab
	 * @return : boolean
	 * @throws : Exception - if there is an issue finding or clicking the tab.
	 * @author : YAKSHA
	 */
	public boolean visitTab(String tabText) throws Exception {
		
		return false;
	}

	/**
	 * @Test12.3 about this method isRadioButtonSelected()
	 * 
	 * @param : String - Text of the radio button.
	 * @description : This method clicks on the radio button by its text.
	 * @return : boolean - true if the intended radio button was clicked and false
	 *         if it was not clicked
	 * @throws : Exception - if there is an issue finding or clicking the radio
	 *           button
	 * @author : YAKSHA
	 */
	public boolean selectDropdownValueByText(String optionToSelect) throws Exception {
		

		return false;
	}

	/**
	 * @Test12.4 about this method applyDateFilter()
	 * 
	 * @param : String, String
	 * @description : Applies the date filter with date range
	 * @return : void
	 * @throws : Exception - if there is an issue finding or filling the date fields
	 * @author : YAKSHA
	 */
	public boolean applyDateFilter(String fromDate, String toDate) throws Exception {
		
		return false;
	}

	/**
	 * @Test12.5 about this method clickViewButtonForTheFirstRequisition()
	 * 
	 * @param : null
	 * @description : This method clicks on the "View" button for the first result.
	 * @return : String - requisition number of the first requisition from the
	 *         result.
	 * @throws : Exception - if there is an issue finding or clicking the radio
	 *           button.
	 * @author : YAKSHA
	 */
	public String getRequisitionNumberAndClickViewButtonForTheFirstRequisition() throws Exception {
		
		return null;
	}

	/**
	 * @Test12.6 about this method getRequisitionNumberFromTheReport()
	 * 
	 * @param : null
	 * @description : This method returns the requisition number from the report
	 * @return : String - requisition number
	 * @throws : Exception - if there is an issue finding the requisition number in
	 *           the report.
	 * @author : YAKSHA
	 */
	public String getRequisitionNumberFromTheReport() throws Exception {
		
		return null;
	}

	/**
	 * @Test12.7 about this method clickButtonByText()
	 * 
	 * @param buttonText : String - The text of the button to be clicked
	 * @description : This method locates a button using its text and performs a
	 *              click action on it. If the button is found and successfully
	 *              clicked, it highlights the button first. In case of failure
	 *              (e.g., button not found or click error), an error message is
	 *              printed and an exception is thrown.
	 * @return : void
	 * @throws : Exception - if there is an issue finding the button or performing
	 *           the click action
	 * @author : YAKSHA
	 */
	public boolean clickButtonByText(String buttonText) throws Exception {
		
		return false;
	}

	/**
	 * @Test13 about this method verifyRecordCountMatches()
	 * 
	 * @param : null
	 * @description : This test case navigates to the "Verification" module, goes to
	 *              "Inventory" -> "Purchase Request", selects "All" radio button,
	 *              and verifies that the shown result count and the total record
	 *              count at the bottom of the page are equal.
	 * @return : boolean - true if the counts match, false otherwise
	 * @throws : Exception - if there is an issue navigating, selecting radio
	 *           buttons, or extracting counts
	 * @author : YAKSHA
	 */
	public boolean verifyRecordCountMatches() throws Exception {
		return false;
	}

	/**
	 * @Test15 about this method verifyRequiredFieldErrormessage()
	 * 
	 * @param : null
	 * @description : This method verifies the error message for a required field
	 *              when creating a purchase request. It navigates to the
	 *              "Inventory" section, selects "Internal", then "Purchase
	 *              Request", and attempts to create a purchase request without
	 *              filling the required item name field. The method highlights the
	 *              required field error message and returns its text.
	 * @return : String - the error message text for the required item name field
	 * @throws : Exception - if there is an issue locating or clicking elements, or
	 *           retrieving the error message text
	 * @author : YAKSHA
	 */
	public String verifyRequiredFieldErrormessage() throws Exception {
		return null;
	}

	/**
	 * @Test16 about this method createAndVerifyPurchaseRequest()
	 * 
	 * @param inventoryExpectedData : A map containing expected data for inventory
	 *                              fields such as vendor name, item name, and
	 *                              remarks.
	 * @description : This method navigates to the Inventory tab, scrolls to the
	 *              Purchase Request section, and creates a new Purchase Request by
	 *              filling in mandatory fields. It then verifies the creation
	 *              success message.
	 * @return : String - the success message text after creating the Purchase
	 *         Request.
	 * @throws : Exception - if there is an issue with locating elements, clicking
	 *           buttons, or filling in fields.
	 * @author : YAKSHA
	 */
	public String createAndverifyPurchaseRequest(Map<String, String> inventoryExpectedData) throws Exception {
		return null;
	}

	/**
	 * @Test17 about this method verifyPurchaseRequestInVerificationModule()
	 * 
	 * @param verificationExpectedData : A map containing expected data for
	 *                                 verification fields such as vendor name and
	 *                                 status.
	 * @description : This method navigates to the Verification tab, scrolls to the
	 *              Purchase Request section under Inventory, and verifies the
	 *              status of a Purchase Request by checking its status text.
	 * @return : String - the status text of the Purchase Request.
	 * @throws : Exception - if there is an issue with locating elements, clicking
	 *           buttons, or verifying the status.
	 * @author : YAKSHA
	 */
	public String verifyPurchaseRequestInVerificationModule(Map<String, String> verificationExpectedData) {
		
		return null;
	}

	/**
	 * @Test18.1 about this method approveThePurchaseRequestAndVerifyStatus()
	 * 
	 * @param : verificationExpectedData - a map containing expected data for
	 *          verification
	 * @description : This method clicks the view button for a purchase request,
	 *              approves it, and verifies the success message after approval.
	 * @return : String - the success message text after approving the purchase
	 *         request
	 * @throws : Exception - if there is an issue locating or clicking elements, or
	 *           verifying the message
	 * @author : YAKSHA
	 */
	public String approveThePurchaseRequestAndVerifyStatus(Map<String, String> verificationExpectedData) {
		return null;
	}

	/**
	 * @Test19 about this method rejectAPurchaseRequestAndVerifyThePopUpMessage()
	 * 
	 * @param verificationExpectedData - a map containing expected data for
	 *                                 verification
	 * @description : This method clicks the view button for a purchase request in
	 *              the approved section, attempts to reject it without entering
	 *              remarks, and verifies the popup message indicating that remarks
	 *              are compulsory for cancellation.
	 * @return : String - the popup message text indicating remarks are compulsory
	 *         for cancellation
	 * @throws : Exception - if there is an issue locating or clicking elements, or
	 *           verifying the popup message
	 * @author : YAKSHA
	 */
	public String rejectAPurchaseRequestAndVerifyThePopUpMessage(Map<String, String> verificationExpectedData) {
		
		return null;
	}

	/**
	 * @Test20.1 about this method rejectThePurchaseRequestAndVerifyStatus()
	 * 
	 * @param : verificationExpectedData - a map containing expected data for
	 *          verification
	 * @description : This method clicks the view button for a purchase request in
	 *              the approved section, rejects it, enters remarks, and verifies
	 *              the success message after rejection.
	 * @return : String - the success message text after rejecting the purchase
	 *         request
	 * @throws : Exception - if there is an issue locating or clicking elements,
	 *           entering remarks, or verifying the message
	 * @author : YAKSHA
	 */
	public String rejectThePurchaseRequestAndVerifyStatus(Map<String, String> verificationExpectedData) {
		return null;

	}

	/**
	 * @Test18.2 and @Test20.2 about this method
	 * verifyPurchaseRequestStatusInTable()
	 * 
	 * @param : verificationExpectedData - a map containing expected data for
	 *          verification
	 * @param : status - the status to be verified (e.g., "approved", "pending",
	 *          "rejected")
	 * @description : This method clicks the radio button for the specified status,
	 *              finds the purchase request with the given vendor name and
	 *              status, and verifies its status in the table.
	 * @return : String - the status of the purchase request from the table
	 * @throws : Exception - if there is an issue locating or clicking elements, or
	 *           verifying the status
	 * @author : YAKSHA
	 */
	public String verifyPurchaseRequestStatusInTable(Map<String, String> verificationExpectedData, String status) {
		return null;	
		
	}

	/**
	 * @Test21 about this method takingScreenshotOfTheCurrentPage()
	 * @param : null
	 * @description : Taking screenshot of the current page.
	 * @return : Boolean
	 * @author : YAKSHA
	 */
	public Boolean takingScreenshotOfTheCurrentPage() throws Exception {
		
		return false;
	}

}
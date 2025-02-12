package WebPageObjects;

import ProjectBase.TestBaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HanaDashBoardPage extends TestBaseClass {
    public HanaDashBoardPage() {
        PageFactory.initElements(getChromeDriver(), this);
    }

    //========================================= Hana Dashboard page web elements =========================
    @FindBy(xpath = "//a[@class='li_Hana navbar-brand']")
    private WebElement HanaLogo;

    @FindBy(xpath = "//a[@id='clsUnRead']/following::button[3]")
    private WebElement NewOrder_Menu_Btn;

    @FindBy(xpath = "//a[@id='clsUnRead']/following::button[5]")
    private WebElement Dispatch_Menu_Btn;

    @FindBy(xpath = "(//button[@class='btn btn-default dropdown-toggle'][normalize-space()='New Order'])")
    private WebElement NewOrderMenuBtn;

    @FindBy(xpath = "(//button[@class='btn btn-default dropdown-toggle'][normalize-space()='Dispatch'])")
    private WebElement DispatchMenuBtn;

    @FindBy(xpath = "//a[@class='li_Dispatch']")
    private WebElement quickDispatch;

    @FindBy(xpath = "//a[@class='li_AdvanceDispatch']")
    private WebElement advanceDispatch;

    @FindBy(xpath = "((//button[@class='btn btn-default dropdown-toggle'][normalize-space()='New Order'])/following::ul//li[1]//a)[1]")
    ////a[@class='li_NewOrder']
    private WebElement OrderEntry;

    @FindBy(xpath = "((//button[@class='btn btn-default dropdown-toggle'][normalize-space()='New Order'])/following::ul//li[2]//a)[1]")
    ////a[@class='li_CashAndCarry']
    private WebElement CashAndCarry;

    @FindBy(xpath = "//span[normalize-space()='Orders']")
    private WebElement OrdersMenu;

    @FindBy(xpath = "//a[normalize-space()='All Orders']")
    private WebElement AllOrdersSubMenu;

    @FindBy(xpath = "//select[@id='ddlShop']")
    private WebElement ShopNameDropDown;

    @FindBy(xpath = "//div[@id='dashboard_datepicker']//div[@class='dashborad-tabs']")
    private WebElement DateSelection;

    @FindBy(xpath = "//span[contains(text(),' Wait Queue ')]")
    private WebElement WaitQueueTab;

    @FindBy(xpath = "//span[@id='lblWaitQueue']")
    private WebElement WaitQueueCount;

    @FindBy(xpath = "//span[contains(text(),' Pending Pickups ')]")
    private WebElement PendingPickupsTab;

    @FindBy(xpath = "//span[@id='lblPickupOrders']")
    private WebElement PendingPickupsCount;

    @FindBy(xpath = "//span[contains(text(),' Pending Deliveries ')]")
    private WebElement PendingDeliveriesTab;

    @FindBy(xpath = "//span[@id='lblAwaitingDispatch']")
    private WebElement PendingDeliveriesCount;

    @FindBy(xpath = "//span[contains(text(),' Pending Confirmations ')]")
    private WebElement PendingConfirmationsTab;

    @FindBy(xpath = "//span[@id='lblUnconfirm']")
    private WebElement PendingConfirmationsCount;

    @FindBy(xpath = "//label[@id='lblWaitcnt']")
    private WebElement WaitQueueIconCount;

    @FindBy(xpath = "//a[@id='idWaitQueue']")
    private WebElement WaitQueueIcon;

    @FindBy(xpath = "(//i[@class='fa fa-2x fa-shopping-basket'])[1]")
    private WebElement PendingPickupIcon;

    @FindBy(xpath = "//span[@id='spnPickupCnt']")
    private WebElement PendingPickupIconCount;

    @FindBy(xpath = "(//i[@class='fa fa-2x fa-clipboard'])[1]")
    private WebElement ViewDraftIcon;

    @FindBy(xpath = "//label[@id='lblDraftcnt']")
    private WebElement ViewDraftIconCount;

    @FindBy(xpath = "(//i[@class='fa fa-2x fa-comment'])[1]")
    private WebElement NewMessageIcon;

    @FindBy(xpath = "//label[@id='lblUnRead']")
    private WebElement NewMessageIconCount;

    @FindBy(xpath = "(//button[normalize-space()='Dispatch'])[1]")
    private WebElement DispatchMenu;

    @FindBy(xpath = "//a[normalize-space()='Advanced Dispatch']")
    private WebElement AdvancedDispatch;

    @FindBy(xpath = "//a[normalize-space()='Quick Dispatch']")
    private WebElement QuickDispatch;

    @FindBy(xpath = "(//i[@data-toggle='tooltip'])[1]")
    private WebElement NewTabPlusIcon;

    @FindBy(xpath = "(//input[@id='top-search1'])[1]")
    private WebElement DashboardSearchBox;

    @FindBy(xpath = "//a[@id='idEmail']")
    private WebElement EmailIcon;

    @FindBy(xpath = "//label[@id='lblEmail']")
    private WebElement EmailIconCount;

    @FindBy(xpath = "//a[@id='CalenderList']")
    private WebElement IntegrateCalenderIcon;

    @FindBy(xpath = "//a[@id='btnRemoteConnect']")
    private WebElement RemoteConnectIcon;

    @FindBy(xpath = "//a[@class='open-small-chat']")
    private WebElement HelpIcon;

    @FindBy(xpath = "//a[@id='hana-profile-menu-link']")
    private WebElement ProfileIcon;

    @FindBy(xpath = "//a[@class='li_LogoutNew common-dynamic-font-size']")
    private WebElement LogoutIcon;

    @FindBy(xpath = "//ul[@class='dropdown-menu']//li//a")
    private List<WebElement> NewOrderList;

    @FindBy(id = "CustomersMenu")
    private WebElement CustomersMenu;

    @FindBy(xpath = "//li[@id='ConfigurationMenu']")
    private WebElement ConfigurationMenu;

    @FindBy(xpath = "//a[@class='li_Configuration']")
    private WebElement settingsSubmenu;

    @FindBy(xpath = "//a[@class='li_Websiteadmin dropdown-item' and contains(text(), 'Web Admin')]")
    private WebElement WebAdmin_SubMenu;

    @FindBy(xpath = "(//span[normalize-space()='Marketing'])[1]")
    private WebElement MarketingMenu;

    @FindBy(xpath = "//a[@class='li_EmailMarketing dropdown-item' and contains(text(),'Email / SMS Marketing')]")
    private WebElement email_marketing_submenu;

    @FindBy(xpath = "//a[@class='dropdown-item li_Reminder']")
    private WebElement Reminder_FromMarkingMenu;

    //	Orders Menu Element from the Left menu
    @FindBy(xpath = "//li[@id='OrderMenu']")
    private WebElement OrderMenu;

    //	Confirmation sub menu Element from the Orders Menu in the Left Menu
    @FindBy(xpath = "//a[@id='btnOpenDeliveryConfModalNew']")
    private WebElement ConfirmationSubMenu;

    @FindBy(xpath = "//a[@id='userWebsiteDisplayName']")
    private WebElement userWebsiteDisplayName;

    @FindBy(xpath = "//li[@id='ReportsMenu']/a")
    private WebElement Reports_Menu;

    @FindBy(xpath = "//a[@data-action='DeliveryReview']")
    private WebElement Payroll_Report_Sub_Menu;

    //======================================= Hana Dashboard page Web Elements =========================
    public String VerifyPageTitleonDashboard() {
        return getDriver().getTitle();
    }


    /**
     * Selects the shop name from the dropdown field on dashboard page
     *
     * @param shopname Provided Shop name to be selected
     * @Description: This function select the visible text of provided shop name from the dropdown field on the hana dashboard page
     * @Author: Balaji N
     */
   /* public HanaDashBoardPage SelectShopNameDropDown(String shopname) {
        fluentWait(ShopNameDropDown);
        Select select = new Select(ShopNameDropDown);
        select.selectByVisibleText(shopname);
        return this;
    }*/
    public HanaDashBoardPage SelectShopNameDropDown(String shopname) {
        try {
            // Wait for the ShopNameDropDown to be visible
            fluentWait(ShopNameDropDown);

            // Highlight the dropdown element for better visibility during debugging
            // HighlightElement(ShopNameDropDown);

            // Create a Select object and choose the shop name by visible text
            Select select = new Select(ShopNameDropDown);
            select.selectByVisibleText(shopname);
        } catch (NoSuchElementException e) {
            System.err.println("Shop name '" + shopname + "' not found in the dropdown.");
            throw new RuntimeException("Shop name not found: " + shopname, e);
        } catch (Exception e) {
            System.err.println("An error occurred while selecting the shop name.");
            throw new RuntimeException("Failed to select shop name: " + shopname, e);
        }

        return this;
    }


    /**
     * It gets the selected shop name from the dropdown field on dashboard page
     *
     * @return If the shop name is selected it returns the selected shop name; otherwise it returns null
     * @Description: This function select the visible text of provided shop name from the dropdown field on the hana dashboard page
     * @Author: Balaji N
     */
    public String get_selected_shopname_from_hanadashboard() {
        Select select = new Select(ShopNameDropDown);
        return select.getFirstSelectedOption().getText();
    }

    /**
     * Validates whether the Hana home icon is present on the hana dashboard page.
     *
     * @return if the password field is displayed on the page it return true; otherwise it returns false
     * @Description: This function highlights the home icon and checks if it is displayed on hana dashboard page.
     * @Author: Balaji N
     */
/*    public boolean VerifyHanaDashBoardPage() {
        boolean isLogoDisplayed = false;
        HighlightElement(HanaLogo);
        fluentWait(HanaLogo);
        delayWithGivenTime(2000);

        if (HanaLogo.isDisplayed() == false) {
            getDriver().navigate().refresh();
        }

        isLogoDisplayed = HanaLogo.isDisplayed();
        return isLogoDisplayed;
    }*/
    public boolean VerifyHanaDashBoardPage() {
        try {
            boolean isLogoDisplayed = false;
            int retryCount = 3; // Number of retries for refreshing the page
            int currentAttempt = 0;

            while (currentAttempt < retryCount) {
                try {
                    // Highlight the HanaLogo element
                    HighlightElement(HanaLogo);

                    // Fluent wait for HanaLogo to be present and visible
                    fluentWait(HanaLogo);

                    // Verify if the HanaLogo is displayed
                    if (HanaLogo.isDisplayed()) {
                        isLogoDisplayed = true;
                        break; // Exit the loop as the logo is displayed
                    }
                } catch (Exception e) {
                    System.out.println("HanaLogo not found. Attempting refresh...");
                }
                // Refresh the page and retry
                getDriver().navigate().refresh();
                currentAttempt++;
            }

            return isLogoDisplayed;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }


    /**
     * Clicks on the Hana home icon using javascript click.
     *
     * @Description: This function clicks on the Hana home icon using javascript click.
     *
     * <p>
     * Expected functionality: If the Hana home icon is clicked on hana dashboard page, It should be refreshed or reloaded
     * </p>
     * @Author: Balaji N
     */
    public void ClickOnHomeIcon() {
        jsClick(HanaLogo);
    }

    public void NewOrderMenuClick() {
        HighlightElement(NewOrderMenuBtn);
        MouseHover(NewOrderMenuBtn);
        HighlightElement(OrderEntry);
        click(OrderEntry);

    }

    /**
     * Clicks on the Cash and Carry menu option.
     *
     * @Description: This function clicks on the Cash and Carry menu option.
     * @Author: Balaji N
     */
    public void CashAndCarryMenuClick() {
        int retryCount = 3; // Number of retries to handle flakiness
        boolean isClicked = false;

        for (int attempt = 0; attempt < retryCount; attempt++) {
            try {
                // Initial wait for potential UI transitions
                delayWithGivenTime(2000);

                // Explicit wait for the New Order button to be clickable
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(40));
                WebElement newOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='btn btn-default dropdown-toggle'][normalize-space()='New Order'])")));

                // Perform fluent wait on NewOrderMenuBtn
                fluentWait(NewOrderMenuBtn);

                // Create Actions instance
                Actions action = new Actions(getDriver());

                // Hover over the New Order menu button
                action.moveToElement(NewOrderMenuBtn).perform();
                delayWithGivenTime(2000);

                // Fluent wait for the Cash and Carry option
                fluentWait(CashAndCarry);
                HighlightElement(CashAndCarry);

                // Click the Cash and Carry option
                action.moveToElement(CashAndCarry).click().perform();
                isClicked = true;

                break;

            } catch (TimeoutException e) {
                System.out.println("TimeoutException on attempt " + (attempt + 1) + ": " + e.getMessage());
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException on attempt " + (attempt + 1) + ": " + e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("NoSuchElementException on attempt " + (attempt + 1) + ": " + e.getMessage());
            } catch (ElementNotInteractableException e) {
                System.out.println("ElementNotInteractableException on attempt " + (attempt + 1) + ": " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected exception on attempt " + (attempt + 1) + ": " + e.getMessage());
            }

            // Retry mechanism with a short delay before retrying
            if (!isClicked && attempt < retryCount - 1) {

                delayWithGivenTime(2000);
            }
        }

        // Final validation after all retries
        if (!isClicked) {
            throw new RuntimeException("Failed to click on the Cash and Carry menu after " + retryCount + " attempts.");
        }
    }

/*    public CashAndCarryPage CashAndCarryMenuClick() {
        ThreadWait(2000);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(40));
        WebElement newOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='btn btn-default dropdown-toggle'][normalize-space()='New Order'])")));
        fluentWait(NewOrderMenuBtn);
        Actions action = new Actions(getDriver());
        action.moveToElement(NewOrderMenuBtn).build().perform();
        delayWithGivenTime(2000);
        fluentWait(CashAndCarry);
        HighlightElement(CashAndCarry);
        action.moveToElement(CashAndCarry).click().build().perform();
        return new CashAndCarryPage();
    }*/

    /**
     * Hovering the mouse on new and Clicks the Order Entry option on hana dashboard page.
     *
     * @return If the Order Entry option is clicked it returns Cash And Carry Page otherwise it returns null
     * @Description: This function will Hovering the mouse and Clicks the Order Entry option on hana dashboard page it should display the
     * Cash and carry page.
     * @Author: Balaji N
     */
    public void ClickOrderEntry() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(40));
        Actions action = new Actions(getDriver());

        try {
            // Wait for the page to fully load
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            // Wait for any AJAX calls to complete
            Boolean isJQueryPresent = (Boolean) ((JavascriptExecutor) getDriver()).executeScript("return typeof jQuery != 'undefined'");
            if (Boolean.TRUE.equals(isJQueryPresent)) {
                wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return jQuery.active == 0"));
            }

            // Synchronize and interact with elements
            fluentWait(NewOrderMenuBtn);
            action.moveToElement(NewOrderMenuBtn).perform();

            fluentWait(OrderEntry);
            HighlightElement(OrderEntry);
            action.moveToElement(OrderEntry).click().perform();

        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {

            throw e;
        }
    }


    public void Hover_Dispatch_And_Click_QuickDispatch() {
        fluentWait(DispatchMenuBtn);
        Actions action = new Actions(getDriver());
        action.moveToElement(DispatchMenuBtn).build().perform();
        delayWithGivenTime(1500);
        fluentWait(quickDispatch);
        ThreadWait(500);
        HighlightElement(quickDispatch);
        action.moveToElement(quickDispatch).click().build().perform();
    }

    public void Hover_Dispatch_And_Click_AdvanceDispatch() {
        fluentWait(DispatchMenuBtn);
        Actions action = new Actions(getDriver());
        action.moveToElement(DispatchMenuBtn).build().perform();
        delayWithGivenTime(1500);
        fluentWait(advanceDispatch);
        ThreadWait(500);
        HighlightElement(advanceDispatch);
        action.moveToElement(advanceDispatch).click().build().perform();
    }

    /**
     * Clicks the Orders menu from the left menu
     *
     * @Description: This function will wait for the Orders menu element using fluent wait on Orders menu and clicks it using jsclick function.
     * @Author: Balaji N
     */
    public void ClickOrder() {
        fluentWait(OrdersMenu);
        js_Click(OrdersMenu,"Orders Menu on Hana Dashboard Page");
    }

    public void ClickAllOrder() {
        explicitWait(OrdersMenu);
        MouseHoverAndClick(OrdersMenu, AllOrdersSubMenu);
    }

    public HanaDashBoardPage SelectShopName(String shopname) {
        dropDown(ShopNameDropDown, shopname, "VisibleText");
        return this;
    }

    /**
     * Validates the Order Entry option is displayed on the Hana dashboard page.
     *
     * @return If the Order Entry option is displayed on the page it return true; otherwise it returns false
     * @Description: This function will hover the mouse on New Order Menu button and checks if Order Entry option on hana dashboard page is displayed.
     * @Author: Balaji N
     */
    public boolean VerifyOrderEntryOptionIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(getChromeDriver(), Duration.ofSeconds(30));
        FluentWait<WebDriver> fluentWait = new FluentWait<>(getChromeDriver())
                .withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        int retryCount = 3;
        boolean isDisplayed = false;

        for (int attempt = 0; attempt < retryCount; attempt++) {
            try {
                // Ensure the page is fully loaded
                wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

                // Wait for jQuery AJAX calls to complete if present
                Boolean isJQueryPresent = (Boolean) ((JavascriptExecutor) getDriver()).executeScript("return typeof jQuery != 'undefined'");
                if (Boolean.TRUE.equals(isJQueryPresent)) {
                    wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return jQuery.active == 0"));
                }

                // Wait for elements to be visible and interactable
                fluentWait.until(ExpectedConditions.visibilityOf(NewOrderMenuBtn));
                HighlightElement(NewOrderMenuBtn);
                Mouse_Hover(NewOrderMenuBtn, "New Order Menu Button on Hana Dashboard Page");

                fluentWait.until(ExpectedConditions.visibilityOf(OrderEntry));
                HighlightElement(OrderEntry);

                isDisplayed = is_Element_Displayed(OrderEntry, "Order Entry Option on New Order Menu");
                //OrderEntry.isDisplayed();

                break;

            } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
                System.err.println("Attempt " + (attempt + 1) + " failed. Retrying...");
                if (attempt == retryCount - 1) {
                    throw new RuntimeException("Failed to verify Order Entry option after " + retryCount + " attempts.", e);
                }
            }
        }

        return isDisplayed;
    }

    /**
     * Validates the Cash and carry option is displayed on the Hana dashboard page.
     *
     * @return If the Cash and Carry option is displayed on the page it return true; otherwise it returns false
     * @Description: This function will hover the mouse on New Order Menu button and checks if Cash and carry option on hana dashboard page is displayed.
     * @Author: Balaji N
     */
    public boolean Verify_Cashandcarry_OptionIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(getChromeDriver(), Duration.ofSeconds(30));
        FluentWait<WebDriver> fluentWait = new FluentWait<>(getChromeDriver())
                .withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        int retryCount = 3;
        boolean isDisplayed = false;

        for (int attempt = 0; attempt < retryCount; attempt++) {
            try {
                System.out.println("Attempt " + (attempt + 1) + " to verify Cash and Carry option...");

                // Wait for page load and AJAX completion
                wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

                Boolean isJQueryPresent = (Boolean) ((JavascriptExecutor) getDriver()).executeScript("return typeof jQuery != 'undefined'");
                if (Boolean.TRUE.equals(isJQueryPresent)) {
                    wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return jQuery.active == 0"));
                }

                // Wait for elements to be visible and interactable
                fluentWait.until(ExpectedConditions.visibilityOf(NewOrderMenuBtn));
                HighlightElement(NewOrderMenuBtn);
                Mouse_Hover(NewOrderMenuBtn, "New Order Menu Button on Hana Dashboard Page");

                fluentWait.until(ExpectedConditions.visibilityOf(CashAndCarry));
                HighlightElement(CashAndCarry);

                isDisplayed = is_Element_Displayed(CashAndCarry, "Cash and Carry Option on New Order Menu"); //CashAndCarry.isDisplayed()
                System.out.println("Cash and Carry option is displayed.");
                break;

            } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
                System.err.println("Attempt " + (attempt + 1) + " failed. Retrying...");
                if (attempt == retryCount - 1) {
                    throw new RuntimeException("Failed to verify Cash and Carry option after " + retryCount + " attempts.", e);
                }
            }
        }

        return isDisplayed;
    }

    public boolean Verify_QuickDispatch_OptionIsDisplayed() {
        MouseHover(DispatchMenuBtn);
        return quickDispatch.isDisplayed();
    }

    public boolean Verify_AdvanceDispatch_OptionIsDisplayed() {
        MouseHover(DispatchMenuBtn);
        //HighlightElement(advanceDispatch);
        return advanceDispatch.isDisplayed();
    }

    public static void waitForElementAndHover(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).perform();
    }

    public static boolean isElementVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            System.err.println("Element not visible: " + element);
            return false;
        }
    }

    public void ClickCustomersMenu() {
        fluentWait(CustomersMenu);
        CustomersMenu.click();
        //jsClick(CustomersMenu);
    }

    public void Click_ConfigurationMenu() {
        jsClick(ConfigurationMenu);
    }


    public void Click_settingsSubmenu() {
        try {
            MouseHover(ConfigurationMenu);
            jsClick(settingsSubmenu);
            switchToWindowbyIndex(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Click_WebAdmin_Submenu() {
        try {
            // MouseHover(ConfigurationMenu);
            Mouse_Hover(ConfigurationMenu, "Configuration Menu on Hana Dashboard Page");
            ThreadWait(2000);
            // jsClick(WebAdmin_SubMenu);
            js_Click(WebAdmin_SubMenu, "Web Admin SubMenu on Configuration Menu");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Click_Marketing_RemainderMenu() {
        MouseHover(MarketingMenu);
        delayWithGivenTime(1000);
        click(Reminder_FromMarkingMenu);
    }

    public void Click_EmailMarketing_SubMenu() {
        MouseHover(MarketingMenu);
        delayWithGivenTime(1000);
        jsClick(email_marketing_submenu);
    }


    //	Navigate to Confirmation Sub Menu
    public void MouseAndClick_Confirmation_sub_menu() {
        MouseHover(OrderMenu);
        delayWithGivenTime(1000);
        click(ConfirmationSubMenu);
    }

    /**
     * Clicks the Profile icon on the Hana dashboard page.
     *
     * @Description: This function will click the Profile icon on the Hana dashboard page.
     * @Author: Balaji N
     */
    public void Click_ProfileIcon_On_HanaDashBoardPage_And_Clicks_User_Website() {
        fluentWait(ProfileIcon);
        HighlightElement(ProfileIcon);
        explicitWait(ProfileIcon);
        Actions action = new Actions(getDriver());
        action.moveToElement(ProfileIcon).build().perform();
        delayWithGivenTime(2000);
        if (userWebsiteDisplayName.isDisplayed() == true) {
            click(userWebsiteDisplayName);
        }
        switchToWindowbyIndex(1);
    }

    //	Navigate to Confirmation Sub Menu
    public void MouseAndClick_Payroll_Reports_sub_menu() {
        scrollAction(Reports_Menu);
        MouseHover(Reports_Menu);
        delayWithGivenTime(1000);
        jsClick(Payroll_Report_Sub_Menu);
    }

}

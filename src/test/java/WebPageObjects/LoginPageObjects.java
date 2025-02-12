package WebPageObjects;

import ProjectBase.TestBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects extends TestBaseClass {
    /**
     * This is the constructor for the {@code LoginPage} class.
     * It initializes the web elements on the page using Selenium's {@code PageFactory}.
     * The {@code PageFactory.initElements()} method binds the web elements
     * in this page object with their corresponding elements on the web page.
     */

    public LoginPageObjects() {
        if (getChromeDriver() == null) {
            throw new IllegalStateException("WebDriver is not initialized. Make sure to start WebDriver before creating Page Objects.");
        }
        PageFactory.initElements(getChromeDriver(), this);
    }

    // ================ Login page Webelements ================
    @FindBy(xpath = "//div[contains(@class,'loginscreen animated fadeInDown')]//div//h3")
    private WebElement LoginPage;

    @FindBy(id = "Username")
    private WebElement Username;

    @FindBy(id = "Password")
    private WebElement Password;

    @FindBy(id = "btnLogin")
    private WebElement loginbutton;

    @FindBy(xpath = "//span[@class='field-validation-error']")
    private WebElement loginPageErrorMsg;

    @FindBy(xpath = "//span[contains(text(),'Please Enter Valid User Details')]")
    private WebElement IncorrectValidationMsg;

    @FindBy(xpath = "//small[normalize-space()='Forgot UserName?']")
    private WebElement forgotUserName;

    @FindBy(xpath = "//h3[normalize-space()='FORGOT USERNAME']")
    private WebElement forgotUserNamePage;

    @FindBy(xpath = "//small[normalize-space()='Forgot password?']")
    private WebElement forgotpassword;

    @FindBy(xpath = "//h3[normalize-space()='FORGOT PASSWORD']")
    private WebElement forgotPasswordPage;

    @FindBy(xpath = "//small[normalize-space()='Do not have an account?']")
    private WebElement donothaveanaccount;

    @FindBy(xpath = "//a[normalize-space()='Create an account']")
    private WebElement CreateAccount;

    @FindBy(xpath = "//div[@class='llogin text-center loginscreen animated fadeInDown']//div//h3")
    private WebElement loginPage;

    @FindBy(xpath = "//div[@class='form-group text-left']")
    private WebElement terms_and_conditions_entire_element;

    @FindBy(xpath = "//div[@class='form-group text-left']//input[1]")
    private WebElement terms_and_conditions_checkbox;

    // ================ Login page Functions ================

    // Used to verify login page title
    public String VerifyLoginPageTitle() {
        return getDriver().getTitle();
    }

    // Used to validate login page welcome text
    public String ValidateLoginPage() {

        if (LoginPage.getText().contains("Welcome to HANA Florist POS")) {
            HighlightElement(LoginPage);
            return LoginPage.getText();
        }
        return LoginPage.getText();
    }

    /**
     * Validates whether the login page is present on the page.
     *
     * @return if login page is displayed it will return true otherwise returns false
     * @Description: This function will validate if login page is displayed or not.
     * @Author: Balaji N
     */
    public boolean LoginPageIsDisplayed() {
        if (LoginPage.getText().equals("Welcome to HANA POS")) {
            return is_Element_Displayed(LoginPage, "Login Page Welcome text");
        } else if (loginPage.getText().equals("Welcome to HANA Florist POS")) {
            return is_Element_Displayed(LoginPage, "Login Page Welcome text");
        } else {
            return false;
        }
    }


    // Used to validate login page url
    public String ValidateLoginPageUrl() {
        return getDriver().getCurrentUrl();
    }

    /**
     * Enters the username into the login field based on the current environment.
     *
     * @param username The username to be entered into the username field.
     * @return The LoginPage instance for method chaining.
     * @throws RuntimeException If an exception occurs during interaction.
     * @Author: Balaji N
     */
    public void EnterUserName(String username) {
        String environment = prop.getProperty("env");
        switch (environment) {
            case "dev":
                //ClickAndType(Username, username,"Username Field on Login Page");
                ClickAndType(Username, username, "Username Textbox Field on Login Page");
                break;
            case "qa-final":
                ClickAndType(Username, username, "Username Textbox Field on Login Page");
                break;
            case "staging":
                ClickAndType(Username, username, "Username Textbox Field on Login Page");
                break;
            case "live":
                ClickAndType(Username, username, "Username Textbox Field on Login Page");
                //  clickAndType(Username, username);
                break;
            default:
                throw new IllegalArgumentException("Unknown environment: " + environment);
        }

    }

    /**
     * Is used to get the entered username
     *
     * @return Is entered username is displayed it gets the entered username else return null
     * @Description: This function highlights the username field and checks if it is displayed.
     * @Author: Balaji N
     */
    public String get_entered_username() {
        return getElementAttribute(Username, "Username value of Textbox Field on Login Page");
       /* HighlightElement(Username);
        return Username.getAttribute("value");*/
    }

    // Used to validate username field is displayed
    public boolean ValidateUsernameFieldIsPresence() {
        HighlightElement(Username);
        return Username.isDisplayed();
    }

    /**
     * Validates whether the password field is present on the page.
     *
     * @return true if the password field is displayed on the page; false otherwise
     * @Description: This function highlights the password field and checks if it is displayed.
     */
    public boolean ValidatePasswordFieldIsPresence() {
        HighlightElement(Password);
        return Password.isDisplayed();
    }

    /**
     * Enters the given password into the password field on the login page.
     *
     * @param password The password to be entered in the password field
     * @return The current instance of the LoginPage, allowing method chaining
     * @Description: This function clicks on the password field and enters the provided password.
     * @Author: Balaji N
     */
    public void EnterPassword(String password) {
        // clickAndType(Password, password);
        ClickAndType(Password, password, "Password Textbox Field on Login Page");

    }

    /**
     * It returns the entered password.
     *
     * @return If the password entered is exist it returns the value of password entered, otherwise it returns null string
     * @Description: This function highlights the password field and checks if it is displayed.
     * @Author: Balaji N
     */
    public String get_entered_password() {
        return getElementAttribute(Password, "Password value of Textbox Field on Login Page");
        /*HighlightElement(Password);
        return Password.getAttribute("value");*/
    }

    /**
     * Clicks the login button on the login page.
     *
     * @return A new instance of the HanaDashBoardPage after successful login.
     * @Description: This function checks if the Terms and Conditions element is displayed before clicking the login button.
     * @Author: Balaji N
     */

    public HanaDashBoardPage ClickLoginButton() {
        // Check if the Terms and Conditions element is displayed and visible
        if (is_Element_Displayed(terms_and_conditions_entire_element,"Terms and Condition")) {
            /*
            // Dev team has removed the checkbox of terms and conditions
            SO that we comment this function...
            if (!terms_and_conditions_checkbox.isSelected()) {
                jsClick(terms_and_conditions_checkbox);
                System.out.println("Checked the Terms and Conditions checkbox.");
            }*/

            delayWithGivenTime(1000);
            js_Click(loginbutton, "Login Button on Login Page");
        }
        return new HanaDashBoardPage();
    }


    public boolean ValidateLoginButtonIsPresence() {
        HighlightElement(loginbutton);
        return loginbutton.isDisplayed();
    }

    // Used to validate login page error message
    public boolean IsValidationMessageExist() {
        HighlightElement(loginPageErrorMsg);
        return loginPageErrorMsg.isDisplayed();
    }

    public boolean IncorrectErrorMsgIsDisplayed() {
        HighlightElement(IncorrectValidationMsg);
        return IncorrectValidationMsg.isDisplayed();
    }

    public String NullValidationErrorMsg() {
        HighlightElement(loginPageErrorMsg);
        return loginPageErrorMsg.getText();
    }

    public String IncorrectValidationErrorMsg() {
        HighlightElement(IncorrectValidationMsg);
        return IncorrectValidationMsg.getText();
    }


    public boolean ValidateForgotUserNameIsPresence() {
        HighlightElement(forgotUserName);
        return forgotUserName.isDisplayed();
    }

    public void ClickForgotUserName() {
        click(forgotUserName);
    }

    public void ClickForgotPassword() {
        click(forgotpassword);
    }

    public boolean ValidateForgotPasswordIsPresence() {
        HighlightElement(forgotpassword);
        return forgotpassword.isDisplayed();
    }

    public boolean ValidateDonothaveanaccountIsPresence() {
        HighlightElement(donothaveanaccount);
        return donothaveanaccount.isDisplayed();
    }

    public boolean ValidateCreateAccountIsPresence() {
        HighlightElement(CreateAccount);
        return CreateAccount.isDisplayed();
    }

    public boolean ValidateForgotUserNamePage() {
        HighlightElement(forgotUserNamePage);
        return forgotUserNamePage.isDisplayed();
    }

    public boolean ValidateForgotPasswordPage() {
        HighlightElement(forgotPasswordPage);
        return forgotPasswordPage.isDisplayed();
    }
}

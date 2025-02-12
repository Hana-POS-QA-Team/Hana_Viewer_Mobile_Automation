package ProjectBase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Optional;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;

import static java.lang.System.getProperty;

public class TestBaseClass {
    public static AndroidDriver driver;
    private static final String CHROMEDRIVER_PATH = getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\chromedriver.exe";
    private static AppiumDriverLocalService service;
    //    private static ChromeDriver chromeDriver;
    public static Properties prop;
    public static ThreadLocal<RemoteWebDriver> chromeDriver = new ThreadLocal<>();
    // Maps to track request start times, status codes, and response sizes
    public DesiredCapabilities capabilities = new DesiredCapabilities();

    public void SuiteBeforeMethods() {
        startServer();
        System.out.println(CHROMEDRIVER_PATH);
//        launchEmulator();
//        try {
//            Thread.sleep(5000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        launchAndroidApp();
    }

    public void Suite_Chrome_Before_Method() {
//        startServer();
        System.out.println(CHROMEDRIVER_PATH);
//        launchEmulator();
//        try {
//            Thread.sleep(5000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        setup_chrome("chrome");
    }

    public void setup_chrome(@Optional("chrome") String browser) {
        try {
            if (getChromeDriver() != null) {
                launchApplication(browser);

            }else{
                System.out.println("Chrome is not setup properly");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tearDown_chrome() {
        if (getChromeDriver() != null) {
            try {
                getChromeDriver().quit();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * This method is used to launch the Android mobile app with opening chrome applicaiton
     */
    public static void launchAndroidApp() {
        DesiredCapabilities caps = new DesiredCapabilities();
        // General Capabilities
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:platformVersion", "15");
        caps.setCapability("appium:deviceName", "AutomationEmulator");
        caps.setCapability("appium:automationName", "UiAutomator2");

        // App-Specific Capabilities (Testing an APK File)
        caps.setCapability("appium:app", "C:\\Users\\sakrateesh\\Downloads\\base.apk");
        caps.setCapability("appium:appPackage", "com.Hana.HanaViewersNew");
        caps.setCapability("appium:appActivity", "com.Hana.HanaViewersNew.MainActivity");

        // Additional Appium Settings
        caps.setCapability("appium:ensureWebviewsHavePages", true);
        caps.setCapability("appium:nativeWebScreenshot", true);
        caps.setCapability("appium:newCommandTimeout", 3600);
        caps.setCapability("appium:connectHardwareKeyboard", true);


        try {
            // Initialize Appium Driver
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            // Perform actions here if needed

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void fluentWait(WebElement ele) {
        Wait<WebDriver> wait = null;
        try {
            wait = new FluentWait<WebDriver>((WebDriver) getDriver())
                    .withTimeout(Duration.ofSeconds(60))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(Exception.class);
            wait.until(ExpectedConditions.visibilityOf(ele));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Perform a mouse hover action on a given element with enhanced exception handling.
     *
     * @param ele The WebElement to hover over.
     * @throws IllegalArgumentException        If the provided element is null.
     * @throws ElementNotInteractableException If the element is not interactable.
     * @throws NoSuchElementException          If the element is not found in the DOM.
     * @Author Balaji N
     */
    public void js_Click(WebElement ele, String fieldName) {
        try {
            // Validate the WebElement is not null
            if (ele == null) {
                throw new IllegalArgumentException("The provided WebElement is null.");
            }

            // Validate the WebElement is attached to the DOM and visible
            if (!ele.isDisplayed()) {
                throw new ElementNotInteractableException("The provided WebElement is not visible on the DOM.");
            }

            // Synchronize with the WebElement to avoid stale or unready element issues
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(ele));

            // Highlight the element for debugging purposes
            HighlightElement(ele);

            // Perform the mouse hover action
            Actions action = new Actions(getDriver());
            action.moveToElement(ele).perform();

            // Optional delay (can be removed for performance optimization)
            delayWithGivenTime(500);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid argument: " + e.getMessage());
            throw e; // Re-throw the exception
        } catch (StaleElementReferenceException e) {
            System.err.println("Stale element reference: " + e.getMessage());
            throw e; // Re-throw the exception
        } catch (ElementNotInteractableException e) {
            System.err.println("Element not interactable: " + e.getMessage());
            throw e; // Re-throw the exception
        } catch (NoSuchElementException e) {
            System.err.println("No such element found: " + e.getMessage());
            throw e; // Re-throw the exception
        } catch (TimeoutException e) {
            System.err.println("Timeout while waiting for the element to be visible: " + e.getMessage());
            throw e; // Re-throw the exception
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred during mouse hover.", e); // Wrap and re-throw
        }
    }

    public void MouseHoverAndClick(WebElement hoverele, WebElement clickeele) {
        try {
            HighlightElement(hoverele);
            fluentWait(clickeele);

            Actions action = new Actions(getDriver());
            action.moveToElement(hoverele).build().perform();

            delayWithGivenTime(2000);

            HighlightElement(clickeele);
            action.moveToElement(clickeele).click().build().perform();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public void explicitWait(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void clickAndType(WebElement ele, String data) {
        try {
            fluentWait(ele);
            ele.clear();
            HighlightElement(ele);
            ele.click();
            ele.sendKeys(data);
        } catch (StaleElementReferenceException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * Clicks on the given element, clears it, highlights it, and types the provided data.
     * Retries in case of a StaleElementReferenceException.
     *
     * @param ele  The WebElement to interact with.
     * @param data The data to be entered into the element.
     * @throws RuntimeException if the action fails after retries.
     * @Author: Balaji N
     */
    public void ClickAndType(WebElement ele, String data, String fieldname) {
        int retryCount = 3; // Number of retries in case of StaleElementReferenceException
        for (int attempt = 1; attempt <= retryCount; attempt++) {
            try {
                // Wait for the element to be clickable
                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(ele));
                ele.clear();
                HighlightElement(ele);
                ele.click();
                ele.sendKeys(data);
                return; // Successfully executed, exit the loop
            } catch (StaleElementReferenceException e) {

                if (attempt == retryCount) {
                    printError(ele, fieldname, "StaleElementReference exception error while performing click and type: " + e.getMessage(), e);
                    //   throw new RuntimeException("StaleElementReferenceException after " + retryCount + " retries: " + e.getMessage(), e);
                }
            } catch (NoSuchElementException e) {
                printError(ele, fieldname, "NoSuchElementException exception error while performing click and type: " + e.getMessage(), e);
                //  throw new RuntimeException("Element not found: " + ele.toString(), e);
            } catch (ElementNotInteractableException e) {
                printError(ele, fieldname, "ElementNotInteractable exception error while performing click and type: " + e.getMessage(), e);
                // printError("Element is not interactable: " + ele.toString(), e);
                throw new RuntimeException("Element is not interactable: " + ele.toString(), e);
            } catch (TimeoutException e) {
                printError(ele, fieldname, "Timeout exception error while performing click and type: " + e.getMessage(), e);
                // throw new RuntimeException("Timeout waiting for element to be clickable: " + ele.toString(), e);
            } catch (WebDriverException e) {
                printError(ele, fieldname, "Webdriver exception error while performing click and type: " + e.getMessage(), e);
                //  throw new RuntimeException("WebDriver exception while performing click and type: " + ele.toString(), e);
            } catch (Exception e) {
                printError(ele, fieldname, "Unexpected error while performing click and type: " + e.getMessage(), e);
                //  throw new RuntimeException("Unexpected error while performing click and type: " + ele.toString(), e);
            }
        }
    }

    public String getElementAttribute(WebElement ele, String fieldName) {
        String text = "";
        if (ele == null) {
            return "Error: WebElement is null for " + (fieldName != null ? fieldName : "Unknown field");
        }

        if (fieldName == null || fieldName.isEmpty()) {
            return "Error: fieldName is null or empty";
        }

        try {
            HighlightElement(ele);
            text = ele.getAttribute("value");
           /* if (text == null || text.isEmpty()) {
                return "Error: Attribute value is null or empty for " + fieldName;
            }*/
            return text;
        } catch (TimeoutException e) {
            printError(ele, fieldName, "TimeoutException", e);
        } catch (NoSuchElementException e) {
            printError(ele, fieldName, "NoSuchElementException", e);
        } catch (StaleElementReferenceException e) {
            printError(ele, fieldName, "StaleElementReferenceException", e);
        } catch (Exception e) {
            printError(ele, fieldName, "UnexpectedException", e);
        }
        return "Error: Unable to retrieve text from " + fieldName + " : field value : " + text;
    }


    public static void Web_Clear_and_Type(WebElement element, String text) {
        try {
            // Clear the existing text
            element.clear();

            // Type the new text
            element.sendKeys(text);
        } catch (Exception e) {
            System.err.println("Failed to clear and type into the input box: " + e.getMessage());
        }
    }

    public static void clickElement(WebElement ele) {
        ele.click();
    }

    /**
     * This function will highlight the element displayed with scroll action
     *
     * @param ele Element to be interact
     * @Author Balaji N
     */
    public static void HighlightElement(WebElement ele) {
        fluentWait(ele);

        // Scroll to the element (if not already visible)
        ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].scrollIntoView(true);", ele);

        // Flash effect (if app supports JavaScript changes)
        try {
            ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].style.backgroundColor = 'yellow';", ele);
            Thread.sleep(500);
            ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].style.backgroundColor = '';", ele);
        } catch (Exception e) {
            System.out.println("Highlight effect not supported for native mobile elements.");
        }
    }

    public void dropDown(WebElement ele, String value, String usingmethod) {
        Select select = new Select(ele);

        try {
            //	HighlightElement(ele);
            switch (usingmethod) {
                case "index":
                    select.selectByIndex(Integer.parseInt(value));
                    break;
                case "value":
                    select.selectByValue(value);
                    break;
                case "VisibleText":
                    jsClick(ele);
                    select.selectByVisibleText(value);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    public void jsScrollClick(WebElement ele) {
        try {
            fluentWait(ele);
            HighlightElement(ele);
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].click();", ele);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void jsClick(WebElement ele) {
        try {
            HighlightElement(ele);
            fluentWait(ele);
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].click();", ele);
        } catch (Exception e) {
            Assert.fail("Unable to click the element using js click method");
            throw new RuntimeException();
        }
    }

    public static void delayWithGivenTime(int i) {
        try {
            Thread.sleep(i);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static String CurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedCurrentDate = currentDate.format(formatter);
        return (formattedCurrentDate);
    }

    public static void click(WebElement ele) {
        try {
            HighlightElement(ele);
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            ele.click();
        } catch (Exception e) {
        }
    }

    public void jsDatePicker(WebElement ele, String dateval) {
        try {
            HighlightElement(ele);
            JavascriptExecutor JS = (JavascriptExecutor) getDriver();
            JS.executeScript("arguments[0].setAttribute('value','" + dateval + "');", ele);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Perform a mouse hover action on a given element with enhanced exception handling.
     *
     * @param ele The WebElement to hover over.
     * @throws IllegalArgumentException        If the provided element is null.
     * @throws ElementNotInteractableException If the element is not interactable.
     * @throws NoSuchElementException          If the element is not found in the DOM.
     * @Author Balaji N
     */
    public void MouseHover(WebElement ele) {
        try {
            // Validate the WebElement is not null
            if (ele == null) {
                throw new IllegalArgumentException("The provided WebElement is null.");
            }

            // Validate the WebElement is attached to the DOM and visible
            if (!ele.isDisplayed()) {
                throw new ElementNotInteractableException("The provided WebElement is not visible on the DOM.");
            }

            // Synchronize with the WebElement to avoid stale or unready element issues
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(ele));

            // Highlight the element for debugging purposes
            HighlightElement(ele);

            // Perform the mouse hover action
            Actions action = new Actions(getDriver());
            action.moveToElement(ele).perform();

            // Optional delay (can be removed for performance optimization)
            delayWithGivenTime(500);
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        } catch (StaleElementReferenceException e) {
            System.err.println(e);

        } catch (ElementNotInteractableException e) {
            System.err.println(e);

        } catch (NoSuchElementException e) {
            System.err.println(e);

        } catch (TimeoutException e) {
            System.err.println(e);

        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("An unexpected error occurred during mouse hover.", e); // Wrap and re-throw
        }
    }

    /**
     * Perform a mouse hover action on a given element with enhanced exception handling.
     *
     * @param ele       The WebElement to hover over.
     * @param fieldname The field name associated with the element.
     * @throws IllegalArgumentException        If the provided element is null.
     * @throws ElementNotInteractableException If the element is not interactable.
     * @throws NoSuchElementException          If the element is not found in the DOM.
     * @Author Balaji N
     */
    public void Mouse_Hover(WebElement ele, String fieldname) {
        try {
            // Validate the WebElement is not null
            if (ele == null) {
                throw new IllegalArgumentException("The provided WebElement is null.");
            }

            // Validate the WebElement is attached to the DOM and visible
            if (!ele.isDisplayed()) {
                throw new ElementNotInteractableException("The provided WebElement is not visible on the DOM.");
            }

            // Synchronize with the WebElement to avoid stale or unready element issues
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(ele));

            // Highlight the element for debugging purposes
            HighlightElement(ele);

            // Perform the mouse hover action
            Actions action = new Actions(getDriver());
            action.moveToElement(ele).perform();

            // Optional delay (can be removed for performance optimization)
            delayWithGivenTime(500);

        } catch (IllegalArgumentException e) {
            printError(ele, fieldname, "IllegalArgumentException", e);
        } catch (StaleElementReferenceException e) {
            printError(ele, fieldname, "StaleElementReferenceException", e);
        } catch (ElementNotInteractableException e) {
            printError(ele, fieldname, "ElementNotInteractableException", e);
        } catch (NoSuchElementException e) {
            printError(ele, fieldname, "NoSuchElementException", e);
        } catch (TimeoutException e) {
            printError(ele, fieldname, "TimeoutException", e);
        } catch (Exception e) {
            printError(ele, fieldname, "An unexpected error occurred during mouse hover: ", e);
        }
    }

    public void ThreadWait(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollAction(WebElement ele) {
        try {
            if (ele == null) {
                throw new IllegalArgumentException("Scroll Action at WebElement cannot be null");
            }
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].scrollIntoView(true);", ele);
        } catch (StaleElementReferenceException e) {
            System.err.println("Element became stale. Retrying scroll...");
            scrollAction(ele); // Retry once if element is stale
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Element not found on the page: " + ele, e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to scroll to element: " + ele, e);
        }
    }



    public static void setDriver(AndroidDriver appiumDriver) {
        driver = appiumDriver;
    }

    /**
     * Gets the current AppiumDriver instance.
     *
     * @return The AppiumDriver instance.
     */
    public static AndroidDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver has not been initialized. Call setDriver() first.");
        }
        return driver;
    }

    public static WebDriver getChromeDriver() {
        if (chromeDriver.get() == null) {
            throw new IllegalStateException("Driver has not been initialized. Call launchApplication() first.");
        }
        return chromeDriver.get();
    }

    /**
     * Get the URL based on the environment
     *
     * @return It returns the URL based on the environment setup
     * @author Balaji N
     */
    public String getAppURL() {
        switch (prop.getProperty("env")) {
            case "dev":
                return "https://hanadevpos3-dev1.azurewebsites.net/Account/Login";
            case "qa-final":
                return "https://hanadevpos3-qa-final.azurewebsites.net/";
            case "staging":
                return "https://hanafloralpos3-staging.azurewebsites.net/";
            case "live":
                return "https://hanafloralpos3.com/Account/Login";
            default:
                throw new IllegalStateException("Unexpected value: " + prop.getProperty("appURL"));
        }
    }

    public void launchApplication(String browserName) {
        String downloadPath = System.getProperty("user.dir");
        try {
            System.out.println("Launching Browser: " + browserName);

            if (browserName.equalsIgnoreCase("Chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();

                // Debugging
                System.out.println("Chrome setup complete!");

                LoggingPreferences logPrefs = new LoggingPreferences();
                logPrefs.enable(LogType.BROWSER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logPrefs);

                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", downloadPath);
                options.setExperimentalOption("prefs", chromePrefs);
                options.addArguments("--incognito");
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

                // Create ChromeDriver instance and set to ThreadLocal
                ChromeDriver driverInstance = new ChromeDriver(options);
                chromeDriver.set(driverInstance);
                System.out.println("ChromeDriver Initialized: " + chromeDriver.get());

            } else if (browserName.equalsIgnoreCase("FireFox")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.merge(capabilities);
                capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                chromeDriver.set(new FirefoxDriver(options));

            } else if (browserName.equalsIgnoreCase("EDGE")) {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                options.merge(capabilities);
                options.addArguments("--remote-allow-origins=*");
                capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                chromeDriver.set(new EdgeDriver(options));
            }

            // Ensure driver is initialized before proceeding
            if (getDriver() == null) {
                throw new IllegalStateException("WebDriver initialization failed.");
            }

            getDriver().manage().window().maximize();
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            getDriver().get(getAppURL());

            System.out.println("Browser Launched Successfully!");

        } catch (Exception e) {
            System.err.println("Error launching browser: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to launch browser due to: " + e.getMessage());
        }
    }

    public void loadConfig() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
            prop.load(ip);
            // initializeLogDirectory();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void startServer() {
        if (service == null) {
            AppiumServiceBuilder builder = new AppiumServiceBuilder()
                    .withIPAddress("127.0.0.1")  // Localhost
                    .usingPort(4723)             // Default Appium port
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)  // Override existing sessions
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "info") // Set log level
                    .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe")) // Path to Node.js
                    .withAppiumJS(new File(getProperty("user.home")
                            + "\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")); // Path to Appium

            // Initialize the Appium service
            service = AppiumDriverLocalService.buildService(builder);
        }

        // Start the server
        if (!service.isRunning()) {
            service.start();
            System.out.println("Appium Server started!");
        }
    }

    /**
     * Stops the Appium server.
     */
    public static void stopServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            System.out.println("Appium Server stopped!");
        }
    }

    public static void teardown() {
        if (getDriver() != null) {
            getDriver().quit();
        }
        stopServer();
        //closeEmulator();

    }

    public void launchEmulator() {
        try {
            // Replace with the name of your Android Emulator AVD
            String avdName = getProperty("avdName");

            // Start the emulator via the command line
            String command = "emulator -avd " + avdName;

            // Execute the command to launch the emulator
            Process process = Runtime.getRuntime().exec(command);

            // Wait for a few seconds to give the emulator time to start

            Thread.sleep(2000); // Adjust as needed
            waitForEmulatorToBoot();
            System.out.println("Emulator launched successfully.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Function to close the emulator
    public static void closeEmulator() {
        try {
            // Get the emulator ID dynamically from adb devices
            String emulatorId = getEmulatorId();
            if (emulatorId == null || emulatorId.isEmpty()) {
                System.out.println("No emulator is currently running.");
                return;
            }

            // Form the command to close the emulator
            String command = "adb -s " + emulatorId + " emu kill";
            System.out.println("Executing command: " + command);

            // Execute the command
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor(); // Wait for the process to complete

            System.out.println("Emulator closed successfully.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Helper function to get the emulator ID
    private static String getEmulatorId() {
        try {
            // Run the adb devices command to list devices
            Process process = Runtime.getRuntime().exec("adb devices");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("emulator-")) {
                    // Return the first emulator ID found
                    return line.split("\\s+")[0];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // No emulator found
    }


    private static void waitForEmulatorToBoot() {
        try {
            // Wait for emulator to be ready (checking if it is responding to ADB commands)
            boolean isEmulatorReady = false;
            while (!isEmulatorReady) {
                // Use adb to check if the emulator is responding
                String adbCommand = "adb shell getprop sys.boot_completed";
                Process process = Runtime.getRuntime().exec(adbCommand);
                process.waitFor();

                // Check if the emulator has booted successfully
                String output = new String(process.getInputStream().readAllBytes()).trim();
                if ("1".equals(output)) {
                    isEmulatorReady = true;  // Emulator is ready
                } else {
                    System.out.println("Waiting for emulator to finish booting...");
                    Thread.sleep(2000);  // Wait for a short time before retrying
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to launch the emulator
    public static void startEmulator() {
        try {
            String avdName = getProperty("avdName");
            System.out.println("Starting emulator: " + avdName);


            // Command to launch the emulator
            String command = "emulator -avd " + avdName;

            // Execute the command
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Monitor the output to ensure it's starting
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("boot completed")) {
                    System.out.println("Emulator started successfully.");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to start the emulator. Ensure AVD name is correct.");
        }
    }

    // Method to start the Appium server
    public static void startAppiumServer() {
        try {
            System.out.println("Starting Appium server...");

            // Command to start the Appium server
            String command = "appium";

            // Execute the command
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Monitor the output to ensure it's running
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("Appium REST http interface listener started")) {
                    System.out.println("Appium server started successfully.");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to start the Appium server. Ensure Appium is installed correctly.");
        }
    }

    public void openNotifications() {
        // Open notification panel
        //driver.openNotifications();

        try {
            // Wait for notifications to load (add explicit wait if necessary)
            Thread.sleep(2000);

            // Find and interact with the notification (Example: dismiss button)
            WebElement allowButton = driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
            allowButton.click();
        } catch (Exception e) {
            System.out.println("No notifications found or could not dismiss.");
        }
    }

//    public void pressBack() {
//        // Press BACK button to close notification shade if needed
//        driver.pressKey(new KeyEvent(AndroidKey.BACK));
//    }

    /**
     * This method is used to click Enter
     *
     * @Description: This method is used to click the Enter key
     * @Author: Sakrateesh R
     */
    public void pressEnter() {
        // Press BACK button to close notification shade if needed
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    /**
     * This method is used to click and type in the textbox
     *
     * @param ele
     * @param text
     * @Description: This method is used to click the textbox and enter the text
     * @Author: Sakrateesh R
     */
    public void click_and_type(WebElement ele, String text) {
        ele.click();
        ele.sendKeys(text);
    }

    /**
     * Logs and throws a runtime exception when an error occurs.
     *
     * @param locator       The WebElement where the error occurred.
     * @param fieldName     The name of the field related to the error.
     * @param exceptionType The type of exception encountered.
     * @param e             The original exception that was caught.
     * @throws RuntimeException with detailed error information.
     */
    public void printError(WebElement locator, String fieldName, String exceptionType, Exception e) {
        String errorMsg = String.format(
                "Error on field: '%s' | Locator: %s | Exception: %s | Message: %s",
                fieldName, locator.toString(), exceptionType, e.getMessage()
        );
        System.err.println(errorMsg);
        throw new RuntimeException(errorMsg, e);
    }

    /**
     * Verifies whether the specified element is displayed on the web page.
     *
     * @param element   The WebElement to verify.
     * @param fieldName The name of the field for logging purposes.
     * @return {@code true} if the element is displayed, otherwise {@code false}.
     * @Author Balaji N
     */
    public boolean is_Element_Displayed(WebElement element, String fieldName) {
        try {
            HighlightElement(element);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            printError(element, fieldName, "NoSuchElementException", e);
        } catch (StaleElementReferenceException e) {
            printError(element, fieldName, "StaleElementReferenceException", e);
        } catch (ElementNotInteractableException e) {
            printError(element, fieldName, "ElementNotInteractableException", e);
        } catch (TimeoutException e) {
            printError(element, fieldName, "TimeoutException", e);
        } catch (WebDriverException e) {
            printError(element, fieldName, "WebDriverException", e);
        } catch (Exception e) {
            printError(element, fieldName, "UnexpectedException", e);
        }
        return false;
    }

    public void switchToWindowbyIndex(int i) {
        try {
            Set<String> windowIds = getDriver().getWindowHandles();
            List<String> windowIdslist = new ArrayList<>(windowIds);
            String childWindowId = windowIdslist.get(i);
            getDriver().switchTo().window(childWindowId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error on switch_to_window_by_index " + e);

        }
    }

}

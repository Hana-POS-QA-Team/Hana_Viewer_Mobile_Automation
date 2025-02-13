package ProjectBase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.System.getProperty;

public class TestBaseClass {
    public static AndroidDriver driver;
    private static final String CHROMEDRIVER_PATH = getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\chromedriver.exe";
    private static AppiumDriverLocalService service;
    //    private static ChromeDriver chromeDriver;
    public static Properties prop;
    public DesiredCapabilities capabilities = new DesiredCapabilities();

    public void SuiteBeforeMethods() {
        startServer();
        System.out.println(CHROMEDRIVER_PATH);
        launchEmulator();
//        try {
//            Thread.sleep(5000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        launchAndroidApp();
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

    public void explicitWait(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(ele));
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



    public void ThreadWait(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    public void allowNotification() {
        // Open notification panel
        //driver.allowNotification();

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

    public void pressTab() {
        // Press BACK button to close notification shade if needed
        driver.pressKey(new KeyEvent(AndroidKey.TAB));
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
        delayWithGivenTime(1000);
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

    public void Click_Allow_Button_On_Location_Popup(){


        try {
            // Wait for notifications to load (add explicit wait if necessary)
            Thread.sleep(2000);

            // Find and interact with the notification (Example: dismiss button)
            WebElement allowButton = driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"));
            allowButton.click();
        } catch (Exception e) {
            System.out.println("No notifications found or could not dismiss.");
        }

    }

}

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



import java.util.List;

public class FacebookAutomation
{

    static String email = "";
    static String password= "";


    public static void main(String[] args)
    {
        /*get email and pasword from command line*/

        if(args.length !=2)
        {
            usage();
            return;
        }
        email = args[0];
        password=args[1];




        WebDriver driver = getWebDriver();
        driver.get("https://facebook.com");

        signin(driver, email, password);

        int number= getNotificationNumber(driver);
        FacebookAutomation.notifyBySms(String.valueOf(number));


    }

    private static void usage()
    {
        String m="you must specify email and password\n" +
                "java FacebookAutomation email password";
        System.out.println(m);

    }

    private static int getNotificationNumber(WebDriver driver)
    {
        /*click notification button => to list all new notification, that means to get 'li' list items */
        WebElement anch= driver.findElement(By.xpath("//a[@name='notifications']"));
        anch.click();

        /*  get all li element and count */
        List<WebElement> li = driver.findElements(By.xpath("//*/div/div/ul/li[1]/div/ul/li"));
        System.out.println(li.size());

        return li.size();
    }

    private static void signin(WebDriver driver, String email, String password)
    {

        /*  fill email and password to sign in ( this can also done with fewer line ) ( only for readability) */
        WebElement emailInut = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        emailInut.sendKeys(email);
        WebElement passwordInout = driver.findElement(By.xpath("//*[@id=\"pass\"]"));
        passwordInout.sendKeys(password);


        /*   the id of login input is dynamically changed so value is used instead of id */
        WebElement login = driver.findElement(By.xpath("//*[@value=\"Log In\"]"));
        login.click();
    }

    private static WebDriver getWebDriver()
    {

        /* disable chrome notification
           b/c after sign to facebook, chrome display notification, this stop the script execution
           so we must disable notification
         */


        /*  Create a instance of ChromeOptions class*/
        ChromeOptions options = new ChromeOptions();

        /*   Add chrome switch to disable notification - "--disable-notifications" */
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        return new ChromeDriver(options);
    }



    public static void notifyBySms(String message)
    {

        Sms sms = new Sms();
        sms.SendSms(message);

    }

}

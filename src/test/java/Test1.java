import java.util.*;
import java.lang.*;
import java.math.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Test1 {


    public static void main (String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\jlzer\\Downloads\\Programming Tools\\Automation\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://tasks.evalartapp.com/automatization/");

        //LoginPage
        WebElement element_username = driver.findElement(By.name("username"));
        element_username.sendKeys("619901");
        WebElement element_password = driver.findElement(By.name("password"));
        element_password.sendKeys("10df2f32286b7120My01LTEwOTkxNg==30e0c83e6c29f1c3");
        WebElement element_loginButton = driver.findElement(By.xpath("//html//body//div//div[2]//form//button"));
        element_loginButton.click();


        //Get table to sum
        WebDriverWait wait1= new WebDriverWait(driver,10);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //GetRows
        System.out.print("BeforeRows" + "\n\n");
        WebElement sumTable = driver.findElement(By.id("table_wrapper"));
        List<WebElement> num_rows = driver.findElements(By.xpath("//*[@id=\"table\"]/tbody/tr"));
        System.out.print("NumRows" + num_rows.size() + "\n\n");
        int resultPage1 = splitRowsPerPage(num_rows);


        //SendResult
        WebElement element_totalSum = driver.findElement(By.xpath("//html//body//div[2]//div[2]//form//input"));
        String sReultado = Integer.toString(resultPage1);
        element_totalSum.sendKeys(sReultado);
        WebElement element_resultButton = driver.findElement(By.xpath("//html//body//div[2]//div[2]//form//button"));
        element_resultButton.click();


        //wait


        //driver.quit();
    }
    public static int splitRowsPerPage(List<WebElement> split_row){
        int rowResult = 0, partialResult = 0, partialPageResult = 0;
        for (WebElement row :split_row)
        {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            String item = null, subItem = null, amount = null, amount2 = null;
            for (WebElement col : cols) {
                item = cols.get(0).getText();
                subItem = cols.get(1).getText();
                amount = cols.get(2).getText();
                amount2 = cols.get(3).getText();
                rowResult = Integer.parseInt(amount)+ Integer.parseInt(amount2);
            }
            partialResult = partialResult+rowResult;
            System.out.print(item + "/" + subItem + "/" + amount + "/" + amount2 + "/" + rowResult);
            System.out.println("// partialSumIs =" + partialResult);

        }
        partialPageResult = partialPageResult + partialResult;
        System.out.println("partial Page Sum Result Is =" + partialPageResult);

        return partialPageResult;
    }

}

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;





public class scrapowanie {



    private static WebDriver driver;


    public static void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
        driver = new ChromeDriver();


    }



    public static void ScrapowanieTemperatury(){
        driver.navigate().to("https://www.wunderground.com/history/monthly/us/mi/detroit/KDET/date/2023-2");
        List<WebElement> temperatureElements = driver.findElements(By.xpath("//*[@id='inner-content']/div[2]/div[1]/div[5]/div[1]/div/lib-city-history-observation/div/div[2]/table/tbody/tr/td[2]/table/tr/td[2]"));

        String csv_file_path = "src/main/temperatures.csv";

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(csv_file_path,true));

            // Write the temperatures to the CSV file
            int i = 1;
            while (i < temperatureElements.size()) {
                String temperature = temperatureElements.get(i).getText();
                writer.write(temperature);
                writer.newLine();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }




    public static void afterTest() {
        driver.close();
        driver.quit();
    }
}




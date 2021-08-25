import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

public class SelenideAdvancedTest {
    @Test
    public void advanced() throws FileNotFoundException {
        // System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        // Configuration.browser = "chrome";
        Configuration.reportsFolder = "\\src\\main\\resources\\Reports";
        Configuration.savePageSource = false;
        open("https://demoqa.com/books ");
        SoftAssert softAssert = new SoftAssert();
//        Find all books with publisher 'O'Reilly Media' containing title 'Javascript'
        ElementsCollection list = $("div.rt-tbody")
                .findAll(By.xpath("//span[contains (@id,\"JavaScript\")]/../../../div[4][text()=\"O'Reilly Media\"]/../.."));
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Book " + (i + 1) + " >> " + list.get(i).text());
            System.out.println("----");
        }
        System.out.println();

//        Check that size of returned list equals to 10
        softAssert.assertEquals(list.size(), 10, "array size is not 10");

//      Check that first match row's title equals to 'Learning JavaScript Design Patterns'
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                softAssert.assertEquals(list.get(i).find("span").getText(), "Learning JavaScript Design Patterns");
                System.out.println("First row title is: " + list.get(i).find("span").getText());
            }
        }
        System.out.println("-----------------------------------");

//        Using stream() click on all matching row's title
        list.stream().forEach(y -> {
            y.find(".mr-2 > a").click();
            System.out.println(WebDriverRunner.url());
            back();
        });

        softAssert.assertAll();
    }

}






















//        ElementsCollection list = $("div.rt-tbody").findAll("span[id*='JavaScript'] > div");
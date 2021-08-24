import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

public class SelenideAdvancedTest {
    @Test
    public void advanced() throws InterruptedException {
        open("https://demoqa.com/books ");
        SoftAssert softAssert = new SoftAssert();
        ArrayList<WebElement> javaScriptArray = new ArrayList<>();
//        Find all books with publisher 'O'Reilly Media' containing title 'Javascript'
        ElementsCollection divList = $("div.rt-tbody").findAll("div[class='rt-tr-group']");
        for (WebElement el : divList) {
            WebElement item = $(el.findElement(By.cssSelector("div > div:nth-child(4)")));
            if (item.getAttribute("innerText").equals("O'Reilly Media")) {
                WebElement result = $(el.findElement(By.cssSelector("div > div:nth-child(2)")));
                if (result.getText().contains("JavaScript")) {
                    javaScriptArray.add(result);
                    System.out.println(result.getText());
                }
            }
        }
//        Check that size of returned list equals to 10
        softAssert.assertEquals(javaScriptArray.size(), 10, "array size is not 10");
//      Check that first match row's title equals to 'Learning JavaScript Design Patterns'
        for (int i = 0; i < javaScriptArray.size(); i++) {
            if (i == 0) {
                softAssert.assertEquals(javaScriptArray.get(i).getText(), "Learning JavaScript Design Patterns");
                System.out.println("First row title is: " + javaScriptArray.get(i).getText());
            }

        }


        //     $$("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]").filter(Condition.exist("O'Reilly Media")).filter()
//         $$("div.rt-tbody").filter("//div[2]/div/div[4]".contains("O'Reilly Media"))

        softAssert.assertAll();
    }

}

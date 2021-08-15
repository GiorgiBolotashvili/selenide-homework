import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static com.codeborne.selenide.Selenide.*;

import java.util.ArrayList;

public class SelenideBasics2Test {
    @Test
    public void softAssert() throws InterruptedException {
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
        softAssert.assertEquals(javaScriptArray.size(),10, "array size is not 10");
//        Find that each books images are not empty
        $("div.rt-tbody").findAll("div > div > div > img").stream().forEach(y -> {
            softAssert.assertFalse(y.getAttribute("src").isEmpty());
        });

        softAssert.assertAll();
    }
}





/*
    ElementsCollection bookImages = $("div.rt-tbody").findAll("div > div > div > img");
            System.out.println(bookImages.size());
        for (WebElement item : bookImages) {
                System.out.println(item.getAttribute("src").isEmpty());
                }*/

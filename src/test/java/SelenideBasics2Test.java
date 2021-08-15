import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Selenide.*;

import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.*;


public class SelenideBasics2Test {
    @Test
    public void softAssert() throws InterruptedException {
/*        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);
        */
        open("https://demoqa.com/books ");
        ElementsCollection divList = $("div.rt-tbody").findAll("div[class='rt-tr-group']");
        for (WebElement el : divList) {
            WebElement item = $(el.findElement(By.cssSelector("div > div:nth-child(4)")));
            if (item.getAttribute("innerText").equals("O'Reilly Media")) {
                WebElement result = $(el.findElement(By.cssSelector("div > div:nth-child(2)")));
                if (result.getText().contains("JavaScript")) {
                    System.out.println(result.getText());
                }
            }
        }

        System.out.println(divList.size());

//        $$("div.rt-tr-group").stream().forEach(x -> {System.out.println(x.getText());});

//        System.out.println( $("div.rt-tbody").getText());
        ElementsCollection list = $("div.rt-tbody").findAll("span#see-book-Speaking JavaScript");

        System.out.println(list.size());

        $("div.rt-tbody").findAll("div > * > span[id*='JavaScript']").stream().forEach(x -> {
            System.out.println(x.getText());
        });

        //                    WebElement result = $(el.findElement(By.cssSelector("div > div:nth-child(2) > div > span[id*='JavaScript'] > a")));

//        $("div.rt-tbody").findAll("div").stream().forEach(x -> {System.out.println(x.getText());});
//
//                stream().forEach(el -> { el.$$("//div[text()=\"O'Reilly Media\"]"); });
//        Thread.sleep(90000);
//       $("#checkboxes > input:nth-child(1)").click();
  /*       ElementsCollection boxes = $$("#checkboxes > input");
        System.out.println("The number of elements is " + boxes.size());
        for (WebElement b: boxes) {
            Assert.assertTrue(b.isSelected());
        }*/
    }
}

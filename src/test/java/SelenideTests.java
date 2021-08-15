import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTests {

    @Test
    public void checkbox(){
        open("http://the-internet.herokuapp.com/checkboxes ");
        $("#checkboxes > input:nth-child(1)").click();
        ElementsCollection boxes = $$("#checkboxes > input");
        System.out.println("The number of elements is " + boxes.size());
        for (WebElement b: boxes) {
            System.out.println(b.getAttribute("type"));
            Assert.assertTrue(b.isSelected());
        }
    }

    @Test
    public  void dropdown(){
        open("http://the-internet.herokuapp.com/dropdown");
        WebElement dropDown  = $("#dropdown");
        Select drop = new Select(dropDown);

        Assert.assertEquals(drop.getFirstSelectedOption().getText(),"Please select an option");
        drop.selectByIndex(2);
        Assert.assertEquals(drop.getFirstSelectedOption().getText(),"Option 2");
    }

    @Test
    public  void textBox() throws InterruptedException {
        open("https://demoqa.com/text-box");
        $("#userName").sendKeys("Giorgi Bolotashvili");
        $("#userEmail").sendKeys("gio_bolota@yahoo.com");
        $("#currentAddress").sendKeys("Georgia, Tbilisi");
        $("#permanentAddress").sendKeys("Georgia, Gori");
        $("#submit").click();

        ElementsCollection elements =  $$(".border > p");
        for ( WebElement e : elements) {
            System.out.println(e.getText());
        }
        ElementsCollection newElements =  $$(".border > p").
                shouldHave(exactTexts( "Name:Giorgi Bolotashvili",
                                       "Email:gio_bolota@yahoo.com",
                                       "Current Address :Georgia, Tbilisi",
                                       "Permananet Address :Georgia, Gori"));
        System.out.println("Print new array: ----------------");
        for ( WebElement e : newElements) {
            System.out.println(e.getText());
        }
    }
}

package functionalVerifficationAndActions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.sql.Driver;

public class WebActions
{

//    This function clicks on the specified Web Element
    public static void userclick(WebElement we){
//        User clicks on element
            we.click();

    }

    //    This function enters values in Web Element
    public static void userSendKeys(WebElement we,String data){
//        User clicks on element
        we.sendKeys(data);

    }

    //    This function clears values in Web Element
    public static void userClear(WebElement we){
//        User clicks on element
        we.clear();
    }
    //    This function clears values in Web Element
    public static void userSelects(WebElement we, String data){
//        User selects value on element
        Select drpSize = new Select(we);
        drpSize.selectByVisibleText(data);
    }
}

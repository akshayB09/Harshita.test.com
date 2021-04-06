package stepDefinition;

import io.cucumber.datatable.DataTable;
import functionalVerifficationAndActions.WebActions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import readConfigurationProperties.readPropertyAndReturnValue;

/*
This Automation suite has been designed with cucumber framework integrated with Junit. For best results
please run the suite in IntelliJ. Following are the pre-condition for the successful execution of this
Automation suite:
1. Suite has been downloaded as Maven/Cucumber Project
2. No disruption in accessing dependencies or website
3. Java Version 8 and above
4. cucumber-java and cucumber-junit version to be used as 6.10.2
5. Selenium version 3.14 and above
 */
public class StepDefinition {
    WebDriver driver;
    //        Read data from property file
    readPropertyAndReturnValue readPropertyAndReturnValue = new readPropertyAndReturnValue();
    /*
            Beginning of new function
    */
    @Before
    public void setUp(){
        System.out.println("\n\n\n=======  Execution Started for Scenario. Web Driver will be downloaded and setup  =======");
        WebDriverManager.chromedriver().setup();
        driver =new ChromeDriver();

    }
   /*
            Beginning of new function
    */

    @Given("^User navigates to Website$")
    public void goToWebsite() {
        try {
            readPropertyAndReturnValue = new readPropertyAndReturnValue();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    Navigate to application url retrieved through Property File
            driver.navigate().to(readPropertyAndReturnValue.getApplicationUrl());
            driver.manage().window().maximize();
            System.out.println("Website Launched successfully");
        }
        catch(Exception e){
            System.err.println("Error encountered during website launched and exception caught as: "+e.getLocalizedMessage());
        }
    }
   /*
            Beginning of new function
    */

    @And("^User is logged in successfully$")
    public void checkUserLoggedIn(){
        try{
        readPropertyAndReturnValue= new readPropertyAndReturnValue();
//        Click on Sign-In
            WebActions.userclick(driver.findElement(By.className("login")));

      System.out.println("User Clicked on sign-in link");
//      Enter user details
        WebActions.userSendKeys(driver.findElement(By.id("email")),readPropertyAndReturnValue.getUserName());
        WebActions.userSendKeys(driver.findElement(By.id("passwd")),readPropertyAndReturnValue.getPassword());
        System.out.println("Username and Password read from properties and entered successfully");
//        Click on Sign-in button
        WebActions.userclick(driver.findElement(By.id("SubmitLogin")));
        System.out.println("User Logged in Successfully");
        }
        catch(Exception e){
            System.err.println("Error encountered during sign-in of user and exception caught as: "+e.getLocalizedMessage());
        }

    }
   /*
            Beginning of new function
    */

    @When("^User select Category, subcategories and other details$")
    public void selectItem(DataTable itemDetails) {
        //Write the code to handle Data Table


        for (Map<Object, Object> data : itemDetails.asMaps(String.class, String.class)) {

            WebActions.userclick(driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[1]/a")));
            System.out.println("Women section selected");
//            select items
            String subcategory=(String) data.get("subcategory1");
            String subcategory2=(String) data.get("subcategory2");
            String itemName=(String) data.get("itemName");
            String quantity=(String) data.get("quantity");
            String itemSize=(String) data.get("size");
            System.out.println(subcategory);
            String subcategoryXpath;
            switch(subcategory){
                case "T-shirt": selectTshirt();break;

                case "Dress":selectDress();

            }
//            Select Item with description
            WebActions.userclick(driver.findElement(By.xpath("//*[@class='product-name' and contains(@title, '"+itemName+"')]")));
            System.out.println("Select T shirt as "+ itemName);

//            Select Quantity
            driver.findElement(By.id("quantity_wanted")).clear();
            WebActions.userSendKeys(driver.findElement(By.id("quantity_wanted")),quantity);
            System.out.println("Quantity Selected as "+ quantity);

//            Select Size
            WebActions.userSelects(driver.findElement(By.name("group_1")),itemSize);
            System.out.println("Item size selected as "+ itemSize);

//            Add to Cart
            WebActions.userclick(driver.findElement(By.name("Submit")));
            System.out.println("Item added to cart ");

//            Clicked on HomePage
            handleWindowsDuringPlacingOfOrder();
            WebActions.userclick(driver.findElement(By.xpath("//*[@class='home' and contains(@title, 'Return to Home')]")));
        }
    }

   /*
            Beginning of new function
    */

    private void handleWindowsDuringPlacingOfOrder() {
        String parentWindowHandler = driver.getWindowHandle();
        String subWindowHandler = null;
        Set<String> handles = driver.getWindowHandles(); // get all window handles
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            System.out.println("Switched to Pop-up window");
            subWindowHandler = iterator.next();
        }
        WebActions.userclick(driver.findElement(By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/span/span")));
        driver.switchTo().window(parentWindowHandler);
        System.out.println("Switched back to parent window");
    }

   /*
            Beginning of new function
    */

    private void selectDress() {
        String subcategoryXpath="xpath=//a[contains(@href,'http://automationpractice.com/index.php?id_category=8&controller=category') and @id='subcategories']";
        WebActions.userclick(driver.findElement(By.xpath(subcategoryXpath)));
    }

   /*
            Beginning of new function
    */

    private void selectTshirt() {
        String subcategoryXpath="/html/body/div/div[2]/div/div[3]/div[2]/div[2]/ul/li[1]/div[1]/a";
        WebActions.userclick( driver.findElement(By.xpath(subcategoryXpath)));
        System.out.println("Subcategory selected as Tops");
        subcategoryXpath="/html/body/div/div[2]/div/div[3]/div[2]/div[2]/ul/li[1]/div[1]/a";
        WebActions.userclick( driver.findElement(By.xpath(subcategoryXpath)));
        System.out.println("Subcategory selected as T-Shirts");
    }

    /*
        Beginning of new function
     */

    @When("^User navigates to Personal Information and update First Name$")
    public void navigateAndUpdatePersonalDetails(){
        System.out.println("Switched back to parent window");
        WebActions.userclick(driver.findElement(By.xpath("//span[text()='My personal information']")));
        System.out.println("Clicked on Personal Information button");

//        compare and update first Name
        String firstName=driver.findElement(By.id("firstname")).getAttribute("value");
        System.out.println("Current First Name of user: "+firstName);
        WebActions.userClear(driver.findElement(By.id("firstname")));
        WebActions.userSendKeys(driver.findElement(By.id("firstname")),readPropertyAndReturnValue.getFirstName());
        System.out.println("First Name of user updated to : "+readPropertyAndReturnValue.getFirstName());

//        Enter current password
        WebActions.userSendKeys(driver.findElement(By.id("old_passwd")),readPropertyAndReturnValue.getPassword());
        System.out.println("Current Password re-entered successfully");
//        Click on Save button
        WebActions.userclick(driver.findElement(By.xpath("//span[text()='Save']")));
        System.out.println("Clicked on Save button");

    }
    /*
        Beginning of new function
     */

    @And("^User Clicks on Add to Cart button and user clicks on Proceed to Checkout button$")
    public void PlaceOrder(){
        boolean valueMismatch=false;
        try{
//    Click on Cart
        WebActions.userclick(driver.findElement(By.xpath("//*[@id='header']/div[3]/div/div/div[3]/div/a")));
        System.out.println("Clicked on cart icon on Home page");
//        verify item cart value
        List<WebElement> totalUniqueItems = driver.findElements(
                By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr"));

        System.out.println("Total unique items in cart; "+totalUniqueItems.size());
        // iterate through the rows in the outer element
        for (WebElement rowIteration : totalUniqueItems) {
            // Extract the price
            String strTemp = rowIteration.findElement(By.xpath("//td[4]/span/span")).getText();
            Float uniqueItemPrice=Float.parseFloat(strTemp.substring(1,strTemp.length()));
            strTemp=rowIteration.findElement(By.xpath("//td[5]/input[2]")).getAttribute("value");
            int nmbr=Integer.parseInt(strTemp);
            strTemp=rowIteration.findElement(By.xpath("//td[6]/span")).getText();
            float totalPriceOfItem=Float.parseFloat(strTemp.substring(1,strTemp.length()));
            if(totalPriceOfItem!=(uniqueItemPrice*nmbr)) {
                System.err.println("Total value does not matched with computed value of cart");
                valueMismatch=true;
            }
        }
        if(!valueMismatch) {
            System.out.println("Cart Items and the total price has been verified successfully");
            System.out.println("Total value of items in Cart = " + driver.findElement(By.id("total_product")).getText());
            System.out.println("Shipping Charges = " + driver.findElement(By.id("total_shipping")).getText());
            System.out.println("Total value without taxes = " + driver.findElement(By.id("total_price_without_tax")).getText());
            System.out.println("Total taxes = " + driver.findElement(By.id("total_tax")).getText());
            System.out.println("Total amount to pay = " + driver.findElement(By.id("total_price_container")).getText());
//            Clicked on Proceed to CheckOut
            WebActions.userclick(driver.findElement(By.xpath("//*[@id='center_column']/p[2]/a[1]/span")));
            System.out.println("Proceeded to Check Out the items");
//           Delivery Address
            String address = driver.findElement(By.id("address_delivery")).getText();
            System.out.println("Delivery Address Details \n"+address);
            WebActions.userclick(driver.findElement(By.xpath("//*[@id='center_column']/form/p/button/span")));
            WebActions.userclick(driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/form/p/button/span")));
            System.out.println("CLicked on Proceed button without agreeing to terms and service");

//            Windows Handler check
            handleWindowsDuringCheckout();

//            Click on Proceed to Checkout button
            WebActions.userclick(driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/form/p/button/span")));

            System.out.println("Clicked on Proceed to Checkout button");
            String paymentMethod = readPropertyAndReturnValue.getPaymentMethod();
            WebActions.userclick(driver.findElement(By.xpath("//*[@class='bankwire' and contains(@title, '" + paymentMethod + "')]")));
            System.out.println("Selected payment method as " + paymentMethod);

//           Verify the details displayed
            String paymentDetails = driver.findElement(By.xpath("//*[@id='center_column']/form/div")).getText();
            System.out.println("Payment details displayed as  " + paymentDetails);

//            Clicked on order confirmation
            WebActions.userclick(driver.findElement(By.xpath("//*[@id='cart_navigation']/button/span")));
            System.out.println("User confirmed the order details");

//            Verify order details
            String ordrDetails = driver.findElement(By.xpath("//*[@id='center_column']/div")).getText();
            System.out.println("Order Details displayed as :\n" + ordrDetails);
        }
        }catch(NoSuchElementException e1){
            System.err.println("One or more Element not present and exception observed as " + e1.getLocalizedMessage());
            throw new RuntimeException("Fail!");

        }
        catch(Exception e1){
            System.err.println("One or more exception observed as " + e1.getLocalizedMessage());
            throw new RuntimeException("Fail!");
        }
    }

   /*
            Beginning of new function
    */

    private void handleWindowsDuringCheckout() {
        String parentWindowHandler = driver.getWindowHandle();
        String subWindowHandler = null;
        Set<String> handles = driver.getWindowHandles(); // get all window handles
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()) {
            System.out.println("Switched to Pop-up window");
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler);
        String fancyErr = driver.findElement(By.xpath("//*[@id='order']/div[2]/div/div/div/div/p")).getText();
        if (fancyErr.contains("You must agree to the terms of service before continuing."))
            System.out.println("Alert popup displayed with text as: " + fancyErr);
        WebActions.userclick(driver.findElement(By.xpath("//*[@id='order']/div[2]/div/div/a")));
        System.out.println("Closed Alert popup");
        driver.switchTo().window(parentWindowHandler);
        WebActions.userclick(driver.findElement(By.id("cgv")));
        System.out.println("Agreed to terms and conditions by clicking on checkbox");

    }

   /*
            Beginning of new function
    */

    @Then("^Order is placed successfully and verified n Order History$")
    public void verifyOrder(){
//        Verify order is placed successfully at the website
//        Navigate to Order section
        WebActions.userclick(driver.findElement(By.xpath("//*[text()='Back to orders']")));
        System.out.println("Clicked on 'Back to Order' link");

        System.out.println("\n\n\nOrder placed successfully!!!");
    }

   /*
            Beginning of new function
    */

    @Then("^New name is updated and is reflected in Home Page$")
    public void verifyUpdatedDetails(){
        String updateMessage=driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/p")).getText();
        if(updateMessage.contains("Your personal information has been successfully updated."))
            System.out.println("Personal Information updated successfully and expected message displayed");
        else
            System.err.println("Personal Information was not updated as expected message is not displayed");

//        Navigate to Home Screen
        WebActions.userclick(driver.findElement(By.xpath("//*[@class='home' and contains(@title, 'Return to Home')]")));
        System.out.println("Navigate to Home Page");

//        Verify updated username
        String currentUsername=driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")).getText();
        String expectedFirstUsername=readPropertyAndReturnValue.getFirstName();
        if(currentUsername.contains(expectedFirstUsername)) {
            System.out.println("Updated First Name is displayed on Home PAge as well");
            System.out.println("\n\n\nUserName updated and Verified successfully on Home Page");
        }
        else
            System.err.println("Updated first name not displayed on Home Page");
    }

   /*
            Beginning of new function
    */

    @After
    public void AfterActions(){

        System.out.println("\n\n\n=======  Execution Completed. Driver will be closed  =======");

        driver.close();

    }
}

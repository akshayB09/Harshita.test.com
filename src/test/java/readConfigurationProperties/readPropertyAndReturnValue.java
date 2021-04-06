package readConfigurationProperties;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

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

public class readPropertyAndReturnValue {
    private Properties properties;
    private final String propertyFilePath= "propertiesFiles\\configurationAndUserDetails.properties";

/*
This function reads the configuration file
 */
    public readPropertyAndReturnValue(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();

                properties.load(reader);
                reader.close();


        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Configuration.properties not found at " + propertyFilePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

/*
    This function reads the username and return it

     */
    public String getUserName() {
        String username = properties.getProperty("username");
        if(username != null) return username;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }
    /*
        This function reads the password and return it

         */
    public String getPassword() {
        String password = properties.getProperty("password");
        if(password != null) return password;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if(url != null) return url;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public String getFirstName() {
        String newFirstName = properties.getProperty("newFirstName");
        if(newFirstName != null) return newFirstName;
        else throw new RuntimeException("New First Name is not specified in the Configuration.properties file.");
    }

    public String getPaymentMethod() {
        String newFirstName = properties.getProperty("paymentMethod");
        if(newFirstName != null) return newFirstName;
        else throw new RuntimeException("Payment method is not specified in the Configuration.properties file.");
    }

}

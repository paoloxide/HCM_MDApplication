package hcm.pageobjects;

import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.awt.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;

import common.BasePage;
import common.BaseTest;
import common.ExcelUtilities;
import static common.BaseTest.TestCaseRow;
import static util.ReportLogger.log;

public class WorkforceStructureTasksPage extends BasePage {
	
	public WorkforceStructureTasksPage(WebDriver driver){
		super(driver);
	}
	
	@Override
    public String getPageId()
    {
        return "/hcmCoreSetup/faces/HcmWSIntWA";
    }
	
	public void clickTask(String menu){
		
		clickByXpath("//li/a[contains(text(),'"+ menu +"')]");		
		log("Clicking " + menu +"...");
		System.out.println("Clicking " + menu +"...");

	}
	
	public void clickCreate() throws InterruptedException{
		//driver.findElement(By.xpath("//a/span[text()=' Create']/../../../td/div/a")).click();;
		//driver.findElement(By.xpath("//td[text()='Create']")).click();;
		clickByXpath("//a/span[text()=' Create']/../../../td/div/a");  
		Thread.sleep(2000);
		clickByXpath("//td[text()='Create']");
		log("Clicking Create...");
		System.out.println("Clicking Create...");

	}
	
	public void clickNext(){
		
		clickByXpath("//a/span[text()='Ne']");	
		log("Clicking Next...");
		System.out.println("Clicking Next...");

	}
	
	public void clickOKButton(){
		
		clickByXpath("//button[text()='O']");	
		log("Clicking OK...");
		System.out.println("Clicking OK...");

	}
	
	public void clickYesButton(){
		
		clickByXpath("//button[text()='es']");	
		log("Clicking Yes...");
		System.out.println("Clicking Yes..."); 

	}
	
	public void clickSubmitButton(){
		
		clickByXpath("//a/span[text()='Submit']");	
		log("Clicking Submit...");
		System.out.println("Clicking Submit...");

	}
	
	public void clickSaveandCloseButton() throws InterruptedException{
		
		clickByXpath("//span[text()='Save']/../../../td/div/a");	
		Thread.sleep(3000);
		clickByXpath("//td[text()='ave and Close']");
		Thread.sleep(3000);
		log("Clicking Submit...");
		System.out.println("Clicking Submit...");

	}
	
	public void clickSearchButton(){
		
		clickByXpath("//button[text()='Search']");	
		log("Clicking Search...");
		System.out.println("Clicking Search...");

	}
	
	public void enterData(String dataLocator, String value) throws Exception{
		
		String inputBoxPath = "//td/label[text()='"+dataLocator+"']/../../td/input";
		clickByXpath(inputBoxPath);
		enterTextByXpath(inputBoxPath, "\b\b\b\b\b\b\b\b"+value);
		log("Entered "+dataLocator+"..");
		System.out.println("Entered "+dataLocator+"...");
	}
	
	public void enterDropdownData(String dataLocator, String value) throws Exception{
		
		String inputBoxPath = "//td/label[text()='"+dataLocator+"']/../../td/span/input";
		clickByXpath(inputBoxPath);
		enterTextByXpath(inputBoxPath, value);
		log("Entered "+dataLocator+"..");
		System.out.println("Entered "+dataLocator+"...");
		
	}
	
	public void enterDropdownLocation(String dataLocator, String value) throws Exception{
		
		String inputBoxPath = "//td/label[text()='"+dataLocator+"']/../../td/span/span/input";
		clickByXpath(inputBoxPath);
		enterTextByXpath(inputBoxPath, value);
		log("Entered "+dataLocator+"..");
		System.out.println("Entered "+dataLocator+"...");
		
	}
	
	public void selectFromDropdown(String dataLocator, String value) throws Exception{
		
		String inputBoxPath = "//td/label[text()='"+dataLocator+"']/../../td/select";
		clickByXpath(inputBoxPath);
		clickByXpath(inputBoxPath+"/option['"+value+"']");
		log("Selected "+dataLocator+"..");
		System.out.println("Selected "+dataLocator+"...");
		
	}
	
	public void enterSearchData(String dataLocator, String value) throws Exception{
		
		String inputBoxPath = "//td/label[text()='"+dataLocator+"']/../../td/table/tbody/tr/td/table/tbody/tr/td/span/input";
		clickByXpath(inputBoxPath);
		enterTextByXpath(inputBoxPath, "\b\b\b\b\b\b\b\b"+value);
		log("Entered "+dataLocator+"..");
		System.out.println("Entered "+dataLocator+"...");
	}
	
	public void waitForElementToBeClickable(int waitTime, String elementPath) throws Exception{

		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		try{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementPath)));
		}catch(TimeoutException e){
			System.out.println("Waiting for element has timed out... No alternative method available.");
		}
		System.out.println("Check element presence is now finised...");
	}
	
	public void retryingFindClick(By by) throws Exception{

        int attempts = 0;
        boolean scrollDown = true;
        
        while(attempts < 11) {
            try {
            	System.out.println("Validating element.....retries:"+attempts);
                driver.findElement(by).click();
                System.out.println("Element has been refreshed.....");
                return ;
            } catch(StaleElementReferenceException e) {
            
            } catch(NoSuchElementException e){
            
            } catch(ElementNotVisibleException e){
            
            }
            attempts++;
        }
        
        System.out.println("Throwing Error.....");
        //throw new StaleElementReferenceException("The Element cannot be found...");
	}
	
	public String retryingSearchInput(String dataLocator) throws Exception{

        int attempts = 0;
        String[] inputTypesArray = {
        		"//td/label[text()='"+dataLocator+"']/../../td/input",
        		"//td/label[text()='"+dataLocator+"']/../../td/span/input",
        		"//td/label[text()='"+dataLocator+"']/../../td/span/span/input",
        		"//td/label[text()='"+dataLocator+"']/../../td/select",
        		"//td/label[text()='"+dataLocator+"']/../../td/table/tbody/tr/td/table/tbody/tr/td/span/input"
        };
        
        while(attempts < inputTypesArray.length) {
            try {
	            	System.out.println("Validating element.....retries:"+attempts);
	                driver.findElement(By.xpath(inputTypesArray[attempts])).click();
	                System.out.println("Input tab has been found.....");
	                return inputTypesArray[attempts];
	                
	            } catch(StaleElementReferenceException e) {
	            
	            } catch(NoSuchElementException e1){
	            
	            } catch(ElementNotVisibleException e2){
	            
	            } catch(Exception e3){
	            	
	            }
            attempts++;
        }
        return null;
	}	
	
}
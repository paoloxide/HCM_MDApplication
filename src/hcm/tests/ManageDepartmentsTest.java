package hcm.tests;

import static util.ReportLogger.log;
import static util.ReportLogger.logFailure;

import java.util.ArrayList;
import java.util.List;
import static common.ExcelUtilities.getCellType;

import org.openqa.selenium.By;
import org.openqa.selenium.internal.FindsByXPath;
import org.testng.annotations.Test;

import common.BaseTest;
import hcm.pageobjects.FuseWelcomePage;
import hcm.pageobjects.LoginPage;
import hcm.pageobjects.TaskListManagerTopPage;
import hcm.pageobjects.WorkforceStructureTasksPage;

import static util.ReportLogger.log;
import static util.ReportLogger.logFailure;

public class ManageDepartmentsTest extends BaseTest{
	@Test
	public void a_test() throws Exception  {
		testReportFormat();
	
	try{
		createDepartment();
	  
	  	}
	
        catch (AssertionError ae)
        {
            //takeScreenshot();
            logFailure(ae.getMessage());

            throw ae;
        }
        catch (Exception e)
        {
            //takeScreenshot();
            logFailure(e.getMessage());

            throw e;
        }
    }

	public void createDepartment() throws Exception{
			
		LoginPage login = new LoginPage(driver);
		//takeScreenshot();
		login.enterUserID(5);
		login.enterPassword(6);
		login.clickSignInButton();
		
		FuseWelcomePage welcome = new FuseWelcomePage(driver);
		//takeScreenshot();
		welcome.clickNavigator("More...");
		clickNavigationLink("Workforce Structures");
			
		WorkforceStructureTasksPage task = new WorkforceStructureTasksPage(driver);
		//takeScreenshot();
		
		task.waitForElementToBeClickable(10, "//li/a[text()='Manage Departments']");
		task.clickTask("Manage Departments");
		Thread.sleep(5000);
		//takeScreenshot();
		
		task.clickCreate();
		Thread.sleep(5000);
		//task.clickCreate();
		//Thread.sleep(1000);
		//task.waitForElementToBeClickable(10, "//td/label[text()='Effective Start Date']/../../td/input");

		//Enable task
		int inputLabel = 7;
		int inputs = 8;
		int rowNum = 8;
		String type, inputValue;
		
		//Fill in Entries.....
		System.out.println("New value: "+getExcelData(rowNum, inputs)); System.out.println("Input type: "+getExcelData(9, inputs, "date"));
		System.out.println("New value: 1/1/2000");
		
		System.out.println("New value: "+getExcelData(rowNum, 18)); System.out.println("Input type: "+getExcelData(9, 18, "time"));
		System.out.println("New value: 09:00");
		
		//Test Case
		while(inputs < 22){
			System.out.println(inputs+": "+getExcelData(rowNum, inputs));
			inputs += 1;
		}
		inputs = 8;
		/*for(int colNum = 8;getExcelData(inputLabel, colNum).length()>0; colNum++){
				String dataLocator = getExcelData(inputLabel, colNum, "text");
				System.out.println("Filtering text....."+dataLocator);
				dataLocator = dataLocator.replaceAll("\\*", "");
				System.out.println("New value....."+dataLocator);
				
				if(getCellType(inputLabel, colNum) == 1){
							type = "text";

					}else if(dataLocator.contains("Date")){
							type = "date";
						}else if(dataLocator.contains("Time")){
							type = "time";
						}else{
							type = "text";
						}
					
				inputValue = getExcelData(inputs, colNum, type);
				System.out.println("New value....."+inputValue);
				
				//Proceed on Iterating all the inputs....
				String dataPath =  task.retryingSearchInput(dataLocator);
				driver.findElement(By.xpath(dataPath)).sendKeys(inputValue);
				//Triggering Next Button.....
				if(dataLocator.contentEquals("Context Value")){
					Thread.sleep(500); task.clickNext();
					Thread.sleep(500); task.clickNext();
					Thread.sleep(3000);
					task.waitForElementToBeClickable(10, "//td/label[text()='Reporting Name']/../../td/input");
				}
		}*/
		
		task.enterData("Effective Start Date", getExcelData(rowNum, inputs)); inputs++;
		task.enterDropdownData("Department Set", getExcelData(rowNum, inputs)); inputs++;
		task.enterData("Name", getExcelData(rowNum, inputs)); inputs++;
		task.enterData("Internal Address Line", getExcelData(rowNum, inputs)); inputs++;
		task.selectFromDropdown("Status", getExcelData(rowNum, inputs)); inputs++;
		task.enterDropdownData("Action Reason", getExcelData(rowNum, inputs)); inputs++;
		task.enterDropdownLocation("Location", getExcelData(rowNum, inputs)); inputs++;
		
		task.clickNext();
		Thread.sleep(1000);
		task.clickNext();
		Thread.sleep(3000);
		//task.waitForElementToBeClickable(10, "//td/label[text()='Reporting Name']/../../td/input");
		//Skipped Context Values...
		task.enterData("Reporting Name", getExcelData(rowNum, inputs));inputs+=2;
		task.enterDropdownData("Manager", getExcelData(rowNum, inputs)); inputs++;

		task.enterData("Work Start Time", getExcelData(rowNum, inputs)); inputs++;
		task.enterData("Work End Time", getExcelData(rowNum, inputs)); inputs++;
		task.enterData("Standard Working Hours", getExcelData(rowNum, inputs)); inputs++; 
		task.enterDropdownData("Standard Working Hours Frequency", getExcelData(rowNum, inputs)); inputs++;
		
		
		task.waitForElementToBeClickable(10, "//a/span[text()='Submit']");
		//takeScreenshot();
		task.clickSubmitButton();
		//takeScreenshot();
		task.waitForElementToBeClickable(10, "//button[text()='es']");
		//Saving the work.....
		task.clickYesButton(); //button[text()='O']
		//takeScreenshot();
		task.waitForElementToBeClickable(10, "//button[text()='O']");
		//takeScreenshot();
		task.clickOKButton();
		//takeScreenshot();
		Thread.sleep(5000);
		
		//Thread.sleep(3000);
		//takeScreenshot();
		//task.clickSaveandCloseButton();
		//Thread.sleep(15000);
		//takeScreenshot();
		

		//Verifying if the department has been added.....
	//	task.clickTask("Manage Departments");
	//	Thread.sleep(25000);
	//	task.enterSearchData("Name", getExcelData(inputs, 10));
	//	task.clickSearchButton();
	//	Thread.sleep(20000);
		
		//Ending message::
		//		Thread.sleep(1000);//1000
				//takeScreenshot();
				
				//task.clickSaveAndCloseButton();
				
			//	System.out.println("Department Creation Completed\n***************\n");
			//	log("Validation Completed");
				
		//		Thread.sleep(1500);//3000
				//takeScreenshot();
	}
}

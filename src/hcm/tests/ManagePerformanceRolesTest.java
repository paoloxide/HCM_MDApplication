package hcm.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.Test;

import common.BaseTest;
import hcm.pageobjects.FuseWelcomePage;
import hcm.pageobjects.LoginPage;
import hcm.pageobjects.TaskListManagerTopPage;
import static util.ReportLogger.logFailure;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class ManagePerformanceRolesTest extends BaseTest {
	
	@Test
	public void a_test() throws Exception  {
		testReportFormat();
	
	try{
		managePerf();
	  
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

	public void managePerf() throws Exception{
			
		LoginPage login = new LoginPage(driver);
		//takeScreenshot();
		login.enterUserID(5);
		login.enterPassword(6);
		login.clickSignInButton();
		
		FuseWelcomePage welcome = new FuseWelcomePage(driver);
		//takeScreenshot();
		welcome.clickNavigator("More...");
		clickNavigationLink("Setup and Maintenance");
		
		TaskListManagerTopPage task = new TaskListManagerTopPage(driver);
		//takeScreenshot();
		
		task.clickTask("Manage Implementation Projects");
		Thread.sleep(3000);
		//takeScreenshot();
		
		/*startTableRep("Validation of the Current Screen");
		assertTextInElementPresent("//td[2]/div/h1", "Manage Implementation Projects");
		assertPageElementPresent("//span/div/div[2]", "Search");
		assertPageElementPresent("//span/div[2]/div[2]", "Search Results");
		endTableRep();*/
				
		//task.enterSearch(7);
		//takeScreenshot();
		
		//task.clickSearchButton();
		//takeScreenshot();
		
		//clickNavigationLink(7);
		//Thread.sleep(3000);
		//takeScreenshot();
		
		/*startTableRep("Validation of the Current Screen");
		assertTextInElementPresent("//td[2]/div/h1", "Implementation Project: "+getExcelData(7));
		assertTextInElementPresent("//div[2]/table/tbody/tr/td[2]/div/table/tbody/tr/td/div", 8);
		assertTextInElementPresent("//tr[2]/td[2]/div/table/tbody/tr/td", 9);
		assertTextInElementPresent("//tr[3]/td[2]/div/table/tbody/tr/td/div", 10);
		endTableRep();*/
		
		//task.clickExpandWorkForceDevelopment();
		//Thread.sleep(3000);
		//takeScreenshot();
		
		/*startTableRep("Validation of the Tasks");
		assertTextInElementPresent("//table[2]/tbody/tr/td[2]/div/table/tbody/tr/td/div", 11);
		assertTextInElementPresent("//table[2]/tbody/tr[2]/td[2]/div/table/tbody/tr/td/div", 12);
		assertTextInElementPresent("//table[2]/tbody/tr[3]/td[2]/div/table/tbody/tr/td/div", 13);
		assertTextInElementPresent("//table[2]/tbody/tr[4]/td[2]/div/table/tbody/tr/td/div", 14);
		assertTextInElementPresent("//table[2]/tbody/tr[5]/td[2]/div/table/tbody/tr/td/div", 15);
		endTableRep();*/
		
		
		//task.clickDoneButton();
		//takeScreenshot();
		
		//task.clickDoneButton();
		//takeScreenshot();
		
		//task.enterSearchName(16);
		//takeScreenshot();
		
		//task.clickSearchButton();
		//takeScreenshot();
		
		//task.clickGoToTaskIcon();
		//Thread.sleep(5000);
		//takeScreenshot();
		
		//startTableRep("Validate the Current Screen");
		//assertTextInElementPresent("//td[2]/div/h1", 17);
		//endTableRep();
		
		task.clickCreateIcon();
		//Thread.sleep(3000);
		//takeScreenshot();
		
		/*startTableRep("Validate the Current Screen");
		assertTextInElementPresent("//td[2]/div/h1", 18);
		endTableRep();*/
		
		//task.enterRole(19);
		//task.enterDescription(20);
		//task.enterFromDate(todaysDate());
		//task.enterToDate(tomorrowsDate());
		//task.selectStatus(21);
		//task.clickSaveAndCloseButton();
		
		//takeScreenshot();
		//waitForElement("//*[@id='d1::msgDlg']", "xpath");
		
		//startTableRep("Validate the changes are saved");
		//assertTextInElementPresent("//*[@id='d1::msgDlg::_cnt']/div/table/tbody/tr/td/table/tbody/tr/td[2]/div", "Your changes were saved.");
		//endTableRep();
	
		//task.clickOkButton();
		
		task.waitForElementToBeClickable(15,1,1,"//button[text()='Ne']");
		task.clickNextButton();
		Thread.sleep(250);
		task.clickNextButton();
		
		int expectedTaskValue = 7;
		int subTaskExpectedValue = 9;
		String mainTaskCommonPath, subTaskCommonPath, refID;
		String expandableSubTaskPath = null;
		String thirdLevelSubTaskPath = null;
		ArrayList<String> currSubTaskAttr = new ArrayList<String>();
		ArrayList<String> prevSubTaskAttr = new ArrayList<String>();
		ArrayList<String> subTaskL3Attr   = new ArrayList<String>();
		boolean isScrollingDown = true;
		String mainRef = "afrrk";
		String subRef = "afrap";
		
		//Initialize values of the ArrayList
		currSubTaskAttr.add("0"); currSubTaskAttr.add("0");
		prevSubTaskAttr.add("0"); prevSubTaskAttr.add("0");
		subTaskL3Attr.add("0");   subTaskL3Attr.add("0");
		
		for(int rowNum = 2; getExcelData(rowNum, expectedTaskValue).length() > 0; rowNum++){

			//START OF MAIN TASK
			task.waitForElementToBeInvisible("//div[text()='Fetching Data...']", "Fetching Data...", 10);
			refID = task.findMainTaskUniqueID(rowNum, expectedTaskValue);
			mainTaskCommonPath = "//tr[@_"+mainRef+"='"+refID+"']/td/div/table/tbody/tr/td/div[text()='"+getExcelData(rowNum, expectedTaskValue)+"']";
			task.retryingFindClick(By.xpath(mainTaskCommonPath));
			
			System.out.println("Main ref is now..."+mainTaskCommonPath );
			
			
			while(!is_element_visible(mainTaskCommonPath, "xpath")){
				isScrollingDown = task.scrollDownToElement(isScrollingDown, expectedTaskValue);
			}
			
			if(is_element_visible(mainTaskCommonPath,"xpath")){
				/*try{
					task.clickElementByXPath(mainTaskCommonPath);
				}catch(Exception e){
					task.scrollElementIntoView(mainTaskCommonPath);
				}*/
				task.retryingFindClick(By.xpath(mainTaskCommonPath));
			}
			
			if(is_element_visible(mainTaskCommonPath+"/span/a[contains(@title,'Expand')]","xpath")){
					
				task.retryingFindClick(By.xpath(mainTaskCommonPath));
				task.clickMainTaskCheckbox(rowNum, expectedTaskValue, mainTaskCommonPath);
					
				task.clickExpandFolder(mainTaskCommonPath);
				Thread.sleep(1000);
				
			}else {

				System.out.println("Visible Element is Not Expandable: "+mainTaskCommonPath );

				task.retryingFindClick(By.xpath(mainTaskCommonPath));
				Thread.sleep(150);
				task.clickMainTaskCheckbox(rowNum, expectedTaskValue, mainTaskCommonPath);
				/*try{
					task.clickMainTaskCheckbox(rowNum, expectedTaskValue, mainTaskCommonPath);
				}catch(Exception e){
					
					if(!is_element_visible(mainTaskCommonPath,"xpath")){
						isScrollingDown = task.scrollDownToElement(isScrollingDown, expectedTaskValue);
					}
					task.clickElementByXPath(mainTaskCommonPath);
					Thread.sleep(150);
					task.clickMainTaskCheckbox(rowNum, expectedTaskValue, mainTaskCommonPath);
				}*/
			}
			
			Thread.sleep(250);
			//END OF MAIN TASK
			
			//START OF SUB TASK
				task.waitForElementToBeInvisible("//div[text()='Fetching Data...']", "Fetching Data...", 10);
				refID = task.findMainTaskUniqueID(rowNum, expectedTaskValue);
				
				while (getExcelData(rowNum, subTaskExpectedValue).length() > 0){
					
					subTaskCommonPath  = "//tr[@_"+subRef+"='"+refID+"' or contains(@_"+subRef+",'"+refID+"_')]/td/div/table/tbody/tr/td/div[text() = '"+getExcelData(rowNum, subTaskExpectedValue)+"']";

					System.out.println("\n**********\nNext Task: "+getExcelData(rowNum, subTaskExpectedValue));
					System.out.println("Sub ref is now..."+subTaskCommonPath );

					//Adjust view to make element visible in the DOM...
					try{
						while(!is_element_visible(subTaskCommonPath, "xpath")){
							System.out.println("Element Path is not yet visible: "+subTaskCommonPath );
							isScrollingDown = task.scrollDownToElement(isScrollingDown, subTaskExpectedValue);
							Thread.sleep(500);
						}
						task.scrollElementIntoView(subTaskCommonPath);
					}catch(StaleElementReferenceException e){
						//Skips staleElement....
					}catch(NoSuchElementException e1){
						isScrollingDown = task.scrollDownToElement(isScrollingDown, subTaskExpectedValue);
					}catch(Exception e2){
						//Skips visibility checks
					}finally{
						task.retryingFindClick(By.xpath(subTaskCommonPath));
					}
					
					//Reworking All Elements before processing.....
					try{
						
						task.retryingFindClick(By.xpath(subTaskCommonPath));
						//setting values after element refresh...
						currSubTaskAttr.set(0, (String)task.findByXpath(subTaskCommonPath+"/../../../../../../..").getAttribute("_afrrk"));
						currSubTaskAttr.set(1, (String)task.findByXpath(subTaskCommonPath+"/../../../../../../..").getAttribute("_afrap"));
						if((prevSubTaskAttr.get(1)+"_"+prevSubTaskAttr.get(0)).equals(currSubTaskAttr.get(0))){
							System.out.println("Third Level folder found.. adjustment in progress...");
							thirdLevelSubTaskPath = expandableSubTaskPath;
							subTaskL3Attr.set(0,(String)task.findByXpath(thirdLevelSubTaskPath).getAttribute("_afrrk"));
							subTaskL3Attr.set(1,(String)task.findByXpath(thirdLevelSubTaskPath).getAttribute("_afrap"));
							expandableSubTaskPath = null;
						}
						
						//Realign SubTaskCommonPath
						System.out.println("Reworking subTask Path...");
						subTaskCommonPath ="//tr[@_afrrk = '"+currSubTaskAttr.get(0)+"' and @_afrap = '"+currSubTaskAttr.get(1)+"']/td/div/table/tbody/tr/td/div[text() = '"+getExcelData(rowNum, subTaskExpectedValue)+"']";
						System.out.println("New subTask Path... "+subTaskCommonPath);
					}catch(Exception e){
						//Nothing to do here..
					}
					
					//Refresh Element reference in DOM after detecting it..
					task.retryingFindClick(By.xpath(subTaskCommonPath));
					
					if(is_element_visible(subTaskCommonPath, "xpath")){
						System.out.println("Visibility of Element path CONFIRMED...");
						/*try{
							task.clickElementByXPath(subTaskCommonPath);
						}catch(Exception e){
							task.scrollElementIntoView(subTaskCommonPath);
						}*/
						task.retryingFindClick(By.xpath(subTaskCommonPath));
						task.scrollElementIntoView(subTaskCommonPath);
					}
					
					if(is_element_visible(subTaskCommonPath+"/span/a[contains(@title,'Expand')]","xpath")){
						System.out.println("Visible Element is Expandable: "+subTaskCommonPath );
						if(expandableSubTaskPath != null && !currSubTaskAttr.get(1).contains(prevSubTaskAttr.get(0))){
							if(!is_element_visible(expandableSubTaskPath,"xpath")){
								isScrollingDown = task.scrollDownToElement(isScrollingDown, subTaskExpectedValue);
							}
							task.retryingFindClick(By.xpath(subTaskCommonPath));
							task.clickCollapseFolder(expandableSubTaskPath);
							Thread.sleep(2000);
							
							//Collapses expandable sub task that has a sub task...
							if(prevSubTaskAttr.get(1).equals( subTaskL3Attr.get(1)+"_"+subTaskL3Attr.get(0) )
									&& !currSubTaskAttr.get(1).contains( subTaskL3Attr.get(0) ) ){
								
								task.retryingFindClick(By.xpath(thirdLevelSubTaskPath));
								task.clickCollapseFolder(thirdLevelSubTaskPath);
								Thread.sleep(1000);
							}
							
						}
						
						expandableSubTaskPath = subTaskCommonPath;
						if(!is_element_visible(subTaskCommonPath, "xpath")){
							isScrollingDown = task.scrollDownToElement(isScrollingDown, subTaskExpectedValue);
							Thread.sleep(1000);
						}
						
						//Finally expands the target subtask folder...
						try{
							task.retryingFindClick(By.xpath(subTaskCommonPath));
							task.clickExpandFolder(subTaskCommonPath);
							Thread.sleep(2000);
						}catch(Exception e){
							isScrollingDown = task.scrollDownToElement(isScrollingDown, -1);
							
							task.retryingFindClick(By.xpath(subTaskCommonPath));
							task.clickExpandFolder(subTaskCommonPath);
							Thread.sleep(2000);	
						}
					}
					 
					//Scroll down to the subtaskExpectedValue;
					System.out.println("Ticking Target: "+getExcelData(rowNum, subTaskExpectedValue));
					task.clickElementByXPath(subTaskCommonPath);

					//Ticking the expected Tasks;
					System.out.println("ELEMENT FOUND: Proceed to ticking...");
						
						task.retryingFindClick(By.xpath(subTaskCommonPath));
						task.clickSubTaskCheckbox(rowNum, subTaskExpectedValue, subTaskCommonPath);
					
					//Save prevSubTaskAttr
					prevSubTaskAttr.set(0, currSubTaskAttr.get(0));
					prevSubTaskAttr.set(1, currSubTaskAttr.get(1));
					Thread.sleep(1000);
					subTaskExpectedValue += 2;
				}
			
			//Collapsing Collapsible SubTask Folders
			//Ensuring Subtask to collapse is fresh...
			if(expandableSubTaskPath != null) task.retryingFindClick(By.xpath(expandableSubTaskPath)); 
			if(is_element_visible(expandableSubTaskPath+"/span/a[contains(@title,'Collapse')]","xpath")){
				System.out.println("Visible Element is Collapsible: "+expandableSubTaskPath );
				task.clickCollapseFolder(expandableSubTaskPath);
				Thread.sleep(2000);
			}
			
			task.scrollDownToElement(false, -1);
			Thread.sleep(1000);
			
			//Collapsing MainTask Folder
			task.retryingFindClick(By.xpath(mainTaskCommonPath)); //Ensuring Main Task to collapse is fresh...
			if(is_element_visible(mainTaskCommonPath+"/span/a[contains(@title,'Collapse')]","xpath")){
				task.clickElementByXPath(mainTaskCommonPath);
				task.clickCollapseFolder(mainTaskCommonPath);
				Thread.sleep(3000);
				
			}
			
			System.out.println("Moving on to next row...");
			expandableSubTaskPath = null;
			thirdLevelSubTaskPath = null;
			subTaskExpectedValue = 9;
		}
		
		task.clickSaveandOpenProject();
		task.waitForElementToBeClickable(3600,1,1,"//button[text()='D']");
		task.clickDoneButton();
		
	} 	  
}
package hcm.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import common.BaseTest;
import hcm.pageobjects.FuseWelcomePage;
import hcm.pageobjects.LoginPage;
import hcm.pageobjects.TaskListManagerTopPage;
import static util.ReportLogger.log;
import static util.ReportLogger.logFailure;
import static common.ExcelUtilities.getCellData;

public class ExcelFileValidation extends BaseTest{
	
	@Test
	public void a_test() throws Exception  {
		testReportFormat();
	
	try{
		validateExcelFile();
	  
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

	public void validateExcelFile() throws Exception{
		
		boolean isScrollingDown = true;
		boolean isNowClickable = false;
		boolean hasSetMainTask = false;
			
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
		
		task.clickTask("Configure Offerings");
		Thread.sleep(3000);//3000
		//takeScreenshot();
			
		//Enable task
		
		String mainRef = "afrrk";
		String subRef = "afrap";
		String[][] mainTaskAttrHolder = {};
		String[][] subTaskAttrHolder = {};
		String[] taskStatusHolder = {};
		List<String> validEntries = new ArrayList<String>();
		//String[] validEntries = new String[255];
		int excelEntryCounter = -1;
		int oldMTCount = 1, oldSTCount = 1;
		boolean hasNextExcelEntry = true;
		//NOTE: Use afrrk to identify a mainTask, succeeding subfolder use as afrap;;
		while(!is_element_visible("//div[text()='Compensation Management']", "xpath")){}
		//subTaskAttrHolder = task.queryAllsubtaskFolder("159");
		mainTaskAttrHolder =  task.queryAllTaskMainFolder();
		
		//Collapse all MainTask folders.....
		while(oldMTCount != mainTaskAttrHolder.length){
			for(int i = 0; i<mainTaskAttrHolder.length; i++){ //Cutting the iteration short...
				String loopPath = "//div[text()='"+mainTaskAttrHolder[i][0]+"']";
				System.out.println(loopPath);
				task.scrollDownToElement(false, 0);
				if(loopPath.contentEquals("//div[text()='Coexistence for HCM']")){		
					Thread.sleep(2000);
				}
				task.waitForElementToBeInvisible("//div[text()='Fetching Data...']", "Fetching Data...", 10);
				task.scrollElementIntoView(loopPath);
				task.retryingFindClick(By.xpath(loopPath));
				if(is_element_visible(loopPath+"/span/a[contains(@title,'Collapse')]", "xpath")){
					task.clickCollapseFolder(loopPath);
					task.waitForElementToBeClickable(10, 0, 0, loopPath+"/span/a[contains(@title,'Expand')]");
				}
				task.retryingFindClick(By.xpath(loopPath));
			}
			
			task.waitForElementToBeInvisible("//div[text()='Fetching Data...']", "Fetching Data...", 10);
			oldMTCount = mainTaskAttrHolder.length;
			mainTaskAttrHolder = task.queryAllTaskMainFolder();
		}
		
		//Expands Main Tasks folder one by then extracts Subfolder details
		System.out.println("main Holder size:"+mainTaskAttrHolder.length);
		for(int i = 0; i<mainTaskAttrHolder.length; i++){	//changed i from 1 to 12.. for test...
			System.out.println("Now checking all entries...."+i);
			String loopPath = "//div[text()='"+mainTaskAttrHolder[i][0]+"']";
			//Expand main task folder....
			if(is_element_visible(loopPath+"/span/a[contains(@title,'Expand')]", "xpath")){
				task.clickExpandFolder(loopPath);
				task.waitForElementToBeClickable(10, 0, 0, loopPath+"/span/a[contains(@title,'Collapse')]");
				task.waitForElementToBeInvisible("//div[text()='Fetching Data...']", "Fetching Data...", 10);
			}
			//Query all visible subtask values....
			subTaskAttrHolder = task.queryAllsubtaskFolder(mainTaskAttrHolder[i][1]);
			
			//Expands All Expandable SubTasks..
			while(oldSTCount != subTaskAttrHolder.length){
				for(int j = 0; j<subTaskAttrHolder.length; j++){
					String refID = mainTaskAttrHolder[i][1];
					String afrrk_ = subTaskAttrHolder[j][1];
					String subTName = subTaskAttrHolder[j][0];
					String subloopPath = "//tr[@_"+subRef+"='"+refID+"' and @_afrrk='"+afrrk_+"']/td/div/table/tbody/tr/td/div[text() = '"+subTName+"']";
					System.out.println(subloopPath);
					if(is_element_visible(subloopPath+"/span/a[contains(@title,'Expand')]", "xpath")){
						task.clickExpandFolder(subloopPath);
						task.waitForElementToBeInvisible("//div[text()='Fetching Data...']", "Fetching Data...", 10);
					}
				}
				
				task.waitForElementToBeInvisible("//div[text()='Fetching Data...']", "Fetching Data...", 10);
				oldSTCount = subTaskAttrHolder.length;
				task.retryingFindClick(By.xpath("//div[text()='"+mainTaskAttrHolder[i][0]+"']"));
				subTaskAttrHolder = task.queryAllsubtaskFolder(mainTaskAttrHolder[i][1]);
			}
			//Collapse Main folder
			if(is_element_visible(loopPath+"/span/a[contains(@title,'Collapse')]", "xpath")){
				task.scrollElementIntoView(loopPath);
				task.retryingFindClick(By.xpath(loopPath));
				task.clickCollapseFolder(loopPath);
				task.waitForElementToBeClickable(10, 0, 0, loopPath+"/span/a[contains(@title,'Expand')]");
				task.waitForElementToBeInvisible("//div[text()='Fetching Data...']", "Fetching Data...", 10);
			}
			
			//Add Subtask to collections after query.....
			for(int k = 0 ;k<subTaskAttrHolder.length; k++){
				validEntries.add(subTaskAttrHolder[k][0]);
				//validEntries[k] = subTaskAttrHolder[k][0];
			}
			//Refresh STCount value.....
		
		}
		
		//Finally Add mainTask entries...
		for(int l=0;l<mainTaskAttrHolder.length;l++){
			validEntries.add(mainTaskAttrHolder[l][0]);
			//validEntries[l] = mainTaskAttrHolder[l][0];
		}
		
		//Find all possible status for each tasks
		String statpath = "//div[text()='Compensation Management']";
		if(is_element_visible(statpath+"/../../td/span/a", "xpath")){
			task.clickTaskStatus(statpath);
			task.waitForElementToBeClickable(10, 0, 0, "//div/span/input");
			taskStatusHolder = task.queryAlltaskstatusFolder();
			Thread.sleep(250);
		}
		
		//Finally Adding all status entries...
		for(int m=0;m<taskStatusHolder.length;m++){
			validEntries.add(taskStatusHolder[m]);
		}
		
		System.out.println("All valid entries: "+validEntries+"\nSize: "+validEntries.size());
		
		/*String[] validEntries = { 	
				"Coexistence for HCM", "Compensation Management", "Workforce Profiles", "Benefits", "Individual Compensation", "Workforce Compensation", "Total Compensation Statements", "Absence Management", "Human Resources Business Intelligence Analytics", "Absence and Accrual Business Intelligence Analytics", "Payroll Business Intelligence Analytics", "Workforce Effectiveness Business Intelligence Analytics", "Workforce Deployment Business Intelligence Analytics", "Customer Data Management", "Enterprise Contracts", "Financials", "Fusion Accounting Hub", "Grants Management", "Incentive Compensation", "Marketing", "Materials Management and Logistics", "Order Orchestration", "Procurement", "Product Management", "Project Execution Management", "Project Financial Management", "Risk and Controls", "Sales", "Supply Chain Financial Orchestration", "Supply Chain Managerial Accounting",
				"Procurement Contracts", "Sales Contracts",
				"Financials", "Supplier Invoice Processing", "Expenses", "Fixed Assets", "Customer Invoice Processing", "Collections", "Revenue Management", "Intercompany", "Budgetary Control and Encumbrance Accounting", "Financial Business Intelligence Analytics", "Profitability Business Intelligence Analytics", "Fixed Assets Business Intelligence Analytics", "General Ledger Business Intelligence Analytics", "Accounts Payable Business Intelligence Analytics", "Employee Expenses Business Intelligence Analytics", "Intrastat Reporting",
				"Fusion Accounting Hub", "Accounting Coexistence", "Intercompany",
				"Grants Management", "Project Integration Gateway", "Capital Projects", "Internal Project Billing", "Transaction Tax", "Project Performance Reporting",
				"Incentive Compensation",
				"Marketing", "E-mail Server for Marketing", "Lead Management", "Segmentation Server for Marketing", "Marketing Business Intelligence Analytics", "Leads Business Intelligence Analytics", "Marketing Planning Business Intelligence Analytics", "Opportunity Landscape Business Intelligence Analytics", "Sales Prediction Engine Business Intelligence Analytics", "Sales Catalog", "Data Import and Export",
				"Materials Management and Logistics", "Supply Chain and Order Management Business Intelligence Analytics", "Order Management Business Intelligence Analytics", "Logistics Business Intelligence Analytics", "Shipping", "Receiving", 
				"Order Orchestration", 
				"Procurement", "Self Service Procurement", "Supplier Portal", "Sourcing", "Supplier Qualification", "Procurement Contracts", "Supplier Invoice Processing", "Procurement and Spend Business Intelligence Analytics", "Sourcing Business Intelligence Analytics", "Procurement Business Intelligence Analytics",
				"Product Management", "Item and Catalog Management", "Inventory Organizations", "Catalogs", "Structures", "Suppliers for Product Management", "Item Mass Update", "Advanced Catalogs", "Data Governance", "New Item Requests", "Change Orders", "Product Rules", "Data Quality Rules for Products", "Audit Trail", "Data Consolidation", "Product Spoke Systems", "Item Batches", "Data Pool Integration", "Data Quality for Products", "Supplier Collaboration", "Supplier Portal", "Configurator", "Configurator Modeling Environment", "Product Development", "Change Orders", "Product Development Configuration", "Product Requirements and Ideation Management", "Concept Design Management", "Product Lifecycle Portfolio Management", "Product Management Business Intelligence Analytics",
				"Project Resource Management",
				"Project Financial Management", "Burdening", "Project Control", "Project Integration Gateway", "Project Costing", "Capital Projects", "Project Billing", "Internal Project Billing", "Transaction Tax", "Project Performance Reporting", "Project Business Intelligence Analytics", "Project Revenue and Billing Business Intelligence Analytics", "Project Performance Business Intelligence Analytics", "Project Control and Costing Business Intelligence Analytics", "Risk and Controls",
				"Sales", "Sales Prediction Engine", "Outlook Integration", "Quotas", "References", "Competitors", "Sales Forecasting", "Partners", "Partner Business Intelligence Analytics", "Partner Performance", "Partner Deals", "Partner Programs", "Territory Management", "Sales Business Intelligence Analytics", "Quota Management Business Intelligence Analytics", "Territory Management Business Intelligence Analytics", "Opportunity and Revenue Management Business Intelligence Analytics", "Customer Interactions Management", "Leads Business Intelligence Analytics", "Opportunity Landscape Business Intelligence Analytics", "Sales Prediction Engine Business Intelligence Analytics", "Sales Forecasting Management Business Intelligence Analytics", "Basic Catalogs", "Sales Cloud Integration", "Supply Chain Financial Orchestration",
				"Supply Chain Managerial Accounting", "Receipt Accounting", "Cost Accounting", "Cost and Profit Planning",
				"Workforce Deployment", "Payroll", "Absence Management", "Workforce Scheduling", "Workforce Predictions", "Individual Compensation", "Time and Labor", "Workforce Profiles", "Network at Work", "Workforce Management", "Human Resources Business Intelligence Analytics", "Absence and Accrual Business Intelligence Analytics", "Payroll Business Intelligence Analytics", "Workforce Effectiveness Business Intelligence Analytics", "Workforce Deployment Business Intelligence Analytics",
				"Workforce Development", "Worker Goal Setting", "Career Development", "Questionnaires", "Worker Performance", "Talent Review", "Succession Management", "Human Resources Business Intelligence Analytics", "Absence and Accrual Business Intelligence Analytics", "Payroll Business Intelligence Analytics", "Workforce Effectiveness Business Intelligence Analytics", "Workforce Deployment Business Intelligence Analytics",
				"Data Quality", "Data Quality Matching", "Data Quality Cleansing", "Customer Hub", "Customer Data Management Business Intelligence Analytics",
				"In Progress", "Implemented", "Not Started"
		};*/

		for(int rowNum =2; getExcelData(rowNum, 7).length() > 0; rowNum++ ){
				excelEntryloop:
				for(int colNum = 7; (hasNextExcelEntry) ; colNum++){
					
					String excelEntry = (String)getExcelData(rowNum, colNum);
					System.out.println("Entry No: \nFilled excelEntry...."+excelEntry);

					if(getExcelData(rowNum, colNum).length() <= 0 && getExcelData(rowNum, colNum+1).length() > 0 ){
						log(excelEntry+" is an invalid entry!!!\n;");
						System.out.println(excelEntry+" is an invalid entry!!!\n");
						throw new IllegalArgumentException();
					}else if(getExcelData(rowNum, colNum).length() <= 0){
						 break excelEntryloop;
					}

					arrayEntryloop:
					for(int arrayInt = 0; arrayInt < validEntries.size()+2; arrayInt++){
						//String arrayEntry = (String)validEntries[arrayInt];
						String arrayEntry = (String)validEntries.get(arrayInt);
						System.out.println("Filled arrayEntry...."+arrayEntry);
						System.out.println(arrayEntry+" vs. "+excelEntry);
						if(arrayEntry.equals(excelEntry) && !excelEntry.equals("")){
							System.out.println(excelEntry+" is found to be valid...");
							break arrayEntryloop;
						}

						System.out.println(arrayInt+" vs. "+validEntries.size());
						if((int)arrayInt+1 >= (int)validEntries.size()){
							log(excelEntry+" is an invalid entry!!!\n;");
							System.out.println(excelEntry+" is an invalid entry!!!\n");
							throw new IllegalArgumentException();
						}
					}
				}
		}
		
		//Ending message::
				Thread.sleep(1000);//1000
				//takeScreenshot();
				
				//task.clickSaveAndCloseButton();
				
				System.out.println("Validation Completed\n***************\n");
				log("Validation Completed");
				
				Thread.sleep(1500);//3000
				//takeScreenshot();
	}
}

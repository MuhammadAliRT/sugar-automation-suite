package SugarTest;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SugarAutomationSuite {
	public static void main(String[] args)
	{
		WebDriver driver;
		try {
			driver = signIn();
			CsvHandler csv = new CsvHandler("data.csv");
			csv.createResultFile();
			ArrayList elements = csv.readCsvFile(1);
			
			ArrayList webElements = create(driver, elements);
			verify(driver, webElements, elements, csv);		
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static WebDriver signIn() throws Exception
	{
		WebDriver driver = AppLogin.login();
        return driver;
	}
	private static void clickButtonElement(WebDriver driver, String by, String path)
	{
		By temp = FieldSupport.strategy(by, path);
		driver.findElement(temp).click();
	}
	
	private static ArrayList<WebElement> create(WebDriver driver, ArrayList<String[]> elements) throws Exception
	{
		clickButtonElement(driver, "linkText", "Accounts");
		clickButtonElement(driver, "name", "create_button");
		
		ArrayList<WebElement> webElements = new ArrayList<WebElement>();
		// loop through all elements of array, find them and add them to webelements array
		for(String[] element : elements)
		{
			By temp = FieldSupport.strategy(element[1], element[2]);
			webElements.add(driver.findElement(temp));
		}	
		fillFields(driver, webElements, elements);
		save(driver);
		return webElements;	
	}

	private static void fillFields(WebDriver driver,ArrayList<WebElement> webElements, ArrayList<String[]> elements) throws Exception
	{
		//fill the fields
		for(WebElement webElement : webElements)
		{
			int index = webElements.indexOf(webElement);
			String[] currentRow = elements.get(index);
			if(currentRow[5].equals("select"))
			{
				webElement.click();
				driver.findElement(By.xpath(currentRow[6] +"'"+currentRow[3]+"']")).click();
			}
			else
			webElement.sendKeys(currentRow[3]);
		}
	}
	public static void save(WebDriver driver) throws Exception
	{
        WebElement duplicateElement = driver.findElement(By.xpath("//span[@class='duplicate_count']"));
        clickButtonElement(driver, "cssSelector","div.drawer.active a[name=save_button]");
        
		TimeUnit.SECONDS.sleep(2);
		if(!(duplicateElement.getText().equals("")))
		{
			clickButtonElement(driver, "xpath", "//span[@class='create']/a[@track='click:duplicate_button']");
		}
		TimeUnit.SECONDS.sleep(3);
    
	}
	private static void verify(WebDriver driver, ArrayList<WebElement> webElements, ArrayList<String[]> elements, CsvHandler csv) throws Exception
	{
		driver.findElement(By.xpath("//tr[1]/td[@data-type='name']/span/div/a")).click();
		for(WebElement element : webElements)
		{
			int i = webElements.indexOf(element);
	
			String[] currentRow= elements.get(i);
			String elementText ="";
			if((elementText = driver.findElement(FieldSupport.strategy("xpath",currentRow[4])).getText()).equals(currentRow[3]))
			{
		    	System.out.println(currentRow[0] + " verified: " + currentRow[3]);
			}
			else
			{
		    	System.out.println(currentRow[0] + " not verified" + elementText +  " "+ currentRow[3]);
			}
			csv.writeResults(currentRow[0], currentRow[3], elementText);

		}
	}
}

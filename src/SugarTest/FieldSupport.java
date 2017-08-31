package SugarTest;

import org.openqa.selenium.By;

public class FieldSupport 
{
	public static By strategy(String by, String path)
	{
		switch(by)
		{
			case "name":
			{
				return By.name(path);
			}
			case "cssSelector":
			{
				return By.cssSelector(path);
			}
			case "className":
			{
				return By.className(path);
			}
			case "id":
			{
				return By.id(path);
			}
			case "xpath":
			{
				return By.xpath(path);
			}
			case "linkText":
			{
				return By.linkText(path);
			}
		}
		return null;
	}
	
}

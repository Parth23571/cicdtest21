package com.stc.gitcheck.cicdtest.scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenApplication {
	public WebDriver driver = null;
	public String strPath = "E:\\SeleniumTraining1\\TestingApp.html";

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

	}

	@Test
	public void forgotpassword() {
		try {
			driver.get(strPath);
			driver.manage().window().maximize();

			driver.findElement(By.xpath("(//a)[1]")).click();
			Thread.sleep(3000);
			System.out.println("Clicked successfully");
			String strActual = driver.findElement(By.xpath("//h2")).getText();
	
			String strExpected = "OOps something went wrong. Please contact your administrator";
			boolean result = strActual.equalsIgnoreCase(strExpected);
			if (result == true) {
				System.out.println("MATCHED");
			} else {
				System.out.println("NOT MATCHED");
			}
			driver.navigate().back();
			Thread.sleep(2000);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("SKIPPPPPPPPPPPPPPPPPPPPPPPPPP");
		}

	}

	@Test
	public void openApplication() {

		try {
			driver.get(strPath);
			driver.manage().window().maximize();
			driver.findElement(By.id("PWD")).sendKeys("Testing");
			Thread.sleep(2000);
			driver.findElement(By.id("PWD")).clear();
			WebElement w = driver.findElement(By.id("PWD"));
			JavascriptExecutor javasc = (JavascriptExecutor) driver;
			javasc.executeScript("arguments[0].value='DDD';", w);
			Thread.sleep(3000);
			// WebElement password=driver.findElement(By.id("PWD1"));
			boolean passw = isFieldExists("PWD");
			System.out.println(passw);
			if (passw == true) {
				System.out.println("Elsemene Exists");

			} else {
				System.out.println("Elsemene does NOT Exist");

			}
			Thread.sleep(3000);

			// driver.quit();
		}

		catch (Exception e) {
			System.out.println("Error while launching " + e.getMessage());
		}

	}

	public boolean isFieldExists(String fieldname1) {
		boolean flag = false;

		try {
			WebElement fieldname = driver.findElement(By.id(fieldname1));
			flag = false;

			if (fieldname.isDisplayed()) {
				flag = true;
			} else {

				flag = false;
			}
		} catch (Exception e) {
			flag = false;
			System.out.println(e.getMessage());
			return flag;

		}
		return flag;
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}

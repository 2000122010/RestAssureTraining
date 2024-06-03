import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class LumaShopping {

	public static void main(String[] args) throws IOException, InterruptedException{
			// TODO Auto-generated method stub
			/*
			 * Pre-requirements
			 * -To have an account
			 * 
			 * To do
			 * -Add three different product to cart
			 * -To buy the cart's products
			 * 
			 * Validate
			 * -The sum of the products is equal to the total
			 * -That the three chosen products is in the cart
			 * -Address is correct
			 * -To go "Thank for your purchase" page
			 * */
				//Set driver path in System propeties
					System.setProperty("webdriver.chrome.driver", "C:\\Users\\2000122010\\Documents\\chromedriver-win64\\chromedriver.exe");
					
					//Create CrhomeDriver
					ChromeOptions options = new ChromeOptions();
					
					//Create wait object
					Watis waitFor = new Watis();
					
					options.addArguments("--start-maximized");
					options.addArguments("--remote-allow-origins=*");
					ChromeDriver driver = null;
					
					//Create driver and open the page
					driver = new ChromeDriver(options);
					driver.get("https://magento.softwaretestingboard.com/");
					
					//Create ob Action class
					Actions action = new Actions(driver);
		try {
			/*
			 * 0.Vital data
			 * */
				//Acount info
				String	FirstName="Isaac", LasName="Garcia", eMail="2000122010@hexaware.com", 
						Password="Hexaware.com",Company="Hexaware", PhoneNumber="8440000000", StreetAdress="Calle buenavida 3145", 
						City="Springfield", State="Texas", ZipCode="31459", Country="United States";
			
			/*
			 * 1. 	To login
			 * */
				//Go to Sign in
				By signIn = By.xpath("(//ul/li/a)[1]");
				WebElement signInButton = driver.findElement(signIn);
				highlightElement(signInButton, driver);
				takeScreenshot("1. Main menu", driver);
				signInButton.click();

				//login page
				By logIn = By.xpath("(//div[@class=\"actions-toolbar\"])[1]/div/button");
				By username = By.name("login[username]");
				By pass = By.name("login[password]");
				
				waitFor.ImplicitWait(driver);
				
				WebElement SignInButton = waitFor.ExplicitWait(driver, logIn);
				takeScreenshot("1.1. Login before", driver);
				highlightElement(SignInButton, driver);
				
				WebElement eMailInput = driver.findElement(username);
				highlightElement(eMailInput, driver);
				eMailInput.sendKeys(eMail);
				
				WebElement passwordInput = driver.findElement(pass);
				highlightElement(passwordInput, driver);
				passwordInput.sendKeys(Password);
				
				takeScreenshot("1.2. Login after", driver);
				SignInButton.click();
				
				
				/*
				 * 2. Choosing products
				 * */
				//waitFor.ImplicitWait(driver);
				takeScreenshot("2.1. Main menu", driver);
				
				String womanProductName, womanProductPrice;
				String manProductName, manProductPrice;
				String gearProductName, gearProductPrice;
				
				//Main elements
				By newYoga = By.cssSelector(".action.more.button");
				By men = By.cssSelector("a#ui-id-5");
				By women = By.cssSelector("a#ui-id-4");
				By gear = By.cssSelector("a#ui-id-6");
				
				WebElement yogaButton = waitFor.ExplicitWait(driver, newYoga);
				highlightElement(yogaButton, driver);
				
				/*#############################*/
				
				//Go to women's catalog
				WebElement womenButton = driver.findElement(women);
				womenButton.click();
				waitFor.ImplicitWait(driver);
				
				//Scroll down
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0, 1750);");
				
				//Use onHover to product item
				WebElement womenDiv = driver.findElement(By.cssSelector(".product-item:nth-child(1)"));
				action.moveToElement(womenDiv).perform();
				
				
				//Select size
				WebElement sizeWoman = driver.findElement(By.cssSelector(".product-item:nth-child(1) .swatch-option.text:nth-child(1)"));
				highlightElement(sizeWoman, driver);
				sizeWoman.click();
				
				//Select color
				WebElement colorWoman = driver.findElement(By.cssSelector(".product-item:nth-child(1) .swatch-option.color:nth-child(1)"));
				highlightElement(colorWoman, driver);
				colorWoman.click();
				
				//get selected info
				WebElement womanProductElement = driver.findElement(By.cssSelector(".products-grid.grid li.product-item:nth-child(1) .product-item-name a"));
				womanProductName = womanProductElement.getText();
				
				WebElement womanElementPrice = driver.findElement(By.cssSelector(".products-grid.grid li.product-item:nth-child(1) .price"));
				womanProductPrice = womanElementPrice.getText();
				
				System.out.println("/n"+womanProductName+","+womanProductPrice+"/n");
				
				//Adding to cart
				action.moveToElement(womenDiv).perform();
				By womenAdd = By.xpath("(//button[@type=\"submit\"])[2]");
				WebElement womenAddButton = waitFor.ExplicitWait(driver, womenAdd);
				highlightElement(womenAddButton, driver);
				takeScreenshot("2.2.1. Selecting product", driver);
				womenAddButton.click();
				
				//Wait until the product its added correctly
				By alertWomanDiv = By.xpath("//div[@role=\"alert\"]");
				WebElement alertWoman = waitFor.ExplicitWait(driver, alertWomanDiv);
				highlightElement(alertWoman, driver);
				takeScreenshot("2.2.2. Product selected and added", driver);
				
				/*#############################*/

				//Go to man's catalog
				WebElement menButton = driver.findElement(men);
				menButton.click();
				waitFor.ImplicitWait(driver);

				//Scroll down
				jse.executeScript("scroll(0, 1750);");
				

				//Use onHover to product item
				WebElement menDiv = driver.findElement(By.cssSelector(".products-grid.grid li.product-item:nth-child(1)"));
				action.moveToElement(menDiv).perform();

				
				//Select size
				WebElement sizemen = driver.findElement(By.cssSelector(".product-item:nth-child(1) .swatch-option.text:nth-child(1)"));
				highlightElement(sizemen, driver);
				sizemen.click();

				//Select color
				WebElement colormen = driver.findElement(By.cssSelector(".product-item:nth-child(1) .swatch-option.color:nth-child(1)"));
				highlightElement(colormen, driver);
				colormen.click();

				//get selected info
				WebElement menProductElement = driver.findElement(By.cssSelector(".products-grid.grid li.product-item:nth-child(1) .product-item-name a"));
				manProductName = menProductElement.getText();
				
				WebElement menElementPrice = driver.findElement(By.cssSelector(".products-grid.grid li.product-item:nth-child(1) .price"));
				manProductPrice = menElementPrice.getText();
				
				System.out.println("/n"+manProductName+","+manProductPrice+"/n");

				//Adding to cart
				action.moveToElement(menDiv).perform();
				By menAdd = By.xpath("(//button[@type=\"submit\"])[2]");
				WebElement menAddButton = waitFor.ExplicitWait(driver, menAdd);
				highlightElement(menAddButton, driver);
				takeScreenshot("2.3.1. Selecting product", driver);
				menAddButton.click();

				
				//Wait until the product its added correctly
				By alertManDiv = By.xpath("//div[@role=\"alert\"]");
				WebElement alertMan = waitFor.ExplicitWait(driver, alertManDiv);
				highlightElement(alertMan, driver);
				takeScreenshot("2.3.2. Product selected and added", driver);
				
				
				/*#############################*/
				//Go to gear's catalog
				WebElement gearButton = driver.findElement(gear);
				gearButton.click();
				waitFor.ImplicitWait(driver);

				//Scroll down
				jse.executeScript("scroll(0, 1750);");


				//Use onHover to product item
				WebElement gearDiv = driver.findElement(By.cssSelector(".products-grid.grid li.product-item:nth-child(1)"));
				action.moveToElement(gearDiv).perform();


				//get selected info
				WebElement gearProductElment = driver.findElement(By.cssSelector(".products-grid.grid li.product-item:nth-child(1) .product-item-name a"));
				gearProductName = gearProductElment.getText();
				
				WebElement gearPrice = driver.findElement(By.cssSelector(".products-grid.grid li.product-item:nth-child(1) .price"));
				gearProductPrice = gearPrice.getText();
				
				System.out.println("/n"+gearProductName+","+gearProductPrice+"/n");

				//Adding to cart
				action.moveToElement(gearDiv).perform();
				By gearAdd = By.xpath("(//button[@type=\"submit\"])[2]");
				WebElement gearAddButton = waitFor.ExplicitWait(driver, gearAdd);
				highlightElement(gearAddButton, driver);
				takeScreenshot("2.4.1. Selecting product", driver);
				gearAddButton.click();


				//Wait until the product its added correctly
				By alertgearDiv = By.xpath("//div[@role=\"alert\"]");
				WebElement alertgear = waitFor.ExplicitWait(driver, alertgearDiv);
				highlightElement(alertgear, driver);
				takeScreenshot("2.4.2. Product selected and added", driver);
				
			/*
			 * 3.Go to cart
			 * */
				//Go to cart
				By alertGoToCart = By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/div/div/a");
				WebElement goToCartLink = waitFor.ExplicitWait(driver, alertGoToCart);
				highlightElement(goToCartLink, driver);
				takeScreenshot("3.1. Select link to cart", driver);
				goToCartLink.click();
				
				waitFor.ImplicitWait(driver);
				takeScreenshot("3.2. cart's items", driver);
				
				
				
			/*
			 * 4.Validate data
			 * */
				By ToProceed = By.cssSelector("li button.action.primary.checkout");
				WebElement Proceed =waitFor.ExplicitWait(driver, ToProceed);
				
				//Validate name
				WebElement nameProductW = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody[1]/tr[1]/td[1]/div/strong/a"));
				String womenRealName = nameProductW.getText();
				
				WebElement nameProductM = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody[2]/tr[1]/td[1]/div/strong/a"));
				String menRealName = nameProductM.getText();
				 
				WebElement nameProductG = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody[3]/tr[1]/td[1]/div/strong/a"));
				String gearRealName = nameProductG.getText();
				
				if(!womenRealName.equals(womanProductName) || !menRealName.equals(manProductName) || !gearRealName.equals(gearProductName)) {
					System.out.print("/nTest failed. Products names doesn't match");
					driver.quit();
				}
				else {System.out.print("/nFirst test success!.");}
				
				//Validate price
				WebElement priceProductW = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody[1]/tr[1]/td[2]/span/span/span"));
				String womenRealPrice = priceProductW.getText();
				WebElement priceProductM = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody[2]/tr[1]/td[2]/span/span/span"));
				String menRealPrice = priceProductM.getText();
				WebElement priceProductG = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody[3]/tr[1]/td[2]/span/span/span"));
				String gearRealPrice = priceProductG.getText();
				
				if(!womenRealPrice.equals(womanProductPrice) || !menRealPrice.equals(womanProductPrice) || !gearRealPrice.equals(gearProductPrice)) {
					System.out.print("/nTest failed. Products prices doesn't match");
					driver.quit();
				}
				else {System.out.print("/nSecond test success!.");}
				
				
				By subTotalSpan= By.xpath("//*[@id=\"cart-totals\"]/div/table/tbody/tr[3]/td/strong/span");
				WebElement subTotal =waitFor.ExplicitWait(driver, subTotalSpan);
				String RealSubTotal = subTotal.getText();
				
				Double total=
								Double.parseDouble(womanProductPrice.substring(1))+
								Double.parseDouble(womanProductPrice.substring(1))+
								Double.parseDouble(gearProductPrice.substring(1));
				
				if(!(Double.parseDouble(RealSubTotal.substring(1))==total)){
					System.out.print("/nTest failed. Sum of the prices doesn't match");
					driver.quit();
				}

				else {System.out.print("/nThird test success!.");}
				
				
				takeScreenshot("4.1. cart's items", driver);
				Proceed.click();
				
			/*
			 * 5.Validate address
			 * */
				By next= By.cssSelector("#shipping-method-buttons-container > div > button");
				WebElement nextButton =waitFor.ExplicitWait(driver, next);
				
				By shipingMethod = By.xpath("//*[@id=\"checkout-shipping-method-load\"]/table/tbody/tr[1]/td[1]/input");
				WebElement shipingChoose = driver.findElement(shipingMethod);
				shipingChoose.click();
				
				By adress = By.xpath("//div[@class=\"shipping-address-item selected-item\" and text()=\"Calle buenavida 3145\"]");
				WebElement adressText =driver.findElement(adress);
				String RealAdress = adressText.getText();
				
				System.out.print(RealAdress);
				
				if(!RealAdress.contains(StreetAdress)) {
					System.out.print("/nTest failed. Address doesn's match");
					driver.quit();
				}
				takeScreenshot("5.1. Shipment", driver);
				nextButton.click();
				
				//Acept purchase
				By cart= By.xpath("//*[@id=\"opc-sidebar\"]/div[1]/div/div[2]/div/ol/li[3]/div/div");
				WebElement cartDetail =waitFor.ExplicitWait(driver, cart);
				
				By placeOrder= By.xpath("//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[2]/div[4]/div/button");
				WebElement placeOrderButton =waitFor.ExplicitWait(driver, placeOrder);
				Thread.sleep(3000000);
				placeOrderButton.click();
				placeOrderButton.click();	
				takeScreenshot("5.2. Acept", driver);
				
				
				//Thanks for yor purchase
				By Final= By.cssSelector("#maincontent > div.columns > div > div.checkout-success > div > div > a");
				WebElement FinalButton =waitFor.ExplicitWait(driver, next);
				takeScreenshot("5.3. Final", driver);

				
				
				
				
				
		}
		catch(Exception e) {
			System.out.println("/nError: "+e+"/n");
		}
			driver.close();
	}

	public static void highlightElement(WebElement element, WebDriver driver) {
		JavascriptExecutor js =(JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.outline = '#f00 solid 2px' ", element);
	}
	
	public static void takeScreenshot(String name, WebDriver driver) throws IOException{
		String ssFolder ="C:\\Users\\2000122010\\Pictures\\LumaShopping\\%s.png";
		String ssName = String.format(ssFolder, name);
		
		TakesScreenshot ss = (TakesScreenshot)driver;
		File screenshot = ss.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(screenshot, new File(ssName));
	}
}

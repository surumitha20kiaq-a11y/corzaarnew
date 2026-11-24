package com.BaseClass;




import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;




public class BaseClass {
	public static WebDriver driver;
	public static WebDriverWait wait;

	//	----------------Browser Launch---------------------------

	public void ChromebrowserLaunch() {
		driver= new ChromeDriver();
	}
	public static void edgebrowserLaunch() {
		driver = new EdgeDriver();
	}
	public static void firefoxbrowserLaunch() {
		driver = new FirefoxDriver();
	}

	public static  void enterApplicationUrl(String Url) {
		driver.get(Url);
	}
	public void maximizeWindow() {
		driver.manage().window().maximize();
	}
	public  static void insertvalue(WebElement element,String data) {
		element.clear();
		element.sendKeys(data);
	}
	public static void insertValueAndEnter(WebElement element,String data) {
		element.sendKeys(data,Keys.ENTER);
	}
	public static void pressEnter(WebElement element ) {
		element.sendKeys(Keys.ENTER);
	}
	public static void clickElement(WebElement element) {
		element.click();
	}


	//	-----------------------Find Locators--------------

	public static WebElement findelement(By locator) {
		return
				driver.findElement(locator);
	}
	public static List<WebElement> findelements(By locator) {
		return
				driver.findElements(locator);
	}
	public static List<String> getDropdownValues(WebElement element){
		Select select = new Select(element);
		List<String> values = new ArrayList<>();
		for(WebElement option: select.getOptions()) {
			values.add(option.getText().trim());
		}
		return values;
		}
	


	//	--------------get values-------------------------------

	public static String getText( WebElement element) {
		return element.getText();
	}
	public static String getapplicationTitle() {
		String title = driver.getTitle();
		return title;
	}
	public static String geturl() {
		String text = driver.getCurrentUrl();
		return text;
	}
	public static String getDompropertyElement(WebElement element, String attributeName) {
		String domproperty = element.getDomProperty("value");
		return domproperty;
	}


	//    --------------verify-----------------------

	public static boolean isDisplayed(WebElement element) {
		boolean displayed = element.isDisplayed();
		return displayed;
	}
	public  static boolean isEnabled(WebElement element) {
		boolean enabled = element.isEnabled();
		return enabled;
	}
	public static boolean isselected(WebElement element) {
		boolean selected = element.isSelected();
		return selected;
	}
	public static  void clearTextBox(WebElement element) {
		element.clear();
	}

	//	-----------------Waits----------------------------------


	public static void  implicitWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}
	public static void initializeWait() {
		wait = new WebDriverWait(driver,Duration.ofSeconds(40));
}
	public static void waitForvisibility(WebElement element) {
		wait = new WebDriverWait(driver,Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public static void waitForElementToClickable(WebElement element) {
		wait = new WebDriverWait(driver,Duration.ofSeconds(40));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
     public static void clickWithRetry(WebElement element) {
 		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
      for(int i=0;i<3;i++) {
    	  try {
    		  scrollToElement(element);
    		  wait.until(ExpectedConditions.visibilityOf(element));
    		  wait.until(ExpectedConditions.elementToBeClickable(element));
    		  element.click();
    		  return;
    	  }catch(StaleElementReferenceException | ElementClickInterceptedException ele) {
    		  if(i==2) {
    			  throw ele;
    		  }
    	  }
      }
}
     public static void clickFirstPaidCourse(List<WebElement> courseCards) {

    	    for (WebElement card : courseCards) {

    	        String priceText = card.findElement(
    	                By.xpath(".//span[contains(@class,'font-black')]"))
    	                .getText();

    	        String amountText = priceText.replaceAll("[^0-9.]", "");

    	        if (!amountText.isEmpty()) {

    	            double amount = Double.parseDouble(amountText);

    	            if (amount > 0) {

    	                System.out.println("Selected paid course: " + priceText);

    	                waitForElementToClickable(card);
    	                clickElement(card);

    	                return;
    	            }
    	        }
    	    }

    	    Assert.fail("No paid course found");
     }

     public void clickCheckbox(WebElement checkbox) {

    	    if (checkbox.isSelected()) {
    	        checkbox.click();
    	    }

    	    checkbox.click();
    	}
     public void clearDeliveryCheckboxes(
    	        WebElement live,
    	        WebElement classroom,
    	        WebElement hybrid,
    	        WebElement selfPaced) {

    	    List<WebElement> checkboxes = Arrays.asList(
    	        live, classroom, hybrid, selfPaced
    	    );

    	    for (WebElement checkbox : checkboxes) {
    	        if (checkbox.isSelected()) {
    	            checkbox.click();
    	        }
    	    }
    	}
   public static boolean waitForCondition(Function<WebDriver, Boolean> condition) {
FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
 	            .withTimeout(Duration.ofSeconds(40))
 	            .pollingEvery(Duration.ofSeconds(1))
 	            .ignoring(NoSuchElementException.class);

 	    return fluentWait.until(condition);
 	}
//	--------------------Alerts------------------------------------------

	public static void acceptAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	public static void cancelAlert() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}
	public static String getAlertText() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}
	public static void sendTextToAlert(String text) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(text);
	}


	//	---------------Action class Methods-----------------


	public static void click(WebElement element) {
		Actions actions = new Actions(driver);
		actions.click(element).perform();
	}
	public static void doubleclick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}
	public static void rightclick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.contextClick(element).perform();
	}
	public static void mouseover(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	public static void draganddrop(WebElement source,WebElement target) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source,target).perform();
	}
	public static void sendkeys(WebElement element,String text) {
		Actions actions = new Actions(driver);
		actions.sendKeys(element,text).perform();
	}

	//	------------------select class methods -------------------

	public static void selectByVisibleText(WebElement element ,String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	public static void selectDropdown(By locator, String value) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		new Select(element).selectByVisibleText(value);
	}
	public static void selectByvalue(WebElement element,String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	public static void selectByIndex(WebElement element,int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	public static String getfirstSelectedoption(WebElement element) {
		Select select = new Select(element);
		return
				select.getFirstSelectedOption().getText();
	}
	public static void delectByVisibleText(WebElement element,String text) {
		Select select = new Select(element);
		select.deselectByVisibleText(text);
	}
	public static void delectByvalue(WebElement element,String value) {
		Select select = new Select(element);
		select.deselectByValue(value);
	}
	public static void delectByIndex(WebElement element,int index) {
		Select select = new Select(element);
		select.deselectByIndex(index);
	}
	public static void deselectall(WebElement element) {
		Select select = new Select(element);
		select.deselectAll();
	}
	 public void selectDropdownValues(
	            WebElement element, String value) {

	        Select select = new Select(element);

	        select.selectByValue(value);
	    }

	
	    public String getSelectedDropdownValue(
	            WebElement element) {

	        Select select = new Select(element);

	        return select.getFirstSelectedOption().getAttribute("value");
	    }

	    
	    public static List<String> getDropdownoptions(By locator) {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));


	        Select select = new Select(element);

	        List<String> values = new ArrayList<String>();

	        for (WebElement option : select.getOptions()) {

	            String text  = option.getText().trim();
	            if(!text.isEmpty()) {
	            	values.add(text);
	            }
	        }

	        return values;
	    }
	    
	   
	    public boolean isDurationMatching(String filter, String actualDuration) {

	        // Any Duration should accept every course
	        if (filter.trim().equalsIgnoreCase("Any Duration")) {
	            return true;
	        }

	        java.util.regex.Matcher matcher =
	            java.util.regex.Pattern.compile(
	                "(\\d+)\\s*(month|months|year|years|week|weeks)",
	                java.util.regex.Pattern.CASE_INSENSITIVE
	            ).matcher(actualDuration);

	        if (!matcher.find()) {
	            return false;
	        }
          double months = Double.parseDouble(matcher.group(1));

	        String unit = matcher.group(2).toLowerCase();

	        if (unit.startsWith("year")) {
	            months = months * 12;
	        } else if (unit.startsWith("week")) {
	            months = months / 4.345;
	        }

	        switch (filter.trim()) {

	            case "Under 1 month":
	                return months < 1;

	            case "1–3 months":
	                return months >= 1 && months <= 3;

	            case "3–6 months":
	                return months > 3 && months <= 6;

	            case "6–12 months":
	                return months > 6 && months <= 12;

	            case "1+ year":
	                return months >= 12;

	            default:
	                return false;
	        }
	    }
	  public static  String getSelectedValues(By locator) {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));  
			return new Select(element).getFirstSelectedOption().getText().trim();	 
			}
	   public static void setcheckbox(By locator ,boolean shouldBeSelected) {
			WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(locator));
         if(checkbox.isSelected()!=shouldBeSelected) {
        	 checkbox.click();
         }
	   }
	   public static  boolean isChechboxSelected(By locator) {
			WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));  
			return checkbox.isSelected();	 
			}
	   public static List<String> getElemeentsText(By locator){
		   List<WebElement> elements = driver.findElements(locator);
		   List<String> texts = new ArrayList<>();
		   for(WebElement element : elements) {
			   texts.add(element.getText().trim());
		   }
		   return texts;
	   }
       public static void waitForCourseresult(By courseLocator, By noResultsLocator) {
	wait.until(driver-> !driver.findElements(courseLocator).isEmpty()
			|| !driver.findElements(noResultsLocator).isEmpty());
       }
      public static void selectCheckbox(WebElement element) {
    	  if(!element.isSelected()){
    		  element.click();
    		  }
    	  }
      public static boolean isCheckboxSelected(WebElement element) {
    	  return element.isSelected();
      }

	    public static int getElementCount(List<WebElement> elements) {
	    	return elements.size();
	    	}
	

	//	----------------JS executor--------------

	public static void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	public static void jsSendKeys(WebElement element, String text) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value=arguments[1];", element, text);
	}
	public static void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block:'center',inline:'nearest'});", element);
	}
	public static void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
	}
	public static void scrollUp(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo({top:0,behavior: 'instant'});",element);
	}
	public static String getTitleByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript("return document.title;");
	}
	public static void scrollToBottom() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("window.scrollTo(0,documentElement.scrollHeight);");
	}


	//	    ----------------switch frames-------------------------

	public static void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}
	public static void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}
	public static void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}
	public static void switchToParentFrame() {
		driver.switchTo().parentFrame();
	}
	public static void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	//	-------------------Browser close Actions---------------

	public static void closewindow() {
		driver.close();
	}
	public  void quitchrome() {
		driver.quit();
	}




}




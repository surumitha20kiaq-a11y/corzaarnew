package com.PomClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PomClass {
	WebDriver driver;
    public PomClass(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this);
}
//    ==============Locators====================================
      @FindBy(xpath="//span[contains(text(),'Sign In')]")
     private WebElement signin;
      
      @FindBy(xpath="//button[contains(text(),'Auto-Fill Demo')]")
      private WebElement autofilldemo;
      

      
      @FindBy(xpath="//button[@type='submit']")
      private WebElement sendonetimepasscode;
      
      @FindBy(xpath="//button[contains(text(),'Auto')]")
      private WebElement autofillotp;
      
      @FindBy(xpath="//span[contains(text(),'Verify ')]")
      private WebElement verifyotp;
       
      @FindBy(xpath="//input[@type='tel']")
      private WebElement mobile;
      
      @FindBy(xpath="//button[@type='button']/div")
      private WebElement profile;
      
      @FindBy(xpath="//span[contains(text(),'Failed ')]")
      private WebElement errormessage;
  
      @FindBy(id="full-name-(optional-for-existing-learners)")
      private WebElement fullname;
      
      @FindBy(xpath="//a[normalize-space()='Courses']")	
      private WebElement courses;
     
      @FindBy(xpath="//input[@placeholder='Search courses, skills, institutes...']")
      private WebElement search;
      
      @FindBy(xpath="//label[text()='Discipline']/following-sibling::select")
      private WebElement discipline;
      
      @FindBy(xpath="//span[contains(text(),'Design')]")
      private List<WebElement> design;
      
      @FindBy(xpath="//span[contains(text(),'Technology')]")
      private List<WebElement> technologyname;
      
      @FindBy(xpath="//button[contains(normalize-space(),'Explore Courses')]")
      private WebElement explorecourses;
      
      @FindBy(xpath="//span[contains(text(),'Technology')]")
      private WebElement technology;
      
      
      @FindBy(xpath="//h3[contains(text(),'Data Analytics with Python')]")
      private WebElement dataanalytics;
      
      @FindBy(xpath="//input[(@type='checkbox' and @id='live-online' or @id='in-person-/-classroom' or @id='hybrid' or @id='self-paced')]")
      private List<WebElement> allcheckboxes;
      
      @FindBy(xpath="//input[@id='live-online']/following-sibling::div")
      private WebElement liveonline;
      
      @FindBy(xpath="//input[@id='in-person-/-classroom']/following-sibling::div")
      private WebElement personclassroom;
      
      @FindBy(xpath="//input[@id='hybrid']/following-sibling::div")
      private WebElement hybrid;
      
      @FindBy(xpath="//input[@id='self-paced']/following-sibling::div")
      private WebElement selfpaced;
      
      
      @FindBy(xpath="//select[.//option[contains(.,'Any Duration')]]")
      private WebElement programduration;
      
      @FindBy(xpath="//h3[contains(text(),'No matching programs found')]")
      private WebElement emptymessage;
      
      
      
      @FindBy(xpath="//span[@class='bg-surface-alt dark:bg-slate-800 px-2 py-0.5 rounded-md']")
      private List<WebElement> durationcourses;
      
      @FindBy(xpath="//label[.//input[@id='free-enrollment-only']]")
      private WebElement freeenrollment;
      
      @FindBy(xpath="//main//a[contains(@class,'group')]//span[normalize-space()='Free']")
      private List<WebElement> freecourses;
      
      @FindBy(xpath="//button[contains(text(),'Reset')]")
      private WebElement resetlink;
      
      @FindBy(xpath="//*[@id=\"root\"]/div[1]/main/div/div[2]/main/div[1]/div/select")
      private WebElement sortfilter;
      
      @FindBy(xpath = "//*[@id=\"root\"]/div[1]/main/div/div[2]/main/div[1]/div/select")
      WebElement sortDropdown;

      @FindBy(xpath = "//h3[normalize-space()='No matching programs found']")
     WebElement emptycourse;
      
      @FindBy(xpath = "//main//div[contains(@class,'grid-cols-1')]/a[contains(@class,'group') and contains(@class,'flex-col')]")
	    private List<WebElement> coursecard ;
      
      @FindBy(xpath = "//main//a[contains(@class,'group')]//span[contains(@class,'rounded-md') and contains(@class,'capitalize')]")
	    private List<WebElement> coursestatuses ;
      @FindBy(xpath = "//button[contains(text(),'Reset')]")
	     WebElement resetbutton; 
      
      @FindBy(xpath = "//main//a[contains(@class,'group')]//div[contains(@class,'mt-3')  and  contains(@class,'flex')]/span[1]")
	    private List<WebElement> courseduration ;
      
      @FindBy(xpath = "//select[option[@value='under_1m']]")
	     WebElement selectprogram; 
      
      @FindBy(id = "learner-mobile-number")
	     WebElement mobilenumber;
      
      @FindBy(id = "full-name-(optional-for-existing-learners)")
	     WebElement name;
      
      @FindBy(id = "enter-6-digit-passcode")
	     WebElement otpfield;
      
      @FindBy(xpath = "//select[.//option[contains(.,'Recommended')]]")
	     WebElement recommended;
      
      @FindBy(xpath = "//main//a[contains(@class,'group')]//div[contains(@class,'inline-flex') and contains(@class,'items-center')]//span[contains(@class,'font-bold')]")
	    private List<WebElement> courseratings ;
      
      @FindBy(xpath = "//main//a[contains(@class,'group')]//span[contains(@class,'font-black') and contains(normalize-space(),'₹')]")
      private List<WebElement> courseprices;
      
      @FindBy(xpath = "//main//a[contains(@class,'group')]//span[contains(@class,'font-medium') and contains(@class,'truncate')]")
      private List<WebElement> institutename;
      
      @FindBy(xpath = "//main//a[contains(@class,'group')]//h3")
      private List<WebElement> coursenames; 
      
      @FindBy(xpath = "//input[@placeholder='Search courses, skills, institutes...']")
	     WebElement searchfield;
      
      @FindBy(xpath = "//main//a[contains(@class,'group')]//div[contains(@class,'flex-wrap')]/span")
      private List<WebElement> courseskills;
      
      @FindBy(xpath = "//button[contains(normalize-space(.),'Enroll Immediately')]")
      private WebElement enrollImmediately;
      
      @FindBy(xpath = "//*[self::h1 or self::h2 or self::h3][contains(normalize-space(.),'Review Your Enrollment')]")
      private WebElement reviewEnrollment;
      
      @FindBy(xpath = "//main//a[contains(@class,'group')]")
      private List<WebElement> courseCards;
      
      @FindBy(xpath = "//a[normalize-space()='Offers' or .//span[normalize-space()='Offers']]")
      private WebElement offersModule;
      
      @FindBy(xpath = "//button[normalize-space()='Copy']/ancestor::div[contains(@class,'rounded') and contains(@class,'border')][1]")
      private List<WebElement> offerCards;
      
      public WebElement getOfferPercentage(WebElement card) {
    	    return card.findElement(
    	        By.xpath(".//*[contains(text(),'%')]")
    	    );
    	}
      
      public WebElement getCouponCode(WebElement card) {
    	    return card.findElement(
    	        By.xpath(".//button[normalize-space()='Copy']/preceding-sibling::*[1]")
    	    );
    	}
      public WebElement getActiveStatus(WebElement card) {
    	    return card.findElement(
    	        By.xpath(".//*[normalize-space()='Active']")
    	    );
    	}
     public WebElement getCopyButton(WebElement card) {
    	    return card.findElement(
    	        By.xpath(".//button[contains(normalize-space(.),'Copy')]")
    	    );
    	    }
     @FindBy(xpath = "//main//a[contains(@class,'group')]//span[contains(@class,'font-black')]")
     private List<WebElement> courseprice;
     
      @FindBy(xpath = "//h4[normalize-space()='Copied to Clipboard!']")
	    private WebElement copyMessage;
      
      @FindBy(xpath = "//h1[normalize-space()='Review Your Enrollment Order']")
      private WebElement reviewEnrollmentHeading;
      
      @FindBy(xpath = "//main//*[contains(@class,'uppercase') and normalize-space()]")
      private List<WebElement> courseCategories;
     
      @FindBy(xpath = "//div[normalize-space()='Partner Institute']")
      private WebElement enrollmentInstitute;

      @FindBy(xpath = "//div[contains(@class,'rounded')]//span[contains(normalize-space(),'₹')]")
      private WebElement courseTuition;
     
      @FindBy(xpath = "//input[@placeholder='E.G. WELCOME20']")
      private WebElement promoCouponField;
      
      @FindBy(xpath = "//button[normalize-space()='Apply']")
      private WebElement applyCouponButton;
      
      @FindBy(xpath = "//input[@placeholder=\"FRIEND'S REFERRAL CODE\"]")
      private WebElement referralCodeField;
      
      @FindBy(xpath = "//[normalize-space()='Tuition Subtotal']/following-sibling::")
      private WebElement tuitionSubtotal;
      
      @FindBy(xpath = "//[normalize-space()='Total Due']/following-sibling::")
      private WebElement totalDue;
      
      @FindBy(xpath = "//button[contains(normalize-space(),'Pay & Enroll')]")
      private WebElement payAndEnrollButton;
      
      @FindBy(xpath = "//main//h1")
      private WebElement courseDetails;
      
      @FindBy(xpath="//main//a[contains(@class,'group')]")
      private List<WebElement> courseCard;
      
      
//      ===============Getters=======================================
      public WebElement signinbutton() {
    	  return signin;
      }
      public WebElement Autofilldemo() {
    	  return autofilldemo;
      }
      public WebElement sendonetimepasscodebutton() {
    	  return sendonetimepasscode;
      }
      public WebElement autofillotpbutton() {
    	  return autofillotp;
      }
      public WebElement verifyotpbutton() {
    	  return verifyotp;
      }
      public WebElement mobilefield() {
    	  return mobile;
      }
      public WebElement profileicon() {
    	  return profile;
      }
      public WebElement failedmessage() {
    	  return errormessage;
      }
      public WebElement fullnamefield() {
    	  return fullname;
      }
      public WebElement  coursesmodule() {
    	  return courses;
      }
      public WebElement  searchfield() {
    	  return search;
      }
     
      public WebElement disciplinedropdown() {
    	  return discipline ;
      }
      public WebElement dataanalyticscourse() {
    	  return dataanalytics;
      }
      public List<WebElement> disciplinedesign() {
    	  return design;
      }
      public List<WebElement> disciplinetechnology() {
    	  return technologyname ;
      }
      public WebElement explorecoursesbutton() {
    	  return explorecourses;
      }
      public WebElement disciplinetech() {
    	  return technology;
      }
      public List<WebElement> getallcheckboxes() {
    	  return allcheckboxes;
      }
      public WebElement clickliveonline() {
    	  return liveonline;
      }
      public WebElement clickpersonclassroom() {
    	  return personclassroom;
      }
      public WebElement clickhybrid() {
    	  return hybrid;
      }
      public WebElement clickselfpaced() {
    	  return selfpaced;
      }
      
      
      public WebElement programdurationdropdown(){
    	  return programduration;
      }
      public WebElement emptydropdownmessage(){
    	  return emptymessage;
      }
      
      public List<WebElement> durationcoursescard(){
    	  return durationcourses;
      }
      public WebElement freeenrollmentcheckbox(){
    	  return freeenrollment;
      }
      public List<WebElement> freecoursescard(){
    	  return freecourses;
      }
      public WebElement clickresetlink(){
    	  return resetlink;
      }
      public WebElement clicksortfilter(){
    	  return sortfilter;
      }
      public WebElement sortDropdownclick(){
    	  return sortDropdown;
      }
      public WebElement emptycoursemessage(){
    	  return emptycourse;
      }
      public List<WebElement> getcoursecards(){
    	  return coursecard;
      }
      public List<WebElement> getcoursestatuses(){
    	  return coursestatuses;
      }
    
      public WebElement clickresetbutton(){
    	  return resetbutton;
      }
      public List<WebElement> coursedurationcards(){
    	  return courseduration;
      }
      public WebElement selectprogramduration(){
    	  return selectprogram;
      }
      
      public WebElement mobilenumberfield(){
    	  return mobilenumber;
      }
      public WebElement namefield(){
    	  return name;
      }
      public WebElement sendotp(){
    	  return otpfield;
      }
      public WebElement recommendedDropdown(){
    	  return recommended;
      }
     
      public List<WebElement> getcourseratings (){
    	  return courseratings;
      }
     
      public List<WebElement> getInstituteName() {
    	    return institutename;
    	}
      public List<WebElement> getCourseNames() {
    	    return coursenames;
    	}
      public WebElement sendsearchfield(){
    	  return searchfield;
      }
      public List<WebElement> getCourseSkills() {
    	    return courseskills;
    	}
      public WebElement getEnrollImmediately() {
    	    return enrollImmediately;
    	}
      public WebElement getReviewEnrollment() {
    	    return reviewEnrollment;
    	}
      public List<WebElement> getCourseCards() {
    	    return courseCards;
    	}
      public WebElement getOffersModule() {
    	    return offersModule;
    	}
      public List<WebElement> getOfferCards() {
    	    return offerCards;
    	}
      public WebElement getCopyMessage() {
    	    return copyMessage;
    	}
      public WebElement getReviewEnrollmentHeading() {
    	    return reviewEnrollmentHeading;
    	}
      
      public List<WebElement> getCourseCategories() {
    	    return courseCategories;
    	}
      public WebElement getEnrollmentInstitute() {
    	    return enrollmentInstitute;
    	}
      public WebElement getCourseTuition() {
    	    return courseTuition;
    	}
      public WebElement getPromoCouponField() {
    	    return promoCouponField;
    	}
      public WebElement getApplyCouponButton() {
    	    return applyCouponButton;
    	}
      public WebElement getReferralCodeField() {
    	    return referralCodeField;
    	}
      public List<WebElement> getcourseprice() {
    	    return courseprice;
    	}

      public WebElement getTuitionSubtotal() {
return tuitionSubtotal;
}
public WebElement getTotalDue() {
return totalDue;
}
public WebElement getPayAndEnrollButton() {
return payAndEnrollButton;
}
public WebElement getCourseDetails() {
return courseDetails;
}
public List<WebElement> getcourseCard() {
    return courseCard;
}

}




package com.StepDefinition;
    import java.util.List;
	import org.openqa.selenium.support.ui.Select;
	import org.testng.Assert;
	import org.openqa.selenium.NoSuchElementException;
	import org.openqa.selenium.StaleElementReferenceException;
	import org.openqa.selenium.WebElement;
	import com.BaseClass.BaseClass;
	import com.PomClass.PomClass;
	import io.cucumber.java.en.Given;
	import io.cucumber.java.en.Then;
	import io.cucumber.java.en.When;
	

	public class StepDefinition  extends BaseClass {
		PomClass pom = new PomClass(driver);

	@Given("user is on the Corzaar login page")
		public void user_is_on_the_corzaar_login_page() {
			enterApplicationUrl("https://dci3e266umn5y.cloudfront.net/");
		}
		@When("user enter invalid {string}")
		public void user_enter_invalid(String string) {
			waitForvisibility(pom.signinbutton());
			clickElement(pom.signinbutton());
			waitForvisibility(pom.fullnamefield());
			insertvalue(pom.fullnamefield(),string);
		}
		@Then("student not login with invalid Name")
		public void student_not_login_with_invalid_name() {
			System.out.println("Failed");
		}
		@When("user enter invalid mobile {string}")
		public void user_enter_invalid_mobile(String string) {
			waitForvisibility(pom.signinbutton());
			clickElement(pom.signinbutton());
			waitForvisibility(pom.mobilefield());
			insertvalue(pom.mobilefield(),string);
			scrollToElement(pom.sendonetimepasscodebutton());
			clickWithRetry(pom.sendonetimepasscodebutton());
			clickElement(pom.sendonetimepasscodebutton());
			waitForElementToClickable(pom.sendonetimepasscodebutton());
		}
		@Then("student not login with invalid mobile number")
		public void student_not_login_with_invalid_mobile_number() {
			waitForvisibility(pom.failedmessage());
			WebElement actual = pom.failedmessage();
			String actualmessage = actual.getText();
			Assert.assertTrue(actualmessage.contains("Failed to send OTP"));
		}
		@When("user enter valid mobile number and name")
		public void user_enter_valid_mobile_number_and_name() {
			waitForvisibility(pom.signinbutton());
			clickElement(pom.signinbutton());
			waitForvisibility(pom.mobilenumberfield());
			insertvalue(pom.mobilenumberfield(),"76543289765");
			insertvalue(pom.namefield(),"priya");
//			waitForvisibility(pom.Autofilldemo());
//			clickElement(pom.Autofilldemo());
			scrollToElement(pom.sendonetimepasscodebutton());
			clickWithRetry(pom.sendonetimepasscodebutton());
			clickElement(pom.sendonetimepasscodebutton());
			waitForElementToClickable(pom.sendonetimepasscodebutton());
		}
		@When("user successfully verify the OTP")
		public void user_successfully_verify_the_otp() {
			waitForvisibility(pom.sendotp());
			insertvalue(pom.sendotp(),"123456");
//			clickElement(pom.autofillotpbutton());
//			waitForElementToClickable(pom.autofillotpbutton());
			waitForvisibility(pom.verifyotpbutton());
			clickElement(pom.verifyotpbutton());
			waitForElementToClickable(pom.verifyotpbutton());
		}
		@Then("student successfully login and navigate to course explore page.")
		public void student_successfully_login_and_navigate_to_course_explore_page() {
			getapplicationTitle();
			System.err.println("user successfully login with valid credentials");
		}
	   @When("user click the courses module")
		public void user_click_the_courses_module() {

			waitForvisibility(pom.explorecoursesbutton());
			clickWithRetry(pom.explorecoursesbutton());
		}
		@Then("user successfully navigate to the courses detailed page")
		public void user_successfully_navigate_to_the_courses_detailed_page() {
			String getapplicationTitle = getapplicationTitle();
			System.err.println(getapplicationTitle);
		}
		@When("user select the discipline dropdown values one by one")
		public void user_select_the_discipline_dropdown_values_one_by_one() throws InterruptedException {
			waitForvisibility(pom.explorecoursesbutton());
			clickWithRetry(pom.explorecoursesbutton());
			waitForvisibility(pom.disciplinedropdown());
			selectByVisibleText(pom.disciplinedropdown(),"Design");
			initializeWait();
			Select select = new Select(pom.disciplinedropdown());
			System.out.println("selected values: "+select.getFirstSelectedOption().getText());
			int count =pom.disciplinedesign().size();
			System.out.println("Total courses displayed: "+ count);
			Assert.assertTrue(count > 0,"No design courses displayed");

			selectByVisibleText(pom.disciplinedropdown(),"Technology");
			initializeWait();
			Select select1 = new Select(pom.disciplinedropdown());
			System.out.println("selected values: "+select1.getFirstSelectedOption().getText());
			int count1 =pom.disciplinetechnology().size();
			System.err.println("Total courses displayed: "+ count1);
			Assert.assertTrue(count > 0,"No design courses displayed");
		}
		@Then("user successfully select the values and view the relevent courses")
		public void user_successfully_select_the_values_and_view_the_relevent_courses() {
			System.out.println("successfully verify discipline dropdown values and relevent courses");
		}
		@When("user click the delivery format checkbox one by one and verify the displayed courses")
		public void user_click_the_delivery_format_checkbox_one_by_one_and_verify_the_displayed_courses() throws InterruptedException {

			waitForvisibility(pom.explorecoursesbutton());
			clickWithRetry(pom.explorecoursesbutton());

			waitForElementToClickable(pom.clickliveonline());
			clickElement(pom.clickliveonline());
			Thread.sleep(3000);
			if(pom.getcoursestatuses().size()> 0) {
				for (WebElement status : pom.getcoursestatuses()) {
					Assert.assertEquals(status.getText().trim(),"📍 Live Online","live online filters showing incorrect courses");
				}
			}
			else {
				Assert.assertTrue(pom.emptycoursemessage().isDisplayed(),"Live online empty message not displayed");
			}
			initializeWait();
	if(pom.clickliveonline().isSelected()) {
				waitForElementToClickable(pom.clickliveonline());	
			}
	waitForElementToClickable(pom.clickpersonclassroom());
			clickElement(pom.clickpersonclassroom());
			initializeWait();
			List<WebElement> courses =pom.getcoursestatuses();
			if(courses.size()>0) {
				String status = courses.get(0).getText();
				System.out.println("course status: "+status);
			}
	else {
				waitForvisibility(pom.emptycoursemessage());
				String message =pom.emptycoursemessage().getText();
				Assert.assertTrue(message.contains("No matching programs found"),"Expected empty message was not displayed");
				System.err.println("pass : No In-person/classroom courses are available.Empty message displayed");
			}
			waitForElementToClickable(pom.clickhybrid());
			clickElement(pom.clickhybrid());
			initializeWait();
			List<WebElement> inpersoncourse =pom.getcoursestatuses();
			if(inpersoncourse.size()>0) {
				String status1 = inpersoncourse.get(0).getText();
				System.out.println("course status: "+status1);
			}
			else {
				waitForvisibility(pom.emptycoursemessage());
				String message =pom.emptycoursemessage().getText();
				Assert.assertTrue(message.contains("No matching programs found"),"Expected empty message was not displayed");
				System.err.println("pass :Hybrid courses are not available.Empty message displayed");
			}
			waitForElementToClickable(pom.clickselfpaced());
			clickElement(pom.clickselfpaced());
			initializeWait();
			List<WebElement> selfpacedcourse =pom.getcoursestatuses();
			if(selfpacedcourse.size()>0) {
				String status2 = selfpacedcourse.get(0).getText();
				System.out.println("course status: "+status2);
			}
			else {
				waitForvisibility(pom.emptycoursemessage());
				String message =pom.emptycoursemessage().getText();
				Assert.assertTrue(message.contains("No matching programs found"),"Expected empty message was not displayed");
				System.err.println("pass :In-person courses are not available.Empty message displayed");
			}
		}
		@Then("user successfully click the all delivery format checkbox")
		public void user_successfully_click_the_all_delivery_format_checkbox() {
			System.err.println("delivery format checkbox verified successfully");
		}
		@When("user click the  program duration dropdown one by one and verify the displayed courses")
		public void user_click_the_program_duration_dropdown_one_by_one_and_verify_the_displayed_courses() {
			waitForvisibility(pom.explorecoursesbutton());
			clickWithRetry(pom.explorecoursesbutton());
			String[] durations = {
					"Any Duration",
					"Under 1 month",
					"1–3 months",
					"3–6 months",
					"6–12 months",
					"1+ year"
			};
			initializeWait();
			for (String duration : durations) {
				waitForvisibility(pom.selectprogramduration());
				selectByVisibleText(pom.selectprogramduration(), duration);
				wait.until(driver -> {
					if (!pom.coursedurationcards().isEmpty()) {
						return true;
					}
					try {
						return pom.emptycoursemessage().isDisplayed();
					} catch (org.openqa.selenium.NoSuchElementException e) {
						return false;
					}
				});
				String selectedValue = new Select(
						pom.selectprogramduration()
						).getFirstSelectedOption().getText().trim();

				Assert.assertEquals(
						selectedValue,
						duration,
						"Incorrect program duration selected"
						);
				List<WebElement> courses = pom.coursedurationcards();
				if (!courses.isEmpty()) {
					System.out.println(
							"Selected Duration: " + duration
							);
					for (WebElement course : courses) {
						String actualDuration = course.getText().trim();
						Assert.assertTrue(isDurationMatching(duration, actualDuration),
								"Incorrect course duration: " + actualDuration
								+ " for selected filter: " + duration);
						System.out.println("PASS: Matching course duration: " + actualDuration);}}

				else {
					String message = pom.emptycoursemessage().getText().trim();
					Assert.assertEquals(  message,"No matching programs found","Incorrect empty message displayed");
					System.out.println("PASS: " + duration+" - No matching programs found");
				}
			}
		}
		@Then("user successfully click the all program duration dropdown")
		public void user_successfully_click_the_all_program_duration_dropdown() {
			System.out.println("successfully verify program  durationdropdown values");
		}
			@When("user click the free enrollment checkbox one by one and verify the displayed courses")
			public void user_click_the_free_enrollment_checkbox_one_by_one_and_verify_the_displayed_courses() {
				waitForvisibility(pom.explorecoursesbutton());
				clickWithRetry(pom.explorecoursesbutton());
				waitForvisibility(pom.freeenrollmentcheckbox());
			    clickWithRetry(pom.freeenrollmentcheckbox());
	System.out.println("Free Enrollment checkbox clicked");
			}
			@Then("user successfully click the free enrollment checkbox")
			public void user_successfully_click_the_free_enrollment_checkbox() {
				waitForCondition(d->!pom.freecoursescard().isEmpty());
				         List<WebElement> courses = pom.freecoursescard();
				    Assert.assertTrue( courses.size()>0, "No free courses displayed");
	for (WebElement course : courses) {
	String coursetext = course.getText().trim();
	System.out.println("course text: " + coursetext);
	Assert.assertTrue(coursetext.contains("Free"),"Incorrect course fee: " + coursetext);}
				    System.out.println("Pass: Free courses displayed and verified");
				    }
			@When("user selects {string} from the sort dropdown")
			public void user_selects_from_the_sort_dropdown(String sortValue) {
				 waitForvisibility(pom.explorecoursesbutton());
				    clickWithRetry(pom.explorecoursesbutton());
	 waitForvisibility(pom.recommendedDropdown());
	selectByVisibleText(pom.recommendedDropdown(), sortValue);
	Assert.assertEquals(new Select(pom.recommendedDropdown()).getFirstSelectedOption().getText().trim(),sortValue,
				        "Incorrect sort value selected");
	 waitForCondition(d -> !pom.getcourseratings().isEmpty());
				}
			@Then("courses should be sorted correctly by {string}")
			public void courses_should_be_sorted_correctly_by(String sortValue) {
				switch(sortValue) {
				case "Highest Rated":
					List<WebElement> ratings = pom.getcourseratings();
	 Assert.assertTrue(
			        ratings.size() > 0,
			        "No course ratings displayed"
			    );
	double previousRating = Double.MAX_VALUE;
	for (WebElement rating : ratings) {
			    	double currentRating =Double.parseDouble(rating.getText().trim());
	System.out.println("Course rating: " + currentRating);
	 Assert.assertTrue(previousRating >= currentRating,"Highest Rated sorting failed. Rating order is incorrect.");
	previousRating = currentRating; }
	System.out.println("PASS: Highest Rated courses displayed in descending order");
			break;
			case "Newest Releases":
	waitForCondition(d -> !pom.getCourseNames().isEmpty());
	List<WebElement> newestCourses = pom.getCourseNames();
	Assert.assertTrue(newestCourses.size() > 0, "No courses displayed for Newest Releases");
	System.out.println("PASS: Newest Releases courses displayed");
	break;
				    case "Price: Low to High":
					waitForCondition(d -> !pom.getCoursePrices().isEmpty());
	List<WebElement> lowToHighPrices = pom.getCoursePrices();
	Assert.assertTrue(lowToHighPrices.size() > 0,"No course prices displayed");
	 double previousLowPrice = Double.MIN_VALUE;
	for (WebElement price : lowToHighPrices) {
	String priceText = price.getText().replaceAll("[^0-9.]", "");
	double currentPrice = Double.parseDouble(priceText);
	System.out.println("Course price: " + currentPrice);
	Assert.assertTrue(previousLowPrice <= currentPrice,"Price Low to High sorting failed");
	previousLowPrice = currentPrice;}
				    break;
				    case "Price: High to Low":
					waitForCondition(d -> !pom.getCoursePrices().isEmpty());
	List<WebElement> highToLowPrices = pom.getCoursePrices();
	Assert.assertTrue(highToLowPrices.size() > 0,"No course prices displayed");
	double previousHighPrice = Double.MAX_VALUE;
	for (WebElement price : highToLowPrices) {
	String priceText = price.getText().replaceAll("[^0-9.]", "");
	double currentPrice = Double.parseDouble(priceText);
	Assert.assertTrue(previousHighPrice >= currentPrice,"Price High to Low sorting failed");
	previousHighPrice = currentPrice;
			        }
			        break;
				    case "Most Enrolled":
	 waitForCondition(d -> !pom.getCourseNames().isEmpty());
	 List<WebElement> enrolledCourses = pom.getCourseNames();
	 Assert.assertTrue(enrolledCourses.size() > 0,"No courses displayed for Most Enrolled");
	System.out.println("PASS: Most Enrolled courses displayed");
	break;
	          default:
			        Assert.fail("Invalid sort option: " + sortValue);}
				}
			@When("user searches for {string} using {string}")
	public void user_searches_for_using(String searchType, String searchText) {
				waitForvisibility(pom.explorecoursesbutton());
				clickWithRetry(pom.explorecoursesbutton());
				waitForvisibility(pom.sendsearchfield());
	pom.sendsearchfield().clear();
	insertValueAndEnter(pom.sendsearchfield(), searchText);
	System.out.println("Search Type: " + searchType);
			    System.out.println("Search Text: " + searchText);
	}
	@Then("user should verify the {string} search result for {string} using {string}")
	public void user_should_verify_the_search_result_for_using(String expectedResult, String searchType, String searchText) {
		switch (searchType) {
	case "Course":
	if (expectedResult.equalsIgnoreCase("Valid")) {
	 boolean courseFound = waitForCondition(d -> {
	for (WebElement course : pom.getCourseNames()) {
	try {String name = course.getText().trim();
	 if (name.toLowerCase().contains(searchText.toLowerCase())) {
		 return true;
	                        }

	                    } catch (StaleElementReferenceException e) {
	                        return false;
	                    }
	                }
	return false;
	            });

	            Assert.assertTrue(courseFound,
	                    "Expected course not displayed: " + searchText);

	        } else if (expectedResult.equalsIgnoreCase("Invalid")) {

	            waitForvisibility(pom.emptycoursemessage());

	            Assert.assertEquals(
	                    pom.emptycoursemessage().getText().trim(),
	                    "No matching programs found",
	                    "Invalid course search message is incorrect"
	            );
	        }

	        break;



	    case "Skill":

	        if (expectedResult.equalsIgnoreCase("Valid")) {

	            boolean relevantCourseFound = waitForCondition(d -> {

	                for (WebElement course : pom.getCourseNames()) {

	                    try {
	                        String courseName = course.getText().trim();

	                        if (courseName.toLowerCase()
	                                .contains(searchText.toLowerCase())) {
	                            return true;
	                        }

	                    } catch (StaleElementReferenceException e) {
	                        return false;
	                    }
	                }

	                return false;
	            });

	            Assert.assertTrue(
	                    relevantCourseFound,
	                    "No relevant course displayed for skill: " + searchText
	            );

	        } else if (expectedResult.equalsIgnoreCase("Invalid")) {

	            waitForvisibility(pom.emptycoursemessage());

	            Assert.assertEquals(
	                    pom.emptycoursemessage().getText().trim(),
	                    "No matching programs found",
	                    "Invalid skill search message is incorrect"
	            );
	        }

	        break;



	    case "Institute":

	        if (expectedResult.equalsIgnoreCase("Valid")) {

	          waitForCondition(d -> {

	                for (WebElement institute : pom.getInstituteName()) {

	                    try {
	                        String actualName = institute.getText().trim();

	                        if (actualName.equalsIgnoreCase(searchText)) {
	                            return true;
	                        }

	                    } catch (StaleElementReferenceException e) {
	                        return false;
	                    }
	                }

	                return !pom.getInstituteName().isEmpty()
	                        || pom.emptycoursemessage().isDisplayed();
	            });

	            boolean matchingInstitute = false;

	            for (WebElement institute : pom.getInstituteName()) {

	                try {
	                    if (institute.getText().trim()
	                            .equalsIgnoreCase(searchText)) {
	                        matchingInstitute = true;
	                        break;
	                    }

	                } catch (StaleElementReferenceException e) {
	                    matchingInstitute = false;
	                }
	            }

	            Assert.assertTrue(
	                    matchingInstitute,
	                    "BUG: Expected institute '" + searchText
	                    + "' not displayed. Check institute name in course cards."
	            );

	        } else if (expectedResult.equalsIgnoreCase("Invalid")) {

	            waitForvisibility(pom.emptycoursemessage());

	            Assert.assertEquals(
	                    pom.emptycoursemessage().getText().trim(),
	                    "No matching programs found",
	                    "Invalid institute search message is incorrect"
	            );
	        }

	        break;


	    default:

	        Assert.fail("Invalid search type: " + searchType);

	    }
	 }
	@When("user selects a paid course")
	public void user_selects_a_paid_course() {
		waitForvisibility(pom.explorecoursesbutton());
		clickWithRetry(pom.explorecoursesbutton());
	waitForCondition(d -> !pom.getCourseCards().isEmpty());
	    clickFirstPaidCourse(pom.getCourseCards());  
	}

	@When("user clicks the Enroll Immediately button")
	public void user_clicks_the_enroll_immediately_button() {
		waitForElementToClickable(pom.getEnrollImmediately());

	    clickElement(pom.getEnrollImmediately());
	}

	@Then("user should navigate to Review Your Enrollment page")
	public void user_should_navigate_to_review_your_enrollment_page() {
		waitForvisibility(pom.getReviewEnrollment());

	    String actualText = pom.getReviewEnrollment().getText().trim();

	    Assert.assertTrue(
	            actualText.contains("Review Your Enrollment"),
	            "Failed: Review Your Enrollment page is not displayed"
	    );

	    System.out.println("PASS: Review Your Enrollment page displayed");
	}
	@When("user navigates to the Offers module")
	public void user_navigates_to_the_offers_module() {
		  waitForElementToClickable(pom.getOffersModule());

		    clickElement(pom.getOffersModule());

		    waitForCondition(d -> !pom.getOfferCards().isEmpty());

		    System.out.println("Offers module opened successfully");
	}
	@Then("user should verify coupon code, percentage, active status and copy button")
	public void user_should_verify_coupon_code_percentage_active_status_and_copy_button() {
		List<WebElement> offers = pom.getOfferCards();

	    Assert.assertTrue(
	            offers.size() > 0,
	            "No offer cards displayed"
	    );

	    for (WebElement offer : offers) {

	        try {

	          
	            String percentage =
	                    pom.getOfferPercentage(offer).getText().trim();

	            Assert.assertTrue(
	                    percentage.matches("\\d+\\s*%\\s*off"),
	                    "Incorrect coupon percentage: " + percentage
	            );


	          
	            String couponCode =
	                    pom.getCouponCode(offer).getText().trim();

	            Assert.assertFalse(
	                    couponCode.isEmpty(),
	                    "Coupon code is missing"
	            );


	          
	            String status =
	                    pom.getActiveStatus(offer).getText().trim();

	            Assert.assertEquals(
	                    status,
	                    "Active",
	                    "Offer is not active"
	            );


	           
	            WebElement copyButton =
	                    pom.getCopyButton(offer);

	            Assert.assertTrue(
	                    copyButton.isDisplayed(),
	                    "Copy button is not displayed"
	            );

	            Assert.assertTrue(
	                    copyButton.isEnabled(),
	                    "Copy button is disabled"
	            );

	            System.out.println(
	                    "Verified coupon: " + couponCode
	                    + " | Percentage: " + percentage
	                    + " | Status: " + status
	            );

	        } catch (StaleElementReferenceException e) {

	            Assert.fail("Offer card changed during verification");
	        }
	    }

	    System.out.println("All offer cards verified successfully");
	}
	@When("user clicks the Copy button for an offer")
	public void user_clicks_the_copy_button_for_an_offer() {
		List<WebElement> offers = pom.getOfferCards();

	    Assert.assertTrue(
	            offers.size() > 0,
	            "No offer available to copy"
	    );

	    WebElement copyButton =
	            pom.getCopyButton(offers.get(0));

	    waitForElementToClickable(copyButton);

	    clickElement(copyButton);
	}

	@Then("user should see the coupon copied success message")
	public void user_should_see_the_coupon_copied_success_message() {
		boolean messageFound = waitForCondition(d -> {

	        try {

	            return pom.getCopyMessage().isDisplayed()
	                    && pom.getCopyMessage()
	                    .getText().trim()
	                    .equals("Copied to Clipboard!");

	        } catch (NoSuchElementException
	                | StaleElementReferenceException e) {

	            return false;
	        }
	    });

	    Assert.assertTrue(
	            messageFound,
	            "Coupon copied success message was not displayed"
	    );

	    System.out.println(
	            "PASS: Copied to Clipboard!"
	    );

	}
	@When("user selects a paid course and clicks Enroll Immediately")
	public void user_selects_a_paid_course_and_clicks_enroll_immediately() {
		waitForvisibility(pom.explorecoursesbutton());
		clickWithRetry(pom.explorecoursesbutton());
		  waitForCondition(d -> !pom.getCourseCards().isEmpty());

		    List<WebElement> courses = pom.getCourseCards();

		    Assert.assertFalse(courses.isEmpty(),
		            "No course cards found");

		    boolean paidCourseSelected = false;

		    for (WebElement course : courses) {

		        String courseText = course.getText();

		        if (courseText.contains("₹")) {

		            course.click();
		            paidCourseSelected = true;

		            System.out.println("Selected paid course: " + courseText);
		            break;
		        }
		    }

		    Assert.assertTrue(paidCourseSelected,
		            "No paid course found");

		    waitForvisibility(pom.getEnrollImmediately());
		    waitForElementToClickable(pom.getEnrollImmediately());
		    clickElement(pom.getEnrollImmediately());

		    waitForvisibility(pom.getReviewEnrollmentHeading());

	}
	@When("user enters promo coupon code {string}")
	public void user_enters_promo_coupon_code(String couponCode) {

	    waitForvisibility(pom.getPromoCouponField());

	    pom.getPromoCouponField().clear();
	    pom.getPromoCouponField().sendKeys(couponCode);

	    System.out.println("Entered promo coupon code: " + couponCode);
	}


	@When("user clicks Apply button")
	public void user_clicks_apply_button() {

	    waitForElementToClickable(pom.getApplyCouponButton());

	    clickElement(pom.getApplyCouponButton());

	    System.out.println("Clicked Apply button");
	}


	

	@Then("user should verify the Review Your Enrollment Order heading")
	public void user_should_verify_review_your_enrollment_order_heading() {

	    waitForvisibility(pom.getReviewEnrollmentHeading());

	    Assert.assertTrue(
	            pom.getReviewEnrollmentHeading().isDisplayed(),
	            "Review Your Enrollment Order heading is not displayed");

	    System.out.println("Review Your Enrollment Order heading is displayed");
	}


	@Then("user should verify course details are displayed")
	public void user_should_verify_course_details_are_displayed() {

	    waitForvisibility(pom.getCourseDetails());

	    Assert.assertTrue(
	            pom.getCourseDetails().isDisplayed(),
	            "Course details are not displayed");

	    Assert.assertFalse(
	            pom.getCourseDetails().getText().trim().isEmpty(),
	            "Course details are empty");

	    System.out.println("Course details: "
	            + pom.getCourseDetails().getText());
	}


	@Then("user should verify tuition amount is displayed")
	public void user_should_verify_tuition_amount_is_displayed() {

	    waitForvisibility(pom.getCourseTuition());

	    String tuition = pom.getCourseTuition().getText();

	    Assert.assertTrue(
	            pom.getCourseTuition().isDisplayed(),
	            "Tuition amount is not displayed");

	    Assert.assertTrue(
	            tuition.contains("₹"),
	            "Tuition amount does not contain ₹: " + tuition);

	    System.out.println("Tuition amount: " + tuition);
	}


	@Then("user should verify promo coupon code field is displayed")
	public void user_should_verify_promo_coupon_code_field_is_displayed() {

	    waitForvisibility(pom.getPromoCouponField());

	    Assert.assertTrue(
	            pom.getPromoCouponField().isDisplayed(),
	            "Promo coupon code field is not displayed");

	    System.out.println("Promo coupon code field is displayed");
	}


	@Then("user should verify Apply button is displayed and enabled")
	public void user_should_verify_apply_button_is_displayed_and_enabled() {

	    waitForvisibility(pom.getApplyCouponButton());

	    Assert.assertTrue(
	            pom.getApplyCouponButton().isDisplayed(),
	            "Apply button is not displayed");

	    Assert.assertTrue(
	            pom.getApplyCouponButton().isEnabled(),
	            "Apply button is not enabled");

	    System.out.println("Apply button is displayed and enabled");
	}


	@Then("user should verify optional referral code field is displayed")
	public void user_should_verify_optional_referral_code_field_is_displayed() {

	    waitForvisibility(pom.getReferralCodeField());

	    Assert.assertTrue(
	            pom.getReferralCodeField().isDisplayed(),
	            "Referral code field is not displayed");

	    System.out.println("Optional referral code field is displayed");
	}


	@Then("user should verify tuition subtotal")
	public void user_should_verify_tuition_subtotal() {

	    waitForvisibility(pom.getTuitionSubtotal());

	    String subtotal = pom.getTuitionSubtotal().getText();

	    Assert.assertTrue(
	            pom.getTuitionSubtotal().isDisplayed(),
	            "Tuition subtotal is not displayed");

	    Assert.assertFalse(
	            subtotal.trim().isEmpty(),
	            "Tuition subtotal is empty");

	    System.out.println("Tuition subtotal: " + subtotal);
	}


	@Then("user should verify total due")
	public void user_should_verify_total_due() {

	    waitForvisibility(pom.getTotalDue());

	    String total = pom.getTotalDue().getText();

	    Assert.assertTrue(
	            pom.getTotalDue().isDisplayed(),
	            "Total Due is not displayed");

	    Assert.assertFalse(
	            total.trim().isEmpty(),
	            "Total Due amount is empty");

	    System.out.println("Total Due: " + total);
	}


	@Then("user should verify Pay and Enroll button is displayed and enabled")
	public void user_should_verify_pay_and_enroll_button_is_displayed_and_enabled() {

	    waitForvisibility(pom.getPayAndEnrollButton());

	    Assert.assertTrue(
	            pom.getPayAndEnrollButton().isDisplayed(),
	            "Pay & Enroll button is not displayed");

	    Assert.assertTrue(
	            pom.getPayAndEnrollButton().isEnabled(),
	            "Pay & Enroll button is not enabled");

	    System.out.println("Pay & Enroll button is displayed and enabled");
	}

	}
//	@When("student select the favourite course and click add to card button")
		//	public void student_select_the_favourite_course_and_click_add_to_card_button() {
		//	}
		//
		//	@Then("student successfully click the add to card button")
		//	public void student_successfully_click_the_add_to_card_button() {
		//	}
		//
		//	@When("student select the free courses and enroll without payment.")
		//	public void student_select_the_free_courses_and_enroll_without_payment() {
		//	}
		//
		//	@Then("student successfully complete the free courses without payment.")
		//	public void student_successfully_complete_the_free_courses_without_payment() {
		//	    
		//	}
		//
		//	@Then("student successfully verify the course modules")
		//	public void student_successfully_verify_the_course_modules() {
		//	    
		//	}
		//
		//	@When("student click the Batches & schedules tab and select the scheduled batches")
		//	public void student_click_the_batches_schedules_tab_and_select_the_scheduled_batches() {
		//	    
		//	}
		//
		//	@Then("student select the active batches successfully.")
		//	public void student_select_the_active_batches_successfully() {
		//	    
		//	}
		//
		//	@When("student click the student review tab and verify displayed reviews")
		//	public void student_click_the_student_review_tab_and_verify_displayed_reviews() {
		//	    
		//	}
		//
		//	@Then("student successfully verify student review tab")
		//	public void student_successfully_verify_student_review_tab() {
		//	  
		//	}
		//
		//	@When("student Add their reviews in student tab")
		//	public void student_add_their_reviews_in_student_tab() {
		//	   
		//	}
		//
		//	@Then("verify added reviews.")
		//	public void verify_added_reviews() {
		//	   
		//	}
		//
		//	@When("student lick the offer modules and view the active coupons")
		//	public void student_lick_the_offer_modules_and_view_the_active_coupons() {
		//	    
		//	}
		//
		//	@Then("student successfully verify the Active coupons.")
		//	public void student_successfully_verify_the_active_coupons() {
		//	   
		//	}
		//
		//	@When("student click the my referral code button")
		//	public void student_click_the_my_referral_code_button() {
		//	    
		//	}
		//
		//	@Then("Student navigate to referral page successfully.")
		//	public void student_navigate_to_referral_page_successfully() {
		//	    
		//	}
		//
		//	@When("student click the verify module, enter valid certificate id and click verify credential status button")
		//	public void student_click_the_verify_module_enter_valid_certificate_id_and_click_verify_credential_status_button() {
		//	   
		//	}
		//
		//	@Then("student successfully verify the certificate.")
		//	public void student_successfully_verify_the_certificate() {
		//	   
		//	}
		//
		//	@When("student click the my learning space in top right")
		//	public void student_click_the_my_learning_space_in_top_right() {
		//	    
		//	}
		//
		//	@Then("student successfully view  the enrolled courses and completed courses.")
		//	public void student_successfully_view_the_enrolled_courses_and_completed_courses() {
		//	   
		//	}
		//
		//	@When("student click the wallet icon in top right")
		//	public void student_click_the_wallet_icon_in_top_right() {
		//	}
		//
		//	@Then("student successfully navigate to the Rewards Wallet & Referrals page.")
		//	public void student_successfully_navigate_to_the_rewards_wallet_referrals_page() {
		//	   
		//	}
		//
		//	@When("student verify the wallet balance ,status, withdrawal amount.")
		//	public void student_verify_the_wallet_balance_status_withdrawal_amount() {
		//	    
		//	}
		//
		//	@Then("student Successfully verify card")
		//	public void student_successfully_verify_card() {
		//	    
		//	}
		//
		//	@When("student verify the referral code, copy button, referred friends")
		//	public void student_verify_the_referral_code_copy_button_referred_friends() {
		//	    
		//	}
		//
		//	@When("student click up and  down arrow")
		//	public void student_click_up_and_down_arrow() {
		//	   
		//	}
		//
		//	@Then("verify the minimum withdrawal amount")
		//	public void verify_the_minimum_withdrawal_amount() {
		//	   
		//	}
		//
		//	@When("student enter the vald {string} in field")
		//	public void student_enter_the_vald_in_field(String string) {
		//	    
		//	}
		//
		//	@Then("filed accept valid UPI ID")
		//	public void filed_accept_valid_upi_id() {
		//	   
		//	}
		//
		//	@When("student click the Add to card icon in top right")
		//	public void student_click_the_add_to_card_icon_in_top_right() {
		//	   
		//	}
		//
		//	@Then("student successfully view the Add to card page")
		//	public void student_successfully_view_the_add_to_card_page() {
		//	   
		//	}
		//
		//	@When("student click  the user profile icon and click one by  one")
		//	public void student_click_the_user_profile_icon_and_click_one_by_one() {
		//	    
		//	}
		//
		//	@Then("student successfully verify navigation after clicking the pages")
		//	public void student_successfully_verify_navigation_after_clicking_the_pages() {
		//	    
		//	}
		//
		//	@When("student click the profile  icon and click my profile page")
		//	public void student_click_the_profile_icon_and_click_my_profile_page() {
		//	    
		//	}
		//
		//	@When("change profile  values and save the changes")
		//	public void change_profile_values_and_save_the_changes() {
		//	}
		//
		//	@Then("student successfully verify the profile page")
		//	public void student_successfully_verify_the_profile_page() {
		//	   
		//	}
		//
		//	@When("student click the signout button in profile page")
		//	public void student_click_the_signout_button_in_profile_page() {
		//	    
		//	}
		//
		//	@Then("student successfully signout the account")
		//	public void student_successfully_signout_the_account() {
		//	    
		//	}
		//
		//















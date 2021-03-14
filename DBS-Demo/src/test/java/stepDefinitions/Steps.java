package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.CompareCardPage;
import pageObjects.HomePage;
import utilities.ReadCardInfo;
import pageObjects.CardsPage;
import java.io.FileInputStream;
import java.io.IOException;


import java.util.List;
import java.util.Properties;

public class Steps extends BaseClass {

    public WebDriver driver;
    public HomePage hp;
    public CardsPage cp;
    public CompareCardPage comp;
    public String compareCard1;
    public String compareCard2;
    //public static Logger logger;

    @Before
    public void setup() throws IOException {

        configProp=new Properties();
        FileInputStream configPropfile=new FileInputStream("config.properties");
        configProp.load(configPropfile);


        logger = Logger.getLogger("MPHC Application");
        PropertyConfigurator.configure("log4j.properties");

        String os=configProp.getProperty("os");
        String browser=configProp.getProperty("browser");
        if (browser.equals("chrome")) {
            if(os.equals("mac"))
                System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath_mac"));
            else if(os.equals("windows")){
                System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath_windows"));
            }
        }
        else
        {
            System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
        }
        logger.info("***** Launcing Browser *****");
        driver = new ChromeDriver();

    }

    @Given("launch the browser")
    public void launch_the_browser() {

        hp = new HomePage(driver);
        cp= new CardsPage(driver);

    }

    @When("open the url")
    public void open_the_url() {
        String url=configProp.getProperty("url");
        logger.info("***** Opening URL *****");
        driver.get(url);
        System.out.println("Maximize windows");
        driver.manage().window().maximize();
    }

    @Then("click cardMenu")
    public void click_card_menu() {
        logger.info("***** Clicking the card Menu *****");
        hp.clickCardsMenu();
    }

    @Then("click creditCardMenu")
    public void click_credit_card_menu() throws InterruptedException {
        Thread.sleep(5000);
        logger.info("***** Clicking credit card Menu *****");

        cp.clickCreditCardsMenu();
    }

    @Then("select {string} and {string}")
    public void select_cards(String card1, String card2) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[text()='"+card1+"']")).click();
        driver.findElement(By.xpath("//div[text()='"+card2+"']")).click();

    }

    @Then("select compare button")
    public void select_compare_button() throws IOException {

        cp.clickCompareButton();

    }


    @Then("validate cards info {string} and {string}")
    public void validate_cards_info_and(String card1, String card2) throws IOException {
        comp=new CompareCardPage(driver);
        // validate card1
        String card1Title=comp.card1_title.getText();
        String card1bestFor=comp.card1_bestFor.getText();
        String card1cardType=comp.card1_cardType.getText();
        String card1cardMinIncome=comp.card1_min_income.getText();
        String card1cardMinIncomeForeign=comp.card1_min_income_foreign.getText();
        String card1cardFeeWaiver=comp.card1_fee_waiver.getText();

//        System.out.println("page cardName:"+card1Title);
//        System.out.println("page bestfor:"+card1bestFor);
//        System.out.println("page type:"+card1cardType);
//        System.out.println("page minIncome:"+card1cardMinIncome);
//        System.out.println("page minIncomeForeign:"+card1cardMinIncomeForeign);
//        System.out.println("page waiver:"+card1cardFeeWaiver);

        String card2Title=comp.card2_title.getText();
        String card2bestFor=comp.card2_bestFor.getText();
        String card2cardType=comp.card2_cardType.getText();
        String card2cardMinIncome=comp.card2_min_income.getText();
        String card2cardMinIncomeForeign=comp.card2_min_income_foreign.getText();
        String card2cardFeeWaiver=comp.card2_fee_waiver.getText();

//        System.out.println("page card2Name:"+card2Title);
//        System.out.println("page bestfor:"+card2bestFor);
//        System.out.println("page type:"+card2cardType);
//        System.out.println("page minIncome:"+card2cardMinIncome);
//        System.out.println("page minIncomeForeign:"+card2cardMinIncomeForeign);
//        System.out.println("page waiver:"+card2cardFeeWaiver);


        //Assertion card1
        String expected_bestfor= ReadCardInfo.getBestFor("DBS Altitude Visa Signature Card");
        String expected_cardType= ReadCardInfo.getCardType("DBS Altitude Visa Signature Card");
        String expected_cardMinIncome= ReadCardInfo.getMinIncome("DBS Altitude Visa Signature Card");
        String expected_cardMinIncomeForeign= ReadCardInfo.getMinIncomeForeign("DBS Altitude Visa Signature Card");
        String expected_FeeWaiver= ReadCardInfo.getFeeWaiver("DBS Altitude Visa Signature Card");


        Assert.assertEquals(card1Title,card1);
        Assert.assertEquals(card1bestFor,expected_bestfor);
        Assert.assertEquals(card1cardType,expected_cardType);
        Assert.assertEquals(card1cardMinIncome,expected_cardMinIncome);
        Assert.assertEquals(card1cardMinIncomeForeign,expected_cardMinIncomeForeign);
        Assert.assertEquals(card1cardFeeWaiver,expected_FeeWaiver);


        //Assertion card2
       expected_bestfor= ReadCardInfo.getBestFor("DBS Black Visa Card");
       expected_cardType= ReadCardInfo.getCardType("DBS Black Visa Card");
       expected_cardMinIncome= ReadCardInfo.getMinIncome("DBS Black Visa Card");
       expected_cardMinIncomeForeign= ReadCardInfo.getMinIncomeForeign("DBS Black Visa Card");
       expected_FeeWaiver= ReadCardInfo.getFeeWaiver("DBS Black Visa Card");

        Assert.assertEquals(card2Title,card2);
        Assert.assertEquals(card2bestFor,expected_bestfor);
        Assert.assertEquals(card2cardType,expected_cardType);
        Assert.assertEquals(card2cardMinIncome,expected_cardMinIncome);
        Assert.assertEquals(card2cardMinIncomeForeign,expected_cardMinIncomeForeign);
        Assert.assertEquals(card2cardFeeWaiver,expected_FeeWaiver);

    }


    @Then("validate card benefits {string} and {string}")
    public void validate_card_benefits_and(String card1, String card2) throws IOException {
        String[] benefits=ReadCardInfo.getCardBenefits(card1);

        List<WebElement> card1Benefits=driver.findElements(By.xpath("//div[text()='Card Benefits']/following-sibling::div[@class='sub-header'][1]/descendant::ul/li"));


        //Asset card1 Benefits
        for(int i=0; i<card1Benefits.size();i++){
            //int x=i+1;
            Assert.assertEquals(card1Benefits.get(i).getText(),benefits[++i]);
            System.out.println("Benefits:"+card1Benefits.get(i).getText());
        }


        String[] card2_benefits=ReadCardInfo.getCardBenefits(card2);
        List<WebElement>card2Benefits=driver.findElements(By.xpath("//div[@id='card1']/descendant::ul[2]/li"));

        //System.out.println("Benefit card2:"+benefits[1]);
        //Asset card1 Benefits
        for(int i=0; i<card2Benefits.size();i++){
            int x=i+1;
            Assert.assertEquals(card2Benefits.get(i).getText(),card2_benefits[x]);
            //System.out.println("Benefits:"+card2Benefits.get(i).getText());
            System.out.println("Benefit card2:"+card2_benefits[x]);
        }
    }

    @Then("Page title should be {string}")
    public void page_title_should_be(String title) {
        logger.info("***** Verifying Home Page *****");
        Assert.assertEquals(title, driver.getTitle());
    }

    @Then("close the browser")
    public void close_the_browser(){
        logger.info("***** Closing the Browser *****");
        driver.quit();
    }




















}

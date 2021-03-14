package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompareCardPage {
    public WebDriver ldriver;

    public CompareCardPage(WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(xpath = "//div[@id='card0']/descendant::div[@class='cardcontainer-header'][2]/child::div")
    public WebElement card1_title;

    @FindBy(xpath = "//div[@id='card0']/descendant::div[text()='Best For']/following-sibling::div")
    public WebElement card1_bestFor;

    @FindBy(xpath = "//div[@id='card0']/descendant::div[text()='Card Type']/following-sibling::div")
    public WebElement card1_cardType;

    @FindBy(xpath = "//div[@id='card0']/descendant::div[text()='Min Income Per Annum']/following-sibling::div")
    public WebElement card1_min_income;

    @FindBy(xpath = "//div[@id='card0']/descendant::div[text()='Min Income Per Annum Foreigners']/following-sibling::div")
    public WebElement card1_min_income_foreign;

    @FindBy(xpath = "//div[@id='card0']/descendant::div[text()='Annual Fee Waiver']/following-sibling::div")
    public WebElement card1_fee_waiver;


    //card 2
    @FindBy(xpath = "//div[@id='card1']/child::div[@class='cardcontainer-header']/child::div")
    public WebElement card2_title;

    @FindBy(xpath = "//div[@id='card1']/child::div[@class='section-seperator'][1]//child::div[@class='sub-header'][1]")
    public WebElement card2_bestFor;

    @FindBy(xpath = "//div[@id='card1']/child::div[@class='section-seperator'][1]//child::div[@class='sub-header'][2]")
    public WebElement card2_cardType;

    @FindBy(xpath = "//div[@id='card1']/child::div[@class='section-seperator'][1]//child::div[@class='sub-header'][3]")
    public WebElement card2_min_income;

    @FindBy(xpath = "//div[@id='card1']/child::div[@class='section-seperator'][1]//child::div[@class='sub-header'][4]")
    public WebElement card2_min_income_foreign;

    @FindBy(xpath = "//div[@id='card1']/child::div[@class='section-seperator'][1]//child::div[@class='sub-header'][6]")
    public WebElement card2_fee_waiver;


}

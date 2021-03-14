package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CardsPage {

    public WebDriver ldriver;

    public CardsPage(WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(xpath = "//button[text()='Refresh Tax Relief Table']")
    WebElement page;

    @FindBy(linkText = "Credit Cards")
    WebElement linkcreditCard;

    @FindBy(xpath = "//button[text()='Compare']")
    WebElement btnCompare;

    public void clickCreditCardsMenu(){

        linkcreditCard.click();
    }

    public void clickCompareButton(){

        btnCompare.click();
    }
}

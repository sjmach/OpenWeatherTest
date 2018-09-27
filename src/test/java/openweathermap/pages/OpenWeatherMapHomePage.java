package openweathermap.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;


@DefaultUrl("https://openweathermap.org")
public class OpenWeatherMapHomePage extends PageObject {

    final static Logger logger = Logger.getLogger(OpenWeatherMapHomePage.class);

    @FindBy(xpath="//button[contains(@class, 'btn btn-orange')]")
    WebElementFacade searchButton;

    @FindBy(id="nav-search")
    WebElementFacade navSearchText;

    @FindBy(id="searchform")
    WebElementFacade searchForm;

    @FindBy(xpath="//h2[contains(@class, 'widget__title')]")
    WebElementFacade widgetTitle;

    @FindBy(id="tab-main")
    WebElementFacade tabMain;

    @FindBy(id="tab-daily")
    WebElementFacade tabDaily;

    @FindBy(id="tab-hourly")
    WebElementFacade tabHourly;

    @FindBy(id="tab-chart")
    WebElementFacade tabChart;

    @FindBy(id="tab-map")
    WebElementFacade tabMap;


    public OpenWeatherMapHomePage(WebDriver driver) {
        super(driver);
    }

    public void searchFor(String term) {
        searchForm.findElement(By.tagName("input")).sendKeys(term);
        searchButton.click();
        waitABit(200);
    }

    public String getPageTitle(){
        waitForTextToAppear(widgetTitle,"Current weather and forecasts in your city");
        return getTitle();
    }

    public String getNavSearchTitle(){
      return navSearchText.getText();

    }

    public String getwidgetTitle(){
        return widgetTitle.getText();
    }

    public String getTabMainText(){
        return tabMain.getText();

    }
    public String getTabDailyText(){
        return tabDaily.getText();

    }

    public String getTabHourlyText(){
        return tabHourly.getText();

    }

    public String getTabChartText(){
        return tabChart.getText();

    }
    public String getTabMapText(){
        return tabMap.getText();

    }

}

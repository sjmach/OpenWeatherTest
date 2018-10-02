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
    private WebElementFacade searchButton;

    @FindBy(id="nav-search")
    private WebElementFacade navSearchText;

    @FindBy(id="searchform")
    private WebElementFacade searchForm;

    @FindBy(xpath="//h2[contains(@class, 'widget__title')]")
    private WebElementFacade widgetTitle;

    @FindBy(id="tab-main")
    private WebElementFacade tabMain;

    @FindBy(id="tab-daily")
    private WebElementFacade tabDaily;

    @FindBy(id="tab-hourly")
    private WebElementFacade tabHourly;

    @FindBy(id="tab-chart")
    private WebElementFacade tabChart;

    @FindBy(id="tab-map")
    private WebElementFacade tabMap;


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

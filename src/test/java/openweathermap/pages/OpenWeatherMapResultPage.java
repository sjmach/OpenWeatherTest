package openweathermap.pages;



import net.aksingh.owmjapis.model.CurrentWeather;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import openweathermap.utils.WeatherRestClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;


public class OpenWeatherMapResultPage extends PageObject {



    @FindBy(name="q")
    WebElementFacade resultquery;

    @FindBy(xpath="//div[contains(@class, 'alert alert-warning')]")
    WebElementFacade errorBox;

    @FindBy(xpath="//button[contains(@class, 'btn btn-color')]")
    WebElementFacade searchButton;

    @FindBy(id="forecast_list_ul")
    WebElementFacade tableList;

    @FindBy(id="searchform")
    WebElementFacade searchForm;


    public OpenWeatherMapResultPage(WebDriver driver) {
        super(driver);
    }

 public Boolean resultCityExists(String city) {
        waitFor(tableList);
        List<WebElement> atags = tableList.findElements(By.tagName("a"));
        for (WebElement element : atags){
           if(element.getText().contains(city)){
               return true;
           }

        }
        return false;
    }

    public String getErrorBoxText(){
        waitFor(errorBox);
        return errorBox.getText();
    }

    public String getSearchedStringText(){
        waitFor(searchForm);
        return searchForm.findElement(By.tagName("input")).getAttribute("value");
    }

    public HashMap<String,String> getWeatherAPI(String city){
        CurrentWeather cwd =null;
        HashMap<String,String> cityMap = new HashMap<String,String>();
        Integer temp =0;

        WeatherRestClient wrc=  WeatherRestClient.getInstance();
        try {
             cwd=  wrc.owmclient.currentWeatherByCityName(city);
             temp  = (int) Math.round( cwd.getMainData().getTemp()- 273.15F);
             cityMap.put("temperature", temp.toString()+"°С");
             cityMap.put("name", cwd.getCityName());
             cityMap.put("windspeed",cwd.getWindData().getSpeed().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }




        return cityMap;

    }


    public String getActualTemperatureUI(){
        waitFor(tableList);
        List<WebElement> spantags = tableList.findElements(By.tagName("span"));
        for (WebElement element : spantags){
            if(element.getText().contains("°С")){
                return element.getText();
            }
        }
        return  null;

    }

    public String getWindSpeed() {
        waitFor(tableList);
        List<WebElement> spantags = tableList.findElements(By.tagName("P"));
        for (WebElement element : spantags){
            if(element.getText().contains("°С")){
                return element.getText();
            }
        }
        return  null;
    }
}
package openweathermap.steps;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.steps.ScenarioSteps;
import openweathermap.pages.OpenWeatherMapHomePage;
import openweathermap.pages.OpenWeatherMapResultPage;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.containsString;


public class OpenWeatherMapSearchSteps extends ScenarioSteps {
    OpenWeatherMapHomePage homePage;
    OpenWeatherMapResultPage resultPage;


    @Given("I am on home page of the application")
    public void open_home_page() {
        homePage.open();
        //Validate the Title
        Assert.assertEquals("The Title is not matching of the homepage","Сurrent weather and forecast - OpenWeatherMap", homePage.getPageTitle());
        Assert.assertEquals("The Title in Nav bar Search is not matching","Weather in your city",  homePage.getNavSearchTitle());

    }


    @When("I enter a city name $city")
    public void searchFor(String city) {
        homePage.searchFor(city);
        Serenity.setSessionVariable("city").to(city);
    }

    @Then("I should get error response")
    public void assertInvalidResult() {
        Assert.assertThat("Error Text mismatch",resultPage.getErrorBoxText(), containsString("Not found"));
    }

    @Then("I should see all elements are correctly present on the homepage")
    public void see_elements_home_page() {

       Assert.assertEquals("The widget title is not correct","Current weather and forecasts in your city",  homePage.getwidgetTitle());
    }

    @Then("I should get a valid response")
    public void see_valid_response() {
        HashMap<String,String> cityData= new HashMap<String,String>();
        Assert.assertTrue("The city name is not displayed in results table ", resultPage.resultCityExists(Serenity.sessionVariableCalled("city")));
        Assert.assertEquals("The city name is not present in the search box in results",Serenity.sessionVariableCalled("city"),  resultPage.getSearchedStringText());
        cityData = resultPage.getWeatherAPI(Serenity.sessionVariableCalled("city"));
        Assert.assertThat("The Wind Speed on UI is not matching as per API",resultPage.getWindSpeed(),containsString(cityData.get("windspeed")));
        Assert.assertEquals("The Temperature displayed on UI is not matching as per API",resultPage.getActualTemperatureUI(),  cityData.get("temperature"));

    }



}

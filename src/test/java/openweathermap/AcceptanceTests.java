package openweathermap;

import net.serenitybdd.jbehave.SerenityStories;
import net.thucydides.core.annotations.WhenPageOpens;
import org.jbehave.core.io.StoryFinder;

import java.util.List;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

public class AcceptanceTests extends SerenityStories {

    @WhenPageOpens
    public void maximiseScreen() {
        getDriver().manage().window().maximize();
    }

	@Override
	public List<String> storyPaths() {
		String storyProperty = System.getProperty("story");
		List<String> stories = null;

		if(storyProperty.equals("home"))
		{
			stories = new StoryFinder().findPaths(
					codeLocationFromClass(this.getClass()), "**/homepage.story", "**/excluded*.story");
		}
		
		if(storyProperty.equals("city"))
		{
	     stories = new StoryFinder().findPaths(
	    		
	            codeLocationFromClass(this.getClass()), "**/citysearch.story", "**/excluded*.story");
		}


		if(storyProperty.equals("all"))
		{
	     stories = new StoryFinder().findPaths(codeLocationFromClass(this.getClass()), "**/*.story", "**/excluded*.story");

		}
	    return stories;
	}
 
}

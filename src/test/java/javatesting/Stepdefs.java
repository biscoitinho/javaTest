package javatesting;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.junit.Test;

class IsDXThere {
  public static String searchDx(String baseUrl) {
    WebDriver driver = new ChromeDriver();
    driver.get(baseUrl);

    //Search for keyword
    WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
    search.click();
    search.clear();
    search.sendKeys("nikon");
    search.sendKeys(Keys.RETURN);

    //Sort by
    Select oSelect = new Select(driver.findElement(By.id("s-result-sort-select")));
    oSelect.selectByVisibleText("Price: High to Low");

    //Pick 2nd element
    WebElement link = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/span[3]/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div/div/div/h2/a"));
    link.click();

    String dx = "Nikon D3X";

    //List all elements with the fraze in variable dx
    List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + dx + "')]"));
    if(list.size() > 0) {
      driver.close();
      return "Found";
    } else {
      driver.close();
      return "Not found";
    }
  }
}

public class Stepdefs {

    private String value;
    private String ret;

    @Given("Search fraze")
    public void search_fraze() {
        value = "Not found";
    }

    @When("Check list of the matches in the topic")
    public void check_list_of_the_matches_in_the_topic() {
        ret = IsDXThere.searchDx("http://www.amazon.com/");
    }

    @Then("It shouldn't be found")
    public void it_shouldn_t_be_found() {
        assertEquals(value, ret);
    }
}

package stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Weather;
import model.WeatherResponse;
import org.junit.jupiter.api.Assertions;
import requesters.WeatherRequester;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

public class WeatherStepDefs {
    private long cityId;
    private WeatherResponse response;

    @Given("city ID: {long}")
    public void set_city_id(long cityId) {

        this.cityId = cityId;
    }

    @When("we are requesting weather data")
    public void request_weather() throws JsonProcessingException {
        WeatherRequester requester = new WeatherRequester();
        response = requester.getWeatherData(cityId);
    }

    @Then("coordinates are:")
    public void check_coords(Map<String, Double> coords) {
        Assertions.assertEquals(coords.get("lon"), response.getCoord().getLon(), "Incorrect Coord lon");
        Assertions.assertEquals(coords.get("lat"), response.getCoord().getLat(), "Incorrect Coord lat");
    }

    @Then("base is")
    public void check_base() {

    }

    @Then("main is:")
    public void check_main(Map<String, Double> main) {
        Assertions.assertEquals(main.get("temp"), response.getMain().getTemp(), "Incorrect Main Temp ");
        Assertions.assertEquals(main.get("pressure"), response.getMain().getPressure(), "Incorrect Main Pressure ");
        Assertions.assertEquals(main.get("humidity"), response.getMain().getHumidity(), "Incorrect Main Humidity ");
        Assertions.assertEquals(main.get("temp_min"), response.getMain().getTemp_min(), "Incorrect Main Temp_min ");
        Assertions.assertEquals(main.get("temp_max"), response.getMain().getTemp_max(), "Incorrect Main Temp_max ");
    }

    @Then("visibility is")
    public void check_visibility() {

    }

    @Then("wind is:")
    public void check_wind(Map<String, Double> wind) {
        Assertions.assertEquals(wind.get("speed"), response.getWind().getSpeed(), "Incorrect Wind Speed ");
        Assertions.assertEquals(wind.get("deg"), response.getWind().getDeg(), "Incorrect Wind Deg ");
    }

    @Then("clouds are:")
    public void check_clouds(Map<String, Double> clouds) {
        Assertions.assertEquals(clouds.get("all"), response.getClouds().getAll(), "Incorrect Clouds All ");

    }

    @Then("dt is")
    public void check_dt() {

    }

    @Then("sys is:")
    public void check_sys(Map<String, String> params) {
        Assertions.assertEquals(Integer.parseInt(params.get("type")), response.getSys().getType(), "Wrong type");
        Assertions.assertEquals(Long.parseLong(params.get("id")), response.getSys().getId(), "Wrong  Id");
        Assertions.assertEquals(Double.parseDouble(params.get("message")), response.getSys().getMessage(), "Wrong message");
        Assertions.assertEquals(params.get("country"), response.getSys().getCountry(), "Wrong country");
        Assertions.assertEquals(Long.parseLong(params.get("sunrise")), response.getSys().getSunset(), "Wrong sunrise");
        Assertions.assertEquals(Long.parseLong(params.get("sunset")), response.getSys().getSunset(), "Wrong sunset");


        LocalDate date = Instant.ofEpochSecond(response.getSys().getSunrise()).atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(date);
        // response.getWeathers().get(0).
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(response.getSys().getSunrise()), ZoneId.systemDefault());
        System.out.println(dateTime);
    }

    @Then("id is")
    public void check_id() {

    }

    @Then("name is")
    public void check_name() {

    }

    @Then("cod is")
    public void check_cod() {

    }

    @Then("weathers are:")
    public void check_weathers(DataTable dataTable) {
        List<Map<String, String>> weathers = dataTable.asMaps();

        Assertions.assertEquals(weathers.size(), response.getWeathers().size(), "Wrong weathers");

        for (int i = 0; i < weathers.size(); i++) {
            Map<String, String> expectedWeather = weathers.get(i);
            Weather actualWeather = response.getWeathers().get(i);


            Assertions.assertEquals(Long.parseLong(expectedWeather.get("id")), actualWeather.getId(), "Wrong ...");
            Assertions.assertEquals(expectedWeather.get("main"), actualWeather.getMain(), "Wrong ...");
            Assertions.assertEquals(expectedWeather.get("description"), actualWeather.getDescription(), "Wrong ...");
            Assertions.assertEquals(expectedWeather.get("icon"), actualWeather.getIcon(), "Wrong ...");
        }
    }
}

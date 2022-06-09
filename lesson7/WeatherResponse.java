package lesson_7;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    //@JsonProperty("DailyForecasts.Date")
    private String DATE;
    //@JsonProperty("DailyForecasts.IconPhrase")
    private String WEATHER_TEXT;
   // @JsonProperty("DailyForecasts.Temperature.Minimum.Value")
    private String TEMPERATUREmin;
   // @JsonProperty("DailyForecasts.Temperature.Maximum.Value")
    private String TEMPERATUREmax;

    public WeatherResponse() {}

    public WeatherResponse(String DATE, String WEATHER_TEXT, String TEMPERATUREmin, String TEMPERATUREmax) {
        this.DATE = DATE;
        this.WEATHER_TEXT = WEATHER_TEXT;
        this.TEMPERATUREmin = TEMPERATUREmin;
        this.TEMPERATUREmax = TEMPERATUREmax;
    }

    @Override
    public String toString() {
        return "на дату " + DATE +
                ",\nописание: " + WEATHER_TEXT +
                ",\nминимальная температура " + TEMPERATUREmin + " C" +
                ",\nмаксимальная температура " + TEMPERATUREmax + " C.";
    }
}

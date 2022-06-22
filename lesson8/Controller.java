package lesson_8.Weather;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private WeatherModel model = new AccuweatherModel();
    private Map<Integer, Period> variants = new HashMap<>();

    public Controller() {
        variants.put(1, Period.NOW);
        variants.put(5, Period.FIVE_DAYS);
        variants.put(2, Period.DB);
    }

    public void getWeather(String userInput, String city) throws IOException {
        Integer command = Integer.parseInt(userInput);

        switch (variants.get(command)) {
            case NOW:
                model.getWeather(city, Period.NOW);
                break;
            case FIVE_DAYS:
                model.getWeather(city, Period.FIVE_DAYS);
            case DB:
                model.getSavedToDBWeather();
        }
    }
}

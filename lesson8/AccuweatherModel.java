package lesson_8.Weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import lesson_8.Weather.entity.Weather;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AccuweatherModel implements WeatherModel{
    private static final String PROTOKOL = "http";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAYS = "5day";
    private static final String API_KEY = "Z3eC2U13kNTAKtl8cScNo5eyyhJzDAUf";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LANGUAGE_QUERY_PARAM = "language";
    private static final String LANGUAGE = "ru";
    private static final String METRIC_QUERY_PARAM = "metric";
    private static final String METRIC_C = "true";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final DataBaseRepositiry dataBaseRepositiry = new DataBaseRepositiry();

    @Override
    public void getWeather(String selectedCity, Period period) throws IOException {
        System.out.println("Погода в городе " + selectedCity);
        switch (period) {
            case NOW:
                //http://dataservice.accuweather.com/forecasts/v1/daily/1day/{}
                 HttpUrl httpUrl1 = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter(LANGUAGE_QUERY_PARAM, LANGUAGE)
                        .addQueryParameter(METRIC_QUERY_PARAM, METRIC_C)
                        .build();

                Request request = new Request.Builder()
                        .url(httpUrl1)
                        .build();

                Response oneDayForecastResponse = okHttpClient.newCall(request).execute();
                String weatherResponse1 = oneDayForecastResponse.body().string();
                WeatherResponse weather1 = new WeatherResponse(
                        objectMapper.readTree(weatherResponse1).at("/DailyForecasts").get(0).at("/Date").asText().substring(0, 10),
                        objectMapper.readTree(weatherResponse1).at("/DailyForecasts").get(0).at("/Day/IconPhrase").asText(),
                        objectMapper.readTree(weatherResponse1).at("/DailyForecasts").get(0).at("/Temperature/Minimum/Value").asText(),
                        objectMapper.readTree(weatherResponse1).at("/DailyForecasts").get(0).at("/Temperature/Maximum/Value").asText()
                );
                System.out.println(weather1);

                try {
                    dataBaseRepositiry.saveWeatherToDatabase(new Weather("Yekaterinburg", "22-06-2022", 19.0));
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                break;

            case FIVE_DAYS:
                //http://dataservice.accuweather.com/forecasts/v1/daily/5day/{}
                HttpUrl httpUrl5 = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIVE_DAYS)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter(LANGUAGE_QUERY_PARAM, LANGUAGE)
                        .addQueryParameter(METRIC_QUERY_PARAM, METRIC_C)
                        .build();

                request = new Request.Builder()
                        .url(httpUrl5)
                        .build();

                Response fiveDayForecastResponse = okHttpClient.newCall(request).execute();
                String weatherResponse5 = fiveDayForecastResponse.body().string();
                WeatherResponse[] weather5 = new WeatherResponse[5];
                for (int i = 0; i < 5; i++) {
                    weather5[i] = new WeatherResponse(
                            objectMapper.readTree(weatherResponse5).at("/DailyForecasts").get(i).at("/Date").asText().substring(0, 10),
                            objectMapper.readTree(weatherResponse5).at("/DailyForecasts").get(i).at("/Day/IconPhrase").asText(),
                            objectMapper.readTree(weatherResponse5).at("/DailyForecasts").get(i).at("/Temperature/Minimum/Value").asText(),
                            objectMapper.readTree(weatherResponse5).at("/DailyForecasts").get(i).at("/Temperature/Maximum/Value").asText()
                    );
                    System.out.println(weather5[i] + "\n");
                }
                break;
        }
    }

    @Override
    public List<Weather> getSavedToDBWeather() {
        return dataBaseRepositiry.getSavedToDatabaseWeather();
    }

    private String detectCityKey(String selectCity) throws IOException {
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter("q", selectCity)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        Response cityCodeResponse = okHttpClient.newCall(request).execute();
        String cityCodeString = cityCodeResponse.body().string();
        String cityKey = objectMapper.readTree(cityCodeString).get(0).at("/Key").asText();
        return cityKey;
    }
}

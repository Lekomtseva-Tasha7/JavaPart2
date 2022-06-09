package lesson_7;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccuweatherModel implements WeatherModel {
    private static final String PROTOKOL = "http";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAYS = "5day";
    private static final String API_KEY = "QNdYlQNp9YPUd0PDuj5POfyfuUR9xwFS";
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

    @Override
    public void getWeather(String selectedCity, Period period) throws IOException {
        String DATE;
        String WEATHER_TEXT;
        String TEMPERATUREmin;
        String TEMPERATUREmax;
        String weatherResponse;
        System.out.println("Погода в городе " + selectedCity);
        switch (period) {
            case NOW:
                //http://dataservice.accuweather.com/forecasts/v1/daily/1day/{}
                 HttpUrl httpUrl = new HttpUrl.Builder()
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
                        .url(httpUrl)
                        .build();

                Response oneDayForecastResponse = okHttpClient.newCall(request).execute();
                weatherResponse = oneDayForecastResponse.body().string();
                //WeatherResponse weather1 = objectMapper.readValue(weatherResponse, WeatherResponse.class);
                DATE = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).at("/Date").asText();
                WEATHER_TEXT = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).at("/Day/IconPhrase").asText();
                TEMPERATUREmin = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).at("/Temperature/Minimum/Value").asText();
                TEMPERATUREmax= objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).at("/Temperature/Maximum/Value").asText();
                WeatherResponse weather = new WeatherResponse(DATE, WEATHER_TEXT, TEMPERATUREmin, TEMPERATUREmax);
                System.out.println(weather);
                break;

            case FIVE_DAYS:
                //http://dataservice.accuweather.com/forecasts/v1/daily/5day/{}
                httpUrl = new HttpUrl.Builder()
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
                        .url(httpUrl)
                        .build();

                Response fiveDayForecastResponse = okHttpClient.newCall(request).execute();
                weatherResponse = fiveDayForecastResponse.body().string();
                //List<WeatherResponse> weather5 = objectMapper.readValue(weatherResponse, new TypeReference<List<WeatherResponse>>() {});
                System.out.println(weatherResponse);
                break;
        }
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

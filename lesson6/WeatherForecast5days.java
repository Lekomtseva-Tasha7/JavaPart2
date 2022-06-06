package lesson_6;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class WeatherForecast5days {
    public static void main(String[] args) throws IOException {
        //Экземпляр класса OkHttpClient выполняет всю работу по созданию и отправке запросов
        OkHttpClient client = new OkHttpClient();

        //http://dataservice.accuweather.com/forecasts/v1/daily/5day/295863 -> Екатеринбург - 5 дней
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("forecasts")
                .addPathSegment("v1")
                .addPathSegment("daily")
                .addPathSegment("5day")
                .addPathSegment("295863")
                .addQueryParameter("apikey", "Z3eC2U13kNTAKtl8cScNo5eyyhJzDAUf")
                .addQueryParameter("metric", "true") // перевод в град. Цельсия
                .build();

        Request request = new Request.Builder() // Запрос по собранной ссылке
                .url(httpUrl)
                .build();

        Response fiveDaysWeatherResponse = client.newCall(request).execute(); // Ответ на запрос от сервера

        String weatherResponse = fiveDaysWeatherResponse.body().string();
        System.out.println(weatherResponse);
    }
}

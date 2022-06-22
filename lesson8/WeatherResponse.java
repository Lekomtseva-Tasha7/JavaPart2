package lesson_8.Weather;

public class WeatherResponse {
    private String date;
    private String weather_text;
    private String temperature_min;
    private String temperature_max;

    public WeatherResponse(String date, String weather_text, String temperature_min, String temperature_max) {
        this.date = date;
        this.weather_text = weather_text;
        this.temperature_min = temperature_min;
        this.temperature_max = temperature_max;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeather_text() {
        return weather_text;
    }

    public void setWeather_text(String weather_text) {
        this.weather_text = weather_text;
    }

    public String getTemperature_min() {
        return temperature_min;
    }

    public void setTemperature_min(String temperature_min) {
        this.temperature_min = temperature_min;
    }

    public String getTemperature_max() {
        return temperature_max;
    }

    public void setTemperature_max(String temperature_max) {
        this.temperature_max = temperature_max;
    }

    @Override
    public String toString() {
        return "На дату " + date +
                ",\nОжидается: " + weather_text +
                ",\nМинимальная температура " + temperature_min + " C" +
                ",\nМаксимальная температура " + temperature_max + " C.";
    }
}

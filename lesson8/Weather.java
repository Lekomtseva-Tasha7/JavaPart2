package lesson_8.Weather.entity;

public class Weather {
    private String city;
    private String localdate;
    private Double temperature;

    public Weather(String city, String localdate, Double temperature) {
        this.city = city;
        this.localdate = localdate;
        this.temperature = temperature;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocaldate() {
        return localdate;
    }

    public void setLocaldate(String localdate) {
        this.localdate = localdate;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", localdate='" + localdate + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}

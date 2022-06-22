package lesson_8.Weather;

import lesson_8.Weather.entity.Weather;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseRepositiry {
    public static final String DB_PATH = "jdbc:sqlite:geekbrains.db";
    private String insertWeather = "insert into weather (city, localdate, temperature) values (?, ?, ?)";
    private String getWeather = "select * from weather";

    //Блок кода
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean saveWeatherToDatabase(Weather weather) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
            saveWeather.setString(1, weather.getCity());
            saveWeather.setString(2, weather.getLocaldate());
            saveWeather.setDouble(3, weather.getTemperature());

            return saveWeather.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException("Сохранение в БД произошло с ошибкой!");
    }

   // public boolean saveWeatherToDatabase(List<Weather> weather){
   //     return
   // }


    public List<Weather> getSavedToDatabaseWeather() {
        List<Weather> weatherList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            Statement statement = connection.createStatement();
            ResultSet weatherResult = statement.executeQuery(getWeather);

            while (weatherResult.next()){
                System.out.print(weatherResult.getInt("id"));
                System.out.println(" ");
                System.out.print(weatherResult.getString("city"));
                System.out.println(" ");
                System.out.print(weatherResult.getString("localdate"));
                System.out.println(" ");
                System.out.print(weatherResult.getDouble("temperature"));
                System.out.println(" ");

                weatherList.add(new Weather(weatherResult.getString("city"),
                        weatherResult.getString("localdate"),
                        weatherResult.getDouble("temperature")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weatherList;
    }
}

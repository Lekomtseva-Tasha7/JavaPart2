package lesson_8;

import java.sql.*;

public class SQLExamples {
    public static void main(String[] args){
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:geekbrains.db");
            Statement statement = connection.createStatement();
            statement.executeUpdate("update faculties set name = 'IT' where id = 1");
            ResultSet resultSet = statement.executeQuery("select * from students");
            while (resultSet.next()){
                System.out.print(resultSet.getInt("id"));
                System.out.print(" ");
                System.out.println(resultSet.getString("name"));
            }

            //Long time = System.currentTimeMillis();
            //for (int i = 1; i <= 10000; i++) {
            //    statement.executeUpdate(String.format("insert into students (name, score, fac_id) values ('%s', %d, %d)",
            //            "student" + i, i, i));
            //}
            //System.out.println("Время выполнения с автокоммитом: " + (System.currentTimeMillis() - time));
            //
            //time = System.currentTimeMillis();
            //connection.setAutoCommit(false);
            //for (int i = 10001; i < 20000; i++) {
            //    statement.executeUpdate(String.format("insert into students (name, score, fac_id) values ('%s', %d, %d)",
            //            "student" + i, i, i));
            //}
            //connection.commit();
            //System.out.println("Время выполнения без автокоммита: " + (System.currentTimeMillis() - time));

            PreparedStatement preparedStatement = connection.prepareStatement("insert into students (name, score, fac_id) values (?, ?, ?)");
            connection.setAutoCommit(false);
            preparedStatement.setString(1, "Олег");
            preparedStatement.setInt(2, 10);
            preparedStatement.setInt(3, 12);
            preparedStatement.addBatch();
            preparedStatement.setString(1, "Anton");
            preparedStatement.setInt(2, 10);
            preparedStatement.setInt(3, 12);
            preparedStatement.addBatch();
            preparedStatement.setString(1, "Anton");
            preparedStatement.setInt(2, 10);
            preparedStatement.setInt(3, 12);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

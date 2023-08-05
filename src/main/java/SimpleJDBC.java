import java.sql.*;
import java.util.*;

public class SimpleJDBC {
    public static void main (String arg[]) throws SQLException {
        /*
        Need to have the details on the sql database, url, username, password
         */
        String url = "jdbc:mysql://localhost:3306/first";
        String userName = "root";
        String password = "12345678";

        /*
        need to initiate the connection using the sql package.
         */
        Connection connection = DriverManager.getConnection(url, userName, password);
        if(connection != null) {
            System.out.println("connection has established successfully");
        }

        /*
        creating the DB query and executing the same to retrive the data
         */

        System.out.println("####### Static query/Statement implementation #########");

        Statement statement = connection.createStatement();
        String query = "SELECT * FROM first.booking ";
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            System.out.print("booking id is " + resultSet.getInt("booking_id") );
            System.out.print(" Aadhar number is " + resultSet.getString("aadhar_num"));
            System.out.print(" booking is for " + resultSet.getDate("booked_on"));
            System.out.print(" booked on " + resultSet.getDate("from_date"));
            System.out.print(" room numbers are " + resultSet.getString("room_numbers"));
            System.out.println();
        }
        statement.close();

        System.out.println("####### prepared statement implementation #########");

        String preQuery = "SELECT * FROM first.booking where booking_id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(preQuery);
        preparedStatement.setString(1, "1");
        ResultSet resultSet1 = preparedStatement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet1.getMetaData();
        while (resultSet1.next()){
            System.out.print("booking id is " + resultSet1.getInt("booking_id") );
            System.out.print(" Aadhar number is " + resultSet1.getString("aadhar_num"));
            System.out.print(" booking is for " + resultSet1.getDate("booked_on"));
            System.out.print(" booked on " + resultSet1.getDate("from_date"));
            System.out.print(" room numbers are " + resultSet1.getString("room_numbers"));
            System.out.println();
        }

        /**
         * Exploring Metadata of result set
         */
        System.out.println("######## Exploring metadata ############");

        System.out.println("The no of columns in the table is:" + resultSetMetaData.getColumnCount());
        System.out.println("The first two colums are "+ resultSetMetaData.getColumnName(1) +" "+ resultSetMetaData.getColumnName(2));

        preparedStatement.close();

    }
}

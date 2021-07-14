/*
* Author : J Hencil Peter
* Purpose : Insert sample records to mysql table (only for testing)
* */
package kafkamysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class simplemysqldatainsert {
    static final String MySQL_DB_URL = "jdbc:mysql://localhost/testdb";
    static final String USERNAME = "user1";
    static final String PASSWORD = "password";

    public static void main(String[] args) {


        try {
            Class.forName("com.mysql.jdbc.Driver");

            //Create connection object
            Connection conn = DriverManager.getConnection(MySQL_DB_URL, USERNAME, PASSWORD);
            //statement object
            Statement stmt = conn.createStatement();

            System.out.println("Inserting records into the table...");

            //insert first record
            String sql = "insert into employee values(1, 'Jerry');\n";
            stmt.executeUpdate(sql);

            //insert second record
            sql = "insert into employee values(2, 'Nobel');\n";
            stmt.executeUpdate(sql);
            //insert third record
            sql = "insert into employee values(3, 'Will');\n";
            stmt.executeUpdate(sql);

            //insert fourth record
            sql = "insert into employee values(4, 'Tom');\n";
            stmt.executeUpdate(sql);

            //insert fifth record
            sql = "insert into employee values(5, 'Jeff');\n";
            stmt.executeUpdate(sql);

            //insert sixth record
            sql = "insert into employee values(6, 'Allen');\n";
            stmt.executeUpdate(sql);

            //insert seventh record
            sql = "insert into employee values(7, 'Niles');\n";
            stmt.executeUpdate(sql);

            System.out.println("Inserted records into the table...");

        } catch (SQLException e) {
            System.out.println("SQL Exception raised : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception raised : " + e.getMessage());
        }

    }
}

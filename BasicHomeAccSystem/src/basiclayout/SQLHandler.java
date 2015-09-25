package basiclayout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLHandler {
    
        // JDBC driver name and database URL
        private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        private static final String database_url = "jdbc:mysql://localhost/home_acc_system";

        // 1. Database User name & Password
        private static final String username = "root";
        private static final String password = "";

        static Connection connection;
        static Statement statement1, statement2;
        static ResultSet resultset1, resultset2;

        String tableC = "categorymaster", tableE = "expenseincomemaster";

        public static void initialize_connection() throws ClassNotFoundException, SQLException{
                    connection = null;
                    statement1 = statement2 = null;
                    resultset1 = resultset2 = null;
                
                    // 2. Register JDBC driver
                    Class.forName("com.mysql.jdbc.Driver");

                    // 3. Open a connection
                    connection = DriverManager.getConnection(database_url, username, password);

                    // 4. Execute a query
                    statement1 = connection.createStatement();
                    statement2 = connection.createStatement();
        }


        public static void close_connection(){
            try {
                    if (statement1 != null || statement2 != null) {
                        statement1.close();
                        statement2.close();
                    }
                } catch (SQLException e) {
                }
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException se) {
                    System.out.println(se.getMessage());
                }
        }



    }

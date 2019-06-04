package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Neo4jConnection {
    private Connection connection;
    public Connection getConnection(String uri, String username, String password){
        try {
            this.connection = DriverManager.getConnection(uri, username, password);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return this.connection;
    }
    public Connection getConnection(String uri){
        try {
            this.connection = DriverManager.getConnection(uri);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return this.connection;
    }
}
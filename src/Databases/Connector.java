package Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {//连接到petscoffee数据库,如果不存在则创建该数据库
    private Connection connection;

    public Connector(String user, String password) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            System.out.println("连接数据库");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/petscoffee", user, password);
            this.connection =conn;
        } catch (Exception e) {
            try {
                System.out.println("数据库连接失败，创建petscoffee数据库");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", user, password);
                conn.createStatement().execute("CREATE DATABASE petscoffee;");//创建数据库
                conn.close();
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/petscoffee", user, password);//创建后连接到数据库
                conn.createStatement().execute("CREATE TABLE petscoffee(id INT AUTO_INCREMENT PRIMARY KEY,day INT,time INT,money FLOAT,pets LONGBLOB,bag LONGBLOB,date VARCHAR(32));");
                this.connection =conn;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println("数据库创建失败");
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

package base;


import java.sql.*;

public class DBConnection {

    private static Connection connection = null;
    private String url = "";
    private static Statement statement;

    public DBConnection(){

        url  = "jdbc:sqlite:db/dbs3.db";
        try {
            if(connection == null){
                connection = DriverManager.getConnection(url);
                System.out.println("Opened database successfully");
                statement = connection.createStatement();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Statement getStatement(){
        return statement;

    }

    public Boolean setConnection(){

        return true;
    }
}

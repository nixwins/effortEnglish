package base;


import java.sql.*;

public class DBConnection {

    private Connection connection = null;
    private String url = "";
    private Statement statement;

    public DBConnection(){
        url  = "jdbc:sqlite:db/dbs3.db";
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Opened database successfully");
            statement = connection.createStatement();
           // statement.executeQuery("SELECT * FROM phrases;");
//            ResultSet rs;
//
//                rs  = statement.executeQuery("SELECT * FROM phrases;");
//                while ( rs.next() ) {
//
//                    int id = rs.getInt("id");
//                    System.out.println( "ID = " + id );
//                    System.out.println();
//                }

        }catch (Exception e){

        }

    }

    public Statement getStatement(){
        return statement;

    }

    public Boolean setConnection(){

        return true;
    }
}

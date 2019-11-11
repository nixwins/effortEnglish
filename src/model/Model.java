package model;

import base.DBConnection;

import java.sql.ResultSet;
import java.sql.Statement;

public class Model {

    public static Statement statement;

    public Model(){

       statement = new DBConnection().getStatement();

    }
    public Statement getStmt(){
        return statement;
    }
}

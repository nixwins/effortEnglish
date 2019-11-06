package model;

import base.DBConnection;

import java.sql.Statement;

public class Model {

    public Statement stmt;

    public Model(){
       stmt = new DBConnection().getStatement();
    }
    public Statement getStmt(){
        return stmt;
    }
}

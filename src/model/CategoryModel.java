package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryModel extends Model {

    private String categoryName;
    private int id;

    public CategoryModel(){ }

    public CategoryModel(int id, String categoryName){
        this.categoryName = categoryName;
        this.id = id;
    }

    public ResultSet getAllCategory(){

        ResultSet rs = null;

        try{

            rs  = statement.executeQuery("SELECT * FROM category;");

        } catch (Exception e){

            e.printStackTrace();
        }

        return rs;
    }
}

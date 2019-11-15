package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhraseModel extends Model {

    public String getAll(int catID){

        ResultSet rs;
        String str = "";

        String sql = "SELECT phrase FROM phrases WHERE cat_id="+catID;

        try {
            rs = statement.executeQuery(sql);
           // str = rs.getString("phrase");
            StringBuilder stringBuilder  = new StringBuilder();
            while(rs.next()){
                stringBuilder.append(rs.getString("phrase"));
                stringBuilder.append(" ");
            }
            str = stringBuilder.toString();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  str;
    }
}

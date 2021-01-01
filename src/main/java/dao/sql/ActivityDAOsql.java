package dao.sql;

import bean.ActivityBean;
import dao.ActivityDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ActivityDAOsql implements ActivityDAO {

    DAOFactorySQL factory;

    public ActivityDAOsql(DAOFactorySQL factorySQL){
        factory = factorySQL;
    }

    @Override
    public ActivityBean loadActivity(int id, int id_user, int id_location){
        String sql = "SELECT * FROM activity WHERE id = ? and id_user = ? and id_location = ?;";
        PreparedStatement pst = null;
        ActivityBean activity = new ActivityBean();
        activity.setId(-1);
        activity.setId_user(-1);
        activity.setId_user(-1);
        try {
            pst = factory.getConnexion().prepareStatement(sql);

            pst.setInt(1,id);
            pst.setInt(2,id_user);
            pst.setInt(3,id_location);

            ResultSet result = pst.executeQuery();

            if(result.next()){
                activity.setId(result.getInt("id"));
                activity.setId_user(result.getInt("id_user"));
                activity.setId_location(result.getInt("id_location"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return activity;
    }

    @Override
    public ActivityBean[] loadAll(int id) {

        ArrayList<ActivityBean> activityList = new ArrayList<>();

        try {
            Statement st = factory.getConnexion().createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM ACTIVITY WHERE id_user = id;");

            ActivityBean activity = null;
            while (result.next()){
                activity = new ActivityBean();
                activity.setId(result.getInt("id"));
                activity.setId_user(result.getInt("id_user"));
                activity.setId_location(result.getInt("id_location"));
                activity.setName(result.getString("name"));
                activity.setDate(result.getString("date"));
                activity.setStart_at(result.getString("start_at"));
                activity.setEnd_at(result.getString("end_at"));

            }
            result.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ActivityBean[] activities = activityList.toArray(new ActivityBean[0]);

        return activities;
    }

    @Override
    public boolean exist(String date, int id){
        String sql = "SELECT * FROM activity where (date = ? and id = ?)";
        PreparedStatement pst = null;
        boolean exist = false;
        try {
            pst = factory.getConnexion().prepareStatement(sql);

            pst.setInt(1,id);
            pst.setString(2,date);

            ResultSet result = pst.executeQuery();

            if(result.next()){
                exist = true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return exist;
    }

    @Override
    public void createActivity(ActivityBean newActivity){
        if(!exist(newActivity.getDate(),newActivity.getId()))
            try {
                String sql = "INSERT INTO ACTIVITY (id,id_user,id_location) VALUES (?,?,?)";
                PreparedStatement pst = factory.getConnexion().prepareStatement(sql);

                pst.setInt(1,newActivity.getId());
                pst.setInt(2,newActivity.getId_user());
                pst.setInt(3,newActivity.getId_location());

                pst.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

}

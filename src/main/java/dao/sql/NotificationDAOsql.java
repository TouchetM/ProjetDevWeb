package dao.sql;

import bean.LocationBean;
import bean.NotificationBean;
import bean.UserBean;
import dao.LocationDAO;
import dao.NotificationDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NotificationDAOsql implements NotificationDAO {

    DAOFactorySQL factory;

    public NotificationDAOsql(DAOFactorySQL factorySQL){
        factory = factorySQL;
    }

    @Override
    public NotificationBean loadNotification(int id){
        String sql = "SELECT * FROM notification WHERE id = ?;";
        PreparedStatement pst = null;
        NotificationBean notification = new NotificationBean();
        notification.setId(-1);
        try {
            pst = factory.getConnexion().prepareStatement(sql);

            pst.setInt(1,id);

            ResultSet result = pst.executeQuery();

            if(result.next()){
                notification.setId(result.getInt("id"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return notification;
    }

    @Override
    public NotificationBean[] loadAll(){

        ArrayList<NotificationBean> notificationList = new ArrayList<>();

        try {
            Statement st = factory.getConnexion().createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM NOTIFICATION;");

            NotificationBean notification = null;
            while (result.next()){
                notification = new NotificationBean();
                notification.setId(result.getInt("id"));
                notification.setMessage(result.getString("message"));
                notification.setCheck(result.getBoolean("check"));

            }
            result.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        NotificationBean[] notifications = notificationList.toArray(new NotificationBean[0]);

        return notifications;
    }

    @Override
    public NotificationBean[] userNotification(UserBean user) {
        ArrayList<NotificationBean> notifications = new ArrayList<>();

        try {
            String sql ="SELECT id_notification_FK from user_notification where " +
                    "id_user_FK = ?";

            PreparedStatement pst = factory.getConnexion().prepareStatement(sql);

            pst.setInt(1,user.getId());

            ResultSet result = pst.executeQuery();
            NotificationBean notif = null;
            while (result.next()){
                notif = loadNotification(result.getInt("id_notification_FK"));
                notifications.add(notif);
            }
            result.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return notifications.toArray(new NotificationBean[0]);
    }

    @Override
    public boolean exist(int id){
        String sql = "SELECT * FROM notification where id = ?";
        PreparedStatement pst = null;
        boolean exist = false;
        try {
            pst = factory.getConnexion().prepareStatement(sql);

            pst.setInt(1,id);

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
    public void createNotification(NotificationBean newNotification){
        if(!exist(newNotification.getId()))
            try {
                String sql = "INSERT INTO NOTIFICATION (id,message,check) VALUES (?,?,?)";
                PreparedStatement pst = factory.getConnexion().prepareStatement(sql);

                pst.setInt(1,newNotification.getId());
                pst.setString(2,newNotification.getMessage());
                pst.setBoolean(3,newNotification.getCheck());

                pst.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }
}

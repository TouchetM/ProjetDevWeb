package dao.sql;

import bean.UserBean;
import dao.UserDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/** @author - Maxime Choné **/

public class UserDAOsql implements UserDAO {

    private DAOFactorySQL factory;

    public UserDAOsql(DAOFactorySQL factory){
        this.factory = factory;
    }

    @Override
    public UserBean load(String mail) {

        String sql = "SELECT * FROM USER WHERE mail=?;";
        PreparedStatement pst = null;
        UserBean user = new UserBean();
        user.setId(-1);
        try {
            pst = factory.getConnexion().prepareStatement(sql);

            pst.setString(1,mail);

            ResultSet result = pst.executeQuery();

            if(result.next()){
                user.setId(result.getInt("id"));
                user.setRole(result.getString("role"));
                user.setMail(result.getString("mail"));
                user.setPassword(result.getString("password"));
                user.setFirstName(result.getString("firstname"));
                user.setLastName(result.getString("lastname"));
                user.setBirthdate(result.getString("birthdate"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;    }

    @Override
    public UserBean load(int id) {
        String sql = "SELECT * FROM USER WHERE id=?;";
        PreparedStatement pst = null;
        UserBean user = new UserBean();
        user.setId(-1);
        try {
            pst = factory.getConnexion().prepareStatement(sql);

            pst.setInt(1,id);

            ResultSet result = pst.executeQuery();

            if(result.next()){
                user.setId(result.getInt("id"));
                user.setRole(result.getString("role"));
                user.setMail(result.getString("mail"));
                user.setPassword(result.getString("password"));
                user.setFirstName(result.getString("firstname"));
                user.setLastName(result.getString("lastname"));
                user.setBirthdate(result.getString("birthdate"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean userMatch(String mail, String password) {
        PreparedStatement pst = null;
        boolean matching = false;
        try {
            String sql = "SELECT mail FROM USER where mail=? and password=?";
            pst = factory.getConnexion().prepareStatement(sql);
            pst.setString(1,mail);
            pst.setString(2,password);
            ResultSet result = pst.executeQuery();

            if(result.next())
                matching = true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return matching;
    }

    @Override
    public UserBean[] loadAll() {

        ArrayList<UserBean> usersList = new ArrayList<>();

        try {
            Statement st = factory.getConnexion().createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM USER;");

            UserBean user = null;
            while (result.next()){
                user = new UserBean();
                user.setId(result.getInt("id"));
                user.setRole(result.getString("role"));
                user.setMail(result.getString("mail"));
                user.setPassword(result.getString("password"));
                user.setFirstName(result.getString("firstname"));
                user.setLastName(result.getString("lastname"));
                user.setBirthdate(result.getString("birthdate"));
                usersList.add(user);

            }
            result.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        UserBean[] users = usersList.toArray(new UserBean[0]);

        return users;
    }

    @Override
    public void save(UserBean user) {
        try {
            String sql = "INSERT INTO USER (mail,password,firstname,lastname,birthdate,role) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = factory.getConnexion().prepareStatement(sql);

            pst.setString(1,user.getMail());
            pst.setString(2,user.getPassword());
            pst.setString(3, user.getFirstName());
            pst.setString(4,user.getLastName());
            pst.setString(5,user.getBirthdate());
            pst.setString(6,user.getRole());


            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    @Override
    public void saveModification(String mail, UserBean user) {

    }

    @Override
    public boolean exist(String mail) {
        PreparedStatement pst = null;
        boolean exist = false;
        try {
            String sql = "SELECT mail FROM USER where mail=?";
            pst = factory.getConnexion().prepareStatement(sql);
            pst.setString(1,mail);

            ResultSet result = pst.executeQuery();

            if(result.next())
                exist = true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return exist;
    }

    @Override
    public UserBean[] friendList(UserBean user) {
        ArrayList<UserBean> friendList = new ArrayList<>();

        try {
            String sql ="SELECT * from user where " +
                    "user.id = ( SELECT id_user_1 from friend where " +
                    "friend.id_user_2 = ? and request_state = 'accepted') " +
                    "or " +
                    "user.id = ( SELECT id_user_2 from friend where " +
                    "friend.id_user_1 = ? and request_state = 'accepted');";
            PreparedStatement pst = factory.getConnexion().prepareStatement(sql);

            pst.setInt(1,user.getId());
            pst.setInt(2,user.getId());

            ResultSet result = pst.executeQuery();

            UserBean friend = null;
            while (result.next()){
                friend = new UserBean();
                friend.setId(result.getInt("id"));
                friend.setRole(result.getString("role"));
                friend.setMail(result.getString("mail"));
                friend.setPassword(result.getString("password"));
                friend.setFirstName(result.getString("firstname"));
                friend.setLastName(result.getString("lastname"));
                friend.setBirthdate(result.getString("birthdate"));
                friendList.add(friend);

            }
            result.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        UserBean[] users = friendList.toArray(new UserBean[0]);

        return users;
    }

    @Override
    public UserBean[] friendRequested(UserBean user) {
        ArrayList<UserBean> friendRequestedList = new ArrayList<>();

        try {
            String sql ="SELECT * from user where " +
                    "user.id = ( SELECT id_user_2 from friend where " +
                    "friend.id_user_1 = ? and request_state = 'pending')";
            PreparedStatement pst = factory.getConnexion().prepareStatement(sql);

            pst.setInt(1,user.getId());

            ResultSet result = pst.executeQuery();

            UserBean requested = null;
            while (result.next()){
                requested = new UserBean();
                requested.setId(result.getInt("id"));
                requested.setRole(result.getString("role"));
                requested.setMail(result.getString("mail"));
                requested.setPassword(result.getString("password"));
                requested.setFirstName(result.getString("firstname"));
                requested.setLastName(result.getString("lastname"));
                requested.setBirthdate(result.getString("birthdate"));
                friendRequestedList.add(requested);

            }
            result.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        UserBean[] users = friendRequestedList.toArray(new UserBean[0]);

        return users;
    }

    @Override
    public UserBean[] friendRequesting(UserBean user) {
        ArrayList<UserBean> friendRequestingList = new ArrayList<>();

        try {
            String sql ="SELECT * from user where " +
                    "user.id = ( SELECT id_user_1 from friend where " +
                    "friend.id_user_2 = ? and request_state = 'pending') ";
            PreparedStatement pst = factory.getConnexion().prepareStatement(sql);

            pst.setInt(1,user.getId());

            ResultSet result = pst.executeQuery();

            UserBean userWhoIsRequesting = null;
            while (result.next()){
                userWhoIsRequesting = new UserBean();
                userWhoIsRequesting.setId(result.getInt("id"));
                userWhoIsRequesting.setRole(result.getString("role"));
                userWhoIsRequesting.setMail(result.getString("mail"));
                userWhoIsRequesting.setPassword(result.getString("password"));
                userWhoIsRequesting.setFirstName(result.getString("firstname"));
                userWhoIsRequesting.setLastName(result.getString("lastname"));
                userWhoIsRequesting.setBirthdate(result.getString("birthdate"));
                friendRequestingList.add(userWhoIsRequesting);

            }
            result.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        UserBean[] users = friendRequestingList.toArray(new UserBean[0]);

        return users;    }
}

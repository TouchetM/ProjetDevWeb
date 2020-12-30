package dao;

import bean.UserBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/** @author - Maxime Choné **/

public class UserDAOsql implements UserDAO{

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
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;    }

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

        System.out.println("Connexion matching: "+matching);
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
            String sql = "INSERT INTO USER (mail,password,firstname,lastname,role) VALUES (?,?,?,?,?)";
            PreparedStatement pst = factory.getConnexion().prepareStatement(sql);

            pst.setString(1,user.getMail());
            pst.setString(2,user.getPassword());
            pst.setString(3, user.getFirstName());
            pst.setString(4,user.getLastName());
            pst.setString(5,user.getRole());

            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void saveModification(String mail, UserBean user) {

    }
}

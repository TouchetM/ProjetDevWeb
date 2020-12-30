package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** @author - Maxime Choné **/

public class DAOFactorySQL {

    private static volatile DAOFactorySQL instance;

    private Connection connexion;


    public synchronized static DAOFactorySQL getInstance() {
        if(instance == null)
            instance = new DAOFactorySQL();
        return instance;
    }

    private DAOFactorySQL() {
        connect();
    }

    public void closeConnexion() throws SQLException{
        if(connexion!=null){
            connexion.close();
            instance=null;
        }
    }

    private void connect(){
        try{
            Properties p = new Properties();
            p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("confBDD.properties"));

            // chargement du driver
            Class.forName(p.getProperty("driver"));
            connexion = DriverManager.getConnection(
                        p.getProperty("url"),
                        p.getProperty("user"),
                        p.getProperty("pwd"));
            //cnx = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8889/projet_web_master","root","root");

        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();

        }
    }

    public Connection getConnexion(){
        return connexion;
    }

    public UserDAO getUserDAO(){
        return new UserDAOsql(this);
    }
}

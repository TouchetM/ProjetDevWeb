package dao.sql;

import dao.ActivityDAO;
import dao.FriendDAO;
import dao.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
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

    public void initDataBase() {
        String nomFichier = "createTables";
        BufferedReader fr = null;

        InputStream x = getClass().getResourceAsStream("/" + nomFichier);
        try {
            System.out.println("allo1");

            if (x != null)
                fr = new BufferedReader(new InputStreamReader(x)); // Permet de lire le fichier
            else
                throw new IOException("Impossible de lire le fichier, le fichier n'existe pas. " + "\"/" + nomFichier + "\"");

            System.out.println("allo2");

            String line;
            StringBuilder fileContent = new StringBuilder("");
            while ((line = fr.readLine()) != null){
                System.out.println(line);
                fileContent.append(line);
            }

            String[] request = fileContent.toString().split(";");

            if(connexion != null) {

                Statement st = connexion.createStatement();

                for (int i = 0; i < request.length; i++) {
                    System.out.println("Executed queries "+i+": "+request[i]);
                    if(!request[i].equals(""))
                    st.executeUpdate(request[i]);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (fr != null) {
            try {
                fr.close(); // On ferme la lecture du fichier
            } catch (IOException e) {
                System.out.println("Erreur lecture fichier : " + e.getMessage());
            }
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

    public FriendDAO getFriendRequestDAO(){
        return new FriendDAOsql(this);

    }

    public ActivityDAO getActivityDAO(){return new ActivityDAOsql(this);}

}

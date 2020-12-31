package dao.sql;

import bean.LocationBean;
import dao.LocationDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LocationDAOsql implements LocationDAO {

    DAOFactorySQL factory;

    public LocationDAOsql(DAOFactorySQL factorySQL){
        factory = factorySQL;
    }

    @Override
    public LocationBean loadLocation(int id){
        String sql = "SELECT * FROM location WHERE id = ?;";
        PreparedStatement pst = null;
        LocationBean location = new LocationBean();
        location.setId(-1);
        try {
            pst = factory.getConnexion().prepareStatement(sql);

            pst.setInt(1,id);

            ResultSet result = pst.executeQuery();

            if(result.next()){
                location.setId(result.getInt("id"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return location;
    }

    @Override
    public LocationBean[] loadAll(){

        ArrayList<LocationBean> locationList = new ArrayList<>();

        try {
            Statement st = factory.getConnexion().createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM LOCATION;");

            LocationBean location = null;
            while (result.next()){
                location = new LocationBean();
                location.setId(result.getInt("id"));
                location.setName(result.getString("name"));
                location.setAddress(result.getString("address"));

            }
            result.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        LocationBean[] locations = locationList.toArray(new LocationBean[0]);

        return locations;
    }

    @Override
    public boolean exist(int id, String name, String address){
        String sql = "SELECT * FROM location where (id = ? and 'name' = ? and address = ?)";
        PreparedStatement pst = null;
        boolean exist = false;
        try {
            pst = factory.getConnexion().prepareStatement(sql);

            pst.setInt(1,id);
            pst.setString(2,name);
            pst.setString(3,address);

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
    public void createLocation(LocationBean newLocation){
        if(!exist(newLocation.getId(),newLocation.getName(),newLocation.getAddress()))
            try {
                String sql = "INSERT INTO LOCATION (id,name,address) VALUES (?,?,?)";
                PreparedStatement pst = factory.getConnexion().prepareStatement(sql);

                pst.setInt(1,newLocation.getId());
                pst.setString(2,newLocation.getName());
                pst.setString(3,newLocation.getAddress());

                pst.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

}

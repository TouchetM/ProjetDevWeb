package dao;

import bean.LocationBean;

public interface LocationDAO {

    LocationBean load(int id);
    LocationBean[] loadAll();
    void createLocation(LocationBean location);
    boolean exist(String name, String address);
    int getId(String adresse);
}

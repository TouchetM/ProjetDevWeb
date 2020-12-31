package dao;

import bean.LocationBean;

public interface LocationDAO {

    LocationBean loadLocation(int id);
    LocationBean[] loadAll();
    void createLocation(LocationBean location);
    boolean exist(int id, String name, String address);
}

package dao;

import bean.ActivityBean;

public interface ActivityDAO {

    ActivityBean loadActivity(int id, int id_user, int id_location);
    ActivityBean[] loadAll(int id);
    void createActivity(ActivityBean newActivity);
    boolean exist(String date, int id);
}

package dao;

import bean.ActivityBean;
import bean.UserBean;

public interface ActivityDAO {

    ActivityBean load(int id);
    ActivityBean[] loadAll(UserBean user);
    void createActivity(ActivityBean newActivity);
    boolean exist(String date, int id);
}

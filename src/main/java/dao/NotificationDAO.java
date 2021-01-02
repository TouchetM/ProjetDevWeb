package dao;

import bean.NotificationBean;
import bean.UserBean;

public interface NotificationDAO {

    NotificationBean loadNotification(int id);
    NotificationBean[] loadAll();
    NotificationBean[] userNotification(UserBean user);
    void notifier(UserBean[] friends);
    void createNotification(NotificationBean notification);
    boolean exist(int id);
}
package dao;

import bean.UserBean;

/** @author - Maxime Choné **/

public interface UserDAO {

    public UserBean load(String mail);

    public boolean userMatch(String mail, String password);

    public UserBean[] loadAll();

    public void save(UserBean user);

    public void saveModification(String mail,UserBean user);

    public boolean exist(String mail);

}

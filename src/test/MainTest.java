package test;

import dao.UserDao;
import vo.LoggedInUser;
import vo.User;

import java.util.UUID;

public class MainTest {

    public static void main(String[] args){

        UserDao dao=UserDao.getUserDao();
        LoggedInUser logged=dao.getLogged("1@qq.com", "123456");
       
       

    }

}

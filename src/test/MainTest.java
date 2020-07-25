package test;

import dao.FavoriteDao;
import dao.HistoryDao;
import dao.UserDao;
import vo.ImageWrapper;
import vo.LoggedInUser;
import vo.User;

import java.util.List;
import java.util.UUID;

public class MainTest {

    public static void main(String[] args){


        FavoriteDao dao= FavoriteDao.getFavoriteDao();
        ImageWrapper imageWrapper=new ImageWrapper();
        imageWrapper.setCategory("cat");
        imageWrapper.setFileSize(100);
        imageWrapper.setFullSizeImage("full");
        imageWrapper.setResolution("100x100");
        imageWrapper.setThumbnailImage("thumb");
   //  "15097052-1db7-4223-96a3-d740e1b4845e");

        List<String> pics=dao.getPicId(3,"token");

        for (int i=0;i<pics.size();i++){
            System.out.println(pics.get(i));
        }

       
       

    }

}

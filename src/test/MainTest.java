package test;

import dao.FavoriteDao;
import dao.UserDao;
import vo.ImageWrapper;
import vo.LoggedInUser;
import vo.User;

import java.util.UUID;

public class MainTest {

    public static void main(String[] args){


        FavoriteDao dao=FavoriteDao.getFavoriteDao();
        ImageWrapper imageWrapper=new ImageWrapper();
        imageWrapper.setCategory("cat");
        imageWrapper.setFileSize(100);
        imageWrapper.setFullSizeImage("full");
        imageWrapper.setResolution("100x100");
        imageWrapper.setThumbnailImage("thumb");
        imageWrapper.setToken("15097052-1db7-4223-96a3-d740e1b4845e");

        if (dao.addFavorite(imageWrapper)){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }
       
       

    }

}

package test;

import dao.FavoriteDao;
import dao.HistoryDao;
import vo.ImageSource;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MainTest {

    public static void main(String[] args){


        HistoryDao historyDao= HistoryDao.getHistoryDao();
        FavoriteDao favoriteDao=FavoriteDao.getFavoriteDao();
        ImageSource imageSource =new ImageSource();
        imageSource.setCategory("cat");
        imageSource.setFileSize(100);
        imageSource.setFullSizeImage("full");
        imageSource.setResolution("100x100");
        imageSource.setThumbnailImage("thumb");
   //  "15097052-1db7-4223-96a3-d740e1b4845e");



        List<ImageSource> pics=historyDao.getImages(1,"d6c5ee5b-59bb-44c7-b285-5f5a0b269f6d");

        for (int i=0;i<pics.size();i++){
            System.out.println(pics.get(i));
        }




       

    }

}

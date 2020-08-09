package dao;

import jdbcUtil.JDBCUtil;
import vo.ImageSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class FavoriteDao {
    private static FavoriteDao dao;
    public static FavoriteDao getFavoriteDao(){
        if (dao==null){
            dao=new FavoriteDao();
        }
        return dao;
    }

    private boolean isPicExist(String url){
        boolean b=false;
        String id=null;
        PreparedStatement ps=null;
        Connection c=null;
        ResultSet rs=null;
        String sql="SELECT id FROM picture WHERE url=?";
        String[] paras ={url};
        try{
            c= JDBCUtil.getConnection();
            ps=c.prepareStatement(sql);
            for(int i=0;i<paras.length;i++){
                ps.setString(i+1,paras[i]);

            }
            rs=ps.executeQuery();
            while (rs.next()){
                id=rs.getString("id");
            }
            if (id!=null){
                b=true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.release(c, ps,rs);
        }
        return b;
    }

    private String getUserId(String token){
        String id=null;
        PreparedStatement ps=null;
        Connection c=null;
        ResultSet rs=null;
        String sql="SELECT id FROM user WHERE token=?";
        String[] paras ={token};
        try{
            c= JDBCUtil.getConnection();
            ps=c.prepareStatement(sql);
            for(int i=0;i<paras.length;i++){
                ps.setString(i+1,paras[i]);

            }
            rs=ps.executeQuery();
            while (rs.next()){
                id=rs.getString("id");
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.release(c, ps,rs);
        }
        return id;
    }

    private String getPicId(String url){
        String id=null;
        PreparedStatement ps=null;
        Connection c=null;
        ResultSet rs=null;
        String sql="SELECT id FROM picture WHERE url=?";
        String[] paras ={url};
        try{
            c= JDBCUtil.getConnection();
            ps=c.prepareStatement(sql);
            for(int i=0;i<paras.length;i++){
                ps.setString(i+1,paras[i]);

            }
            rs=ps.executeQuery();
            while (rs.next()){
                id=rs.getString("id");
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.release(c, ps,rs);
        }
        return id;
    }

    public boolean addToFavorite(ImageSource imageSource, String token) {
        // TODO Auto-generated method stub
        boolean b=true;
        String userId=getUserId(token);
        delFromFavorite(imageSource.getThumbnailImage(),token);
        boolean picExist=isPicExist(imageSource.getFullSizeImage());
        String picId;
        if (!picExist){
            picId= UUID.randomUUID().toString();
        }else {
            picId=getPicId(imageSource.getFullSizeImage());
        }
        PreparedStatement psP=null;
        Connection cP=null;
        PreparedStatement psC=null;
        Connection cC=null;
        String sqlC="INSERT INTO collection(userId,picId)VALUES(?,?)";
        String[] parasC ={userId,picId};
        String sqlP="INSERT INTO picture (id,url,thumbnailUrl,category,resolution,userId,fileSize) VALUES(?,?,?,?,?,?,?)";
        String[] parasP ={picId, imageSource.getFullSizeImage(), imageSource.getThumbnailImage(), imageSource.getCategory(), imageSource.getResolution()
        ,userId};

        try{
            cC=JDBCUtil.getConnection();
            psC=cC.prepareStatement(sqlC);
            for (int i=0;i<parasC.length;i++){
                psC.setString(i+1,parasC[i]);
            }
            psC.executeUpdate();

            if (!picExist){
                cP= JDBCUtil.getConnection();
                psP=cP.prepareStatement(sqlP);
                for(int i=0;i<parasP.length;i++){
                    psP.setString(i+1,parasP[i]);
                }
                psP.setInt(7, imageSource.getFileSize());
                psP.executeUpdate();
            }

        }catch(Exception e){
            b=false;
        }finally{
            JDBCUtil.release(cP, psP);
            JDBCUtil.release(cC, psC);
        }
        return b;
    }

    public boolean delFromFavorite(String thumbUrl,String token){
        boolean b=true;
        String uId=getUserId(token);
        PreparedStatement ps=null;
        Connection c=null;
        String sql="DELETE FROM collection WHERE picId=(SELECT id FROM picture WHERE thumbnailUrl=?)AND userId=?";
        String[] paras ={thumbUrl,uId};
        try{
            c= JDBCUtil.getConnection();
            ps=c.prepareStatement(sql);
            for(int i=0;i<paras.length;i++){
                ps.setString(i+1,paras[i]);

            }
            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
            b=false;
        }finally{
            JDBCUtil.release(c, ps);
        }
        return b;
    }

    public boolean getFavoriteState(String thumbUrl,String token){
        boolean b=false;
        String uId=getUserId(token);
        PreparedStatement ps=null;
        Connection c=null;
        ResultSet rs=null;
        String sql="select picId from collection WHERE picId=(SELECT id FROM picture WHERE thumbnailUrl=?)AND userId=?";

        String[] paras ={thumbUrl,uId};
        try{
            c= JDBCUtil.getConnection();
            ps=c.prepareStatement(sql);
            for(int i=0;i<paras.length;i++){
                ps.setString(i+1,paras[i]);

            }
            rs=ps.executeQuery();
            while (rs.next()){
                if (rs.getString("picId")!=null){
                    b=true;
                    System.out.println(rs.getString("picId"));
                }
            }

        }catch(Exception e){
            e.printStackTrace();
            b=false;
        }finally{
            JDBCUtil.release(c, ps,rs);
        }
        return b;
    }

    private List<String> getPicIds(int page,String token) {
        // TODO Auto-generated method stub
        int pageNow=page;//褰撳墠椤�
        int pageSize=20;//姣忛〉鏄剧ず鐨勮褰�

        String uId=getUserId(token);
        List<String> picIds=new LinkedList<>();
        PreparedStatement ps=null;
        ResultSet rs=null;
        Connection c=null;
        PreparedStatement ps2=null;
        ResultSet rs2=null;
        Connection c2=null;

        try{
            c=JDBCUtil.getConnection();
            c2=JDBCUtil.getConnection();
            //绠楀嚭椤垫暟
            ps=c.prepareStatement("select COUNT(*) from collection");
            rs=ps.executeQuery();
            rs.next();
            String[] paras={uId,uId};
            String sql=("SELECT picId FROM collection WHERE picId NOT in(SELECT t.picId from(SELECT picId FROM collection where userId=? LIMIT 0,"+pageSize*(pageNow-1)+")as t)AND userId=? LIMIT 0,"+pageSize);
            ps2=c2.prepareStatement(sql);
            for(int i=0;i<paras.length;i++){
                ps2.setString(i+1,paras[i]);

            }
            rs2=ps2.executeQuery();
            while(rs2.next()){
                String picId=rs2.getString("picId");
                picIds.add(picId);

            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.release(c, ps, rs);
            JDBCUtil.release(c2, ps2, rs2);
        }

        return picIds;
    }

    public List<ImageSource> getImages(int page, String token){

        List<String>picIds=getPicIds(page,token);
        String uId=getUserId(token);
        List<ImageSource>images=new ArrayList<>();
        PreparedStatement ps=null;
        Connection c=null;
        ResultSet rs=null;
        String sql="select url,thumbnailUrl,category,resolution,fileSize from picture,collection WHERE id=? and collection.userId=?";
        String[] paras=new String[2];
        paras[1]=uId;
        try{
            c= JDBCUtil.getConnection();
            ps=c.prepareStatement(sql);
            for (int i=0;i<picIds.size();i++){
                paras[0]=picIds.get(i);
                ps.setString(1,paras[0]);
                ps.setString(2,paras[1]);
                ImageSource image=new ImageSource();
                rs=ps.executeQuery();
                while (rs.next()){
                    image.setThumbnailImage(rs.getString("thumbnailUrl"));
                    image.setResolution(rs.getString("resolution"));
                    image.setFullSizeImage(rs.getString("url"));
                    image.setFileSize(rs.getInt("fileSize"));
                    image.setCategory(rs.getString("category"));
                }
                images.add(image);

            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtil.release(c, ps,rs);
        }
        return images;
    }







}

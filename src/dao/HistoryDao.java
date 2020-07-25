package dao;

import jdbcUtil.JDBCUtil;
import vo.ImageWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class HistoryDao {

    private static HistoryDao dao;
    public static HistoryDao getHistoryDao(){
        if (dao==null){
            dao=new HistoryDao();
        }
        return dao;
    }

    private boolean isPicExist(String userId){
        boolean b=false;
        String id=null;
        PreparedStatement ps=null;
        Connection c=null;
        ResultSet rs=null;
        String sql="SELECT id FROM picture WHERE userId=?";
        String[] paras ={userId};
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

    private String getPicId(String userId){
        String id=null;
        PreparedStatement ps=null;
        Connection c=null;
        ResultSet rs=null;
        String sql="SELECT id FROM picture WHERE userId=?";
        String[] paras ={userId};
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

    public boolean addToHistory(ImageWrapper imageWrapper,String token) {
        // TODO Auto-generated method stub
        boolean b=true;
        String userId=getUserId(token);
        boolean picExist=isPicExist(userId);
        String picId;
        if (!picExist){
            picId= UUID.randomUUID().toString();
        }else {
            picId=getPicId(userId);
        }
        PreparedStatement psP=null;
        Connection cP=null;
        PreparedStatement psH=null;
        Connection cH=null;
        String sqlH="INSERT INTO history(userId,picId)VALUES(?,?)";
        String[] parasC ={userId,picId};
        String sqlP="INSERT INTO picture (id,url,thumbnailUrl,category,resolution,userId,fileSize) VALUES(?,?,?,?,?,?,?)";
        String[] parasP ={picId,imageWrapper.getFullSizeImage(),imageWrapper.getThumbnailImage(),imageWrapper.getResolution(),imageWrapper.getResolution()
                ,userId};

        try{

            cH=JDBCUtil.getConnection();
            psH=cH.prepareStatement(sqlH);
            for (int i=0;i<parasC.length;i++){
                psH.setString(i+1,parasC[i]);
            }
            psH.executeUpdate();

            if (!picExist){
                cP= JDBCUtil.getConnection();
                psP=cP.prepareStatement(sqlP);
                for(int i=0;i<parasP.length;i++){
                    psP.setString(i+1,parasP[i]);
                }
                psP.setInt(7,imageWrapper.getFileSize());
                psP.executeUpdate();
            }

        }catch(Exception e){
            b=false;
        }finally{
            JDBCUtil.release(cP, psP);
            JDBCUtil.release(cH, psH);
        }
        return b;
    }

 /*   public boolean delFromHistory(String thumbUrl,String token){
        boolean b=true;
        String uId=getUserId(token);
        PreparedStatement ps=null;
        Connection c=null;
        String sql="DELETE FROM history WHERE picId=(SELECT id FROM picture WHERE thumbnailUrl=?)AND userId=?";
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
    }*/


}

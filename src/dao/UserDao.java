package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import jdbcUtil.JDBCUtil;
import vo.User;
import vo.LoggedInUser;


public class UserDao {
	private static UserDao dao;
	
	private UserDao(){
		
	}
	
	public boolean addUser(User u) {
		// TODO Auto-generated method stub
		boolean b=true;
		PreparedStatement ps=null;
		Connection c=null;
		String sql="insert into user( id,username,password,token) values(?,?,?,? )";
		String[] paras ={u.getId(),u.getUsername(),u.getPassword(),u.getToken()};
		try{
			c=JDBCUtil.getConnection();
			ps=c.prepareStatement(sql);
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1,paras[i]);
			}
			ps.executeUpdate();
		}catch(Exception e){
			b=false;
		}finally{
			JDBCUtil.release(c, ps);
		}
		return b;
	}
	
	public LoggedInUser getLogged(String username,String password) {
		// TODO Auto-generated method stub
		LoggedInUser u=new LoggedInUser();
	
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection c=null;
		String sql=("select id,token from user where username=? and password=? ");	
		String []paras={username,password};
		try{
			c=JDBCUtil.getConnection();
			ps=c.prepareStatement(sql);
			for(int i=0;i<paras.length;i++){
			ps.setString(i+1,paras[i]);
			}	
			rs=ps.executeQuery();
			while(rs.next()){
				u.setId(rs.getString("id"));
				u.setToken(rs.getString("token"));
			}
		}catch(Exception e){
				
		}finally{
			JDBCUtil.release(c, ps, rs);
		}
					
		return u;
	}
	public static UserDao getUserDao(){
		if(dao==null){
			dao=new UserDao();
		}
		return dao;
	}
}

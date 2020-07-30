package servlet;

import com.google.gson.Gson;
import dao.FavoriteDao;
import result.FavoriteStateResult;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetFavoriteState
 */
@WebServlet("/GetFavoriteState")
public class GetFavoriteState extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFavoriteState() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setCharacterEncoding("utf-8");
		FavoriteStateResult result=new FavoriteStateResult();
		PrintWriter out = response.getWriter();
		FavoriteDao dao=FavoriteDao.getFavoriteDao();
		String token=request.getParameter("token");
		String thumbnailUrl=request.getParameter("thumbnailUrl");
		
		System.out.println(thumbnailUrl+token);
		
		result.setFavorite(dao.getFavoriteState(thumbnailUrl,token));
		result.setSuccess(result.isFavorite());
		System.out.println(" «∑Ò ’≤ÿ?"+result.isFavorite());
		Gson gson=new Gson();
		out.write(gson.toJson(result));
		out.flush();
		out.close();
		
	}

}

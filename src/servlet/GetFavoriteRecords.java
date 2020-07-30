package servlet;

import com.google.gson.Gson;
import dao.FavoriteDao;
import vo.ImageSource;
import vo.OpenWebRepo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetFavoriteRecords
 */
@WebServlet("/GetFavoriteRecords")
public class GetFavoriteRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFavoriteRecords() {
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

		PrintWriter out=response.getWriter();
		String string=request.getQueryString();
		String sPage=request.getQueryString().substring(string.indexOf("=")+1,string.length());
		Integer page= Integer.valueOf(sPage);
		String token=request.getParameter("token");
		
		System.out.println(page+token);
		
		FavoriteDao dao=FavoriteDao.getFavoriteDao();
		List<ImageSource> images=dao.getImages(page,token);
		OpenWebRepo openWebRepo=new OpenWebRepo();
		openWebRepo.setData(images);
		Gson gson=new Gson();
		out.write(gson.toJson(openWebRepo));
		out.flush();
		out.close();


	}

}

package servlet;

import com.google.gson.Gson;
import dao.FavoriteDao;
import result.FavoriteStateResult;
import vo.ImageWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CollectionServlet
 */
@WebServlet("/SaveToFavoriteServlet")
public class SaveToFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveToFavoriteServlet() {
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
		FavoriteDao dao=FavoriteDao.getFavoriteDao();
		Gson gson=new Gson();
		PrintWriter out = response.getWriter();
		ImageWrapper imageWrapper=null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String line;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = br.readLine()) != null) {
				stringBuilder.append(line);
			}
			imageWrapper=gson.fromJson(stringBuilder.toString(),ImageWrapper.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(" ’≤ÿsource="+imageWrapper.getImageSource().toString());
		FavoriteStateResult result=new FavoriteStateResult();
		result.setSuccess(dao.addToFavorite(imageWrapper.getImageSource(),imageWrapper.getToken()));
		out.write(gson.toJson(result));
		out.flush();
		out.close();



	}

}

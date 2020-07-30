package servlet;

import com.google.gson.Gson;
import dao.HistoryDao;
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
 * Servlet implementation class HistoryServlet
 */
@WebServlet("/SaveToHistoryServlet")
public class SaveHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveHistoryServlet() {
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
		HistoryDao dao=HistoryDao.getHistoryDao();
		Gson gson=new Gson();
		ImageWrapper imageWrapper=null;
		PrintWriter out = response.getWriter();
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
		System.out.println("ÀúÊ·source="+imageWrapper.getImageSource().toString());
		FavoriteStateResult result=new FavoriteStateResult();
		result.setSuccess(dao.addToHistory(imageWrapper.getImageSource(),imageWrapper.getToken()));
		out.write(gson.toJson(result));
		out.flush();
		out.close();

	}
}

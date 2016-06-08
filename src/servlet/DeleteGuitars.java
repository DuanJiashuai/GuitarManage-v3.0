package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IGuitar;
import dao.dataAccess;

/**
 * Servlet implementation class DeleteGuitars
 */
@WebServlet("/DeleteGuitars")
public class DeleteGuitars extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGuitars() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		int count=Integer.parseInt(request.getParameter("count"));
		IGuitar ig = dataAccess.createGuitarDao();
		if(count==1){
			String serialNumber=request.getParameter("guitarId").substring(1);
			ig.deleteGuitar(serialNumber);
		}else{
			String guitarId=request.getParameter("guitarId").substring(1);
			for(int i=0;i<count;i++){
				String serialNumber=guitarId.split(",")[i];
				ig.deleteGuitar(serialNumber);
			}
		}
		
		response.sendRedirect("GuitarList.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}

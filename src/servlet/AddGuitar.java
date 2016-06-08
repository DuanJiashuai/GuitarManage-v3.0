package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IGuitar;
import dao.dataAccess;
import model.Builder;
import model.Guitar;
import model.GuitarSpec;
import model.Type;
import model.Wood;

/**
 * Servlet implementation class AddGuitar
 */
@WebServlet("/AddGuitar")
public class AddGuitar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGuitar() {
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
		
		Map properties = new HashMap();
		properties.put("builder", Builder.valueOf(request.getParameter("builder").toUpperCase().replaceAll(" ", "_")));
		properties.put("backWood", Wood.valueOf(request.getParameter("backWood").toUpperCase().replaceAll(" ", "_")));
		properties.put("topWood", Wood.valueOf(request.getParameter("topWood").toUpperCase().replaceAll(" ", "_")));
		properties.put("model", request.getParameter("model").toUpperCase().replaceAll(" ", "_"));
		properties.put("type", Type.valueOf(request.getParameter("type").toUpperCase().replaceAll(" ", "_")));
		properties.put("stringNum", request.getParameter("stringNum"));
		GuitarSpec spec = new GuitarSpec(properties);
		
		String serialNumber=request.getParameter("serialNumber");
		double price=Double.parseDouble(request.getParameter("price"));
		Guitar guitar=new Guitar(serialNumber,price,spec);
		
		IGuitar ig = dataAccess.createGuitarDao();
		ig.addGuitar(guitar);
		
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

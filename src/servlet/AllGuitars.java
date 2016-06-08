package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Guitar;
import model.GuitarSpec;
import model.Inventory;

/**
 * Servlet implementation class AllGuitars
 */
@WebServlet("/AllGuitars")
public class AllGuitars extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllGuitars() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		JSONArray ja = new JSONArray();

		// 初始化inventory
		Inventory inventory = new Inventory();
		inventory.initialize();

		// 空GuitarSpec
		Map properties = new HashMap();
		GuitarSpec Nullspec = new GuitarSpec(properties);
		// 查询匹配
		List<Guitar> Guitars = inventory.search(Nullspec);

		if (!Guitars.isEmpty()) {
			for (Iterator<Guitar> i = Guitars.iterator(); i.hasNext();) {
				Guitar guitar = (Guitar) i.next();
				GuitarSpec spec = guitar.getSpec();
				// 写入json
				JSONObject jo = new JSONObject();
				jo.put("serialNumber", guitar.getSerialNumber());
				jo.put("price", guitar.getPrice());
				jo.put("builder", spec.getProperty("builder").toString());
				jo.put("model", spec.getProperty("model").toString());
				jo.put("type", spec.getProperty("type").toString());
				jo.put("backWood", spec.getProperty("backWood").toString());
				jo.put("topWood", spec.getProperty("topWood").toString());
				jo.put("StringNum", spec.getProperty("StringNum").toString());
				ja.put(jo);
			}
		}
		out.print(ja.toString());
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package mysqlDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.IGuitar;
import model.Builder;
import model.Guitar;
import model.GuitarSpec;
import model.Type;
import model.Wood;
import util.DBUtil;

public class GuitarImpl implements IGuitar {

	@Override
	public List<Guitar> getAllGuitars(){
		Connection Conn = DBUtil.getMySqlConnection();
		String sql = "select * from Guitars";
		List<Guitar> inventory = new ArrayList<Guitar>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Map properties = new HashMap();
				properties.put("builder",  Builder.fromString(rs.getString("builder")));
				properties.put("backWood", Wood.fromString(rs.getString("backWood")));
				properties.put("topWood", Wood.fromString(rs.getString("topWood")));
				properties.put("model", rs.getString("model"));
				properties.put("type", Type.fromString(rs.getString("type")));
				properties.put("StringNum", rs.getString("StringNum"));
				GuitarSpec spec = new GuitarSpec(properties);
				Guitar Guitar = new Guitar(rs.getString("serialNumber"),rs.getDouble("price"),spec);
				inventory.add(Guitar);				
			}			
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return inventory;
	}

	@Override
	public void addGuitar(Guitar guitar){
		String serialNumber=guitar.getSerialNumber();
		double price=guitar.getPrice();
		GuitarSpec spec=guitar.getSpec();
		
		String sql="insert into Guitars (serialNumber,price,";
		for (Iterator i = spec.getProperties().keySet().iterator(); i.hasNext();) {
			String propertyName = (String) i.next();
			sql+=propertyName+",";
		}
		sql=sql.substring(0, sql.length()-1);
		sql+=") values('"+serialNumber+"',"+price+",";
		for (Iterator i = spec.getProperties().keySet().iterator(); i.hasNext();) {
			String propertyName = (String) i.next();
			String propertyValue = spec.getProperty(propertyName).toString();
			sql+="'"+propertyValue+"',";
		}
		sql=sql.substring(0, sql.length()-1);
		sql+=")";

		Connection conn = DBUtil.getMySqlConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库更新异常："+e.getMessage());
		}
	}
	
	@Override
	public void deleteGuitar(String serialNumber){
		Connection conn = DBUtil.getMySqlConnection();
		String sql = "delete from Guitars where serialNumber='"+serialNumber+"'";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库更新异常："+e.getMessage());
		}
	}
}

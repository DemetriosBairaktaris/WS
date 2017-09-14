package edu.luc.comp433.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import edu.luc.comp433.domain.order.Order;
import edu.luc.comp433.domain.order.OrderDetail;
import edu.luc.comp433.domain.partner.ConcretePartnerProfile;
import edu.luc.comp433.domain.partner.PartnerProfile;
import edu.luc.comp433.domain.product.Product;

public class ConcreteDatabaseAccess implements DatabaseAccess {
	//private final String JDBC_DRIVER = "";  
	private String DB_URL = "jdbc:postgresql:COMP433";
	//  Database credentials
	private String USER = "postgres";
	private String PASS = "root";
	private Connection db ;
	private Statement stmt ; 
	
	public ConcreteDatabaseAccess() throws SQLException{
		DB_URL = "jdbc:postgresql://ec2-54-163-233-201.compute-1.amazonaws.com:5432/dej2ecm8hpoisr"+
				"?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory" ;
		USER = "evtgoojkjfryzn" ;
		PASS = "a8c878c4bf9212dcbfe7b1de5f7ff345be7be1a7d5e14bb7407a739ed4223d08";
		db = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = db.createStatement();
	}
	@Override
	public boolean insertOrder() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOrderDetail(OrderDetail detail) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Order getOrder(double orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetail> getOrderDetails(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetail> getOrderDetails(double id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertPartner(PartnerProfile profile) throws Exception,SQLException {
		String partnerName = profile.getName();
		String sql = "INSERT INTO PARTNERS (PARTNER_NAME) VALUES ( " +
				this.wrapSingleQuotes(partnerName) + " );"; 
		int success = stmt.executeUpdate(sql);
		if (success == 0) {
			System.out.println("Couln't insert row into Partners");
			return false ; 
		}
		else {
			profile.setId(this.getPartnerProfile(profile.getName()).getId());
			return true ; 
		}
	}

	@Override
	public boolean updatePartner(PartnerProfile profile) throws SQLException {
		String partnerName = profile.getName() ; 
		int partnerId = (int) profile.getId() ; 
		String sql = "UPDATE PARTNERS SET PARTNER_NAME = " +
				this.wrapSingleQuotes(partnerName) + " WHERE "+
				"PARTNER_ID = " + partnerId ;
		int success = stmt.executeUpdate(sql) ;
		if(success == 0) {
			return false ; 
		}
		else {
			return true ; 
		}
	}

	@Override
	public boolean deletePartner(PartnerProfile profile) throws SQLException {
		String partnerName = profile.getName();
		int partnerId = (int) profile.getId();
	
		String sql = "DELETE FROM PARTNERS WHERE PARTNER_ID = " + 
				 partnerId + " OR " +
				"PARTNER_NAME = " + this.wrapSingleQuotes(partnerName) + " ; "; 
		int success = stmt.executeUpdate(sql);
		if(success == 0) {
			System.out.println("Unable to delete partner");
			return false ; 
		}
		return true ;  
	}

	@Override
	public PartnerProfile getPartnerProfile(double id) throws Exception,SQLException {
		int partnerId = (int) id ; 
		String sql = "SELECT * FROM PARTNERS WHERE PARTNER_ID = " 
				+ id + " ; " ; 
		
		ResultSet rs = stmt.executeQuery(sql);
		if(!rs.next()) {
			throw new Exception("Partner does not exist") ;  
		}
		else{
			PartnerProfile p = new ConcretePartnerProfile() ; 
			p.setId(rs.getInt(1)) ; 
			p.setName(rs.getString(2));
			return p ; 
		}
	}
	
	@Override
	public PartnerProfile getPartnerProfile(String name) throws Exception,SQLException {
		String partnerName = name ; 
		String sql = "SELECT * FROM PARTNERS WHERE PARTNER_NAME = " 
				+ this.wrapSingleQuotes(name) + " ; " ; 
		
		ResultSet rs = stmt.executeQuery(sql);
		if(!rs.next()) {
			throw new Exception("Partner does not exist") ;  
		}
		else{
			PartnerProfile p = new ConcretePartnerProfile() ; 
			p.setId(rs.getInt(1)) ; 
			p.setName(rs.getString(2));
			return p ; 
		}
	}

	@Override
	public boolean insertProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private String wrapSingleQuotes(String s) {
		String wrappedResult = "'" + s + "'" ;
		return wrappedResult;
	}

}

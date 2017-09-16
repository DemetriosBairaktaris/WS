package edu.luc.comp433.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.* ; 

import edu.luc.comp433.domain.consumer.Address;
import edu.luc.comp433.domain.consumer.ConcreteAddress;
import edu.luc.comp433.domain.consumer.ConcreteConsumer;
import edu.luc.comp433.domain.consumer.ConcretePayment;
import edu.luc.comp433.domain.consumer.ConcretePhone;
import edu.luc.comp433.domain.consumer.Consumer;
import edu.luc.comp433.domain.consumer.Payment;
import edu.luc.comp433.domain.consumer.Phone;
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

	//TODO add more table values to SQL code
	@Override
	public boolean insertPartner(PartnerProfile profile) throws Exception,SQLException {
		
		String sql = "INSERT INTO PARTNERS (PARTNER_USER_NAME,PARTNER_NAME,PARTNER_ADDRESS,PARTNER_PHONE) VALUES ( " +
				this.wrapSingleQuotes(profile.getUserName()) + "," +
				this.wrapSingleQuotes(profile.getName()) + "," +
				this.wrapSingleQuotes(profile.getAddress())+ "," +
				this.wrapSingleQuotes(profile.getPhone())+ " );"; 
		int success = stmt.executeUpdate(sql);
		if (success == 0) {
			System.out.println("Couln't insert row into Partners");
			return false ; 
		}
		else {
			return true ; 
		}
	}

	//TODO add more table values to SQL code
	@Override
	public boolean updatePartner(PartnerProfile profile) throws SQLException {
		String partnerName = profile.getName();
		String partnerAddress = profile.getAddress() ; 
		String partnerPhone = profile.getPhone() ;
		String partnerUserName = profile.getUserName() ; 
		
		String sql = "UPDATE PARTNERS SET PARTNER_NAME = " +
				this.wrapSingleQuotes(partnerName) + " , " +
				"PARTNER_ADDRESS = " + this.wrapSingleQuotes(partnerAddress) + " , "+
				"PARTNER_PHONE = " + this.wrapSingleQuotes(partnerPhone) +
				" WHERE PARTNER_USER_NAME = " + this.wrapSingleQuotes(partnerUserName) ; 
		
		int success = stmt.executeUpdate(sql) ;
		if(success == 0) {
			return false ; 
		}
		else {
			return true ; 
		}
	}

	//TODO check this code
	@Override
	public boolean deletePartner(PartnerProfile profile) throws SQLException {
		String partnerName = profile.getUserName();
		String sql = "DELETE FROM PARTNERS WHERE PARTNER_USER_NAME = " + this.wrapSingleQuotes(partnerName) + " ; "; 
		int success = stmt.executeUpdate(sql);
		if(success == 0) {
			System.out.println("Unable to delete partner");
			return false ; 
		}
		return true ;  
	}

	//TODO map additional attributes
	@Override
	public PartnerProfile getPartnerProfile(String userName) throws Exception,SQLException {
		String sql = "SELECT * FROM PARTNERS WHERE PARTNER_USER_NAME = " 
				+ this.wrapSingleQuotes(userName) + " ; " ; 
		
		ResultSet rs = stmt.executeQuery(sql);
		if(!rs.next()) {
			throw new Exception("Partner does not exist") ;  
		}
		else{
			PartnerProfile p = new ConcretePartnerProfile() ; 
			p.setUserName(rs.getString(1));
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
	@Override
	public boolean insertConsumer(Consumer consumer) throws SQLException {
		db.setAutoCommit(false);
		/**Steps
		 * insert into consumer
		 * insert into address
		 * insert into phone
		 * insert into payment
		 */
		String sql = "INSERT INTO CONSUMERS (CONSUMER_USER_NAME,CONSUMER_FIRST_NAME,CONSUMER_LAST_NAME)"
				+ " VALUES ( "+ this.wrapSingleQuotes(consumer.getUserName()) + ", "  + 
				this.wrapSingleQuotes(consumer.getFirstName()) + ", " +
				this.wrapSingleQuotes(consumer.getLastName()) + ") ; " ;
				
		if(stmt.executeUpdate(sql) == 0) {
			db.rollback();
			db.setAutoCommit(true);
			return false ;
		}; 
		
		for (Address a : consumer.getAddresses()) {
			sql = "INSERT INTO CONSUMER_ADDRESSES (CONSUMER_USER_NAME,ADDRESS,ADDRESS_TYPE) VALUES"+
					"("+ this.wrapSingleQuotes(consumer.getUserName()) + "," +
					 this.wrapSingleQuotes(a.getAddress()) + " ," +
					 this.wrapSingleQuotes(a.getAddressType()) + ") ; ";
			if(stmt.executeUpdate(sql) == 0) {
				db.rollback();
				db.setAutoCommit(true);
				return false ;
			}
		}
		for (Phone p : consumer.getPhones()) {
			sql = "INSERT INTO CONSUMER_PHONES (CONSUMER_USER_NAME,PHONE_NUMBER,PHONE_TYPE) VALUES"+
					"("+ this.wrapSingleQuotes(consumer.getUserName()) + "," +
					 this.wrapSingleQuotes(p.getNumber()) + " ," +
					 this.wrapSingleQuotes(p.getType()) + ") ; ";
			if(stmt.executeUpdate(sql) == 0) {
				db.rollback();
				db.setAutoCommit(true);
				return false ;
			}
		}
		for(Payment p : consumer.getPayments()) {
			sql = "INSERT INTO CONSUMER_PAYMENTS (CONSUMER_USER_NAME,CARD_NAME,CARD_NUMBER,CVV) VALUES"+
					"("+ this.wrapSingleQuotes(consumer.getUserName()) + "," +
					 this.wrapSingleQuotes(p.getCardName()) + " ," +
					 this.wrapSingleQuotes(p.getCardNumber()) + " , " +
					 this.wrapSingleQuotes(p.getCVV()) + ") ; ";
			
			if(stmt.executeUpdate(sql) == 0) {
				db.rollback();
				db.setAutoCommit(true);
				return false ;
			}
		}
		db.commit();
		db.setAutoCommit(true);
		return true;
	}
	@Override
	public boolean updateConsumer(Consumer consumer) throws SQLException {
		if(this.getConsumer(consumer.getUserName()).getUserName().equals(consumer.getUserName())) {
			db.setAutoCommit(false);
			this.deleteConsumer(consumer);
			db.commit();
			if(this.insertConsumer(consumer)) {
				return true ;
			}
		}
		
		return false;
	}
	@Override
	public boolean deleteConsumer(Consumer consumer) throws SQLException {
		String sql = "DELETE FROM CONSUMERS WHERE CONSUMER_USER_NAME = " +
		this.wrapSingleQuotes(consumer.getUserName()) + " ;" ; 
		if(stmt.executeUpdate(sql) > 0) {
			return true ; 
		}
		else {
			return false ;
		}
	}
	@Override
	public Consumer getConsumer(String userName) throws SQLException {
		
		String getConsumerCredSql = "Select * from consumers where consumer_user_name = " + this.wrapSingleQuotes(userName) +";";
		String getAddressSql = "SELECT CONSUMERS.CONSUMER_USER_NAME,CONSUMERS.CONSUMER_FIRST_NAME,CONSUMERS.CONSUMER_LAST_NAME,"
				+ "CONSUMER_ADDRESSES.ADDRESS,CONSUMER_ADDRESSES.ADDRESS_TYPE FROM CONSUMERS, CONSUMER_ADDRESSES WHERE"
				+ " CONSUMERS.CONSUMER_USER_NAME = CONSUMER_ADDRESSES.CONSUMER_USER_NAME 	AND CONSUMERS.CONSUMER_USER_NAME = "
				+ this.wrapSingleQuotes(userName) + " ;" ;
		
		
		String getPhoneSql = "SELECT CONSUMERS.CONSUMER_USER_NAME,CONSUMERS.CONSUMER_FIRST_NAME,CONSUMERS.CONSUMER_LAST_NAME,"
				+ "CONSUMER_PHONES.PHONE_NUMBER,CONSUMER_PHONES.PHONE_TYPE FROM CONSUMERS, CONSUMER_PHONES WHERE"
				+ " CONSUMERS.CONSUMER_USER_NAME = CONSUMER_PHONES.CONSUMER_USER_NAME AND CONSUMERS.CONSUMER_USER_NAME = "
				+ this.wrapSingleQuotes(userName) + " ;" ;
		
		String getPaymentSql = "SELECT CONSUMERS.CONSUMER_USER_NAME,CONSUMERS.CONSUMER_FIRST_NAME,CONSUMERS.CONSUMER_LAST_NAME,"
				+ "CONSUMER_PAYMENTS.CARD_NAME,CONSUMER_PAYMENTS.CARD_NUMBER, CONSUMER_PAYMENTS.CVV FROM CONSUMERS, CONSUMER_PAYMENTS WHERE"
				+ " CONSUMERS.CONSUMER_USER_NAME = CONSUMER_PAYMENTS.CONSUMER_USER_NAME 	AND CONSUMERS.CONSUMER_USER_NAME = "
				+ this.wrapSingleQuotes(userName) + " ;" ;
		
		
		
		ResultSet rs = stmt.executeQuery(getConsumerCredSql);
		Consumer c = new ConcreteConsumer();
		List<Address> addresses ;
		List<Phone> phones ;
		List<Payment> payments ;
		if(rs.next()) {
			c.setUserName(rs.getString(1));
			c.setFirstName(rs.getString(2));
			c.setLastName(rs.getString(3));
			addresses = new LinkedList<>();
			phones = new LinkedList<>();
			payments = new LinkedList<>();
		}
		else {
			return null ;
		}
		
		rs = stmt.executeQuery(getAddressSql);
		while(rs.next()) {
			Address a = new ConcreteAddress();
			a.setAddress(rs.getString(4));
			a.setAddressType(rs.getString(5));
			addresses.add(a);
		}
		c.setAddresses(addresses);
		rs = stmt.executeQuery(getPhoneSql);
		while(rs.next()) {
			Phone p = new ConcretePhone();
			p.setNumber(rs.getString(4));
			p.setType(rs.getString(5));
			phones.add(p);
		}
		c.setPhones(phones);
		rs = stmt.executeQuery(getPaymentSql);
		while(rs.next()) {
			c.setUserName(rs.getString(1));
			c.setFirstName(rs.getString(2));
			c.setLastName(rs.getString(3));
			Payment p = new ConcretePayment();
			p.setCardName(rs.getString(4));
			p.setCardNumber(rs.getString(5));
			p.setCVV(rs.getString(6));
			payments.add(p);
		}
		c.setPayments(payments);
		return c ; 
	}

}

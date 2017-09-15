package edu.luc.comp433.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.luc.comp433.dao.ConcreteDatabaseAccess;
import edu.luc.comp433.dao.DatabaseAccess;
import edu.luc.comp433.domain.partner.ConcretePartnerProfile;
import edu.luc.comp433.domain.partner.PartnerProfile;

import static org.junit.Assert.*;

import java.sql.* ;

public class DatabaseTests {
	
	//private final String JDBC_DRIVER = "";  
	//format : jdbc:postgresql://host:port/database 
	private String DB_URL = "jdbc:postgresql:COMP433" ;
	   //  Database credentials
	private String USER = "postgres";
	private String PASS = "root";
	
	private Connection db ;
	private Statement stmt ; 
	private DatabaseAccess dal ; 
	private String partnerName1 ;
	private String partnerName2 ; 
	
	@Before
	public void setUp() throws SQLException {
		DB_URL = "jdbc:postgresql://ec2-54-163-233-201.compute-1.amazonaws.com:5432/dej2ecm8hpoisr"+
				"?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory" ;
		USER = "evtgoojkjfryzn" ;
		PASS = "a8c878c4bf9212dcbfe7b1de5f7ff345be7be1a7d5e14bb7407a739ed4223d08";
		db = DriverManager.getConnection(DB_URL, USER, PASS);
	    stmt = db.createStatement();
	    dal = new ConcreteDatabaseAccess();
	    partnerName1 = "JSHARP@GMAIL.COM" ; 
	    partnerName2 = "JSHARP7@GMAIL.COM";
	}
	
	@After
	public void tearDown() {
		db = null ; 
		stmt = null ; 
		dal = null ;
		partnerName1 = null ; 
		partnerName2 = null ;
	}
	
    @Test
    public void testConnection() throws SQLException {
    		//test our connection to our lovely database <3
    		stmt = db.createStatement();
    		String sql = "Select * from Products;";
    		assertTrue(stmt.execute(sql)); // should be able to query 
    }
    
    @Test
    public void testInsertPartner() throws Exception,SQLException {
    		PartnerProfile partner = new ConcretePartnerProfile();
    		partner.setUserName(partnerName1);
    		partner.setName("Johnson and sons");
    		partner.setAddress("1232 Lolly Way");
    		partner.setPhone("219-292-1111");
    		//stmt = db.createStatement();
    		assertTrue(dal.insertPartner(partner));	
    }

    @Test
    public void testGetPartnerByName() throws Exception,SQLException {
    		String partnerUserName = "Newbie@Gmail.com"; 
    		String partnerName = "Newbie Co"; 
    		PartnerProfile partner = new ConcretePartnerProfile();
    		partner.setName(partnerName); 
    		partner.setUserName(partnerUserName);
    		partner.setAddress("kjadsf");
    		partner.setPhone("lldkfjal"); 
		stmt = db.createStatement();
	    dal.insertPartner(partner);
	    //String sql = "SELECT PARTNER_USER_NAME FROM PARTNERS WHERE PARTNER_USER_NAME = '"  + partnerUserName + "';";
		//ResultSet rs = stmt.executeQuery(sql);
	    if(dal.getPartnerProfile(partnerUserName).getUserName().equals(partnerUserName)) {
			dal.deletePartner(partner);
			assertTrue((partner.getUserName().equals(partnerUserName)));
		}
		else {
			assertTrue(false);
		}
    }

    @Test
    public void testUpdatePartner() throws Exception,SQLException{
    		String partnerUserName = "Newbie@gmail.com" ;
    		String partnerName = "Newbie Co" ; 
    		String address = "funky brats" ;
    		String phone = "219-222-2222"; 
    		String newPhone = "219-333-3333" ; 
		PartnerProfile partner = new ConcretePartnerProfile();
		
		partner.setName(partnerName) ; 
		partner.setUserName(partnerUserName);
		partner.setAddress(address);
		partner.setPhone(phone);
		
		stmt = db.createStatement();	
		dal.insertPartner(partner);
		
    		String sql = "SELECT PARTNER_USER_NAME FROM PARTNERS WHERE PARTNER_USER_NAME = '"  + partnerUserName +
    				" ; '";
    		
    		//ResultSet rs = stmt.executeQuery(sql);
    		partner.setPhone(newPhone);
	    	assertTrue(dal.updatePartner(partner));
	    	dal.deletePartner(partner) ; 
    		
    }
    


    @Test
    public void testDeletePartner() throws SQLException{
    		PartnerProfile partner = new ConcretePartnerProfile();
		stmt = db.createStatement();
		String sql = "SELECT PARTNER_USER_NAME FROM PARTNERS WHERE PARTNER_USER_NAME = '"  + partnerName1 +
				"' or PARTNER_USER_NAME = '"+partnerName2+"'";
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			String partnerUserName = rs.getString(1);
			partner.setUserName(partnerUserName);
			assertTrue(dal.deletePartner(partner));
		}
		else {
			assertTrue(false);
		}
    }
}

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
	    partnerName1 = "Johnson and Sons" ; 
	    partnerName2 = "Johnson and Daughters and Sons";
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
    		partner.setName(partnerName1);
    		stmt = db.createStatement();
    		assertTrue(dal.insertPartner(partner));	
    }

    @Test
    public void testGetPartnerByName() throws Exception,SQLException {
    		String partnerNameNew = "Newbie Co." ; 
    		PartnerProfile partner = new ConcretePartnerProfile();
    		partner.setName(partnerNameNew) ; 
		stmt = db.createStatement();
	    String sql = "SELECT PARTNER_ID FROM PARTNERS WHERE PARTNER_NAME = '"  + partnerNameNew + "';";
		
	    dal.insertPartner(partner);
		
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) { 
			//int id = rs.getInt(1);
			partner = dal.getPartnerProfile(partnerNameNew);
			dal.deletePartner(partner);
			assertTrue((partner.getName().equals(partnerNameNew)));
		}
		else {
			assertTrue(false);
		}
    }

    @Test
    public void testUpdatePartner() throws Exception,SQLException{
    		String partnerNameNew = "Newbie Co." ; 
		PartnerProfile partner = new ConcretePartnerProfile();
		partner.setName(partnerNameNew) ; 
		stmt = db.createStatement();	
		dal.insertPartner(partner);
		
    		String sql = "SELECT PARTNER_ID FROM PARTNERS WHERE PARTNER_NAME = '"  + partnerNameNew + "'";
    		
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		if(rs.next()) {
	    		partner.setName(partnerNameNew + " Update");
	    		assertTrue(dal.updatePartner(partner));
	    		dal.deletePartner(partner) ; 
    		}
    		else {
    			assertTrue(false);
    		}
    }
    


    @Test
    public void testDeletePartner() throws SQLException{
    		PartnerProfile partner = new ConcretePartnerProfile();
		stmt = db.createStatement();
		String sql = "SELECT PARTNER_ID FROM PARTNERS WHERE PARTNER_NAME = '"  + partnerName1 +
				"' or PARTNER_NAME = '"+partnerName2+"'";
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			assertTrue(dal.deletePartner(partner));
		}
		else {
			assertTrue(false);
		}
    }
}

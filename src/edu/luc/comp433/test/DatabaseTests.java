package edu.luc.comp433.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.luc.comp433.dao.ConcreteDatabaseAccess;
import edu.luc.comp433.dao.DatabaseAccess;
import edu.luc.comp433.domain.customer.ConcreteCustomer;
import edu.luc.comp433.domain.customer.ConcretePayment;
import edu.luc.comp433.domain.customer.Customer;
import edu.luc.comp433.domain.customer.Payment;
import edu.luc.comp433.domain.partner.ConcretePartnerProfile;
import edu.luc.comp433.domain.partner.PartnerProfile;
import edu.luc.comp433.domain.product.ConcreteProduct;
import edu.luc.comp433.domain.product.Product;

public class DatabaseTests {

	// private final String JDBC_DRIVER = "";
	// format : jdbc:postgresql://host:port/database
	private String DB_URL = "jdbc:postgresql:COMP433";
	// Database credentials
	private String USER = "postgres";
	private String PASS = "root";

	private Connection db;
	private Statement stmt;
	private DatabaseAccess dal;
	private String partnerName1;
	private String partnerName2;

	public DatabaseTests() {
		DB_URL = "jdbc:postgresql://ec2-54-163-233-201.compute-1.amazonaws.com:5432/dej2ecm8hpoisr"
				+ "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
		USER = "evtgoojkjfryzn";
		PASS = "a8c878c4bf9212dcbfe7b1de5f7ff345be7be1a7d5e14bb7407a739ed4223d08";

		try {
			db = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = db.createStatement();
			dal = new ConcreteDatabaseAccess();
		} catch (SQLException e) {
			//
		}
	}

	@Override
	public void finalize() {
		try {
			db.close();
			stmt.close();
			dal = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not close db connection");
		}
	}

	@Before
	public void setUp() throws SQLException {

		partnerName1 = "JSHARP@GMAIL.COM";
		partnerName2 = "JSHARP7@GMAIL.COM";
	}

	@After
	public void tearDown() throws SQLException {
		partnerName1 = null;
		partnerName2 = null;
	}

	@Test
	public void testConnection() throws SQLException {
		// test our connection to our lovely database <3
		String sql = "Select * from Products;";
		assertTrue(stmt.execute(sql)); // should be able to query
	}

	@Test
	public void testInsertPartner() throws Exception, SQLException {
		PartnerProfile partner = new ConcretePartnerProfile();
		partner.setUserName(partnerName1);
		partner.setName("Johnson and sons");
		partner.setAddress("1232 Lolly Way");
		partner.setPhone("219-292-1111");
		partner.setProducts(new LinkedList());
		assertTrue(dal.insertPartner(partner));
		assertNotNull(dal.getPartnerProfile(partnerName1).getProducts());
		// assertNotNull(dal.getPartnerProfile(partnerUserName).getOrders());
	}

	@Test
	public void testGetPartnerByName() throws Exception, SQLException {
		String partnerUserName = "Newbie@Gmail.com";
		String partnerName = "Newbie Co";
		PartnerProfile partner = new ConcretePartnerProfile();
		partner.setName(partnerName);
		partner.setUserName(partnerUserName);
		partner.setAddress("kjadsf");
		partner.setPhone("lldkfjal");
		partner.setProducts(new LinkedList());
		stmt = db.createStatement();
		assertTrue(dal.insertPartner(partner));

		if (dal.getPartnerProfile(partnerUserName).getUserName().equals(partnerUserName)) {
			assertTrue((partner.getUserName().equals(partnerUserName)));
			assertNotNull(dal.getPartnerProfile(partnerUserName).getProducts());
			// assertNotNull(dal.getPartnerProfile(partnerUserName).getOrders());
			dal.deletePartner(partner);
		} else {
			assertTrue(false);
		}
	}

	@Test
	public void testUpdatePartner() throws Exception, SQLException {
		String partnerUserName = "Newbie@gmail.com";
		String partnerName = "Newbie Co";
		String address = "funky brats";
		String phone = "219-222-2222";
		String newPhone = "219-333-3333";
		PartnerProfile partner = new ConcretePartnerProfile();

		partner.setName(partnerName);
		partner.setUserName(partnerUserName);
		partner.setAddress(address);
		partner.setPhone(phone);
		partner.setProducts(new LinkedList());

		stmt = db.createStatement();
		dal.insertPartner(partner);

		partner.setPhone(newPhone);
		assertTrue(dal.updatePartner(partner));
		assertTrue(dal.getPartnerProfile(partnerUserName).getPhone().equals(newPhone));
		assertNotNull(dal.getPartnerProfile(partnerUserName).getProducts());
		// assertNotNull(dal.getPartnerProfile(partnerUserName).getOrders());
		assertTrue(dal.deletePartner(partner));

	}

	@Test
	public void testDeletePartner() throws SQLException {
		PartnerProfile partner = new ConcretePartnerProfile();
		stmt = db.createStatement();
		String sql = "SELECT PARTNER_USER_NAME FROM PARTNERS WHERE PARTNER_USER_NAME = '" + partnerName1
				+ "' or PARTNER_USER_NAME = '" + partnerName2 + "' ; ";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			String partnerUserName = rs.getString(1);
			partner.setUserName(partnerUserName);
			assertTrue(dal.deletePartner(partner));
		} else {
			assertTrue(false);
		}
	}

	@Test
	public void testInsertProduct() throws SQLException {
		String productName = "WaxOn-WaxOff";
		String desc = "Everyones favorite wax"; // todo fix for apostraphe
		String partnerUserName = "wonderbread@gmail.com";
		double cost = 500000.00;
		int stock = 30;
		Product product = new ConcreteProduct();
		product.setName(productName);
		product.setDesc(desc);
		product.setCost(cost);
		product.setStock(stock);

		PartnerProfile profile = new ConcretePartnerProfile();

		profile.setUserName(partnerUserName);
		assertFalse(dal.insertProduct(product, profile)); // should fail/ partner doesn't exist...
		profile.setUserName("BIGDADDY@GMAIL.COM"); // change to one that does exist
		assertTrue(dal.insertProduct(product, profile)); // should pass :)
		assertTrue(dal.deleteProduct(product, profile));

	}

	@Test
	public void testDeleteProduct() throws SQLException {
		String productName = "WaxOn-WaxOff";
		String desc = "Everyones favorite wax";
		String partnerUserName = "BIGDADDY@GMAIL.COM";
		double cost = 500000.00;
		int stock = 30;
		Product product = new ConcreteProduct();
		product.setName(productName);
		product.setDesc(desc);
		product.setCost(cost);
		product.setStock(stock);

		PartnerProfile profile = new ConcretePartnerProfile();
		profile.setUserName(partnerUserName);

		assertTrue(dal.insertProduct(product, profile)); // should pass :)
		assertTrue(dal.deleteProduct(product, profile));

	}

	@Test
	public void testInsertConsumer() throws SQLException {
		String username = "MHM@gmail.com";
		String firstName = "Doug";
		String lastName = "Frankenstein";
		String address = "232 dslakj st";
		String phone = "219-202-2222";

		Payment payment = new ConcretePayment();
		payment.setCardName("visa");
		payment.setCardNumber("2233333333334444");
		payment.setCvv("822");

		Customer c = new ConcreteCustomer();
		c.setUserName(username);
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setAddress(address);
		c.setPhone(phone);
		c.setPayment(payment);

		String delete_sql = "Delete from consumers where consumer_user_name = '" + username + "' ;";
		int b = stmt.executeUpdate(delete_sql);
		assertTrue(dal.insertConsumer(c));
		b = stmt.executeUpdate(delete_sql);
	}

	@Test
	public void testGetConsumer() throws SQLException {
		String username = "MHM@gmail.com";
		String firstName = "Doug";
		String lastName = "Frankenstein";
		String address = "232 dslakj st";
		String phone = "219-202-2222";

		Payment payment = new ConcretePayment();
		payment.setCardName("visa");
		payment.setCardNumber("2233333333334444");
		payment.setCvv("822");

		Customer c = new ConcreteCustomer();
		c.setUserName(username);
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setAddress(address);
		c.setPhone(phone);
		c.setPayment(payment);

		String delete_sql = "Delete from consumers where consumer_user_name = '" + username + "' ; ";
		dal.insertConsumer(c);
		assertTrue(c.getUserName().equals(dal.getConsumer(username).getUserName()));
		stmt.executeUpdate(delete_sql);
	}

	@Test
	public void testUpdateConsumer() throws SQLException {
		String username = "MHM@gmail.com";
		String firstName = "Doug";
		String lastName = "Frankenstein";
		String address = "232 dslakj st";
		String phone = "219-202-2222";

		Payment payment = new ConcretePayment();
		payment.setCardName("visa");
		payment.setCardNumber("2233333333334444");
		payment.setCvv("822");

		Customer c = new ConcreteCustomer();
		c.setUserName(username);
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setAddress(address);
		c.setPhone(phone);
		c.setPayment(payment);

		dal.insertConsumer(c);
		String newFirstName = "Tee";
		c.setFirstName(newFirstName);
		dal.updateConsumer(c);

		assertTrue(newFirstName.equals(dal.getConsumer(username).getFirstName()));
	}

	@Test
	public void testDeleteConsumer() throws SQLException {
		String username = "MHM@gmail.com";
		String firstName = "Doug";
		String lastName = "Frankenstein";
		String address = "232 dslakj st";
		String phone = "219-202-2222";

		Payment payment = new ConcretePayment();
		payment.setCardName("visa");
		payment.setCardNumber("2233333333334444");
		payment.setCvv("822");

		Customer c = new ConcreteCustomer();
		c.setUserName(username);
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setAddress(address);
		c.setPhone(phone);
		c.setPayment(payment);

		assertTrue(dal.insertConsumer(c));
		assertTrue(dal.deleteConsumer(c));
	}

}


DROP TABLE IF EXISTS PARTNERS,PRODUCTS,CUSTOMERS,CUSTOMER_PAYMENTS,ORDERS,ORDER_DETAILS,REVIEWS ; 

CREATE TABLE PARTNERS (
    PARTNER_USER_NAME VARCHAR (32) PRIMARY KEY,
	PARTNER_NAME VARCHAR (32) NOT NULL,
	PARTNER_ADDRESS VARCHAR(32) NOT NULL,
	PARTNER_PHONE VARCHAR(32) NOT NULL
	);

CREATE TABLE PRODUCTS (
	PRODUCT_ID SERIAL PRIMARY KEY,
	PRODUCT_NAME VARCHAR(32),
	DESCRIPTION TEXT NOT NULL,
	COST FLOAT NOT NULL,
	STOCK INTEGER NOT NULL,
	PARTNER_USER_NAME VARCHAR(32) REFERENCES PARTNERS(PARTNER_USER_NAME)
	);
	
CREATE TABLE REVIEWS (
    REVIEW_ID SERIAL PRIMARY KEY,
    PRODUCT_ID INTEGER REFERENCES PRODUCTS(PRODUCT_ID) ON DELETE CASCADE,
    REVIEW_RATING INTEGER NOT NULL,
    REVIEW_CONTENT TEXT NOT NULL
    );

CREATE TABLE CUSTOMERS (
	USER_NAME VARCHAR(32) PRIMARY KEY,
	CUSTOMER_FIRST_NAME VARCHAR(32),
	CUSTOMER_LAST_NAME VARCHAR(32),
	CUSTOMER_ADDRESS VARCHAR(32),
	CUSTOMER_PHONE VARCHAR(32)
	);


CREATE TABLE CUSTOMER_PAYMENTS( 
	PAYMENT_ID SERIAL PRIMARY KEY,
	USER_NAME VARCHAR(32) REFERENCES CUSTOMERS(USER_NAME) ON DELETE CASCADE,
	CARD_NAME VARCHAR(32) NOT NULL,
	CARD_NUMBER VARCHAR(32) NOT NULL,
	CVV VARCHAR(3) NOT NULL,
	EXPIRATION TIMESTAMP NOT NULL
	);


CREATE TABLE ORDERS (
	ORDER_ID SERIAL PRIMARY KEY,
	USER_NAME VARCHAR(32) REFERENCES CUSTOMERS(USER_NAME) ON DELETE CASCADE,
 	ORDER_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE ORDER_DETAILS (
     ORDER_DETAIL_ID SERIAL PRIMARY KEY ,
     ORDER_ID INTEGER REFERENCES ORDERS(ORDER_ID),
     USER_NAME VARCHAR(32) REFERENCES CUSTOMERS(USER_NAME) ON DELETE CASCADE,
     PRODUCT_ID INTEGER REFERENCES PRODUCTS(PRODUCT_ID),
     QUANTITY INTEGER NOT NULL DEFAULT 1,
     STATUS VARCHAR(32) NOT NULL DEFAULT 'PLACED'
	);


--now we test for proper constraints:

INSERT INTO PARTNERS (PARTNER_USER_NAME,PARTNER_NAME,PARTNER_ADDRESS,PARTNER_PHONE) VALUES ('BIGDADDY@GMAIL.COM','WAL MART','912 FUNNY ST.','219-292-2222');
INSERT INTO PARTNERS (PARTNER_USER_NAME,PARTNER_NAME,PARTNER_ADDRESS,PARTNER_PHONE) VALUES ('EEEEEEEEEEEHHHHHH@GMAIL.COM','TARGET','913 DENNY ST.','219-292-2222');
INSERT INTO PARTNERS (PARTNER_USER_NAME,PARTNER_NAME,PARTNER_ADDRESS,PARTNER_PHONE) VALUES ('G@GMAIL.COM','DICKS SPORTING GOODS','9134 RANDY ST.','219-292-4222');
\echo 'CREATED 3 PARTNERS' ; 
SELECT * FROM PARTNERS ; 

INSERT INTO PRODUCTS (PRODUCT_NAME,DESCRIPTION,PARTNER_USER_NAME,COST,STOCK) VALUES 
	('SAMSUNG GALAXY 5', 'ANDROID SMART PHONE', 'BIGDADDY@GMAIL.COM', 500.00, 20);
INSERT INTO PRODUCTS (PRODUCT_NAME,DESCRIPTION,PARTNER_USER_NAME,COST,STOCK) VALUES 
	('SAMSUNG GALAXY 5', 'ANDROID SMART PHONE', 'EEEEEEEEEEEHHHHHH@GMAIL.COM', 499.99, 20);
INSERT INTO PRODUCTS (PRODUCT_NAME,DESCRIPTION,PARTNER_USER_NAME,COST,STOCK) VALUES 
	('SAMSUNG GALAXY 5', 'ANDROID SMART PHONE', 'G@GMAIL.COM', 699.50, 20);
INSERT INTO PRODUCTS (PRODUCT_NAME,DESCRIPTION,PARTNER_USER_NAME,COST,STOCK) VALUES 
	('SAMSUNG GALAXY 5', 'ANDROID SMART PHONE', 'LAJDFLA@GMAIL.COM', 500.00, 20); --SHOULD FAIL 
\echo 'Previous should have failed NO PARTNER_ID = 4' ; 	
INSERT INTO PRODUCTS (PRODUCT_NAME,DESCRIPTION,PARTNER_USER_NAME,COST,STOCK) VALUES 
	('XBOX ONE', 'MODERN GAME CONSOLE', 'BIGDADDY@GMAIL.COM', 250.00, 100);

\echo 'CREATED 4 PRODUCTS' ; 
SELECT * FROM PRODUCTS ; 



INSERT INTO CUSTOMERS (USER_NAME,CUSTOMER_FIRST_NAME,CUSTOMER_LAST_NAME) VALUES
	('MRS_HOPEFUL@YAHOO.COM','JENNY','GUMP');
\echo 'CREATED A CUSTOMER';
SELECT * FROM CUSTOMER ; 

INSERT INTO ORDERS (USER_NAME) VALUES 
	('MRS_HOPEFUL@YAHOO.COM');
\echo 'CREATED AN ORDER: ' ; 
SELECT * FROM ORDERS ; 

INSERT INTO ORDER_DETAILS (ORDER_ID,USER_NAME,PRODUCT_ID) VALUES
	(1,'MRS_HOPEFUL@YAHOO.COM',3);
\echo 'CREATED AN ORDER_DETAIL' ; 
SELECT * FROM ORDER_DETAILS ; 


--SELECT PARTNERS.PARTNER_NAME FROM PARTNERS,ORDER_DETAILS,PRODUCTS WHERE 
  --  ORDER_DETAILS.PRODUCT_ID = PRODUCTS.PRODUCT_ID 
    --	AND
    --PRODUCTS.PARTNER_ID = PARTNERS.PARTNER_ID ; 


\echo 'TESTING FOR INSERTIONS....' ; 
DO
$$
<<test_block>>
BEGIN  
	IF ((SELECT COUNT(*) FROM PRODUCTS WHERE PRODUCT_NAME = 'SAMSUNG GALAXY 5' AND 
		DESCRIPTION = 'ANDROID SMART PHONE') = 3) THEN 
		RAISE NOTICE 'ALL GOOD!' ; 
	ELSE 
		RAISE NOTICE 'NOT ALL GOOD' ; 
	END IF ;

	IF ((SELECT PARTNERS.PARTNER_NAME FROM PARTNERS,ORDER_DETAILS,PRODUCTS WHERE 
    	ORDER_DETAILS.PRODUCT_ID = PRODUCTS.PRODUCT_ID 
    		AND
    	PRODUCTS.PARTNER_USER_NAME = PARTNERS.PARTNER_USER_NAME )= 'DICKS SPORTING GOODS')  THEN
		RAISE NOTICE 'ALL GOOD!' ;
	ELSE 
		RAISE NOTICE 'GOT SOME PROBLEMS	' ; 
	END IF ; 
END test_block $$;




	
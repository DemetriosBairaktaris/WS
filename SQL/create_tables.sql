
DROP TABLE IF EXISTS PARTNERS,PRODUCTS,CUSTOMERS,ORDERS,ORDER_DETAILS ; 

CREATE TABLE PARTNERS (
		PARTNER_ID SERIAL PRIMARY KEY,
		PARTNER_NAME VARCHAR (32) NOT NULL
	);

CREATE TABLE PRODUCTS (
	PRODUCT_ID SERIAL PRIMARY KEY,
	PRODUCT_NAME VARCHAR(32) NOT NULL,
	DESCRIPTION TEXT NOT NULL,
	PARTNER_ID INTEGER REFERENCES PARTNERS(PARTNER_ID),
	COST FLOAT(2) NOT NULL ,
	STOCK INTEGER NOT NULL  
	);

CREATE TABLE CUSTOMERS(
	CUSTOMER_ID SERIAL PRIMARY KEY,
	CREDIT_CARD_NUMBER VARCHAR(32)
	);

CREATE TABLE ORDERS (
	ORDER_ID SERIAL PRIMARY KEY,
	CUSTOMER_ID INTEGER REFERENCES CUSTOMERS(CUSTOMER_ID),
 	ORDER_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	);

CREATE TABLE ORDER_DETAILS (
     ORDER_DETAIL_ID SERIAL PRIMARY KEY ,
     ORDER_ID INTEGER REFERENCES ORDERS(ORDER_ID),
     CUSTOMER_ID INTEGER REFERENCES CUSTOMERS(CUSTOMER_ID),
     PRODUCT_ID INTEGER REFERENCES PRODUCTS(PRODUCT_ID),
     QUANTITY INTEGER NOT NULL DEFAULT 1,
     STATUS VARCHAR(32) NOT NULL DEFAULT 'PLACED'
	);


--now we test for proper constraints:

INSERT INTO PARTNERS (PARTNER_NAME) VALUES ('WAL MART');
INSERT INTO PARTNERS (PARTNER_NAME) VALUES ('TARGET');
INSERT INTO PARTNERS (PARTNER_NAME) VALUES ('DICKS SPORTING GOODS');
\echo 'CREATED 3 PARTNERS' ; 
SELECT * FROM PARTNERS ; 

INSERT INTO PRODUCTS (PRODUCT_NAME,DESCRIPTION,PARTNER_ID,COST,STOCK) VALUES 
	('SAMSUNG GALAXY 5', 'ANDROID SMART PHONE', 1, 500.00, 20);
INSERT INTO PRODUCTS (PRODUCT_NAME,DESCRIPTION,PARTNER_ID,COST,STOCK) VALUES 
	('SAMSUNG GALAXY 5', 'ANDROID SMART PHONE', 2, 499.99, 20);
INSERT INTO PRODUCTS (PRODUCT_NAME,DESCRIPTION,PARTNER_ID,COST,STOCK) VALUES 
	('SAMSUNG GALAXY 5', 'ANDROID SMART PHONE', 3, 699.50, 20);
INSERT INTO PRODUCTS (PRODUCT_NAME,DESCRIPTION,PARTNER_ID,COST,STOCK) VALUES 
	('SAMSUNG GALAXY 5', 'ANDROID SMART PHONE', 4, 500.00, 20); --SHOULD FAIL 
\echo 'Previous should have failed NO PARTNER_ID = 4' ; 	
INSERT INTO PRODUCTS (PRODUCT_NAME,DESCRIPTION,PARTNER_ID,COST,STOCK) VALUES 
	('XBOX ONE', 'MODERN GAME CONSOLE', 1, 250.00, 100);

\echo 'CREATED 4 PRODUCTS' ; 
SELECT * FROM PRODUCTS ; 



INSERT INTO CUSTOMERS (CREDIT_CARD_NUMBER) VALUES
	('1112223333');
\echo 'CREATED A CUSTOMER';
SELECT * FROM CUSTOMERS ; 

INSERT INTO ORDERS (CUSTOMER_ID) VALUES 
	(1);
\echo 'CREATED AN ORDER: ' ; 
SELECT * FROM ORDERS ; 

INSERT INTO ORDER_DETAILS (ORDER_ID,CUSTOMER_ID,PRODUCT_ID) VALUES
	(1,1,3);
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
		DESCRIPTION = 'ANDROID SMART PHONE' ) = 3) THEN 
		RAISE NOTICE 'ALL GOOD!' ; 
	ELSE 
		RAISE NOTICE 'NOT ALL GOOD' ; 
	END IF ;

	IF ((SELECT PARTNERS.PARTNER_NAME FROM PARTNERS,ORDER_DETAILS,PRODUCTS WHERE 
    	ORDER_DETAILS.PRODUCT_ID = PRODUCTS.PRODUCT_ID 
    		AND
    	PRODUCTS.PARTNER_ID = PARTNERS.PARTNER_ID) = 'DICKS SPORTING GOODS' ) THEN
		RAISE NOTICE 'ALL GOOD!' ;
	ELSE 
		RAISE NOTICE 'GOT SOME PROBLEMS	' ; 
	END IF ; 
END test_block $$;




	
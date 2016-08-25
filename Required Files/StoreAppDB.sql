--------------------------------------------------------
--  DDL for Table CUSTOMR
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."CUSTOMR" 
   (	"ID" NUMBER(*,0), 
	"NAME" VARCHAR2(200), 
	"ADDR" VARCHAR2(400), 
	"MOBNO" VARCHAR2(20)
   ) ;
   
--------------------------------------------------------
--  Constraints for Table CUSTOMR
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."CUSTOMR" ADD PRIMARY KEY ("ID") ENABLE;

---------------------------------------------------
--   DATA FOR TABLE CUSTOMR
---------------------------------------------------
Insert into SYSTEM.CUSTOMR (ID,NAME,ADDR,MOBNO) values (1,'vittal','vishrambag sangli','7875362378');
Insert into SYSTEM.CUSTOMR (ID,NAME,ADDR,MOBNO) values (7,'priti','wce,sangli','8484858592');
Insert into SYSTEM.CUSTOMR (ID,NAME,ADDR,MOBNO) values (3,'Shital','navipeth,sangola','7875775888');
Insert into SYSTEM.CUSTOMR (ID,NAME,ADDR,MOBNO) values (4,'amruta','vishrambag,sangli','9888787212');
Insert into SYSTEM.CUSTOMR (ID,NAME,ADDR,MOBNO) values (5,'ajay','navipeth,mumbai','7875344543');
Insert into SYSTEM.CUSTOMR (ID,NAME,ADDR,MOBNO) values (6,'Kishor','vishrambag,sangli','8484787373');
Insert into SYSTEM.CUSTOMR (ID,NAME,ADDR,MOBNO) values (8,'pratibha patil','kolaur','9834598947');
Insert into SYSTEM.CUSTOMR (ID,NAME,ADDR,MOBNO) values (9,'ragvendra','sangola','870890988');
Insert into SYSTEM.CUSTOMR (ID,NAME,ADDR,MOBNO) values (10,'ankita','sangola','985958987');
Insert into SYSTEM.CUSTOMR (ID,NAME,ADDR,MOBNO) values (11,'yogesh j patil','vishrambag, sangli','9850678451');
Insert into SYSTEM.CUSTOMR (ID,NAME,ADDR,MOBNO) values (12,'Anjana','wce,sangli','98837483784');
Insert into SYSTEM.CUSTOMR (ID,NAME,ADDR,MOBNO) values (0,null,null,null);
Insert into SYSTEM.CUSTOMR (ID,NAME,ADDR,MOBNO) values (13,'sneha','sangli','980496809');
Insert into SYSTEM.CUSTOMR (ID,NAME,ADDR,MOBNO) values (14,'Sujata Regoti','solapur','7767887872');

---------------------------------------------------
--   END DATA FOR TABLE CUSTOMR
---------------------------------------------------


--------------------------------------------------------
--  DDL for Table COMPANY1
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."COMPANY1" 
   (	"ID" NUMBER(*,0), 
	"NAME" VARCHAR2(200)
   ) ;
   

--------------------------------------------------------
--  Constraints for Table COMPANY1
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."COMPANY1" ADD PRIMARY KEY ("ID") ENABLE;
--------------------------------------------------------
---------------------------------------------------
--   DATA FOR TABLE COMPANY1
---------------------------------------------------
Insert into SYSTEM.COMPANY1 (ID,NAME) values (1,'dell');
Insert into SYSTEM.COMPANY1 (ID,NAME) values (2,'hp');
Insert into SYSTEM.COMPANY1 (ID,NAME) values (3,'flipkart');
Insert into SYSTEM.COMPANY1 (ID,NAME) values (4,'samsang');
Insert into SYSTEM.COMPANY1 (ID,NAME) values (5,'benq');
Insert into SYSTEM.COMPANY1 (ID,NAME) values (6,'amazon');

---------------------------------------------------
--   END DATA FOR TABLE COMPANY1
---------------------------------------------------
 
 
--------------------------------------------------------
--  DDL for Table SUPPLIER
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."SUPPLIER" 
   (	"SID" NUMBER(*,0), 
	"SNAME" VARCHAR2(200), 
	"SADDR" VARCHAR2(400), 
	"SMOBNO" VARCHAR2(20)
   ) ;
   
--------------------------------------------------------
--  Constraints for Table SUPPLIER
--------------------------------------------------------

ALTER TABLE "SYSTEM"."SUPPLIER" ADD PRIMARY KEY ("SID") ENABLE;
  
---------------------------------------------------
--   DATA FOR TABLE SUPPLIER
---------------------------------------------------
Insert into SYSTEM.SUPPLIER (SID,SNAME,SADDR,SMOBNO) values (1,'alex','colombo,shrilanka','009093567789');
Insert into SYSTEM.SUPPLIER (SID,SNAME,SADDR,SMOBNO) values (3,'Smith','Perryridge','0098263671');
Insert into SYSTEM.SUPPLIER (SID,SNAME,SADDR,SMOBNO) values (2,'john','us','8897493');
Insert into SYSTEM.SUPPLIER (SID,SNAME,SADDR,SMOBNO) values (4,'abcd','hsdgjmasn','72874889');
Insert into SYSTEM.SUPPLIER (SID,SNAME,SADDR,SMOBNO) values (5,'Saiamar','solapur','890780569807');

---------------------------------------------------
--   END DATA FOR TABLE SUPPLIER
---------------------------------------------------

--------------------------------------------------------
--  DDL for Table PRODUCT1
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."PRODUCT1" 
   (	"PID" NUMBER(*,0), 
	"PNAME" VARCHAR2(200), 
	"ID" NUMBER(*,0), 
	"PRATE" FLOAT(63), 
	"PTYPE" VARCHAR2(300), 
	"INFO" VARCHAR2(500), 
	"MINLIMIT" NUMBER(*,0), 
	"BARCODE" VARCHAR2(100)
   ) ;
   
 --------------------------------------------------------
--  Constraints for Table PRODUCT1
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."PRODUCT1" ADD CONSTRAINT "PRODUCT1_UK1" UNIQUE ("BARCODE") ENABLE;
 
  ALTER TABLE "SYSTEM"."PRODUCT1" ADD PRIMARY KEY ("PID") ENABLE;
  
  
--------------------------------------------------------
--  Ref Constraints for Table PRODUCT1
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."PRODUCT1" ADD FOREIGN KEY ("ID")
	  REFERENCES "SYSTEM"."COMPANY1" ("ID") ENABLE;
	  
	  
---------------------------------------------------
--   DATA FOR TABLE PRODUCT1
---------------------------------------------------
Insert into SYSTEM.PRODUCT1 (PID,PNAME,ID,PRATE,PTYPE,INFO,MINLIMIT,BARCODE) values (1,'5432 laptop',1,32000,'electronic','inspiron 15 3000 series',10,'6711430844174');
Insert into SYSTEM.PRODUCT1 (PID,PNAME,ID,PRATE,PTYPE,INFO,MINLIMIT,BARCODE) values (2,'wireless mouse',1,300,'electronic','wireless mouse desc		',10,'8906010500894');
Insert into SYSTEM.PRODUCT1 (PID,PNAME,ID,PRATE,PTYPE,INFO,MINLIMIT,BARCODE) values (3,'duos mobile',4,3000,'electronic',null,10,null);
Insert into SYSTEM.PRODUCT1 (PID,PNAME,ID,PRATE,PTYPE,INFO,MINLIMIT,BARCODE) values (4,'galaxy mobile',4,7000,'electronic',null,10,null);
Insert into SYSTEM.PRODUCT1 (PID,PNAME,ID,PRATE,PTYPE,INFO,MINLIMIT,BARCODE) values (5,'happy biscuit',3,10,'food','kdjsfhlksjd',15,null);
Insert into SYSTEM.PRODUCT1 (PID,PNAME,ID,PRATE,PTYPE,INFO,MINLIMIT,BARCODE) values (6,'good day biscuit',3,20,'electronic','info',18,null);
Insert into SYSTEM.PRODUCT1 (PID,PNAME,ID,PRATE,PTYPE,INFO,MINLIMIT,BARCODE) values (7,'laptop',2,40000,'electronics','jdjkfgkfdj',5,null);
Insert into SYSTEM.PRODUCT1 (PID,PNAME,ID,PRATE,PTYPE,INFO,MINLIMIT,BARCODE) values (21,'abc',5,100,'electronic','jkwjek',20,'8901233018041');
Insert into SYSTEM.PRODUCT1 (PID,PNAME,ID,PRATE,PTYPE,INFO,MINLIMIT,BARCODE) values (8,'charger',1,2000,'electronic',null,10,'8901425052327');

---------------------------------------------------
--   END DATA FOR TABLE PRODUCT1
---------------------------------------------------

 
 --------------------------------------------------------
--  DDL for Table PRODTYPE
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."PRODTYPE" 
   (	"ID" NUMBER(*,0), 
	"PTYPE" VARCHAR2(300)
   ) ;
   
   
--------------------------------------------------------
--  Constraints for Table PRODTYPE
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."PRODTYPE" ADD PRIMARY KEY ("ID", "PTYPE") ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table PRODTYPE
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."PRODTYPE" ADD FOREIGN KEY ("ID")
	  REFERENCES "SYSTEM"."COMPANY1" ("ID") ENABLE;  
 
---------------------------------------------------
--   DATA FOR TABLE PRODTYPE
---------------------------------------------------
Insert into SYSTEM.PRODTYPE (ID,PTYPE) values (1,'electronic');
Insert into SYSTEM.PRODTYPE (ID,PTYPE) values (2,'electronics');
Insert into SYSTEM.PRODTYPE (ID,PTYPE) values (3,'books');
Insert into SYSTEM.PRODTYPE (ID,PTYPE) values (3,'electronic');
Insert into SYSTEM.PRODTYPE (ID,PTYPE) values (3,'fabrics');
Insert into SYSTEM.PRODTYPE (ID,PTYPE) values (3,'food');
Insert into SYSTEM.PRODTYPE (ID,PTYPE) values (3,'jwellery');
Insert into SYSTEM.PRODTYPE (ID,PTYPE) values (4,'electronic');
Insert into SYSTEM.PRODTYPE (ID,PTYPE) values (5,'electronic');
Insert into SYSTEM.PRODTYPE (ID,PTYPE) values (6,'books');

---------------------------------------------------
--   END DATA FOR TABLE PRODTYPE
---------------------------------------------------

--------------------------------------------------------
--  DDL for Table AVAILABLE1
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."AVAILABLE1" 
   (	"PID" NUMBER(*,0), 
	"AVAIL" NUMBER(*,0)
   ) ;
   
--------------------------------------------------------
--  Constraints for Table AVAILABLE1
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."AVAILABLE1" ADD PRIMARY KEY ("PID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table AVAILABLE1
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."AVAILABLE1" ADD FOREIGN KEY ("PID")
	  REFERENCES "SYSTEM"."PRODUCT1" ("PID") ENABLE;
 
---------------------------------------------------
--   DATA FOR TABLE AVAILABLE1
---------------------------------------------------
Insert into SYSTEM.AVAILABLE1 (PID,AVAIL) values (1,108);
Insert into SYSTEM.AVAILABLE1 (PID,AVAIL) values (2,147);
Insert into SYSTEM.AVAILABLE1 (PID,AVAIL) values (3,10);
Insert into SYSTEM.AVAILABLE1 (PID,AVAIL) values (4,43);
Insert into SYSTEM.AVAILABLE1 (PID,AVAIL) values (5,48);
Insert into SYSTEM.AVAILABLE1 (PID,AVAIL) values (6,20);
Insert into SYSTEM.AVAILABLE1 (PID,AVAIL) values (7,8);
Insert into SYSTEM.AVAILABLE1 (PID,AVAIL) values (21,20);
Insert into SYSTEM.AVAILABLE1 (PID,AVAIL) values (8,0);

---------------------------------------------------
--   END DATA FOR TABLE AVAILABLE1
---------------------------------------------------

--------------------------------------------------------
--  DDL for Table SUPPLIERPRODUCTS
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."SUPPLIERPRODUCTS" 
   (	"SID" NUMBER(*,0), 
	"PID" NUMBER(*,0)
   ) ;
   
--------------------------------------------------------
--  Constraints for Table SUPPLIERPRODUCTS
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."SUPPLIERPRODUCTS" ADD PRIMARY KEY ("SID", "PID") ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table SUPPLIERPRODUCTS
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."SUPPLIERPRODUCTS" ADD FOREIGN KEY ("SID")
	  REFERENCES "SYSTEM"."SUPPLIER" ("SID") ENABLE;
 
  ALTER TABLE "SYSTEM"."SUPPLIERPRODUCTS" ADD FOREIGN KEY ("PID")
	  REFERENCES "SYSTEM"."PRODUCT1" ("PID") ENABLE;

	  
---------------------------------------------------
--   DATA FOR TABLE SUPPLIERPRODUCTS
---------------------------------------------------
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (1,1);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (1,2);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (1,3);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (1,4);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (1,5);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (1,6);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (1,7);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (1,21);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (2,1);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (2,2);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (2,4);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (2,5);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (2,6);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (2,21);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (3,1);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (3,2);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (3,3);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (3,4);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (3,5);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (3,6);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (3,7);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (3,21);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (4,2);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (4,3);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (4,5);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (4,6);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (4,7);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (4,8);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (5,1);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (5,2);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (5,3);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (5,4);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (5,5);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (5,6);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (5,7);
Insert into SYSTEM.SUPPLIERPRODUCTS (SID,PID) values (5,21);

---------------------------------------------------
--   END DATA FOR TABLE SUPPLIERPRODUCTS
---------------------------------------------------

--------------------------------------------------------
--  DDL for Table PURCHASE1
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."PURCHASE1" 
   (	"GRNNO" NUMBER(*,0), 
	"SID" NUMBER(*,0), 
	"BILL" FLOAT(63), 
	"PURCH_DATE" DATE, 
	"ORDERNO" NUMBER(*,0)
   ) ;


--------------------------------------------------------
--  Constraints for Table PURCHASE1
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."PURCHASE1" ADD PRIMARY KEY ("GRNNO") ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table PURCHASE1
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."PURCHASE1" ADD FOREIGN KEY ("SID")
	  REFERENCES "SYSTEM"."SUPPLIER" ("SID") ENABLE;

	  
---------------------------------------------------
--   DATA FOR TABLE PURCHASE1
---------------------------------------------------
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (1,1,161200,to_timestamp('07-JUN-15 05.21.26.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (2,1,71500,to_timestamp('07-JUN-15 05.27.19.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (3,1,16500,to_timestamp('07-JUN-15 05.31.43.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (4,1,190000,to_timestamp('07-JUN-15 05.35.46.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (5,1,321500,to_timestamp('07-JUN-15 06.47.54.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (6,2,160900,to_timestamp('10-JUN-15 03.12.58.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (7,1,32300,to_timestamp('10-JUN-15 03.15.07.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (8,1,224000,to_timestamp('13-JUN-15 11.47.51.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (9,1,209100,to_timestamp('13-JUN-15 01.29.11.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (10,2,37000,to_timestamp('13-JUN-15 01.31.06.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (11,1,353500,to_timestamp('14-JUN-15 02.05.20.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (12,2,28000,to_timestamp('14-JUN-15 05.24.09.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (13,1,322700,to_timestamp('16-JUN-15 10.32.56.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (16,1,600,to_timestamp('19-JUN-15 06.36.49.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),1);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (14,2,43500,to_timestamp('18-JUN-15 12.09.39.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (15,2,21000,to_timestamp('18-JUN-15 08.44.58.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (17,2,9000,to_timestamp('20-JUN-15 08.14.40.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),3);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (18,1,215000,to_timestamp('21-JUN-15 05.49.29.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),2);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (19,2,49900,to_timestamp('21-JUN-15 09.48.41.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),4);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (20,2,32000,to_timestamp('23-JUN-15 08.31.13.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),6);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (21,1,1000,to_timestamp('25-JUN-15 08.33.54.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),9);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (22,1,70150,to_timestamp('06-JUL-15 07.23.14.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),12);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (23,1,73000,to_timestamp('10-JUL-15 02.33.59.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (24,2,2800,to_timestamp('12-JUL-15 10.42.48.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),13);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (25,1,105100,to_timestamp('22-JUL-15 10.27.57.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),10);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (26,3,107000,to_timestamp('08-JAN-16 11.00.41.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),11);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (27,1,390000,to_timestamp('08-JAN-16 11.01.35.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (28,1,6300,to_timestamp('08-JAN-16 11.36.29.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),22);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (29,1,163000,to_timestamp('08-JAN-16 11.37.11.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (30,5,12700,to_timestamp('08-JAN-16 12.05.39.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),17);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (31,1,163000,to_timestamp('08-JAN-16 12.06.08.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (32,1,7500,to_timestamp('08-JAN-16 12.26.05.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),18);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (33,1,966000,to_timestamp('08-JAN-16 12.26.35.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (34,2,6300,to_timestamp('09-JAN-16 02.38.43.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),21);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (35,2,2600,to_timestamp('09-JAN-16 02.40.05.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),19);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (36,5,322000,to_timestamp('09-JAN-16 02.45.46.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),25);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (37,1,4800,to_timestamp('09-JAN-16 02.49.49.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),15);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (38,1,320000,to_timestamp('09-JAN-16 02.50.14.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),0);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (39,1,3000,to_timestamp('09-JAN-16 02.52.43.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),16);
Insert into SYSTEM.PURCHASE1 (GRNNO,SID,BILL,PURCH_DATE,ORDERNO) values (40,1,960000,to_timestamp('09-JAN-16 02.53.06.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),0);

---------------------------------------------------
--   END DATA FOR TABLE PURCHASE1
---------------------------------------------------

--------------------------------------------------------
--  DDL for Table PROD_PURCH
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."PROD_PURCH" 
   (	"PID" NUMBER(*,0), 
	"GRNNO" NUMBER(*,0), 
	"QUANTITY" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  Constraints for Table PROD_PURCH
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."PROD_PURCH" ADD PRIMARY KEY ("PID", "GRNNO") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PROD_PURCH
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."PROD_PURCH" ADD FOREIGN KEY ("GRNNO")
	  REFERENCES "SYSTEM"."PURCHASE1" ("GRNNO") ENABLE;
 
  ALTER TABLE "SYSTEM"."PROD_PURCH" ADD FOREIGN KEY ("PID")
	  REFERENCES "SYSTEM"."PRODUCT1" ("PID") ENABLE;

--------------------------------------------------------
--  DDL for Trigger PURCHASE
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."PURCHASE" 
before insert on prod_purch
for each row 
declare ava int;
    
begin
  select avail into ava from available1 where pid=:new.pid;
  if(ava >= 0) then 
  update available1 set avail=ava+:new.quantity where pid=:new.pid;
  else 
      raise_application_error (-20999,'insuffiecient stock available');
  end if;
 
end;


/ 

---------------------------------------------------
--   DATA FOR TABLE PROD_PURCH
---------------------------------------------------
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (1,2,2);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (2,3,5);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (1,4,5);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (3,4,10);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (1,5,10);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (2,5,5);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (1,6,5);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (2,6,3);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (1,7,1);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (2,7,1);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (1,8,2);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (2,9,7);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (3,9,5);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (1,9,6);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (2,11,5);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (1,11,11);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (4,12,4);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (2,13,9);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (1,13,10);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (2,14,5);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (4,14,6);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (3,15,7);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (6,16,20);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (5,16,20);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (3,17,3);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (3,18,5);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (7,18,5);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (2,19,3);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (4,19,7);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (4,20,2);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (3,20,6);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (6,21,50);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (5,22,15);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (4,22,10);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (1,23,2);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (3,23,3);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (21,24,25);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (5,24,30);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (5,25,10);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (4,25,15);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (4,26,15);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (21,26,20);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (1,27,10);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (4,27,10);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (6,28,15);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (2,28,20);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (1,29,5);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (2,29,10);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (2,30,39);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (21,30,10);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (1,31,5);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (2,31,10);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (2,32,25);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (1,33,30);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (2,33,20);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (2,34,19);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (7,36,8);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (21,36,20);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (2,37,16);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (1,38,10);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (2,39,10);
Insert into SYSTEM.PROD_PURCH (PID,GRNNO,QUANTITY) values (1,40,30);

---------------------------------------------------
--   END DATA FOR TABLE PROD_PURCH
---------------------------------------------------

--------------------------------------------------------
--  DDL for Table PLACEORDER
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."PLACEORDER" 
   (	"ORDERNO" NUMBER(*,0), 
	"SID" NUMBER(*,0), 
	"BILL" FLOAT(63), 
	"ORD_DATE" DATE, 
	"ISRECEIVED" VARCHAR2(5)
   ) ;
   
--------------------------------------------------------
--  Constraints for Table PLACEORDER
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."PLACEORDER" ADD PRIMARY KEY ("ORDERNO") ENABLE;

---------------------------------------------------
--   DATA FOR TABLE PLACEORDER
---------------------------------------------------
REM INSERTING into SYSTEM.PLACEORDER
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (1,1,550,to_timestamp('19-JUN-15 04.50.06.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'yes');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (2,1,216500,to_timestamp('20-JUN-15 02.47.58.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'yes');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (3,2,15000,to_timestamp('20-JUN-15 08.13.49.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'yes');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (4,2,57500,to_timestamp('21-JUN-15 09.47.02.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'yes');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (5,1,9500,to_timestamp('23-JUN-15 08.06.46.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'no');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (6,2,17000,to_timestamp('23-JUN-15 08.18.12.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'yes');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (7,1,80,to_timestamp('25-JUN-15 04.48.50.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'no');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (8,1,70000,to_timestamp('25-JUN-15 04.52.58.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'no');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (9,1,1000,to_timestamp('25-JUN-15 08.32.51.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'yes');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (10,1,112100,to_timestamp('28-JUN-15 12.44.45.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'yes');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (11,3,72000,to_timestamp('02-JUL-15 08.02.04.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'yes');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (12,1,70150,to_timestamp('06-JUL-15 07.21.40.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'yes');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (13,2,9400,to_timestamp('10-JUL-15 11.21.35.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'yes');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (14,1,2700,to_timestamp('22-JUL-15 10.23.39.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'no');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (15,1,4800,to_timestamp('26-JUL-15 10.46.30.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'yes');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (16,1,3000,to_timestamp('31-JUL-15 06.53.01.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'yes');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (17,5,15400,to_timestamp('08-JAN-16 10.27.14.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'yes');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (18,1,6000,to_timestamp('08-JAN-16 10.30.08.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'yes');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (19,2,8300,to_timestamp('08-JAN-16 10.32.12.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'no');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (20,2,7700,to_timestamp('08-JAN-16 10.39.44.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'no');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (21,2,6300,to_timestamp('08-JAN-16 10.57.43.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'no');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (22,1,6260,to_timestamp('08-JAN-16 11.17.27.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'yes');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (23,1,402700,to_timestamp('08-JAN-16 11.22.25.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'no');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (24,1,206000,to_timestamp('08-JAN-16 11.34.21.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'no');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (25,5,442000,to_timestamp('08-JAN-16 12.04.26.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'yes');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (26,1,600000,to_timestamp('08-JAN-16 12.24.06.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'no');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (27,2,2100,to_timestamp('09-JAN-16 02.36.31.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'no');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (28,4,90000,to_timestamp('09-JAN-16 02.38.24.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'no');
Insert into SYSTEM.PLACEORDER (ORDERNO,SID,BILL,ORD_DATE,ISRECEIVED) values (29,4,30000,to_timestamp('09-JAN-16 02.48.19.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),'no');

---------------------------------------------------
--   END DATA FOR TABLE PLACEORDER
---------------------------------------------------


--------------------------------------------------------
--  DDL for Table PROD_ORDERED
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."PROD_ORDERED" 
   (	"PID" NUMBER(*,0), 
	"ORDERNO" NUMBER(*,0), 
	"QUANTITY" NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  Constraints for Table PROD_ORDERED
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."PROD_ORDERED" ADD PRIMARY KEY ("PID", "ORDERNO") ENABLE;
  
--------------------------------------------------------
--  Ref Constraints for Table PROD_ORDERED
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."PROD_ORDERED" ADD FOREIGN KEY ("ORDERNO")
	  REFERENCES "SYSTEM"."PLACEORDER" ("ORDERNO") ENABLE;
 
  ALTER TABLE "SYSTEM"."PROD_ORDERED" ADD FOREIGN KEY ("PID")
	  REFERENCES "SYSTEM"."PRODUCT1" ("PID") ENABLE;
---------------------------------------------------
--   DATA FOR TABLE PROD_ORDERED
---------------------------------------------------
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (5,1,15);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (6,1,20);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (7,2,5);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (3,2,5);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (2,2,5);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (3,3,5);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (2,4,5);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (4,4,8);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (6,5,10);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (3,6,1);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (4,6,2);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (5,7,8);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (4,8,10);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (6,9,50);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (4,10,16);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (5,10,10);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (4,11,10);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (21,11,20);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (4,12,10);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (5,12,15);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (2,14,9);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (2,15,16);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (21,13,30);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (5,13,30);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (2,5,15);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (2,16,10);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (2,17,39);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (2,18,20);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (2,19,19);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (21,19,26);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (2,20,19);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (21,20,20);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (2,21,19);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (21,21,6);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (6,22,13);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (2,22,20);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (2,23,9);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (7,23,10);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (2,24,20);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (7,24,5);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (7,25,8);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (21,25,20);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (21,17,10);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (7,26,15);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (21,27,21);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (8,28,25);
Insert into SYSTEM.PROD_ORDERED (PID,ORDERNO,QUANTITY) values (8,29,15);

---------------------------------------------------
--   END DATA FOR TABLE PROD_ORDERED
---------------------------------------------------


--------------------------------------------------------
--  DDL for Table SALE1
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."SALE1" 
   (	"BILLNO" NUMBER(*,0), 
	"ID" NUMBER(*,0), 
	"BILL" FLOAT(63), 
	"SALE_DATE" DATE, 
	"DISCOUNT" FLOAT(63), 
	"BALANCE" FLOAT(63)
   ) ;
   
 
--------------------------------------------------------
--  Constraints for Table SALE1
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."SALE1" ADD PRIMARY KEY ("BILLNO") ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table SALE1
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."SALE1" ADD FOREIGN KEY ("ID")
	  REFERENCES "SYSTEM"."CUSTOMR" ("ID") ENABLE;  
 

 
---------------------------------------------------
--   DATA FOR TABLE SALE1
---------------------------------------------------
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (1,1,32300,to_timestamp('07-JUN-15 06.02.54.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (2,3,64300,to_timestamp('08-JUN-15 07.14.01.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (3,3,64600,to_timestamp('08-JUN-15 07.30.48.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (4,1,193800,to_timestamp('13-JUN-15 01.39.15.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (5,3,321500,to_timestamp('14-JUN-15 05.25.39.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (6,4,348500,to_timestamp('14-JUN-15 05.27.32.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (7,7,3000,to_timestamp('17-JUN-15 05.08.46.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (8,5,25500,to_timestamp('18-JUN-15 08.43.56.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (9,4,28000,to_timestamp('21-JUN-15 08.18.24.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (10,6,200,to_timestamp('23-JUN-15 08.04.42.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (11,7,47000,to_timestamp('23-JUN-15 08.17.02.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (12,6,70050,to_timestamp('24-JUN-15 07.50.10.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (13,5,60,to_timestamp('24-JUN-15 07.56.16.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (14,0,86940,to_timestamp('27-JUN-15 05.04.22.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),10,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (15,5,29034,to_timestamp('30-JUN-15 12.48.08.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),10,900);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (16,8,14478,to_timestamp('30-JUN-15 07.02.33.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),5,2478);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (17,0,36000,to_timestamp('09-JUL-15 03.40.15.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),10,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (18,1,270,to_timestamp('09-JUL-15 03.41.15.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,-130);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (19,12,140,to_timestamp('09-JUL-15 03.47.15.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),0,-10);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (20,0,1710,to_timestamp('17-JUL-15 12.00.53.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),10,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (21,0,665,to_timestamp('26-JUL-15 10.36.49.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),5,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (22,7,31500,to_timestamp('30-JUL-15 03.19.37.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),10,1000);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (23,7,450,to_timestamp('08-JAN-16 11.04.45.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),10,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (24,0,950,to_timestamp('08-JAN-16 11.06.37.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),5,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (25,1,29925,to_timestamp('08-JAN-16 11.10.22.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),5,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (26,12,6127.5,to_timestamp('08-JAN-16 11.11.49.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),5,2127.5);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (27,1,43700,to_timestamp('08-JAN-16 11.25.52.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),5,700);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (28,12,228000,to_timestamp('08-JAN-16 11.38.19.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),5,218000);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (29,0,4465,to_timestamp('08-JAN-16 11.39.34.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'),5,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (30,13,114000,to_timestamp('08-JAN-16 12.07.19.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),5,14000);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (31,0,1900,to_timestamp('08-JAN-16 12.07.55.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),5,0);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (32,6,159600,to_timestamp('08-JAN-16 12.16.21.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),5,59600);
Insert into SYSTEM.SALE1 (BILLNO,ID,BILL,SALE_DATE,DISCOUNT,BALANCE) values (33,0,1140,to_timestamp('08-JAN-16 12.17.13.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'),5,0);

---------------------------------------------------
--   END DATA FOR TABLE SALE1
---------------------------------------------------



 --------------------------------------------------------
--  DDL for Table CUSTOMERDEBIT
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."CUSTOMERDEBIT" 
   (	"DEBITNO" NUMBER, 
	"BILLNO" NUMBER, 
	"AMOUNT" FLOAT(63), 
	"DEBITDATE" DATE
   ) ;
   
   
--------------------------------------------------------
--  Constraints for Table CUSTOMERDEBIT
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."CUSTOMERDEBIT" ADD PRIMARY KEY ("DEBITNO") ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table CUSTOMERDEBIT
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."CUSTOMERDEBIT" ADD FOREIGN KEY ("BILLNO")
	  REFERENCES "SYSTEM"."SALE1" ("BILLNO") ENABLE;

	  
--------------------------------------------------------
--  DDL for Trigger CUSTOMERDEBIT
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."CUSTOMERDEBIT" 
before insert on CustomerDebit
for each row 
begin
  update sale1 set balance = balance-:new.Amount where billno=:new.billno;
end;


/
ALTER TRIGGER "SYSTEM"."CUSTOMERDEBIT" ENABLE;
	  
---------------------------------------------------
--   DATA FOR TABLE CUSTOMERDEBIT
--   FILTER = none used
---------------------------------------------------
REM INSERTING into SYSTEM.CUSTOMERDEBIT
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (18,15,2000,to_timestamp('09-JUL-15 05.22.46.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (19,15,34,to_timestamp('09-JUL-15 05.33.48.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (1,1,32300,to_timestamp('07-JUN-15 06.02.54.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (2,2,64300,to_timestamp('08-JUN-15 07.14.01.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (3,3,64600,to_timestamp('08-JUN-15 07.30.48.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (4,4,193800,to_timestamp('13-JUN-15 01.39.15.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (5,5,321500,to_timestamp('14-JUN-15 05.25.39.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (6,6,348500,to_timestamp('14-JUN-15 05.27.32.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (7,7,3000,to_timestamp('17-JUN-15 05.08.46.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (8,8,25500,to_timestamp('18-JUN-15 08.43.56.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (9,9,28000,to_timestamp('21-JUN-15 08.18.24.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (10,10,200,to_timestamp('23-JUN-15 08.04.42.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (11,11,47000,to_timestamp('23-JUN-15 08.17.02.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (12,12,70050,to_timestamp('24-JUN-15 07.50.10.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (13,13,60,to_timestamp('24-JUN-15 07.56.16.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (14,14,86940,to_timestamp('27-JUN-15 05.04.22.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (15,15,20000,to_timestamp('30-JUN-15 12.48.08.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (16,16,10000,to_timestamp('30-JUN-15 07.02.33.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (0,18,200,to_timestamp('09-JUL-15 03.41.18.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (17,19,100,to_timestamp('09-JUL-15 03.47.15.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (20,15,3000,to_timestamp('09-JUL-15 06.12.31.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (21,19,50,to_timestamp('10-JUL-15 08.51.07.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (22,22,3000,to_timestamp('30-JUL-15 03.19.39.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (23,15,2000,to_timestamp('31-JUL-15 06.50.31.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (24,16,2000,to_timestamp('06-AUG-15 06.06.42.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (25,15,1000,to_timestamp('08-JAN-16 10.19.21.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (26,15,100,to_timestamp('08-JAN-16 10.25.24.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (27,22,8000,to_timestamp('08-JAN-16 10.38.17.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (28,22,500,to_timestamp('08-JAN-16 10.53.33.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (29,22,1000,to_timestamp('08-JAN-16 10.56.41.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (30,23,400,to_timestamp('08-JAN-16 11.04.45.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (31,25,10000,to_timestamp('08-JAN-16 11.10.22.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (32,26,4000,to_timestamp('08-JAN-16 11.11.49.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (33,23,50,to_timestamp('08-JAN-16 11.16.12.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (34,25,19925,to_timestamp('08-JAN-16 11.21.30.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (35,27,40000,to_timestamp('08-JAN-16 11.25.52.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (36,27,3000,to_timestamp('08-JAN-16 11.29.38.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (37,22,9000,to_timestamp('08-JAN-16 11.33.03.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (38,28,10000,to_timestamp('08-JAN-16 11.38.20.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (39,22,5000,to_timestamp('08-JAN-16 12.03.32.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (40,30,100000,to_timestamp('08-JAN-16 12.07.19.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (41,32,100000,to_timestamp('08-JAN-16 12.16.21.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (42,22,1000,to_timestamp('08-JAN-16 12.22.53.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (43,22,1000,to_timestamp('08-JAN-16 05.05.19.000000000 PM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (44,22,1000,to_timestamp('09-JAN-16 02.35.35.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (45,22,500,to_timestamp('09-JAN-16 02.47.24.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));
Insert into SYSTEM.CUSTOMERDEBIT (DEBITNO,BILLNO,AMOUNT,DEBITDATE) values (46,22,500,to_timestamp('09-JAN-16 02.54.39.000000000 AM','DD-MON-RR HH.MI.SS.FF AM'));

---------------------------------------------------
--   END DATA FOR TABLE CUSTOMERDEBIT
---------------------------------------------------


--------------------------------------------------------
--  DDL for Table CUST_PURCH
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."CUST_PURCH" 
   (	"PID" NUMBER(*,0), 
	"BILLNO" NUMBER(*,0), 
	"QUANTITY" NUMBER(*,0)
   ) ;
------------------
--------------------------------------------------------
--  Constraints for Table CUST_PURCH
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."CUST_PURCH" ADD PRIMARY KEY ("PID", "BILLNO") ENABLE;

--------------------------------------------------------
--  Ref Constraints for Table CUST_PURCH
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."CUST_PURCH" ADD FOREIGN KEY ("BILLNO")
	  REFERENCES "SYSTEM"."SALE1" ("BILLNO") ENABLE;
 
  ALTER TABLE "SYSTEM"."CUST_PURCH" ADD FOREIGN KEY ("PID")
	  REFERENCES "SYSTEM"."PRODUCT1" ("PID") ENABLE;



--------------------------------------------------------
--  DDL for Trigger SALE1
--------------------------------------------------------

CREATE OR REPLACE TRIGGER "SYSTEM"."SALE1" 
before insert on cust_purch
for each row 
declare ava int;
begin
  select avail into ava from available1 where pid=:new.pid;
  if(ava >= 0) then 
  update available1 set avail=ava-:new.quantity where pid=:new.pid;
  else 
      raise_application_error (-20999,'insuffiecient stock available');
  end if;
end;

/
---------------------------------------------------
--   DATA FOR TABLE CUST_PURC
---------------------------------------------------
REM INSERTING into SYSTEM.CUST_PURCH
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (1,1,1);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (2,1,1);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (2,2,1);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (1,2,2);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (1,3,1);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (2,3,2);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (1,4,6);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (2,4,6);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (1,5,10);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (2,5,5);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (1,6,10);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (2,6,5);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (3,6,9);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (2,7,10);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (2,8,5);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (3,8,8);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (4,9,4);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (6,10,10);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (3,11,4);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (4,11,5);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (5,12,5);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (4,12,10);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (5,13,6);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (2,14,2);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (1,14,3);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (6,15,12);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (5,15,2);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (1,15,1);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (6,16,12);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (3,16,5);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (7,17,1);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (6,18,11);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (5,18,5);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (6,19,5);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (5,19,4);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (2,20,5);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (21,20,4);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (21,21,7);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (4,22,5);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (6,23,15);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (21,23,10);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (21,24,10);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (1,25,1);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (3,25,1);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (3,26,2);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (5,26,5);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (21,26,4);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (1,27,1);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (4,27,2);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (21,28,5);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (7,28,2);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (21,29,5);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (2,29,14);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (7,30,1);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (21,30,2);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (21,31,5);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (2,31,5);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (21,32,4);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (7,32,1);
Insert into SYSTEM.CUST_PURCH (PID,BILLNO,QUANTITY) values (2,33,4);

---------------------------------------------------
--   END DATA FOR TABLE CUST_PURCH
---------------------------------------------------

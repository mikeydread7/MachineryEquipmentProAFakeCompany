drop sequence user_products_order_id_seq;
drop sequence user_products_id_seq;
drop sequence user_id_seq;

-- unique/primary keys in table referenced by enabled foreign keys fk_user_product_pk
-- and drop the table with  foriegn key first - the frop order matters
ALTER TABLE USER_ORDERS disable constraint fk_user_product_pk;
ALTER TABLE USER_ORDERS disable constraint fk_user_profile_pk;

drop table  USER_ORDERS;
drop table  USER_PRODUCTS;
drop table  USER_PROFILES;

create table USER_PROFILES  
(  
 	userId  	number(4) not null,  
 	userName 	VARCHAR2(32) not null,
 	eyeColor 	VARCHAR2(16) not null,
 	height 		number(4) not null,
 	weight 		number(4) not null,
 	birthday 	date not null,
 	userAge 	number(4) not null ,
 	CONSTRAINT user_profile_pk PRIMARY KEY (userId)
);  

create table USER_PRODUCTS
(
	productId    	number(4) not null,
  	productName  	varchar(32) not null,
  	productVersion  varchar(16) not null,
  	productView		varchar(128) not null,
  	productDesc     varchar(256) not null,
  	CONSTRAINT user_products_pk PRIMARY KEY (productId)
);

create table USER_ORDERS
(
    productOrderId      number(4) not null,
  	productId      		number(4) not null,
  	productUserId		number(4) not null, 
  	productAmount  		number (4) not null,
  	productOrderedDate	date  not null,
  	productSaleDate		date ,
  	productShippedDate	date ,
  	CONSTRAINT products_order_pk PRIMARY KEY (productOrderId),
  	CONSTRAINT fk_user_product_pk
	    FOREIGN KEY (productId)
	    REFERENCES USER_PRODUCTS(productId),
	CONSTRAINT fk_user_profile_pk
	    FOREIGN KEY (productUserId)
	    REFERENCES USER_PROFILES(userId)
);

create sequence user_id_seq increment by 1 start with 1;
create sequence user_products_order_id_seq increment by 1 start with 1;
create sequence user_products_id_seq increment by 1 start with 1;

insert into USER_PROFILES values(user_id_seq.nextval,'Michael','brown',73,140,sysdate-16900,47);  
insert into USER_PROFILES values(user_id_seq.nextval,'Steffan','brown',63,89,sysdate-5000,13);  
insert into USER_PROFILES values(user_id_seq.nextval,'Louis','brown',70,170,sysdate-16990,48);  
insert into USER_PROFILES values(user_id_seq.nextval,'Jeff','brown',68,190,sysdate-16800,43); 
insert into USER_PROFILES values(user_id_seq.nextval,'Sean','brown',58,170,sysdate-16780,30); 


-- add product

insert into  USER_PRODUCTS values(user_products_id_seq.nextval,'IC Counterbalance Fork Lift','C-5 Series','c5-series.png'
,'Capacity: Up to 6500 lb Lift Height: 294 in');

insert into  USER_PRODUCTS values(user_products_id_seq.nextval,'IC Counterbalance Fork Lift','C-G Series','cg-dosan.png'
,'Capacity: Up to 12000 lb Lift Height: 238 in');

insert into  USER_PRODUCTS values(user_products_id_seq.nextval,'Reach Trucks Reaching','RR/RD-series','rr-series.png'
,'Reach Truck Capacity: Up to 4500 lb Lift Height: Up to 442 in');

insert into  USER_PRODUCTS values(user_products_id_seq.nextval,
'Very Narrow Aisle Trucks','TSCP-series','tsp-series.png'
,'VNA Turret Truck Capacity: Up to 3300 lb Lift Height: Up to 675 in');

--add orders

  insert into USER_ORDERS values(user_products_order_id_seq.nextval,
  (select productId from USER_PRODUCTS where productName='IC Counterbalance Fork Lift' and productVersion='C-G Series'),
  1,2,sysdate,sysdate+3,sysdate+3);
  	
  insert into USER_ORDERS values(user_products_order_id_seq.nextval,
  (select productId from USER_PRODUCTS where productName='Reach Trucks Reaching' and productVersion='RR/RD-series'),
  1,2,sysdate,sysdate+3,sysdate+3);
  	
  insert into USER_ORDERS values(user_products_order_id_seq.nextval,
  (select productId from USER_PRODUCTS where productName='Reach Trucks Reaching' and productVersion='RR/RD-series'),
  3,1,sysdate-3,sysdate+4,sysdate+2);
   insert into USER_ORDERS values(user_products_order_id_seq.nextval,
  (select productId from USER_PRODUCTS where productName='Reach Trucks Reaching' and productVersion='RR/RD-series'),
  4,1,sysdate-3,sysdate+4,sysdate+2);

   insert into USER_ORDERS values(user_products_order_id_seq.nextval,
  (select productId from USER_PRODUCTS where productName='Very Narrow Aisle Trucks' and productVersion='TSCP-series'),
  3,1,sysdate-3,sysdate+4,sysdate+2);
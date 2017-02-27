create database eshop default character set utf8;

GRANT ALL PRIVILEGES ON eshop.* TO “eshop”@”localhost” IDENTIFIED BY “eshop”;

use eshop;

DROP TABLE IF EXISTS PRODUCT_ORDER;
DROP TABLE IF EXISTS ORDER_STATUS;
DROP TABLE IF EXISTS PURCHASE_ORDER;
DROP TABLE IF EXISTS PRODUCT_ASPECT;
DROP TABLE IF EXISTS PRODUCT;
DROP TABLE IF EXISTS STATUS;
DROP TABLE IF EXISTS BRAND;
DROP TABLE IF EXISTS ASPECT;
DROP TABLE IF EXISTS CATEGORY;
DROP TABLE IF EXISTS ADDRESS;
DROP TABLE IF EXISTS USER_PROFILE;
DROP TABLE IF EXISTS DEPARTMENT;

CREATE TABLE DEPARTMENT (
  DEPARTMENT_ID   INT(4) NOT NULL AUTO_INCREMENT,
  DEPARTMENT_NAME VARCHAR(25) NOT NULL,
  PRIMARY KEY (DEPARTMENT_ID));

CREATE TABLE USER_PROFILE (
  USER_ID        INT(10) NOT NULL AUTO_INCREMENT,
  FIRST_NAME     VARCHAR(20) NOT NULL,
  LAST_NAME      VARCHAR(20) NOT NULL,
  EMAIL_ID       VARCHAR(30) NOT NULL,
  PASSWORD       VARCHAR(20) NOT NULL,
  MOBILE_NUMBER  VARCHAR(15) NOT NULL,
  GENDER         ENUM('M', 'F') NOT NULL DEFAULT 'M',
  PROFILE_IND    ENUM('A', 'U', 'S') NOT NULL DEFAULT 'U',
  CREATED_DATE   TIMESTAMP NOT NULL DEFAULT current_timestamp,
  STATUS_IND     ENUM('N', 'A', 'I') NOT NULL DEFAULT 'N',
  END_DATE       TIMESTAMP NULL,
  DEPARTMENT_ID  INT(4) NOT NULL,
  EMAIL_PREF_IND ENUM('Y', 'N') NOT NULL DEFAULT 'Y',
  SMS_PREF_IND   ENUM('Y', 'N') NOT NULL DEFAULT 'Y',
  PRIMARY KEY (USER_ID),
  UNIQUE INDEX EMAIL_ID_UNIQUE (EMAIL_ID ASC),
  INDEX DEPARTMENT_ID_IDX (DEPARTMENT_ID ASC),
  CONSTRAINT DEPARTMENT_ID
    FOREIGN KEY (DEPARTMENT_ID)
    REFERENCES DEPARTMENT (DEPARTMENT_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ADDRESS (
  ADDRESS_ID    INT(10) NOT NULL AUTO_INCREMENT,
  USER_ID       INT(10) NOT NULL,
  ADDRESS_LINE1 VARCHAR(100) NOT NULL,
  ADDRESS_LINE2 VARCHAR(100) NULL,
  CITY          VARCHAR(20) NOT NULL,
  STATE         VARCHAR(20) NOT NULL,
  ZIP_CODE      VARCHAR(10) NOT NULL,
  IS_DFLT_IND   ENUM('Y', 'N') NOT NULL,
  PRIMARY KEY (ADDRESS_ID),
  INDEX USER_ID_IDX (USER_ID ASC),
  CONSTRAINT USER_ID
    FOREIGN KEY (USER_ID)
    REFERENCES USER_PROFILE (USER_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
CREATE TABLE CATEGORY (
  CATEGORY_ID   INT(4) NOT NULL AUTO_INCREMENT,
  CATEGORY_NAME VARCHAR(20) NOT NULL,
  CATEGORY_DESC VARCHAR(1000) NOT NULL,
  PRIMARY KEY (CATEGORY_ID))ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
CREATE TABLE ASPECT (
  ASPECT_ID   INT(4) NOT NULL AUTO_INCREMENT,
  CATEGORY_ID INT(4) NOT NULL,
  ASPECT_NAME VARCHAR(20) NOT NULL,
  PRIMARY KEY (ASPECT_ID),
  INDEX CATEGORY_ID_IDX (CATEGORY_ID ASC),
  CONSTRAINT CATEGORY_ID
    FOREIGN KEY (CATEGORY_ID)
    REFERENCES CATEGORY (CATEGORY_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE BRAND (
  BRAND_ID   INT(4) NOT NULL AUTO_INCREMENT,
  BRAND_NAME VARCHAR(50) NOT NULL,
  PRIMARY KEY (BRAND_ID))ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE STATUS (
  STATUS_ID           INT(2) NOT NULL AUTO_INCREMENT,
  STATUS_NAME         VARCHAR(20) NOT NULL,
  IS_CNCL_ALLOWED_IND ENUM('Y', 'N') NOT NULL,
  PRIMARY KEY (STATUS_ID))ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE PRODUCT (
  PRODUCT_ID       INT(10) NOT NULL AUTO_INCREMENT,
  PRODUCT_NAME     VARCHAR(50) NOT NULL,
  PRODUCT_DESC     VARCHAR(1000) NOT NULL,
  CATEGORY_ID      INT(4) NOT NULL,
  PRODUCT_TYPE     ENUM('HW', 'SW', 'SE') NOT NULL,   
  IMG_PATH         VARCHAR(100) NULL,
  BRAND_ID         INT(4) NOT NULL,
  MSRP_PER_UNIT    FLOAT(8,2) NOT NULL,
  DISC_PERCENT     FLOAT(4,2) NULL,
  PRICE_PER_UNIT   FLOAT(8,2) NOT NULL,
  RATING           INT(2) NULL,
  IS_PUBLISHED_IND ENUM('Y', 'N') NOT NULL,
  PUBLISHED_DATE   TIMESTAMP NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (PRODUCT_ID),
  INDEX CATEGORY_ID_IDX (CATEGORY_ID ASC),
  INDEX BRAND_ID_IDX (BRAND_ID ASC),
  CONSTRAINT fk_CATEGORY_ID
    FOREIGN KEY (CATEGORY_ID)
    REFERENCES CATEGORY (CATEGORY_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT BRAND_ID
    FOREIGN KEY (BRAND_ID)
    REFERENCES BRAND (BRAND_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE PRODUCT_ASPECT (
  PRODUCT_ID INT(10) NOT NULL,
  ASPECT_ID  INT(4) NOT NULL,
  ASPECT_VAL VARCHAR(100) NOT NULL,
  PRIMARY KEY (PRODUCT_ID, ASPECT_ID),
  INDEX ASPECT_ID_IDX (ASPECT_ID ASC),
  CONSTRAINT PRODUCT_ID
    FOREIGN KEY (PRODUCT_ID)
    REFERENCES PRODUCT (PRODUCT_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT ASPECT_ID
    FOREIGN KEY (ASPECT_ID)
    REFERENCES ASPECT (ASPECT_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION) ENGINE=InnoDB DEFAULT CHARSET=utf8; 

CREATE TABLE PURCHASE_ORDER (
  ORDER_ID   INT NOT NULL AUTO_INCREMENT,
  USER_ID    INT(10) NOT NULL,
  ADDRESS_ID INT(10) NOT NULL,
  ORDER_DATE TIMESTAMP NOT NULL DEFAULT current_timestamp,
  STATUS_ID  INT(11) NOT NULL,
  PRIMARY KEY (ORDER_ID),
  INDEX STATUS_ID_IDX (STATUS_ID ASC),
  INDEX ADDRESS_ID_IDX (ADDRESS_ID ASC),
  INDEX USER_ID_IDX (USER_ID ASC),
  CONSTRAINT USER_ID_FK
    FOREIGN KEY (USER_ID)
    REFERENCES USER_PROFILE (USER_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT ADDRESS_ID
    FOREIGN KEY (ADDRESS_ID)
    REFERENCES ADDRESS (ADDRESS_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT STATUS_ID
    FOREIGN KEY (STATUS_ID)
    REFERENCES STATUS (STATUS_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ORDER_STATUS (
  ORDER_ID         INT(11) NOT NULL,
  STATUS_ID        INT(2) NOT NULL,
  LAST_MODIFIED_TM TIMESTAMP NOT NULL DEFAULT current_timestamp,
  INDEX ORDER_ID_IDX (ORDER_ID ASC),
  INDEX STATUS_ID_IDX (STATUS_ID ASC),
  CONSTRAINT ORDER_ID_FK
    FOREIGN KEY (ORDER_ID)
    REFERENCES PURCHASE_ORDER (ORDER_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT STATUS_ID_FK
    FOREIGN KEY (STATUS_ID)
    REFERENCES STATUS (STATUS_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)ENGINE=InnoDB DEFAULT CHARSET=utf8; 

CREATE TABLE PRODUCT_ORDER (
  ORDER_ID   INT(11) NOT NULL,
  PRODUCT_ID INT(10) NOT NULL,
  QTY INT(4) NOT NULL,
  PRIMARY KEY (ORDER_ID, PRODUCT_ID),
  INDEX ORDER_ID_IDX (ORDER_ID ASC),
  INDEX PRODUCT_ID_IDX (PRODUCT_ID ASC),
  CONSTRAINT ORDER_ID
    FOREIGN KEY (ORDER_ID)
    REFERENCES PURCHASE_ORDER (ORDER_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT PRODUCT_ID_FK
    FOREIGN KEY (PRODUCT_ID)
    REFERENCES PRODUCT (PRODUCT_ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)ENGINE=InnoDB DEFAULT CHARSET=utf8;

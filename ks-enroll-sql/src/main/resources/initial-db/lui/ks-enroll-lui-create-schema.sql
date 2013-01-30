

-----------------------------------------------------------------------------
-- KSEN_LUI
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_LUI';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUI CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_LUI
(
      ID VARCHAR2(255)
        , OBJ_ID VARCHAR2(36)
        , LUI_TYPE VARCHAR2(255) NOT NULL
        , LUI_STATE VARCHAR2(255) NOT NULL
        , NAME VARCHAR2(255)
        , DESCR_PLAIN VARCHAR2(4000)
        , DESCR_FORMATTED VARCHAR2(4000)
        , EFF_DT TIMESTAMP
        , EXPIR_DT TIMESTAMP
        , CLU_ID VARCHAR2(255)
        , ATP_ID VARCHAR2(255)
        , SCHEDULE_ID VARCHAR2(255)
        , MAX_SEATS NUMBER(10)
        , MIN_SEATS NUMBER(10)
        , REF_URL VARCHAR2(255)
        , VER_NBR NUMBER(19) NOT NULL
        , CREATETIME TIMESTAMP NOT NULL
        , CREATEID VARCHAR2(255) NOT NULL
        , UPDATETIME TIMESTAMP
        , UPDATEID VARCHAR2(255)
    

)
/

ALTER TABLE KSEN_LUI
    ADD CONSTRAINT KSEN_LUIP1
PRIMARY KEY (ID)
/


CREATE INDEX KSEN_LUI_I1 
  ON KSEN_LUI 
  (CLU_ID, ATP_ID)
/





-----------------------------------------------------------------------------
-- KSEN_LUICAPACITY_RELTN
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_LUICAPACITY_RELTN';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUICAPACITY_RELTN CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_LUICAPACITY_RELTN
(
      ID VARCHAR2(255)
        , OBJ_ID VARCHAR2(36)
        , LUI_ID VARCHAR2(255)
        , LUI_CAPACITY_ID VARCHAR2(255)
        , VER_NBR NUMBER(19) NOT NULL
        , CREATETIME TIMESTAMP NOT NULL
        , CREATEID VARCHAR2(255) NOT NULL
        , UPDATETIME TIMESTAMP
        , UPDATEID VARCHAR2(255)
    

)
/

ALTER TABLE KSEN_LUICAPACITY_RELTN
    ADD CONSTRAINT KSEN_LUICAPACITY_RELTNP1
PRIMARY KEY (ID)
/


CREATE INDEX KSEN_LUICAPACITY_RELTN_IF1 
  ON KSEN_LUICAPACITY_RELTN 
  (LUI_ID)
/
CREATE INDEX KSEN_LUICAPACITY_RELTN_IF2 
  ON KSEN_LUICAPACITY_RELTN 
  (LUI_CAPACITY_ID)
/





-----------------------------------------------------------------------------
-- KSEN_LUILUI_RELTN
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_LUILUI_RELTN';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUILUI_RELTN CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_LUILUI_RELTN
(
      ID VARCHAR2(255)
        , OBJ_ID VARCHAR2(36)
        , LUILUI_RELTN_TYPE VARCHAR2(255) NOT NULL
        , LUILUI_RELTN_STATE VARCHAR2(255) NOT NULL
        , NAME VARCHAR2(255)
        , DESCR_PLAIN VARCHAR2(4000)
        , DESCR_FORMATTED VARCHAR2(4000)
        , LUI_ID VARCHAR2(255)
        , RELATED_LUI_ID VARCHAR2(255)
        , EFF_DT TIMESTAMP
        , EXPIR_DT TIMESTAMP
        , VER_NBR NUMBER(19) NOT NULL
        , CREATETIME TIMESTAMP NOT NULL
        , CREATEID VARCHAR2(255) NOT NULL
        , UPDATETIME TIMESTAMP
        , UPDATEID VARCHAR2(255)
    

)
/

ALTER TABLE KSEN_LUILUI_RELTN
    ADD CONSTRAINT KSEN_LUILUI_RELTNP1
PRIMARY KEY (ID)
/


CREATE INDEX KSEN_LUILUI_RELTN_I1 
  ON KSEN_LUILUI_RELTN 
  (LUILUI_RELTN_TYPE, RELATED_LUI_ID)
/
CREATE INDEX KSEN_LUILUI_RELTN_I2 
  ON KSEN_LUILUI_RELTN 
  (LUILUI_RELTN_TYPE, LUI_ID)
/





-----------------------------------------------------------------------------
-- KSEN_LUILUI_RELTN_ATTR
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_LUILUI_RELTN_ATTR';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUILUI_RELTN_ATTR CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_LUILUI_RELTN_ATTR
(
      ID VARCHAR2(255)
        , OBJ_ID VARCHAR2(36)
        , ATTR_KEY VARCHAR2(255)
        , ATTR_VALUE VARCHAR2(2000)
        , OWNER_ID VARCHAR2(255)
    

)
/

ALTER TABLE KSEN_LUILUI_RELTN_ATTR
    ADD CONSTRAINT KSEN_LUILUI_RELTN_ATTRP1
PRIMARY KEY (ID)
/







-----------------------------------------------------------------------------
-- KSEN_LUI_ATTR
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_LUI_ATTR';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUI_ATTR CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_LUI_ATTR
(
      ID VARCHAR2(255)
        , OBJ_ID VARCHAR2(36)
        , ATTR_KEY VARCHAR2(255)
        , ATTR_VALUE VARCHAR2(2000)
        , OWNER_ID VARCHAR2(255)
    

)
/

ALTER TABLE KSEN_LUI_ATTR
    ADD CONSTRAINT KSEN_LUI_ATTRP1
PRIMARY KEY (ID)
/


CREATE INDEX KSEN_LUI_ATTR_IF1 
  ON KSEN_LUI_ATTR 
  (OWNER_ID)
/





-----------------------------------------------------------------------------
-- KSEN_LUI_CAMPUS_LOC
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_LUI_CAMPUS_LOC';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUI_CAMPUS_LOC CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_LUI_CAMPUS_LOC
(
      ID VARCHAR2(255)
        , LUI_ID VARCHAR2(255) NOT NULL
        , CAMPUS_LOC VARCHAR2(255) NOT NULL
    
    , CONSTRAINT KSEN_LUI_CAMPUS_LOC_I1 UNIQUE (LUI_ID, CAMPUS_LOC)

)
/

ALTER TABLE KSEN_LUI_CAMPUS_LOC
    ADD CONSTRAINT KSEN_LUI_CAMPUS_LOCP1
PRIMARY KEY (ID)
/


CREATE INDEX KSEN_LUI_CAMPUS_LOC_IF1 
  ON KSEN_LUI_CAMPUS_LOC 
  (LUI_ID)
/





-----------------------------------------------------------------------------
-- KSEN_LUI_CAPACITY
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_LUI_CAPACITY';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUI_CAPACITY CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_LUI_CAPACITY
(
      ID VARCHAR2(255)
        , OBJ_ID VARCHAR2(36)
        , LUI_CAPACITY_TYPE VARCHAR2(255) NOT NULL
        , LUI_CAPACITY_STATE VARCHAR2(255) NOT NULL
        , NAME VARCHAR2(255)
        , DESCR_PLAIN VARCHAR2(4000)
        , DESCR_FORMATTED VARCHAR2(4000)
        , MAX_SEATS NUMBER(22)
        , PROCESSING_ORDER NUMBER(22)
        , EFF_DT TIMESTAMP
        , EXPIR_DT TIMESTAMP
        , VER_NBR NUMBER(19) NOT NULL
        , CREATETIME TIMESTAMP NOT NULL
        , CREATEID VARCHAR2(255) NOT NULL
        , UPDATETIME TIMESTAMP
        , UPDATEID VARCHAR2(255)
    

)
/

ALTER TABLE KSEN_LUI_CAPACITY
    ADD CONSTRAINT KSEN_LUI_CAPACITYP1
PRIMARY KEY (ID)
/


CREATE INDEX KSEN_LUI_CAPACITY_I1 
  ON KSEN_LUI_CAPACITY 
  (LUI_CAPACITY_TYPE)
/





-----------------------------------------------------------------------------
-- KSEN_LUI_CLUCLU_RELTN
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_LUI_CLUCLU_RELTN';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUI_CLUCLU_RELTN CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_LUI_CLUCLU_RELTN
(
      ID VARCHAR2(255)
        , LUI_ID VARCHAR2(255) NOT NULL
        , CLUCLU_RELTN_ID VARCHAR2(255) NOT NULL
    
    , CONSTRAINT KSEN_LUI_CLUCLU_RELTN_I1 UNIQUE (LUI_ID, CLUCLU_RELTN_ID)

)
/

ALTER TABLE KSEN_LUI_CLUCLU_RELTN
    ADD CONSTRAINT KSEN_LUI_CLUCLU_RELTNP1
PRIMARY KEY (ID)
/


CREATE INDEX KSEN_LUI_CLUCLU_RELTN_IF1 
  ON KSEN_LUI_CLUCLU_RELTN 
  (LUI_ID)
/





-----------------------------------------------------------------------------
-- KSEN_LUI_IDENT
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_LUI_IDENT';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUI_IDENT CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_LUI_IDENT
(
      ID VARCHAR2(255)
        , OBJ_ID VARCHAR2(36)
        , LUI_ID_TYPE VARCHAR2(255) NOT NULL
        , LUI_ID_STATE VARCHAR2(255) NOT NULL
        , LUI_CD VARCHAR2(255)
        , SHRT_NAME VARCHAR2(255)
        , LNG_NAME VARCHAR2(255)
        , DIVISION VARCHAR2(255)
        , SUFX_CD VARCHAR2(255)
        , VARTN VARCHAR2(255)
        , VER_NBR NUMBER(19) NOT NULL
        , CREATETIME TIMESTAMP NOT NULL
        , CREATEID VARCHAR2(255) NOT NULL
        , UPDATETIME TIMESTAMP
        , UPDATEID VARCHAR2(255)
        , LUI_ID VARCHAR2(255)
    

)
/

ALTER TABLE KSEN_LUI_IDENT
    ADD CONSTRAINT KSEN_LUI_IDENTP1
PRIMARY KEY (ID)
/


CREATE INDEX KSEN_LUI_IDENT_IF1 
  ON KSEN_LUI_IDENT 
  (LUI_ID)
/





-----------------------------------------------------------------------------
-- KSEN_LUI_IDENT_ATTR
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_LUI_IDENT_ATTR';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUI_IDENT_ATTR CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_LUI_IDENT_ATTR
(
      ID VARCHAR2(255)
        , OBJ_ID VARCHAR2(36)
        , ATTR_KEY VARCHAR2(255)
        , ATTR_VALUE VARCHAR2(2000)
        , OWNER_ID VARCHAR2(255)
    

)
/

ALTER TABLE KSEN_LUI_IDENT_ATTR
    ADD CONSTRAINT KSEN_LUI_IDENT_ATTRP1
PRIMARY KEY (ID)
/


CREATE INDEX KSEN_LUI_IDENT_ATTR_IF1 
  ON KSEN_LUI_IDENT_ATTR 
  (OWNER_ID)
/





-----------------------------------------------------------------------------
-- KSEN_LUI_LU_CD
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_LUI_LU_CD';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUI_LU_CD CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_LUI_LU_CD
(
      ID VARCHAR2(255)
        , OBJ_ID VARCHAR2(36)
        , LUI_LUCD_TYPE VARCHAR2(255) NOT NULL
        , DESCR_PLAIN VARCHAR2(4000)
        , DESCR_FORMATTED VARCHAR2(4000)
        , VALUE VARCHAR2(255)
        , VER_NBR NUMBER(19) NOT NULL
        , CREATEID VARCHAR2(255) NOT NULL
        , CREATETIME TIMESTAMP NOT NULL
        , UPDATEID VARCHAR2(255)
        , UPDATETIME TIMESTAMP
        , LUI_ID VARCHAR2(255)
    

)
/

ALTER TABLE KSEN_LUI_LU_CD
    ADD CONSTRAINT KSEN_LUI_LU_CDP1
PRIMARY KEY (ID)
/


CREATE INDEX KSEN_LUI_LU_CD_IF1 
  ON KSEN_LUI_LU_CD 
  (LUI_ID)
/





-----------------------------------------------------------------------------
-- KSEN_LUI_LU_CD_ATTR
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_LUI_LU_CD_ATTR';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUI_LU_CD_ATTR CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_LUI_LU_CD_ATTR
(
      ID VARCHAR2(255)
        , OBJ_ID VARCHAR2(36)
        , ATTR_KEY VARCHAR2(255)
        , ATTR_VALUE VARCHAR2(2000)
        , OWNER_ID VARCHAR2(255)
    

)
/

ALTER TABLE KSEN_LUI_LU_CD_ATTR
    ADD CONSTRAINT KSEN_LUI_LU_CD_ATTRP1
PRIMARY KEY (ID)
/


CREATE INDEX KSEN_LUI_LU_CD_ATTR_IF1 
  ON KSEN_LUI_LU_CD_ATTR 
  (OWNER_ID)
/





-----------------------------------------------------------------------------
-- KSEN_LUI_RELATED_LUI_TYPES
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_LUI_RELATED_LUI_TYPES';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUI_RELATED_LUI_TYPES CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_LUI_RELATED_LUI_TYPES
(
      RELATED_LUI_TYPE VARCHAR2(255)
        , LUI_ID VARCHAR2(255)
    

)
/

ALTER TABLE KSEN_LUI_RELATED_LUI_TYPES
    ADD CONSTRAINT KSEN_LUI_RELATED_LUI_TYPESP1
PRIMARY KEY (RELATED_LUI_TYPE,LUI_ID)
/


CREATE INDEX KSEN_LUI_RELATED_LUI_TYPES_IF1 
  ON KSEN_LUI_RELATED_LUI_TYPES 
  (LUI_ID)
/





-----------------------------------------------------------------------------
-- KSEN_LUI_RESULT_VAL_GRP
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_LUI_RESULT_VAL_GRP';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUI_RESULT_VAL_GRP CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_LUI_RESULT_VAL_GRP
(
      LUI_ID VARCHAR2(255) NOT NULL
        , RESULT_VAL_GRP_ID VARCHAR2(255) NOT NULL
    
    , CONSTRAINT KSEN_LUI_RESULT_VAL_GRP_I1 UNIQUE (LUI_ID, RESULT_VAL_GRP_ID)

)
/



CREATE INDEX KSEN_LUI_RESULT_VAL_GRP_IF1 
  ON KSEN_LUI_RESULT_VAL_GRP 
  (LUI_ID)
/





-----------------------------------------------------------------------------
-- KSEN_LUI_UNITS_CONT_OWNER
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_LUI_UNITS_CONT_OWNER';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUI_UNITS_CONT_OWNER CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_LUI_UNITS_CONT_OWNER
(
      LUI_ID VARCHAR2(255) NOT NULL
        , ORG_ID VARCHAR2(255) NOT NULL
    
    , CONSTRAINT KSEN_LUI_UNITS_CONT_OWNER_I1 UNIQUE (LUI_ID, ORG_ID)

)
/



CREATE INDEX KSEN_LUI_UNITS_CONT_OWNER_IF1 
  ON KSEN_LUI_UNITS_CONT_OWNER 
  (LUI_ID)
/





-----------------------------------------------------------------------------
-- KSEN_LUI_UNITS_DEPLOYMENT
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_LUI_UNITS_DEPLOYMENT';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUI_UNITS_DEPLOYMENT CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_LUI_UNITS_DEPLOYMENT
(
      ID VARCHAR2(255)
        , LUI_ID VARCHAR2(255) NOT NULL
        , ORG_ID VARCHAR2(255) NOT NULL
    
    , CONSTRAINT KSEN_LUI_UNITS_DEPLOYMENT_I1 UNIQUE (LUI_ID, ORG_ID)

)
/

ALTER TABLE KSEN_LUI_UNITS_DEPLOYMENT
    ADD CONSTRAINT KSEN_LUI_UNITS_DEPLOYMENTP1
PRIMARY KEY (ID)
/


CREATE INDEX KSEN_LUI_UNITS_DEPLOYMENT_IF1 
  ON KSEN_LUI_UNITS_DEPLOYMENT 
  (LUI_ID)
/


-----------------------------------------------------------------------------
-- KSEN_LUI_SET
-----------------------------------------------------------------------------
DECLARE TEMP NUMBER;
BEGIN
  SELECT COUNT(*) INTO TEMP FROM USER_TABLES WHERE TABLE_NAME = 'KSEN_LUI_SET';
	IF TEMP > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUI_SET CASCADE CONSTRAINTS PURGE'; END IF;
END;
/


CREATE TABLE KSEN_LUI_SET
(
	ID                   VARCHAR2(255) NOT NULL ,
	OBJ_ID               VARCHAR2(36) NULL ,
	LUI_SET_TYPE         VARCHAR2(255) NOT NULL ,
	LUI_SET_STATE        VARCHAR2(255) NOT NULL ,
	NAME                 VARCHAR2(255) NULL ,
	DESCR_PLAIN          VARCHAR2(4000) NULL ,
	DESCR_FORMATTED      VARCHAR2(4000) NULL ,
	EFF_DT               TIMESTAMP(6) NULL ,
	EXPIR_DT             TIMESTAMP(6) NULL ,
	VER_NBR              NUMBER(19) NOT NULL ,
	CREATETIME           TIMESTAMP(6) NOT NULL ,
	CREATEID             VARCHAR2(255) NOT NULL ,
	UPDATETIME           TIMESTAMP(6) NULL ,
	UPDATEID             VARCHAR2(255) NULL
)
/


ALTER TABLE KSEN_LUI_SET
	ADD CONSTRAINT  KSEN_LUI_SET_P PRIMARY KEY (ID)
/

CREATE  INDEX KSEN_LUI_SET_I1 ON KSEN_LUI_SET
(LUI_SET_TYPE   ASC)
/

-----------------------------------------------------------------------------
-- KSEN_LUI_SET_ATTR
-----------------------------------------------------------------------------

DECLARE TEMP NUMBER;
BEGIN
  SELECT COUNT(*) INTO TEMP FROM USER_TABLES WHERE TABLE_NAME = 'KSEN_LUI_SET_ATTR';
	IF TEMP > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUI_SET_ATTR CASCADE CONSTRAINTS PURGE'; END IF;
END;
/


CREATE TABLE KSEN_LUI_SET_ATTR
(
	ID                   VARCHAR2(255) NOT NULL ,
	OBJ_ID               VARCHAR2(36) NULL ,
	ATTR_KEY             VARCHAR2(255) NULL ,
	ATTR_VALUE           VARCHAR2(4000) NULL ,
	OWNER_ID             VARCHAR2(255) NULL
)
/


ALTER TABLE KSEN_LUI_SET_ATTR
	ADD CONSTRAINT  KSEN_LUI_SET_ATTR_P PRIMARY KEY (ID)
/


CREATE  INDEX KSEN_LUI_SET_ATTR_IF1 ON KSEN_LUI_SET_ATTR
(OWNER_ID   ASC)
/

-----------------------------------------------------------------------------
-- KSEN_LUI_SET_LUI
-----------------------------------------------------------------------------

DECLARE TEMP NUMBER;
BEGIN
  SELECT COUNT(*) INTO TEMP FROM USER_TABLES WHERE TABLE_NAME = 'KSEN_LUI_SET_LUI';
	IF TEMP > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_LUI_SET_LUI CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KSEN_LUI_SET_LUI
(
	LUI_ID               VARCHAR2(255) NOT NULL ,
	LUI_SET_ID           VARCHAR2(255) NOT NULL
)
/

ALTER TABLE KSEN_LUI_SET_LUI
	ADD CONSTRAINT  KSEN_LUI_SET_LUI_P PRIMARY KEY (LUI_SET_ID,LUI_ID)
/


CREATE  INDEX KSEN_LUI_SET_LUI_IF1 ON KSEN_LUI_SET_LUI
(LUI_SET_ID   ASC)
/


CREATE  INDEX KSEN_LUI_SET_LUI_IF2 ON KSEN_LUI_SET_LUI
(LUI_ID   ASC)
/
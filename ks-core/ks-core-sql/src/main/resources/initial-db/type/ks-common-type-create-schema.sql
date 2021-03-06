DECLARE temp NUMBER;
BEGIN
  SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_TYPE';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_TYPE CASCADE CONSTRAINTS PURGE'; END IF;
END;
/
DECLARE temp NUMBER;
BEGIN
  SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_TYPE_ATTR';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_TYPE_ATTR CASCADE CONSTRAINTS PURGE'; END IF;
END;
/
DECLARE temp NUMBER;
BEGIN
  SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_TYPETYPE_RELTN';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_TYPETYPE_RELTN CASCADE CONSTRAINTS PURGE'; END IF;
END;
/
DECLARE temp NUMBER;
BEGIN
  SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KSEN_TYPETYPE_RELTN_ATTR';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KSEN_TYPETYPE_RELTN_ATTR CASCADE CONSTRAINTS PURGE'; END IF;
END;
/
CREATE TABLE KSEN_TYPE
(
	TYPE_KEY             VARCHAR2(255) NOT NULL ,
	OBJ_ID               VARCHAR2(36) NULL ,
	NAME                 VARCHAR2(255) NOT NULL ,
	DESCR_PLAIN          VARCHAR2(4000) NULL ,
	DESCR_FORMATTED      VARCHAR2(4000) NULL ,
	EFF_DT               TIMESTAMP(6) NULL ,
	EXPIR_DT             TIMESTAMP(6) NULL ,
	REF_OBJECT_URI       VARCHAR2(255) NULL ,
	SERVICE_URI          VARCHAR2(255) NULL ,
	VER_NBR              NUMBER(19) NOT NULL ,
	CREATETIME           TIMESTAMP(6) NOT NULL ,
	CREATEID             VARCHAR2(255) NOT NULL ,
	UPDATETIME           TIMESTAMP(6) NULL ,
	UPDATEID             VARCHAR2(255) NULL 
)
/
CREATE TABLE KSEN_TYPE_ATTR
(
	ID                   VARCHAR2(255) NOT NULL ,
	OBJ_ID               VARCHAR2(36) NULL ,
	ATTR_KEY             VARCHAR2(255) NULL ,
	ATTR_VALUE           VARCHAR2(4000) NULL ,
	OWNER_ID             VARCHAR2(255) NULL 
)
/

CREATE TABLE KSEN_TYPETYPE_RELTN
(
	ID                   VARCHAR2(255) NOT NULL ,
	OBJ_ID               VARCHAR2(36) NULL ,
	TYPETYPE_RELTN_TYPE  VARCHAR2(255) NOT NULL ,
	TYPETYPE_RELTN_STATE VARCHAR2(255) NOT NULL ,
	EFF_DT               TIMESTAMP(6) NULL ,
	EXPIR_DT             TIMESTAMP(6) NULL ,
	OWNER_TYPE_ID        VARCHAR2(255) NOT NULL ,
	RELATED_TYPE_ID      VARCHAR2(255) NOT NULL ,
	RANK                 NUMBER(10) NULL ,
	VER_NBR              NUMBER(19) NOT NULL ,
	CREATETIME           TIMESTAMP(6) NOT NULL ,
	CREATEID             VARCHAR2(255) NOT NULL ,
	UPDATETIME           TIMESTAMP(6) NULL ,
	UPDATEID             VARCHAR2(255) NULL 
)
/
CREATE TABLE KSEN_TYPETYPE_RELTN_ATTR
(
	ID                   VARCHAR(255) NOT NULL ,
	OBJ_ID               VARCHAR2(36) NULL ,
	ATTR_KEY             VARCHAR2(255) NULL ,
	ATTR_VALUE           VARCHAR2(4000) NULL ,
	OWNER_ID             VARCHAR2(255) NULL 
)
/
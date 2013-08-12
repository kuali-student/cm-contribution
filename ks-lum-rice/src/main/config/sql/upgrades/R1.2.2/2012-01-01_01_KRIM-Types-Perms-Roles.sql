-- *** KS PERMISSION NAMESPACES ***
INSERT INTO KRNS_NMSPC_T (NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, APPL_NMSPC_CD)
  VALUES ('KS-LUM', sys_guid(), 1, 'Kuali Student Learning Unit Management', 'Y', 'STUDENT')
/
INSERT INTO KRNS_NMSPC_T (NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, APPL_NMSPC_CD)
  VALUES ('KS-SYS', sys_guid(), 1, 'Kuali Student System', 'Y', 'STUDENT')
/



-- *** KS PERMISSION TYPES ***

-- KS Permission Type: This uses the permissionPermissionTypeService & is analagous to the default rice Permission type
INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
  VALUES (KRIM_TYP_ID_S.NEXTVAL, sys_guid(), '1', 'KS Permission', 'permissionPermissionTypeService', 'Y', 'KS-SYS')
/

-- Field Permisson Type: This permission type is used to determine field level permissions 
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR) 
  VALUES ('Y',KRIM_TYP_ID_S.NEXTVAL,'Field Permission Type','KS-SYS',sys_guid(),1)
/


-- *** KS DERIVED ROLE PERMISSION TYPES ***

-- Derived Role: KS Non-Adhoc Action Request Role Type Permission Type
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR) 
  VALUES ('Y',KRIM_TYP_ID_S.NEXTVAL,'Derived Role: KS Non-Adhoc Action Request Role Type','KS-SYS',sys_guid(),'ksNonAdhocActionRequestDerivedRoleTypeServiceImpl',1)
/

-- Derived Role: KS Route Log Role Type Permission Type
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR) 
  VALUES ('Y',KRIM_TYP_ID_S.NEXTVAL,'Derived Role: KS Route Log Role Type','KS-SYS',sys_guid(),'ksRouteLogDerivedRoleTypeServiceImpl',1)
/

-- Derived Role: KS Document Editor Permission Type
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y',KRIM_TYP_ID_S.NEXTVAL,'Derived Role: KS Document Editor Role Type','KS-SYS',sys_guid(),'ksEditDocumentRoleTypeService',1)
/

-- Derived Role: KS Document Commenter Permission Type
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y',KRIM_TYP_ID_S.NEXTVAL,'Derived Role: KS Document Commenter Role Type','KS-SYS',sys_guid(),'ksCommentOnDocumentRoleTypeService',1)
/

-- Derived Role Affiliation Permisson Type
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y',KRIM_TYP_ID_S.NEXTVAL,'Derived Role: Affiliation Role Type','KS-SYS',sys_guid(),'ksAffiliationService',1)
/

-- *** KS PERMISSION TEMPLATES ***

-- Field Access Permission Template
---- Permission Template
INSERT INTO KRIM_PERM_TMPL_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Field Permission Type' AND NMSPC_CD='KS-SYS'),'Field Access','KS-SYS',sys_guid(),KRIM_PERM_TMPL_ID_S.NEXTVAL,1)
/
--- DTO Name Attribute Definition
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR,LBL) 
  VALUES ('Y','org.kuali.rice.student.bo.KualiStudentKimAttributes',KRIM_ATTR_DEFN_ID_S.NEXTVAL,'dtoName','KS-SYS',sys_guid(),1, 'DTO Name')
/
--- DTO Name Attribute Definition Link
INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND,KIM_ATTR_DEFN_ID,KIM_TYP_ATTR_ID,KIM_TYP_ID,OBJ_ID,SORT_CD,VER_NBR) 
  VALUES ('Y',KRIM_ATTR_DEFN_ID_S.CURRVAL,KRIM_TYP_ATTR_ID_S.NEXTVAL,(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Field Permission Type' AND NMSPC_CD='KS-SYS'),sys_guid(),'a',1)
/
--- Field Name Attribute Definition
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR,LBL) 
  VALUES ('Y','org.kuali.rice.student.bo.KualiStudentKimAttributes',KRIM_ATTR_DEFN_ID_S.NEXTVAL,'dtoFieldKey','KS-SYS',sys_guid(),1,'DTO Field Key')
/
--- Field Name Attribute Definition Link
INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND,KIM_ATTR_DEFN_ID,KIM_TYP_ATTR_ID,KIM_TYP_ID,OBJ_ID,SORT_CD,VER_NBR) 
  VALUES ('Y',KRIM_ATTR_DEFN_ID_S.CURRVAL,KRIM_TYP_ATTR_ID_S.NEXTVAL,(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Field Permission Type' AND NMSPC_CD='KS-SYS'),sys_guid(),'b',1)
/
--- Access Level Attribute Defintion
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR,LBL) 
  VALUES ('Y','org.kuali.rice.student.bo.KualiStudentKimAttributes',KRIM_ATTR_DEFN_ID_S.NEXTVAL,'fieldAccessLevel','KS-SYS',sys_guid(),1,'Field Access Level')
/
--- Access Level Attribute Definition Link
INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND,KIM_ATTR_DEFN_ID,KIM_TYP_ATTR_ID,KIM_TYP_ID,OBJ_ID,SORT_CD,VER_NBR) 
  VALUES ('Y',KRIM_ATTR_DEFN_ID_S.CURRVAL,KRIM_TYP_ATTR_ID_S.NEXTVAL,(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Field Permission Type' AND NMSPC_CD='KS-SYS'),sys_guid(),'c',1)
/


-- Open Document Permission Template
INSERT INTO KRIM_PERM_TMPL_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','1','Open Document','KS-SYS',sys_guid(),KRIM_PERM_TMPL_ID_S.NEXTVAL,1)
/

-- Withdraw Document Permission Template
INSERT INTO KRIM_PERM_TMPL_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','1','Withdraw Document','KS-SYS',sys_guid(),KRIM_PERM_TMPL_ID_S.NEXTVAL,1)
/

-- Comment on Document Permission Template
INSERT INTO KRIM_PERM_TMPL_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','1','Comment on Document','KS-SYS',sys_guid(),KRIM_PERM_TMPL_ID_S.NEXTVAL,1)
/

-- Edit Document Permission Template
INSERT INTO KRIM_PERM_TMPL_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','1','Edit Document','KS-SYS',sys_guid(),KRIM_PERM_TMPL_ID_S.NEXTVAL,1)
/


-- Use Screen Permission Template
---- Permission Template
INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, ACTV_IND, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID)
  VALUES (KRIM_PERM_TMPL_ID_S.NEXTVAL, sys_guid(),1,'Y','KS-SYS','Use Screen','Used to create KS screen permissions', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Default'))
/
---- Screen Component Attribute Definition
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,LBL,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.student.bo.KualiStudentKimAttributes',KRIM_ATTR_DEFN_ID_S.NEXTVAL,'Screen Component','screenComponent','KS-SYS',sys_guid(),1)
/
---- Screen Component Attribute Definition Link
INSERT INTO KRIM_TYP_ATTR_T (ACTV_IND,KIM_ATTR_DEFN_ID,KIM_TYP_ATTR_ID,KIM_TYP_ID,OBJ_ID,SORT_CD,VER_NBR)
  VALUES ('Y',KRIM_ATTR_DEFN_ID_S.CURRVAL,KRIM_TYP_ATTR_ID_S.NEXTVAL,(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Default'),sys_guid(),'a',1)
/

-- Add Addhoc Reviewer Template
INSERT INTO KRIM_PERM_TMPL_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','1','Add Adhoc Reviewer','KS-SYS',sys_guid(),KRIM_PERM_TMPL_ID_S.NEXTVAL,1)
/


-- *** KS ROLES ***

-- KS System User Role
INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR, DESC_TXT) 
  VALUES ('Y','1','KS-SYS',sys_guid(),KRIM_ROLE_ID_S.NEXTVAL,'Student System User Role',1,'This role provides system user privileges in KS CM Application')
/

-- KS CM Admin Role
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES (KRIM_ROLE_ID_S.NEXTVAL, sys_guid(),'1','Kuali Student CM Admin', 'KS-SYS','This role provides adminstrative privileges to KS CM application.(eg. Admin Screens)', '1','Y',null)
/

-- KS CM User Role
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES (KRIM_ROLE_ID_S.NEXTVAL, sys_guid(),'1','Kuali Student CM User', 'KS-SYS','General Kuali Student Curriculum Management User', '1','Y',null)
/

-- Derived Role: Affiliation
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT) 
  VALUES (KRIM_ROLE_ID_S.NEXTVAL, sys_guid(),'1','Derived Role : Affiliation', 'KS-SYS','This role identifies users who are affiliates',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Derived Role: Affiliation Role Type'),'Y',null)
/

-- Derived Role: Approve Request Recipient
INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
  VALUES ('Y',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Derived Role: KS Non-Adhoc Action Request Role Type') ,'KS-SYS',sys_guid(),KRIM_ROLE_ID_S.NEXTVAL,'Derived Role: Approve Request Recipient',1)
/

-- Derived Role: Acknowledge Request Recipient
INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
  VALUES ('Y',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Derived Role: KS Non-Adhoc Action Request Role Type'),'KS-SYS',sys_guid(),KRIM_ROLE_ID_S.NEXTVAL,'Derived Role: Acknowledge Request Recipient',1)
/

-- Derived Role: FYI Request Recipient
INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
  VALUES ('Y',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Derived Role: KS Non-Adhoc Action Request Role Type'),'KS-SYS',sys_guid(),KRIM_ROLE_ID_S.NEXTVAL,'Derived Role: FYI Request Recipient',1)
/

-- Derived Role: Initiator or Reviewer
INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
  VALUES ('Y',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Derived Role: KS Route Log Role Type'),'KS-SYS',sys_guid(),KRIM_ROLE_ID_S.NEXTVAL,'Derived Role: Initiator or Reviewer',1)
/

-- Derived Role: Router
INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
  VALUES ('Y',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Derived Role: KS Route Log Role Type'),'KS-SYS',sys_guid(),KRIM_ROLE_ID_S.NEXTVAL,'Derived Role: Router',1)
/

-- Derived Role: Document Editor
INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
  VALUES ('Y',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Derived Role: KS Document Editor Role Type'),'KS-SYS',sys_guid(),KRIM_ROLE_ID_S.NEXTVAL,'Derived Role: Document Editor',1)
/

-- Derived Role: Document Commenter
INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
  VALUES ('Y',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Derived Role: KS Document Commenter Role Type'),'KS-SYS',sys_guid(),KRIM_ROLE_ID_S.NEXTVAL,'Derived Role: Document Commenter',1)
/




-- *** KS PERMISSIONS (& ROLE ASSIGNMENTS)***

-- KIM Assign Role Permissions
INSERT INTO KRIM_PERM_T (ACTV_IND,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR,NM)
  VALUES ('Y','KS-SYS',sys_guid(),KRIM_PERM_ID_S.NEXTVAL,'35',1, 'Assign Role')
/
INSERT INTO KRIM_PERM_ATTR_DATA_T (VER_NBR,OBJ_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_DATA_ID,PERM_ID,ATTR_VAL)
   VALUES (1,sys_guid(),'20','4',KRIM_ATTR_DATA_ID_S.NEXTVAL,KRIM_PERM_ID_S.CURRVAL,'KS*')
/
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,'63',KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Kuali Student CM Admin'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/


-- KIM Grant Permission Permission
INSERT INTO KRIM_PERM_T (ACTV_IND,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR,NM)
  VALUES ('Y','KS-SYS',sys_guid(),KRIM_PERM_ID_S.NEXTVAL,'36',1, 'Grant Permission')
/
INSERT INTO KRIM_PERM_ATTR_DATA_T (VER_NBR,OBJ_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_DATA_ID,PERM_ID,ATTR_VAL)
   VALUES (1,sys_guid(),'20','4',KRIM_ATTR_DATA_ID_S.NEXTVAL,KRIM_PERM_ID_S.CURRVAL,'KS*')
/
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,'63',KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Kuali Student CM Admin'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/


-- KIM Grant Responsibility Permission
INSERT INTO KRIM_PERM_T (ACTV_IND,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR,NM)
  VALUES ('Y','KS-SYS',sys_guid(),KRIM_PERM_ID_S.NEXTVAL,'37',1, 'Grant Responsibility')
/
INSERT INTO KRIM_PERM_ATTR_DATA_T (VER_NBR,OBJ_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_DATA_ID,PERM_ID,ATTR_VAL)
   VALUES (1,sys_guid(),'20','4',KRIM_ATTR_DATA_ID_S.NEXTVAL,KRIM_PERM_ID_S.CURRVAL,'KS*')
/
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,'63',KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Kuali Student CM Admin'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/

-- KIM Populate Group Permission
INSERT INTO KRIM_PERM_T (ACTV_IND,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR,NM)
  VALUES ('Y','KS-SYS',sys_guid(),KRIM_PERM_ID_S.NEXTVAL,'38',1, 'Populate Group')
/
INSERT INTO KRIM_PERM_ATTR_DATA_T (VER_NBR,OBJ_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_DATA_ID,PERM_ID,ATTR_VAL)
   VALUES (1,sys_guid(),'20','4',KRIM_ATTR_DATA_ID_S.NEXTVAL,KRIM_PERM_ID_S.CURRVAL,'KS*')
/
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,'63',KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Kuali Student CM Admin'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/


-- Use Curriculum Review Permission
--- Permission
INSERT INTO KRIM_PERM_T (ACTV_IND,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR,NM, DESC_TXT)
  VALUES ('Y','KS-SYS',sys_guid(),KRIM_PERM_ID_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD='KS-SYS' AND NM='Use Screen'),1,'Use Curriculum Review','Allows the user to use the curriculum review checkbox')
/
--- Permission Detail
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,PERM_ID,VER_NBR)
  VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL,'useCurriculumReview',(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='screenComponent' AND NMSPC_CD='KS-SYS'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Default'),sys_guid(),KRIM_PERM_ID_S.CURRVAL,1)
/
--- Assign to KS CM Admin Role
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Kuali Student CM Admin'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/


--- Modify Clu Dialog Permission
-- Permission
INSERT INTO KRIM_PERM_T (ACTV_IND,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR,NM, DESC_TXT)
  VALUES ('Y','KS-SYS',sys_guid(),KRIM_PERM_ID_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD='KS-SYS' AND NM='Use Screen'),1,'Modify Clu Without Version Dialog','Allows the user to modify the clu without a version')
/
-- Permission Detail
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,PERM_ID,VER_NBR)
  VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL,'cluModifyItem',(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='screenComponent' AND NMSPC_CD='KS-SYS'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Default'),sys_guid(),KRIM_PERM_ID_S.CURRVAL,1)
/
-- Assign to KS CM Admin Role
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Kuali Student CM Admin'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/


-- Create Course Proposal Permission
--- Permission
INSERT INTO KRIM_PERM_T (ACTV_IND,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR,NM, DESC_TXT)
  VALUES ('Y','KS-SYS',sys_guid(),KRIM_PERM_ID_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD='KR-SYS' AND NM='Initiate Document'),1, 'Create Course By Proposal', 'Allow user to create course proposal')
/
-- Permission Detail
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, VER_NBR,OBJ_ID,KIM_ATTR_DEFN_ID,KIM_TYP_ID,PERM_ID,ATTR_VAL)
   VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, 1,sys_guid(),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='documentTypeName' AND NMSPC_CD='KR-WKFLW'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Document Type (Permission)' AND NMSPC_CD='KR-SYS'),KRIM_PERM_ID_S.CURRVAL,'kuali.proposal.type.course.create')
/
--- Assign to KS CM User Role
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(), KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Kuali Student CM User'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/
--- Assign to KS CM Admin Role
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(), KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Kuali Student CM Admin'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/

-- Create Modify Course Proposal Permission
--- Permission
INSERT INTO KRIM_PERM_T (ACTV_IND,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR,NM, DESC_TXT)
  VALUES ('Y','KS-SYS',sys_guid(),KRIM_PERM_ID_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD='KR-SYS' AND NM='Initiate Document'),1, 'Modify Course By Proposal', 'Allow user to create a modify course proposal')
/
-- Permission Detail
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, VER_NBR,OBJ_ID,KIM_ATTR_DEFN_ID,KIM_TYP_ID,PERM_ID,ATTR_VAL)
   VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, 1,sys_guid(),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='documentTypeName' AND NMSPC_CD='KR-WKFLW'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Document Type (Permission)' AND NMSPC_CD='KR-SYS'),KRIM_PERM_ID_S.CURRVAL,'kuali.proposal.type.course.modify')
/
--- Assign to KS CM User Role
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(), KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Kuali Student CM User'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/
--- Assign to KS CM Admin Role
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(), KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Kuali Student CM Admin'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/


-- Create Course Admin Proposal Permission
--- Permission
INSERT INTO KRIM_PERM_T (ACTV_IND,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR,NM, DESC_TXT)
  VALUES ('Y','KS-SYS',sys_guid(),KRIM_PERM_ID_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD='KR-SYS' AND NM='Initiate Document'),1, 'Create Course By Admin Proposal', 'Allow user to create course admin proposal')
/
-- Permission Detail
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, VER_NBR,OBJ_ID,KIM_ATTR_DEFN_ID,KIM_TYP_ID,PERM_ID,ATTR_VAL)
   VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, 1,sys_guid(),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='documentTypeName' AND NMSPC_CD='KR-WKFLW'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Document Type (Permission)' AND NMSPC_CD='KR-SYS'),KRIM_PERM_ID_S.CURRVAL,'kuali.proposal.type.course.create.admin')
/
--- Assign to KS CM Admin Role
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(), KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Kuali Student CM Admin'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/


-- Create Modify Course by Admin Proposal Permission
--- Permission
INSERT INTO KRIM_PERM_T (ACTV_IND,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR,NM, DESC_TXT)
  VALUES ('Y','KS-SYS',sys_guid(),KRIM_PERM_ID_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD='KR-SYS' AND NM='Initiate Document'),1, 'Modify Course By Admin Proposal', 'Allow user to create modify course admin proposal')
/
-- Permission Detail
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, VER_NBR,OBJ_ID,KIM_ATTR_DEFN_ID,KIM_TYP_ID,PERM_ID,ATTR_VAL)
   VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, 1,sys_guid(),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='documentTypeName' AND NMSPC_CD='KR-WKFLW'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Document Type (Permission)' AND NMSPC_CD='KR-SYS'),KRIM_PERM_ID_S.CURRVAL,'kuali.proposal.type.course.modify.admin')
/
--- Assign to KS CM Admin Role
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(), KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Kuali Student CM Admin'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/


-- Create Program Proposal Permission 
--- Permission
INSERT INTO KRIM_PERM_T (ACTV_IND,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR,NM,DESC_TXT)
  VALUES ('Y','KS-SYS',sys_guid(),KRIM_PERM_ID_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD='KR-SYS' AND NM='Initiate Document'),1, 'Create Program By Proposal', 'Allow user to create a program proposal')
/
-- Permission Detail
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, VER_NBR,OBJ_ID,KIM_ATTR_DEFN_ID,KIM_TYP_ID,PERM_ID,ATTR_VAL)
   VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, 1,sys_guid(),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='documentTypeName' AND NMSPC_CD='KR-WKFLW'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Document Type (Permission)' AND NMSPC_CD='KR-SYS'),KRIM_PERM_ID_S.CURRVAL,'kuali.proposal.type.majorDiscipline.create')
/
-- Assign to KS CM Admin Role
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Kuali Student CM Admin'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/


-- Modify Program By Proposal Permission 
--- Permission
INSERT INTO KRIM_PERM_T (ACTV_IND,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR,NM,DESC_TXT)
  VALUES ('Y','KS-SYS',sys_guid(),KRIM_PERM_ID_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD='KR-SYS' AND NM='Initiate Document'),1, 'Modify Program By Proposal', 'Allow user to mdoify a program by proposal')
/
-- Permission Detail
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, VER_NBR,OBJ_ID,KIM_ATTR_DEFN_ID,KIM_TYP_ID,PERM_ID,ATTR_VAL)
   VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, 1,sys_guid(),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='documentTypeName' AND NMSPC_CD='KR-WKFLW'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Document Type (Permission)' AND NMSPC_CD='KR-SYS'),KRIM_PERM_ID_S.CURRVAL,'kuali.proposal.type.majorDiscipline.modify')
/
-- Assign to KS CM Admin Role
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Kuali Student CM Admin'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/


-- Initiate Document Permission --> Is this permission required?
--- Permission
INSERT INTO KRIM_PERM_T (ACTV_IND,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR,NM)
  VALUES ('Y','KS-SYS',sys_guid(),KRIM_PERM_ID_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD='KR-SYS' AND NM='Initiate Document'),1, 'Initiate Document')
/
-- Permission Detail
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, VER_NBR,OBJ_ID,KIM_ATTR_DEFN_ID,KIM_TYP_ID,PERM_ID,ATTR_VAL)
   VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, 1,sys_guid(),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='documentTypeName' AND NMSPC_CD='KR-WKFLW'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Document Type (Permission)' AND NMSPC_CD='KR-SYS'),KRIM_PERM_ID_S.CURRVAL,'KualiStudentDocument')
/


-- Open Document Permission
--- Permission
INSERT INTO KRIM_PERM_T (ACTV_IND,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR,NM,DESC_TXT)
  VALUES ('Y','KS-LUM',sys_guid(),KRIM_PERM_ID_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM='Open Document' AND NMSPC_CD='KS-SYS'),1, 'Open Document', 'Allows users with responsibility on workflow document to open document')
/
--- Assign to derived roles (approval, fyi, ack requested, or route log reviewer, router, or initiator)
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Derived Role: Document Editor' AND NMSPC_CD='KS-SYS'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Derived Role: Initiator or Reviewer' AND NMSPC_CD='KS-SYS'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Derived Role: Router' AND NMSPC_CD='KS-SYS'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Derived Role: Document Commenter' AND NMSPC_CD='KS-SYS'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/


-- Comment on Document Permission
--- Permission
INSERT INTO KRIM_PERM_T (ACTV_IND,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR,NM)
  VALUES ('Y','KS-LUM',sys_guid(),KRIM_PERM_ID_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM='Comment on Document' AND NMSPC_CD='KS-SYS'),1, 'Comment on Document')
/
--- Assign to derived roles (approval, fyi, ack requested)
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Derived Role: Document Editor' AND NMSPC_CD='KS-SYS'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Derived Role: Acknowledge Request Recipient' AND NMSPC_CD='KS-SYS'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Derived Role: FYI Request Recipient' AND NMSPC_CD='KS-SYS'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/


-- Edit Document Permission
--- Permission
INSERT INTO KRIM_PERM_T (ACTV_IND,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR,NM)
  VALUES ('Y','KS-LUM',sys_guid(),KRIM_PERM_ID_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM='Edit Document' AND NMSPC_CD='KS-SYS'),1, 'Edit Document')
/
--- Assign to derived role (approval requested)
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Derived Role: Approve Request Recipient' AND NMSPC_CD='KS-SYS'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/


-- Add-Reviewer Permission
-- Permission 
INSERT INTO KRIM_PERM_T (ACTV_IND,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR,NM, DESC_TXT)
  VALUES ('Y','KS-LUM',sys_guid(),KRIM_PERM_ID_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM='Add Adhoc Reviewer' AND NMSPC_CD='KS-SYS'),1, 'Add Adhoc Reviewer','Authorizes users to add adhoc reviewers to documents.')
/
--- Assign to derived role (approval requested)
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Derived Role: Approve Request Recipient' AND NMSPC_CD='KS-SYS'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/


-- Add Withdraw Document Permission
-- Permission 
INSERT INTO KRIM_PERM_T (ACTV_IND,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR,NM, DESC_TXT)
  VALUES ('Y','KS-LUM',sys_guid(),KRIM_PERM_ID_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM='Withdraw Document' AND NMSPC_CD='KS-SYS'),1, 'Withdraw Document','Authorizes users to withdraw a KS workflow document.')
/
-- Permission Detail - Document Type
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, VER_NBR,OBJ_ID,KIM_ATTR_DEFN_ID,KIM_TYP_ID,PERM_ID,ATTR_VAL)
   VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, 1,sys_guid(),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='documentTypeName' AND NMSPC_CD='KR-WKFLW'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Document Type & Routing Node or State' AND NMSPC_CD='KR-SYS'),KRIM_PERM_ID_S.CURRVAL,'KualiStudentDocument')
/
-- Permission Detail - Route Status
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, VER_NBR,OBJ_ID,KIM_ATTR_DEFN_ID,KIM_TYP_ID,PERM_ID,ATTR_VAL)
   VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, 1,sys_guid(),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM='routeStatusCode' AND NMSPC_CD='KR-WKFLW'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM='Document Type & Routing Node or State' AND NMSPC_CD='KR-SYS'),KRIM_PERM_ID_S.CURRVAL,'R')
/
--- Assign to workflow initiator role
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',sys_guid(),KRIM_PERM_ID_S.CURRVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Initiator' AND NMSPC_CD='KR-WKFLW'),KRIM_ROLE_PERM_ID_S.NEXTVAL,1)
/
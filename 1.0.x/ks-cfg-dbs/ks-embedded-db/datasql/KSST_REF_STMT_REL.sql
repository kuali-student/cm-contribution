TRUNCATE TABLE KSST_REF_STMT_REL DROP STORAGE
/
INSERT INTO KSST_REF_STMT_REL (CREATEID,CREATETIME,EFF_DT,EXPIR_DT,ID,REF_OBJ_ID,REF_OBJ_TYPE_KEY,REF_STMT_REL_TYPE_ID,ST,STMT_ID,UPDATEID,UPDATETIME,VERSIONIND)
  VALUES ('CREATEID',TO_DATE( '20000101000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20000101000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '21001231000000', 'YYYYMMDDHH24MISS' ),'ref-stmt-rel-1','CLU-NL-1','clu','clu.prerequisites','ACTIVE','STMT-1','UPDATEID',TO_DATE( '20010101000000', 'YYYYMMDDHH24MISS' ),1)
/
INSERT INTO KSST_REF_STMT_REL (CREATEID,CREATETIME,EFF_DT,EXPIR_DT,ID,REF_OBJ_ID,REF_OBJ_TYPE_KEY,REF_STMT_REL_TYPE_ID,ST,STMT_ID,UPDATEID,UPDATETIME,VERSIONIND)
  VALUES ('CREATEID',TO_DATE( '20000101000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20000101000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '21001231000000', 'YYYYMMDDHH24MISS' ),'ref-stmt-rel-5','CLU-NL-1','clu','clu.prerequisites','ACTIVE','STMT-5','UPDATEID',TO_DATE( '20010101000000', 'YYYYMMDDHH24MISS' ),1)
/

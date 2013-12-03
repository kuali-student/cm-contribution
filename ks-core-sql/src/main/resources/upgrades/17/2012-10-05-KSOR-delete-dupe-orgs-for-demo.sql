--Delete duplicate orgs
DELETE
FROM
    KSOR_ORG_ORG_RELTN
WHERE
    KSOR_ORG_ORG_RELTN.ORG IN ('1000117','1000136','1000112','1000852')
 OR KSOR_ORG_ORG_RELTN.RELATED_ORG IN ('1000117','1000136','1000112','1000852')
/
DELETE
FROM
    KSOR_ORG
WHERE
    id IN ('1000117','1000136','1000112','1000852')
/

--Update references to point to correct org
UPDATE KSLU_CLU_ADMIN_ORG set ORG_ID='1000CHEM' where org_ID='1000117'
/
UPDATE KSLU_CLU_ADMIN_ORG set ORG_ID='1000ENGL' where org_ID='1000136'
/
UPDATE KSLU_CLU_ADMIN_ORG set ORG_ID='1000BIOL' where org_ID='1000112'
/
UPDATE KSLU_CLU_ADMIN_ORG set ORG_ID='1000LAW' where org_ID='1000852'
/
update KSLU_CLU SET KSLU_CLU.EXP_FIRST_ATP = ( REGEXP_REPLACE(KSLU_CLU.EXP_FIRST_ATP,'(\d+).*','\11')) where EXP_FIRST_ATP like '%SPRING'
/
update KSLU_CLU SET KSLU_CLU.EXP_FIRST_ATP = ( REGEXP_REPLACE(KSLU_CLU.EXP_FIRST_ATP,'(\d+).*','\12')) where EXP_FIRST_ATP like '%SUMMER'
/
update KSLU_CLU SET KSLU_CLU.EXP_FIRST_ATP = ( REGEXP_REPLACE(KSLU_CLU.EXP_FIRST_ATP,'(\d+).*','\13')) where EXP_FIRST_ATP like '%FALL'
/
update KSLU_CLU SET KSLU_CLU.EXP_FIRST_ATP = ( REGEXP_REPLACE(KSLU_CLU.EXP_FIRST_ATP,'(\d+).*','\14')) where EXP_FIRST_ATP like '%WINTER'
/

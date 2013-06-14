package org.kuali.student.common.util;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.util.date.DateFormatters;
import org.kuali.student.r2.core.acal.dto.AcademicCalendarInfo;
import org.kuali.student.r2.core.acal.dto.HolidayCalendarInfo;
import org.kuali.student.r2.core.acal.dto.TermInfo;
import org.kuali.student.r2.core.acal.service.AcademicCalendarService;
import org.kuali.student.r2.core.atp.service.AtpService;
import org.kuali.student.r2.core.constants.AcademicCalendarServiceConstants;
import org.kuali.student.r2.core.search.dto.SearchRequestInfo;
import org.kuali.student.r2.core.search.dto.SearchResultCellInfo;
import org.kuali.student.r2.core.search.dto.SearchResultInfo;
import org.kuali.student.r2.core.search.dto.SearchResultRowInfo;
import org.kuali.student.r2.core.search.infc.SearchResultCell;

import java.util.ArrayList;
import java.util.List;

import static org.kuali.rice.core.api.criteria.PredicateFactory.*;

public class CalendarSearchViewHelperUtil {

    public final static String NAME = "name";
    public final static String START_DATE = "startDate";
    public final static String END_DATE = "endDate";
    public final static String CALENDAR_TYPE = "atpType";


    public static List<TermInfo> searchForTerms(String name, String year, ContextInfo context, AcademicCalendarService academicCalendarService) throws Exception {

        List<TermInfo> termInfoList = new ArrayList<TermInfo>();

        QueryByCriteria.Builder query = buildQueryByCriteria(name, year, "Term");

        List<TermInfo> terms = academicCalendarService.searchForTerms(query.build(), context);
        for (TermInfo term : terms) {
            termInfoList.add(term);
        }

        return termInfoList;

    }

    public static List<AcademicCalendarInfo> searchForAcademicCalendars(String nameParam, String yearParam, ContextInfo context, AtpService atpService) throws Exception {

        List<AcademicCalendarInfo> acalInfoList = new ArrayList<AcademicCalendarInfo>();

        SearchRequestInfo searchRequest = new SearchRequestInfo("atp.search.advancedAtpSearch");
        searchRequest.addParam("atp.advancedAtpSearchParam.atpType", AcademicCalendarServiceConstants.ACADEMIC_CALENDAR_TYPE_KEY);
        if(nameParam!=null&&!nameParam.isEmpty()){
            searchRequest.addParam("atp.advancedAtpSearchParam.atpShortName", nameParam);
        }
        if(yearParam!=null&&!yearParam.isEmpty()){
            searchRequest.addParam("atp.advancedAtpSearchParam.atpYear", yearParam);
        }
        SearchResultInfo searchResults = atpService.search(searchRequest, context);


        for(SearchResultRowInfo row : searchResults.getRows()){
            String id = null;
            String name = null;
            String startDate = null;
            String endDate = null;
            String stateKey = null;

            for(SearchResultCellInfo cell : row.getCells()){
                if("atp.resultColumn.atpId".equals(cell.getKey())){
                    id = cell.getValue();
                }else if("atp.resultColumn.atpShortName".equals(cell.getKey())){
                    name = cell.getValue();
                }else if("atp.resultColumn.atpStartDate".equals(cell.getKey())){
                    startDate = cell.getValue();
                }else if("atp.resultColumn.atpEndDate".equals(cell.getKey())){
                    endDate = cell.getValue();
                }else if("atp.resultColumn.atpState".equals(cell.getKey())){
                    stateKey = cell.getValue();
                }
            }

            AcademicCalendarInfo acalInfo = new AcademicCalendarInfo();
            acalInfo.setId(id);
            acalInfo.setName(name);
            acalInfo.setStartDate(DateFormatters.DEFAULT_YEAR_MONTH_24HOUR_MILLISECONDS_FORMATTER.parse(startDate));
            acalInfo.setEndDate(DateFormatters.DEFAULT_YEAR_MONTH_24HOUR_MILLISECONDS_FORMATTER.parse(endDate));
            acalInfo.setStateKey(stateKey);

            acalInfoList.add(acalInfo);
        }

        return acalInfoList;

    }

    public static List<HolidayCalendarInfo> searchForHolidayCalendars(String name, String year, ContextInfo context, AcademicCalendarService academicCalendarService) throws Exception {

        List<HolidayCalendarInfo> hCals = new ArrayList<HolidayCalendarInfo>();

        QueryByCriteria.Builder query = buildQueryByCriteria(name, year, AcademicCalendarServiceConstants.HOLIDAY_CALENDAR_TYPE_KEY);

        List<HolidayCalendarInfo> hcs = academicCalendarService.searchForHolidayCalendars(query.build(), context);
        for (HolidayCalendarInfo hc : hcs) {
            hCals.add(hc);
        }

        return hCals;


    }

    private static QueryByCriteria.Builder buildQueryByCriteria(String name, String year, String typeKey) {

        QueryByCriteria.Builder qBuilder = QueryByCriteria.Builder.create();
        List<Predicate> pList = new ArrayList<Predicate>();
        Predicate p;

        qBuilder.setPredicates();
        if (StringUtils.isNotBlank(name)) {
            p = like(NAME, "%" + name + "%");
            pList.add(p);
        }

        if (StringUtils.isNotBlank(year)) {
            try {
                //FIXME: Find some better way to check the year
                Predicate startDatePredicate = and(greaterThanOrEqual(START_DATE, DateFormatters.MONTH_DAY_YEAR_DATE_FORMATTER.parse("01/01/" + year)),
                        lessThanOrEqual(START_DATE, DateFormatters.MONTH_DAY_YEAR_DATE_FORMATTER.parse("12/31/" + year)));


                Predicate endDatePredicate = and(greaterThanOrEqual(END_DATE, DateFormatters.MONTH_DAY_YEAR_DATE_FORMATTER.parse("01/01/" + year)),
                        lessThanOrEqual(END_DATE, DateFormatters.MONTH_DAY_YEAR_DATE_FORMATTER.parse("12/31/" + year)));


                pList.add(or(startDatePredicate, endDatePredicate));

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        if (StringUtils.equalsIgnoreCase(typeKey, "Term")) {
            p = notIn(CALENDAR_TYPE, AcademicCalendarServiceConstants.ACADEMIC_CALENDAR_TYPE_KEY, AcademicCalendarServiceConstants.HOLIDAY_CALENDAR_TYPE_KEY);
        } else {
            p = equal(CALENDAR_TYPE, typeKey);
        }

        pList.add(p);

        if (!pList.isEmpty()) {
            Predicate[] preds = new Predicate[pList.size()];
            pList.toArray(preds);
            qBuilder.setPredicates(and(preds));
        }
        return qBuilder;
    }


}

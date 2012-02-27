package org.kuali.student.lum.program.service.validation;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.kuali.student.r1.common.dictionary.dto.FieldDefinition;
import org.kuali.student.r1.common.dictionary.dto.ObjectStructureDefinition;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.common.search.dto.SearchRequest;
import org.kuali.student.common.search.dto.SearchResult;
import org.kuali.student.common.search.dto.SearchResultCell;
import org.kuali.student.common.search.dto.SearchResultRow;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r1.common.validator.DefaultValidatorImpl;
import org.kuali.student.r2.lum.program.dto.MajorDisciplineInfo;

public class ProgramManagingBodiesValidator extends DefaultValidatorImpl {

    @Override
    // TODO KSCM-254
    public List<ValidationResultInfo> validateObject(FieldDefinition field,
            Object o, ObjectStructureDefinition objStructure,
            Stack<String> elementStack, ContextInfo contextInfo) {
        List<ValidationResultInfo> validationResults = new ArrayList<ValidationResultInfo>();

        String element = getElementXpath(elementStack) + "/" + field.getName();

        if (o instanceof MajorDisciplineInfo) {
            MajorDisciplineInfo majorDisciplineInfo = (MajorDisciplineInfo) o;

            if (field.getName().equalsIgnoreCase("unitsContentOwner") && null != majorDisciplineInfo.getUnitsContentOwner()
                    && !majorDisciplineInfo.getUnitsContentOwner().isEmpty()) {
                validationResults = validateObject(element, majorDisciplineInfo.getUnitsContentOwner(), majorDisciplineInfo.getDivisionsContentOwner());
            } else if (field.getName().equalsIgnoreCase("unitsDeployment") && null != majorDisciplineInfo.getUnitsDeployment()
                    && !majorDisciplineInfo.getUnitsDeployment().isEmpty()) {
                validationResults = validateObject(element, majorDisciplineInfo.getUnitsDeployment(), majorDisciplineInfo.getDivisionsDeployment());
            } else if (field.getName().equalsIgnoreCase("unitsFinancialControl") && null != majorDisciplineInfo.getUnitsFinancialControl()
                    && !majorDisciplineInfo.getUnitsFinancialControl().isEmpty()) {
                validationResults = validateObject(element, majorDisciplineInfo.getUnitsFinancialControl(), majorDisciplineInfo.getDivisionsFinancialControl());
            } else if (field.getName().equalsIgnoreCase("unitsFinancialResources") && null != majorDisciplineInfo.getUnitsFinancialResources()
                    && !majorDisciplineInfo.getUnitsFinancialResources().isEmpty()) {
                validationResults = validateObject(element, majorDisciplineInfo.getUnitsFinancialResources(), majorDisciplineInfo.getDivisionsFinancialResources());
            } else if (field.getName().equalsIgnoreCase("unitsStudentOversight") && null != majorDisciplineInfo.getUnitsStudentOversight()
                    && !majorDisciplineInfo.getUnitsStudentOversight().isEmpty()) {
                validationResults = validateObject(element, majorDisciplineInfo.getUnitsStudentOversight(), majorDisciplineInfo.getDivisionsStudentOversight());
            }
        }

        return validationResults;
    }

    public List<ValidationResultInfo> validateObject(String element, List<String> departmentIds, List<String> collegeIds) {
        List<ValidationResultInfo> validationResults = new ArrayList<ValidationResultInfo>();

        List<String> departmentRelatedCollegeIds = getDepartmentRelatedColleges(departmentIds);

        if (null != collegeIds) {
            for (String collegeId : collegeIds) {
                if (!departmentRelatedCollegeIds.contains(collegeId)) {
                    validationResults.addAll(getValidationResultInfo(element, collegeId, departmentIds));
                }
            }
        }

        return validationResults;
    }

    private List<String> getDepartmentRelatedColleges(List<String> departmentIds) {
        List<String> departmentRelatedCollegeIds = new ArrayList<String>();
        SearchRequest searchRequest = new SearchRequest("org.search.orgQuickViewByRelationTypeOrgTypeRelatedOrgIds");

        List<String> orgTypes = new ArrayList<String>();
        orgTypes.add("kuali.org.College");

        searchRequest.addParam("org.queryParam.optionalOrgTypeList", orgTypes);
        searchRequest.addParam("org.queryParam.optionalRelationType", "kuali.org.Contain");
        searchRequest.addParam("org.queryParam.relatedOrgIds", departmentIds);

        SearchResult searchResult = getSearchDispatcher().dispatchSearch(searchRequest);

        if (null != searchResult) {
            for (SearchResultRow row : searchResult.getRows()) {
                for (SearchResultCell cell : row.getCells()) {
                    if ("org.resultColumn.orgId".equals(cell.getKey())) {
                        departmentRelatedCollegeIds.add(cell.getValue());
                    }
                }
            }
        }

        return departmentRelatedCollegeIds;
    }

    private List<ValidationResultInfo> getValidationResultInfo(String element, String collegeId, List<String> departmentIds) {
        List<ValidationResultInfo> validationResults = new ArrayList<ValidationResultInfo>();

        String message = getMessage("validation.programManagingBodiesMatch" , new ContextInfo());
        String collegeName = getCollegeName(collegeId);
        List<String> departments = getDepartments(departmentIds);

        for (String departmentName : departments) {
            ValidationResultInfo validationResultInfo = new ValidationResultInfo(element);
            validationResultInfo.setWarning(MessageFormat.format(message, collegeName, departmentName));
            validationResults.add(validationResultInfo);
        }

        return validationResults;
    }

    private String getCollegeName(String collegeId) {
        String collegeName = "";
        SearchRequest searchRequest = new SearchRequest("org.search.generic");

        List<String> orgTypes = new ArrayList<String>();
        orgTypes.add("kuali.org.College");

        searchRequest.addParam("org.queryParam.orgOptionalType", orgTypes);
        searchRequest.addParam("org.queryParam.orgOptionalId", collegeId);

        SearchResult searchResult = getSearchDispatcher().dispatchSearch(searchRequest);
        if (null != searchResult) {
            for (SearchResultRow result : searchResult.getRows()) {
                for (SearchResultCell resultCell : result.getCells()) {
                    if ("org.resultColumn.orgOptionalLongName".equals(resultCell.getKey())) {
                        collegeName = resultCell.getValue();
                    }
                }
            }
        }

        return collegeName;
    }

    private List<String> getDepartments(List<String> departmentIds) {
        List<String> departments = new ArrayList<String>();
        SearchRequest searchRequest = new SearchRequest("org.search.generic");

        List<String> orgTypes = new ArrayList<String>();
        orgTypes.add("kuali.org.Department");

        searchRequest.addParam("org.queryParam.orgOptionalType", orgTypes);
        searchRequest.addParam("org.queryParam.orgOptionalIds", departmentIds);

        SearchResult searchResult = getSearchDispatcher().dispatchSearch(searchRequest);

        if (null != searchResult) {
            for (SearchResultRow result : searchResult.getRows()) {
                for (SearchResultCell resultCell : result.getCells()) {
                    if ("org.resultColumn.orgOptionalLongName".equals(resultCell.getKey())) {
                        departments.add(resultCell.getValue());
                    }
                }
            }
        }

        return departments;
    }
}

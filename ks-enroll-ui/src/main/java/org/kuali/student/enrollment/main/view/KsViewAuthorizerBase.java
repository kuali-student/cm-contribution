package org.kuali.student.enrollment.main.view;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.component.ComponentSecurity;
import org.kuali.rice.krad.uif.container.Group;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.field.Field;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.uif.view.View;
import org.kuali.rice.krad.uif.view.ViewAuthorizerBase;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.kuali.rice.krad.uif.widget.Widget;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.student.enrollment.class2.courseoffering.dto.ActivityOfferingWrapper;
import org.kuali.student.enrollment.class2.courseoffering.dto.CourseOfferingListSectionWrapper;
import org.kuali.student.enrollment.class2.courseoffering.form.CourseOfferingManagementForm;
import org.kuali.student.enrollment.class2.courseoffering.form.RegistrationGroupManagementForm;
import org.kuali.student.enrollment.courseoffering.dto.CourseOfferingInfo;
import org.kuali.student.r2.common.util.ContextUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Custom Authorizer that resolves qualifiers from the model. This will need to be refactored and made more generic in
 * the future (configuration of mapping from model object graph to role qualifiers, maybe using beanutils
 * (object.field1[3].field2->qualifierId)
 */
public class KsViewAuthorizerBase extends ViewAuthorizerBase {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(KsViewAuthorizerBase.class);

    @Override
    protected void addRoleQualification(Object primaryDataObjectOrDocument, Map<String, String> attributes) {
        if (primaryDataObjectOrDocument !=null && primaryDataObjectOrDocument instanceof CourseOfferingManagementForm) {
            CourseOfferingManagementForm theForm = (CourseOfferingManagementForm) primaryDataObjectOrDocument;
            if(theForm.getAdminOrg() != null){
                attributes.put("org", theForm.getAdminOrg());
            }
        }
        if (primaryDataObjectOrDocument !=null && primaryDataObjectOrDocument instanceof RegistrationGroupManagementForm) {
            RegistrationGroupManagementForm theForm = (RegistrationGroupManagementForm) primaryDataObjectOrDocument;
            if(theForm.getTheCourseOffering() != null){
                //Pull out the org ids and pass in the first one as a role qualifier
                List<String> orgIds = theForm.getTheCourseOffering().getUnitsDeploymentOrgIds();
                if(orgIds !=null && !orgIds.isEmpty()){
                    attributes.put("org", orgIds.get(0));
                }
            }
        }
        super.addRoleQualification(primaryDataObjectOrDocument, attributes);
    }

    @Override
    protected void addPermissionDetails(Object primaryDataObjectOrDocument, Map<String, String> attributes) {
        if (primaryDataObjectOrDocument !=null && primaryDataObjectOrDocument instanceof CourseOfferingManagementForm) {
            CourseOfferingManagementForm theForm = (CourseOfferingManagementForm) primaryDataObjectOrDocument;
            if (theForm.getSocState() != null) {
                attributes.put("socState", theForm.getSocState());
            }
        }
        super.addPermissionDetails(primaryDataObjectOrDocument, attributes);    //To change body of overridden methods use File | Settings | File Templates.
    }

    /**
     * This method resolves the unitsDeploymentOrgId from the CourseOffering Model and passes it in as
     * a role qualifier
     */
    /**
     * Performs a permission check for the given template name in the context of the given view and component
     *
     * <p>
     * First standard permission details are added based on the type of component the permission check is being
     * done for.
     * Then the {@link org.kuali.rice.krad.uif.component.ComponentSecurity} of the given component is used to pick up additional permission details and
     * role qualifiers.
     * </p>
     *
     * @param view - view instance the component belongs to
     * @param component - component instance the permission check is being done for
     * @param model - object containing the views data
     * @param permissionTemplateName - template name for the permission to check
     * @param user - user to perform the authorization for
     * @param additionalPermissionDetails - additional key/value pairs to pass with the permission details
     * @param additionalRoleQualifications - additional key/value paris to pass with the role qualifiers
     * @param checkPermissionExistence - boolean indicating whether the existence of the permission should be checked
     * before performing the authorization
     * @return boolean indicating whether the user has authorization, this will be the case if the user has been
     * granted the permission or checkPermissionExistence is true and the permission does not exist
     */
    @Override
    protected boolean isAuthorizedByTemplate(View view, Component component, ViewModel model,
                                             String permissionTemplateName, Person user, Map<String, String> additionalPermissionDetails,
                                             Map<String, String> additionalRoleQualifications, boolean checkPermissionExistence) {
        if(additionalRoleQualifications == null){
            //Instantiate if null was passed in
            additionalRoleQualifications = new HashMap<String, String>();
        }
        addRoleQualification(model, additionalRoleQualifications);

        Map<String, String> permissionDetails = new HashMap<String, String>();
        Map<String, String> roleQualifications = new HashMap<String, String>();

        if (additionalPermissionDetails != null) {
            permissionDetails.putAll(additionalPermissionDetails);
        }

        if (additionalRoleQualifications != null) {
            roleQualifications.putAll(additionalRoleQualifications);
        }

        Object dataObjectForContext = getDataObjectContext(view, model);

        // add permission details depending on the type of component
        if (component instanceof Field) {
            permissionDetails.putAll(getFieldPermissionDetails(view, dataObjectForContext, (Field) component));
        } else if (component instanceof Group) {
            permissionDetails.putAll(getGroupPermissionDetails(view, dataObjectForContext, (Group) component));
        } else if (component instanceof Widget) {
            permissionDetails.putAll(getWidgetPermissionDetails(view, dataObjectForContext, (Widget) component));
        } else if (component instanceof Action) {
            permissionDetails.putAll(getActionPermissionDetails(view, dataObjectForContext, (Action) component));
        }

        // pick up additional attributes and overrides from component security
        ComponentSecurity componentSecurity = component.getComponentSecurity();

        // add configured overrides
        if (StringUtils.isNotBlank(componentSecurity.getNamespaceAttribute())) {
            permissionDetails.put(KimConstants.AttributeConstants.NAMESPACE_CODE,
                    componentSecurity.getNamespaceAttribute());
        }
        if (StringUtils.isNotBlank(componentSecurity.getComponentAttribute())) {
            permissionDetails.put(KimConstants.AttributeConstants.COMPONENT_NAME,
                    componentSecurity.getComponentAttribute());
        }
        if (StringUtils.isNotBlank(componentSecurity.getIdAttribute())) {
            if (component instanceof Field) {
                permissionDetails.put(KimConstants.AttributeConstants.FIELD_ID, componentSecurity.getIdAttribute());
            } else if (component instanceof Group) {
                permissionDetails.put(KimConstants.AttributeConstants.GROUP_ID, componentSecurity.getIdAttribute());
            } else if (component instanceof Widget) {
                permissionDetails.put(KimConstants.AttributeConstants.WIDGET_ID, componentSecurity.getIdAttribute());
            }
        }

        // Add attribute depending on CO state for 'permissionExpression'
        if (component instanceof Action) {
            if (!((Action) component).getActionParameters().isEmpty() &&
                    (((Action) component).getActionParameters().get(UifParameters.SELECTED_LINE_INDEX) != null) && !((Action) component).getActionParameters().get(UifParameters.SELECTED_LINE_INDEX).equals("") &&
                    (((Action) component).getActionParameters().get(UifParameters.SELLECTED_COLLECTION_PATH) != null && !((Action) component).getActionParameters().get(UifParameters.SELLECTED_COLLECTION_PATH).equals("")) ) {
                String selectedCollectionPath = ((Action) component).getActionParameters().get(UifParameters.SELLECTED_COLLECTION_PATH);

                int selectedLineIndex = -1;
                String selectedLine = ((Action) component).getActionParameters().get(UifParameters.SELECTED_LINE_INDEX);
                if (StringUtils.isNotBlank(selectedLine)) {
                    selectedLineIndex = Integer.parseInt(selectedLine);
                }

                if (model != null && model instanceof CourseOfferingManagementForm) {
                    CourseOfferingManagementForm theForm = (CourseOfferingManagementForm) model;
                    Collection<Object> collection = ObjectPropertyUtils.getPropertyValue(theForm, selectedCollectionPath);
                    Object selectedObject = ((List<Object>) collection).get(selectedLineIndex);
                    if(selectedObject instanceof CourseOfferingListSectionWrapper){
                        CourseOfferingListSectionWrapper theCourseOfferingWrapper =  (CourseOfferingListSectionWrapper)selectedObject;
                        String courseOfferingStateDisplay = theCourseOfferingWrapper.getCourseOfferingStateDisplay();
                        if (courseOfferingStateDisplay != null) {
                            permissionDetails.put("coState", courseOfferingStateDisplay);
                        }
                    } else if(selectedObject instanceof ActivityOfferingWrapper){
                        ActivityOfferingWrapper theActivityOfferingWrapper =  (ActivityOfferingWrapper)selectedObject;
                        String activityOfferingStateName = theActivityOfferingWrapper.getStateName();
                        if (activityOfferingStateName != null) {
                            permissionDetails.put("aoState", activityOfferingStateName);
                        }
                    }
                }
            }
        }

        if (componentSecurity.getAdditionalPermissionDetails() != null) {
            permissionDetails.putAll(componentSecurity.getAdditionalPermissionDetails());
        }

        if (componentSecurity.getAdditionalRoleQualifiers() != null) {
            roleQualifications.putAll(componentSecurity.getAdditionalRoleQualifiers());
        }

        boolean result = true;
        if (!checkPermissionExistence || (checkPermissionExistence && permissionExistsByTemplate(dataObjectForContext,
                "KS-ENR", permissionTemplateName, permissionDetails))) {
            result = isAuthorizedByTemplate(dataObjectForContext, "KS-ENR", permissionTemplateName,
                    user.getPrincipalId(), permissionDetails, roleQualifications);

            if (LOG.isDebugEnabled()) {
                LOG.debug("Performed permission check for: " + permissionTemplateName + " and got result: " + result);
            }
        }

        return result;
    }

    /**
     * Override the method to change the KRAD namespace to KS Enroll
     * @param view
     * @param model
     * @param user
     * @return
     */
    @Override
    public boolean canOpenView(View view, ViewModel model, Person user) {
        Map<String, String> additionalPermissionDetails = new HashMap<String, String>();
        additionalPermissionDetails.put(KimConstants.AttributeConstants.NAMESPACE_CODE, view.getNamespaceCode());
        additionalPermissionDetails.put(KimConstants.AttributeConstants.VIEW_ID, model.getViewId());

        if (permissionExistsByTemplate(model, "KS-ENR",
                KimConstants.PermissionTemplateNames.OPEN_VIEW, additionalPermissionDetails)) {
            return isAuthorizedByTemplate(model, "KS-ENR",
                    KimConstants.PermissionTemplateNames.OPEN_VIEW, user.getPrincipalId(), additionalPermissionDetails,
                    null);
        }

        return true;
    }

    /**
     * Checks for an edit view permission for the view id, and if found verifies the user has that permission
     *
     * @see org.kuali.rice.krad.uif.view.ViewAuthorizer#canEditView(org.kuali.rice.krad.uif.view.View, org.kuali.rice.krad.uif.view.ViewModel,
     *      org.kuali.rice.kim.api.identity.Person)
     */
    @Override
    public boolean canEditView(View view, ViewModel model, Person user) {
        Map<String, String> additionalPermissionDetails = new HashMap<String, String>();
        additionalPermissionDetails.put(KimConstants.AttributeConstants.NAMESPACE_CODE, view.getNamespaceCode());
        additionalPermissionDetails.put(KimConstants.AttributeConstants.VIEW_ID, model.getViewId());

        if (permissionExistsByTemplate(model, "KS-ENR",
                KimConstants.PermissionTemplateNames.EDIT_VIEW, additionalPermissionDetails)) {
            return isAuthorizedByTemplate(model, "KS-ENR",
                    KimConstants.PermissionTemplateNames.EDIT_VIEW, user.getPrincipalId(), additionalPermissionDetails,
                    null);
        }

        return true;
    }

}
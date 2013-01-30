package org.kuali.student.enrollment.class1.krms.keyvalues;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.kuali.rice.krms.api.repository.language.NaturalLanguageTemplate;
import org.kuali.rice.krms.api.repository.language.NaturalLanguageUsage;
import org.kuali.rice.krms.api.repository.type.KrmsTypeDefinition;
import org.kuali.rice.krms.api.repository.type.KrmsTypeRepositoryService;
import org.kuali.rice.krms.api.repository.typerelation.TypeTypeRelation;
import org.kuali.rice.krms.impl.repository.KrmsRepositoryServiceLocator;
import org.kuali.rice.krms.impl.repository.NaturalLanguageTemplateBoService;
import org.kuali.rice.krms.impl.repository.NaturalLanguageUsageBoService;
import org.kuali.rice.krms.impl.repository.TypeTypeRelationBoService;
import org.kuali.student.enrollment.class1.krms.util.KsKrmsConstants;
import org.kuali.student.enrollment.class1.krms.util.KsKrmsRepositoryServiceLocator;
import org.kuali.student.enrollment.class2.courseoffering.service.decorators.PermissionServiceConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RequisiteAgendaTypeKeyValues extends UifKeyValuesFinderBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public List<KeyValue> getKeyValues(ViewModel model) {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        String agendaId = "10000";

        NaturalLanguageUsage usage = getNaturalLanguageUsageBoService().getNaturalLanguageUsageByName(PermissionServiceConstants.KS_SYS_NAMESPACE, KsKrmsConstants.KRMS_NL_TYPE_DESCRIPTION);

        //Use KRMS Type Repository Service to get Agenda Types
        try {
            List<TypeTypeRelation> typeTypeRelationList = getTypeTypeRelationBoService().findTypeTypeRelationsByFromType(agendaId);

            for (TypeTypeRelation typeTypeRelation : typeTypeRelationList) {
                NaturalLanguageTemplate template = null;
                try{
                    template = getNaturalLanguageTemplateBoService().findNaturalLanguageTemplateByLanguageCodeTypeIdAndNluId("en", typeTypeRelation.getToTypeId(), usage.getId());
                }catch (IndexOutOfBoundsException e){
                    //Ignore, rice error in NaturalLanguageTemplateBoServiceImpl line l
                }

                if (template != null){
                    //Use the template in the dropdown
                    keyValues.add(new ConcreteKeyValue(typeTypeRelation.getToTypeId(), template.getTemplate()));
                } else {
                    //If no template exist, display the type name
                    keyValues.add(new ConcreteKeyValue(typeTypeRelation.getToTypeId(), getKrmsTypeRepositoryService().getTypeById(typeTypeRelation.getToTypeId()).getName()));
                }
            }
        } catch (Exception ex) {}
        return keyValues;
    }

    private KrmsTypeRepositoryService getKrmsTypeRepositoryService() {
        return KrmsRepositoryServiceLocator.getKrmsTypeRepositoryService();
    }

    public TypeTypeRelationBoService getTypeTypeRelationBoService() {
        return KrmsRepositoryServiceLocator.getTypeTypeRelationBoService();
    }

    private NaturalLanguageUsageBoService getNaturalLanguageUsageBoService() {
        return KsKrmsRepositoryServiceLocator.getNaturalLanguageUsageBoService();
    }

    private NaturalLanguageTemplateBoService getNaturalLanguageTemplateBoService() {
        return KsKrmsRepositoryServiceLocator.getNaturalLanguageTemplateBoService();
    }
}
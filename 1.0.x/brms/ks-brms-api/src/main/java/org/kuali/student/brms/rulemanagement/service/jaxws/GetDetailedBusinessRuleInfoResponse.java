
package org.kuali.student.brms.rulemanagement.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.2
 * Tue Apr 07 16:13:12 EDT 2009
 * Generated source version: 2.2
 */

@XmlRootElement(name = "getDetailedBusinessRuleInfoResponse", namespace = "http://student.kuali.org/wsdl/brms/RuleManagement")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDetailedBusinessRuleInfoResponse", namespace = "http://student.kuali.org/wsdl/brms/RuleManagement")

public class GetDetailedBusinessRuleInfoResponse {

    @XmlElement(name = "return")
    private org.kuali.student.brms.rulemanagement.dto.BusinessRuleInfo _return;

    public org.kuali.student.brms.rulemanagement.dto.BusinessRuleInfo getReturn() {
        return this._return;
    }

    public void setReturn(org.kuali.student.brms.rulemanagement.dto.BusinessRuleInfo new_return)  {
        this._return = new_return;
    }

}


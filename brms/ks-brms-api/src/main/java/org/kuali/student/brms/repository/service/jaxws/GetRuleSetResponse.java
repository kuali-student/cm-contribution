
package org.kuali.student.brms.repository.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.2
 * Mon Jul 13 20:53:46 PDT 2009
 * Generated source version: 2.2
 */

@XmlRootElement(name = "getRuleSetResponse", namespace = "http://student.kuali.org/wsdl/brms/RuleRepository")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRuleSetResponse", namespace = "http://student.kuali.org/wsdl/brms/RuleRepository")

public class GetRuleSetResponse {

    @XmlElement(name = "return")
    private org.kuali.student.brms.repository.dto.RuleSetInfo _return;

    public org.kuali.student.brms.repository.dto.RuleSetInfo getReturn() {
        return this._return;
    }

    public void setReturn(org.kuali.student.brms.repository.dto.RuleSetInfo new_return)  {
        this._return = new_return;
    }

}


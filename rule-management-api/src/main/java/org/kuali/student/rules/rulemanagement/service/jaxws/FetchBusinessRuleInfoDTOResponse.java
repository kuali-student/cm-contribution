
package org.kuali.student.rules.rulemanagement.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.1
 * Thu Sep 25 10:29:50 EDT 2008
 * Generated source version: 2.1
 */

@XmlRootElement(name = "fetchBusinessRuleInfoDTOResponse", namespace = "http://student.kuali.org/poc/wsdl/brms/rulemanagement")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fetchBusinessRuleInfoDTOResponse", namespace = "http://student.kuali.org/poc/wsdl/brms/rulemanagement")

public class FetchBusinessRuleInfoDTOResponse {

    @XmlElement(name = "return")
    private java.util.List _return;

    public java.util.List getReturn() {
        return this._return;
    }

    public void setReturn(java.util.List new_return)  {
        this._return = new_return;
    }

}


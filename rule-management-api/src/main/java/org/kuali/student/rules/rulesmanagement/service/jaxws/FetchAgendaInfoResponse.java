
package org.kuali.student.rules.rulesmanagement.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.1
 * Fri Sep 05 14:53:07 EDT 2008
 * Generated source version: 2.1
 */

@XmlRootElement(name = "fetchAgendaInfoResponse", namespace = "http://student.kuali.org/poc/wsdl/brms/rulesmanagement")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fetchAgendaInfoResponse", namespace = "http://student.kuali.org/poc/wsdl/brms/rulesmanagement")

public class FetchAgendaInfoResponse {

    @XmlElement(name = "return")
    private org.kuali.student.rules.rulesmanagement.dto.AgendaInfoDTO _return;

    public org.kuali.student.rules.rulesmanagement.dto.AgendaInfoDTO getReturn() {
        return this._return;
    }

    public void setReturn(org.kuali.student.rules.rulesmanagement.dto.AgendaInfoDTO new_return)  {
        this._return = new_return;
    }

}


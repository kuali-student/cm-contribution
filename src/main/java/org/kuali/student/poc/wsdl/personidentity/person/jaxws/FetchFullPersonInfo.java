
package org.kuali.student.poc.wsdl.personidentity.person.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by the CXF 2.0.4-incubator
 * Fri Mar 21 10:30:13 EDT 2008
 * Generated source version: 2.0.4-incubator
 * 
 */

@XmlRootElement(name = "fetchFullPersonInfo", namespace = "http://student.kuali.org/poc/wsdl/personidentity/person")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fetchFullPersonInfo", namespace = "http://student.kuali.org/poc/wsdl/personidentity/person")

public class FetchFullPersonInfo {

    @XmlElement(name = "personId")
    private long personId;

    public long getPersonId() {
        return this.personId;
    }
    
    public void setPersonId( long newPersonId ) {
        this.personId = newPersonId;
    }
    
}



package org.kuali.student.poc.wsdl.personidentity.person.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by the CXF 2.0.4-incubator
 * Thu Mar 27 10:13:52 EDT 2008
 * Generated source version: 2.0.4-incubator
 * 
 */

@XmlRootElement(name = "fetchPersonInfoByPersonAttributeSetTypesResponse", namespace = "http://student.kuali.org/poc/wsdl/personidentity/person")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fetchPersonInfoByPersonAttributeSetTypesResponse", namespace = "http://student.kuali.org/poc/wsdl/personidentity/person")

public class FetchPersonInfoByPersonAttributeSetTypesResponse {

    @XmlElement(name = "return")
    private org.kuali.student.poc.xsd.personidentity.person.dto.PersonInfo _return;

    public org.kuali.student.poc.xsd.personidentity.person.dto.PersonInfo get_return() {
        return this._return;
    }
    
    public void set_return( org.kuali.student.poc.xsd.personidentity.person.dto.PersonInfo new_return ) {
        this._return = new_return;
    }
    
}


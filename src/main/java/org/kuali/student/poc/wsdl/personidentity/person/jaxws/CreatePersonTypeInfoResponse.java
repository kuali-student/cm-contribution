package org.kuali.student.poc.wsdl.personidentity.person.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by the CXF 2.0.4-incubator Thu Mar 20 11:20:16 EDT 2008 Generated source version: 2.0.4-incubator
 */

@XmlRootElement(name = "createPersonTypeInfoResponse", namespace = "http://student.kuali.org/poc/wsdl/personidentity/person")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createPersonTypeInfoResponse", namespace = "http://student.kuali.org/poc/wsdl/personidentity/person")
public class CreatePersonTypeInfoResponse {

    @XmlElement(name = "return")
    private long _return;

    public long get_return() {
        return _return;
    }

    public void set_return(long new_return) {
        _return = new_return;
    }

}

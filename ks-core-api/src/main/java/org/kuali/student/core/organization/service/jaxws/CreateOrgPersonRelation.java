
package org.kuali.student.core.organization.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.1.3
 * Wed Jan 21 13:23:58 EST 2009
 * Generated source version: 2.1.3
 */

@XmlRootElement(name = "createOrgPersonRelation", namespace = "http://org.kuali.student/core/organization")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createOrgPersonRelation", namespace = "http://org.kuali.student/core/organization", propOrder = {"orgId","personId","orgPersonRelationTypeKey","orgPersonRelationInfo"})

public class CreateOrgPersonRelation {

    @XmlElement(name = "orgId")
    private java.lang.String orgId;
    @XmlElement(name = "personId")
    private java.lang.String personId;
    @XmlElement(name = "orgPersonRelationTypeKey")
    private java.lang.String orgPersonRelationTypeKey;
    @XmlElement(name = "orgPersonRelationInfo")
    private org.kuali.student.core.organization.dto.OrgPersonRelationInfo orgPersonRelationInfo;

    public java.lang.String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(java.lang.String newOrgId)  {
        this.orgId = newOrgId;
    }

    public java.lang.String getPersonId() {
        return this.personId;
    }

    public void setPersonId(java.lang.String newPersonId)  {
        this.personId = newPersonId;
    }

    public java.lang.String getOrgPersonRelationTypeKey() {
        return this.orgPersonRelationTypeKey;
    }

    public void setOrgPersonRelationTypeKey(java.lang.String newOrgPersonRelationTypeKey)  {
        this.orgPersonRelationTypeKey = newOrgPersonRelationTypeKey;
    }

    public org.kuali.student.core.organization.dto.OrgPersonRelationInfo getOrgPersonRelationInfo() {
        return this.orgPersonRelationInfo;
    }

    public void setOrgPersonRelationInfo(org.kuali.student.core.organization.dto.OrgPersonRelationInfo newOrgPersonRelationInfo)  {
        this.orgPersonRelationInfo = newOrgPersonRelationInfo;
    }

}


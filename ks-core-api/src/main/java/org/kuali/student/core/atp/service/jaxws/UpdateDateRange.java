
package org.kuali.student.core.atp.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.2
 * Tue Mar 31 14:06:06 EDT 2009
 * Generated source version: 2.2
 */

@XmlRootElement(name = "updateDateRange", namespace = "http://student.kuali.org/core/atp")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateDateRange", namespace = "http://student.kuali.org/core/atp", propOrder = {"dateRangeKey","dateRangeInfo"})

public class UpdateDateRange {

    @XmlElement(name = "dateRangeKey")
    private java.lang.String dateRangeKey;
    @XmlElement(name = "dateRangeInfo")
    private org.kuali.student.core.atp.dto.DateRangeInfo dateRangeInfo;

    public java.lang.String getDateRangeKey() {
        return this.dateRangeKey;
    }

    public void setDateRangeKey(java.lang.String newDateRangeKey)  {
        this.dateRangeKey = newDateRangeKey;
    }

    public org.kuali.student.core.atp.dto.DateRangeInfo getDateRangeInfo() {
        return this.dateRangeInfo;
    }

    public void setDateRangeInfo(org.kuali.student.core.atp.dto.DateRangeInfo newDateRangeInfo)  {
        this.dateRangeInfo = newDateRangeInfo;
    }

}


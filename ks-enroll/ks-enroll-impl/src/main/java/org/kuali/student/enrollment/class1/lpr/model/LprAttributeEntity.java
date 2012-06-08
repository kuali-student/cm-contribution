/**
 * Copyright 2010 The Kuali Foundation Licensed under the Educational Community
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.osedu.org/licenses/ECL-2.0 Unless required by applicable law or
 * agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.student.enrollment.class1.lpr.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kuali.student.r2.common.entity.BaseAttributeEntity;
import org.kuali.student.r2.common.infc.Attribute;

@Entity
@Table(name = "KSEN_LPR_ATTR")
public class LprAttributeEntity extends BaseAttributeEntity<LprEntity> {

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private LprEntity owner;

    public LprAttributeEntity() {}

    public LprAttributeEntity(String key, String value) {
        super(key, value);
    }

    public LprAttributeEntity(Attribute att) {
        super(att);
    }

    @Override
    public void setOwner(LprEntity owner) {
        this.owner = owner;
    }

    @Override
    public LprEntity getOwner() {
        return owner;
    }
    
    public void fromDto (Attribute att) {
    	
    	setKey(att.getKey());
    	setValue(att.getValue());
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LprAttributeEntity [owner=");
		builder.append(owner);
		builder.append(", id=");
		builder.append(getId());
		builder.append(", key=");
		builder.append(getKey());
		builder.append(", value=");
		builder.append(getValue());
		builder.append("]");
		return builder.toString();
	}
    
    
}

/*
 * Copyright 2009 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.kuali.student.brms.statement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.kuali.student.core.entity.Type;

@Entity
@Table(name = "KSST_OBJECT_SUB_TYPE")
public class ObjectSubType extends Type<ObjectSubTypeAttribute> {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private List<ObjectSubTypeAttribute> attributes = new ArrayList<ObjectSubTypeAttribute>();
	
	@Override
	public List<ObjectSubTypeAttribute> getAttributes() {
		return this.attributes;
	}

	@Override
	public void setAttributes(List<ObjectSubTypeAttribute> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return "ObjectSubType[id=" + super.getId() + "]";
	}
}

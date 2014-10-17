/*
 * Copyright 2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.student.core.ui.admin.type;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.log4j.Logger;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.krad.inquiry.InquirableImpl;
import org.kuali.student.common.util.ContextBuilder;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.core.class1.type.dto.TypeInfo;
import org.kuali.student.r2.core.class1.type.service.TypeService;
import org.kuali.student.r2.core.constants.TypeServiceConstants;


public class TypeInfoAdminInquirableImpl extends InquirableImpl
{
	private static final Logger LOG = Logger.getLogger(TypeInfoAdminInquirableImpl.class);
	private transient TypeService typeService;
	private final static String PRIMARY_KEY = "key";
	private static final long serialVersionUID = 1L;

	@Override
	public TypeInfo retrieveDataObject(Map<String, String> parameters)
	{
		String key = parameters.get(PRIMARY_KEY);
		try
		{
			TypeInfo info = this.getTypeService().getType(key, getContextInfo());
			return info;
		}
		catch (Exception ex) {
		    throw new RuntimeException(ex);
		}
	}

	public void setTypeService(TypeService typeService)
	{
		    this.typeService = typeService;
	}

	public TypeService getTypeService()
	{
		if (typeService == null)
		{
			QName qname = new QName(TypeServiceConstants.NAMESPACE,TypeServiceConstants.SERVICE_NAME_LOCAL_PART);
			typeService = (TypeService) GlobalResourceLoader.getService(qname);
		}
		return this.typeService;
	}

	private ContextInfo getContextInfo() {
	    return ContextBuilder.loadContextInfo();
	}
}


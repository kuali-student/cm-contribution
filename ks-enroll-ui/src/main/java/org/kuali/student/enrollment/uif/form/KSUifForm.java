/**
 * Copyright 2013 The Kuali Foundation Licensed under the
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
 *
 * Created by David Yin on 1/16/13
 */
package org.kuali.student.enrollment.uif.form;

import org.kuali.rice.krad.web.form.UifFormBase;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * KS base form class contains properties that are shared among all the
 * KS forms. KS forms can extend it and it extends UifFormBase.
 *
 * @author Kuali Student Team
 */
public class KSUifForm extends UifFormBase {
    private String breadCrumbJSON;
    private LinkedHashMap<String,String> breadCrumbItemsMap;

    public KSUifForm() {
        breadCrumbJSON = "";
        breadCrumbItemsMap = new LinkedHashMap<String,String>();
    }

    public String getBreadCrumbJSON() {
        return breadCrumbJSON;
    }

    public void setBreadCrumbJSON(String breadCrumbJSON) {
        this.breadCrumbJSON = breadCrumbJSON;
    }

    public LinkedHashMap<String, String> getBreadCrumbItemsMap() {
        return breadCrumbItemsMap;
    }

    public void setBreadCrumbItemsMap(LinkedHashMap<String, String> breadCrumbItemsMap) {
        this.breadCrumbItemsMap = breadCrumbItemsMap;
    }
}
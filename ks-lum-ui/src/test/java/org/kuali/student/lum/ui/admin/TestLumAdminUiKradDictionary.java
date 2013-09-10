/*
 * Copyright 2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.student.lum.ui.admin;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author nwright
 */
public class TestLumAdminUiKradDictionary {

    private static final Logger log = Logger.getLogger(TestLumAdminUiKradDictionary.class);

    public TestLumAdminUiKradDictionary() {
    }

    @Test
    public void testLoadKradDictionary() {
        String[] contexts = new String[]{
            // base KRAD stuff
            "org/kuali/rice/krad/uif/UifActionDefinitions.xml",
            "org/kuali/rice/krad/uif/UifConfigurationDefinitions.xml",
            "org/kuali/rice/krad/uif/UifControlDefinitions.xml",
            "org/kuali/rice/krad/uif/UifDocumentDefinitions.xml",
            "org/kuali/rice/krad/uif/UifElementDefinitions.xml",
            "org/kuali/rice/krad/uif/UifFieldDefinitions.xml",
            "org/kuali/rice/krad/uif/UifGroupDefinitions.xml",
            "org/kuali/rice/krad/uif/UifHeaderFooterDefinitions.xml",
            "org/kuali/rice/krad/uif/UifIncidentReportDefinitions.xml",
            "org/kuali/rice/krad/uif/UifInitiateDocumentInfoDefinitions.xml",
            "org/kuali/rice/krad/uif/UifInquiryDefinitions.xml",
            "org/kuali/rice/krad/uif/UifLayoutManagerDefinitions.xml",
            "org/kuali/rice/krad/uif/UifLookupDefinitions.xml",
            "org/kuali/rice/krad/uif/UifMaintenanceDefinitions.xml",
            "org/kuali/rice/krad/uif/UifRiceDefinitions.xml",
            "org/kuali/rice/krad/uif/UifViewPageDefinitions.xml",
            "org/kuali/rice/krad/uif/UifWidgetDefinitions.xml",           
            // lrc -- move this and test tehse in LUM UI
            "org/kuali/student/lum/ui/admin/lrc/ResultScaleInfoAdminLookupView.xml",
            "org/kuali/student/lum/ui/admin/lrc/ResultScaleInfoAdminInquiryView.xml",
            "org/kuali/student/lum/ui/admin/lrc/ResultValueInfoAdminLookupView.xml",
            "org/kuali/student/lum/ui/admin/lrc/ResultValueInfoAdminInquiryView.xml",
            "org/kuali/student/lum/ui/admin/lrc/ResultValuesGroupInfoAdminLookupView.xml",
            "org/kuali/student/lum/ui/admin/lrc/ResultValuesGroupInfoAdminInquiryView.xml"
        };
        ApplicationContext ac = new ClassPathXmlApplicationContext(contexts);
    }
}

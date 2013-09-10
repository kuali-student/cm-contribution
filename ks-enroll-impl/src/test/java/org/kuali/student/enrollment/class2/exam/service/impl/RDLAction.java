/**
 * Copyright 2005-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.student.enrollment.class2.exam.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.krms.api.engine.ExecutionEnvironment;
import org.kuali.rice.krms.framework.engine.Action;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.TimeOfDayInfo;
import org.kuali.student.r2.common.util.date.DateFormatters;
import org.kuali.student.r2.core.constants.KSKRMSServiceConstants;
import org.kuali.student.r2.core.scheduling.constants.SchedulingServiceConstants;
import org.kuali.student.r2.core.scheduling.dto.ScheduleRequestComponentInfo;
import org.kuali.student.r2.core.scheduling.dto.TimeSlotInfo;
import org.kuali.student.r2.core.scheduling.service.SchedulingService;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Used to help test actions
 *
 * @author Kuali Student Team
 */
public class RDLAction implements Action {

    private SchedulingService schedulingService;

    private List<Integer> weekdays;
    private String startTime;
    private String endTime;

    @Override
    public void execute(ExecutionEnvironment environment) {
        ContextInfo context = (ContextInfo) environment.getFacts().get(KSKRMSServiceConstants.TERM_PREREQUISITE_CONTEXTINFO);
        //schedulingService.createScheduleRequest("scheduleRequestTypeKey", scheduleRequest, context);
    }

    /**
     * @see org.kuali.rice.krms.framework.engine.Action#executeSimulation(org.kuali.rice.krms.api.engine.ExecutionEnvironment)
     */
    @Override
    public void executeSimulation(ExecutionEnvironment environment) {
        throw new UnsupportedOperationException();
    }

    private ScheduleRequestComponentInfo buildScheduleComponentRequest(ContextInfo defaultContextInfo) throws Exception {

        ScheduleRequestComponentInfo componentInfo = new ScheduleRequestComponentInfo();
        componentInfo.setIsTBA(false);

        componentInfo.getBuildingIds().clear();

        TimeSlotInfo timeSlot = new TimeSlotInfo();
        timeSlot.setTypeKey(SchedulingServiceConstants.TIME_SLOT_TYPE_ACTIVITY_OFFERING_STANDARD);
        timeSlot.setStateKey(SchedulingServiceConstants.TIME_SLOT_STATE_ACTIVE);
        timeSlot.setWeekdays(this.getWeekdays());

        long startTime = DateFormatters.HOUR_MINUTE_AM_PM_TIME_FORMATTER.parse(this.getStartTime()).getTime();
        TimeOfDayInfo startTimeOfDayInfo = new TimeOfDayInfo();
        startTimeOfDayInfo.setMilliSeconds(startTime);
        timeSlot.setStartTime(startTimeOfDayInfo);

        long endTime = DateFormatters.HOUR_MINUTE_AM_PM_TIME_FORMATTER.parse(this.getEndTime()).getTime();
        TimeOfDayInfo endTimeOfDayInfo = new TimeOfDayInfo();
        endTimeOfDayInfo.setMilliSeconds(endTime);
        timeSlot.setEndTime(endTimeOfDayInfo);

        try {
            TimeSlotInfo createdTimeSlot = getSchedulingService().createTimeSlot(SchedulingServiceConstants.TIME_SLOT_TYPE_ACTIVITY_OFFERING_STANDARD, timeSlot, defaultContextInfo);
            componentInfo.getTimeSlotIds().add(createdTimeSlot.getId());
        } catch (Exception e) {
            throw new Exception("Error creating timeslot: " + timeSlot, e);
        }

        return componentInfo;
    }

    public List<Integer> getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(List<Integer> weekdays) {
        this.weekdays = weekdays;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public SchedulingService getSchedulingService() {
        return schedulingService;
    }

    public void setSchedulingService(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }
}
/**
 * Copyright 2011 The Kuali Foundation Licensed under the Educational
 * Community License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.student.r2.core.process.infc;

import java.util.List;

import org.kuali.student.r2.common.infc.Relationship;
import org.kuali.student.r2.common.infc.DateRange;
import org.kuali.student.r2.common.infc.RichText;


/**
 * Information about an Instruction. An Instruction is a Relationship
 * between a Process and a Check. Instructions contain a set of
 * applied data that determines if the Check applies to a Process.
 *
 * To determine if a Check applies to a Process:
 *      1. the person is a member of any applied Orgs
 *      2. and the person is a member of any applied Populations
 *      3. and the current ATP type is any of the applied ATP Types
 *      4. and the current ATP is any of the applied ATPs
 *      5. and the current date is within any applied date range
 *
 * If any of the above applied elements is empty, then the appleid
 * element evaluates to true. If all applied elements are empty, then
 * the Check is globally applied.
 *
 * @author tom
 * @since Thu Nov 21 14:22:34 EDT 2011
 */ 

public interface Instruction extends Relationship {

    /**
     * The process key.
     *
     * @name Process Key
     * @required
     * @readOnly
     */
    public String getProcessKey();

    /**
     * The Check Id.
     *
     * @name Check Id
     * @required
     * @readOnly
     */
    public String getCheckId();

    /**
     * The Org Ids to which the Check applies.
     *
     * @name Applied Org Ids
     */
    public List<String> getAppliedOrgIds();

    /**
     * The Population Ids to which the Check applies.
     *
     * @name Applied Population Ids
     */
    public List<String> getAppliedPopulationIds();

    /**
     * The ATP Type keys to which the Check applies.
     *
     * @name Applied Atp Type Keys
     */
    public List<String> getAppliedAtpTypeKeys();

    /**
     * The ATP keys to which the Check applies.
     *
     * @name Applied Atp Keys
     */
    public List<String> getAppliedAtpKeys();

    /**
     * The date ranges to which the Check applies.
     *
     * @name Applied Date Ranges
     */
    public List<? extends DateRange> getAppliedDateRanges();

    /**
     * The text of a message to display to a user on fail or warning
     * for this Instruction.
     *
     * @name Message
     */
    public RichText getMessage();

    /**
     * The position in the Process.
     *
     * @name Position
     */
    public Integer getPosition();

    /**
     * Tests if a failure in this Check results in warning or failure
     * for this step. If true, the Check failure is interpreted as a
     * warning.
     *
     * @name Is Warning 
     */
    public Boolean getIsWarning();

    /**
     * Tests if processing should continue if this Check fails.
     *
     * @name Continue On Fail
     */
    public Boolean getContinueOnFail();

    /**
     * Tests an Exemption can be applied to this Instruction.
     *
     * @name Is Exemptable
     */
    public Boolean getIsExemptable();
}

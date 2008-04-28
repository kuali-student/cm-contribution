/*
 * Copyright 2007 The Kuali Foundation
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
package org.kuali.student.brms.repository.drools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import javax.jcr.Credentials;
import javax.jcr.SimpleCredentials;

import org.drools.repository.RulesRepositoryException;
import org.drools.repository.StateItem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DroolsJackrabbitRepositoryTest {
    private static DroolsJackrabbitRepository repo;

    @BeforeClass
    public static void setUpOnce() throws Exception {
        repo = new DroolsJackrabbitRepository();
        repo.clearAll();
        repo.startupRepository();
    }

    @AfterClass
    public static void tearDownOnce() throws Exception {
        repo.shutdownRepository();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private static Credentials getCredentials() {
        String id = "superuser";
        char[] password = "superuser".toCharArray();
        return new SimpleCredentials(id, password);
    }
    
    @Test
    public void testGetRepository() throws Exception {
        repo.login( getCredentials() );
        assertFalse( repo.getRepository() == null );
        assertTrue( repo.getRepository().getSession().isLive() );
        repo.logout();
    }
    
    @Test
    public void testLogin() throws Exception
    {
        repo.login( getCredentials() );
        assertTrue( repo.getRepository().getSession().isLive() );
        repo.logout();
    }

    @Test
    public void testLogout() throws Exception {
        repo.login( getCredentials() );
        repo.logout();
        assertFalse( repo.getRepository().getSession().isLive() );
    }
    
    @Test
    public void testClearData() throws Exception {
        repo.login( getCredentials() );
        StateItem item1 = repo.getRepository().createState( "NewState" );
        StateItem item2 = repo.getRepository().getState( "NewState" );
        assertEquals( item1.getName(), item2.getName() );
        repo.clearData();
        try {
            assertNull( repo.getRepository().getState( "NewState" ) );
            fail( "'Newstate' should not exist in repository" );
        } catch (RulesRepositoryException e) {
            assertTrue( true );
        }
        repo.logout();
    }

}

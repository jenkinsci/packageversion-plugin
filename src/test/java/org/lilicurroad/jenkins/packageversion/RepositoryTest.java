package org.lilicurroad.jenkins.packageversion;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RepositoryTest {

    @Test
    public void testEquality() throws Exception {

        final Repository first = new Repository("id", "type", "http://some.url");
        final Repository second = new Repository("id", "type", "http://some.url");

        assertEquals(first, second);
    }

}
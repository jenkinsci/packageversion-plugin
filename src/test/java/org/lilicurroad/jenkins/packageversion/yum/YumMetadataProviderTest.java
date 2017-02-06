package org.lilicurroad.jenkins.packageversion.yum;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.lilicurroad.jenkins.packageversion.yum.model.repo.Data;

import java.util.List;

import static org.junit.Assert.*;

public class YumMetadataProviderTest {

    @Test
    public void shouldParseMetaData() throws Exception {

        System.setProperty("java.protocol.handler.pkgs", "org.lilicurroad.jenkins.packageversion.yum");

        final YumMetadataProvider yumMetadataProvider = new YumMetadataProvider();
        final List<Data> data = yumMetadataProvider.getData("classpath:org/lilicurroad/jenkins/packageversion/yum");

        assertThat(data, new BaseMatcher<List<Data>>() {
            @Override
            public boolean matches(Object o) {
                return o instanceof List && ((List<Data>)o).size() == 8;
            }

            @Override
            public void describeMismatch(Object o,
                                         Description description) {

            }

            @Override
            public void describeTo(Description description) {

            }
        });

    }
}

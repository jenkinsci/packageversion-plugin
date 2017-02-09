package org.lilicurroad.jenkins.packageversion.yum;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.lilicurroad.jenkins.packageversion.yum.model.common.Metadata;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import static org.junit.Assert.assertThat;

public class YumPrimaryParserTest {

    @Before
    public void setUp() {
        System.setProperty("java.protocol.handler.pkgs", "org.lilicurroad.jenkins.packageversion.yum");
    }

    @Test
    public void shouldParsePrimary() throws Exception {
        final URL url = new URL("classpath:/org/lilicurroad/jenkins/packageversion/yum/valid/repodata/primary.xml.gz");
        try (final InputStream repoStream = new GZIPInputStream(url.openStream())) {
            final Metadata metadata = new YumPrimaryParser().parse(repoStream);

            assertThat(metadata, new TypeSafeMatcher<Metadata>() {
                @Override
                protected boolean matchesSafely(Metadata metadata) {
                    return metadata.getPackageCount().equals(1104);
                }

                @Override
                public void describeTo(Description description) {

                }
            });

        } catch (final IOException e) {
            throw new RuntimeException("Couldn't retrieve Yum package metadata", e);
        }
    }
}
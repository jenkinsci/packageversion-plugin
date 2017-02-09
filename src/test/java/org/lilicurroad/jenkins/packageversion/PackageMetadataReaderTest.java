package org.lilicurroad.jenkins.packageversion;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.lilicurroad.jenkins.packageversion.yum.ListPackageMetadataMatcher.listPackageMetadata;
import static org.lilicurroad.jenkins.packageversion.yum.PackageMetadataMatcher.packageName;

public class PackageMetadataReaderTest {

    @Before
    public void setUp() throws Exception {
        System.setProperty("java.protocol.handler.pkgs", "org.lilicurroad.jenkins.packageversion.yum");
    }

    @Test
    public void shouldBuildNewMetadataReader() throws Exception {
        final PackageMetadataReader yum = PackageMetadataReader.Builder.newInstance("classpath:/org/lilicurroad/jenkins/packageversion/yum/valid", "yum").build();

        assertThat(yum.getPackages(), listPackageMetadata(packageName("package_1"), packageName("package_2")));
    }

}
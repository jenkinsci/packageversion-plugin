package org.lilicurroad.jenkins.packageversion;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.lilicurroad.jenkins.packageversion.yum.ListPackageMetadataMatcher.listPackageMetadata;
import static org.lilicurroad.jenkins.packageversion.yum.PackageMetadataMatcher.packageName;

public class PackageMetadataReaderTest {

    @Test
    public void shouldBuildNewMetadataReader() throws Exception {

        System.setProperty("java.protocol.handler.pkgs", "org.lilicurroad.jenkins.packageversion.yum");

        final PackageMetadataReader yum = PackageMetadataReader.Builder.newInstance("classpath:org/lilicurroad/jenkins/packageversion/yum", "yum").build();

        assertThat(yum.getPackages(), listPackageMetadata(packageName("package_1"), packageName("package_2")));
    }

}
package org.lilicurroad.jenkins.packageversion.yum;

import org.junit.Test;
import org.lilicurroad.jenkins.packageversion.PackageMetadata;
import org.lilicurroad.jenkins.packageversion.yum.model.repo.Data;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.lilicurroad.jenkins.packageversion.yum.DataMatcher.dataByType;
import static org.lilicurroad.jenkins.packageversion.yum.ListDataMatcher.listData;
import static org.lilicurroad.jenkins.packageversion.yum.ListPackageMetadataMatcher.listPackageMetadata;
import static org.lilicurroad.jenkins.packageversion.yum.PackageMetadataMatcher.packageName;

public class YumMetadataProviderTest {

    @Test
    public void shouldParseMetaData() throws Exception {

        System.setProperty("java.protocol.handler.pkgs", "org.lilicurroad.jenkins.packageversion.yum");

        final YumMetadataProvider yumMetadataProvider = new YumMetadataProvider();
        final List<Data> data = yumMetadataProvider.getData("classpath:org/lilicurroad/jenkins/packageversion/yum");

        assertThat(data, listData(dataByType("primary"), dataByType("other")));
    }

    @Test
    public void shouldParsePrimary() throws Exception {

        System.setProperty("java.protocol.handler.pkgs", "org.lilicurroad.jenkins.packageversion.yum");

        final YumMetadataProvider yumMetadataProvider = new YumMetadataProvider();
        final List<PackageMetadata> packages = yumMetadataProvider.getPackages("classpath:org/lilicurroad/jenkins/packageversion/yum/repodata/primary.xml.gz");

        assertThat(packages, listPackageMetadata(packageName("package_1"), packageName("package_2")));
    }
}

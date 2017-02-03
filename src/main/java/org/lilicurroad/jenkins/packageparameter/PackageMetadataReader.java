package org.lilicurroad.jenkins.packageparameter;

import org.lilicurroad.jenkins.packageparameter.yum.YumMetadataProvider;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class PackageMetadataReader {

    private final String repoPath;
    private final PackageMetadataProvider packageMetadataProvider;

    private PackageMetadataReader(final Builder builder) {
        this.repoPath = builder.repoPath;
        this.packageMetadataProvider = new YumMetadataProvider();
    }

    public List<PackageMetadata> getPackages() throws IOException {
        return packageMetadataProvider.extract(new URL(packageMetadataProvider.getMetatdataFilePath(repoPath)).openStream());
    }

    public List<String> getPackageNames() throws IOException {
        return getPackages().stream().map(PackageMetadata::getPackageName).distinct().sorted().collect(Collectors.toList());
    }

    public List<String> getPackageVersions() throws IOException {
        return getPackages().stream().map(PackageMetadata::getPackageVersion).distinct().sorted().collect(Collectors.toList());
    }

    public static class Builder {
        private final String repoType;
        private final String repoPath;

        private Builder(final String repoPath,
                        final String repoType) {
            this.repoPath = repoPath;
            this.repoType = repoType;
        }

        public static Builder newInstance(final String repoPath,
                                          final String repoType) {
            return new Builder(repoPath, repoType);
        }

        public PackageMetadataReader build() throws JAXBException {
            return new PackageMetadataReader(this);
        }
    }
}

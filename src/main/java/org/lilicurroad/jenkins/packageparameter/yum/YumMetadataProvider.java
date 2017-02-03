package org.lilicurroad.jenkins.packageparameter.yum;

import org.lilicurroad.jenkins.packageparameter.PackageMetadata;
import org.lilicurroad.jenkins.packageparameter.PackageMetadataProvider;
import org.lilicurroad.jenkins.packageparameter.yum.model.common.Metadata;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

public class YumMetadataProvider implements PackageMetadataProvider {

    private YumPrimaryParser parser = new YumPrimaryParser();

    @Override
    public List<PackageMetadata> extract(final InputStream file) {
        try {
            final Metadata metadata = parser.parse(new GZIPInputStream(file));
            return metadata.getPackages().stream().collect(Collectors.toList());
        } catch (final IOException e) {
            throw new RuntimeException("Couldn't retrieve Yum package metadata", e);
        }
    }

    @Override
    public String getMetatdataFilePath(final String repoPath) {
        try (final InputStream repoStream = new URL(String.format("%s/repodata/repomd.xml", repoPath)).openStream()) {
            final Optional<String> location = new YumRepomdParser().parse(repoStream).getData().stream()
                                                                   .filter(data -> data.getType().equals("primary"))
                                                                   .map(data -> data.getLocation().getHref())
                                                                   .findFirst();
            return String.format("%s/%s", repoPath, location.orElse("repodata/primary.xml.gz"));
        } catch (final IOException e) {
            throw new RuntimeException("Couldn't retrieve Yum repo metadata", e);
        }
    }

}

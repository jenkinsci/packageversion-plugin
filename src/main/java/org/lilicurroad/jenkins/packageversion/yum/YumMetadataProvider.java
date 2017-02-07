package org.lilicurroad.jenkins.packageversion.yum;

import org.lilicurroad.jenkins.packageversion.PackageMetadata;
import org.lilicurroad.jenkins.packageversion.PackageMetadataProvider;
import org.lilicurroad.jenkins.packageversion.yum.model.repo.Data;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

public class YumMetadataProvider implements PackageMetadataProvider {

    private final YumPrimaryParser yumPrimaryParser = new YumPrimaryParser();
    private final YumRepomdParser yumRepomdParser = new YumRepomdParser();

    @Override
    public List<PackageMetadata> extract(final String url) {
        return getPackages(getMetatdataFilePath(url));
    }

    List<PackageMetadata> getPackages(final String repoPath) {
        try (final InputStream repoStream = new GZIPInputStream(new URL(repoPath).openStream())) {
            return yumPrimaryParser.parse(repoStream).getPackages().stream().collect(Collectors.toList());
        } catch (final IOException e) {
            throw new RuntimeException("Couldn't retrieve Yum package metadata", e);
        }
    }

    List<Data> getData(final String repoPath) {
        try (final InputStream repoStream = new URL(String.format("%s/repodata/repomd.xml", repoPath)).openStream()) {
            return yumRepomdParser.parse(repoStream).getData().stream().collect(Collectors.toList());
        } catch (final IOException e) {
            throw new RuntimeException("Couldn't retrieve Yum repo metadata", e);
        }
    }

    private String getMetatdataFilePath(final String repoPath) {
        final Optional<String> location = getData(repoPath).stream()
                                                           .filter(data -> data.getType().equals("primary"))
                                                           .map(data -> data.getLocation().getHref())
                                                           .findFirst();
        return String.format("%s/%s", repoPath, location.orElse("repodata/primary.xml.gz"));
    }
}

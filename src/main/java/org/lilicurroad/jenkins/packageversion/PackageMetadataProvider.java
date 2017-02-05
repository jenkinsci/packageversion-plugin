package org.lilicurroad.jenkins.packageversion;

import java.io.InputStream;
import java.util.List;

public interface PackageMetadataProvider {
	List<PackageMetadata> extract(InputStream file);
	String getMetatdataFilePath(String repoPath);
}

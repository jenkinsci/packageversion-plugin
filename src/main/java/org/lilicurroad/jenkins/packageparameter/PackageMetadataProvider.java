package org.lilicurroad.jenkins.packageparameter;

import java.io.InputStream;
import java.util.List;

public interface PackageMetadataProvider {
	List<PackageMetadata> extract(InputStream file);
	String getMetatdataFilePath(String repoPath);
}

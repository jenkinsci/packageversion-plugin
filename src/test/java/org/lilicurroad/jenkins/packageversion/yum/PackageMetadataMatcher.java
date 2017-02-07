package org.lilicurroad.jenkins.packageversion.yum;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.lilicurroad.jenkins.packageversion.PackageMetadata;

public class PackageMetadataMatcher extends TypeSafeDiagnosingMatcher<PackageMetadata> {

    private final String name;

    public PackageMetadataMatcher(final String name) {

        this.name = name;
    }

    public static PackageMetadataMatcher packageName(final String type) {
        return new PackageMetadataMatcher(type);
    }

    @Override
    protected boolean matchesSafely(final PackageMetadata data,
                                    final Description description) {
        return data.getPackageName().equals(name);
    }

    @Override
    public void describeTo(Description description) {

    }
}

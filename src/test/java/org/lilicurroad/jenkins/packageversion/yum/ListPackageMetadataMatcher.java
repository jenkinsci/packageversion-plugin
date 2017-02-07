package org.lilicurroad.jenkins.packageversion.yum;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.lilicurroad.jenkins.packageversion.PackageMetadata;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ListPackageMetadataMatcher extends TypeSafeDiagnosingMatcher<List<PackageMetadata>> {

    private final List<Matcher<PackageMetadata>> data;

    public ListPackageMetadataMatcher(final List<Matcher<PackageMetadata>> data) {
        this.data = data;
    }

    public static ListPackageMetadataMatcher listPackageMetadata(final List<Matcher<PackageMetadata>> data) {
        return new ListPackageMetadataMatcher(data);
    }

    public static ListPackageMetadataMatcher listPackageMetadata(final Matcher<PackageMetadata>... data) {
        return new ListPackageMetadataMatcher(Arrays.asList(data));
    }

    @Override
    protected boolean matchesSafely(final List<PackageMetadata> actual,
                                    final Description description) {
        return actual.size() == data.size() && data.stream().allMatch(matcher -> actual.stream().anyMatch(matcher::matches));
    }

    @Override
    public void describeTo(Description description) {

    }
}

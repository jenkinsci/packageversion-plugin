package org.lilicurroad.jenkins.packageversion;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.Arrays;
import java.util.List;

public class ListRepositoryMatcher extends TypeSafeDiagnosingMatcher<List<Repository>> {

    private final List<Matcher<Repository>> data;

    public ListRepositoryMatcher(final List<Matcher<Repository>> data) {
        this.data = data;
    }

    public static ListRepositoryMatcher repos(final List<Matcher<Repository>> data) {
        return new ListRepositoryMatcher(data);
    }

    public static ListRepositoryMatcher repos(final Matcher<Repository>... data) {
        return new ListRepositoryMatcher(Arrays.asList(data));
    }

    @Override
    protected boolean matchesSafely(final List<Repository> actual,
                                    final Description description) {
        return actual.size() == data.size() && data.stream().allMatch(matcher -> actual.stream().anyMatch(matcher::matches));
    }

    @Override
    public void describeTo(Description description) {

    }
}

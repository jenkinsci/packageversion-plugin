package org.lilicurroad.jenkins.packageversion.yum;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.lilicurroad.jenkins.packageversion.yum.model.repo.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ListDataMatcher extends TypeSafeDiagnosingMatcher<List<Data>> {

    private final List<Matcher<Data>> data;

    public ListDataMatcher(final List<Matcher<Data>> data) {
        this.data = data;
    }

    public static ListDataMatcher listData(final List<Matcher<Data>> data) {
        return new ListDataMatcher(data);
    }

    public static ListDataMatcher listData(final Matcher<Data>... data) {
        return new ListDataMatcher(Arrays.asList(data));
    }

    @Override
    protected boolean matchesSafely(final List<Data> actual,
                                    final Description description) {
        return actual.size() == data.size() && data.stream().allMatch(matcher -> actual.stream().anyMatch(matcher::matches));
    }

    @Override
    public void describeTo(Description description) {

    }
}

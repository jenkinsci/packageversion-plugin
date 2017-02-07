package org.lilicurroad.jenkins.packageversion.yum;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.lilicurroad.jenkins.packageversion.yum.model.repo.Data;

public class DataMatcher extends TypeSafeDiagnosingMatcher<Data> {

    private final String type;

    public DataMatcher(final String type) {

        this.type = type;
    }

    public static DataMatcher dataByType(final String type) {
        return new DataMatcher(type);
    }

    @Override
    protected boolean matchesSafely(final Data data,
                                    final Description description) {
        return data.getType().equals(type);
    }

    @Override
    public void describeTo(Description description) {

    }
}

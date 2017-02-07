package org.lilicurroad.jenkins.packageversion;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class RepositoryMatcher extends TypeSafeDiagnosingMatcher<Repository> {

    private final String id;

    public RepositoryMatcher(final String id) {

        this.id = id;
    }

    public static RepositoryMatcher repo(final String id,
                                         final String type,
                                         final String url) {
        return new RepositoryMatcher(id);
    }

    @Override
    protected boolean matchesSafely(final Repository repository,
                                    final Description description) {
        return repository.getId().equals(id);
    }

    @Override
    public void describeTo(Description description) {

    }
}

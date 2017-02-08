package org.lilicurroad.jenkins.packageversion;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.recipes.LocalData;
import org.kohsuke.stapler.StaplerRequest;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThat;
import static org.lilicurroad.jenkins.packageversion.ListRepositoryMatcher.repos;
import static org.lilicurroad.jenkins.packageversion.RepositoryMatcher.repo;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RepositoryConfigurationTest {

    @Rule
    public JenkinsRule jenkinsRule = new JenkinsRule();

    @Mock
    private StaplerRequest request;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldBeAbleToAddMultipleRepositories() throws Exception {
        final RepositoryConfiguration repositoryConfiguration = new RepositoryConfiguration();

        final Repository repository = new Repository("id", "type", "url");
        final Map<String, Object> formData = new HashMap<>();
        formData.put("repos", JSONArray.fromObject(repository));

        when(request.bindJSON(argThat(new ClassOrSubclassMatcher<>(Repository.class)),
                              argThat(new JSONObjectMatcher(repository))))
                .thenReturn(repository);

        repositoryConfiguration.configure(request, JSONObject.fromObject(formData));
        final List<Repository> repositories = repositoryConfiguration.getRepos().stream().collect(Collectors.toList());

        assertThat(repositories, repos(repo("id", "type", "url")));

        verify(request, times(1)).bindJSON(any(), any());
    }

    @Test
    public void shouldBeAbleToAddSingleRepository() throws Exception {
        final RepositoryConfiguration repositoryConfiguration = new RepositoryConfiguration();

        final Repository repository = new Repository("id", "type", "url");
        final Map<String, Object> formData = new HashMap<>();
        formData.put("repos", JSONObject.fromObject(repository));

        when(request.bindJSON(argThat(new ClassOrSubclassMatcher<>(Repository.class)),
                              argThat(new JSONObjectMatcher(repository))))
                .thenReturn(repository);

        repositoryConfiguration.configure(request, JSONObject.fromObject(formData));
        final List<Repository> repositories = repositoryConfiguration.getRepos().stream().collect(Collectors.toList());

        assertThat(repositories, repos(repo("id", "type", "url")));

        verify(request, times(1)).bindJSON(any(), any());
    }

    @Test
    @LocalData
    public void shouldLoadConfiguration() throws Exception {
        final RepositoryConfiguration repositoryConfiguration = RepositoryConfiguration.get();
        assertThat(repositoryConfiguration.getRepos(), repos(repo("centos", "yum", "http://mirror.centos.org/centos/6/os/i386")));
    }

    public class JSONObjectMatcher extends TypeSafeMatcher<JSONObject> {

        private final Repository repository;

        public JSONObjectMatcher(Repository repository) {
            this.repository = repository;
        }

        @Override
        protected boolean matchesSafely(JSONObject actual) {
            return repository.getId().equals(actual.get("id")) &&
                    repository.getType().equals(actual.get("type")) &&
                    repository.getUrl().equals(actual.get("url"));
        }

        @Override
        public void describeTo(Description description) {

        }
    }

    public class ClassOrSubclassMatcher<T> extends TypeSafeMatcher<Class<T>> {

        private final Class<T> targetClass;

        public ClassOrSubclassMatcher(Class<T> targetClass) {
            this.targetClass = targetClass;
        }

        @SuppressWarnings("unchecked")
        public boolean matchesSafely(Class<T> actual) {
            return actual != null && targetClass.isAssignableFrom(actual);
        }

        public void describeTo(Description desc) {
            desc.appendText("Matches a class or subclass");
        }
    }
}
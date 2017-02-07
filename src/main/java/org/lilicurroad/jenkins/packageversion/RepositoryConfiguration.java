package org.lilicurroad.jenkins.packageversion;

import hudson.Extension;
import hudson.model.Descriptor;
import jenkins.model.GlobalConfiguration;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.StaplerRequest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Extension
public class RepositoryConfiguration extends GlobalConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Map<String, Repository> repos = new HashMap<>();

    public RepositoryConfiguration() {
        load();
    }

    public static RepositoryConfiguration get() {
        return GlobalConfiguration.all().get(RepositoryConfiguration.class);
    }

    @Override
    public boolean configure(final StaplerRequest req,
                             final JSONObject formData) throws Descriptor.FormException {
        repos.clear();

        final Object repoJson = formData.get("repos");
        if (repoJson instanceof JSONArray) {
            final JSONArray jsonArray = (JSONArray) repoJson;
            for (Object object : jsonArray) {
                addRepo(req, (JSONObject) object);
            }
        } else {
            addRepo(req, (JSONObject) repoJson);
        }

        save();
        return true;
    }

    private void addRepo(final StaplerRequest req,
                         final JSONObject jsonObject) {
        final Repository repo = req.bindJSON(Repository.class, jsonObject);
        if (repo != null) {
            if (!StringUtils.isBlank(repo.getUrl())) {
                repos.put(repo.getId(), repo);
            }
        }
    }

    public List<Repository> getRepos() {
        return repos.values().stream().sorted().collect(Collectors.toList());
    }

    public Map<String, Repository> getRepositoryMap() {
        return repos;
    }

}

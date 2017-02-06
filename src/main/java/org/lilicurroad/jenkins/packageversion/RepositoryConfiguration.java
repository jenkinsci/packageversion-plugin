package org.lilicurroad.jenkins.packageversion;

import hudson.Extension;
import hudson.model.Descriptor;
import jenkins.model.GlobalConfiguration;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.StaplerRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public boolean configure(final StaplerRequest req, final JSONObject formData) throws Descriptor.FormException {
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

    private void addRepo(final StaplerRequest req, final JSONObject jsonObject) {
        final Repository repo = req.bindJSON(Repository.class, jsonObject);
        if (repo != null) {
            if (!StringUtils.isBlank(repo.getUrl())) {
                repos.put(repo.getId(), repo);
            }
        }
    }

    public Collection<Repository> getRepos() {
        List<Repository> r = new ArrayList<Repository>();
        r.addAll(repos.values());
        Collections.sort(r);
        return r;
    }

    public Map<String, Repository> getRepositoryMap() {
        return repos;
    }

    public static class ValueComparator implements Comparator<String> {

        Map<String, Double> base;
        public ValueComparator(final Map<String, Double> base) {
            this.base = base;
        }

        public int compare(String a, String b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}

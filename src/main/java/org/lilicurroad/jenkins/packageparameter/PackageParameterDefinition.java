package org.lilicurroad.jenkins.packageparameter;

import hudson.Extension;
import hudson.model.ParameterValue;
import hudson.model.SimpleParameterDefinition;
import hudson.model.StringParameterValue;
import hudson.util.ListBoxModel;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.bind.JavaScriptMethod;
import org.kohsuke.stapler.export.Exported;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class PackageParameterDefinition extends SimpleParameterDefinition {

    @Extension
    public static final DescriptorImpl DESCRIPTOR = new DescriptorImpl();

    private final String rep;
    private final String pkg;

    @DataBoundConstructor
    public PackageParameterDefinition(final String name,
                                      final String description,
                                      final String rep,
                                      final String pkg) {
        super(name, description);
        this.rep = rep;
        this.pkg = pkg;
    }

    @Override
    public ParameterValue createValue(final String value) {
        return new StringParameterValue(getName(), value, getDescription());
    }

    @Override
    public ParameterValue createValue(final StaplerRequest req,
                                      final JSONObject jo) {
        StringParameterValue value = req.bindJSON(StringParameterValue.class, jo);
        value.setDescription(getDescription());
        return value;
    }

    @Exported
    public List<String> getChoices() throws JAXBException, IOException {
        return PackageMetadataReader.Builder.newInstance(DESCRIPTOR.getRepo(rep).getUrl(), DESCRIPTOR.getRepo(rep).getType()).build().getPackageVersions();
    }

    public String getRep() {
        return rep;
    }

    public String getPkg() {
        return pkg;
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return DESCRIPTOR;
    }

    public static class DescriptorImpl extends ParameterDescriptor {

        @Override
        public String getDisplayName() {
            return "Package choice parameter";
        }

        @Override
        public String getHelpFile() {
            return "/help/parameter/choice.html";
        }

        Repository getRepo(String id) {
            Repository repo = null;
            RepositoryConfiguration repoConfig = RepositoryConfiguration.get();
            if (repoConfig != null) {
                repo = repoConfig.getRepositoryMap().get(id);
            }
            return repo;
        }

        public ListBoxModel doFillRepItems() {
            ListBoxModel items = new ListBoxModel();
            RepositoryConfiguration.get().getRepos().forEach(repository -> {
                items.add(repository.getId(), repository.getId());
            });
            return items;
        }

        public ListBoxModel doFillPkgItems(@QueryParameter final String rep) throws IOException {
            ListBoxModel items = new ListBoxModel();
            if (null != rep && rep.length() > 0) {
                PackageMetadataReader.Builder.newInstance(getRepo(rep).getUrl(), getRepo(rep).getType()).build().getPackageNames().forEach(s -> {
                    items.add(s, s);
                });
            }
            return items;
        }

        @Override
        public boolean configure(StaplerRequest req,
                                 JSONObject json) throws FormException {
            return super.configure(req, json);
        }

        @JavaScriptMethod
        public int add(int x,
                       int y) {
            return x + y;
        }

    }

}

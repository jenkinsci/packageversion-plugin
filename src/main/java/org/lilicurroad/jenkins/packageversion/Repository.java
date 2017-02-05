package org.lilicurroad.jenkins.packageversion;

import org.kohsuke.stapler.DataBoundConstructor;

import java.io.Serializable;

public class Repository implements Serializable, Comparable<Repository> {

    private static final long serialVersionUID = 1L;

    final private String url;
    final private String type;
    final private String id;

    @DataBoundConstructor
    public Repository(final String id,
                      final String type,
                      final String url) {
        this.id = id;
        this.type = type;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Repository that = (Repository) o;

        if (url != null ? !url.equals(that.url) : that.url != null) {
            return false;
        }

        if (type != null ? !type.equals(that.type) : that.type != null) {
            return false;
        }

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(final Repository other) {
        return id.compareTo(other.getId());
    }
}

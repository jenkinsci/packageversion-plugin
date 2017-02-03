package org.lilicurroad.jenkins.packageparameter.yum.model.repo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Set;

@XmlRootElement(name = "metadata", namespace = "http://linux.duke.edu/metadata/repo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "revision", "data" })
public class Repomd {

    @XmlElement(name = "revision")
    private Long revision;
    @XmlElement(name = "data", required = true)
    private Set<Data> data;

    public Long getRevision() {
        return revision;
    }

    public void setRevision(Long revision) {
        this.revision = revision;
    }

    public Set<Data> getData() {
        return data;
    }

    public void setData(Set<Data> data) {
        this.data = data;
    }
}
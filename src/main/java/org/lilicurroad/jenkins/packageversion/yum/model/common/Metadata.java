package org.lilicurroad.jenkins.packageversion.yum.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Set;

@XmlRootElement(name = "metadata", namespace = "http://linux.duke.edu/metadata/common")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "packages" })
public class Metadata {

    @XmlAttribute(name = "packages")
    private Integer packageCount;
    @XmlElement(name = "package", required = true)
    private Set<Package> packages;

    public Integer getPackageCount() {
        return packageCount;
    }

    public void setPackageCount(Integer packageCount) {
        this.packageCount = packageCount;
    }

    public Set<Package> getPackages() {
        return packages;
    }

    public void setPackages(Set<Package> packages) {
        this.packages = packages;
    }
}

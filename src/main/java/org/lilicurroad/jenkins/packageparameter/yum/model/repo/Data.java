package org.lilicurroad.jenkins.packageparameter.yum.model.repo;

import org.lilicurroad.jenkins.packageparameter.yum.model.common.Package;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "package", namespace = "http://linux.duke.edu/metadata/repo")
@XmlAccessorType(XmlAccessType.NONE)
public class Data {

    @XmlAttribute(name = "type")
    private String type;
    @XmlElement
    private Location location;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
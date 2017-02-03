package org.lilicurroad.jenkins.packageparameter.yum.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = "http://linux.duke.edu/metadata/common")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Version {

    @XmlAttribute
    private String epoch;
    @XmlAttribute
    private String ver;
    @XmlAttribute
    private String rel;

    public String getEpoch() {
        return epoch;
    }

    public void setEpoch(String epoch) {
        this.epoch = epoch;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}

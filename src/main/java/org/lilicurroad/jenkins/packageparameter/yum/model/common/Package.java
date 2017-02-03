package org.lilicurroad.jenkins.packageparameter.yum.model.common;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.lilicurroad.jenkins.packageparameter.PackageMetadata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "package", namespace = "http://linux.duke.edu/metadata/common")
@XmlAccessorType(XmlAccessType.NONE)
public class Package implements Comparable<Package>, PackageMetadata {

    private String name;
    private String arch;
    private Location location;
    private Version version;
    private Time time;

    @XmlElement(required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @XmlElement
    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    @XmlElement
    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    @XmlElement
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String getPackageName() {
        return name;
    }

    @Override
    public String getPackageVersion() {
        return String.format("%s-%s", version.getVer(), version.getRel());
    }

    @Override
    public int compareTo(final Package o) {
        if (o == null || o.getTime() == null || o.getTime().getBuild() == null) {
            return 1;
        } else if (time.getBuild() == null) {
            return -1;
        } else {
            return time.getBuild().compareTo(o.getTime().getBuild());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Package)) {
            return false;
        }
        Package aPackage = (Package) o;
        return Objects.equals(name, aPackage.name) &&
                Objects.equals(arch, aPackage.arch) &&
                Objects.equals(location, aPackage.location) &&
                Objects.equals(version, aPackage.version) &&
                Objects.equals(time, aPackage.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, arch, location, version, time);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("arch", arch)
                .append("location", location)
                .append("version", version)
                .append("time", time)
                .toString();
    }

}

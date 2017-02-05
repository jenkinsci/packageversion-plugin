package org.lilicurroad.jenkins.packageversion.yum;

import org.lilicurroad.jenkins.packageversion.yum.model.repo.Repomd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

public class YumRepomdParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(YumRepomdParser.class);
    private final Unmarshaller unmarshaller;

    public YumRepomdParser() {
        try {
            this.unmarshaller = JAXBContext.newInstance(Repomd.class).createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException("Couldn't initialize Yum Metadata XML parser.", e);
        }
    }

    public Repomd parse(InputStream inputStream) {
        try {
            final JAXBElement<Repomd> jaxbElement = unmarshaller.unmarshal(new StreamSource(inputStream), Repomd.class);
            return jaxbElement.getValue();
        } catch (JAXBException e) {
            LOGGER.error("Cannot communicate with the Yum repomd repository xml", e);
            throw new RuntimeException(e);
        }

    }
}

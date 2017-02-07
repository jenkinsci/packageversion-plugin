package org.lilicurroad.jenkins.packageversion.yum;

import org.lilicurroad.jenkins.packageversion.yum.model.common.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

public class YumPrimaryParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(YumPrimaryParser.class);
    private final Unmarshaller unmarshaller;

    public YumPrimaryParser() {
        try {
            this.unmarshaller = JAXBContext.newInstance(Metadata.class).createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException("Couldn't initialize Yum Metadata XML parser.", e);
        }
    }

    public Metadata parse(InputStream inputStream) {
        try {
            final JAXBElement<Metadata> jaxbElement = unmarshaller.unmarshal(new StreamSource(inputStream), Metadata.class);
            return jaxbElement.getValue();
        } catch (JAXBException e) {
            LOGGER.error("Cannot communicate with the Yum primary repository xml", e);
            throw new RuntimeException(e);
        }
    }
}

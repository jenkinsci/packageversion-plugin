package org.lilicurroad.jenkins.packageversion.yum.classpath;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ClasspathUrlConnection extends URLConnection {

    ClasspathUrlConnection(URL url) throws IOException {
        super(url);
    }

    public String getContentType() {
        return guessContentTypeFromName(url.getFile());
    }

    public void connect() throws IOException {
        connected = true;
    }

    public InputStream getInputStream() throws IOException {
        return ClassLoader.class.getResourceAsStream("/" + getURL().getFile());
    }
}
package org.lilicurroad.jenkins.packageversion.yum.classpath;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class Handler extends URLStreamHandler {

    protected void parseURL(final URL url,
                            final String spec,
                            final int start,
                            final int end) {
        super.parseURL(url, spec, start, end);
        setURL(url, url.getProtocol(), url.getHost(), url.getPort(),
               url.getAuthority(), url.getUserInfo(),
               url.getPath(), url.getQuery(), url.getRef());
    }

    protected URLConnection openConnection(final URL url)
            throws IOException {
        return new ClasspathUrlConnection(url);
    }
}
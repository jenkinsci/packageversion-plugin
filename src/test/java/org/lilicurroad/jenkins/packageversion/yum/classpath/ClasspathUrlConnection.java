package org.lilicurroad.jenkins.packageversion.yum.classpath;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

public class ClasspathUrlConnection extends URLConnection {

    public String getContentType( ) {
        return guessContentTypeFromName( url.getFile( ) );
    }

    ClasspathUrlConnection(URL url) throws IOException {
        super( url );
    }

    public void connect( ) throws IOException {
        connected = true;
    }

    public InputStream getInputStream( ) throws IOException {
        return ClassLoader.class.getResourceAsStream("/" + getURL().getFile());
    }
}
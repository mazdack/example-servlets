package test.spring;

import org.apache.tomcat.InstanceManager;
import org.apache.tomcat.SimpleInstanceManager;
import org.eclipse.jetty.apache.jsp.JettyJasperInitializer;
import org.eclipse.jetty.plus.annotation.ContainerInitializer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        HandlerCollection handlers = new HandlerCollection();

        WebAppContext context = new WebAppContext();
        context.setResourceBase(Paths.get("src", "main", "webapp").toString());
        context.setDescriptor(Paths.get("target", "web.xml").toString());
        context.setContextPath("/");
        context.setAttribute("org.eclipse.jetty.containerInitializers", new ContainerInitializer(new JettyJasperInitializer(), null));
        context.setAttribute(InstanceManager.class.getName(), new SimpleInstanceManager());

        handlers.addHandler(context);

        server.setHandler(handlers);

        server.start();
        server.join();
    }
}

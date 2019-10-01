import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.hibernate.SessionFactory;
import servlet.CustomerServlet;
import servlet.DailyReportServlet;
import servlet.NewDayServlet;
import servlet.ProducerServlet;
import util.DBHelper;

public class Main {

    public static void main(String[] args) throws Exception {

        SessionFactory sessionFactory = DBHelper.getSessionFactory();

        CustomerServlet customerServlet = new CustomerServlet();
        DailyReportServlet dailyReportServlet = new DailyReportServlet();
        NewDayServlet newDayServlet = new NewDayServlet();
        ProducerServlet producerServlet = new ProducerServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(customerServlet), "/customer");
        context.addServlet(new ServletHolder(dailyReportServlet), "/report/*"); //*
        context.addServlet(new ServletHolder(newDayServlet), "/newday");
        context.addServlet(new ServletHolder(producerServlet), "/producer");


        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("templates");
        resource_handler.setWelcomeFiles(new String[]{"resultPage.html"}); // В качестве главной страницы будет использоваться authPage.html

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);
        //      server.setHandler(context);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started !!!!!!!!!!!!!!!"); // Надо удалить?
        server.join();
    }
}

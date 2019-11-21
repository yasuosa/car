package cn.rpy.sys.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context=servletContextEvent.getServletContext();
        context.setAttribute("ctx",context.getContextPath());
        System.out.println("------------Servlet容器创建成功；ctx被放在Servlet的作用域内----------");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

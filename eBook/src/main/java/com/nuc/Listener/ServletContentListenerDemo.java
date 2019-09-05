package com.nuc.Listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContentListenerDemo implements ServletContextListener, ServletContextAttributeListener {

    /*
    * ServletContextListener上下文监听
    * ServletContextAttributeListener  属性监听
    * */

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("监听器初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("监听器销毁时");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        System.out.println("监听器增加属性->名字"+scae.getName()+"属性内容"+scae.getValue());

    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        System.out.println("监听器删除属性->名字"+scae.getName()+"属性内容"+scae.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        System.out.println("监听器增加替换->名字"+scae.getName()+"属性内容"+scae.getValue());
    }
}

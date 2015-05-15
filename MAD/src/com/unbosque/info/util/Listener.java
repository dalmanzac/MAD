package com.unbosque.info.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class Listener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent arg) {
		System.out.println("Aplicación Iniciada");
		PropertyConfigurator.configure(getClass().getResource("log4j.properties"));
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg) {
		System.out.println("Aplicación Finalizada");

	}
}

package com.github.cutstock.utils;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.hsqldb.Server;
import org.hsqldb.server.ServerAcl.AclFormatException;
import org.hsqldb.server.ServerConfiguration;
import org.hsqldb.persist.HsqlProperties;

public final class HSQLServerUtil {

	private static final Logger LOG = Logger.getLogger(HSQLServerUtil.class);

	private static final HSQLServerUtil UTIL = new HSQLServerUtil();
	private Server hsqlServer;

	private HSQLServerUtil() {
		// prevent instantiation
	}

	/**
	 * @return utility instance.
	 */
	public static HSQLServerUtil getInstance() {
		return UTIL;
	}

	private void doStart(final HsqlProperties props) {
		ServerConfiguration.translateDefaultDatabaseProperty(props);
		hsqlServer = new Server();
		hsqlServer.setRestartOnShutdown(false);
		hsqlServer.setNoSystemExit(false);
		try {
			hsqlServer.setProperties(props);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AclFormatException e) {
			e.printStackTrace();
		}

		LOG.info("Configured the HSQLDB server...");
		hsqlServer.start();
		LOG.info("HSQLDB server started on port " + hsqlServer.getPort()
				+ "...");
	}

	public void start(final String dbName, final int port) {
		HsqlProperties props = new HsqlProperties();
		props.setProperty("server.port", port);
		props.setProperty("server.database.0", dbName);
		props.setProperty("server.dbname.0", dbName);
		doStart(props);
	}

	/**
	 * start the server with a database configuration.
	 * 
	 * @param dbName
	 *            the name of database
	 */
	public void start(final String dbName) {
		HsqlProperties props = new HsqlProperties();
		props.setProperty("server.database.0", dbName);
		props.setProperty("server.dbname.0", "cutstock");
		doStart(props);
	}

	/**
	 * shutdown the started instance.
	 */
	public void stop() {
		LOG.info("HSQLDB server shutting down...");
		hsqlServer.stop();
		LOG.info("HSQLDB server shutting down... done");
	}

	public void start() {
		
	}

}
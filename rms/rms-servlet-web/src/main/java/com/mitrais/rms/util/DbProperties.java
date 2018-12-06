package com.mitrais.rms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Thread.currentThread;

public class DbProperties {
    private DbProperties(String dbName, String host, Integer port, String username, String password) {
        this.dbName = dbName;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    private static DbProperties dbProperties;
    private String dbName;
    private String host;
    private Integer port;
    private String username;
    private String password;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static DbProperties getInstance() {
        if (dbProperties == null) {
            try {
                Properties prop = new Properties();
                InputStream in = currentThread().getContextClassLoader().getResourceAsStream(("database.properties"));
                prop.load(in);

                String dbName = prop.getProperty("db");
                String host = prop.getProperty("host");
                Integer port = Integer.valueOf(prop.getProperty("port"));
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                dbProperties = new DbProperties(dbName, host, port, user, password);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return dbProperties;
        } else {
            return dbProperties;
        }
    }
}

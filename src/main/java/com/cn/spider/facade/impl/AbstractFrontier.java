package com.cn.spider.facade.impl;

import java.io.File;
import java.io.FileNotFoundException;

import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

public abstract class AbstractFrontier {
	private Environment env;
	
	private static final String CLASS_CATALOG= "java_class_catalog";
	
	protected StoredClassCatalog javaCatalog;
	
	protected Database catalogdatabase;
	
	protected Database database;
	
	public  AbstractFrontier(String homeDirectory) throws DatabaseException,FileNotFoundException{
		EnvironmentConfig envConfig = new EnvironmentConfig();
		envConfig.setTransactional(true);
		envConfig.setAllowCreate(true);
		File file =new File(homeDirectory);
		if(file.isDirectory()) {
			env = new Environment(file, envConfig);
			}else {
				file.mkdirs();
			env = new Environment(file, envConfig);
			}
		DatabaseConfig dbconfig = new DatabaseConfig();
		dbconfig.setAllowCreate(true);
		dbconfig.setTransactional(true);
		catalogdatabase=env.openDatabase(null,CLASS_CATALOG , dbconfig);
	    javaCatalog =new StoredClassCatalog(catalogdatabase);
		DatabaseConfig dbconfig0=new DatabaseConfig();
		dbconfig0.setAllowCreate(true);
		dbconfig0.setTransactional(true);
		database =env.openDatabase(null, "url", dbconfig);
		
	}
	public void close() {
		database.close();
		javaCatalog.close();
		env.close();
	}
	protected abstract void put(Object key,Object value);
	
	
	protected abstract Object get (Object key);
	
	
	protected abstract Object delete(Object key);
	
}

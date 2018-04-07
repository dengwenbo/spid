package com.cn.spider.facade.impl;

import java.io.FileNotFoundException;
import java.util.Map.Entry;
import java.util.Set;

import com.cn.spider.entity.CrawlUrl;
import com.cn.spider.facade.Frontier;
import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.je.DatabaseException;

public class BDBFrontier extends AbstractFrontier implements Frontier {
	
	private StoredMap pendingUrisDB=null;
	 
	private  static BDBFrontier bdbFrontier=null;

	private BDBFrontier(String homeDirectory) throws DatabaseException, FileNotFoundException {
		super(homeDirectory);
         EntryBinding keyBinding =new SerialBinding<>(javaCatalog, String.class);
         EntryBinding valueBinding = new SerialBinding(javaCatalog,CrawlUrl.class);
         pendingUrisDB = new StoredMap(database,keyBinding,valueBinding,true);
	}
	
	
	public static BDBFrontier getInstance() throws Exception {
		if(bdbFrontier== null) {
			synchronized (BDBFrontier.class) {
				if(bdbFrontier== null) {
					bdbFrontier =  new BDBFrontier("G:\\berkeleyDB");
				}
			}
		}
            return bdbFrontier;
	}
	@Override
	public CrawlUrl getNext() throws Exception {
		CrawlUrl result =null;
	    if(!pendingUrisDB.isEmpty()) {
	    	Set entrys = pendingUrisDB.entrySet();
	    	Entry<String,CrawlUrl> entry =(Entry<String,CrawlUrl>)pendingUrisDB.entrySet().iterator().next();
	    	result = entry.getValue();
	    	String key =entry.getKey();
	    	delete(key);
	    }
		return result;
	}
	
	@Override
	public boolean putUrl(CrawlUrl url) {
		put(url.getOriUrl(),url);
		return true;
	}

	@Override
	protected void put(Object key, Object value) {
		pendingUrisDB.put(key, value);
		
	}

	@Override
	protected Object get(Object key) {
		
		return pendingUrisDB.get(key);
	}

	@Override
	protected Object delete(Object key) {
		return pendingUrisDB.remove(key);
	}

	
	public boolean isEmpty() {
		return pendingUrisDB.isEmpty();
	}
	
	public int getUrlNUm() {
		return pendingUrisDB.size();
	}
	
	public static void main(String[] args) {
		try {
			BDBFrontier frontier =new BDBFrontier("G:\\bdb");
			CrawlUrl url =new CrawlUrl();
			url.setOriUrl("http://www.baidu.com");
			frontier.putUrl(url);
			System.out.println(((CrawlUrl)frontier.getNext()).getOriUrl());
			frontier.close();
		} catch (DatabaseException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

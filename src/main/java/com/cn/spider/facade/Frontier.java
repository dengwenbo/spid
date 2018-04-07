package com.cn.spider.facade;

import com.cn.spider.entity.CrawlUrl;

public interface Frontier {

	
	public CrawlUrl getNext() throws Exception;
	
	
	public boolean putUrl(CrawlUrl url) throws Exception;
}

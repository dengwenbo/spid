package com.cn.spider.fonter;

import java.util.Set;

import com.cn.spider.entity.CrawlUrl;
import com.cn.spider.facade.LinkFilter;
import com.cn.spider.facade.impl.BDBFrontier;
import com.cn.spider.util.HtmlParserTool;
import com.cn.spider.util.SimpleBloomFilter;

public class MyCrawler {
  
  private void initCrawlerWithSeeds(String[] seeds) throws Exception {
	  for(int i =0;i<seeds.length;i++) {
		  SimpleBloomFilter filter =new SimpleBloomFilter();
		  BDBFrontier bdbfrontier = BDBFrontier.getInstance();
		  String oriUrl =seeds[i];
		  if(!filter.contains(oriUrl)) {
			  filter.add(oriUrl);;
			  CrawlUrl url = new CrawlUrl();
			  url.setOriUrl(oriUrl);
			  bdbfrontier.putUrl(url);  
		  }
	  }
  }
  
  public void Crawling(String[]  seeds) throws Exception {
	  
	  LinkFilter filter = new LinkFilter() {

		@Override
		public boolean accept(String url) {
		if(url.startsWith("https://v.qq.com/")) {
			return true;
		}
			return false;
		}
	  };
	  
	  initCrawlerWithSeeds(seeds);
	  BDBFrontier bdbfrontier = BDBFrontier.getInstance();
	  int i=0;
	  while(! bdbfrontier.isEmpty() && i<=1000) {
		  CrawlUrl url =bdbfrontier.getNext() ;
		 if(url == null) {
			 continue;
		 }
		// DownLoadFile down = new DownLoadFile();
		 //down.downloadFile(visitedUrl);
		 Set<CrawlUrl> links = HtmlParserTool.extractLinks(url.getOriUrl(), filter);
		 SimpleBloomFilter bloomFilter =new SimpleBloomFilter();
		 links.stream().peek(m ->  {
			 if(!bloomFilter.contains(url)) {
			 bloomFilter.add(m);
			 bdbfrontier.putUrl(m);
			 }
		 });
	  }
	  i++;
  }
  
  public static void main(String[] args) throws Exception {
	  MyCrawler crawler = new MyCrawler();	
	  crawler.Crawling(new String[] {"https://v.qq.com/"});
  }
}

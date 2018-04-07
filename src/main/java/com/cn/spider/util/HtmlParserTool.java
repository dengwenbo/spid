package com.cn.spider.util;

import java.util.HashSet;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.LinkStringFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.cn.spider.entity.CrawlUrl;
import com.cn.spider.facade.LinkFilter;



public class HtmlParserTool {
	
	public static Set<CrawlUrl> extractLinks(String url,LinkFilter filter){
		Set<CrawlUrl> links = new HashSet<CrawlUrl>();
		
		try {
			Parser parser = new Parser(url);
			parser.setEncoding("UTF-8");
			NodeFilter frameFilter = new  NodeFilter() {

				@Override
				public boolean accept(Node node) {
					if(node.getText().startsWith("frame src=")) {
						return true;
					}
						return false;
				}
				
			};
			
			OrFilter linkFilter = new  OrFilter(new NodeClassFilter( LinkTag.class), frameFilter) ;
			NodeList list = parser.extractAllNodesThatMatch(linkFilter);
			
			for(int i = 0; i<list.size();i++) {
				Node tag = list.elementAt(i);
				
				if(tag instanceof LinkTag) {
					LinkTag  link = (LinkTag)tag;
					String linkUrl = link.getLink();
					if(filter.accept(linkUrl)) {
						CrawlUrl unVisitedUrl = new CrawlUrl();
						unVisitedUrl.setOriUrl(linkUrl);
						links.add(unVisitedUrl);
					}
				}else {
					String frame = tag.getText();
					int start = frame.indexOf("src=");
					frame = frame.substring(start);
					int end = frame.indexOf(" ");
					if(end == -1) {
						end = frame.indexOf(">");
						String frameurl =frame.substring(5, end-1);
						if(filter.accept(frameurl)) {
							CrawlUrl unVisitedUrl = new CrawlUrl();
							unVisitedUrl.setOriUrl(frameurl);
							links.add(unVisitedUrl);
						}
					}
					
					
				}
				
			}
			
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return links;
	}

}

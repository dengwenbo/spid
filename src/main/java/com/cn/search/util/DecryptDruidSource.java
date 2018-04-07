package com.cn.search.util;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;

public class DecryptDruidSource{
	
	  public static void main(String[] args) throws Exception {
		        //解密
		   String word="RRtws34o/kSqM9nj1HQU7YxAjV27oP/e373n9GAM5KuyKUOSUKPfiV+ma6GRy+b1xlrFjM+jX9gHb+r8A6z2Lw==";
		   String publickey="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIKaWcE0fzfb7VIgO9NOmIYIYk8RbK2D2KHQcQekU+z3bjaepcmTHTzdi7k+k9C7XPC9V5HYPfkvxCkTB0ZWHrkCAwEAAQ==";
		   String decryptword = ConfigTools.decrypt(publickey,word);
	       System.out.println(decryptword);
	  }
}
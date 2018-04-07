package com.cn.spider.util;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;

public class DownLoadFile {

	/**
	 * ����url����ҳ�������Ƶ���Ҫ�������ҳ���ļ�����ȥ��url�еķ��ļ����ַ�
	 */
	public String getFileNameByUrl(String url, String contentType) {
		// �Ƴ�http
		url = url.substring(7);
		if (contentType.indexOf("html") != -1) {
			url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
			return url;
		} else {

			return url.replaceAll("\\?:*|<>\"]", "_") + "." + contentType.substring(contentType.lastIndexOf("/") + 1);
		}
	}

	/**
	 * ���ļ��洢������
	 * 
	 * @param filepath
	 * @param data
	 * @throws IOException
	 */
	private void saveToLocal(String filepath, InputStream input) throws IOException {

		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(filepath));

			while (input.read() > 0) {
				out.write(input.read());
			}
			if (input != null) {
				input.close();
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    /**
     * ���ص���ҳ
     * @param url
     * @return
     */
	public String downloadFile(String url) {
		String filepath = null;
		try {
			HttpClient httpClient = HttpClients.createDefault();
			//HttpHost httpHost = new HttpHost("192.168.236.128", 80, "http");
			//Builder requestConfig = RequestConfig.custom().setProxy(httpHost).setConnectionRequestTimeout(5000);
			HttpPost httpPost = new HttpPost(url);
			HttpResponse response = httpClient.execute(httpPost);
			int code = response.getStatusLine().getStatusCode();
	

			if (code == HttpStatus.SC_OK) {
				InputStream input = response.getEntity().getContent();
				filepath = "temp:\\" + getFileNameByUrl(url, response.getFirstHeader("Content-Type").getValue());
				saveToLocal(filepath, input);
			} else {
				System.err.println("״̬���ǣ�" + code);
				filepath = null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filepath;
	}

	
}

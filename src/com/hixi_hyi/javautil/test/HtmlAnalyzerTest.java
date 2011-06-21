package com.hixi_hyi.javautil.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import com.hixi_hyi.javautil.HtmlAnalyzer;
import com.hixi_hyi.javautil.URL2FileDownloader;

/**
 *
 * htmlに書かれているurlを全部表示させるサンプル
 * @author Hiroyoshi HOUCHI
 *
 */
public class HtmlAnalyzerTest {
	private static final String URL_PATH = "http://www.hixi-hyi.com/";
//			For Mac
	private static final String FILE_PATH = "/Download/index.html";
//			For Windows
//			private static final String FILE_PATH = "C:¥¥Download¥¥index.html";

	public static void main(String[] args) throws IOException {
		URL url = new URL(URL_PATH);
		File file = new File(FILE_PATH);
		URL2FileDownloader downloader = new URL2FileDownloader(url, file);

		//めんどくさいのでURL2FileDownloaderを利用．ファイルにも保存されちゃう．ごめん
		downloader.run();
		String html = downloader.getString();
		ArrayList<String> urlList = new ArrayList<String>();
		urlList.addAll(HtmlAnalyzer.getUrl(html));
		for(String urlStr:urlList){
			System.out.println(urlStr);
		}
	}

}

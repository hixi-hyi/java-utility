/**
 * Copyright (c) <2011>, <Hiroyoshi Houchi> All rights reserved.
 *
 * http://www.hixi-hyi.com/
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the  following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * The names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
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

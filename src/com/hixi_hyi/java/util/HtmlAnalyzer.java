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
package com.hixi_hyi.java.util;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Htmlを解析するクラスです．
 * 今はURLを返すメソッドしかありません．
 * そのうち更新するかも．
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class HtmlAnalyzer {
	private static final String URL_PATTERN = "(http|https):([^\\x00-\\x20()\"<>\\x7F-\\xFF])*";

	/**
	 * HTMLから(http,httpsから始まる)重複のないURLを抜き出して返します
	 * @return URL http or httpsから始まる重複なしのURL
	 */
	public static Collection<String> getURL(String html){
		Pattern urlPattern = Pattern.compile(URL_PATTERN);
		Matcher m = urlPattern.matcher(html);
		LinkedHashSet<String> urlList = new LinkedHashSet<String>();
		while(m.find()){
			urlList.add(m.group());
		}
		return urlList;
	}
}

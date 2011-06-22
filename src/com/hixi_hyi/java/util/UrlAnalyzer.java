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

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * URLのCollectionから該当するURLを抜き出して返す静的クラス
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class UrlAnalyzer {
	public static Collection<String> getInternalUrl(Collection<String> urls,String internalUrl){
		ArrayList<String> list = new ArrayList<String>();
		for(String url:urls){
			if(url.startsWith(internalUrl)){
				list.add(url);
			}
		}
		return list;
	}

	public static Collection<String> getExternalUrl(Collection<String> urls,String internalUrl){
		ArrayList<String> list = new ArrayList<String>();
		for(String url:urls){
			if(!url.startsWith(internalUrl)){
				list.add(url);
			}
		}
		return list;
	}

	public static Collection<String> getContainExtUrl(Collection<String> urls, String...exts){
		if(exts.length==0){
			return new ArrayList<String>();
		}
		ArrayList<String> list = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		sb.append(".(");
		for(String s:exts){
			sb.append(s);
			sb.append("|");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(")$");
		Pattern p = Pattern.compile(sb.toString());
		for(String url:urls){
			Matcher m = p.matcher(url);
			if(m.find()){
				list.add(url);
			}
		}
		return list;
	}

	public static Collection<String> getNotContainExtUrl(Collection<String> urls,String...exts){
		if(exts.length==0){
			return urls;
		}
		ArrayList<String> list = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		sb.append(".(");
		for(String s:exts){
			sb.append(s);
			sb.append("|");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(")$");
		Pattern p = Pattern.compile(sb.toString());
		for(String url:urls){
			Matcher m = p.matcher(url);
			if(!m.find()){
				list.add(url);
			}
		}
		return list;
	}
}

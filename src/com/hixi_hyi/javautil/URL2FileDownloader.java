/**
 * Copyright (c) <2011>, <Hiroyoshi Houchi> All rights reserved.
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

package com.hixi_hyi.javautil;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 指定されたURLを読み取って指定されたFileに書き込みます．
 * 既にファイルが有った場合は処理を行いません．
 * ディレクトリがない場合は自動的に作成されます．
 * スレッド実行を出来る実装上，指定されたURLが存在しない場合例外はスローされません．
 * fileに書き込みたいけど，そのままも使いたい場合に便利です．
 *
 * @author Hiroyoshi HOUCHI
 *
 */
public class URL2FileDownloader implements Runnable {
	private URL url;
	private File file;
	private ByteArrayOutputStream buffer;

	/**
	 * 指定されたurlとfileで初期化します
	 * @param url ダウンロード先url
	 * @param file 保存先file
	 */
	public URL2FileDownloader(URL url,File file){
		this.url = url;
		this.file = file;
		this.buffer = new ByteArrayOutputStream();
		File dir = this.file.getParentFile();
		dir.mkdirs();
	}


	/**
	 * ダウンロードを実行します
	 */
	@Override
	public void run() {
		try {
			if(file.exists()){
				return;
			}

			URLConnection uc = url.openConnection();
			BufferedInputStream in = new BufferedInputStream(uc.getInputStream());
	        OutputStream out = new FileOutputStream(file);

	        try {
	            byte[] buf = new byte[1024];
	            int len = 0;

	            while ((len = in.read(buf)) > 0) {
	                buffer.write(buf, 0, len);
	            }
	            out.write(buffer.toByteArray());

	            out.flush();
	        } finally {
	            out.close();//ストリームをクローズすることを忘れずに
	            in.close();
	        }

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * ダウンロードされたファイルを文字列で表したもの
	 * 再利用したい場合にどうぞ
	 * (ダウンロードしたURLがHTMLの場合，HTMLがString型で返されます)
	 * @return ダウンロードされた文字列
	 */
	public String getString(){
		return buffer.toString();
	}


	/**
	 * ダウンロードされたファイルをバイト列で表したもの
	 * 再利用したい場合にどうぞ
	 * (ダウンロードしたURLが画像であった場合画像のByteデータが返されます)
	 * @return ダウンロードされたバイト列
	 */
	public byte[] getBytes(){
		return buffer.toByteArray();
	}

	/**
	 * URLとFileのパスを表示します
	 */
	@Override
	public String toString() {
		return "URL2FileDownloader [url=" + url + ", file=" + file + "]";
	}

}

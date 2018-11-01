package com.mode.error;

import io.netty.handler.codec.http.HttpResponseStatus;

/** 
* @author 作者 huangxinyu 
* @version 创建时间：2018年3月28日 下午8:17:06 
* 类说明 
*/
public class MyHttpResponseStatus extends HttpResponseStatus{
	
	
	public MyHttpResponseStatus(int code, String reasonPhrase) {
		super(code, reasonPhrase);
	}


	public static final MyHttpResponseStatus SERVERSHUTDOWN = new MyHttpResponseStatus(800, "server update");

}

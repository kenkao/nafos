package com.mode.filter;

import com.mode.error.MyHttpResponseStatus;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import nafos.core.entry.ResultStatus;
import nafos.core.mode.filter.fInterface.HttpMessageFilter;
import nafos.core.util.UriUtil;
import org.springframework.stereotype.Component;

/** 
* @author 作者 huangxinyu 
* @version 创建时间：2018年4月9日 下午6:22:13 
* 通信过滤 
*/
@Component
public class MyMessageFilter implements HttpMessageFilter {

	private boolean isShutdown = false;

	public ResultStatus filter(ChannelHandlerContext ctx, FullHttpRequest req) {
		ResultStatus resultStatus = new ResultStatus();
		// 0) 系统正在维护
		if(isShutdown&&
				(!UriUtil.parseUri(req.uri()).equals("/login/thirdPartyLogin")||
						!UriUtil.parseUri(req.uri()).equals("/login/getSession")||
						!UriUtil.parseUri(req.uri()).equals("/notice/getNotice"))){
			resultStatus.setSuccess(false);
			resultStatus.setResponseStatus(MyHttpResponseStatus.SERVERSHUTDOWN);
		}
		
		resultStatus.setSuccess(true);
		return resultStatus;
	}

}

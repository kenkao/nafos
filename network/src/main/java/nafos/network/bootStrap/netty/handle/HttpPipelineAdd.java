package nafos.network.bootStrap.netty.handle;

import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import nafos.network.bootStrap.netty.handle.currency.HttpLimitingHandle;
import nafos.network.bootStrap.netty.handle.http.HttpServerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author 黄新宇
 * @Date 2018/7/8 下午8:35
 * @Description pipeline添加处理handle
 **/
@Component
public class HttpPipelineAdd implements PipelineAdd {
    @Autowired
    HttpServerHandler httpServerHandler;
    @Autowired
    HttpLimitingHandle httpLimitingHandle;


    @Override
    public void handAdd(ChannelPipeline pipeline) {
        pipeline.addLast("http-limiting", httpLimitingHandle);

        pipeline.addLast("http-decoder", new HttpRequestDecoder());

        pipeline.addLast("http-aggregator", new HttpObjectAggregator(65535));

        pipeline.addLast("http-encoder", new HttpResponseEncoder());

        //这个handler主要用于处理大数据流,比如一个1G大小的文件如果你直接传输肯定会撑暴jvm内存的,增加ChunkedWriteHandler 这个handler我们就不用考虑这个问题了,内部原理看源代码.
        pipeline.addLast("http-chunked", new ChunkedWriteHandler());

        // 真正处理用户级业务逻辑的地方
        pipeline.addLast("http-user-defined",httpServerHandler);
    }
}
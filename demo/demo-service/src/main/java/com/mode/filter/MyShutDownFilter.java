package com.mode.filter;

import nafos.core.shutdown.ShutDownFilter;
import org.springframework.stereotype.Component;

/**
 * @Author 黄新宇
 * @Date 2018/8/23 下午12:43
 * @Description TODO
 **/
@Component
public class MyShutDownFilter implements ShutDownFilter {

    @Override
    public void run() {
        System.out.println("human-业务服务器正在关闭========");
    }
}
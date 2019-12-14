package com.winter.highlight_spring4.ch2.scope;
import org.springframework.stereotype.Service;

@Service //1默认为Singleton 相当于@Scope("singleton") 全容器共享一个示例
public class DemoSingletonService {
}

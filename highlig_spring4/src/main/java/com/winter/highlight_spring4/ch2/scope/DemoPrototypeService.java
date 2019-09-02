package com.winter.highlight_spring4.ch2.scope;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype") //1 声明Scope为Prototype
public class DemoPrototypeService {
}

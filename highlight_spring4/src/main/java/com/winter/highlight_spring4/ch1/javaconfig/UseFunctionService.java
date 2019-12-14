package com.winter.highlight_spring4.ch1.javaconfig;
//1 此处没有使用@Service声明Bean
import com.winter.highlight_spring4.ch1.javaconfig.FunctionService;
public class UseFunctionService {
    //2 没用使用@Autowired 注解注入Bean
    FunctionService functionService;

    public void setFunctionService(FunctionService functionService){
        this.functionService=functionService;
    }

    public String SayHello(String word){
        return functionService.SayHello(word);
    }
}



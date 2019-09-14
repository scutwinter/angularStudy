package com.winter.highlight_springmvc4.messageconverter;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;
import com.winter.highlight_springmvc4.domain.DemoObj;



//1 继承AbstractHttpMessageConverter接口来实现自定义的HttpMessageConverter
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {

    //2 新建一个自定义的媒体类型application/x-winter
    public MyMessageConverter(){
        super(new MediaType("application","x-winter", Charset.forName("UTF-8")));
    }

    /**
     * 3 重写readIntenal方法，处理请求的数据。代码表明我们处理由"-"隔开的数据，并转成DemoObj的对象
     * @param clazz
     * @param inputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> clazz,HttpInputMessage inputMessage) throws IOException,HttpMessageNotReadableException{
        String temp= StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));
        String[] tempArr = temp.split("-");
        return new DemoObj(new Long(tempArr[0]),tempArr[1]);
    }

    /**
     * 4 表明本HttpMessageConverter只处理DemoObj这个类
     * @param clazz
     * @return
     */
    @Override
    protected boolean supports(Class<?> clazz){
        return DemoObj.class.isAssignableFrom(clazz);
    }

    /**
     * 5 重写WriteInternal，处理如何输出数据到response.此例中，我们在原样输出前面加上"hello:"
     * @param obj
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(DemoObj obj,HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException{
        String out = "hello:"+obj.getId()+"|"+obj.getName();
        outputMessage.getBody().write(out.getBytes());
    }
}

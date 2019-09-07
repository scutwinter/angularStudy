package com.winter.highlight_spring4.ch3.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
public class LinuxCondition implements Condition {
    public boolean matches(ConditionContext context,AnnotatedTypeMetadata meatadata){
        return context.getEnvironment().getProperty("os.name").contains("Linux");
    }
}

package com.aps.spring.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionalBean implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        boolean conditionResolve = Boolean.parseBoolean(context.getEnvironment().getProperty("conditional.property"));
        return conditionResolve;
    }
}

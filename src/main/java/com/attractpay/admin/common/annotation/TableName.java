package com.attractpay.admin.common.annotation;

import java.lang.annotation.*;

@Target(value={ElementType.TYPE})
@Retention(value= RetentionPolicy.RUNTIME)
@Documented
public @interface TableName {
	String value() default "";
}

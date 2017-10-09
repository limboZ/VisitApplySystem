package cn.com.scal.components.annotations;

import cn.com.scal.components.enums.ClassifyEnum;

import java.lang.annotation.*;

/**
 * Created by vslimit on 15/8/28.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    ClassifyEnum value() default ClassifyEnum.SYSTEM;
    String name() default "";

}

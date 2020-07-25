package com.autohome.frostmourne.core.log4j2;

/**
 * Created by kcq on 2018/1/3.
 */
public interface FieldFactory {
    LayoutField fetchField(String name);
}

package com.autohome.frostmourne.monitor.config;

import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import com.fasterxml.jackson.databind.util.StdDateFormat;

/**
 * @author WangXiaoMing
 * @version 1.0
 * @date 2020-08-01
 */
@ControllerAdvice
public class GlobalControllerAdiviceController {

    @InitBinder
    public void dataBind(WebDataBinder binder) {
        StdDateFormat dateFormat = new StdDateFormat();
        // 严格解析日期
        dateFormat.setLenient(false);
        /// 给指定类型注册类型转换器操作
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}

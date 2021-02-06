package com.ybzn.config.converter;

import com.rabbitmq.client.BlockedCallback;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 统一日期返回类
 * @author Hugo
 * @time 2021/2/2
 */
@Component
public class DateConverter implements Converter <String, LocalDate> {


    @Override
    public LocalDate convert (String source) {
        return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}

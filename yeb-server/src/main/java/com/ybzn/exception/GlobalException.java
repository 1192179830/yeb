package com.ybzn.exception;

import com.ybzn.utils.ResultBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局数据库异常类处理
 * @author Hugo
 * @time 2021/1/24
 */
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(SQLException.class)
    public ResultBean MysqlException(SQLException e){
        if(e instanceof SQLIntegrityConstraintViolationException) {
            return ResultBean.error("该数据有关联数据，操作失败");
        }
        return ResultBean.error("数据库异常，操作失败");
    }

}

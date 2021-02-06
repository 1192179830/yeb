package com.ybzn.yeb.service.impl;

import com.ybzn.yeb.pojo.MailLog;
import com.ybzn.yeb.mapper.MailLogMapper;
import com.ybzn.yeb.service.IMailLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2020-07-17
 */
@Service
public class MailLogServiceImpl extends ServiceImpl <MailLogMapper, MailLog> implements IMailLogService {

}

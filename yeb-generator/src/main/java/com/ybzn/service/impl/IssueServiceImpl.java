package com.ybzn.service.impl;

import com.ybzn.pojo.Issue;
import com.ybzn.mapper.IssueMapper;
import com.ybzn.service.IIssueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hugo
 * @since 2021-01-21
 */
@Service
public class IssueServiceImpl extends ServiceImpl<IssueMapper, Issue> implements IIssueService {

}

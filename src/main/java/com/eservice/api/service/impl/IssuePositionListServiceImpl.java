package com.eservice.api.service.impl;

import com.eservice.api.dao.IssuePositionListMapper;
import com.eservice.api.model.issue_position_list.IssuePositionList;
import com.eservice.api.service.IssuePositionListService;
import com.eservice.api.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/07/10.
*/
@Service
@Transactional
public class IssuePositionListServiceImpl extends AbstractService<IssuePositionList> implements IssuePositionListService {
    @Resource
    private IssuePositionListMapper issuePositionListMapper;

}

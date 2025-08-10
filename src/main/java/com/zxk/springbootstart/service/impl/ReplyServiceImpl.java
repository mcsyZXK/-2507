package com.zxk.springbootstart.service.impl;

import com.zxk.springbootstart.mapper.ReplyMapper;
import com.zxk.springbootstart.pojo.Reply;
import com.zxk.springbootstart.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyMapper replyMapper;
    @Override
    public Reply[] getAllReply() {
        return replyMapper.getAllReply();
    }

    @Override
    public Integer insertReply(Reply reply) {
        return replyMapper.insertReply(reply);
    }
}

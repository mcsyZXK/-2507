package com.zxk.springbootstart.service;

import com.zxk.springbootstart.pojo.Reply;


public interface ReplyService {

    Reply[] getAllReply();

    Integer insertReply(Reply reply);
}

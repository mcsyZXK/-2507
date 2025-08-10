package com.zxk.springbootstart.controller;


import com.zxk.springbootstart.mapper.ReplyMapper;
import com.zxk.springbootstart.pojo.Reply;
import com.zxk.springbootstart.pojo.Result;
import com.zxk.springbootstart.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;
    @ResponseBody
    @RequestMapping("/getall")
    public Result ShowAllReply(){return Result.success(replyService.getAllReply());}
    @RequestMapping("/insert")
    @ResponseBody
    public Result InsertReply(@RequestBody Reply reply){
        Integer integer =replyService.insertReply(reply);
        if(integer==0){
            return Result.error("错误");
        }
        else {
            return Result.success("成功:"+integer);
        }
    }
}

package com.mytest.springsecuritydemo2.model.resp;

import com.mytest.springsecuritydemo2.model.entity.BizUser;
import lombok.Data;

import java.util.List;

@Data
public class QueryUserResp {
    private List<BizUser> userList;
}

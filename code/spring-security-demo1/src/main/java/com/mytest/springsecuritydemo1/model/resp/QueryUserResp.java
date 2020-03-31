package com.mytest.springsecuritydemo1.model.resp;

import com.mytest.springsecuritydemo1.model.entity.BizUser;
import lombok.Data;

import java.util.List;

@Data
public class QueryUserResp {
    private List<BizUser> userList;
}

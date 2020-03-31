package com.mytest.springsecuritydemo3.model.resp;

import com.mytest.springsecuritydemo3.model.entity.BizUser;
import lombok.Data;

import java.util.List;

@Data
public class QueryUserResp {
    private List<BizUser> userList;
}

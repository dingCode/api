package com.knowlegene.apirequest.hongkaitest.hongkaitest;

import com.knowlegene.apirequest.hongkaitest.hongkaitest.HongkaitestApiResponse;
import com.knowlegene.apirequest.hongkaitest.hongkaitest.HongkaitestAppiRequest;
import com.knowlegene.apirequest.hongkaitest.hongkaitest.model.HongkaitestApi;
import com.knowlegene.apiservice.entity.Page;
import com.knowlegene.apiservice.service.hongkaitest.hongkaitest.HongkaitestManager;
import com.knowlegene.apiservice.util.PageData;
import com.knowlegene.sample.AbstractUserService;
import com.knowlegene.sample.request.LogonRequest;
import com.knowlegene.sample.response.LogonResponse;
import com.knowlegene.sample.response.UserListResponse;
import com.rop.Constants;
import com.rop.RopRequest;
import com.rop.annotation.*;
import com.rop.session.SimpleSession;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明： hongkaitest
 * 创建人：hongkai
 * 创建时间：2016-12-21
 * 邮箱地址：18611949252@163.com
 */

//标注Rop的注解，使HongkaitestApiService成为一个Rop的服务
@ServiceMethodBean(version = "1.0")
public class HongkaitestApiService extends AbstractUserService {

    @Resource(name = "hongkaitestService")
    private HongkaitestManager hongkaitestService;

    //使该服务方法成为一个webService的方法
    @ServiceMethod(method = "hongkaitest.getSession", version = "1.0", needInSession = NeedInSessionType.NO)
    public Object getSession(LogonRequest request) {
        //创建一个会话
        SimpleSession session = new SimpleSession();
        session.setAttribute("userName", request.getUserName());
        request.getRopRequestContext().addSession("mockSessionId1", session);
        //返回响应
        LogonResponse logonResponse = new LogonResponse();
        logonResponse.setSessionId("mockSessionId1");
        return logonResponse;
    }

    @ServiceMethod(method = "hongkaitest.list", version = "1.0", ignoreSign = IgnoreSignType.YES, needInSession = NeedInSessionType.NO, httpAction = HttpAction.GET)
    public Object hongkaitestList(HongkaitestAppiRequest ropRequest) throws Throwable {
        String userId = ropRequest.getRopRequestContext().getParamValue("travelPage");
        String travl = ropRequest.getTravelPage();
        HongkaitestApi hongkaitestApi = new HongkaitestApi();
        hongkaitestApi.setHONGKAITEST_ID("100021");
        hongkaitestApi.setTTT("222222");
        hongkaitestApi.setTTT3("sdfsdfsdf");
        List<HongkaitestApi> hongkaitestList = new ArrayList<>();
        hongkaitestList.add(0,hongkaitestApi);
        HongkaitestApiResponse hongkaitestApiResponse = new HongkaitestApiResponse();
        hongkaitestApiResponse.setFooList(hongkaitestList);
        return hongkaitestList;
    }
}

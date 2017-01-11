package com.knowlegene.apirequest.groupRegionInfo;

import com.knowlegene.apiservice.entity.Page;
import com.knowlegene.apiservice.service.groupRegionInfo.group_region_info.GROUP_REGION_INFOManager;
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
import java.util.List;

/**
* 说明： 系集中地区信息
* 创建人：hongkai
* 创建时间：2016-12-21
* 邮箱地址：18611949252@163.com
* @version
*/

//标注Rop的注解，使GROUP_REGION_INFOApiService成为一个Rop的服务
@ServiceMethodBean(version = "1.0")
public class GROUP_REGION_INFOApiService extends AbstractUserService {

  @Resource(name="group_region_infoService")
  private GROUP_REGION_INFOManager group_region_infoService;

  //使该服务方法成为一个webService的方法
  @ServiceMethod(method = "group_region_info.getSession",version ="1.0",needInSession = NeedInSessionType.NO)
  public Object getSession(LogonRequest request) {
    //创建一个会话
    SimpleSession session = new SimpleSession();
    session.setAttribute("userName", request.getUserName());
    request.getRopRequestContext().addSession("mockSessionId1", session);
    Page p = new Page();
    PageData pd = new PageData();
    try {
        List<PageData> group_region_infoList = group_region_infoService.listAll(pd);
    } catch (Exception e) {
        e.printStackTrace();
    }
    //返回响应
    LogonResponse logonResponse = new LogonResponse();
    logonResponse.setSessionId("mockSessionId1");
    return logonResponse;
  }

  @ServiceMethod(method = "group_region_info.list",version = "1.0",ignoreSign = IgnoreSignType.YES,needInSession = NeedInSessionType.NO,httpAction = HttpAction.GET)
  public Object group_region_infoList(RopRequest ropRequest) throws Throwable {
    PageData pd = new PageData();
    Page page = new Page();page.setPd(pd);
    List<PageData> group_region_infoList = null;
        try {
            group_region_infoList = group_region_infoService.list(page);	//列出HkCore列表
        } catch (Exception e) {
            e.printStackTrace();
        }
        return group_region_infoList;
  }
}

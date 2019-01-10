package com.qianfeng.application.controller;

import com.google.gson.Gson;
import com.qianfeng.application.model.Org;
import com.qianfeng.application.service.IOrgService;
import com.qianfeng.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tree")
public class TreeController extends BaseController {

    @Autowired
    private IOrgService orgService;

    /**
     * 查询组织树的数据
     *
     * @param request
     * @param response
     */
    @RequestMapping("/orgSubList")
    public void queryOrgTree(HttpServletRequest request, HttpServletResponse response) {
        //获取携带过来的id，如果没有则赋默认值
        String orgParentId = request.getParameter("id");
        if (orgParentId == null || "".equals(orgParentId)) {
            orgParentId = "-1";
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orgParentId", orgParentId);
        //通过父组织id查询该组织下的所有组织信息
        List<Org> orgList = orgService.queryOrgListByOrgParentId(paramMap);

        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < orgList.size(); i++) {
            //把组织的id，name装到map中返回
            Map<String, Object> resultMap = new HashMap<String, Object>();
            Org org = orgList.get(i);
            resultMap.put("id", org.getOrgId());
            resultMap.put("name", org.getOrgName());
            resultMap.put("isParent", true);
            list.add(resultMap);
        }
        Gson gson = new Gson();
        String json = gson.toJson(list);

        this.flushResponse(response, json);
    }
}

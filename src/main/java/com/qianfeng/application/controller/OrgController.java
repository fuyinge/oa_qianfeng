package com.qianfeng.application.controller;

import com.google.gson.Gson;
import com.qianfeng.application.model.Org;
import com.qianfeng.application.model.Orgs;
import com.qianfeng.application.service.IOrgService;
import com.qianfeng.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/org")
public class OrgController extends BaseController {
    @Autowired
    private IOrgService orgService;


    /**
     * 跳转到组织管理页面
     *
     * @return
     */
    @RequestMapping("/orgMana")
    public String toOrgMana() {
        return "org/orgMana";
    }


    @RequestMapping("queryOrgListByOrgParentId")
    public void queryOrgListByOrgParentId(HttpServletRequest request, HttpServletResponse response){

         Map<String,Object> paramMap=this.getParam(request);

        List<Org> orgList=orgService.queryOrgListByOrgParentId(paramMap);

        Gson gson=new Gson();
        String json=gson.toJson(orgList);

        this.flushResponse(response,json);
    }

    /**
     * 查询组织分页数据
     */
    @RequestMapping("/orgPage")
    public ModelAndView orgPage(HttpServletRequest request) {
        //paramMap中装着传递过来的startIndex和pageSize
        Map<String, Object> paramMap = this.getParam(request);
        //map  orgList  total  orgList.ftl
        //查询回来的map中放着两个查询到的数据  orgList和total
        Map<String, Object> resultMap = orgService.queryOrgPageMap(paramMap);
        //跳转视图并携带数据到视图
        ModelAndView modelAndView = new ModelAndView("org/orgList");
        modelAndView.addObject("orgList", resultMap.get("orgList"));
        modelAndView.addObject("total", resultMap.get("total"));
        return modelAndView;
    }

    /**
     * 页码
     */
    @RequestMapping("/getPageNumber")
    public ModelAndView getPageNumber(HttpServletRequest request) {
        Map<String, Object> paramMap = this.getParam(request);
        int total = Integer.parseInt(paramMap.get("total").toString());
        int startIndex = Integer.parseInt(paramMap.get("startIndex").toString());
        int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        ModelAndView result = new ModelAndView("org/orgPageNumber");
        return this.getPageNumberInfo(total, startIndex, pageSize, result);
    }

    /**
     * 新增组织
     */
    @RequestMapping("/addOrg")
    public void addOrg(Org org, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            orgService.addOrg(org);
            map.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("isSuccess", false);
        }
        String json = new Gson().toJson(map);
        this.flushResponse(response, json);
    }

    /**
     * 根据组织ID查询组织对象信息
     */
    @RequestMapping("/getOrgByOrgId")
    public void getOrgByOrgId(int orgId, HttpServletResponse response) {
        Orgs org = orgService.getOrgByOrgId(orgId);
        String json = new Gson().toJson(org);
        this.flushResponse(response, json);
    }

    /**
     * 修改
     */
    @RequestMapping("/updateOrg")
    public void updateOrg(Org org,HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            orgService.updateOrg(org);
            map.put("isSuccess",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("isSuccess",false);
        }
        String json = new Gson().toJson(map);
        this.flushResponse(response,json);
    }

    /**
     * 删除
     */
    @RequestMapping("/deleteOrg")
    public void deleteOrg(long orgId,HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            orgService.deleteOrg(orgId);
            map.put("isSuccess",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("isSuccess",false);
        }
        String json = new Gson().toJson(map);
        this.flushResponse(response,json);
    }
}

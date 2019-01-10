package com.qianfeng.application.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qianfeng.application.model.Area;
import com.qianfeng.application.service.AreaService;


@Controller
public class AreaController extends BaseController {

	@Resource(name = "areaServiceImpl")
	private AreaService areaService;
 
	@RequestMapping(method = RequestMethod.GET, value = "/getAreaList")
	@ResponseBody
	public void getAreaList(HttpServletRequest request,
			HttpServletResponse response) {
		String areaParentId=request.getParameter("areaParentId");
		List<Area>	list = areaService.selectByAreaParentId(areaParentId);
		Gson gson = new Gson();
		String resultJson = gson.toJson(list);
		this.flushResponse(response, resultJson);
	}

	
}
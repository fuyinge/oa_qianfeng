package com.qianfeng.application.service;

import java.util.List;

import com.qianfeng.application.model.Area;


public interface AreaService {
	
	  List<Area> selectByAreaParentId(String areaParentId);
	  
}
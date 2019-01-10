package com.qianfeng.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianfeng.application.dao.AreaMapper;
import com.qianfeng.application.model.Area;
import com.qianfeng.application.service.AreaService;


@Service(value = "areaServiceImpl")
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaMapper areaMapper;

	@Override
	public List<Area> selectByAreaParentId(String areaParentId) {
		return areaMapper.selectByAreaParentId(areaParentId);
	}

	 
}
package com.qianfeng.application.service.impl;

import com.qianfeng.application.dao.OrgMapper;
import com.qianfeng.application.model.Org;
import com.qianfeng.application.model.Orgs;
import com.qianfeng.application.service.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class OrgServiceImpl implements IOrgService {
    @Autowired
    private OrgMapper orgMapper;

    @Override
    public List<Org> queryOrgListByOrgParentId(Map<String, Object> map) {
        return orgMapper.queryOrgListByOrgParentId(map);
    }


    /**
     * 查询组织分页数据
     * @param paramMap
     * @return
     */
    @Override
    public Map<String, Object> queryOrgPageMap(Map<String, Object> paramMap) {
        //把传递的参数转成int类型，再放入集体中
        Integer startIndex = Integer.parseInt(paramMap.get("startIndex").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        paramMap.put("startIndex",startIndex);
        paramMap.put("pageSize",pageSize);
        //查询到分页的集合数据
        List<Org> orgList = orgMapper.queryOrgList(paramMap);
        //查询组织的总条数
        int total = orgMapper.queryOrgTotal(paramMap);
        //把数据装到map中返回到controller
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("orgList",orgList);
        resultMap.put("total",total);
        return resultMap;
    }

    @Override
    public void addOrg(Org org) {
        orgMapper.insert(org);
    }

    @Override
    public Orgs getOrgByOrgId(int orgId) {
        return  orgMapper.getOrgByOrgId(orgId);
    }

    @Override
    public void updateOrg(Org org) {
        orgMapper.updateByPrimaryKey(org);
    }

    @Override
    public void deleteOrg(long orgId) {
        orgMapper.deleteByPrimaryKey(orgId);
    }
}

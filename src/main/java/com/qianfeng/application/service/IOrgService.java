package com.qianfeng.application.service;

import com.qianfeng.application.model.Org;
import com.qianfeng.application.model.Orgs;

import java.util.List;
import java.util.Map;

public interface IOrgService {
    public List<Org> queryOrgListByOrgParentId(Map<String,Object> map);

    Map<String,Object> queryOrgPageMap(Map<String, Object> paramMap);

    void addOrg(Org org);

    Orgs getOrgByOrgId(int orgId);

    void updateOrg(Org org);

    void deleteOrg(long orgId);

}

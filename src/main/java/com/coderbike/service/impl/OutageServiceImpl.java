package com.coderbike.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coderbike.model.constant.ApiConstant;
import com.coderbike.model.constant.ProvinceInitializer;
import com.coderbike.model.task.OutageInfoTask;
import com.coderbike.model.vo.AreaOrg;
import com.coderbike.model.vo.OutageInfo;
import com.coderbike.service.OutageService;
import com.coderbike.utils.http.HttpUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 描述
 * @author LBG - 2017/9/30 0030
 */
@Service
public class OutageServiceImpl implements OutageService {

    private static final ExecutorService EXECUTOR
            = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);

    /**
     * 根据省份查找停电信息
     * @param provinceCode 省份code
     */
    @Override
    public List<OutageInfo> queryOutageInfoByProvince(String provinceCode, String startDate, String endDate) {
        // 获取区
        List<Future<List<OutageInfo>>> outageInfoList = new ArrayList<>(500);
        Map<String, Object> map = new HashMap<>(1);
        map.put("orgNo", provinceCode);
        String areaResult = HttpUtils.okPostForm(ApiConstant.NEXT_ORG_LIST_API, map);
        if (StringUtils.isBlank(areaResult)) {
            return null;
        }
        JSONArray areaArray = JSONArray.parseArray(areaResult);
        if (areaArray == null || areaArray.size() == 0) {
            return null;
        }
        for (int i = 0; i < areaArray.size(); i++) {
            JSONObject jsonObject = areaArray.getJSONObject(i);
            AreaOrg areaOrg = JSON.parseObject(jsonObject.toJSONString(), AreaOrg.class);
            areaOrg.setProvinceCode(provinceCode);
            outageInfoList.add(EXECUTOR.submit(new OutageInfoTask(areaOrg, startDate, endDate)));
        }

        // 获取结果
        List<OutageInfo> totalList = new ArrayList<>(1000);
        for (Future<List<OutageInfo>> future : outageInfoList) {
            try {
                List<OutageInfo> list = future.get();
                if (CollectionUtils.isNotEmpty(list)) {
                    totalList.addAll(list);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return totalList;
    }

    /**
     * 获取所有省份停电信息
     */
    @Override
    public List<OutageInfo> queryAllOutageInfo(String startDate, String endDate) {
        List<AreaOrg> provinceList = ProvinceInitializer.getProvinceList();
        List<OutageInfo> totalList = new ArrayList<>(10000);
        for (AreaOrg areaOrg : provinceList) {
            List<OutageInfo> infoList = queryOutageInfoByProvince(areaOrg.getCode(), startDate, endDate);
            if (CollectionUtils.isEmpty(infoList)) {
                continue;
            }
            totalList.addAll(infoList);
        }
        return totalList;
    }
}

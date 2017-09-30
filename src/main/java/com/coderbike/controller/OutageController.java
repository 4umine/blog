package com.coderbike.controller;

import com.alibaba.fastjson.JSON;
import com.coderbike.model.constant.ProvinceInitializer;
import com.coderbike.model.vo.AreaOrg;
import com.coderbike.model.vo.OutageInfo;
import com.coderbike.model.vo.OutageInfoVO;
import com.coderbike.model.vo.OutageRankVo;
import com.coderbike.service.OutageService;
import com.coderbike.utils.http.MapUtil;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 停电
 * @author LBG - 2017/9/29 0029
 */
@Controller
@RequestMapping("/outage")
public class OutageController {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private OutageService outageService;

    @RequestMapping("")
    public String index(HttpServletRequest req) {
        req.setAttribute("provinceList", ProvinceInitializer.getProvinceList());
        req.setAttribute("basePath", req.getServletContext().getContextPath());
        return "index";
    }

    @RequestMapping("/query.json")
    @ResponseBody
    public String list(String provinceCode) throws IOException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        String outageDate = DATE_FORMAT.format(calendar.getTime());

        List<OutageInfo> outageInfoList
                = outageService.queryOutageInfoByProvince(provinceCode, outageDate, outageDate);
        // 获取结果
        List<OutageInfoVO> voList = new ArrayList<>(outageInfoList.size());
        for (OutageInfo outageInfo : outageInfoList) {
            OutageInfoVO infoVO = new OutageInfoVO();
            infoVO.setLineName(outageInfo.getLineName());
            infoVO.setScope(outageInfo.getScope());
            infoVO.setStartTime(outageInfo.getStartTime());
            infoVO.setStopTime(outageInfo.getStopTime());
            infoVO.setSubsName(outageInfo.getSubsName());
            infoVO.setTypeCode(outageInfo.getTypeCode());
            voList.add(infoVO);
        }
        return JSON.toJSONString(voList);
    }

    @RequestMapping("/rank.json")
    @ResponseBody
    public String rank() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        String outageDate = DATE_FORMAT.format(calendar.getTime());

        Map<String, Integer> outageCountMap = new HashMap<>(ProvinceInitializer.getProvinceList().size());
        for (AreaOrg areaOrg : ProvinceInitializer.getProvinceList()) {
            outageCountMap.put(areaOrg.getCode(), 0);
        }
        List<OutageInfo> outageInfoList
                = outageService.queryAllOutageInfo(outageDate, outageDate);
        for (OutageInfo outageInfo : outageInfoList) {
            String key = outageInfo.getProvinceCode();
            outageCountMap.put(key, outageCountMap.get(key) + 1);
        }
        Map<String, Integer> sortedMap = MapUtil.sortMapByValue(outageCountMap, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        if (MapUtils.isEmpty(sortedMap)) {
            return null;
        }
        List<OutageRankVo> rankList = new ArrayList<>(sortedMap.size());
        Map<String, String> provinceMap = ProvinceInitializer.getProvinceMap();
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            String provinceFullName = provinceMap.get(entry.getKey());
            if (provinceFullName.contains("北京")
                    || provinceFullName.contains("上海")
                    || provinceFullName.contains("天津")
                    || provinceFullName.contains("重庆")) {
                continue;
            }
            OutageRankVo rankVo = new OutageRankVo();
            rankVo.setProvinceName(provinceFullName.replace("有限", "")
                                                   .replace("电力", "")
                                                   .replace("公司", ""));
            rankVo.setOutageNum(entry.getValue());
            rankList.add(rankVo);
        }
        return JSON.toJSONString(rankList);
    }
}

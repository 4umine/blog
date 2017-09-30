package com.coderbike.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coderbike.model.constant.ApiConstant;
import com.coderbike.model.constant.ProvinceInitializer;
import com.coderbike.model.task.OutageInfoTask;
import com.coderbike.model.vo.AreaOrg;
import com.coderbike.model.vo.OutageInfo;
import com.coderbike.model.vo.OutageInfoVO;
import com.coderbike.utils.http.HttpUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 停电
 * @author LBG - 2017/9/29 0029
 */
@Controller
@RequestMapping("/outage")
public class OutageController {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final ExecutorService EXECUTOR
            = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);

    @RequestMapping("")
    public String index(HttpServletRequest req) {
        req.setAttribute("provinceList", ProvinceInitializer.getProvinceList());
        return "index";
    }

    @RequestMapping("/query.do")
    @ResponseBody
    public String list(String provinceCode) throws IOException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        String outageDate = DATE_FORMAT.format(calendar.getTime());

        // 获取区
        List<Future<List<OutageInfo>>> outageInfoList = new ArrayList<>(500);
        Map<String, Object> map = new HashMap<>();
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
            outageInfoList.add(EXECUTOR.submit(new OutageInfoTask(areaOrg, outageDate, outageDate)));
        }

        // 获取结果
        List<OutageInfoVO> voList = new ArrayList<>(1000);
        for (Future<List<OutageInfo>> future : outageInfoList) {
            try {
                List<OutageInfo> list = future.get();
                for (OutageInfo outageInfo : list) {
                    OutageInfoVO infoVO = new OutageInfoVO();
                    infoVO.setLineName(outageInfo.getLineName());
                    infoVO.setScope(outageInfo.getScope());
                    infoVO.setStartTime(outageInfo.getStartTime());
                    infoVO.setStopTime(outageInfo.getStopTime());
                    infoVO.setSubsName(outageInfo.getSubsName());
                    infoVO.setTypeCode(outageInfo.getTypeCode());
                    voList.add(infoVO);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(voList);
        return JSON.toJSONString(voList);
    }
}

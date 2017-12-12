package com.coderbike.model.constant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coderbike.model.vo.AreaOrg;
import com.coderbike.utils.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取省份
 * @author LBG - 2017/9/29 0029
 */
public class ProvinceInitializer {

    private static final List<AreaOrg> ORG_LIST = new ArrayList<>(30);
    private static final Map<String, String> PROVINCE_CODE_NAME_MAP = new HashMap<>(30);
    private static final Logger LOGGER = LoggerFactory.getLogger(ProvinceInitializer.class);

    static {
        try {
            LOGGER.info("----->start get province info");
            String provinceResult = HttpUtils.okPost(ApiConstant.ORG_LIST_API, "");
            JSONArray provinceArray = JSONArray.parseArray(provinceResult);
            if (provinceArray != null && provinceArray.size() > 0) {
                for (int i = 0; i < provinceArray.size(); i++) {
                    JSONObject jsonObject = provinceArray.getJSONObject(i);
                    AreaOrg provinceOrg = JSON.parseObject(jsonObject.toJSONString(), AreaOrg.class);
                    ORG_LIST.add(provinceOrg);
                    PROVINCE_CODE_NAME_MAP.put(provinceOrg.getCode(), provinceOrg.getValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<AreaOrg> getProvinceList() {
        return ORG_LIST;
    }

    public static Map<String, String> getProvinceMap() {
        return PROVINCE_CODE_NAME_MAP;
    }
}

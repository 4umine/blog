package com.coderbike.service;

import com.coderbike.model.vo.OutageInfo;

import java.util.List;

/**
 * 描述
 *
 * @author LBG - 2017/9/30 0030
 */
public interface OutageService {

    /**
     * 根据省份查找停电信息
     * @param provinceCode 省份code
     */
    List<OutageInfo> queryOutageInfoByProvince(String provinceCode, String startDate, String endDate);

    /**
     * 获取所有省份停电信息
     */
    List<OutageInfo> queryAllOutageInfo(String startDate, String endDate);
}

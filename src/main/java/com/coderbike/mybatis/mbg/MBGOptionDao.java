package com.coderbike.mybatis.mbg;

import com.coderbike.model.entity.Option;
import com.coderbike.model.entity.OptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MBGOptionDao {
    long countByExample(OptionExample example);

    int deleteByExample(OptionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Option record);

    int insertSelective(Option record);

    List<Option> selectByExampleWithBLOBs(OptionExample example);

    List<Option> selectByExample(OptionExample example);

    Option selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Option record, @Param("example") OptionExample example);

    int updateByExampleWithBLOBs(@Param("record") Option record, @Param("example") OptionExample example);

    int updateByExample(@Param("record") Option record, @Param("example") OptionExample example);

    int updateByPrimaryKeySelective(Option record);

    int updateByPrimaryKeyWithBLOBs(Option record);

    int updateByPrimaryKey(Option record);
}
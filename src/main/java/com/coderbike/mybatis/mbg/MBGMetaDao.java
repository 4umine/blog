package com.coderbike.mybatis.mbg;

import com.coderbike.model.entity.Meta;
import com.coderbike.model.entity.MetaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MBGMetaDao {
    long countByExample(MetaExample example);

    int deleteByExample(MetaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Meta record);

    int insertSelective(Meta record);

    List<Meta> selectByExample(MetaExample example);

    Meta selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Meta record, @Param("example") MetaExample example);

    int updateByExample(@Param("record") Meta record, @Param("example") MetaExample example);

    int updateByPrimaryKeySelective(Meta record);

    int updateByPrimaryKey(Meta record);
}
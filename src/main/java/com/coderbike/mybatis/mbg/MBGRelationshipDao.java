package com.coderbike.mybatis.mbg;

import com.coderbike.model.entity.Relationship;
import com.coderbike.model.entity.RelationshipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MBGRelationshipDao {
    long countByExample(RelationshipExample example);

    int deleteByExample(RelationshipExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Relationship record);

    int insertSelective(Relationship record);

    List<Relationship> selectByExample(RelationshipExample example);

    Relationship selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Relationship record, @Param("example") RelationshipExample example);

    int updateByExample(@Param("record") Relationship record, @Param("example") RelationshipExample example);

    int updateByPrimaryKeySelective(Relationship record);

    int updateByPrimaryKey(Relationship record);
}
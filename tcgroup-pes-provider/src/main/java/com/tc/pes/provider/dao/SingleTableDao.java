package com.tc.pes.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tc.pes.api.model.PageCriteria;



/**
 * Created by yuyichuan on 8/28/15.
 */
public interface SingleTableDao<U, V extends PageCriteria> extends MyBatisRepository {
    int countByCriteria(V example);

    int deleteByCriteria(V example);

    int deleteById(Long dispenseId);

    int insert(U record);

    int insertSelective(U record);

    List<U> selectByCriteria(V example);

    U selectById(Long dispenseId);

    int updateByCriteriaSelective(@Param("record") U record, @Param("example") V example);

    int updateByCriteria(@Param("record") U record, @Param("example") V example);

    int updateByIdSelective(U record);

    int updateById(U record);
}

package com.tc.pes.dao;

import com.tc.pes.model.PageCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

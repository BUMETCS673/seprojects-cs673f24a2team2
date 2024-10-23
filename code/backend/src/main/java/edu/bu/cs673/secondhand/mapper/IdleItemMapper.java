package edu.bu.cs673.secondhand.mapper;

import edu.bu.cs673.secondhand.domain.IdleItem;
import edu.bu.cs673.secondhand.domain.IdleItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface IdleItemMapper {
    long countByExample(IdleItemExample example);

    int deleteByExample(IdleItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IdleItem row);

    int insertSelective(IdleItem row);

    List<IdleItem> selectByExampleWithRowbounds(IdleItemExample example, RowBounds rowBounds);

    List<IdleItem> selectByExample(IdleItemExample example);

    IdleItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") IdleItem row, @Param("example") IdleItemExample example);

    int updateByExample(@Param("row") IdleItem row, @Param("example") IdleItemExample example);

    int updateByPrimaryKeySelective(IdleItem row);

    int updateByPrimaryKey(IdleItem row);

    List<IdleItem> findIdleByList(List<Long> idList);

    List<IdleItem> getIdleItemByStatus(int status, int i, int nums);

    int countIdleItemByStatus(int status);
}
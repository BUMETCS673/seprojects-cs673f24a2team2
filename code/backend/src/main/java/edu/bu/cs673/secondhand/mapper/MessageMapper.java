package edu.bu.cs673.secondhand.mapper;

import edu.bu.cs673.secondhand.domain.Message;
import edu.bu.cs673.secondhand.domain.MessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface MessageMapper {
    long countByExample(MessageExample example);

    int deleteByExample(MessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Message row);

    int insertSelective(Message row);

    List<Message> selectByExampleWithRowbounds(MessageExample example, RowBounds rowBounds);

    List<Message> selectByExample(MessageExample example);

    Message selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Message row, @Param("example") MessageExample example);

    int updateByExample(@Param("row") Message row, @Param("example") MessageExample example);

    int updateByPrimaryKeySelective(Message row);

    int updateByPrimaryKey(Message row);

    List<Message> getMyMessage(Long userId);

    List<Message> getIdleMessage(Long idleId);
}
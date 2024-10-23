package edu.bu.cs673.secondhand.mapper;

import edu.bu.cs673.secondhand.domain.Admin;
import edu.bu.cs673.secondhand.domain.AdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface

AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Admin row);

    int insertSelective(Admin row);

    List<Admin> selectByExampleWithRowbounds(AdminExample example, RowBounds rowBounds);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Admin row, @Param("example") AdminExample example);

    int updateByExample(@Param("row") Admin row, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin row);

    int updateByPrimaryKey(Admin row);

   
    Admin login(@Param("accountNumber") String accountNumber, @Param("adminPassword") String adminPassword);


    List<Admin> getList(@Param("offset") int offset, @Param("limit") int limit);

    int getCount();
}

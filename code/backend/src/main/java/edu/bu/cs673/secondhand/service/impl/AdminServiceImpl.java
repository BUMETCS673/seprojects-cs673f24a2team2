package edu.bu.cs673.secondhand.service.impl;

import edu.bu.cs673.secondhand.domain.Admin;
import edu.bu.cs673.secondhand.mapper.AdminMapper;
import edu.bu.cs673.secondhand.service.AdminService;
import edu.bu.cs673.secondhand.vo.PageVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin login(String accountNumber, String adminPassword) {
        return adminMapper.login(accountNumber, adminPassword);
    }

    @Override
    public PageVo<Admin> getAdminList(int page, int nums) {
        List<Admin> list = adminMapper.getList((page - 1) * nums, nums);
        int count = adminMapper.getCount();
        return new PageVo<>(list, count);
    }

    @Override
    public boolean addAdmin(Admin adminModel) {
        return adminMapper.insert(adminModel) == 1;
    }
}
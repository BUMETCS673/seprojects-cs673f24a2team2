package edu.bu.cs673.secondhand.domain;

import java.util.List;

public class Admin {
    private Long id;

    private String accountNumber;

    private String adminPassword;

    private String adminName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber == null ? null : accountNumber.trim();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public Admin login(String accountNumber, String adminPassword) {
        return null;
    }

    public List<Admin> getList(int i, int nums) {
        return List.of();
    }

    public int getCount() {
        return 0;
    }

    public int insert(Admin adminModel) {
        return 0;
    }
}
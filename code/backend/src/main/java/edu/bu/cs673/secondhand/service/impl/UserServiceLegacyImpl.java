package edu.bu.cs673.secondhand.service.impl;

import edu.bu.cs673.secondhand.domain.User;
import edu.bu.cs673.secondhand.mapper.UserMapperLegacy;
import edu.bu.cs673.secondhand.service.UserServiceLegacy;
import edu.bu.cs673.secondhand.vo.PageVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 Email: ybinman@bu.edu
 DateTime: 10/15/24-15:45
 *****/
@Service
public class UserServiceLegacyImpl implements UserServiceLegacy {

    @Resource
    private UserMapperLegacy userDao;

    /**
     *
     * @param id
     * @return
     */
    public User getUser(Long id){
        return userDao.selectByPrimaryKey(id);
    }

    /**
     *  TBD!!!!
     * @param email
     * @param userPassword
     * @return
     */
    public User userLogin(String email, String userPassword){
        return userDao.userLogin(email, userPassword);
    }

    /**
     * TBD!!
     * @param user
     * @return
     */
    public boolean userSignIn(User user){
        return userDao.insert(user) == 1;
    }

    /**
     * TBD!
     * @param user
     * @return
     */
    public boolean updateUserInfo(User user){
        return userDao.updateByPrimaryKeySelective(user)==1;
    }

    /**
     *
     * @param newPassword
     * @param oldPassword
     * @param id
     * @return
     */
    public boolean updatePassword(String newPassword, String oldPassword,Long id){
        return userDao.updatePassword(newPassword,oldPassword,id)==1;
    }

    /**
     *
     * @param status
     * @param page
     * @param nums
     * @return
     */
    public PageVo<User> getUserByStatus(int status, int page , int nums){
        List<User> list;
        int count=0;
        if(status==0){
            count=userDao.countNormalUser();
            list=userDao.getNormalUser((page-1)*nums, nums);
        }else {
            count=userDao.countBanUser();
            list=userDao.getBanUser((page-1)*nums, nums);
        }
        return new PageVo<>(list,count);
    }


    /**
     *
     * @param nickname
     * @return
     */
    @Override
    public Long getUserId(String nickname) {
        return userDao.selectByUserName(nickname);
    }


    /**
     *
     * @param searchValue
     * @param mode
     * @return
     */
    @Override
    public PageVo<User> getUserByNumber(String searchValue, int mode) {
        List<User> list;
        list=userDao.getUserByNumber(searchValue, mode-1);
        return new PageVo<>(list, 1);
    }
}

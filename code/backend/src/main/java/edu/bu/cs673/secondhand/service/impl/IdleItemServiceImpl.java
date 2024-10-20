package edu.bu.cs673.secondhand.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.bu.cs673.secondhand.domain.IdleItem;
import edu.bu.cs673.secondhand.domain.IdleItemExample;
import edu.bu.cs673.secondhand.domain.User;
import edu.bu.cs673.secondhand.mapper.IdleItemMapper;
import edu.bu.cs673.secondhand.mapper.UserMapper;
import edu.bu.cs673.secondhand.model.ItemModel;
import edu.bu.cs673.secondhand.service.IdleItemService;
import edu.bu.cs673.secondhand.vo.PageVo;

import jakarta.annotation.Resource;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A class for service provide by idle item
 */
@Service
public class IdleItemServiceImpl implements IdleItemService {

    @Resource
    private IdleItem idleItemDao;

    @Resource
    private User userDao;

    @Autowired
    private IdleItemMapper itemMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addNewItem(IdleItem item) {
        return itemMapper.insert(item) == 1;
    }

    @Override
    public ItemModel getItem(Long id) {
        ItemModel item = new ItemModel(itemMapper.selectByPrimaryKey(id));
        item.setUser(userMapper.selectByPrimaryKey(item.getUserId()));
        return item;
    }

    @Override
    public List<IdleItem> getAllItemByUser(Long userId) {
        IdleItemExample example = new IdleItemExample();
        example.or().andUserIdEqualTo(userId);
        return itemMapper.selectByExample(example);
    }

    @Override
    public boolean updateItem(IdleItem item) {
        return itemMapper.updateByPrimaryKey(item) == 1;
    }

    @Override
    public boolean removeItem(Long id) {
        return itemMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public PageVo<ItemModel> searchItemLabel(int label, int page, int nums) {
        RowBounds rowBounds = new RowBounds((page - 1) * nums, nums);
        IdleItemExample itemExample = new IdleItemExample();
        itemExample.or().andIdleLabelEqualTo(label);
        List<IdleItem> list = itemMapper.selectByExampleWithRowbounds(itemExample, rowBounds);
        HashMap<Long, User> userCache = new HashMap<>();
        List<ItemModel> result = new ArrayList<>();
        for (IdleItem item : list) {
            ItemModel model = new ItemModel(item);
            if (userCache.containsKey(item.getUserId())) {
                model.setUser(userCache.get(item.getUserId()));
            } else {
                User user = userMapper.selectByPrimaryKey(item.getUserId());
                userCache.put(item.getUserId(), user);
                model.setUser(user);
            }
            result.add(model);
        }

        return new PageVo<ItemModel>(result,1);
    }

    @Override
    public PageVo<ItemModel> searchItem(String searchValue, int page, int nums) {
        RowBounds rowBounds = new RowBounds((page - 1) * nums, nums);
        IdleItemExample itemExample = new IdleItemExample();
        itemExample.or().andIdleNameLike('%' + searchValue + '%');
        itemExample.or().andIdleDetailsLike('%' + searchValue + '%');
        List<IdleItem> list = itemMapper.selectByExampleWithRowbounds(itemExample, rowBounds);
        HashMap<Long, User> userCache = new HashMap<>();
        List<ItemModel> result = new ArrayList<>();
        for (IdleItem item : list) {
            ItemModel model = new ItemModel(item);
            if (userCache.containsKey(item.getUserId())) {
                model.setUser(userCache.get(item.getUserId()));
            } else {
                User user = userMapper.selectByPrimaryKey(item.getUserId());
                userCache.put(item.getUserId(), user);
                model.setUser(user);
            }
            result.add(model);
        }

        return new PageVo<ItemModel>(result,1);
    }

    public PageVo<IdleItem> adminGetIdleList(int status, int page, int nums) {
        List<IdleItem> list=idleItemDao.getIdleItemByStatus(status, (page - 1) * nums, nums);
        if(list.size()>0){
            List<Long> idList=new ArrayList<>();
            for(IdleItem i:list){
                idList.add(i.getUserId());
            }
            List<User> userList=userDao.findUserByList(idList);
            Map<Long,User> map=new HashMap<>();
            for(User user:userList){
                map.put(user.getId(),user);
            }
            for(IdleItem i:list){
                i.setUser(map.get(i.getUserId()));
            }
        }
        int count=idleItemDao.countIdleItemByStatus(status);
        return new PageVo<>(list,count);
    }
}

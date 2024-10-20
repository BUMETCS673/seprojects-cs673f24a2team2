package edu.bu.cs673.secondhand.service;

import java.util.List;

import edu.bu.cs673.secondhand.domain.IdleItem;
import edu.bu.cs673.secondhand.model.ItemModel;
import edu.bu.cs673.secondhand.vo.PageVo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * An interface for service provide by idle item
 */
public interface IdleItemService {

    /**
     * Search Item by its label
     * 
     * @param label item label
     * @param page
     * @param nums
     * @return page of items with their owner info
     */
    PageVo<ItemModel> searchItemLabel(int label, int page, int nums);

    /**
     * Search Item by its name and detail
     * 
     * @param searchValue String of search term
     * @param page 
     * @param nums 
     * @return page of items with their owner info
     */
    PageVo<ItemModel> searchItem(String searchValue, int page, int nums);

    /**
     * Add new item
     * 
     * @param item an item
     * @return true if success
     */
    boolean addNewItem(IdleItem item);

    /**
     * Get an item by its id
     * 
     * @param id item id
     * @return an item
     */
    ItemModel getItem(Long id);

    /**
     * Get all items that belongs to a user
     * 
     * @param userId user id
     * @return a list of items
     */
    List<IdleItem> getAllItemByUser(Long userId);

    /**
     * Update an item
     * 
     * @param item an item with updated information
     * @return true if success
     */
    boolean updateItem(IdleItem item);

    /**
     * Remove an item by id
     * 
     * @param id item id
     * @return true if success
     */
    boolean removeItem(Long id);

    PageVo<IdleItem> adminGetIdleList(int status, int page, int nums) ;


}

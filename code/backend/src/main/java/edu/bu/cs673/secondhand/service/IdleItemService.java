package edu.bu.cs673.secondhand.service;

import java.util.List;

import edu.bu.cs673.secondhand.domain.IdleItem;

/**
 * An interface for service provide by idle item
 */
public interface IdleItemService {

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
    IdleItem getItem(Long id);

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
}
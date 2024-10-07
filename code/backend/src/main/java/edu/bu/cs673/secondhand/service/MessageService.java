package edu.bu.cs673.secondhand.service;

import edu.bu.cs673.secondhand.domain.Message;

import java.util.List;

public interface MessageService {

    /**
     * 发送留言
     * @param messageModel
     * @return
     */
    boolean addMessage(Message messageModel);

    /**
     * 删除留言
     * @param id
     * @return
     */
    boolean deleteMessage(Long id);

    /**
     * 获取某个留言
     * @param id
     * @return
     */
    Message getMessage(Long id);

//    /**
//     * 获取某个用户收到的所有留言
//     * @param userId
//     * @return
//     */
//    List<Message> getAllMyMessage(Long userId);
//
//    /**
//     * 获取某个闲置的所有留言
//     * @param idleId
//     * @return
//     */
//    List<Message> getAllIdleMessage(Long idleId);

}

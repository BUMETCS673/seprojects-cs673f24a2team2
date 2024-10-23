package edu.bu.cs673.secondhand.controller;

import edu.bu.cs673.secondhand.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.bu.cs673.secondhand.enums.ErrorMsg;
import edu.bu.cs673.secondhand.domain.Admin;
import edu.bu.cs673.secondhand.domain.IdleItem;
import edu.bu.cs673.secondhand.domain.User;
import edu.bu.cs673.secondhand.vo.ResultVo;

import jakarta.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private IdleItemService idleItemService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserServiceLegacy userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ResultVo login(@RequestParam("accountNumber") @NotNull @NotEmpty String accountNumber,
                          @RequestParam("adminPassword") @NotNull @NotEmpty String adminPassword,
                          HttpSession session) {
        Admin adminModel = adminService.login(accountNumber, adminPassword);
        if (adminModel == null) {
            return ResultVo.fail(ErrorMsg.EMAIL_LOGIN_ERROR);
        }
        session.setAttribute("admin", adminModel);
        return ResultVo.success(adminModel);
    }

    @RequestMapping(value = "loginOut", method = RequestMethod.GET)
    public ResultVo loginOut(HttpSession session) {
        session.removeAttribute("admin");
        return ResultVo.success();
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResultVo getAdminList(HttpSession session,
                                  @RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "nums", required = false) Integer nums) {
        if (session.getAttribute("admin") == null) {
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        int p = (page != null && page > 0) ? page : 1;
        int n = (nums != null && nums > 0) ? nums : 8;
        return ResultVo.success(adminService.getAdminList(p, n));
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResultVo addAdmin(HttpSession session,
                              @RequestBody Admin adminModel) {
        if (session.getAttribute("admin") == null) {
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        if (adminService.addAdmin(adminModel)) {
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.PARAM_ERROR);
    }

    @RequestMapping(value = "idleList", method = RequestMethod.GET)
    public ResultVo idleList(HttpSession session,
                              @RequestParam("status") @NotNull @NotEmpty Integer status,
                              @RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "nums", required = false) Integer nums) {
        if (session.getAttribute("admin") == null) {
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        int p = (page != null && page > 0) ? page : 1;
        int n = (nums != null && nums > 0) ? nums : 8;
        return ResultVo.success(idleItemService.adminGetIdleList(status, p, n));
    }

    @RequestMapping(value = "updateIdleStatus", method = RequestMethod.GET)
    public ResultVo updateIdleStatus(HttpSession session,
                                      @RequestParam("id") @NotNull @NotEmpty Long id,
                                      @RequestParam("status") @NotNull @NotEmpty Integer status) {
        if (session.getAttribute("admin") == null) {
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        IdleItem idleItemModel = new IdleItem();
        idleItemModel.setId(id);
        idleItemModel.setIdleStatus(status.byteValue());
        if (idleItemService.updateItem(idleItemModel)) {
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @RequestMapping(value = "orderList", method = RequestMethod.GET)
    public ResultVo orderList(HttpSession session,
                              @RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "nums", required = false) Integer nums) {
        if (session.getAttribute("admin") == null) {
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        int p = (page != null && page > 0) ? page : 1;
        int n = (nums != null && nums > 0) ? nums : 8;
        return ResultVo.success(orderService.getAllOrder(p, n));
    }

    @RequestMapping(value = "deleteOrder", method = RequestMethod.GET)
    public ResultVo deleteOrder(HttpSession session,
                                @RequestParam("id") @NotNull @NotEmpty Long id) {
        if (session.getAttribute("admin") == null) {
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        if (orderService.deleteOrder(id)) {
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @RequestMapping(value = "userList", method = RequestMethod.GET)
    public ResultVo userList(HttpSession session,
                             @RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "nums", required = false) Integer nums,
                             @RequestParam("status") @NotNull @NotEmpty Integer status) {
        if (session.getAttribute("admin") == null) {
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        int p = (page != null && page > 0) ? page : 1;
        int n = (nums != null && nums > 0) ? nums : 8;
        return ResultVo.success(userService.getUserByStatus(status, p, n));
    }

    @RequestMapping(value = "updateUserStatus", method = RequestMethod.GET)
    public ResultVo updateUserStatus(HttpSession session,
                                      @RequestParam("id") @NotNull @NotEmpty Long id,
                                      @RequestParam("status") @NotNull @NotEmpty Integer status) {
        if (session.getAttribute("admin") == null) {
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        User userModel = new User();
        userModel.setId(id);
        userModel.setUserStatus(status.byteValue());
        if (userService.updateUserInfo(userModel)) {
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @RequestMapping(value = "queryIdle", method = RequestMethod.GET)
    public ResultVo queryIdle(@RequestParam(value = "findValue", required = false) String findValue,
                              @RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "nums", required = false) Integer nums,
                              @RequestParam("status") @NotNull @NotEmpty Integer status) {
        if (findValue == null) {
            findValue = "";
        }
        int p = (page != null && page > 0) ? page : 1;
        int n = (nums != null && nums > 0) ? nums : 8;

        if (status == 1) {
            return ResultVo.success(idleItemService.searchItem(findValue, p, n));
        }
        return ResultVo.success(idleItemService.searchItemLabel(status, p, n));
    }

    @RequestMapping(value = "queryOrder", method = RequestMethod.GET)
    public ResultVo queryOrder(HttpSession session,
                               @RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "nums", required = false) Integer nums,
                               @RequestParam(value = "searchValue", required = false) String searchValue) {
        if (session.getAttribute("admin") == null) {
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }

        int p = (page != null && page > 0) ? page : 1;
        int n = (nums != null && nums > 0) ? nums : 8;

        if (searchValue == null || "".equals(searchValue)) {
            return ResultVo.success(orderService.getAllOrder(p, n));
        }
        return ResultVo.success(orderService.findOrderByNumber(searchValue, p, n));
    }

    @RequestMapping(value = "queryUser", method = RequestMethod.GET)
    public ResultVo queryUser(HttpSession session,
                              @RequestParam(value = "searchValue", required = false) String searchValue,
                              @RequestParam(value = "mode", required = false) Integer mode,
                              @RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "nums", required = false) Integer nums) {
        if (session.getAttribute("admin") == null) {
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        int p = (page != null && page > 0) ? page : 1;
        int n = (nums != null && nums > 0) ? nums : 8;

        if (mode == 1) {
            if (searchValue == null || "".equals(searchValue)) {
                return ResultVo.success(userService.getUserByStatus(0, p, n));
            } else {
                return ResultVo.success(userService.getUserByNumber(searchValue, mode));
            }
        } else if (mode == 2) {
            if (searchValue == null || "".equals(searchValue)) {
                return ResultVo.success(userService.getUserByStatus(1, p, n));
            } else {
                return ResultVo.success(userService.getUserByNumber(searchValue, mode));
            }
        } else {
            return ResultVo.success(adminService.getAdminList(p, n));
        }
    }
}

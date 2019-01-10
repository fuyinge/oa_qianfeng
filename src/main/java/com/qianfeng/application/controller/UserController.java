package com.qianfeng.application.controller;

import com.google.gson.Gson;
import com.qianfeng.application.model.User;
import com.qianfeng.application.model.Users;
import com.qianfeng.application.service.IUserService;
import com.qianfeng.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;
   @RequestMapping("userMana")
   public String toUserMana(){
        return "user/userMana";
    }
    @RequestMapping("/userPage")
    public ModelAndView userPage(HttpServletRequest request) {
        Map<String, Object> paramMap = this.getParam(request);
        Map<String, Object> resultMap = userService.queryUserPageMap(paramMap);
        ModelAndView modelAndView = new ModelAndView("user/userList");
        modelAndView.addObject("userList", resultMap.get("userList"));
        modelAndView.addObject("total", resultMap.get("total"));
        return modelAndView;
    }

    @RequestMapping("/userPageNumber")
    public ModelAndView userPageNumber(HttpServletRequest request) {
        Map<String, Object> paramMap = this.getParam(request);
        int startIndex = Integer.parseInt(paramMap.get("startIndex").toString());
        int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        int total = Integer.parseInt(paramMap.get("total").toString());
        ModelAndView modelAndView = new ModelAndView("user/userPageNumber");
        return this.getPageNumberInfo(total,startIndex,pageSize,modelAndView);
    }

    /**
     * 新增用户
     */
    @RequestMapping("/addUser")
    public void addUser(User user, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> result = new HashMap<>();
        try {
            String birthday_temp = request.getParameter("birthday_temp");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date bithday = sdf.parse(birthday_temp);
            user.setUserBirthday(bithday);
            userService.addUser(user);
            result.put("isSuccess",true);
        } catch (ParseException e) {
            e.printStackTrace();
            result.put("isSuccess",false);
        }
        String json = new Gson().toJson(result);
        this.flushResponse(response,json);
    }

    /**
     * 通过用户id查询用户对象
     */
    @RequestMapping("/getUserByUserId")
    public void getUserByUserId(int userId,HttpServletResponse response){
        Users user = userService.getUserByUserId(userId);
        String json = new Gson().toJson(user);
        this.flushResponse(response,json);
    }

    /**
     * 修改
     */

    @RequestMapping("/updateUser")
    public void updateUser(User user,HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> result = new HashMap<String, Object>();
        try {
            String birthday_temp = request.getParameter("birthday_temp");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date bithday = sdf.parse(birthday_temp);
            user.setUserBirthday(bithday);
            userService.updateUser(user);
            result.put("isSuccess",true);
        } catch (ParseException e) {
            e.printStackTrace();
            result.put("isSuccess",false);
        }
        String json = new Gson().toJson(result);
        this.flushResponse(response,json);
    }
}
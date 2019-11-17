package cn.gotojava.controller;

import cn.gotojava.entity.User;
import cn.gotojava.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 查询所有用户信息
     * @return
     */
    @RequestMapping(value = "/")
    public String findAllUser(Model model, HttpServletRequest request){
        // 获取消息内容
        String msg = (String) request.getAttribute("msg");
        // 获取编码
        String code = (String) request.getAttribute("code");
        List<User> all = userService.findAll();
        model.addAttribute("userlist",all);
        model.addAttribute("msg",msg);
        model.addAttribute("code",code);
        return "index";
    }

    @RequestMapping(value = "/api/findUserById/{userId}")
    public String getUserById(@NotNull @PathVariable String userId, Model model){
        // 获取用户编号
        int id = Integer.parseInt(userId);
        // 校验用户信息
        boolean exists = userService.exists(id);
        // 用户信息不存在 重定向到查询页
        if(!exists){
            return "redirect:/";
        }
        // 用户信息存在 获取用户信息，跳转到用户信息修改页
        User one = userService.getOne(id);
        model.addAttribute("userInfo",one);
        return "update";
    }

    /**
     * 根据用户id修改用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/api/update")
    public String updateUserById(@NotNull User user,Model model){
        try {
            // 获取用户请求编号,验证用户编号合法性
            Integer id = user.getId();
            boolean exists = userService.exists(id);
            if (exists) {
                User save = userService.save(user);
                if (save != null){
                    // 用户信息修改成功
                    model.addAttribute("msg","用户信息修改成功");
                    model.addAttribute("code","2000");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "forward:/";
    }

    @RequestMapping(value = "/api/delete/{userId}")
    public String deleteUserById(@NotNull @PathVariable String userId,Model model){
        int id = Integer.parseInt(userId);
        // 判断被删除的用户是否存在
        boolean exists = userService.exists(id);
        try {
            // 用户不存在
            if (!exists) {
                // 重定向到查询页
                return "redirect:/";
            }
            // 存在该用户，删除用户
            userService.deleteById(id);
            model.addAttribute("msg","删除成功");
            model.addAttribute("code","2000");
        } catch (Exception e) {
            logger.error("用户删除失败，编号：{}",userId);
        }
        return "forward:/";
    }

    /**
     * 新增用户信息
     * @return
     */
    @RequestMapping(value = "/api/save")
    public String saveUser(@NotNull User user,Model model){
        User save = userService.save(user);
        // 新增成功
        if (save != null){
            model.addAttribute("msg","用户新增成功");
            model.addAttribute("code","2000");
        }
        return "forward:/";
    }
}

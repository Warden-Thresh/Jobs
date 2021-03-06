package com.warden.controller;
import com.warden.exception.UserNotFound;
import com.warden.model.JobEntity;
import com.warden.model.UserEntity;
import com.warden.repository.JobSearch;
import com.warden.service.JobService;
import com.warden.service.LoginService;
import com.warden.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("admin")
public class MainController  {
    // 自动装配数据库接口
    @Autowired
    UserService userService;

    @Autowired
    JobService jobService;

    @Autowired
    LoginService loginService;

    @Autowired
    JobSearch jobSearch;

    @RequestMapping(value="/checkName",method={RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody String checkName(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String result;
        System.out.println(name);
        if(userService.findByNickname(name) != null){
            result = "";
        }
        else {
            result =("notfound");
        }
        System.out.println(result);
        return result;
    }

   @RequestMapping("/showInfos")
   public @ResponseBody Map<String,Object> showUserInfos(){      // @ResponseBody 表明输出的对象是JSON 格式。
       List<JobEntity> jobList = jobService.getJobs();
       Map<String,Object> modelMap = new HashMap<String,Object>(3);
       modelMap.put("joblist",jobList);

       return modelMap;
   }

    @RequestMapping(value = "/joblist", method=RequestMethod.GET)
    @ResponseBody
    public Map getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "3") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "jobId");
        if (page<1){
            page=1;
        }
        Pageable pageable = new PageRequest(page-1, size, sort);
        System.out.print(jobSearch.findAll(pageable));
        Page<JobEntity> list = jobSearch.findAll(pageable);
        Map map =new HashMap();
        map.put("totalPages", list.getTotalPages());//总页数
        map.put("totalElements", list.getTotalElements());//数据总数
        map.put("rows",list.getContent());//分页应该显示的数据
        return map;
    }

    @RequestMapping(value="/",method={RequestMethod.GET,RequestMethod.POST})
    public String index(ModelMap modelMap) {
        // 将所有记录传递给要返回的jsp页面，放在userList当中
        modelMap.addAttribute("jobList", jobService.getJobs());

        return "index.html";
    }

    //查看job
    @RequestMapping(value = "admin/job/show/{id}", method = RequestMethod.GET)
    public String showJob(@PathVariable("id") Integer jobId, ModelMap modelMap) {

        // 找到userId所表示的用户
        JobEntity jobEntity = jobService.getJob(jobId);
        System.out.print("job:"+jobEntity.getJobName());


        // 传递给请求页面
        modelMap.addAttribute("job", jobEntity);
        return "jobDetail.jsp";
    }


    @RequestMapping(value="/app/user/api",method={RequestMethod.GET,RequestMethod.POST})
    public String app() {
        System.out.print("dscfdv");
        return "/web.xml";
    }


    @RequestMapping(value="/login",method={RequestMethod.GET,RequestMethod.POST})
    public String login() {
        System.out.print("login");
        return "login.jsp";
    }

    @RequestMapping(value="/admin/login",method={RequestMethod.GET,RequestMethod.POST})
    public String adminlogin() {

        System.out.print("adminlogin");
        return "admin/login.jsp";
    }

    @RequestMapping(value="/admin/loginP",method={RequestMethod.GET,RequestMethod.POST})
    public String login(@ModelAttribute("user") UserEntity loginUser, ModelMap modelMap) {
        return loginService.login(loginUser,modelMap);
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsers(ModelMap modelMap) {
        // 查询user表中所有记录
        List<UserEntity> userList = userService.getUsers();

        // 将所有记录传递给要返回的jsp页面，放在userList当中
        modelMap.addAttribute("userList",userService.getUsers());

        // 返回pages目录下的admin/users.jsp页面
        return "admin/users.jsp";
    }

    @RequestMapping(value = "/admin/jobs", method = RequestMethod.GET)
    public String getJobs(ModelMap modelMap) {
        // 查询user表中所有记录
        List<JobEntity> jobList = jobService.getJobs();
// 将所有记录传递给要返回的jsp页面，放在userList当中
        modelMap.addAttribute("jobList",jobService.getJobs());
        // 返回pages目录下的admin/users.jsp页面
        return "admin/jobs.jsp";
    }

    // 查看用户详情
// @PathVariable可以收集url中的变量，需匹配的变量用{}括起来
// 例如：访问 localhost:8080/admin/users/show/1 ，将匹配 id = 1
    @RequestMapping(value = "/admin/users/show/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer userId, ModelMap modelMap) {

        // 找到userId所表示的用户
        //UserEntity userEntity = userRepository.findOne(userId);
        UserEntity userEntity = userService.getUser(userId);

        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "admin/userDetail.jsp";
    }

    // get请求，访问添加用户 页面
    @RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
    public String addUser() {
        // 转到 admin/addUser.jsp页面
        return "admin/addUser.jsp";
    }

    // get请求，访问添加用户 页面
    @RequestMapping(value = "/admin/jobs/add", method = RequestMethod.GET)
    public String addJob() {
        // 转到 admin/addUser.jsp页面
        return "admin/addJob.jsp";
    }


    // post请求，处理添加用户请求，并重定向到用户管理页面
    @RequestMapping(value = "/admin/users/addP", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("user") UserEntity userEntity) {
        // 注意此处，post请求传递过来的是一个UserEntity对象，里面包含了该用户的信息
        // 通过@ModelAttribute()注解可以获取传递过来的'user'，并创建这个对象

        // 数据库中添加一个用户，该步暂时不会刷新缓存
        //userRepository.save(userEntity);

        // 数据库中添加一个用户，并立即刷新缓存
        //userRepository.saveAndFlush(userEntity);
        userService.addUser(userEntity);

        // 重定向到用户管理页面，方法为 redirect:url
        return "redirect:/admin/users";
    }

    // post请求，处理添加用户请求，并重定向到用户管理页面
    @RequestMapping(value = "/admin/jobs/addP", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("job") JobEntity jobEntity) {
        // 注意此处，post请求传递过来的是一个UserEntity对象，里面包含了该用户的信息
        // 通过@ModelAttribute()注解可以获取传递过来的'user'，并创建这个对象

        // 数据库中添加一个用户，该步暂时不会刷新缓存
        //userRepository.save(userEntity);

        // 数据库中添加一个用户，并立即刷新缓存
        //userRepository.saveAndFlush(userEntity);
        jobService.addJob(jobEntity);
        System.out.printf(jobEntity.toString());

        // 重定向到用户管理页面，方法为 redirect:url
        return "redirect:/admin/jobs";
    }

    // 更新用户信息 页面
    @RequestMapping(value = "/admin/users/update/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") Integer userId, ModelMap modelMap) {

        // 找到userId所表示的用户
        UserEntity userEntity = userService.getUser(userId);

        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "admin/updateUser.jsp";
    }

    // 更新用户信息 操作
    @RequestMapping(value = "/admin/users/updateP", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("userP") UserEntity user) throws UserNotFound {
        System.out.print("update"+user.getUserId());

        // 更新用户信息
        /*userRepository.updateUser(user.getNickname(), user.getFirstName(),
                user.getLastName(), user.getPassword(), user.getUserId());
        userRepository.flush(); // 刷新缓冲区*/
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    // 删除用户
    @RequestMapping(value = "/admin/users/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer userId) throws UserNotFound {

        // 删除id为userId的用户
       userService.deleteUser(userId);
        // 立即刷新
        userService.flush();
        return "redirect:/admin/users";
    }
    // 删除招聘信息
    @RequestMapping(value = "/admin/jobs/delete/{id}", method = RequestMethod.GET)
    public String deleteJob(@PathVariable("id") Integer jobId) throws UserNotFound {
        // 删除id为userId的用户
        jobService.deleteJob(jobId);
        // 立即刷新
        userService.flush();
        return "redirect:/admin/jobs";
    }
}  
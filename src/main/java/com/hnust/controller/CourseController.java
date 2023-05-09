package com.hnust.controller;

import com.hnust.entity.Course;
import com.hnust.entity.Result;
import com.hnust.entity.User;
import com.hnust.service.CourseService;
import com.hnust.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


/**
 * @author 长夜
 * @date 2023/3/23 8:54
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private Result result;
    @Autowired
    private SchoolService schoolService;
    //测试代码
    @GetMapping ("/test")
    @ResponseBody
    public String test()
    {
        return "hello";
    }
    //返回主页面
    @GetMapping("/main_page")
    public String main_page(HttpSession session)
    {
        if(session.getAttribute("user")==null)
        {
            return "redirect:/user/login_page";
        }else{
            User user = (User) session.getAttribute("user");
            System.out.println(user);
            return "main";
        }
    }
    //新增课程页面
    @GetMapping("add_page")
    public String add_page(HttpSession session)
    {
        if(session.getAttribute("user")==null)
        {
            return "redirect:/user/login_page";
        }else{
            User user = (User) session.getAttribute("user");
            System.out.println(user);
            return "add";
        }
    }
    //修改课程页面
    @GetMapping("update_page")
    public String update_page(Integer id,HttpSession session)
    {
        session.removeAttribute("course");
        if(session.getAttribute("user")==null)
        {
            return "redirect:/user/login_page";
        }else{
            Course course=courseService.selectById(id);
            System.out.println(course);
            session.setAttribute("course", course);
            return "update";
        }
    }
    //获取修改课程的信息
    @GetMapping("/get_course")
    @ResponseBody
    public Course get_course(HttpSession session)
    {
        Course course = (Course) session.getAttribute("course");
        System.out.println(session.getAttribute("course")+"--------------");
//        session.removeAttribute("course");
//        System.out.println(session.getAttribute("course")+"--------------");
        return course;
    }
    //返回课程表数据
    @GetMapping("/main_solve")
    @ResponseBody
    public Result main_solve()
    {
        List<Course>courses=courseService.selectAll();
        List<Map<String, Object>> discourse = new ArrayList<>();
        for (Course course : courses) {
            Map<String, Object> courseMap = new HashMap<>();
            courseMap.put("id",course.getId());
            courseMap.put("image",course.getImage());
            courseMap.put("name",course.getName());
            courseMap.put("hours",course.getHours());
            courseMap.put("schools",schoolService.selectSchoolNameById(course.getSchools()).getSchoolName());
            discourse.add(courseMap);
        }
        System.out.println(discourse);
        result.setMsg("success");
        result.setCode(200);
        result.setData(discourse);
        return result;
    }
    //删除课程处理
    @PostMapping("/delete_solve")
    @ResponseBody
    public String delete_solve(@RequestParam("id") int id)
    {
        System.out.println(id);
        courseService.deleteById(id);
        return "success";
    }
    //添加课程处理
    @PostMapping("/add_solve")
    @ResponseBody
    public Result add_solve(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam("name") String name,
                            @RequestParam("hours") Integer hours, @RequestParam("schools") Integer schools)
    {
        List<Course> courses=courseService.selectAll();
        Boolean success =true;
        for(Course res :courses)
        {
            if(res.getName().equals(name))
            {
                success=false;break;
            }
        }
        if(success.equals(true)) {
            try {
                String imageName=null;
                //是否添加课程图片
                if (ObjectUtils.isEmpty(imageFile) || imageFile.getSize() <= 0) {
                    imageName = "c974612e-552b-4733-9d0d-9286c4af620c.png";//默认图片
                    System.out.println(imageName+"我是null");
                }else{
                    // 判断上传的文件是否为图片类型
                    if (!imageFile.getContentType().startsWith("image/")) {
                        result.setMsg("imagefail");
                        return result;
                    }else{
                        imageName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(imageFile.getOriginalFilename());
                        // 生成图片文件名
                        String imagePath = "F:\\IEDAUltimate\\code\\ssm_course\\src\\main\\webapp\\static\\save\\" + imageName;
                        // 将图片保存到磁盘
                        Path imageFilePath = Paths.get(imagePath);
                        Files.write(imageFilePath, imageFile.getBytes());
                        System.out.println(imageName+"我不是null");
                    }
                }
                Course course =new Course(null,imageName,name,hours,schools);
                courseService.insertAutoId(course);
                System.out.println("{'module':'course save success'}");
                result.setMsg("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("{'module':'course save fail'}");
            result.setMsg("namefail");
        }
        result.setCode(200);
        return result;
    }
    //修改课程处理
    @PostMapping ("/update_solve")
    @ResponseBody
    public Result update_solve(@RequestParam("id") Integer id,@RequestParam("imageFile") MultipartFile imageFile,
                               @RequestParam("name") String name, @RequestParam("hours") Integer hours,
                               @RequestParam("schools") Integer schools
    ){
        System.out.println("-----------------update");
        List<Course> courses=courseService.selectAll();
        Boolean success =true;
        for(Course res :courses)
        {
            if(!res.getId().equals(id)&&res.getName().equals(name))
            {
                success=false;break;
            }
        }
        if(success.equals(true)) {
            try {
                String imageName=courseService.selectImage(id);
                //是否修改课程图片
                if (!ObjectUtils.isEmpty(imageFile) && imageFile.getSize() > 0) {
                    // 判断上传的文件是否为图片类型
                    if (!imageFile.getContentType().startsWith("image/")) {
                        result.setMsg("imagefail");
                        result.setCode(200);
                        return result;
                    }else{
                        imageName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(imageFile.getOriginalFilename());
                        // 生成图片文件名
                        String imagePath = "F:\\IEDAUltimate\\code\\ssm_course\\src\\main\\webapp\\static\\save\\" + imageName;
                        // 将图片保存到磁盘
                        Path imageFilePath = Paths.get(imagePath);
                        Files.write(imageFilePath, imageFile.getBytes());
                        System.out.println(imageName+"我不是null");
                    }
                }
                Course course =new Course(id,imageName,name,hours,schools);
                System.out.println(course+"--------------------update");
                courseService.update(course);
                System.out.println(course+"{'module':'course update success'}");
                result.setMsg("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            result.setMsg("namefail");
            System.out.println("{'module':'course update fail'}");
        }
        result.setCode(200);
        return result;
    }
}

import com.hnust.config.SpringConfig;
import com.hnust.entity.Course;
import com.hnust.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author 长夜
 * @date 2023/4/15 21:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
//设置Spring环境对应的配置类
@ContextConfiguration(classes = SpringConfig.class)
public class LogicTest {
    @Autowired
    private CourseService courseService;
    //查询课程
    @Test
    public void testSelectById(){
        int id = 1;
        Course course= courseService.selectById(id);
        System.out.println(course);
    }
    //通过学院名称查询课程
    @Test
    public void testSelectByscName(){
        String name = "计算机学院";
        List<Course> course = courseService.selectByscName(name);
        System.out.println(course);
    }
    //更新课程
    @Test
    public void testCourseUpdate(){
        int id = 4;
        int hours = 40;
        Course course=new Course();
        course.setId(id);
        course.setHours(hours);
        Boolean num= courseService.update(course);
        if(num==true)
        {
            System.out.println(courseService.selectById(id));
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败");
        }
    }
    //注意关闭IEDA->setting->Runner->delegate ide build/run actions to maven
    //不然会出现统一数据添加两次的情况
    //添加课程
    @Test
    public void testInsert(){
        Integer hours = 32;
        String name = "大数据存储";
        Integer schools = 1;
        Course course=new Course();
        course.setName(name);
        course.setHours(hours);
        course.setSchools(schools);
        Boolean num= courseService.insertAutoId(course);
        if(num==true)
        {
            System.out.println(course);
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }
    //查询所有课程
    @Test
    public void testSelectAll(){
        List<Course> course =courseService.selectAll();
        System.out.println(course);
    }
}

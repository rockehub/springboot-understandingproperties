package study.in.understandproperties.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class RestEnpoints {

    @Value("${default.course.name}")
    private String cName;
    @Value("${default.course.chapterCount}")
    private int cChapterCount;
    @Autowired
    private CourseConfiguration CourseConfiguration;

    @RequestMapping("/defaultcourse")
    public Course getDefaultCourse(@RequestParam(value = "name", defaultValue = "Spring Boot", required = false) String name,
                                   @RequestParam(value = "chapterCount", defaultValue = "2", required = false) int chapterCount
    ) {
        return new Course(cName, cChapterCount);

    }

    @RequestMapping("/gethierarchical")
    public HashMap<String, Object> getConfigAnnotationProperties() {
        HashMap<String, Object> map= new HashMap<String, Object>();
        map.put("name", CourseConfiguration.getName());
        map.put("chapterCount", CourseConfiguration.getChapterCount());
        map.put("rating", CourseConfiguration.getRating());
        map.put("author", CourseConfiguration.getAuthor());
        return map;

    }

    @RequestMapping("/course")
    public Course getEndpoint(@RequestParam(value = "name", defaultValue = "Spring Boot", required = false) String name,
                              @RequestParam(value = "chapterCount", defaultValue = "2", required = false) int chapterCount
    ) {
        return new Course(name, chapterCount);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/register/course")
    public String saveCourse(@RequestBody Course course) {
        return "your course named" + course.getName() + "with" + course.getChapterCount() + "chpter saved succesfully";
    }
}
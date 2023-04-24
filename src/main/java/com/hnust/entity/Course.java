package com.hnust.entity;

/**
 * @author 长夜
 * @date 2023/3/20 19:50
 */
public class Course {
    private Integer id;
    private String image;
    private String name;
    private Integer hours;
    private Integer schools;
    //省略构造方法、get和set方法以及toString方法
    public Course() {
    }

    public Course(Integer id, String image, String name, Integer hours, Integer schools) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.hours = hours;
        this.schools = schools;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return hours
     */
    public Integer getHours() {
        return hours;
    }

    /**
     * 设置
     * @param hours
     */
    public void setHours(Integer hours) {
        this.hours = hours;
    }

    /**
     * 获取
     * @return schools
     */
    public Integer getSchools() {
        return schools;
    }

    /**
     * 设置
     * @param schools
     */
    public void setSchools(Integer schools) {
        this.schools = schools;
    }

    public String toString() {
        return "Course{id = " + id + ", image = " + image + ", name = " + name + ", hours = " + hours + ", schools = " + schools + "}";
    }
}

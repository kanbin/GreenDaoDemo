package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class ExampleDaoGenerator {
    public static void main(String[] args){

        // 生成数据库的实体类XXentity对应的是数据库的表
        Schema schema = new Schema(1,"com.student.entity");
        addStudent(schema);
        schema.setDefaultJavaPackageDao("com.student.dao");

        try {
            new DaoGenerator().generateAll(schema, "D:\\malldemo\\GreenDaoDemo\\app\\src\\main\\java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 创建数据库的表
    private static void addStudent(Schema schema){
        Entity entity = schema.addEntity("Student"); // 创建数据库的表
        entity.addIdProperty(); // 主键 int类型
        entity.addStringProperty("name"); // 对应的数据库的列
        entity.addStringProperty("address");
        entity.addIntProperty("age");
    }
}

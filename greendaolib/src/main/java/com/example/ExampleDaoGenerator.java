package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class ExampleDaoGenerator {
    public static void main(String[] args){

        /**
         * http://www.open-open.com/lib/view/open1438065400878.html#articleHeader1
         * http://blog.csdn.net/h3c4lenovo/article/details/43566169
         */

        // 可以分别指定生成的 Bean 与 DAO 类所在的目录
        // 生成数据库的实体类XXentity  对应的是数据库的表
        // 正如你所见的，你创建了一个用于添加实体（Entity）的模式（Schema）对象。
        Schema schema = new Schema(1,"com.student.entity");
        // 一旦你拥有了一个 Schema 对象后，你便可以使用它添加实体（Entities）了。
        addStudent(schema);
        addTeacher(schema);
        schema.setDefaultJavaPackageDao("com.student.dao");

        try {
            // 最后我们将使用 DAOGenerator 类的 generateAll() 方法自动生成代码，此处你需要根据自己的情况更改输出目录（既之前创建的 java-gen)。
            new DaoGenerator().generateAll(schema, "D:\\malldemo\\GreenDaoDemo\\app\\src\\main\\java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTeacher(Schema schema) {
        Entity entity = schema.addEntity("Teacher");
        entity.addIdProperty();
        entity.addStringProperty("name");
        entity.addIntProperty("age");
        entity.addDoubleProperty("salary");
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

package com.kb.greendaodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kb.greendaodemo.dbmanager.CommonUtils;
import com.student.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private CommonUtils mCommonUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCommonUtils = new CommonUtils(this);
    }

    // 插入数据库的操作
    public void insertData(View view) {
        Log.d(TAG, "insertData: ");
        Student student = new Student();
        student.setAddress("江西");
        student.setName("李四");
        student.setAge(23);
//        student.setId(1001l);
        mCommonUtils.insertStudent(student);

    }

    public void insertMultiData(View view) {
        Log.d(TAG, "insertMultiData: ");

        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setAddress("江西");
            student.setName("李四"+i);
            student.setAge(23+i);
            list.add(student);
        }
        mCommonUtils.insertMultStudent(list);
    }

    public void updateData(View view) {
        // update student set name = 'jack' where id = 1001;
        Student student = new Student();
        student.setId(1001l);
        student.setAge(100);
        student.setName("jack");
        student.setAddress("北京朝阳");
        mCommonUtils.updateStudent(student);
    }

    public void deleteData(View view) {
        Student student = new Student();
        student.setId(1001l);
        // delete from student where _id = 1001l;
        mCommonUtils.deleteStudent(student);
    }

    public void queryOneORMore(View view) {
//        List<Student> students = mCommonUtils.listAll();
//        Log.d(TAG, "queryOneORMore: " + students.toString());
        Log.d(TAG, "queryOneORMore: " + mCommonUtils.listOneStudent(1001l));
    }

    /**
     * 使用复合条件进行查询
     * @param view
     */
    public void queryBuilder(View view) {
        mCommonUtils.query2();
    }
}

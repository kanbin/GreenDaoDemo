package com.kb.greendaodemo.dbmanager;

import android.content.Context;
import android.util.Log;

import com.student.dao.StudentDao;
import com.student.entity.Student;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * 完成对某一张表的具体操作，ORM操作的是对象，Student
 * Created by Administrator on 2016/10/21.
 */
public class CommonUtils {
    private static final String TAG = CommonUtils.class.getSimpleName();

    private DaoManager mManager;

    public CommonUtils(Context context) {
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成对数据库中student表的插入操作
     *
     * @param student
     * @return
     */
    public boolean insertStudent(Student student) {
        boolean flag = false;
        flag = mManager.getDaoSession().insert(student) != -1 ? true : false;
        Log.d(TAG, "insertStudent: " + flag);
        return flag;
    }

    /**
     * 插入多条记录，需要开辟新的线程
     *
     * @param studentList
     * @return
     */
    public boolean insertMultStudent(final List<Student> studentList) {
        boolean flag = false;

        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Student student : studentList) {
                        mManager.getDaoSession().insertOrReplace(student);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();

        }

        return flag;
    }

    /**
     * 完成对student某一条记录的修改
     *
     * @param student
     * @return
     */
    public boolean updateStudent(Student student) {
        boolean flag = false;

        try {
            mManager.getDaoSession().update(student);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * @param student
     * @return
     */
    public boolean deleteStudent(Student student) {
        boolean flag = false;
        try {
            mManager.getDaoSession().delete(student); // 按照指定的id进行删除 delete from student where _id = ?
//        mManager.getDaoSession().deleteAll(); // 删除所有的记录
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 返回多行记录
     * @return
     */
    public List<Student> listAll(){
        return mManager.getDaoSession().loadAll(Student.class);
    }

    /**
     * 按照主键返回单行记录
     * @param cls
     * @param key
     * @return
     */
    public Student listOneStudent(long key){
        return mManager.getDaoSession().load(Student.class,key);
    }

    public void query1(){
        // 使用native sql进行查询操作，
        List<Student> list = mManager.getDaoSession().queryRaw(Student.class, " where name like ? and _id > ?", new String[]{"%李%","1002"});
        Log.d(TAG, "query1: " + list);
    }

    /**
     * select * from student where name like ? or name = ? or
     *
     */
    public void query2(){
        // 查询构建器
        QueryBuilder<Student> builder = mManager.getDaoSession().queryBuilder(Student.class);
        List<Student> list = builder.where(StudentDao.Properties.Age.ge(23)).where(StudentDao.Properties.Address.like("江西")).list();
        Log.d(TAG, "query2: "+list);
    }
}

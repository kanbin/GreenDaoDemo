package com.kb.greendaodemo.dbmanager;

import android.content.Context;

import com.student.dao.DaoMaster;
import com.student.dao.DaoSession;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * 1、创建数据库
 * 2、创建数据库的表
 * 3、包含对数据库的CRUD
 * 4、对数据库的升级
 * Created by Administrator on 2016/10/21.
 *
 * 我：关于数据库升级，见http://blog.csdn.net/h3c4lenovo/article/details/43566169
 */
public class DaoManager {
    private static final String TAG = DaoManager.class.getSimpleName();

    private static final String DB_NAME = "mydb.sqlite"; // 数据库名称
    private volatile static DaoManager sManager; // 多线程访问  单例模式
    private  static DaoMaster.DevOpenHelper sHelper;
    private static DaoMaster sDaoMaster;
    private static DaoSession sDaoSession;
    private Context mContext;

    /**
     * 使用单例模式获得操作数据库的对象
     * 保证数据安全
     * @return
     */
    public static DaoManager getInstance(){
        DaoManager instance = null;
        if (sManager == null ){
            synchronized (DaoManager.class){
                if (instance == null){
                    instance = new DaoManager();
                    sManager = instance;
                }
            }
        }
        return instance;
    }

    public void init(Context context){
        this.mContext = context;
    }

    /**
     * 判断是否存在数据库，如果没有则创建数据库
     * @return
     */
    public DaoMaster getDaoMaster(){
        if (sDaoMaster == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
            sDaoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return sDaoMaster;
    }

    /**
     * 完成对数据库的添加、删除、修改、查询的操作，仅仅是一个接口
     * @return
     */
    public DaoSession getDaoSession(){
        if (sDaoSession == null) {
            if (sDaoMaster == null) {
                sDaoMaster = getDaoMaster();
            }
            sDaoSession = sDaoMaster.newSession();
        }
        return sDaoSession;
    }

    /**
     * 打开输出日志的操作，默认是关闭的
     */
    public void setDebug(){
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    /**
     * 关闭所有的操作，数据库开启的时候，使用完了要关闭
     *
     */
    public void closeConnection(){
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper(){
        if (sHelper == null) {
            sHelper .close();
            sHelper = null;
        }
    }

    public void closeDaoSession(){
        if (sDaoSession == null) {
            sDaoSession.clear();
            sDaoSession = null;
        }
    }
}

package com.dpwgc.message.center.ui.config;

import com.dpwgc.message.center.infrastructure.util.MongoDBUtil;
import com.mongodb.DB;
import com.mongodb.MongoCredential;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/**
 * Mongodb配置类
 */
@Configuration
public class MongoDBConfig implements InitializingBean {

    @Value("${mongodb.addr}")
    private String addr;

    @Value("${mongodb.port}")
    private int port;

    @Value("${mongodb.database}")
    private String database;

    @Value("${mongodb.user}")
    private String user;

    @Value("${mongodb.pwd}")
    private String pwd;

    /**
     * mongodb连接
     */
    public static DB db;

    /**
     * spring boot项目启动后自动执行mongodb连接初始化
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println("mongodb loading...");

        //如果账号为空，则默认mongodb没有设置密码
        if(user.length() == 0) {
            db = MongoDBUtil.connDB(addr,port,database);
        } else {
            //进行密码验证
            MongoCredential credential = MongoCredential.createCredential(user, database, pwd.toCharArray());
            db = MongoDBUtil.connDB(addr,port,database,credential);
        }

        System.out.println("mongodb database:"+db.toString());
    }

    /**
     * 获取mongodb连接
     * @return DB
     */
    public static DB getDB() {
        return db;
    }
}

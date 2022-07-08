package com.dpwgc.message.center.infrastructure.util;

import com.dpwgc.message.center.ui.config.MongoDBConfig;
import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

/**
 * Mongodb连接工具类
 */
public class MongoDBUtil {

    /**
     * 与Mongodb的指定数据库建立连接（DB线程安全，全局唯一）
     * @param addr mongodb的ip地址
     * @param port mongodb的端口号
     * @param database 数据库名称
     * @param credential Mongodb账户权限验证
     * @return DB
     * @throws UnknownHostException 抛出错误
     */
    public static DB connDB(String addr, int port, String database, MongoCredential credential) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(new ServerAddress(addr, port), Collections.singletonList(credential));
        return mongoClient.getDB(database);
    }

    /**
     * 与Mongodb的指定数据库建立连接（DB线程安全，全局唯一）
     * @param addr mongodb的ip地址
     * @param port mongodb的端口号
     * @param database 数据库名称
     * @return DB
     * @throws UnknownHostException 抛出错误
     */
    public static DB connDB(String addr, int port, String database) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(addr, port);
        return mongoClient.getDB(database);
    }

    /**
     * 选取数据库中的指定集合
     * @param db 数据库连接
     * @param collName 集合名称
     * @return DBCollection
     */
    public static DBCollection getColl(DB db,String collName) {
        return db.getCollection(collName);
    }

    /**
     * 在指定集合中插入文档
     * @param collName 集合名称
     * @param doc 要插入的文档对象
     * @return boolean
     */
    public static boolean setDoc(String collName, BasicDBObject doc) {

        //获取指定的mongodb集合
        DBCollection coll = getColl(MongoDBConfig.getDB(),collName);

        try {
            //插入文档
            coll.insert(doc);
            return true;
        }catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }

    /**
     * 获取指定集合中的文档
     * @param collName 集合名称
     * @param query 查询条件
     * @return List<DBObject>
     */
    public static List<DBObject> getDoc(String collName, BasicDBObject query) {

        DBCursor cursor = getColl(MongoDBConfig.getDB(),collName).find(query);

        return cursor.toArray();
    }
}

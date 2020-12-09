package com.cykj.auc.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class DbHelper {

    private ComboPooledDataSource dataSource;
    public static DbHelper dbhelper;
    private Connection connection;

    private DbHelper() {

        //加载xml配置文件获取其内部的数据流连接
        dataSource = new ComboPooledDataSource("oracle");

    }
    public synchronized static DbHelper getDbHelper(){
        if (dbhelper==null){
            dbhelper = new DbHelper();
        }
        return dbhelper;
    }


    public Connection getConnection() {

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void close(Connection connection){

            try {
                //判断当连接对象不为空的时候执行代码
                if (connection!=null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    public ComboPooledDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(ComboPooledDataSource dataSource) {
        this.dataSource = dataSource;
    }

}
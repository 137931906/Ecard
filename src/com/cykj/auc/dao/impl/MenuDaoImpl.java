package com.cykj.auc.dao.impl;

import com.cykj.auc.bean.Menu;
import com.cykj.auc.dao.MenuDao;
import com.cykj.auc.util.DbHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImpl implements MenuDao {


    @Override
    public List<Menu> findMenuByPid(int pId) {
        /**
         * 查询菜单栏名
         * @param pid 子菜单的父菜单ID
         * @return
         */
            List<Menu> menus=new ArrayList<>();
            String sql="select menuId,d.value menuName,url,pId from menu m inner join dic d on m.nameId=d.DICid where pId=?";
            Connection connection =null;
            try {
                //获取连接对象
                connection= DbHelper.getDbHelper().getConnection();
                //创建QueryRunner对象
                QueryRunner queryRunner = new QueryRunner();
                //带参数组
                Object[] prames ={pId};
                //执行查询--选择返回集合
                menus = queryRunner.query(connection,sql,new BeanListHandler<>(Menu.class),prames);
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                //归还连接对象
                DbHelper.getDbHelper().close(connection);
            }
            return menus;
        }
    }



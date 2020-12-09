package com.cykj.auc.util;

public class ObjectFactory {

    public static Object newInstance(String className){

        Object obj = null;
        try {
            //使用反射机制拿到地址创建实例类
            Class  clazz = Class.forName(className);
            obj = clazz.newInstance();
            System.out.println("obj:"+obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }
}

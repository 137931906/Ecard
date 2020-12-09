package com.cykj.auc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String methodName = req.getParameter("methodName");
        Class clazz = this.getClass();
        System.out.println(clazz);
        try {
            if (methodName != null && !methodName.trim().equals("")) {
                Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                String path = (String) method.invoke(this, req, resp);
                if (path != null && !path.trim().equals("")) {
                    req.getRequestDispatcher(path).forward(req, resp);
                }
         }
        }catch (NoSuchMethodException e) {
                e.printStackTrace();
        }catch (IllegalAccessException e) {
                e.printStackTrace();
        } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }


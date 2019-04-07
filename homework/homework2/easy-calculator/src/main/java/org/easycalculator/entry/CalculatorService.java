package org.easycalculator.entry;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.PrintWriter;

public class CalculatorService extends HttpServlet {
    private Calculator cal = null;

    @Override
    public void init() throws ServletException {
        super.init();
        cal = new Calculator();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        try{
            super.doGet(req, resp);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String exp = req.getParameter("expression");
        System.out.println(exp);
        try{
            PrintWriter out = resp.getWriter();
            String result = cal.evaluate(exp);
            out.println(result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

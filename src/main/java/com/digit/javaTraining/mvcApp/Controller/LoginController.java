package com.digit.javaTraining.mvcApp.Controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.mvcApp.model.BankApp;
@WebServlet("/Login")
public class LoginController extends HttpServlet{
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BankApp bankapp =new BankApp();
		bankapp.setCust_id(Integer.parseInt(req.getParameter("cust_id")));
		bankapp.setPin(Integer.parseInt(req.getParameter("pin")));
		bankapp.setCust_name(req.getParameter("cust_name"));
		HttpSession session = req.getSession(true);
		boolean isLogin = bankapp.login();
		if(isLogin)
		{
			session.setAttribute("accno",bankapp.getAccno());
        	session.setAttribute("cust_name",bankapp.getCust_name());
        	resp.sendRedirect("/Mvc_BankApp/homePage.jsp");
		}
		else {
			resp.sendRedirect("/Mvc_BankApp/loginFail.html");
		}
	}
}

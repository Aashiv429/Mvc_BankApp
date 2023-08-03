package com.digit.javaTraining.mvcApp.Controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.mvcApp.model.BankApp;
@WebServlet("/Loan")
public class LoanController extends HttpServlet{
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BankApp bankapp =new BankApp();
		HttpSession session = req.getSession(true);
		int loan_id = Integer.parseInt(req.getParameter("choice"));
		bankapp.setL_id(loan_id);
		if(bankapp.loan() ==true)
		{
        	session.setAttribute("l_id",bankapp.getL_id());
        	session.setAttribute("l_type",bankapp.getL_type());
        	session.setAttribute("tenure",bankapp.getTenure());
        	session.setAttribute("interest",bankapp.getInterest());
        	session.setAttribute("description",bankapp.getDescription());
        	resp.sendRedirect("/Mvc_BankApp/loanDetails.jsp");
		}
		else {
			resp.sendRedirect("/Mvc_BankApp/loanFail.html");
		}
}
}
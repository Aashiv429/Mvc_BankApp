package com.digit.javaTraining.mvcApp.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.mvcApp.model.BankApp;

@WebServlet("/CheckBalance")
public class CheckBalanceController extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		int acc_no = (int) session.getAttribute("accno");

		try {

			BankApp bankApp=new BankApp();

			bankApp.setAccno(acc_no);

			if (bankApp.checkBalance() == true) {

				session.setAttribute("balance", bankApp.getBalance());

				resp.sendRedirect("/Mvc_BankApp/balance.jsp");

			} else {

				resp.sendRedirect("/Mvc_BankApp/balanceFail.html");

			}

		} catch (Exception e) {

			e.printStackTrace();



		}
	}
}

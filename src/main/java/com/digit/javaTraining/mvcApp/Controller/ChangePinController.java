package com.digit.javaTraining.mvcApp.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.mvcApp.model.BankApp;
@WebServlet("/ChangePin")
public class ChangePinController extends HttpServlet{


	@Override

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		int accno = (int) session.getAttribute("accno");

		int old_pin = Integer.parseInt(req.getParameter("oldpin"));

		int n_pin = Integer.parseInt(req.getParameter("newpin"));

		int con_pin= Integer.parseInt(req.getParameter("confirmpin"));

		try {

			BankApp bankApp=new BankApp();

			bankApp.setAccno(accno);

			bankApp.setN_pin(n_pin);

			bankApp.setPin(old_pin);

			bankApp.setCon_pin(con_pin);

			if (bankApp.changePin() == true) {

				resp.sendRedirect("/Mvc_BankApp/pinSuccess.jsp");

			} else {

				resp.sendRedirect("/Mvc_BankApp/pinFail.jsp");

			}



		}catch (Exception e) {

			e.printStackTrace();

		}

	}
}

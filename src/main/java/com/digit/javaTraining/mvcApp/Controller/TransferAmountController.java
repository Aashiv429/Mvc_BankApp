package com.digit.javaTraining.mvcApp.Controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.javaTraining.mvcApp.model.TransferAmount;
@WebServlet("/TransferAmount")
public class TransferAmountController extends HttpServlet{
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		TransferAmount transfer  = new TransferAmount();
	int sen_accno = Integer.parseInt(req.getParameter("s_accno"));
	int c_id = Integer.parseInt(req.getParameter("cust_id"));
	String b_name = req.getParameter("bank_name");
	String s_ifsc = req.getParameter("ifsc_code");
	int pin = Integer.parseInt(req.getParameter("pin"));
	int amt = Integer.parseInt(req.getParameter("amount"));

	// receiver's details

	int rec_accno = Integer.parseInt(req.getParameter("r_accNo"));
	String r_ifsc = req.getParameter("r_ifsc");
	
	int t_id = new Random().nextInt(100000)+300000;
	transfer.setCust_id(c_id);
	transfer.setBank_name(b_name);
	transfer.setIfsc_code(s_ifsc);
	transfer.setSender_accno(sen_accno);
	transfer.setReceiver_ifsc(r_ifsc);
	transfer.setReceiver_accno(rec_accno);
	transfer.setAmount(amt);
	transfer.setTransferId(t_id);
	transfer.setPin(pin);
	
	if(transfer.transferAmount() ==true)
	{
    	resp.sendRedirect("/Mvc_BankApp/transferSuccess.html");
	}
	else {
		resp.sendRedirect("/Mvc_BankApp/transferFail.jsp");
	}
}
}

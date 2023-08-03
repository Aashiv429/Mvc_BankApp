package com.digit.javaTraining.mvcApp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TransferAmount {
	String bank_name;
	String ifsc_code;
	int sender_accno;
	String receiver_ifsc;
	int receiver_accno;
	int amount;
	int transferId;	
	int cust_id;
	int pin;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res1;
	private ResultSet res2;

	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getIfsc_code() {
		return ifsc_code;
	}
	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}
	public int getSender_accno() {
		return sender_accno;
	}
	public void setSender_accno(int sender_accno) {
		this.sender_accno = sender_accno;
	}
	public String getReceiver_ifsc() {
		return receiver_ifsc;
	}
	public void setReceiver_ifsc(String receiver_ifsc) {
		this.receiver_ifsc = receiver_ifsc;
	}
	public int getReceiver_accno() {
		return receiver_accno;
	}
	public void setReceiver_accno(int receiver_accno) {
		this.receiver_accno = receiver_accno;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}


	public TransferAmount() {
		String url = "jdbc:mysql://localhost:3306/bank";
		String user = "root";
		String pwd = "Minion@29";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean transferAmount() {
		try {
			// validate sender's details
			pstmt = con.prepareStatement("select * from bankapp where cust_id =? and ifsc_code = ? and accno =? and pin=?");
			pstmt.setInt(1, cust_id);
			pstmt.setString(2, ifsc_code);
			pstmt.setInt(3, sender_accno);
			pstmt.setInt(4, pin);
			res1 = pstmt.executeQuery();

			// validate receiver's details
			if (res1.next() == true) {
				pstmt = con.prepareStatement("select * from bankapp where  ifsc_code = ? and accno =? ");
				pstmt.setString(1, receiver_ifsc);
				pstmt.setInt(2, receiver_accno);
				res2 = pstmt.executeQuery();

				// check for sufficient balance
				int bal = res1.getInt(8);
				// debit amount
				if (bal > amount) {
					pstmt = con.prepareStatement("update bankapp set balance = balance-? where accno=?");
					pstmt.setInt(1, amount);
					pstmt.setInt(2, sender_accno);
					int x1 = pstmt.executeUpdate();

					if (x1 > 0) {
						pstmt = con.prepareStatement("update bankapp set balance = balance + ? where accno=? ");
						pstmt.setInt(1, amount);
						pstmt.setInt(2, receiver_accno);
						int x2 = pstmt.executeUpdate();
						if (x2 > 0) {
							pstmt = con.prepareStatement("insert into transferStatus values(?,?,?,?,?,?,?,?)");
							pstmt.setInt(1, cust_id);
							pstmt.setString(2, bank_name);
							pstmt.setString(3, ifsc_code);
							pstmt.setInt(4, sender_accno);
							pstmt.setString(5, receiver_ifsc);
							pstmt.setInt(6, receiver_accno);
							pstmt.setInt(7, amount);
							pstmt.setInt(8, transferId);
							int x3 = pstmt.executeUpdate();
							if (x3 > 0) {
								return true;
							} 
						} 
					} 
				} 
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return false;
	}


}

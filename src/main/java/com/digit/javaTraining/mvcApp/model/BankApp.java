package com.digit.javaTraining.mvcApp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BankApp {
	int bank_id;
	String bank_name;
	String ifsc_code;
	int accno;
	int pin;
	int cust_id;
	String cust_name;
	int balance;
	String email;
	long phone;
	int n_pin;
	int con_pin;
	int old_pin;
	int l_id;
	String l_type;
	int tenure;
	float interest;
	String description;
	public int getBank_id() {
		return bank_id;
	}
	public void setBank_id(int bank_id) {
		this.bank_id = bank_id;
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
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public int getN_pin() {
		return n_pin;
	}
	public void setN_pin(int n_pin) {
		this.n_pin = n_pin;
	}
	public int getCon_pin() {
		return con_pin;
	}
	public void setCon_pin(int con_pin) {
		this.con_pin = con_pin;
	}
	public int getOld_pin() {
		return old_pin;
	}
	public void setOld_pin(int old_pin) {
		this.old_pin = old_pin;
	}
	public int getL_id() {
		return l_id;
	}
	public void setL_id(int l_id) {
		this.l_id = l_id;
	}
	public String getL_type() {
		return l_type;
	}
	public void setL_type(String l_type) {
		this.l_type = l_type;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public float getInterest() {
		return interest;
	}
	public void setInterest(float interest) {
		this.interest = interest;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static ResultSet resultSet;
	public static Connection con;
	public static PreparedStatement pstmt;
	public BankApp()
	{

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
	

	public boolean register()
	{
		try {


			pstmt = con.prepareStatement("insert into bankapp values(?,?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, bank_id);

			pstmt.setString(2, bank_name);

			pstmt.setString(3, ifsc_code);

			pstmt.setInt(4, accno);

			pstmt.setInt(5, pin);

			pstmt.setInt(6, cust_id);

			pstmt.setString(7, cust_name);

			pstmt.setInt(8, balance);

			pstmt.setString(9, email);

			pstmt.setLong(10, phone);

			int x = pstmt.executeUpdate();
			if(x>0) {
				return true;
			}
			else {
				return false;
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean login()
	{
		try {
			pstmt = con.prepareStatement("select * from bankapp where Cust_id =? and pin = ?");
			pstmt.setInt(1, cust_id);
			pstmt.setInt(2, pin);
			ResultSet result = pstmt.executeQuery();
			if(result.next())
			{
				if(result.getInt("pin") == this.pin) {
					this.setAccno(result.getInt("accno"));
					this.setCust_name(result.getString("cust_name"));
					return true;
				}else {
					return false;
				}
			}
		}
		catch (Exception e) {

			e.printStackTrace();

		}
		return false;
	}

public boolean checkBalance() {
	try {

        pstmt = con.prepareStatement("select balance from bankapp where accno=?");

        pstmt.setInt(1, accno);



        resultSet = pstmt.executeQuery();

        if (resultSet.next() == true) {

            int balance = resultSet.getInt("balance");

            this.setBalance(balance);

            return true;

        } else {


            return false;

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return false;
}

public boolean changePin() {

    try {

        if(n_pin==con_pin) {

            pstmt = con.prepareStatement("update bankapp set pin=? where accno=? and pin=?");

            pstmt.setInt(1, n_pin);

            pstmt.setInt(2, accno);

            pstmt.setInt(3, pin);
            int x = pstmt.executeUpdate();

            if (x >0) {
         return true;

            } else {

                return false;
            }
            }else {

                return false;
            }
        } catch (Exception e) {

            e.printStackTrace();



        }



    return false;

}

public boolean loan()
{
	try {
		pstmt = con.prepareStatement("select * from loan where l_id =? ");
		pstmt.setInt(1, l_id);

		resultSet = pstmt.executeQuery();
		if (resultSet.next() == true) {
			int lid = resultSet.getInt("l_id");
			String ltype = resultSet.getString("l_type");
			int tenure = resultSet.getInt("tenure");
			float interest = resultSet.getInt("interest");
			String description = resultSet.getString("description");
            this.setL_id(lid);
            this.setL_type(ltype);
            this.setTenure(tenure);
            this.setInterest(interest);
            this.setDescription(description);
            return true;
		}else {
			return false;
		}
	} catch (Exception e) {

		e.printStackTrace();

	}
	return false;
}

}



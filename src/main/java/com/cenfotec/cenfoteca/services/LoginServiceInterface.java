package com.cenfotec.cenfoteca.services;

import javax.servlet.http.HttpSession;

import com.cenfotec.cenfoteca.contracts.LoginRequest;
import com.cenfotec.cenfoteca.contracts.LoginResponse;

public interface LoginServiceInterface {

	public void checkUser(LoginRequest lr, LoginResponse response, HttpSession currentSession);

}

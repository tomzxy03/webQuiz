package com.tomzxy.webQuiz.service;

import com.nimbusds.jose.JOSEException;
import com.tomzxy.webQuiz.dto.request.AuthenRequest.LoginRequest;
import com.tomzxy.webQuiz.dto.request.AuthenRequest.IntrospectRequest;
import com.tomzxy.webQuiz.dto.request.AuthenRequest.LogoutRequest;
import com.tomzxy.webQuiz.dto.response.AuthenResponse.LoginResponse;
import com.tomzxy.webQuiz.dto.response.AuthenResponse.IntrospectResponse;

import java.text.ParseException;

public interface AuthenticationService {
    public LoginResponse authenticate(LoginRequest request);

    public IntrospectResponse introspect (IntrospectRequest request) throws JOSEException, ParseException;

    public void logout (LogoutRequest request) throws JOSEException, ParseException;

}

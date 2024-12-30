package com.tomzxy.webQuiz.service;

import com.nimbusds.jose.JOSEException;
import com.tomzxy.webQuiz.dto.request.AuthenticatedRequest;
import com.tomzxy.webQuiz.dto.request.IntrospectRequest;
import com.tomzxy.webQuiz.dto.request.LogoutRequest;
import com.tomzxy.webQuiz.dto.response.AuthenticatedResponse;
import com.tomzxy.webQuiz.dto.response.IntrospectResponse;

import java.text.ParseException;

public interface AuthenticationService {
    public AuthenticatedResponse authenticate(AuthenticatedRequest request);

    public IntrospectResponse introspect (IntrospectRequest request) throws JOSEException, ParseException;

    public void logout (LogoutRequest request) throws JOSEException, ParseException;

}

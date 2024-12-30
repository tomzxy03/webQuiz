package com.tomzxy.webQuiz.controller;

import com.nimbusds.jose.JOSEException;
import com.tomzxy.webQuiz.constants.EndPoint;
import com.tomzxy.webQuiz.dto.request.AuthenticatedRequest;
import com.tomzxy.webQuiz.dto.request.IntrospectRequest;
import com.tomzxy.webQuiz.dto.request.LogoutRequest;
import com.tomzxy.webQuiz.dto.response.AuthenticatedResponse;
import com.tomzxy.webQuiz.dto.response.IntrospectResponse;
import com.tomzxy.webQuiz.dto.response.ResponseData;
import com.tomzxy.webQuiz.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping(EndPoint.Auth.BASE)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;


    @PostMapping(EndPoint.Auth.TOKEN)
    public ResponseData<AuthenticatedResponse> authenticate(@RequestBody AuthenticatedRequest request){
        var result = authenticationService.authenticate(request);

        return new ResponseData<>(HttpStatus.OK.value(), "Authentication token",result);
    }

    @PostMapping(EndPoint.Auth.INTROSPECT)
    public ResponseData<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);

        return new ResponseData<>(HttpStatus.OK.value(), "Introspect token",result);
    }

    @PostMapping(EndPoint.Auth.LOGOUT)
    public ResponseData<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);

        return new ResponseData<Void>(HttpStatus.OK.value(), "Introspect token");
    }
}

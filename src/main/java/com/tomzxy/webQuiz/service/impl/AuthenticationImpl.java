package com.tomzxy.webQuiz.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.tomzxy.webQuiz.dto.request.AuthenticatedRequest;
import com.tomzxy.webQuiz.dto.request.IntrospectRequest;
import com.tomzxy.webQuiz.dto.request.LogoutRequest;
import com.tomzxy.webQuiz.dto.response.AuthenticatedResponse;
import com.tomzxy.webQuiz.dto.response.IntrospectResponse;
import com.tomzxy.webQuiz.exception.ResourceNotFoundException;
import com.tomzxy.webQuiz.model.BaseEntity;
import com.tomzxy.webQuiz.model.InvalidToken;
import com.tomzxy.webQuiz.model.RolePermission;
import com.tomzxy.webQuiz.model.User;
import com.tomzxy.webQuiz.repository.InvalidedTokenRepository;
import com.tomzxy.webQuiz.repository.RolePermissionRepository;
import com.tomzxy.webQuiz.repository.UserRepository;
import com.tomzxy.webQuiz.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationImpl implements AuthenticationService {

    UserRepository userRepository;
    RolePermissionRepository rolePermissionRepository;
    InvalidedTokenRepository invalidedTokenRepository;

    @Value("${jwt.signerKey}")
    @NonFinal
    protected String SIGN_KEY ;


    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isvalid = true;
        try{
            verifyToken(token);

        }catch (Exception e){
            isvalid = false;
        }

        return IntrospectResponse.builder()
                .valid(isvalid)
                .build();


    }

    @Override
    public AuthenticatedResponse authenticate(AuthenticatedRequest request) {
        log.info("get user name of request {} {}", request.getUserName(), request.getPassword());
        var user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new ResourceNotFoundException("User not existed"));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!authenticated)
            throw new ResourceNotFoundException("Not authenticated");

        var token = generateToken(user);

        return AuthenticatedResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    public void logout(LogoutRequest token) throws ParseException, JOSEException {
        var signToken = verifyToken(token.getToken());
        String jit = signToken.getJWTClaimsSet().getJWTID();
        Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

        InvalidToken invalidToken = InvalidToken.builder()
                .id(jit)
                .expireTime(expiryTime)
                .build();
        invalidedTokenRepository.save(invalidToken);
    }

    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGN_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);



        if(!verified && expiryTime.after(new Date())){
            throw new ResourceNotFoundException("Token is expired");
        }

        if(invalidedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())){
            throw new ResourceNotFoundException("Token is invalidated");
        }
        return signedJWT;
    }

    private String generateToken(User user) {

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issuer("tomzxy.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))

                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try{
            jwsObject.sign(new MACSigner(SIGN_KEY.getBytes()));
            return jwsObject.serialize();
        }catch (Exception e){
            log.error("Cannot create token ",e);
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user){
        StringJoiner stringJoiner= new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(role ->{
                stringJoiner.add("ROLE_"+role.getName());

            });

        List<Long> roleIds = user.getRoles().stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
        List<RolePermission> rolePermissions = rolePermissionRepository.findPermissionsByRoleIds(roleIds);
        if(!CollectionUtils.isEmpty(rolePermissions)){
            rolePermissions.forEach(rolePermission -> {
                String scopeEntry = rolePermission.getObjectType() + "_" + rolePermission.getPermission().getName();
                stringJoiner.add(scopeEntry);
            });
        }

        return stringJoiner.toString();
    }



}
package com.sut.se61.g17.config;

public class SecurityConstants {
    public static final String SECRET = "InsuranceSecretKey";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
}

package com.example.storeprojectstep1.user;

import lombok.Data;

public class UserRequest {
    //회원가입수정
    @Data
    public static class UpdateDTO {
        private String username;
        private String password;
        private String phone;
        private String email;
        private String address;
    }

    //회원가입
    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String phone;
        private String email;
        private String address;
        private Integer role;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .phone(phone)
                    .email(email)
                    .address(address)
                    .role(role)
                    .build();
        }

    }

    //로그인
    @Data
    public static class LoginDTO {
        private String username;
        private String password;
        private Integer role;
    }
}

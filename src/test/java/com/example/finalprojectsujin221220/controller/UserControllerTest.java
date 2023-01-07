//package com.example.finalprojectsujin221220.controller;
//
//import com.example.finalprojectsujin221220.dto.UserJoinRequest;
//import com.example.finalprojectsujin221220.dto.UserLoginRequest;
//import com.example.finalprojectsujin221220.exception.ApplicationException;
//import com.example.finalprojectsujin221220.exception.ErrorCode;
//import com.example.finalprojectsujin221220.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithAnonymousUser;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(UserController.class)
//class UserControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    UserService us;
//
//    @Autowired
//    ObjectMapper objtMpr;
//
//    UserJoinRequest userJoinRequest = UserJoinRequest.builder()
//            .userName("sujin")
//            .password("pass123")
//            .build();
//
//    @Test
//    @DisplayName("회원가입 성공")
//    @WithMockUser
//    void join_success() throws Exception {
//        when(us.join(any())).thenReturn(mock(UserDto.class));
//
//        mockMvc.perform(post("/api/v1/users/join")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objtMpr.writeValueAsBytes(userJoinRequest)))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @DisplayName("회원가입 실패 - userName 중복")
//    @WithMockUser
//    void join_fail() throws Exception {
//        when(us.join(any())).thenThrow(new ApplicationException(ErrorCode.DUPLICATED_USER_NAME, ErrorCode.DUPLICATED_USER_NAME.getMessage()));
//
//        mockMvc.perform(post("/api/v1/users/join")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objtMpr.writeValueAsBytes(userJoinRequest)))
//                .andDo(print())
//                .andExpect(status().isConflict());
//    }
//
//    UserLoginRequest userLoginRequest = UserLoginRequest.builder()
//            .userName("sujin")
//            .password("pass123")
//            .build();
//
//    @Test
//    @DisplayName("로그인 성공")
//    @WithMockUser
//    void login_success() throws Exception {
//
//        when(us.login(any(), any())).thenReturn("token");
//
//        mockMvc.perform(post("/api/v1/users/login")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objtMpr.writeValueAsBytes(userLoginRequest)))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName("로그인 실패 - userName 없음")
//    @WithMockUser
//    void login_fail1() throws Exception {
//
//        when(us.login(any(), any())).thenThrow(new ApplicationException(ErrorCode.USERNAME_NOT_FOUND, ErrorCode.USERNAME_NOT_FOUND.getMessage()));
//
//        mockMvc.perform(post("/api/v1/users/login")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objtMpr.writeValueAsBytes(userJoinRequest)))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @DisplayName("로그인 실패 - password 틀림")
//    @WithAnonymousUser
//    void login_fail2() throws Exception {
//
//        when(us.login(any(), any())).thenThrow(new ApplicationException(ErrorCode.INVALID_PASSWORD, ErrorCode.INVALID_PASSWORD.getMessage()));
//
//        mockMvc.perform(post("/api/v1/users/login")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objtMpr.writeValueAsBytes(userLoginRequest)))
//                .andDo(print())
//                .andExpect(status().isUnauthorized());
//    }
//
//
//
//}
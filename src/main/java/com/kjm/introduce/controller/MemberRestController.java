//package com.kjm.introduce.controller;
//
//import com.kjm.introduce.config.security.JwtToken;
//import com.kjm.introduce.dto.MemberDto;
//import com.kjm.introduce.service.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * MemberRestController.java
// * Class 설명을 작성하세요.
// *
// * @author danusys
// * @since 2023.04.12
// */
//@RestController
//@RequestMapping("/members")
//@RequiredArgsConstructor
//public class MemberRestController {
//
//    private final MemberService memberService;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> loginAuth(MemberDto memberDto) {
//        JwtToken token = memberService.login(memberDto);
//        return ResponseEntity.ok(token);
//    }
//}

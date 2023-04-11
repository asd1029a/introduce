package com.kjm.introduce.controller;

import com.kjm.introduce.dto.MemberDto;
import com.kjm.introduce.repository.MemberRepository;
import com.kjm.introduce.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * MemberController.java
 * Class 설명을 작성하세요.
 *
 * @author kjm
 * @since 2023.04.06
 */
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginPage() {
        return "member/login";
    }

    @RequestMapping("/login/error")
    public String loginError(HttpServletRequest request, Model model) {
        String loginFail = (String) request.getAttribute("LoginFail");
        model.addAttribute("errorMessage","아이디,비번을 확인하세요");
        return "member/login";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/signUp";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "member/signUp";
        }
        try {
            memberService.save(memberDto);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/signUp";
        }
        return "redirect:/";
    }
}

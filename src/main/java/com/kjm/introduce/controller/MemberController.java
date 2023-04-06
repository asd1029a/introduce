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

    @GetMapping("/sign-up")
    public String signUp() {
        return "member/signUp";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "/sign-up";
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

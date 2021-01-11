package com.gamemoim.demo.controller;

import com.gamemoim.demo.group.GroupCreateRequestDto;
import com.gamemoim.demo.group.GroupValidator;
import com.gamemoim.demo.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final GroupValidator groupValidator;

    @InitBinder("GroupCreateRequestDto")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(groupValidator);
    }

    @GetMapping("/create-group")
    public String createGroupForm(Model model){
        model.addAttribute(new GroupCreateRequestDto());
        return "group/create";
    }

    @PostMapping("/create-group")
    public String createGroup(@Valid GroupCreateRequestDto requestDto){

        groupService.createGroup(requestDto);

        return "redirect:/";
    }
}

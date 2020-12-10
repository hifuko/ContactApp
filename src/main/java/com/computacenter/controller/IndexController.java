package com.computacenter.controller;

import com.computacenter.pojo.Person;
import com.computacenter.service.PersonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    private PersonService service;


    @GetMapping("/")
    @ApiOperation("Sort all contacts by first name, and list them by page. Then go to index(start) page to render them.")
    public String list(@PageableDefault(size = 5, sort = {"vorname"}, direction = Sort.Direction.ASC) Pageable pageable,
                       Model model){

        model.addAttribute("page", service.listPersons(pageable));
        return "index";
    }


}

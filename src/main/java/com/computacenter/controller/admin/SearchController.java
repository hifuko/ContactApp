package com.computacenter.controller.admin;

import com.computacenter.service.PersonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class SearchController {

    @Autowired
    private PersonService service;

    @PostMapping("/search")
    @ApiOperation("Return search results by user's query")
    public String search(@RequestParam("query") String query,
                         @PageableDefault(size = 5, sort = {"vorname"}, direction = Sort.Direction.ASC) Pageable pageable,
                         Model model){
        model.addAttribute("page", service.listPersons(query, pageable));
        //show query on the page
        model.addAttribute("query", query);
        return "../admin/search";
    }

    @GetMapping("/search/{ab_id}")
    @ApiOperation("Return all people from the same department")
    public String search(@PathVariable("ab_id") Long ab_id,
                         @PageableDefault(size = 5, sort = {"vorname"}, direction = Sort.Direction.ASC) Pageable pageable,
                         Model model){
        model.addAttribute("page", service.listPersons(ab_id, pageable));
        return "../admin/search";
    }


}

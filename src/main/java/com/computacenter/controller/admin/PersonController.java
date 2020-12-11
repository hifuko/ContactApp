package com.computacenter.controller.admin;

import com.computacenter.exception.KontaktNotFoundException;
import com.computacenter.pojo.Person;
import com.computacenter.service.AbteilungService;
import com.computacenter.service.PersonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class PersonController {

    private static final String DETAIL = "../admin/detail";
    private static final String CREATE = "../admin/create";
    private static final String REDIRECT_DETAIL = "redirect:/admin/detail";
    private static final String UPDATE = "../admin/update";

    @Autowired
    private PersonService personService;

    @Autowired
    private AbteilungService abteilungService;

    @GetMapping("/detail")
    @ApiOperation("Sort all contacts by first name, and list them by page. " +
            "Store the result in model and then go to detail to render them.")
    public String list(@PageableDefault(size = 5, sort = {"vorname"}, direction = Sort.Direction.ASC) Pageable pageable,
                       Model model){

        model.addAttribute("page", personService.listPersons(pageable));
        return DETAIL;
    }

    @GetMapping("/create")
    @ApiOperation("store the list of department in session for rendering and go to create page.")
    public String toCreate(Model model, HttpSession session){

        session.setAttribute("abteilungList", abteilungService.listAbteilungen());
        model.addAttribute("person", new Person());
        return CREATE;
    }



    @PostMapping("/detail")
    @ApiOperation("If user input of creating a new contact is valid, and email address is not taken yet, " +
            "then add the contact to database and go to detail page.")
    public String post(@Valid Person person, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()){
            return CREATE;
        }

        Person oldPerson = personService.getByMailadresse(person.getMailadresse());
        if (oldPerson != null){
            System.out.println("Person already exists");
            result.rejectValue("mailadresse", "mailadresseError", "Kontakt (Mailadresse) existiert schon.");
            return CREATE;
        }

        //user input
        Long id = person.getAbteilung().getId();
        if (id != null){
            person.setAbteilung(abteilungService.getAbteilung(id));
        }

        Person p = personService.savePerson(person);

        if (p == null ) {
            attributes.addFlashAttribute("message", "Kontakt Aktualisieren ist fehlgeschalgen!");
        } else {
            attributes.addFlashAttribute("message", "Kontakt erfolgreich eingefügt!");
        }
        return REDIRECT_DETAIL;
    }


    @GetMapping("/update/{id}")
    @ApiOperation("If id exists, then find the right contact info and go to update page.")
    public String toUpdate(@PathVariable("id") Long id, Model model, HttpSession session){
        Person p = personService.getById(id);
        if (p == null){
            throw new KontaktNotFoundException("Kontakt  mit  id  " + id + "  nicht  gefunden!");

        }

        model.addAttribute("person", p);
        session.setAttribute("abteilungList", abteilungService.listAbteilungen());

        return UPDATE;
    }


    @PostMapping("/person/{id}")
    @ApiOperation("If the user input is valid, then update the contact info and redirect to detail page." +
            "Otherwise go to error page")
    public String update(@Valid Person person, BindingResult result, RedirectAttributes attributes, @PathVariable("id") Long id){

        if (result.hasErrors()){
            return UPDATE;
        }


        Person oldPerson = personService.getByMailadresse(person.getMailadresse());
        if (oldPerson != null && !oldPerson.getId().equals(id)){
            System.out.println("Person already exists");
            result.rejectValue("mailadresse", "mailadresseError", "Kontakt (Mailadresse) existiert schon.");
            return UPDATE;

        }


        //user input
        Long abteilungId = person.getAbteilung().getId();
        if (abteilungId != null){
            person.setAbteilung(abteilungService.getAbteilung(abteilungId));
        }

        Person p = personService.updatePerson(person);

        if (p == null ) {
            attributes.addFlashAttribute("message", "Kontakt Einfügen ist fehlgeschalgen!");
        } else {
            attributes.addFlashAttribute("message", "Kontakt erfolgreich aktualisiert!");
        }
        return REDIRECT_DETAIL;

    }


    @GetMapping("/delete/{id}")
    @ApiOperation("If id exists, then delete the contact. Otherwise go to error page.")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes){
        Person p = personService.getById(id);
        if (p == null){
            throw new KontaktNotFoundException("Kontakt  mit  id  " + id + "  nicht  gefunden!");
        }

        personService.deletePerson(id);
        attributes.addFlashAttribute("message", "Kontakt erfolgreich gelöscht!");
        return REDIRECT_DETAIL;

    }
}

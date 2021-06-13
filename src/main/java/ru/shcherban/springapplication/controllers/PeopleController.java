package ru.shcherban.springapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shcherban.springapplication.dao.PersonDAO;
import ru.shcherban.springapplication.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id_in_method_show, Model model) {
        model.addAttribute("person", personDAO.show(id_in_method_show));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("personToBeDeliveredInThymeleaf") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person_from_Browser") Person personCreated) {
        personDAO.save(personCreated);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("personToBeDeliveredInHTMLEditFile", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("personToBeDeliveredInHTMLEditFile") Person person, @PathVariable("id") int id) {
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
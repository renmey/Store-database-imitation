package com.example.project.controllers;

import com.example.project.models.Item;
import com.example.project.models.Person;
import com.example.project.services.ItemsService;
import com.example.project.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/items")
public class ItemsController {

    private final ItemsService itemService;
    private final PeopleService peopleService;

    @Autowired
    public ItemsController(ItemsService itemsService, PeopleService peopleService) {
        this.itemService = itemsService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("items", itemService.findAll());

        return "items/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("item", itemService.findOne(id));

        Person itemOwner = itemService.getItemOwner(id);

        if (itemOwner != null)
            model.addAttribute("owner", itemOwner);
        else
            model.addAttribute("people", peopleService.findAll());

        return "items/show";
    }

    @GetMapping("/new")
    public String newItem(@ModelAttribute("item") Item item, Model model) {
        List<Person> people = peopleService.findAll();
        model.addAttribute("people", people);



        return "items/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("item") @Valid Item item,
                         BindingResult bindingResult,
                         Model model) {


        if (bindingResult.hasErrors())
            return "items/new";



        itemService.save(item);




        return "redirect:/items";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
       model.addAttribute("item", itemService.findOne(id));
        List<Person> people = peopleService.findAll();
        model.addAttribute("people", people);
        return "items/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("item") Item item,
                         BindingResult bindingResult,
                         @PathVariable("id") int id,

                         Model model) {
        if (bindingResult.hasErrors())
            return "items/edit";
        List<Person> people = peopleService.findAll();
        model.addAttribute("people", people);
        itemService.update(id, item);

        return "redirect:/items";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        itemService.delete(id);
        return "redirect:/items";
    }


    @GetMapping("/people")
    @ResponseBody
    public List<Person> getAllPeople() {
        return peopleService.findAll();
    }


}

package com.example.recipe.controllers;

import com.example.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({""})
    public String showRecipes(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        log.debug(LocalDateTime.now() + ":added attribute \"recipes\"");
        return "index";
    }
}

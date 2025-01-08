package com.example.recipe.controllers;

import com.example.recipe.domain.Recipe;
import com.example.recipe.services.RecipeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

public class IndexControllerTest {

    IndexController controller;

    @Mock
    RecipeService service;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        controller = new IndexController(service);
    }

    @Test
    public void testMockMvc() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    public void showRecipesReturnValue() {
        String returnValue = "index";
        Recipe recipe = new Recipe();
        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        Mockito.when(service.getRecipes()).thenReturn(recipesData);
        ArgumentCaptor<Set<Recipe>> captor = ArgumentCaptor.forClass(Set.class);

        Assert.assertEquals(returnValue, controller.showRecipes(model));

        Mockito.verify(service, Mockito.times(1)).getRecipes();
        Mockito.verify(model, Mockito.times(1))
                .addAttribute(ArgumentMatchers.eq("recipes"), captor.capture());

        Assert.assertSame(recipesData, captor.getValue());
    }
}
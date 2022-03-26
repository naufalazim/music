package com.project.musicappbackend.controller;

import com.project.musicappbackend.services.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    //Inject StorageService
    private final StorageService storageService;

    public IndexController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public String getHomepage(Model model) {


        //Model - View - Controller
        model.addAttribute("songFileNames", storageService.getSongFileNames());

        return "index";
    }
}

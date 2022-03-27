package com.project.musicappbackend.controller;

import com.project.musicappbackend.services.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

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

    //Upload file to application:
    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {


        storageService.uploadSong(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/";

//        storageService.uploadSong(file);
//        return "redirect:/";
    }


}

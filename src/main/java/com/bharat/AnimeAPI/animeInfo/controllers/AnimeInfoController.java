package com.bharat.AnimeAPI.animeInfo.controllers;

import com.bharat.AnimeAPI.animeInfo.models.*;
import com.bharat.AnimeAPI.animeInfo.services.AnimeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anime")
public class AnimeInfoController{
    @Autowired
    private AnimeInfoService animeInfoService;

    @GetMapping("/search/{name}")
    public AnimeSearch searchAnime(@PathVariable String name, @RequestParam(defaultValue = "1") String page){
        return animeInfoService.searchAnime(name, page);
    }

    @GetMapping("/details/{animeId}")
    public AnimeDetails getDetails(@PathVariable String animeId){
        return animeInfoService.getDetails(animeId);
    }

    @GetMapping("/top-airing")
    public AnimeTopAiringSearch getTopAiringAnimes(@RequestParam(defaultValue = "1") String page){
        return animeInfoService.getTopAiring(page);
    }

    @GetMapping("/genre/{genre}")
    public AnimeGenresSearch getGenresAnime(@PathVariable String genre, @RequestParam(defaultValue = "1") String page){
        return animeInfoService.getGenreAnime(genre, page);
    }
}

package com.bharat.AnimeAPI.userAnimeData.controller;

import com.bharat.AnimeAPI.userAnimeData.models.AnimeUserResponse;
import com.bharat.AnimeAPI.userAnimeData.services.AnimeUserService;
import com.bharat.AnimeAPI.userAnimeData.models.AnimeResponse;
import com.bharat.AnimeAPI.userAnimeData.models.AnimeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anime/user")
public class AnimeUserController {
    @Autowired
    private AnimeUserService animeUserService;

    @PostMapping("/add")
    public @ResponseBody AnimeResponse addAnimeUser(@RequestBody AnimeUser animeUser){
        animeUserService.addUsersAnime(animeUser);
        return AnimeResponse.builder().message("Added the user").build();
    }

    @PatchMapping("/{email}")
    public @ResponseBody AnimeResponse updateAnimeUser(@PathVariable String email, @RequestBody AnimeUser animeUser){
        animeUserService.updateUsersAnime(animeUser);
        return AnimeResponse.builder().message("Updated the user").build();
    }

    @GetMapping("/{email}")
    public @ResponseBody AnimeUserResponse getUser(@PathVariable String email){
        return animeUserService.getUserAndAnimes(email);
    }

    @DeleteMapping("/delete/{email}")
    public @ResponseBody AnimeResponse deleteUser(@PathVariable String email){
        animeUserService.deleteUser(email);
        return AnimeResponse.builder().message("deleted the user").build();
    }
}

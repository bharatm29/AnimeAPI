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


    @PostMapping("/init")
    public @ResponseBody AnimeResponse addAnimeUser(@RequestBody AnimeUser animeUser){
        return animeUserService.addAnimeUser(animeUser);
    }

    @PutMapping("/update")
    public @ResponseBody AnimeResponse updateAnimeUser(@RequestBody AnimeUser animeUser){
        return animeUserService.updateUsersAnime(animeUser);
    }

    @GetMapping("/{email}")
    public @ResponseBody AnimeUserResponse getUser(@PathVariable String email){
        return animeUserService.getUserAndAnimes(email);
    }

    @DeleteMapping("/delete/{email}")
    public @ResponseBody AnimeResponse deleteUser(@PathVariable String email){
        return animeUserService.deleteUser(email);
    }


}

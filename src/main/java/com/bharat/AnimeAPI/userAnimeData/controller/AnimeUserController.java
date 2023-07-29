package com.bharat.AnimeAPI.userAnimeData.controller;

import com.bharat.AnimeAPI.animeInfo.models.AnimeDetails;
import com.bharat.AnimeAPI.security.repositories.UserRepository;
import com.bharat.AnimeAPI.userAnimeData.models.AnimeUserResponse;
import com.bharat.AnimeAPI.userAnimeData.services.AnimeUserService;
import com.bharat.AnimeAPI.userAnimeData.models.AnimeResponse;
import com.bharat.AnimeAPI.userAnimeData.models.AnimeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anime/user")
public class AnimeUserController {
    @Autowired
    private AnimeUserService animeUserService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public @ResponseBody AnimeResponse addAnimeUser(@RequestBody AnimeUser animeUser){
        if(checkIfEmailDoesNotExists(animeUser.getEmail())){
            return getAnimeResponse("User is not registered!!");
        }

        animeUserService.addUsersAnime(animeUser);
        return getAnimeResponse("Added the user");
    }

    @PutMapping("/{email}")
    public @ResponseBody AnimeResponse updateAnimeUser(@PathVariable String email, @RequestBody AnimeUser animeUser){
        if(checkIfEmailDoesNotExists(animeUser.getEmail())){
            return getAnimeResponse("User is not registered!!");
        }

        animeUserService.updateUsersAnime(animeUser);
        return getAnimeResponse("Updated the user");
    }

    @GetMapping("/{email}")
    public @ResponseBody AnimeUserResponse getUser(@PathVariable String email){
        if (checkIfEmailDoesNotExists(email)) {
            return AnimeUserResponse.builder()
                    .email("Email not registered!!")
                    .animes(
                            List.of(
                                    AnimeDetails.builder()
                                            .animeTitle("Not found")
                                            .build()
                            )
                    )
                    .build();
        }

        return animeUserService.getUserAndAnimes(email);
    }

    @DeleteMapping("/delete/{email}")
    public @ResponseBody AnimeResponse deleteUser(@PathVariable String email){
        if(checkIfEmailDoesNotExists(email)){
            return getAnimeResponse("User is not registered!!");
        }

        animeUserService.deleteUser(email);
        return getAnimeResponse("deleted the user");
    }

    private static AnimeResponse getAnimeResponse(String message) {
        return AnimeResponse.builder()
                .message(message)
                .build();
    }

    private boolean checkIfEmailDoesNotExists(String email){
        return !userRepository.existsById(email);
    }
}

package com.bharat.AnimeAPI.userAnimeData.services;

import com.bharat.AnimeAPI.animeInfo.models.AnimeDetails;
import com.bharat.AnimeAPI.animeInfo.services.AnimeInfoService;
import com.bharat.AnimeAPI.security.repositories.UserRepository;
import com.bharat.AnimeAPI.userAnimeData.models.AnimeResponse;
import com.bharat.AnimeAPI.userAnimeData.models.AnimeUser;
import com.bharat.AnimeAPI.userAnimeData.models.AnimeUserResponse;
import com.bharat.AnimeAPI.userAnimeData.repositories.AnimeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeUserService {
    @Autowired
    private AnimeUserRepository animeUserRepository;

    @Autowired
    private AnimeInfoService animeInfoService;

    @Autowired
    private UserRepository userRepository;

    public AnimeResponse addAnimeUser(AnimeUser animeUser){
        if(checkIfEmailDoesNotExists(animeUser.getEmail())){
            return getAnimeResponse("User is not registered!!");
        }

        AnimeUser saveUser = animeUserRepository.findById(animeUser.getEmail()).orElse(null);

        if(saveUser != null){
            List<String> ids = List.copyOf(saveUser.getAnimeIds());
            animeUser.getAnimeIds().addAll(ids);
        }

        animeUserRepository.save(animeUser);

        return getAnimeResponse("Added the user");
    }

    public AnimeResponse updateUsersAnime(AnimeUser animeUser){
        if(checkIfEmailDoesNotExists(animeUser.getEmail())){
            return getAnimeResponse("User is not registered!!");
        }

        animeUserRepository.findById(animeUser.getEmail())
            .ifPresent(
                saveUser -> saveUser.getAnimeIds().forEach(animeId -> {
                    List<String> animeIds = animeUser.getAnimeIds();

                    if(!animeIds.contains(animeId)){
                        animeIds.add(0, animeId);
                    }
            }
        ));

        animeUserRepository.save(animeUser);
        return getAnimeResponse("Updated the user");
    }

    public AnimeUserResponse getUserAndAnimes(String email){
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

        AnimeUser animeUser = animeUserRepository.findById(email).orElse(null);

        if(animeUser == null){//in case the user is registered but does not save an account
            return AnimeUserResponse.builder().email(email).animes(
                    List.of(AnimeDetails.builder()
                            .animeTitle("No Animes associated with the account! Consider adding something")
                            .build())
            ).build();
        }

        return AnimeUserResponse.builder()
                .email(animeUser.getEmail())
                .animes(animeUser.getAnimeIds().stream().map(animeInfoService::getDetails).toList())
                .build();
    }

    public AnimeResponse deleteUser(String email){
        if(checkIfEmailDoesNotExists(email)){
            return getAnimeResponse("User is not registered!!");
        }

        animeUserRepository.deleteById(email);

        return getAnimeResponse("Deleted the user");
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
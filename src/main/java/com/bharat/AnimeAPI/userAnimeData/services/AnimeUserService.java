package com.bharat.AnimeAPI.userAnimeData.services;

import com.bharat.AnimeAPI.animeInfo.models.AnimeDetails;
import com.bharat.AnimeAPI.animeInfo.services.AnimeInfoService;
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

    public void addUsersAnime(AnimeUser animeUser){
        AnimeUser saveUser = animeUserRepository.findById(animeUser.getEmail()).orElse(null);

        if(saveUser != null){
            List<String> ids = List.copyOf(saveUser.getAnimeIds());
            animeUser.getAnimeIds().addAll(ids);
        }

        animeUserRepository.save(animeUser);
    }

    public void updateUsersAnime(AnimeUser animeUser){
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
    }

    public AnimeUserResponse getUserAndAnimes(String email){
        AnimeUser animeUser = animeUserRepository.findById(email).orElse(null);
        if(animeUser == null){
            return AnimeUserResponse.builder().email("Not found").animes(
                    List.of(AnimeDetails.builder().animeTitle("Not found").build())
            ).build();
        }

        return AnimeUserResponse.builder()
                .email(animeUser.getEmail())
                .animes(
                        animeUser.getAnimeIds().stream().map(
                                animeId -> {
                                    return animeInfoService.getDetails(animeId);
                                }
                        )
                                .toList()
                )
                .build();
    }

    public void deleteUser(String email){
        animeUserRepository.deleteById(email);
    }
}
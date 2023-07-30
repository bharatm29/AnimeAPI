package com.bharat.AnimeAPI.userAnimeData.services;

import com.bharat.AnimeAPI.animeInfo.models.AnimeDetails;
import com.bharat.AnimeAPI.animeInfo.services.AnimeInfoService;
import com.bharat.AnimeAPI.exceptions.AnimeUserException;
import com.bharat.AnimeAPI.security.repositories.UserRepository;
import com.bharat.AnimeAPI.userAnimeData.models.AnimeResponse;
import com.bharat.AnimeAPI.userAnimeData.models.AnimeUser;
import com.bharat.AnimeAPI.userAnimeData.models.AnimeUserResponse;
import com.bharat.AnimeAPI.userAnimeData.repositories.AnimeUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnimeUserService {
    @Autowired
    private AnimeUserRepository animeUserRepository;

    @Autowired
    private AnimeInfoService animeInfoService;

    @Autowired
    private UserRepository userRepository;

    public AnimeResponse addAnimeUser(AnimeUser animeUser) throws AnimeUserException{
        String userEmail = animeUser.getEmail();
        checkIfUserIsRegistered(userEmail);
        if(animeUserRepository.existsById(userEmail)){
            throw new AnimeUserException("User already initialized");
        }

        animeUserRepository.save(animeUser);

        return getAnimeResponse("Added the user");
    }



    public AnimeResponse updateAnimeUsers(AnimeUser animeUser) throws AnimeUserException {
        checkIfUserIsRegistered(animeUser.getEmail());

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

    public AnimeUserResponse getUserAndAnimes(String email) throws AnimeUserException {
        checkIfUserIsRegistered(email);

        AnimeUser animeUser = animeUserRepository.findById(email).orElse(null);

        if(animeUser == null){//in case the user is registered but does not have any animes in their account
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

    public AnimeResponse deleteUser(String email) throws AnimeUserException {
        checkIfUserIsRegistered(email);

        animeUserRepository.deleteById(email);

        return getAnimeResponse("Deleted the user");
    }

    private AnimeResponse getAnimeResponse(String message) {
        return AnimeResponse.builder()
                .message(message)
                .build();
    }

    public boolean checkIfEmailDoesNotExists(String email){
        return !userRepository.existsById(email);
    }

    public void checkIfUserIsRegistered(String userEmail) throws AnimeUserException {
        if(checkIfEmailDoesNotExists(userEmail)){
            throw new AnimeUserException("User is not registered!!");
        }
    }
}
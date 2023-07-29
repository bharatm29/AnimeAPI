package com.bharat.AnimeAPI.animeInfo.services;

import com.bharat.AnimeAPI.animeInfo.models.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AnimeInfoService {
    @Value("${animeapi.uri}")
    private String animeApiUri;

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "search-cb", fallbackMethod = "searchFallback")
    public AnimeSearch searchAnime(String name, String page){
        ResponseEntity<List<Anime>> searchResponse =
                restTemplate.exchange(animeApiUri + "search?keyw=" + name + "&page=" + page,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Anime>>(){
                        });

        return AnimeSearch.builder().animes(searchResponse.getBody()).build();
    }

    @CircuitBreaker(name = "details-cb", fallbackMethod = "detailsFallBack")
    public AnimeDetails getDetails(String animeId){
        return restTemplate.getForObject(
            animeApiUri + "anime-details/" + animeId,
                AnimeDetails.class
        );
    }

    @CircuitBreaker(name = "topAiring-cb", fallbackMethod = "topAiringFallback")
    public AnimeTopAiringSearch getTopAiring(String page){

        ResponseEntity<List<AnimeTopAiring>> topAiringAnimes =
                restTemplate.exchange(
                        animeApiUri + "/top-airing?page=" + page,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<AnimeTopAiring>>(){}
                );
        return AnimeTopAiringSearch.builder().animes(topAiringAnimes.getBody()).build();
    }

    @CircuitBreaker(name = "genre-cb", fallbackMethod = "genresSearchFallBack")
    public AnimeGenresSearch getGenreAnime(String genre, String page){
        ResponseEntity<List<AnimeGenres>> genresResponse =
                restTemplate.exchange(
                        animeApiUri + "/genre/" + genre + "?page=" + page,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<AnimeGenres>>(){}
                );

        return AnimeGenresSearch.builder().animes(
                genresResponse.getBody()
        ).build();
    }

    public AnimeSearch searchFallback(String name, String page, Exception e){
        return AnimeSearch.builder().animes(List.of(
                Anime.builder().animeTitle("Could not found").build()
        )).build();
    }

    public AnimeDetails detailsFallBack(String animeId, Exception e){
        return AnimeDetails.builder().animeTitle("Could not found this anime details").build();
    }

    public AnimeTopAiringSearch topAiringFallback(String page, Exception e){
        return AnimeTopAiringSearch.builder().animes(
                List.of(AnimeTopAiring.builder().animeTitle("Could not find any animes").build())
        ).build();
    }

    public AnimeGenresSearch genresSearchFallBack(String genres, String page, Exception e){
        return AnimeGenresSearch.builder().animes(
                List.of(AnimeGenres.builder().animeTitle("Could not find any anime of this genre").build())
        ).build();
    }
}

package com.bharat.AnimeAPI.animeInfoService.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AnimeDetails {
    private String animeTitle, type, synopsis, releasedDate, status, animeImg;
    private List<String> genres;

    private String episodesAvailable;

    private List<AnimeEpisode> episodesList;
}

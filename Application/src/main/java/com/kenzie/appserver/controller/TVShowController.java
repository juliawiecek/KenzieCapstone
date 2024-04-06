package com.kenzie.appserver.controller;

import com.kenzie.appserver.Exceptions.ShowNotFoundException;
import com.kenzie.appserver.service.ShowService;
import com.kenzie.appserver.service.model.Episode;
import com.kenzie.appserver.service.model.Image;
import com.kenzie.appserver.service.model.ShowInfo;
import com.kenzie.capstone.service.TVShowService;
import com.kenzie.capstone.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shows")
public class TVShowController {

    private final ShowService showService;

    @Autowired
    public TVShowController(ShowService showService) {
        this.showService = showService;
    }

    public List<ShowInfoResponse> convertToShowInfoResponseList(List<ShowInfo> showInfoList) {
        List<ShowInfoResponse> showInfoResponseList = new ArrayList<>();
        for (ShowInfo showInfo : showInfoList) {
            ShowInfoResponse showInfoResponse = new ShowInfoResponse(showInfo.getName(), showInfo.getGenres(),
                    showInfo.getRating(), showInfo.getImage(), showInfo.getSummary());
            showInfoResponseList.add(showInfoResponse);
        }
        return showInfoResponseList;
    }

    @GetMapping("/popular")
    public ResponseEntity<List<ShowInfoResponse>> getPopularShows() {
        List<ShowInfoResponse> showInfoResponseList = convertToShowInfoResponseList(showService.getPopularShows());
        if (showInfoResponseList.isEmpty()) {
            throw new ShowNotFoundException("No popular shows found");
        }
        return ResponseEntity.ok(showInfoResponseList);
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<List<ShowInfoResponse>> getShow(@PathVariable("query") String query) {
        List<ShowInfoResponse> showInfoResponseList = convertToShowInfoResponseList(showService.getShow(query));
        if (showInfoResponseList.isEmpty()) {
            throw new ShowNotFoundException("Show not found for query: " + query);
        }
        return ResponseEntity.ok(showInfoResponseList);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<ShowInfoResponse> getShowInfo(@PathVariable("id") String id) {
        ShowInfo showInfo = showService.getShowInfo(id);
        if (showInfo == null) {
            throw new ShowNotFoundException("Show info not found for ID: " + id);
        }
        return ResponseEntity.ok(new ShowInfoResponse(showInfo.getName(), showInfo.getGenres(), showInfo.getRating(),
                showInfo.getImage(), showInfo.getSummary()));
    }

    @GetMapping("/episodes/{id}")
    public ResponseEntity<List<EpisodeResponse>> getShowEpisodesForSeason(@PathVariable("id") String id) {
        List<EpisodeResponse> response = convertToEpisodeResponseList(showService.getShowEpisodesForSeason(id));
        if (response.isEmpty()) {
            throw new ShowNotFoundException("Episodes not found for season ID: " + id);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<ImageResponse> getShowImages(@PathVariable("id") String id) {
        Image image = showService.getShowImages(id);
        if (image == null) {
            throw new ShowNotFoundException("Images not found for show ID: " + id);
        }
        return ResponseEntity.ok(new ImageResponse(image.getType(), image.getId(), image.isMain(), image.getResolutions()));
    }

    @GetMapping("/seasons/{id}")
    public ResponseEntity<EpisodeResponse> getShowSeasons(@PathVariable("id") String id) {
        Episode episode = showService.getShowSeasons(id);
        if (episode == null) {
            throw new ShowNotFoundException("Seasons not found for show ID: " + id);
        }
        return ResponseEntity.ok(new EpisodeResponse(episode.getName(), episode.getEpisodeOrder(), episode.getNumber(),
                episode.getRuntime(), episode.getImage(), episode.getSummary()));
    }

    @ExceptionHandler(ShowNotFoundException.class)
    public ResponseEntity<String> handleShowNotFoundException(ShowNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    public List<EpisodeResponse> convertToEpisodeResponseList(List<Episode> episodeList) {
        List<EpisodeResponse> episodeResponseList = new ArrayList<>();
        for (Episode episode : episodeList) {
            EpisodeResponse episodeResponse = new EpisodeResponse(
                    episode.getName(),
                    episode.getEpisodeOrder(),
                    episode.getNumber(),
                    episode.getRuntime(),
                    episode.getImage(),
                    episode.getSummary()
            );
            episodeResponseList.add(episodeResponse);
        }
        return episodeResponseList;
    }

}

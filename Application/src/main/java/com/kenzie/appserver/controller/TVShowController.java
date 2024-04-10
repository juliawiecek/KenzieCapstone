package com.kenzie.appserver.controller;

import com.kenzie.appserver.Exceptions.ShowNotFoundException;
import com.kenzie.capstone.service.TVShowService;
import com.kenzie.capstone.service.client.TVShowLambdaServiceClient;
import com.kenzie.capstone.service.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class TVShowController {

    private final TVShowLambdaServiceClient tvShowServiceClient;

    public TVShowController(TVShowLambdaServiceClient tvShowServiceClient) {
        this.tvShowServiceClient = tvShowServiceClient;
    }


    @GetMapping("/popular")
    public ResponseEntity<List<ShowInfoData>> getPopularShows() {
        List<ShowInfoData> showInfoDataList = tvShowServiceClient.getPopularShows();
        return ResponseEntity.ok(showInfoDataList);
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<List<ShowInfoData>> getShow(@PathVariable("query") String query) {
        List<ShowInfoData> showInfoDataList = tvShowServiceClient.getSearchedShows(query);
        return ResponseEntity.ok(showInfoDataList);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<ShowInfoData> getShowInfo(@PathVariable("id") String id) {
        ShowInfoData showInfoData = tvShowServiceClient.getShowInfo(id);
        return ResponseEntity.ok(showInfoData);
    }

    @GetMapping("/episodes/{id}")
    public ResponseEntity<List<EpisodeData>> getShowEpisodesForSeason(@PathVariable("id") String id) {
        List<EpisodeData> episodeDataList = tvShowServiceClient.getEpisodes(id);
        return ResponseEntity.ok(episodeDataList);
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<List<ImageData>> getShowImages(@PathVariable("id") String id) {
        List<ImageData> imageDataList = tvShowServiceClient.getImages(id);
        return ResponseEntity.ok(imageDataList);
    }

    @GetMapping("/seasons/{id}")
    public ResponseEntity<List<SeasonsData>> getShowSeasons(@PathVariable("id") String id) {
        List<SeasonsData> seasonsDataList = tvShowServiceClient.getSeasons(id);
        return ResponseEntity.ok(seasonsDataList);
    }
    @ExceptionHandler(ShowNotFoundException.class)
    public ResponseEntity<String> handleShowNotFound(ShowNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}

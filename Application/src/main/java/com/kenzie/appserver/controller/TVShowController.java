package com.kenzie.appserver.controller;

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

    private final TVShowService TVshowService;

    public TVShowController(TVShowService TVshowService) {
        this.TVshowService = TVshowService;
    }


    @GetMapping("/popular")
    public ResponseEntity<List<ShowInfoResponse>> getPopularShows() {
        List<ShowInfoResponse> showInfoResponseList = TVshowService.getPopularShows();
        System.out.println("Show Info Response List: " + showInfoResponseList);
        return ResponseEntity.ok(showInfoResponseList);
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<List<ShowInfoResponse>> getShow(@PathVariable("query") String query) {
        List<ShowInfoResponse> showInfoResponseList = TVshowService.getShow(query);
        return ResponseEntity.ok(showInfoResponseList);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<ShowInfoResponse> getShowInfo(@PathVariable("id") String id) {
        ShowInfoResponse showInfoResponse = TVshowService.getShowInfo(id);
        return ResponseEntity.ok(showInfoResponse);
    }


    @GetMapping("/episodes/{id}")
    public ResponseEntity<List<EpisodeResponse>> getShowEpisodesForSeason(@PathVariable("id") String id) {
        List<EpisodeResponse> response = TVshowService.getShowEpisodesForSeason(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<List<ImageResponse>> getShowImages(@PathVariable("id") String id) {
        List<ImageResponse> response = TVshowService.getShowImages(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/seasons/{id}")
    public ResponseEntity<List<SeasonsResponse>> getShowSeasons(@PathVariable("id") String id) {
        List<SeasonsResponse> response = TVshowService.getShowSeasons(id);
        return ResponseEntity.ok(response);
    }
}

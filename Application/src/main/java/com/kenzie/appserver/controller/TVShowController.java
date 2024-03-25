package com.kenzie.appserver.controller;

import com.kenzie.capstone.service.TVShowService;
import com.kenzie.capstone.service.model.EpisodeResponse;
import com.kenzie.capstone.service.model.ImageResponse;
import com.kenzie.capstone.service.model.ShowInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class TVShowController {

    private final TVShowService tvShowService;

    @Autowired
    public TVShowController(TVShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    @GetMapping("/popular")
    public ResponseEntity<List<ShowInfoResponse>> getPopularShows() {
        return ResponseEntity.ok(tvShowService.getPopularShows());
    }

    @GetMapping("/{query}")
    public ResponseEntity<List<ShowInfoResponse>> getShow(@PathVariable("query") String query) {
        return ResponseEntity.ok(tvShowService.getShow(query));
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<ShowInfoResponse> getShowInfo(@PathVariable("id") String id) {
        return ResponseEntity.ok(tvShowService.getShowInfo(id));
    }

    @GetMapping("/episodes/{id}")
    public ResponseEntity<List<EpisodeResponse>> getShowEpisodesForSeason(@PathVariable("id") String id) {
        return ResponseEntity.ok(tvShowService.getShowEpisodesForSeason(id));
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<ImageResponse> getShowImages(@PathVariable("id") String id) {
        return ResponseEntity.ok(tvShowService.getShowImages(id));
    }

    @GetMapping("/seasons/{id}")
    public ResponseEntity<EpisodeResponse> getShowSeasons(@PathVariable("id") String id) {
        return ResponseEntity.ok(tvShowService.getShowSeasons(id));
    }

}

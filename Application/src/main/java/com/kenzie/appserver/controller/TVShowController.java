package com.kenzie.appserver.controller;

import com.kenzie.capstone.service.model.EpisodeResponse;
import com.kenzie.capstone.service.model.ShowInfoResponse;
import com.kenzie.capstone.service.model.TVShowReponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class TVShowController {
    // put in service
    public TVShowController(){}
    @GetMapping
    public ResponseEntity<List<TVShowReponse>> getPopularShows(){
        List<TVShowReponse> popularShows = new ArrayList<>();
        if (popularShows == null || popularShows.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(popularShows);
    }
    @GetMapping
    public ResponseEntity<TVShowReponse> getShow(){
        TVShowReponse tvShowReponse = new TVShowReponse();
        // call service
        // check for problems
        return ResponseEntity.ok(tvShowReponse);}
    @GetMapping
    public ResponseEntity<ShowInfoResponse> getShowInfo(){
        ShowInfoResponse showInfoResponse = new ShowInfoResponse();
        // call service
        // check for problems
        return ResponseEntity.ok(showInfoResponse);}
    @GetMapping
    public ResponseEntity<List<EpisodeResponse>> getshowEpisodeList(){
        List<EpisodeResponse> episodeResponses = new ArrayList<>();
        if (episodeResponses == null || episodeResponses.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(episodeResponses);}
}

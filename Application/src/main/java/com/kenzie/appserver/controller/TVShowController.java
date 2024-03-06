package com.kenzie.appserver.controller;

import com.kenzie.capstone.service.client.TVShowLambdaServiceClient;
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
    TVShowLambdaServiceClient tvShowLambdaServiceClient;
    public TVShowController(TVShowLambdaServiceClient tvShowLambdaServiceClient){this.tvShowLambdaServiceClient = tvShowLambdaServiceClient;}
    @GetMapping
    public ResponseEntity<String> getPopularShows(){
        String popularShows = tvShowLambdaServiceClient.getPopularShows();
        if (popularShows == null || popularShows.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(popularShows);
    }
    @GetMapping
    public ResponseEntity<String> getShow(){
        String show = tvShowLambdaServiceClient.getShow("");
        if (show == null || show.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(show);}
    @GetMapping
    public ResponseEntity<String> getShowInfo(){
       String showinfo = tvShowLambdaServiceClient.getShowInfo("");
        if (showinfo == null || showinfo.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(showinfo);}
    @GetMapping
    public ResponseEntity<String> getshowEpisodeList(){
        String episodeList = tvShowLambdaServiceClient.getShowEpisodeList("");
        if (episodeList == null || episodeList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(episodeList);}
}

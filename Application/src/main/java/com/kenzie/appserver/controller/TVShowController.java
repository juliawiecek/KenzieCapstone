package com.kenzie.appserver.controller;

import com.kenzie.capstone.service.client.TVShowLambdaServiceClient;
import com.kenzie.capstone.service.client.TVShowServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TVShowController {
    // put in service
    TVShowServiceClient tvShowLambdaServiceClient
    public TVShowController(TVShowServiceClient tvShowLambdaServiceClient){this.tvShowLambdaServiceClient = tvShowLambdaServiceClient;}
    @GetMapping("/shows")
    public ResponseEntity<String> getPopularShows(){
        String popularShows = tvShowLambdaServiceClient.getPopularShows();
        if (popularShows == null || popularShows.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(popularShows);
    }
//    @GetMapping
//    public ResponseEntity<String> getShow(){
//        String show = tvShowLambdaServiceClient.getShowInfo();
//        if (show == null || show.isEmpty()){
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(show);}
    @GetMapping("/search/shows?q=")
    public ResponseEntity<String> getShowInfo(@PathVariable("id") String id){
       String showinfo = tvShowLambdaServiceClient.getShowInfo(id);
        if (showinfo == null || showinfo.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(showinfo);}
    @GetMapping("/shows/")
    public ResponseEntity<String> getshowEpisodeList(){
        String episodeList = tvShowLambdaServiceClient.getShowEpisodeList("");
        if (episodeList == null || episodeList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(episodeList);}
}

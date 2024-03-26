package com.kenzie.appserver.controller;


//import com.kenzie.capstone.service.client.TVShowServiceClient;
import com.kenzie.appserver.service.model.ShowService;
import com.kenzie.capstone.service.client.TVShowServiceClient;
import com.kenzie.capstone.service.model.EpisodeData;
import com.kenzie.capstone.service.model.ImageData;
import com.kenzie.capstone.service.model.ShowInfoData;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class TVShowController {
   ShowService service;
    public TVShowController(ShowService service){this.service = service;}
    @GetMapping()
    public ResponseEntity<List<ShowInfoData>> getPopularShows(){
        List<ShowInfoData> popularShows = service.getPopularShows();
        if (popularShows == null || popularShows.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(popularShows);
    }
    @GetMapping
    public ResponseEntity<ShowInfoData> getShow(@PathVariable("id") String id){
        ShowInfoData show = service.getShowInfo(id);
        if (show == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(show);}
    @GetMapping
    public ResponseEntity<ImageData> getShowImage(@PathVariable("id") String id){
        ImageData imageData = service.getShowImage(id);
        if (imageData == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(imageData);
    }
    @GetMapping
    public ResponseEntity<ShowInfoData> getShowInfo(@PathVariable("id") String id){
       ShowInfoData showinfo = service.getShowInfo(id);
        if (showinfo == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(showinfo);}
    @GetMapping
    public ResponseEntity<EpisodeData> getShowSeasons(@PathVariable("id") String id){
        EpisodeData episodeData = service.getShowSeasons(id);
        if(episodeData == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(episodeData);
    }
    @GetMapping("/shows/")
    public ResponseEntity<List<EpisodeData>> getshowEpisodeList(@PathVariable("id") String id){
        List<EpisodeData> episodeList = service.getShowEpisodeList(id);
        if (episodeList == null || episodeList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(episodeList);}
}

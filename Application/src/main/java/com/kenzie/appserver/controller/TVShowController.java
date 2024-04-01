package com.kenzie.appserver.controller;

import com.kenzie.appserver.service.ShowService;
import com.kenzie.appserver.service.model.Episode;
import com.kenzie.appserver.service.model.Image;
import com.kenzie.appserver.service.model.ShowInfo;
import com.kenzie.capstone.service.TVShowService;
import com.kenzie.capstone.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class TVShowController {

    private final ShowService showService;

    public TVShowController(ShowService showService) {
        this.showService = showService;
    }

    public List<ShowInfoResponse> convertToShowInfoResponseList(List<ShowInfo> showInfoList) {
        List<ShowInfoResponse> showInfoResponseList = new ArrayList<>();
        for (ShowInfo showInfo : showInfoList) {
            ShowInfoResponse showInfoResponse = new ShowInfoResponse(showInfo.getName(), showInfo.getGenres(),
                    showInfo.getRating(), showInfo.getImage(), showInfo.getSummary());
            showInfoResponseList.add(showInfoResponse);
            showInfoList.add(showInfo);
        }
        return showInfoResponseList;
    }

    @GetMapping("/popular")
    public ResponseEntity<List<ShowInfoResponse>> getPopularShows() {
        List<ShowInfoResponse> showInfoResponseList = convertToShowInfoResponseList(showService.getPopularShows());
        return ResponseEntity.ok(showInfoResponseList);
    }

    @GetMapping("/{query}")
    public ResponseEntity<List<ShowInfoResponse>> getShow(@PathVariable("query") String query) {
        List<ShowInfoResponse> showInfoResponseList = convertToShowInfoResponseList((showService.getShow(query)));
        return ResponseEntity.ok(showInfoResponseList);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<ShowInfoResponse> getShowInfo(@PathVariable("id") String id) {
        ShowInfo showInfo = showService.getShowInfo(id);

        return ResponseEntity.ok(new ShowInfoResponse(showInfo.getName(), showInfo.getGenres(), showInfo.getRating(),
                showInfo.getImage(), showInfo.getSummary()));
    }

    public List<EpisodeResponse> convertToEpisodeResponseList(List<Episode> episodeList) {
        List<EpisodeResponse> episodeResponseList = new ArrayList<>();
        for (Episode episode : episodeList) {
            EpisodeResponse episodeResponse = new EpisodeResponse(episode.getName(), episode.getEpisodeOrder(),
                    episode.getNumber(), episode.getRuntime(), episode.getImage(), episode.getSummary());
            episodeResponseList.add(episodeResponse);
            episodeList.add(episode);
        }
        return episodeResponseList;
    }

    @GetMapping("/episodes/{id}")
    public ResponseEntity<List<EpisodeResponse>> getShowEpisodesForSeason(@PathVariable("id") String id) {
        List<EpisodeResponse> response = convertToEpisodeResponseList(showService.getShowEpisodesForSeason(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<ImageResponse> getShowImages(@PathVariable("id") String id) {
        Image image = showService.getShowImages(id);
        return ResponseEntity.ok(new ImageResponse(image.getType(), image.getId(), image.isMain(), image.getResolutions()));
    }

    @GetMapping("/seasons/{id}")
    public ResponseEntity<EpisodeResponse> getShowSeasons(@PathVariable("id") String id) {
        Episode episode = showService.getShowSeasons(id);
        return ResponseEntity.ok(new EpisodeResponse(episode.getName(), episode.getEpisodeOrder(), episode.getNumber(),
                episode.getRuntime(), episode.getImage(), episode.getSummary()));
    }

}

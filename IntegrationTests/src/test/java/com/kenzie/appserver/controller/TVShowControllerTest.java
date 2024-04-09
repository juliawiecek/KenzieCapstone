//package com.kenzie.appserver.controller;
//
//import com.kenzie.appserver.Exceptions.ShowNotFoundException;
//import com.kenzie.appserver.service.ShowService;
//import com.kenzie.appserver.service.model.Episode;
//import com.kenzie.appserver.service.model.ShowInfo;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(TVShowController.class)
//public class TVShowControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ShowService showService;
//
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    // Happy Path Tests
//    @Test
//    void getPopularShows_HappyPath() throws Exception {
//        List<ShowInfo> shows = Arrays.asList(new ShowInfo("Breaking Bad", Arrays.asList("Drama"), 9.5, Arrays.asList("image1.jpg"), "Description"));
//        given(showService.getPopularShows()).willReturn(shows);
//
//        mockMvc.perform(get("/api/shows/popular").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getShow_HappyPath() throws Exception {
//        List<ShowInfo> shows = Arrays.asList(new ShowInfo("Game of Thrones", Arrays.asList("Drama", "Fantasy"), 9.3, Arrays.asList("image1.jpg"), "Description"));
//        given(showService.getShow("Game of Thrones")).willReturn(shows);
//
//        mockMvc.perform(get("/api/shows/Game of Thrones").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getShowInfo_HappyPath() throws Exception {
//        ShowInfo showInfo = new ShowInfo("The Witcher", Arrays.asList("Fantasy"), 8.7, Arrays.asList("image1.jpg"), "Description");
//        given(showService.getShowInfo("1")).willReturn(showInfo);
//
//        mockMvc.perform(get("/api/shows/info/1").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getShowSeasons_HappyPath() throws Exception {
//        Episode episode = new Episode("Season 1", 1, 10, 60, "image1.jpg", "Description");
//        given(showService.getShowSeasons("1")).willReturn(episode);
//
//        mockMvc.perform(get("/api/shows/seasons/1").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    // Sad Path Tests
//    @Test
//    void getPopularShows_SadPath() throws Exception {
//        given(showService.getPopularShows()).willThrow(new ShowNotFoundException("No popular shows found"));
//
//        mockMvc.perform(get("/api/shows/popular").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void getShow_SadPath() throws Exception {
//        given(showService.getShow("Nonexistent Show")).willThrow(new ShowNotFoundException("Show not found"));
//
//        mockMvc.perform(get("/api/shows/Nonexistent Show").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void getShowInfo_SadPath() throws Exception {
//        given(showService.getShowInfo("Nonexistent ID")).willThrow(new ShowNotFoundException("Show info not found"));
//
//        mockMvc.perform(get("/api/shows/info/Nonexistent ID").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void getShowSeasons_SadPath() throws Exception {
//        given(showService.getShowSeasons("Nonexistent ID")).willThrow(new ShowNotFoundException("Seasons not found"));
//
//        mockMvc.perform(get("/api/shows/seasons/Nonexistent ID").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//}
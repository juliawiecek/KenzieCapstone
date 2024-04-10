package com.kenzie.appserver.controller;

import com.kenzie.appserver.Exceptions.ShowNotFoundException;
import com.kenzie.capstone.service.model.SeasonsData;
import com.kenzie.capstone.service.model.ShowInfoData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.kenzie.capstone.service.client.TVShowLambdaServiceClient;



import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TVShowController.class)
public class TVShowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TVShowLambdaServiceClient showService;


    @BeforeEach
    void setUp() {
    }

    // Happy Path Tests
    @Test
    void getPopularShows_HappyPath() throws Exception {
        List<ShowInfoData> shows = Arrays.asList(new ShowInfoData("Breaking Bad", Arrays.asList("Crime", "Drama"),
                new ShowInfoData.Rating(9.5), new ShowInfoData.Image("image1.jpg", "image2.jpg"),
                "Description", "104"));
        given(showService.getPopularShows()).willReturn(shows);

        mockMvc.perform(get("/shows/popular").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getShow_HappyPath() throws Exception {
        List<ShowInfoData> shows = Arrays.asList(new ShowInfoData("Game of Thrones", Arrays.asList("Drama", "Fantasy"),
                new ShowInfoData.Rating(9.3), new ShowInfoData.Image("image1.jpg", "image2.jpg"),
                "Description", "12"));
        given(showService.getSearchedShows("Game of Thrones")).willReturn(shows);

        mockMvc.perform(get("/shows/Game of Thrones").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getShowInfo_HappyPath() throws Exception {
        ShowInfoData showInfoData = new ShowInfoData("The Witcher", Arrays.asList("Fantasy", "Drama"),
                new ShowInfoData.Rating(8.7), new ShowInfoData.Image("image1.jpg", "image2.jpg"),
                "Description", "432");

        given(showService.getShowInfo("432")).willReturn(showInfoData);

        mockMvc.perform(get("/shows/info/432").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getShowSeasons_HappyPath() throws Exception {
        List<SeasonsData> seasons = Arrays.asList(new SeasonsData(1, "246"));
        given(showService.getSeasons("246")).willReturn(seasons);

        mockMvc.perform(get("/shows/seasons/246").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // Sad Path Tests
    @Test
    void getPopularShows_SadPath() throws Exception {
        given(showService.getPopularShows()).willThrow(new ShowNotFoundException("No popular shows found"));

        mockMvc.perform(get("/shows/popular").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getShow_SadPath() throws Exception {
        given(showService.getSearchedShows("Nonexistent Show")).willThrow(new ShowNotFoundException("Show not found"));

        mockMvc.perform(get("/shows/Nonexistent Show").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getShowInfo_SadPath() throws Exception {
        given(showService.getShowInfo("Nonexistent ID")).willThrow(new ShowNotFoundException("Show info not found"));

        mockMvc.perform(get("/api/shows/info/Nonexistent ID").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getShowSeasons_SadPath() throws Exception {
        given(showService.getSeasons("Nonexistent ID")).willThrow(new ShowNotFoundException("Seasons not found"));

        mockMvc.perform(get("/shows/seasons/Nonexistent ID").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
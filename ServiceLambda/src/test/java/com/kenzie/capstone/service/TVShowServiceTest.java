package com.kenzie.capstone.service;

import com.kenzie.capstone.service.TVShowService;
import com.kenzie.capstone.service.dao.TVShowDao;
import com.kenzie.capstone.service.model.EpisodeResponse;
import com.kenzie.capstone.service.model.ImageResponse;
import com.kenzie.capstone.service.model.ShowInfoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TVShowServiceTest {

    @Mock
    private TVShowDao tvShowDao;

    @InjectMocks
    private TVShowService tvShowService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    //ALL OF THE HAPPY PATH TESTS

    @Test
    void getPopularShows_HappyPath() {
        // Given
        String fakeApiResponse = "[{\"name\":\"Test Show\"}]";
        when(tvShowDao.getPopularShowsFromAPI()).thenReturn(fakeApiResponse);

        // When
        List<ShowInfoResponse> result = tvShowService.getPopularShows();

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Test Show", result.get(0).getName());
    }

    @Test
    void getShow_HappyPath() {
        // Given
        String fakeApiResponse = "{\"name\":\"Test Show\"}";
        when(tvShowDao.getShowFromAPI("1")).thenReturn(fakeApiResponse);

        // When
        ShowInfoResponse result = tvShowService.getShow("1");

        // Then
        assertNotNull(result);
        assertEquals("Test Show", result.getName());
    }

    @Test
    void getShowInfo_HappyPath() {
        // Given
        String fakeApiResponse = "{\"name\":\"Test Show\"}";
        when(tvShowDao.getShowInfoFromAPI("1")).thenReturn(fakeApiResponse);

        // When
        ShowInfoResponse result = tvShowService.getShowInfo("1");

        // Then
        assertNotNull(result);
        assertEquals("Test Show", result.getName());
    }

    @Test
    void getShowImages_HappyPath() {
        // Given
        String fakeApiResponse = "{\"id\":\"1\",\"type\":\"Poster\"}";
        when(tvShowDao.getShowImagesFromAPI("1")).thenReturn(fakeApiResponse);

        // When
        ImageResponse result = tvShowService.getShowImages("1");

        // Then
        assertNotNull(result);
        assertEquals("1", result.getId());
    }

    @Test
    void getShowSeasons_HappyPath() {
        // Given
        String fakeApiResponse = "{\"name\":\"Season 1\"}";
        when(tvShowDao.getShowSeasonsFromAPI("1")).thenReturn(fakeApiResponse);

        // When
        EpisodeResponse result = tvShowService.getShowSeasons("1");

        // Then
        assertNotNull(result);
        assertEquals("Season 1", result.getName());
    }

    @Test
    void getShowEpisodesForSeason_HappyPath() {
        // Given
        String fakeApiResponse = "[{\"name\":\"Episode 1\"}]";
        when(tvShowDao.getShowEpisodesForSeasonFromAPI("1")).thenReturn(fakeApiResponse);

        // When
        List<EpisodeResponse> result = tvShowService.getShowEpisodesForSeason("1");

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Episode 1", result.get(0).getName());
    }

 //ALL OF THE SAD PATH TESTS

    @Test
    void getPopularShows_SadPath() {
        // Given
        when(tvShowDao.getPopularShowsFromAPI()).thenThrow(new RuntimeException("Error fetching shows"));

        // Then
        assertThrows(RuntimeException.class, () -> tvShowService.getPopularShows());
    }

    @Test
    void getShow_SadPath() {
        // Given
        when(tvShowDao.getShowFromAPI("1")).thenThrow(new RuntimeException("Error fetching show"));

        // Then
        assertThrows(RuntimeException.class, () -> tvShowService.getShow("1"));
    }

    @Test
    void getShowInfo_SadPath() {
        // Given
        when(tvShowDao.getShowInfoFromAPI("1")).thenThrow(new RuntimeException("Error fetching show info"));

        // Then
        assertThrows(RuntimeException.class, () -> tvShowService.getShowInfo("1"));
    }

    @Test
    void getShowImages_SadPath() {
        // Given
        when(tvShowDao.getShowImagesFromAPI("1")).thenThrow(new RuntimeException("Error fetching images"));

        // Then
        assertThrows(RuntimeException.class, () -> tvShowService.getShowImages("1"));
    }

    @Test
    void getShowSeasons_SadPath() {
        // Given
        when(tvShowDao.getShowSeasonsFromAPI("1")).thenThrow(new RuntimeException("Error fetching seasons"));

        // Then
        assertThrows(RuntimeException.class, () -> tvShowService.getShowSeasons("1"));
    }

    @Test
    void getShowEpisodesForSeason_SadPath() {
        // Given
        when(tvShowDao.getShowEpisodesForSeasonFromAPI("1")).thenThrow(new RuntimeException("Error fetching episodes"));

        // Then
        assertThrows(RuntimeException.class, () -> tvShowService.getShowEpisodesForSeason("1"));
    }
}
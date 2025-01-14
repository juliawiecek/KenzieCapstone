package com.kenzie.capstone.service.dao;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TVShowDao {

    private static String api = "https://api.tvmaze.com";

    public TVShowDao() {
    }

    /**
     * Allows access to and manipulation of Match objects from the data store.
     * @param mapper Access to DynamoDB
     */


    public String getPopularShowsFromAPI() {
        String endpoint = "/shows";
        String url = api + endpoint;

        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                System.out.println(httpResponse.body());
                return httpResponse.body();
            } else {
                throw new ApiGatewayException("GET request failed: " + statusCode + " status code received"
                        + "\n body: " + httpResponse.body());
            }
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }

    public String getShowFromAPI(String query) {
        String endpoint = "/search/shows?q=";
        String url = api + endpoint + query;

        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                throw new ApiGatewayException("GET request failed: " + statusCode + " status code received"
                        + "\n body: " + httpResponse.body());
            }
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }

    public String getShowInfoFromAPI(String id) {
        String endpoint = "/shows/";
        String url = api + endpoint + id;

        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                throw new ApiGatewayException("GET request failed: " + statusCode + " status code received"
                        + "\n body: " + httpResponse.body());
            }
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }

    public String getShowImagesFromAPI(String id) {
        String endpoint = "/shows/";
        String images = "/images";
        String url = api + endpoint + id + images;

        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                throw new ApiGatewayException("GET request failed: " + statusCode + " status code received"
                        + "\n body: " + httpResponse.body());
            }
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }

    public String getShowSeasonsFromAPI(String id) {
        String endpoint = "/shows/";
        String seasons = "/seasons";
        String url = api + endpoint + id + seasons;

        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                throw new ApiGatewayException("GET request failed: " + statusCode + " status code received"
                        + "\n body: " + httpResponse.body());
            }
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }

    public String getShowEpisodesForSeasonFromAPI(String id) {
        String endpoint = "/seasons/";
        String episodeList = "/episodes";
        String url = api + endpoint + id + episodeList;

        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                throw new ApiGatewayException("GET request failed: " + statusCode + " status code received"
                        + "\n body: " + httpResponse.body());
            }
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }
}
package com.kenzie.capstone.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenzie.capstone.service.TVShowService;
import com.kenzie.capstone.service.dependency.DaggerServiceComponent;
import com.kenzie.capstone.service.dependency.TVShowServiceComponent;
import com.kenzie.capstone.service.model.ShowInfoResponse;
import org.apache.logging.log4j.LogManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class GetShow implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    static final Logger log = (Logger) LogManager.getLogger();
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        log.info(gson.toJson(input));

        TVShowServiceComponent tvShowServiceComponent = (TVShowServiceComponent) DaggerServiceComponent.create();
        TVShowService tvShowService = tvShowServiceComponent.provideTVShowService();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);

        String query = input.getPathParameters().get("query");

        if (query == null || query.isEmpty()){
            return response
                    .withStatusCode(400)
                    .withBody("query is invalid");
        }

        try {

            List<ShowInfoResponse> showInfoResponseList = tvShowService.getShow(query);
            String output = gson.toJson(showInfoResponseList);

            return response
                    .withStatusCode(200)
                    .withBody(output);
        } catch (Exception e){
            return response
                    .withStatusCode(400)
                    .withBody(gson.toJson(e.getMessage()));
        }
    }
}

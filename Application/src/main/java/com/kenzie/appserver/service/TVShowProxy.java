package com.kenzie.appserver.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Insert endpoint")
public class TVShowProxy {

    @GetMapping("insert endpoint")
    public void getTVShow(){
        // call proxy
        // check for null/empty
        //create response
        //return ResponseEntity w/ response
    }
    @GetMapping("Episode endpont")
    public void getAllEpisodes(){
        // same setup as get tvshow
    }
}

package com.ReVibe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ReVibe.model.Vibe;
import com.ReVibe.service.VibeService;

@RestController("vibeController") @RequestMapping("/vibe")
public class VibeController {
    private VibeService vibeService;
    
    @Autowired
    public VibeController(VibeService vibeService){
        this.vibeService = vibeService;
    }
    
    @PostMapping(path="/createVibe", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Vibe> save(@RequestBody Vibe vibe){
    	
        return new ResponseEntity<>(this.vibeService.saveVibe(vibe), HttpStatus.CREATED);
    }
    
    @PostMapping(path="/createReply", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Vibe> saveReply(@RequestBody Vibe vibe, int parentVibe){
    	
        return new ResponseEntity<>(this.vibeService.saveReply(vibe, parentVibe), HttpStatus.CREATED);
    }
    
    @GetMapping(path="/find/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vibe> findById(@PathVariable int vibeId){
    	
        return new ResponseEntity<>(this.vibeService.findById(vibeId), HttpStatus.OK);
    }
    
    @GetMapping(path="/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vibe>> findAll(){
    	
        return new ResponseEntity<>(this.vibeService.findAll(), HttpStatus.OK);
    }

    @PostMapping(path="/like", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Vibe> like(@RequestBody Vibe vibe, int accountId){
        return new ResponseEntity<>(this.vibeService.like(vibe, accountId), HttpStatus.CREATED);

    }
    
    @PostMapping(path="/unlike", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Vibe> unlike(@RequestBody Vibe vibe, int accountId){
        return new ResponseEntity<>(this.vibeService.unlike(vibe, accountId), HttpStatus.CREATED);
    }
    
    @GetMapping(path="/find/account/{account_id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vibe>> findByPoster(@PathVariable int account_id){
        return new ResponseEntity<>(this.vibeService.findByPoster(account_id), HttpStatus.OK);
    }
}
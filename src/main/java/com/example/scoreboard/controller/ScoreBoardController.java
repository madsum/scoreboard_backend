package com.example.scoreboard.controller;

import com.example.scoreboard.model.ScoreBoard;
import com.example.scoreboard.repository.ScoreBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ScoreBoardController {

    @Autowired
    private ScoreBoardRepository boardRepository;


    @GetMapping(path = "/score")
    public ResponseEntity<?> findById(@RequestParam(required=true) long id) {
        Optional<ScoreBoard> profile = boardRepository.findById(id);
        if(profile.isPresent()){
            return new ResponseEntity<>(profile, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Score not fond", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/all/score")
    public ResponseEntity<?> getAll() {
        List<ScoreBoard> result = boardRepository.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping(path = "/score")
    public ResponseEntity<?> addOrUpdateProfileById(@RequestBody ScoreBoard scoreBoard) {
        boardRepository.save(scoreBoard);
        return new ResponseEntity<>("ScoreBoard submission successfully", HttpStatus.OK);
    }


}

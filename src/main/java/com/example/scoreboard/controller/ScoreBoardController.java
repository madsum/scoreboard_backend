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

    @GetMapping(path = "/")
    public String showHomePage() {
        return "Welcome to the score board service.";
    }

    @GetMapping(path = "/score/{id}")
    public ScoreBoard findById(@PathVariable(required=true) long id) {
        Optional<ScoreBoard> scoreBoard = boardRepository.findById(id);
        return scoreBoard.orElse(null);
    }

    @GetMapping(path = "/all/score")
    public List<ScoreBoard> getAll() {
        return boardRepository.findAll();
    }

    @PostMapping(path = "/score")
    public ResponseEntity<?> addOrUpdate(@RequestBody ScoreBoard scoreBoard) {

        boardRepository.save(scoreBoard);
        return new ResponseEntity<>("ScoreBoard submission successfully", HttpStatus.OK);
    }

    @DeleteMapping(path = "/score/{id}")
    public ResponseEntity<?> deleteScoreBoard(@PathVariable(required = true) long id){
        boardRepository.deleteById(id);
        return new ResponseEntity<>("Score board deleted successfully", HttpStatus.OK);
    }
}

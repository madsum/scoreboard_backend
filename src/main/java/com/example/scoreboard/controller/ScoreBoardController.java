package com.example.scoreboard.controller;

import com.example.scoreboard.model.ScoreBoard;
import com.example.scoreboard.repository.ScoreBoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RequestMapping("scoreBoard")
@RestController
public class ScoreBoardController {

    Logger logger = LoggerFactory.getLogger(ScoreBoardController.class);

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

    @PostMapping(path = "/add/score")
    public ResponseEntity<?> addOrUpdate(@RequestBody ScoreBoard scoreBoard) {
        ScoreBoard  savedScoreBoard = boardRepository.save(scoreBoard);
        return new ResponseEntity<ScoreBoard>(savedScoreBoard, HttpStatus.OK);
    }

    @PutMapping(path = "/update/score")
    public ResponseEntity<?> updateScore(@RequestBody ScoreBoard scoreBoard) {
        boardRepository.save(scoreBoard);
        return new ResponseEntity<>("ScoreBoard update successfully", HttpStatus.OK);
    }

    @DeleteMapping(path = "/score/{id}")
    public ResponseEntity<?> deleteScoreBoard(@PathVariable(required = true) long id){
        boardRepository.deleteById(id);
        return new ResponseEntity<>("Score board deleted successfully", HttpStatus.OK);
    }
}

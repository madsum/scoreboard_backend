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
        try {
            boardRepository.save(scoreBoard);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(scoreBoard, HttpStatus.OK);
    }

    @PutMapping(path = "/update/score")
    public ResponseEntity<?> updateScore(@RequestBody ScoreBoard scoreBoard) {
        try {
            boardRepository.save(scoreBoard);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(scoreBoard, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/score/{id}")
    public ResponseEntity<?> deleteScoreBoard(@PathVariable(required = true) long id){
        try {
            boardRepository.deleteById(id);
        }catch (Exception ex){
          return new ResponseEntity<>("Score not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Score deleted successfully", HttpStatus.OK);
    }
}

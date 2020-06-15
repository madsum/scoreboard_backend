package com.example.scoreboard.controller;

import com.example.scoreboard.model.ScoreBoard;
import com.example.scoreboard.repository.ScoreBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("scoreBoard")
@RestController
public class ScoreBoardController {

    public static final String POST_REQUEST = "/add/score";
    public static final String GET_ALL_REQUEST = "/all/score";
    public static final String GET_BY_ID_REQUEST = "/score/";
    public static final String ROOT = "/";
    public static final String PUT_REQUEST = "/update/score";
    public static final String DELETE_REQUEST = "/delete/score/";

    @Autowired
    private ScoreBoardRepository boardRepository;

    @GetMapping(path = ROOT)
    public String showHomePage() {
        return "Welcome to the score board service.";
    }

    @GetMapping(path = GET_BY_ID_REQUEST + "{id}")
    public ScoreBoard getById(@PathVariable(required = true) long id) {
        Optional<ScoreBoard> scoreBoard = boardRepository.findById(id);
        return scoreBoard.orElse(null);
    }

    @GetMapping(path = GET_ALL_REQUEST)
    public List<ScoreBoard> getAll() {
        return boardRepository.findAll();
    }

    @PostMapping(path = POST_REQUEST)
    public ResponseEntity<?> addOrUpdate(@RequestBody ScoreBoard scoreBoard) {
        try {
            boardRepository.save(scoreBoard);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(scoreBoard, HttpStatus.OK);
    }

    @PutMapping(path = PUT_REQUEST)
    public ResponseEntity<?> updateScore(@RequestBody ScoreBoard scoreBoard) {
        try {
            boardRepository.save(scoreBoard);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(scoreBoard, HttpStatus.OK);
    }

    @DeleteMapping(path = DELETE_REQUEST + "{id}")
    public ResponseEntity<?> deleteScoreBoard(@PathVariable(required = true) long id) {
        try {
            boardRepository.deleteById(id);
        } catch (Exception ex) {
            return new ResponseEntity<>("Score not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Score deleted successfully", HttpStatus.OK);
    }
}

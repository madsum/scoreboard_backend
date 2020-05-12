package com.example.scoreboard.repository;

import com.example.scoreboard.model.ScoreBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreBoardRepository extends JpaRepository<ScoreBoard, Long> {

}

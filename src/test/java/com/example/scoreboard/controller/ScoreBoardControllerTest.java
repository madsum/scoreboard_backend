package com.example.scoreboard.controller;

import com.example.scoreboard.model.ScoreBoard;
import com.example.scoreboard.repository.ScoreBoardRepository;
import com.example.scoreboard.util.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScoreBoardControllerTest {

    @InjectMocks
    private ScoreBoardController scoreBoardController;

    @Mock
    private ScoreBoardRepository scoreBoardRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testShowHomePage() {
        // Arrangement
        String expectedString = "Welcome to the score board service.";

        // Act
        String actualString = scoreBoardController.showHomePage();

        // Assert
        assertEquals(actualString, expectedString);
    }

    @Test
    void testFindById() {
        // arrange
        ScoreBoard expectedScoreBoard = TestDataUtil.createScoreBoard();

        // act
        when(scoreBoardRepository.findById(1l)).thenReturn(java.util.Optional.of(expectedScoreBoard));
        ScoreBoard actualScoreBoard = scoreBoardController.findById(1l);

        // assert
        assertNotNull(actualScoreBoard);
        assertSame(expectedScoreBoard, actualScoreBoard);
    }

    @Test
    void testGetAll() {
        // arrange
        List<ScoreBoard> expectScoreBoardList = TestDataUtil.createScoreBoardList(5);

        // act
        when(scoreBoardRepository.findAll()).thenReturn(expectScoreBoardList);
        List<ScoreBoard> actualScoreBoardList = scoreBoardController.getAll();

        // assert
        assertNotNull(actualScoreBoardList);
        assertEquals(actualScoreBoardList.size(), expectScoreBoardList.size());
        assertIterableEquals(actualScoreBoardList, expectScoreBoardList);
    }

    @Test
    void testAddOrUpdate() {
        // arrange
        ScoreBoard expectedScoreBoard = TestDataUtil.createScoreBoard();

        // act
        when(scoreBoardRepository.save(expectedScoreBoard)).thenReturn(expectedScoreBoard);
        ResponseEntity<?> responseEntity = scoreBoardController.addOrUpdate(expectedScoreBoard);

        // assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertSame(responseEntity.getBody(), expectedScoreBoard);
    }

    @Test
    void testDeleteScoreBoard() {
        // arrange
        long id = 1L;

        // act
        ResponseEntity<?> responseEntity = scoreBoardController.deleteScoreBoard(id);

        // assert
        verify(scoreBoardRepository, times(1)).deleteById(eq(id));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody(), "Score deleted successfully");
    }
}
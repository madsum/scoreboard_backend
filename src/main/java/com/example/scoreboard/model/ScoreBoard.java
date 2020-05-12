package com.example.scoreboard.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "scoreboard", uniqueConstraints={@UniqueConstraint(columnNames ={"team"})})
public class ScoreBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String team;

    @NotNull
    private int point;

    public ScoreBoard(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}

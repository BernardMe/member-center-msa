package com.cheshun.po.admin.game;

import com.cheshun.entity.game.puzzle.PuzzlePiece;
import java.io.Serializable;

public class PuzzleRandomPiecePO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String key;

    private PuzzlePiece value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public PuzzlePiece getValue() {
        return value;
    }

    public void setValue(PuzzlePiece value) {
        this.value = value;
    }
}
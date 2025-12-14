package com.cheshun.po.admin.game;

import com.cheshun.entity.game.puzzle.PuzzleCover;
import com.cheshun.entity.game.puzzle.PuzzlePiece;

import java.io.Serializable;
import java.util.List;

public class PuzzleCoverPO extends PuzzleCover implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<PuzzlePiece> pieceList;

    public List<PuzzlePiece> getPieceList() {
        return pieceList;
    }

    public void setPieceList(List<PuzzlePiece> pieceList) {
        this.pieceList = pieceList;
    }
}
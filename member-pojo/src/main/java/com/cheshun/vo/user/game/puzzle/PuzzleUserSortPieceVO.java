package com.cheshun.vo.user.game.puzzle;

public class PuzzleUserSortPieceVO {

    private Integer pieceId;

    private Byte userSort;

    public Integer getPieceId() {
        return pieceId;
    }

    public void setPieceId(Integer pieceId) {
        this.pieceId = pieceId;
    }

    public Byte getUserSort() {
        return userSort;
    }

    public void setUserSort(Byte userSort) {
        this.userSort = ++userSort;
    }

}

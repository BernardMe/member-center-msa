package com.cheshun.vo.user.game.puzzle;

import java.io.Serializable;
import java.util.List;

public class PuzzleAnswerVO implements Serializable {

    /**
     * 用户任务id
     */
    private Integer userTaskId;
    /**
     * 封面id
     */
    private Integer coverId;
    /**
     * 拼图回答列表
     */
    private List<PuzzleUserSortPieceVO> pieceList;

    private static final long serialVersionUID = 1L;

    public Integer getUserTaskId() {
        return userTaskId;
    }

    public void setUserTaskId(Integer userTaskId) {
        this.userTaskId = userTaskId;
    }

    public Integer getCoverId() {
        return coverId;
    }

    public void setCoverId(Integer coverId) {
        this.coverId = coverId;
    }

    public List<PuzzleUserSortPieceVO> getPieceList() {
        return pieceList;
    }

    public void setPieceList(List<PuzzleUserSortPieceVO> pieceList) {
        this.pieceList = pieceList;
    }

}

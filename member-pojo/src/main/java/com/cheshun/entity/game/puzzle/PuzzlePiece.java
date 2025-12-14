package com.cheshun.entity.game.puzzle;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PuzzlePiece implements Serializable {
    private Integer pieceId;

    private Integer coverId;

    private String pieceName;

    private String pieceUrl;

    private Byte sort;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getPieceId() {
        return pieceId;
    }

    public void setPieceId(Integer pieceId) {
        this.pieceId = pieceId;
    }

    public Integer getCoverId() {
        return coverId;
    }

    public void setCoverId(Integer coverId) {
        this.coverId = coverId;
    }

    public String getPieceName() {
        return pieceName;
    }

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName == null ? null : pieceName.trim();
    }

    public String getPieceUrl() {
        return pieceUrl;
    }

    public void setPieceUrl(String pieceUrl) {
        this.pieceUrl = pieceUrl == null ? null : pieceUrl.trim();
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pieceId=").append(pieceId);
        sb.append(", coverId=").append(coverId);
        sb.append(", pieceName=").append(pieceName);
        sb.append(", pieceUrl=").append(pieceUrl);
        sb.append(", sort=").append(sort);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
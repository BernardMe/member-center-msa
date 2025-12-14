package com.cheshun.vo.project.customer.teacher;

import java.io.Serializable;

public class OcrResultVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * OCR结果行
     */
    private T words;

    public T getWords() {
        return words;
    }

    public void setWords(T words) {
        this.words = words;
    }
}
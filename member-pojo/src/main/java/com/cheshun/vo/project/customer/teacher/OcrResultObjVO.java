package com.cheshun.vo.project.customer.teacher;

import java.io.Serializable;
import java.util.List;

public class OcrResultObjVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会员卡号 
     */
    private List<OcrResultVO<String>> words_result;

    public List<OcrResultVO<String>> getWords_result() {
        return words_result;
    }

    public void setWords_result(List<OcrResultVO<String>> words_result) {
        this.words_result = words_result;
    }
}
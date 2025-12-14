package com.cheshun.survey;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class ExcelListener<T> extends AnalysisEventListener<T> {
    private final List<T> rows = new LinkedList<>();

    public void invoke(T object, AnalysisContext context) {
        this.rows.add(object);
    }

    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("read {} rows ", this.rows.size());
    }

    public List<T> getRows() {
        return rows;
    }

}
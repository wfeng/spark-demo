package org.wfeng.spark.rest.vm;

/**
 * 统计结果
 * Created by wfeng on 2018/5/31.
 */
public class WordCount {

    private String word;
    private Long count;

    public WordCount(String word, Long count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

package org.wfeng.spark.service;

import org.apache.commons.lang.StringUtils;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wfeng.spark.rest.vm.Word;
import org.wfeng.spark.rest.vm.WordCount;
import org.wfeng.spark.rest.vm.WordsRequest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 通过spark计算单词数量
 * Created by wfeng on 2018/5/31.
 */
@Service
public class WordsCountService {

    @Autowired
    SparkSession sparkSession;

    public Object wordsCounts(WordsRequest words) {

        if (words != null && !StringUtils.isEmpty(words.getWords())) {
            String[] wordsArray = words.getWords().split(" ");

            List<Word> wordList = Arrays.stream(wordsArray).map(s -> new Word(s)).collect(Collectors.toList());

            Dataset<Row> wordTable = sparkSession.createDataFrame(wordList, Word.class);
            Dataset<Row> wordCount = wordTable.groupBy("word").count();
            List<Row> rows = wordCount.collectAsList();
            return rows.stream().map(row -> new WordCount(row.getString(0), row.getLong(1))).collect(Collectors.toList());
        }

        return "";
    }
}

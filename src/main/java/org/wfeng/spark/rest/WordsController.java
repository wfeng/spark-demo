package org.wfeng.spark.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wfeng.spark.rest.vm.WordsRequest;
import org.wfeng.spark.service.WordsCountService;

/**
 * 接口
 * Created by wfeng on 2018/5/31.
 */
@RestController
public class WordsController {

    @Autowired
    WordsCountService wordsCountService;

    /**
     * 统计单词数量
     * 示例: {"words":"java spring spark hello word spark hello word aborted due to stage failure spark hello word spark hello word aborted due to hello word aborted due to stage failure spark hello java spring spark hello word spark hello word aborted due"}
     * @param words
     * @return
     */
    @PostMapping(value = "/words")
    public Object words(@RequestBody WordsRequest words) {
        return wordsCountService.wordsCounts(words);
    }
}

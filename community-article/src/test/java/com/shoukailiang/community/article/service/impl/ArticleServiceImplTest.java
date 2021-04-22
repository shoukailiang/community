package com.shoukailiang.community.article.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shoukailiang.community.article.mapper.ArticleMapper;
import com.shoukailiang.community.article.req.ArticleListREQ;
import com.shoukailiang.community.entities.Article;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceImplTest {

    @Resource
    private ArticleMapper articleMapper;
    //激活ContiPerf
    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    @Test
    //指定调用次数/线程数
    @PerfTest(invocations = 100, threads = 20)
    //指定性能要求 每次执行的最长时间/平均时间/总时间
    @Required(max = 1000, average = 250, totalTime = 2000)
    public void findArticleAndLabel() {
        ArticleListREQ req = new ArticleListREQ();
        req.setLabelId("2");
        IPage<Article> listByLabelIdOrCategoryId = articleMapper.findListByLabelIdOrCategoryId(req.getPage(), req);
        Assert.assertTrue(listByLabelIdOrCategoryId.getTotal()>0);
    }
}
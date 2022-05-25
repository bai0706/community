package com.nowcoder.community;

import com.nowcoder.community.util.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SensitiveTests {

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitiveFilter(){
        String text = "这里可以赌博，可以吸毒，可以嫖娼~";
        text = sensitiveFilter.filter(text);
        System.out.println(text);

        // 敏感词中间的符号会被一起过滤
        text = "这里可以℗赌℗博℗，可以℗吸℗毒℗，可以嫖娼~";
        text = sensitiveFilter.filter(text);
        System.out.println(text);
    }
}

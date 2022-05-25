package com.nowcoder.community.service;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;

@Service
public class AlphaService {

    // 事务 示例
    // 示例1：声明式事务
    // 示例2：编程式事务

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

    // Propagation 事务传播方式
    // REQUIRED: 支持当前事务（外部事务），如果当前不存在则创建新事务
    // REQUIRED_NEW: 创建一个新的事务，并且暂停当前事务（外部事务)
    // NESTED: 如果当前存在事务（外部事物）则嵌套在该事务中执行（独立的提交和回滚），否则就与REQUIRED操作一样。
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Object save1(){
        // 新增用户
        User user = new User();
        user.setUsername("alpha");
        user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
        user.setCreateTime(new Date());
        user.setHeaderUrl("http://image.nowcoder.com/head/99t.png");
        user.setEmail("alpha.qq.com");
        user.setPassword(CommunityUtil.md5("123") + user.getSalt());
        userMapper.insertUser(user);

        // 新增帖子
        DiscussPost post = new DiscussPost();
        post.setCreateTime(new Date());
        post.setContent("新人报道！");
        post.setTitle("Hello");
        post.setId(user.getId());
        discussPostMapper.insertDiscussPost(post);

        Integer.valueOf("abc");

        return "ok";
    }

    public Object save2(){
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        // 方法需要传一个回调式接口进去
        return transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                // 新增用户
                User user = new User();
                user.setUsername("beta");
                user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
                user.setCreateTime(new Date());
                user.setHeaderUrl("http://image.nowcoder.com/head/999.png");
                user.setEmail("beta.qq.com");
                user.setPassword(CommunityUtil.md5("123") + user.getSalt());
                userMapper.insertUser(user);

                // 新增帖子
                DiscussPost post = new DiscussPost();
                post.setCreateTime(new Date());
                post.setContent("我是新人！");
                post.setTitle("你好");
                post.setId(user.getId());
                discussPostMapper.insertDiscussPost(post);

                Integer.valueOf("abc");

                return "ok";
            }
        });
    }
}

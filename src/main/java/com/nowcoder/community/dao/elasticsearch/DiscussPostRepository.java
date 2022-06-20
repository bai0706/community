package com.nowcoder.community.dao.elasticsearch;

import com.nowcoder.community.entity.DiscussPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscussPostRepository  extends ElasticsearchRepository<DiscussPost, Integer> {
    // 创建接口，相当于数据库  继承es<接口要处理的实体类Discusspost, 实体类中的组件类型Integer>
}

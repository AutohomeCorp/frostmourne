package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import javax.annotation.Resource;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.UserInfo;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl.UserInfoRepository;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author menong
 * @date 2020-08-26
 */
@MybatisTest
@ActiveProfiles("test")
@Import(UserInfoRepository.class)
class UserInfoRepositoryTest {

    @Resource
    private IUserInfoRepository userInfoRepository;

    @Test
    void insert_test() {
        UserInfo insert = new UserInfo();
        insert.setAccount("test-user");
        insert.setFull_name("test-user");
        insert.setEmail("test-user@frostmourne.com");
        insert.setMobile("mobile");
        insert.setTeam_id(1L);
        insert.setWxid("wxid");
        insert.setCreator("test");
        insert.setCreate_at(DateTime.now().toDate());
        insert.setModifier("test");
        insert.setModify_at(DateTime.now().toDate());

        boolean result = userInfoRepository.insert(insert);
        assertTrue(result);

        Optional<UserInfo> optional = userInfoRepository.findByAccount(insert.getAccount());
        assertTrue(optional.isPresent());

        UserInfo select = optional.get();
        System.out.println(JacksonUtil.serialize(select));
        assertThat(insert.getFull_name(), equalTo(select.getFull_name()));
    }

}
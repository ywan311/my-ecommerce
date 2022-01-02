package com.example.myecommerce.Domain.Category;


import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    Environment environment;

    @After
    public void cleaup() {
        categoryRepository.deleteAll();
    }

    @Before
    public void 환경보기(){
        String[] profileArr = environment.getActiveProfiles();
        System.out.println(Arrays.toString(profileArr));

        for(String s : profileArr){
            System.out.println(environment.getProperty(s));
        }

    }

    @Test
    public void 카테고리저장_불러오기() {
        //given
        String title = "카테고리 테스트";

        categoryRepository.save(
                Category.builder()
                        .title(title)
                        .build());

        //when
        List<Category> list = categoryRepository.findAll();

        //then
        Category Category = list.get(0);
        Assertions.assertThat(Category.getTitle()).isEqualTo(title);
    }

    @Test
    public void BaseTimeEntity시간테스트() {
        //given

        String title = "카테고리 테스트";
        LocalDateTime now = LocalDateTime.of(2020, 2, 21, 5, 34, 00);

        categoryRepository.save(
                Category.builder()
                        .title(title)
                        .build());

        //when
        List<Category> list = categoryRepository.findAll();

        //then
        Category test = list.get(0);
        Assertions.assertThat(test.getCreatedDate()).isAfterOrEqualTo(now);
        Assertions.assertThat(test.getModifiedDate()).isAfterOrEqualTo(now);

    }
}


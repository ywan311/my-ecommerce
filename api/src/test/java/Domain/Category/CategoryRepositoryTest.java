package Domain.Category;


import com.example.myecoomerce.myecommercecore.Category.Category;
import com.example.myecoomerce.myecommercecore.Category.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    Environment environment;

    @AfterAll
    public void cleaup() {
        categoryRepository.deleteAll();
    }

    @BeforeAll
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


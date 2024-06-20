package com.example.demo.component;


import com.example.demo.constant.CommonKeyEnum;
import com.example.demo.constant.UserTypeEnum;
import com.example.demo.persistence.entity.JpaUser;
import com.example.demo.persistence.repository.JpaUserRepository;
import com.example.demo.utils.CoreBase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public void run(String... args) throws Exception
    {
        this.loadUser();
    }


    private void loadUser()
    {
        JpaUser jpaUser = new JpaUser();
        jpaUser.setUserType(String.valueOf(UserTypeEnum.ADMIN_USER));
        jpaUser.setUsername("admin");
        jpaUser.setPassword("password");
        jpaUser.setName("Admin");
        jpaUser.setEmail("admin@gmail.com");
        jpaUser.setCreatedBy("system");
        jpaUser.setIsEnable('1');
        jpaUser.setIsDeleted(CommonKeyEnum.NOT_DELETED);
        if (jpaUserRepository.findTopByUsername(jpaUser.getUsername()).isEmpty())
        {
            jpaUserRepository.save(jpaUser);
        }
    }
}

package com.carlrue.rau;

import com.carlrue.rau.adapters.out.persistence.SpringUserRepository;
import com.carlrue.rau.adapters.out.persistence.UserEntity;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InitUsers implements InitializingBean {

    private final SpringUserRepository extra;

    public InitUsers(SpringUserRepository extra) {

        this.extra = extra;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        //this.extra.save(new UserEntity(null, "user1", "Bartolomé Ruiz", "bartolome@gmail.com"));
        //this.extra.save(new UserEntity(null, "user2", "Juan De La Torre", "juan@gmail.com"));
        //this.extra.save(new UserEntity(null, "user3", "Martín de Paz", "martin@gmail.com"));
    }
}

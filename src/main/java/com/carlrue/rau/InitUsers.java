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
        this.extra.save(new UserEntity(null, "andrea", "Andres Ruiz Del Alamo", "bartolome@sharedexpenses.com"));
        this.extra.save(new UserEntity(null, "juan", "Juan De La Torre Mayor", "juantor@sharedexpenses.com"));
        this.extra.save(new UserEntity(null, "felipe", "Felipe González Izquierdo", "felipe@sharedexpenses.com"));
        this.extra.save(new UserEntity(null, "laura", "Laura Iglesias Ortega", "laura@sharedexpenses.com"));
    }
}

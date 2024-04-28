package com.example.storeprojectstep1.user;

import com.example.storeprojectstep1.product.ProductRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepo;
    private final EntityManager em;

    @Transactional
    public User save(User user) {
        em.persist(user);
        return user;
    }

//    @Transactional
//    public User login(UserRequest.LoginDTO reqDTO) {
//        User user = userRepo.findByUsernameAndPassword(reqDTO);
//        return user;
//    }


}

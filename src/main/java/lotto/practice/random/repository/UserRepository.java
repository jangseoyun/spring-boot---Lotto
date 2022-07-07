package lotto.practice.random.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    //회원가입 등록
    public Long saveUser(User user){
        em.persist(user);
        Long userNo = user.getNo();
        return userNo;
    }

    //회원가입 검증(이메일, 이름)
    public Optional<User> findUserId(String userId) {
        List<User> findUserOne = em.createQuery("select u from User u where u.userId = :userId", User.class)
                .setParameter("userId", userId)
                .getResultList();

        return findUserOne.stream().findAny();
    }



}

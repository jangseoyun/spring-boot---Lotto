package lotto.practice.random.repository;

import lombok.RequiredArgsConstructor;
import lotto.practice.random.dto.JoinDto;
import lotto.practice.random.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

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


}

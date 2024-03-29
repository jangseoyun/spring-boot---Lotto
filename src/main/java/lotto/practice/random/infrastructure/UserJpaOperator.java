package lotto.practice.random.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.user.User;
import lotto.practice.random.domain.user.UserOperator;
import lotto.practice.random.dto.LoginDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserJpaOperator implements UserOperator {

    private final EntityManager em;

    //회원가입 등록
    @Override
    public Long saveUser(User user){
        em.persist(user);
        Long userNo = user.getNo();
        return userNo;
    }
    @Override
    //회원가입 검증(이메일, 이름)
    public Optional<User> findUserNo(String userId) {
        List<User> findUserOne = em.createQuery("select u from User u where u.userId = :userId", User.class)
                .setParameter("userId", userId)
                .getResultList();

        return findUserOne.stream().findAny();
    }

    @Override
    public User checkLogin(LoginDto loginUser) {
        List<User> getUserLogin = em.createQuery("select u from User u" +
                        " where u.userId = :loginId" +
                        " and u.userPw = :loginPw", User.class)
                .setParameter("loginId", loginUser.getUserId())
                .setParameter("loginPw", loginUser.getUserPw())
                .getResultList();

        if (getUserLogin.size() == 0) {
            throw new IllegalArgumentException("아이디 혹은 비밀번호가 일치하지 않습니다.");
        }

        return getUserLogin.get(0);

    }

    @Override
    public User getUserOne(Long userNo) {
        return em.createQuery("select u from User u where u.no = :userNo", User.class)
                .setParameter("userNo", userNo)
                .getSingleResult();
    }


}

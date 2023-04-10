package lotto.practice.random.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.sql.Date;

@Service
public class SecurityService {
    //예제라서 여기서 넣어둔것
    private static final String SECURITY_KEY = "sdfkasjdhfkajsdhfkajsdhfeuawhefkvjbxcnzwersdkfjlskdjf";

    //로그인 서비스 던질 때 같이
    //토큰 생성
    public String createToken(String subject, long expTime) {
        if (expTime <= 0) {
            throw new RuntimeException("만료시간이 0보다 커야한다");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;//서명 알고리즘을 하기 위해서 선택야한다

        byte[] secreteKeyBytes = DatatypeConverter.parseBase64Binary(SECURITY_KEY);
        Key singingKey = new SecretKeySpec(secreteKeyBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setSubject(subject)//유저 아이디, 비밀번호 등
                .signWith(singingKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))//만료시간 설정
                .compact();

    }

    //토큰 검증하는 메서드를 valid 사용 --> boolean으로 리턴해서 사용
    //실제 꺼내오는 것
    public String getSubject(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECURITY_KEY))
                .build()
                .parseClaimsJws(token)//토큰을 가지고 풀어줘야한다
                .getBody();// claims이 만들어줌 우리는 subject만 꺼내올 것

        return claims.getSubject();
    }
}

package lotto.practice.random.proxy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;

    public void run() {
        System.out.println(name + " is run!!");
    }

    public void work() {
        throw new RuntimeException("에러발생!!");
    }
}

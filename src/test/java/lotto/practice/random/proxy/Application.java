package lotto.practice.random.proxy;

import org.junit.jupiter.api.Test;

public class Application {

    @Test
    public void test1() {
        UserProxy user = new UserProxy(new User("hun"));
        user.run();
    }

    @Test
    public void test2() {
        UserProxy user = new UserProxy(new User("hun"));
        user.work();
    }
}

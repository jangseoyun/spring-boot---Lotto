package lotto.practice.random.proxy;

public class UserStartEndProxy extends User {
    private User targetUser;

    public UserStartEndProxy(User targetUser) {
        this.targetUser = targetUser;
    }

    @Override
    public void run() {
        System.out.println("run start!!");
        targetUser.run();
        System.out.println("run end!!");
    }

    @Override
    public void work() {
        System.out.println("work start!!");
        targetUser.work();
        System.out.println("work end!!");
    }
}

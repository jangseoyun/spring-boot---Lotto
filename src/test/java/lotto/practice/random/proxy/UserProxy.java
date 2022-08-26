package lotto.practice.random.proxy;

public class UserProxy extends User {
    private User targetUser;

    public UserProxy(User targetUser) {
        this.targetUser = targetUser;
    }

    @Override
    public void run() {
        try {
            start();
            targetUser.run();
            commit();
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        }
    }

    @Override
    public void work() {
        try {
            start();
            targetUser.work();
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
    }

    private void start() {
        System.out.println("Transactional start!!");
    }

    private void commit() {
        System.out.println("Transactional commit!!");
    }

    private void rollback() {
        System.out.println("Transactional rollback!!");
    }
}

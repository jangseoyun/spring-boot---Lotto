package lotto.practice.random;

import lotto.practice.random.domain.Winner.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RandomApplication {

	@Autowired
	private WinnerService winningService;

	public static void main(String[] args) {
		SpringApplication.run(RandomApplication.class, args);
	}
}

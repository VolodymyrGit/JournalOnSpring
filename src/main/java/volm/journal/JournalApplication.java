package volm.journal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import volm.journal.model.User;
import volm.journal.repo.UserRepo;

@SpringBootApplication
public class JournalApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JournalApplication.class, args);

		UserRepo userRepo = context.getBean(UserRepo.class);

//		userRepo.save(new User(1L, "Some Name"));
	}
}

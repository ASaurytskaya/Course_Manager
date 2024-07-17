package by.digital_chief.course_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories
@EnableTransactionManagement
@SpringBootApplication
public class CourseManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseManagerApplication.class, args);
	}

}

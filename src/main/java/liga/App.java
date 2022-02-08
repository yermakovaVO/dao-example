package liga;

import java.util.Collections;
import liga.dao.EmployeeDAO;
import liga.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	EmployeeDAO dao;

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			dao.getAllEmployees();

			dao.deleteAll(Collections.singletonList(1));

			log.info(dao.count().toString());

			dao.getEmployeeById(2);

			Employee emp = new Employee();
			emp.setEmpName("John");
			emp.setAge(25);
			int rowsUpdated = dao.saveEmployee(emp);
			log.info("Save result: " + rowsUpdated + ", saved employee id: " + emp.getEmpId());

			emp.setEmpName("James");
			emp.setAge(35);
			dao.updateEmployee(emp);

//			log.info("Resulting employee id: " + dao.getEmployeeById(emp.getEmpId()).getEmpName());

			log.info(dao.count().toString());

			dao.deleteEmployeeById(10);

			dao.queryAllEmployee();

			dao.queryForListEmployee();

			dao.getEmployeeRowAsMap(1);

		};
	}

}



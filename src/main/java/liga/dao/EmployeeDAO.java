package liga.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import liga.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDAO {

	Employee getEmployeeById(Integer id);

	int saveEmployee(Employee employee);

	void updateEmployee(Employee employee);

	Integer count();

	void deleteEmployeeById(int empId);

	List<Employee> getAllEmployees();

	Integer deleteAll(Collection<Integer> ids);

	List<Employee> queryAllEmployee();

	Map<String, Object> getEmployeeRowAsMap(Integer id);

	List<String> queryForListEmployee();


}
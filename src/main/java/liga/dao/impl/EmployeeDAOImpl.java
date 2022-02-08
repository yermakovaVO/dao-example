package liga.dao.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import liga.dao.EmployeeDAO;
import liga.mapper.EmployeeMapper;
import liga.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class EmployeeDAOImpl implements EmployeeDAO {

	private final JdbcTemplate jdbcTemplate;

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	final String INSERT_QUERY = "insert into employee (name, age) values (?, ?)";

	final String UPDATE_QUERY = "update employee set age = ? where id = ?";

	final String DELETE_QUERY = "delete from employee where id = ?";

	final String COUNT_QUERY = "select count(*) from employee";

	final String GET_BY_ID_QUERY = "select * from employee where id = %d";

	final String GET_ALL = "select * from employee ";

	public EmployeeDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}


	public Integer count() {
		return jdbcTemplate.queryForObject(COUNT_QUERY, Integer.class);
	}

	public Employee getEmployeeById(Integer id) {
		return jdbcTemplate.queryForObject(String.format("select * from employee where id = %d", id), new EmployeeMapper());
	}

	public int saveEmployee(Employee employee) {
		return jdbcTemplate.update(INSERT_QUERY, employee.getEmpName(), employee.getAge());
	}

	public void updateEmployee(Employee employee) {
		int status = jdbcTemplate.update(UPDATE_QUERY, employee.getAge(),
				employee.getEmpId()
		);
		if (status != 0) {
			log.info("Employee data updated for ID " + employee.getEmpId());
		} else {
			log.info("No Employee found with ID " + employee.getEmpId());
		}
	}


	public List<Employee> getAllEmployees() {
		return jdbcTemplate.query(GET_ALL, new EmployeeMapper());
	}

	public List<Employee> queryAllEmployee() {
		String sql = "SELECT * FROM employee";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
	}

	public Map<String, Object> getEmployeeRowAsMap(Integer id) {
		return jdbcTemplate.queryForMap(String.format("select * from employee where id = %d", id), new EmployeeMapper());
	}

	public List<String> queryForListEmployee() {
		return jdbcTemplate.queryForList(String.format("select distinct name from employee"), String.class);
	}

	public void init() {
		jdbcTemplate.execute("create table employee (id int primary key, name varchar(255))");
	}

	public void deleteEmployeeById(int empId) {
		int status = jdbcTemplate.update(DELETE_QUERY, empId);
		if (status != 0) {
			log.info("Employee data deleted for ID " + empId);
		} else {
			log.info("No Employee found with ID " + empId);
		}
	}

	public Integer deleteAll(Collection<Integer> ids) {
		return namedParameterJdbcTemplate.update(
				"delete from employee where id in (:ids)", Collections.singletonMap("ids", ids));
	}
//в примере есть JDBC Operations, это интерфейс
}
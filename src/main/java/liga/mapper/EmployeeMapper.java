package liga.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import liga.model.Employee;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();

		employee.setEmpId(rs.getInt("ID"));
		employee.setEmpName(rs.getString("name"));
		employee.setEmpName(rs.getString("age"));
		return employee;
	}

}

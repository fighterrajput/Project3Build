package in.co.rays.project_3.model;

import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * ModelFactory decides which model implementation run
 * 
 * @author Ankit Rajput
 *
 */
public final class ModelFactory {

	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.project_3.bundle.system");
	private static final String DATABASE = rb.getString("DATABASE");
	private static ModelFactory mFactory = null;
	private static HashMap modelCache = new HashMap();

	private ModelFactory() {

	}

	public static ModelFactory getInstance() {
		if (mFactory == null) {
			mFactory = new ModelFactory();
		}
		return mFactory;
	}

	public MarksheetModelInt getMarksheetModel() {
		MarksheetModelInt marksheetModel = (MarksheetModelInt) modelCache.get("marksheetModel");
		if (marksheetModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				marksheetModel = new MarksheetModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				marksheetModel = new MarksheetModelJDBCImpl();
			}
			modelCache.put("marksheetModel", marksheetModel);
		}
		return marksheetModel;
	}

	public CollegeModelInt getCollegeModel() {
		CollegeModelInt collegeModel = (CollegeModelInt) modelCache.get("collegeModel");
		if (collegeModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				collegeModel = new CollegeModelHibImp();

			}
			if ("JDBC".equals(DATABASE)) {
				collegeModel = new CollegeModelJDBCImpl();
			}
			modelCache.put("collegeModel", collegeModel);
		}
		return collegeModel;
	}

	public RoleModelInt getRoleModel() {
		RoleModelInt roleModel = (RoleModelInt) modelCache.get("roleModel");
		if (roleModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				roleModel = new RoleModelHibImp();

			}
			if ("JDBC".equals(DATABASE)) {
				roleModel = new RoleModelJDBCImpl();
			}
			modelCache.put("roleModel", roleModel);
		}
		return roleModel;
	}

	public UserModelInt getUserModel() {

		UserModelInt userModel = (UserModelInt) modelCache.get("userModel");
		if (userModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				userModel = new UserModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				userModel = new UserModelJDBCImpl();
			}
			modelCache.put("userModel", userModel);
		}

		return userModel;
	}

	public StudentModelInt getStudentModel() {
		StudentModelInt studentModel = (StudentModelInt) modelCache.get("studentModel");
		if (studentModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				studentModel = new StudentModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				studentModel = new StudentModelJDBCImpl();
			}
			modelCache.put("studentModel", studentModel);
		}

		return studentModel;
	}

	public CourseModelInt getCourseModel() {
		CourseModelInt courseModel = (CourseModelInt) modelCache.get("courseModel");
		if (courseModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				courseModel = new CourseModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				courseModel = new CourseModelJDBCImpl();
			}
			modelCache.put("courseModel", courseModel);
		}

		return courseModel;
	}
	
	public PortfolioModelInt getPortfolioModel() {
		PortfolioModelInt portfolioModel = (PortfolioModelInt) modelCache.get("portfolioModel");
		if (portfolioModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				portfolioModel = new PortfolioModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				portfolioModel = new PortfolioModelHibImp();
			}
			modelCache.put("portfolioModel", portfolioModel);
		}

		return portfolioModel;
	}

	public TimetableModelInt getTimetableModel() {

		TimetableModelInt timetableModel = (TimetableModelInt) modelCache.get("timetableModel");

		if (timetableModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				timetableModel = new TimetableModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				timetableModel = new TimetableModelJDBCImpl();
			}
			modelCache.put("timetableModel", timetableModel);
		}

		return timetableModel;
	}

	public SubjectModelInt getSubjectModel() {
		SubjectModelInt subjectModel = (SubjectModelInt) modelCache.get("subjectModel");
		if (subjectModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				subjectModel = new SubjectModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				subjectModel = new SubjectModelJDBCImpl();
			}
			modelCache.put("subjectModel", subjectModel);
		}

		return subjectModel;
	}

	public FacultyModelInt getFacultyModel() {
		FacultyModelInt facultyModel = (FacultyModelInt) modelCache.get("facultyModel");
		if (facultyModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				facultyModel = new FacultyModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				facultyModel = new FacultyModelJDBCImpl();
			}
			modelCache.put("facultyModel", facultyModel);
		}

		return facultyModel;
	}

	public TransportationModelInt getTransportationModel() {

		TransportationModelInt transportModel = (TransportationModelInt) modelCache.get("transportModel");
		if (transportModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				transportModel = new TransportationModelHibImp();
			}

			modelCache.put("transportModel", transportModel);
		}

		return transportModel;
	}

	public AbcModelInt getAbcModel() {

		AbcModelInt abcModel = (AbcModelInt) modelCache.get("abcModel");
		if (abcModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				abcModel = new AbcModelHibImpl();
			}

			modelCache.put("abcModel", abcModel);
		}

		return abcModel;
	}

	public DeveloperModelInt getDeveloperModel() {

		DeveloperModelInt developerModel = (DeveloperModelInt) modelCache.get("developerModel");
		if (developerModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				developerModel = new DeveloperModelHibImp();
			}

			modelCache.put("developerModel", developerModel);
		}

		return developerModel;
	}

	public SystemModelInt getSystemModel() {

		SystemModelInt systemModel = (SystemModelInt) modelCache.get("systemModel");
		if (systemModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				systemModel = new SystemModelHibImp();
			}

			modelCache.put("systemModel", systemModel);
		}

		return systemModel;
	}

	public OrderModelInt getOrderModel() {

		OrderModelInt orderModel = (OrderModelInt) modelCache.get("orderModel");
		if (orderModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				orderModel = new OrderModelHibImp();
			}

			modelCache.put("orderModel", orderModel);
		}

		return orderModel;
	}

}

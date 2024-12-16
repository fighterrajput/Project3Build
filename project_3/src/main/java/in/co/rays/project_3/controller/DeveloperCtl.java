package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.DeveloperDTO;
import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.DeveloperModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "DeveloperCtl", urlPatterns = { "/DeveloperCtl" })
public class DeveloperCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request) {

		Map<Integer, String> map = new HashMap();
		map.put(1, "Internal");
		map.put(2, "Clients");

		request.setAttribute("modee", map);

	}

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "name"));
			pass = false;
		}
		
		else if (!DataValidator.isLetter(request.getParameter("name"))) {
			request.setAttribute("name", "only letters are allowed");
			pass = false;

		}
		 else if (DataValidator.isTooLong(request.getParameter("name"),20)) {
				request.setAttribute("name", "only 20 characters are allowed.");
				pass = false;
			}
		
		if (DataValidator.isNull(request.getParameter("tech"))) {
			request.setAttribute("tech", PropertyReader.getValue("error.require", "tech"));
			pass = false;
		}
		
		else if (!DataValidator.isLetter(request.getParameter("tech"))) {
			request.setAttribute("tech", "only letters are allowed");
			pass = false;

		} else if (DataValidator.isTooLong(request.getParameter("tech"),20)) {
			request.setAttribute("tech", "only 20 characters are allowed.");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("projectNumber"))) {
			request.setAttribute("projectNumber", PropertyReader.getValue("error.require", "projectNumber"));
			pass = false;
		}

		else if (!DataValidator.isInteger(request.getParameter("projectNumber"))) {
			request.setAttribute("projectNumber", "only numbers are allowed.");
			pass = false;
		} else if (DataValidator.isTooLong(request.getParameter("projectNumber"),8)) {
			request.setAttribute("projectNumber", "only 8 digits are allowed");
			pass = false;
		}
		

		if (DataValidator.isNull(request.getParameter("type"))) {
			request.setAttribute("type", PropertyReader.getValue("error.require", "type"));
			pass = false;
		} 
		
		if (DataValidator.isNull(request.getParameter("date"))) {
			request.setAttribute("date", PropertyReader.getValue("error.require", "date"));

			pass = false;

		}
	
		  else if (!DataValidator.isValidDate(request.getParameter("date"))) {
		  request.setAttribute("date", PropertyReader.getValue("User can only enter past or present date", "date"));
		  System.out.println(request.getParameter("date"));
		  pass = false; }
		 
		
		return pass;
	
		

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		DeveloperDTO dto = new DeveloperDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setName(DataUtility.getString(request.getParameter("name")));
		dto.setTech(DataUtility.getString(request.getParameter("tech")));
		dto.setProjectNumber(DataUtility.getLong(request.getParameter("projectNumber")));
		dto.setType(DataUtility.getString(request.getParameter("type")));

		populateBean(dto, request);

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		DeveloperModelInt model = ModelFactory.getInstance().getDeveloperModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			DeveloperDTO dto;
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (Exception e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		DeveloperModelInt model = ModelFactory.getInstance().getDeveloperModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			DeveloperDTO dto = (DeveloperDTO) populateDTO(request);
			try {
				if (id > 0) {
					model.update(dto);

					ServletUtility.setSuccessMessage("Developer is successfully Updated", request);
				} else {

					try {
						model.add(dto);
						ServletUtility.setDto(dto, request);
						ServletUtility.setSuccessMessage("Developer is successfully saved", request);
					} catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						return;
					} catch (DuplicateRecordException e) {
						ServletUtility.setDto(dto, request);
						ServletUtility.setErrorMessage("Login id already exists", request);
					}

				}
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			DeveloperDTO dto = (DeveloperDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.DEVELOPER_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.DEVELOPER_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.DEVELOPER_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.DEVELOPER_VIEW;
	}

}

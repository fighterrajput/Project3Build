package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.AbcDTO;
import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.AbcModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "AbcCtl", urlPatterns = { "/ctl/AbcCtl" })

public class AbcCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request) {

		Map<Integer, String> map = new HashMap();
		map.put(1, "Cash");
		map.put(2, "Online");

		request.setAttribute("modee", map);

	}

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "description"));
			pass = false;
		}
		
		else if (!DataValidator.isLetter(request.getParameter("description"))) {
			request.setAttribute("description", "only letters are allowed");
			pass = false;

		} else if (DataValidator.isTooLong(request.getParameter("description"),100)) {
			request.setAttribute("description", "only 100 characters are allowed.");
			pass = false;
		}
		
		
		
		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "name"));
			pass = false;
		}
		
		else if (!DataValidator.isLetter(request.getParameter("name"))) {
			request.setAttribute("name", "only letters are allowed");
			pass = false;

		} else if (DataValidator.isTooLong(request.getParameter("name"),20)) {
			request.setAttribute("name", "only 20 characters are allowed.");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("cost"))) {
			request.setAttribute("cost", PropertyReader.getValue("error.require", "cost"));
			pass = false;
		}

		else if (!DataValidator.isInteger(request.getParameter("cost"))) {
			request.setAttribute("cost", "only numbers are allowed.");
			pass = false;
		} else if (DataValidator.isTooLong(request.getParameter("cost"),8)) {
			request.setAttribute("cost", "only 8 digits are allowed");
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("phoneNumber"))) {
			request.setAttribute("phoneNumber", PropertyReader.getValue("error.require", "phoneNumber"));
			pass = false;
		}

		else if (!DataValidator.isLong(request.getParameter("phoneNumber"))) {
			request.setAttribute("phoneNumber", "only numbers are allowed.");
			pass = false;
		} else if (DataValidator.isTooLong(request.getParameter("phoneNumber"),10)) {
			request.setAttribute("phoneNumber", "only 10 digits are allowed");
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
		AbcDTO dto = new AbcDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setDescription(DataUtility.getString(request.getParameter("description")));
		dto.setName(DataUtility.getString(request.getParameter("name")));
		dto.setType(DataUtility.getString(request.getParameter("type")));
		dto.setCost(DataUtility.getInt(request.getParameter("cost")));
		dto.setDate(DataUtility.getDate(request.getParameter("date")));
		dto.setPhoneNumber(DataUtility.getLong(request.getParameter("phoneNumber")));

		populateBean(dto, request);

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		AbcModelInt model = ModelFactory.getInstance().getAbcModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			AbcDTO dto;
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
		AbcModelInt model = ModelFactory.getInstance().getAbcModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			AbcDTO dto = (AbcDTO) populateDTO(request);
			try {
				if (id > 0) {
					model.update(dto);

					ServletUtility.setSuccessMessage("ABC is successfully Updated", request);
				} else {

					try {
						model.add(dto);
						ServletUtility.setDto(dto, request);
						ServletUtility.setSuccessMessage("ABC is successfully saved", request);
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

			AbcDTO dto = (AbcDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.ABC_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.ABC_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.ABC_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.ABC_VIEW;
	}

}

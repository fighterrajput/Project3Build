package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.SystemDTO;
import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.SystemModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "SystemCtl", urlPatterns = { "/ctl/SystemCtl" })

public class SystemCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request) {

		Map<Integer, String> map = new HashMap();
		map.put(1, "Phone");
		map.put(2, "Laptop");

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

		} else if (DataValidator.isTooLong(request.getParameter("name"),20)) {
			request.setAttribute("name", "only 20 characters are allowed.");
			pass = false;
		}

		
		if (DataValidator.isNull(request.getParameter("serialNumber"))) {
			request.setAttribute("serialNumber", PropertyReader.getValue("error.require", "serialNumber"));
			pass = false;
		}

		else if (!DataValidator.isLong(request.getParameter("serialNumber"))) {
			request.setAttribute("serialNumber", "only numbers are allowed.");
			pass = false;
		} else if (DataValidator.isTooLong(request.getParameter("serialNumber"),10)) {
			request.setAttribute("serialNumber", "only 10 digits are allowed");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("type"))) {
			request.setAttribute("type", PropertyReader.getValue("error.require", "type"));
			pass = false;
		} 
		
	


		if (DataValidator.isNull(request.getParameter("purchaseDate"))) {
			request.setAttribute("purchaseDate", PropertyReader.getValue("error.require", "purchaseDate"));

			pass = false;

		}
	
		  else if (!DataValidator.isValidDate(request.getParameter("purchaseDate"))) {
		  request.setAttribute("purchaseDate", PropertyReader.getValue("User can only enter past or present date", "purchaseDate"));
		  System.out.println(request.getParameter("purchaseDate"));
		  pass = false; }
		 
		
		return pass;
	
		

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		SystemDTO dto = new SystemDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setName(DataUtility.getString(request.getParameter("name")));
		dto.setType(DataUtility.getString(request.getParameter("type")));
		dto.setSerialNumber(DataUtility.getLong(request.getParameter("serialNumber")));
		dto.setPurchaseDate(DataUtility.getDate(request.getParameter("purchaseDate")));

		populateBean(dto, request);

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		SystemModelInt model = ModelFactory.getInstance().getSystemModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			SystemDTO dto;
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
		SystemModelInt model = ModelFactory.getInstance().getSystemModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			SystemDTO dto = (SystemDTO) populateDTO(request);
			try {
				if (id > 0) {
					model.update(dto);

					ServletUtility.setSuccessMessage("System is successfully Updated", request);
				} else {

					try {
						model.add(dto);
						ServletUtility.setDto(dto, request);
						ServletUtility.setSuccessMessage("System is successfully saved", request);
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

			SystemDTO dto = (SystemDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.SYSTEM_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.SYSTEM_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.SYSTEM_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.SYSTEM_VIEW;
	}

}

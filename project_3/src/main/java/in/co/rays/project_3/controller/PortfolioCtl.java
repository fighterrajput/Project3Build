package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.PortfolioDTO;
import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.PortfolioModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "PortfolioCtl", urlPatterns = { "/ctl/PortfolioCtl" })

public class PortfolioCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request) {

		Map<Integer, String> map = new HashMap();
		map.put(1, "Low");
		map.put(2, "Midium");
		map.put(3, "High");

		request.setAttribute("modee", map);

	}

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "name"));
			pass = false;
		} else if (DataValidator.isTooLong(request.getParameter("name"), 30)) {
			request.setAttribute("name", "only 3 characters are allowed.");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("amount"))) {
			request.setAttribute("amount", PropertyReader.getValue("error.require", "amount"));
			pass = false;
		}

		else if (!DataValidator.isInteger(request.getParameter("amount"))) {
			request.setAttribute("amount", "only numbers are allowed.");
			pass = false;
		} else if (DataValidator.isTooLong(request.getParameter("amount"), 8)) {
			request.setAttribute("amount", "only 8 digits are allowed");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("level"))) {
			request.setAttribute("level", PropertyReader.getValue("error.require", "level"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("strategy"))) {
			request.setAttribute("strategy", PropertyReader.getValue("error.require", "strategy"));
			pass = false;
		}

		else if (!DataValidator.isLetter(request.getParameter("strategy"))) {
			request.setAttribute("strategy", "only letters are allowed");
			pass = false;

		} else if (DataValidator.isTooLong(request.getParameter("strategy"), 200)) {
			request.setAttribute("strategy", "only 200 characters are allowed.");
			pass = false;
		}

		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		PortfolioDTO dto = new PortfolioDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setName(DataUtility.getString(request.getParameter("name")));
		dto.setAmount(DataUtility.getInt(request.getParameter("amount")));
		dto.setLevel(DataUtility.getString(request.getParameter("level")));
		dto.setStrategy(DataUtility.getString(request.getParameter("strategy")));

		populateBean(dto, request);

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		PortfolioModelInt model = ModelFactory.getInstance().getPortfolioModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			PortfolioDTO dto;
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
		PortfolioModelInt model = ModelFactory.getInstance().getPortfolioModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			PortfolioDTO dto = (PortfolioDTO) populateDTO(request);
			try {
				if (id > 0) {
					model.update(dto);

					ServletUtility.setSuccessMessage("Portfolio is successfully Updated", request);
				} else {

					try {
						model.add(dto);
						ServletUtility.setDto(dto, request);
						ServletUtility.setSuccessMessage("Portfolio is successfully saved", request);
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

			PortfolioDTO dto = (PortfolioDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.PORTFOLIO_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.PORTFOLIO_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.PORTFOLIO_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.PORTFOLIO_VIEW;
	}

}
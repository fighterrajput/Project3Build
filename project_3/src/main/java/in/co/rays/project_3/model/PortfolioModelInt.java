package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.PortfolioDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface PortfolioModelInt {

	public long add(PortfolioDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(PortfolioDTO dto) throws ApplicationException;

	public void update(PortfolioDTO dto) throws ApplicationException, DuplicateRecordException;

	public PortfolioDTO findByPK(long pk) throws ApplicationException;

	public PortfolioDTO findByLogin(String login) throws ApplicationException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(PortfolioDTO dto, int pageNo, int pageSize) throws ApplicationException;

	public List search(PortfolioDTO dto) throws ApplicationException;

	public List getRoles(PortfolioDTO dto) throws ApplicationException;

}
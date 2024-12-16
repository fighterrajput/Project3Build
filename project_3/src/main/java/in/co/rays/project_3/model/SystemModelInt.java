package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.SystemDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface SystemModelInt {
	
	public long add(SystemDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(SystemDTO dto)throws ApplicationException;
	public void update(SystemDTO dto)throws ApplicationException,DuplicateRecordException;
	public SystemDTO findByPK(long pk)throws ApplicationException;
	public SystemDTO findByLogin(String login)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(SystemDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search(SystemDTO dto)throws ApplicationException;
	public List getRoles(SystemDTO dto)throws ApplicationException;
	



}

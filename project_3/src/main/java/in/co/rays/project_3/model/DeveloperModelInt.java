package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.DeveloperDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface DeveloperModelInt {
	
	public long add(DeveloperDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(DeveloperDTO dto)throws ApplicationException;
	public void update(DeveloperDTO dto)throws ApplicationException,DuplicateRecordException;
	public DeveloperDTO findByPK(long pk)throws ApplicationException;
	public DeveloperDTO findByLogin(String login)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(DeveloperDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search(DeveloperDTO dto)throws ApplicationException;
	public List getRoles(DeveloperDTO dto)throws ApplicationException;
	



}

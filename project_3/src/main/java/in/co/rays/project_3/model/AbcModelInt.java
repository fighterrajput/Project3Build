package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.AbcDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface AbcModelInt {
	
	public long add(AbcDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(AbcDTO dto)throws ApplicationException;
	public void update(AbcDTO dto)throws ApplicationException,DuplicateRecordException;
	public AbcDTO findByPK(long pk)throws ApplicationException;
	public AbcDTO findByLogin(String login)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(AbcDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search(AbcDTO dto)throws ApplicationException;
	public List getRoles(AbcDTO dto)throws ApplicationException;
	



}

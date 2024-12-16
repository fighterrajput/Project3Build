package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.AbcDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class AbcModelHibImpl implements AbcModelInt {
	
	@Override
	public long add(AbcDTO dto) throws ApplicationException, DuplicateRecordException {
		
		 AbcDTO existDto = null;
			
			Session session = HibDataSource.getSession();
			Transaction tx = null;
			try {

				tx = session.beginTransaction();

				session.save(dto);

				dto.getId();
				tx.commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				if (tx != null) {
					tx.rollback();

				}
				throw new ApplicationException("Exception in Abc Add " + e.getMessage());
			} finally {
				session.close();
			}


		return dto.getId();
	}

	@Override
	public void delete(AbcDTO dto) throws ApplicationException {

		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Abc Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(AbcDTO dto) throws ApplicationException, DuplicateRecordException {
		
		
		Session session = null;
		
		/*
		 * Transaction tx = null; AbcDTO exesistDto = findByLogin(dto.getLogin());
		 * 
		 * if (exesistDto != null && exesistDto.getId() != dto.getId()) { throw new
		 * DuplicateRecordException("Login id already exist"); }
		 * 
		 */		  Transaction tx = null;
		 

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Abc update" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public AbcDTO findByPK(long pk) throws ApplicationException {
		
		
		Session session = null;
		AbcDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (AbcDTO) session.get(AbcDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Bank by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public AbcDTO findByLogin(String login) throws ApplicationException {
		
		
		
		Session session = null;
		AbcDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(AbcDTO.class);
			criteria.add(Restrictions.eq("login", login));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (AbcDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting Abc by Login " + e.getMessage());

		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public List list(int pageNo, int pageSize) throws ApplicationException {
		
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(AbcDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Banks list");
		} finally {
			session.close();
		}

		return list;
	}

	/*
	 * @Override public List list(int pageNo, int pageSize) throws
	 * ApplicationException { // TODO Auto-generated method stub return null; }
	 */
	@Override
	public List search(AbcDTO dto, int pageNo, int pageSize) throws ApplicationException {
		
		Session session = null;
		ArrayList<AbcDTO> list = null;
		try {
			session = HibDataSource.getSession();
			System.out.println("---------------------------------");
			Criteria criteria = session.createCriteria(AbcDTO.class);
			if (dto != null) {
				
				if (dto.getId() != null && dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				
				if (dto.getCost() > 0) {
					criteria.add(Restrictions.eq("cost", dto.getCost()));
				}
				 
				if ( dto.getPhoneNumber() > 0) {
					criteria.add(Restrictions.eq("phoneNumber", dto.getPhoneNumber()));
				}
				  if (dto.getDescription() != null && dto.getDescription().length() > 0) {
					  criteria.add(Restrictions.like("description", dto.getDescription() + "%"));
					  }
				  
				  if (dto.getDate() != null && dto.getDate().getTime() > 0) {
						criteria.add(Restrictions.eq("date", dto.getDate()));
					}
				  
				  if (dto.getType() != null && dto.getType().length() > 0) {
					  criteria.add(Restrictions.like("type", dto.getType() + "%"));
					  }
				  
				  if (dto.getName() != null && dto.getName().length() > 0) {
					  criteria.add(Restrictions.like("name", dto.getName() + "%"));
					  }
			 


			    
			  
				
					
					
			}
					
					if (pageSize > 0) {
						pageNo = (pageNo - 1) * pageSize;
						criteria.setFirstResult(pageNo);
						criteria.setMaxResults(pageSize);
					}
					list = (ArrayList<AbcDTO>) criteria.list();
				} catch (HibernateException e) {
					throw new ApplicationException("Exception in Abc search");
				} finally {
					session.close();
				}

		
		return list;
	}

	@Override
	public List search(AbcDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto,0,0);
	}

	@Override
	public List getRoles(AbcDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0,0);
	}


}

package edu.mssm.pharm.maayanlab.Harmonizome.dal;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

public class GenericDAO {

	@SuppressWarnings("unchecked")
	public static List<String> getSuggestions(String table, String field, String query) {
		String sql = String.format("SELECT %s FROM %s WHERE %s SOUNDS LIKE '%s'", field, table, field, query);		
		return (List<String>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.list();
	}

	@SuppressWarnings("unchecked")
	public static <E> List<E> getBySubstringInField(Class<E> klass, String table, String field, String query) {
		String sql = String.format("SELECT * FROM %s WHERE MATCH(%s) AGAINST('%s*' IN BOOLEAN MODE)", table, field, query);		
		return (List<E>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.addEntity(klass)
			.list();
	}
	
	@SuppressWarnings("unchecked")
	public static <E> List<E> getBySubstringInFieldButIgnoreId(Class<E> klass, String table, String field, String query, int id) {
		String sql = String.format("SELECT * FROM %s WHERE MATCH(%s) AGAINST('%s*' IN BOOLEAN MODE) AND id != %s", table, field, query, id);		
		return (List<E>) HibernateUtil
			.getCurrentSession()
			.createSQLQuery(sql)
			.addEntity(klass)
			.list();
	}

	public static <E> Long getCount(Class<E> klass) {
		Criteria criteria = HibernateUtil
			.getCurrentSession()
			.createCriteria(klass);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
}
package edu.mssm.pharm.maayanlab.Harmonizome.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.mssm.pharm.maayanlab.Harmonizome.dal.GenericDao;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Attribute;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Dataset;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Gene;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Resource;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Returns a list of keywords based on query. This method is not part of the
 * documented API.
 */
@WebServlet(urlPatterns = { "/" + Constant.API_URL + "/" + Constant.SUGGEST_URL + "/*" })
public class SuggestApi extends HttpServlet {

	private static final long serialVersionUID = 778955897675398125L;

	private static final int MAX_ATTRIBUTES_TO_SUGGEST = 100;

	private static final Gson gson;
	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("q");
		String type = request.getParameter("t");
		
		Set<String> suggestions = new HashSet<String>();
		
		try {
			HibernateUtil.beginTransaction();

			List<String> attributeSuggestions = null;
			boolean searchAll = (type == null || type.equals("all"));
			
			if (searchAll || type.equals("gene")) {
				suggestions.addAll(GenericDao.getFromPrefix(Gene.class, query));
			}
			if (searchAll || type.equals("dataset")) {
				suggestions.addAll(GenericDao.getFromPrefix(Dataset.class, query));
				suggestions.addAll(GenericDao.getFromWordInField(Dataset.class, query));
				suggestions.addAll(GenericDao.getFromPrefix(Resource.class, query));
				suggestions.addAll(GenericDao.getFromWordInField(Resource.class, query));
			}
			if (searchAll || type.equals("geneSet")) {
				attributeSuggestions = GenericDao.getFromPrefix(Attribute.class, query);
			}
			
			// There are a lot of attributes. Don't suggest everything.
			if (attributeSuggestions != null) {
				if (attributeSuggestions.size() > MAX_ATTRIBUTES_TO_SUGGEST) {
					attributeSuggestions = attributeSuggestions.subList(0, MAX_ATTRIBUTES_TO_SUGGEST);
				}
				suggestions.addAll(attributeSuggestions);
			}
			
			PrintWriter out = response.getWriter();
			String json = gson.toJson(suggestions);
			out.write(json);
			out.flush();
			
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			he.printStackTrace();
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.close();
		}
	}
}
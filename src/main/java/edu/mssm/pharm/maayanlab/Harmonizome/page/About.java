package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import edu.mssm.pharm.maayanlab.Harmonizome.dal.GeneralDAO;
import edu.mssm.pharm.maayanlab.Harmonizome.model.Resource;
import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;
import edu.mssm.pharm.maayanlab.common.database.HibernateUtil;

@WebServlet(urlPatterns = { "/about" })
public class About extends HttpServlet {

	private static final long serialVersionUID = -4641583897568170335L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Resource> resources = null;
		try {
			HibernateUtil.beginTransaction();
			resources = GeneralDAO.getAllResources();
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		request.setAttribute("resources", resources);
		request.getRequestDispatcher(Constant.TEMPLATE_DIR + "about.jsp").forward(request, response);
	}
}
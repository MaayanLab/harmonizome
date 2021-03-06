package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mssm.pharm.maayanlab.Harmonizome.model.NamingAuthority;

@WebServlet(urlPatterns = { "/" + NamingAuthority.ENDPOINT + "/*" })
public class NamingAuthorityPage extends HttpServlet {

	private static final long serialVersionUID = -3138571504349534938L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BioEntityPage.doGet(request, response, NamingAuthority.class);
	}
}
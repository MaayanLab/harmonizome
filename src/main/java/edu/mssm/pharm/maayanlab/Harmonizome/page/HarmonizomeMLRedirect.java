package edu.mssm.pharm.maayanlab.Harmonizome.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.mssm.pharm.maayanlab.Harmonizome.util.Constant;

@WebServlet(urlPatterns = { "/Harmonizome-ML", "/Harmonizome-ML/*" })
public class HarmonizomeMLRedirect extends HttpServlet {

	private static final long serialVersionUID = -4641583897568170335L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(Constant.HARMONIZOME_ML_URL());
	}
}

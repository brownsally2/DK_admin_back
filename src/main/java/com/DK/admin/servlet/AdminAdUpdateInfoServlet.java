package com.DK.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.DK.admin.common.Common;
import com.DK.admin.dao.AdminAdDAO;
import com.DK.admin.vo.AdminAdVO;


@WebServlet("/AdminUpdateInfoServlet")
public class AdminAdUpdateInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	         throws ServletException, IOException {
	      response.getWriter().append("Served at: ").append(request.getContextPath());
	   }

	   // CORS 처리
	   protected void doOptions(HttpServletRequest request, HttpServletResponse response) 
	         throws ServletException, IOException {
	      Common.corsResSet(response);
	   }
	   
	   @SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	         throws ServletException, IOException {
	     
	      request.setCharacterEncoding("utf-8");
	      Common.corsResSet(response);
	      StringBuffer sb = Common.reqStringBuff(request);
	      JSONObject jsonObj = Common.getJsonObj(sb);
	      
	      String getAd_num = (String)jsonObj.get("ad_num");
	      
	      AdminAdDAO dao = new AdminAdDAO();
//	      dao.AdUpdateInfo(getAd_num);
	      System.out.println("전달 받은 번호 : " + getAd_num );
	      
	      PrintWriter out = response.getWriter();
			JSONObject resJson = new JSONObject();
//			if(rstComplete) resJson.put("result", "OK");
//			else resJson.put("result", "NOK");
			out.print(resJson);
	      
//
	      
	      
	      List<AdminAdVO> list = dao.AdUpdateInfo(getAd_num);
	      
	      JSONArray adArray = new JSONArray();
	      for(AdminAdVO e : list) {
	         JSONObject adminAdUpdateInfo = new JSONObject();
	         // 리액트에서의 MemberInfo.js에서 "id"로해서 소문자 id로 해야 함
//	         adInfo.put("ad_num", e.getAd_num());
	         adminAdUpdateInfo.put("ad_name", e.getAd_name());
	         adminAdUpdateInfo.put("ad_url", e.getAd_url());
//	         adInfo.put("ad_img", e.getAd_img());
	         adArray.add(adminAdUpdateInfo);
	      }
	      System.out.println(adArray);
	      out.print(adArray);
	   }
}

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

@WebServlet("/AdminAdUpdateServlet")
public class AdminAdUpdateServlet extends HttpServlet {
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
	
	   protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		         throws ServletException, IOException {
		      // 한글 깨짐 방지를 위해서 설정
		      request.setCharacterEncoding("utf-8");
		      // CORS 접근 허용
		      Common.corsResSet(response);
		      // 요청 메시지 받기
		      StringBuffer sb = Common.reqStringBuff(request);
		      // 요청 받은 메시지 JSON 파싱
		      JSONObject jsonObj = Common.getJsonObj(sb);
		      
			  String getAd_num = (String)jsonObj.get("ad_num");
			  String getAd_name = (String)jsonObj.get("ad_name");
			  String getAd_url = (String)jsonObj.get("ad_url");
//			  String setAd_img = (String)jsonObj.get("ad_img");
			  
			  
			  AdminAdDAO dao = new AdminAdDAO();
			  dao.adminAdUpdate(getAd_num, getAd_name, getAd_url);
			  
			// request에 대한 response를 출력
				PrintWriter out = response.getWriter();
				JSONObject resJson = new JSONObject();
//				if(rstComplete) resJson.put("result", "OK");
//				else resJson.put("result", "NOK");
				out.print(resJson);
	 
	   
		// 삭제 후 select list를 불러줌 
				List<AdminAdVO> list = dao.AdSelect();
				JSONArray adArray = new JSONArray();
		
		// 삭제 후 리스트틀 다시 보내줌 
				for(AdminAdVO e : list) {
				 JSONObject adInfo = new JSONObject();
			   	 adInfo.put("ad_num", e.getAd_num());
		         adInfo.put("ad_name", e.getAd_name());
		         adInfo.put("ad_url", e.getAd_url());
		         adInfo.put("ad_img", e.getAd_img());
		         adArray.add(adInfo);
	   }
		out.print(adArray);
	   }
}


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

@WebServlet("/AdminADNotiSendServlet")
public class AdminADNotiSendServlet extends HttpServlet {
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
	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			Common.corsResSet(response);
			StringBuffer sb = Common.reqStringBuff(request);
			JSONObject jsonObj = Common.getJsonObj(sb);
		
			// 구별자 받아오기
			String regCmd = (String)jsonObj.get("inputStatus");
			String regCmd2 = (String)jsonObj.get("inputStatus2");
			// AdminAdDAO에 변환한 regCmd를 넣어줌
			AdminAdDAO dao = new AdminAdDAO();
			if(regCmd.equals("true") && regCmd2.equals("false")) {
				dao.AdNotiSend1(regCmd);
			}else {
				dao.AdNotiSend2(regCmd2);
			}
			
			PrintWriter out = response.getWriter();
			if(regCmd.equals("true")&&regCmd2.equals("false")) {
				List<AdminAdVO> list = dao.AdNotiSend1(regCmd);
				JSONArray adArray = new JSONArray();
				for(AdminAdVO e1 : list) {
					JSONObject notiSend1 = new JSONObject();
						notiSend1.put("nickName", e1.getNickName());
						notiSend1.put("email", e1.getEmail());
				        adArray.add(notiSend1);
			}
				out.print(adArray);
				
			}else {
				List<AdminAdVO> list = dao.AdNotiSend2(regCmd);
				JSONArray adArray = new JSONArray();
			for(AdminAdVO e1 : list) {
				JSONObject notiSend2 = new JSONObject();
				notiSend2.put("nickName", e1.getNickName());
				notiSend2.put("email", e1.getEmail());
		        adArray.add(notiSend2);
	}
		out.print(adArray);
			
	   }
	 }
}


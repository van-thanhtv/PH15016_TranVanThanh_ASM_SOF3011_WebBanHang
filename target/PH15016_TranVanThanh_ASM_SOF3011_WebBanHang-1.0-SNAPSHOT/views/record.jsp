<%@ page import="Dao.SizeDao" %>
<%@ page import="entitys.SizeEntity" %><%--
  Created by IntelliJ IDEA.
  User: thanhkali
  Date: 4/8/22
  Time: 4:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String countries[] = {
            "Afghanistan",
            "Albania",
            "Algeria",
            "Andorra",
            "Angola",
            "Antigua and Barbuda",
            "Argentina",
            "Armenia"
    };

    String query = (String)request.getParameter("q");
    //System.out.println("1"+request.getParameterNames().nextElement());
    response.setHeader("Content-Type", "text/html");
    int cnt=1;
    for(int i=0;i<countries.length;i++)
    {
    if(countries[i].toUpperCase().startsWith(query.toUpperCase()))
    {
        System.out.println(countries[i]+"\n");
    if(cnt>=10)
    break;
    cnt++;
    }
    }
%>

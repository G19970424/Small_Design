package cn.com.small_design.common;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author gejj
 * @create 2024年03月25日 15:24
 * @version 1.0
 */
public abstract class JSONAuthentication {

    /**
     * 输出JSON格式数据
     * @param httpServletRequest
     * @param httpServletResponse
     * @param obj
     * @throws IOException
     * @throws ServletException
     */
    protected void WriteJSON(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object obj) throws IOException, ServletException {
        //设置编码格式
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //处理跨域问题
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET");

        //输出JSON
        PrintWriter out = httpServletResponse.getWriter();
        out.write(JSON.toJSONString(obj));
        out.flush();
        out.close();
    }
}

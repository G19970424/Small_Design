package cn.com.small_design.common.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author gejj
 * @create 2024年04月26日 10:46
 * @version 1.0
 */
@Component
public class IpInfoUtil {

    /**
     * 获取客户真实ip地址
     * @param request
     * @return
     */
    public String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forward-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP-CLIENT-IP");
        }

        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP-X-FORWARDED-FOR");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1")){
                //根据网卡获取本机配置的ip
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }

        //对于多个代理
        if( ip != null && ip.length() > 15){
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }

        if("0.0.0.0.0.0.0.1".equals(ip)){
            ip = "127.0.0.1";
        }
        return ip;
    }
}

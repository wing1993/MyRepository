package com.df.action.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.dao.pojo.Mail;
import com.df.dao.pojo.User;
import com.df.service.iservice.IUserService;
import com.opensymphony.xwork2.ModelDriven;

@Controller("findPwdAction")
@Scope("prototype")
public class FindPwdAction implements ModelDriven<User>, RequestAware,ServletRequestAware {

	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	// 1-取值（自动取值，自动类型转换，自动封装成对象供方法使用 --ModelDriven接口 ）
	private User user;
	private Map<String, Object> requestMap;
	HttpServletResponse response = ServletActionContext.getResponse(); 
	private HttpServletRequest request;
	private List<User> u=new ArrayList<User>();
	private String msg="error";
	private String sid;
	
	/**
	 * 发送邮件
	 * @return
	 */
	public String sendmail() {
		response.setContentType("text/html;charset=UTF-8");
        try {
            u=userService.findByMail(user);
            if (u.size() > 0) {

                Mail mail = new Mail();

                String secretKey = UUID.randomUUID().toString(); // 密钥
                Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);// 30分钟后过期
                long date = outDate.getTime() / 1000 * 1000;// 忽略毫秒数  mySql 取出时间是忽略毫秒数的
                
                System.out.println(u);
                u.get(0).setCon3(outDate+"");
                u.get(0).setCon4(secretKey);
                System.out.println("要更新的用户信息"+u.get(0).toString());
                userService.update(u.get(0));

                String key =u.get(0).getUsername() + "$" + date + "$" + secretKey;
                System.out.println(" key>>>"+key);
                //String digitalSignature = Md5.md5(key);// 数字签名

                String path = this.getRequest().getContextPath();
                String basePath = this.getRequest().getScheme() + "://"
                        + this.getRequest().getServerName() + ":"
                        + this.getRequest().getServerPort() + path + "/";
                String resetPassHref = basePath + "checkLink.action?sid="
                        + key +"&username="+u.get(0).getUsername() + "&userId=" + u.get(0).getUserId();
                String emailContent = "请勿回复本邮件.点击下面的链接,重设密码<br/><a href="
                        + resetPassHref + " target='_blank'>" + resetPassHref
                        + "</a>  或者    <a href=" + resetPassHref
                        + " target='_BLANK'>点击我重新设置密码</a>"
                        + "<br/>tips:本邮件超过30分钟,链接将会失效，需要重新申请'找回密码'" + key
                        + "\t" + key;

                mail.setTo(user.getMail());
                mail.setFrom("bujifeiyu@163.com");// 你的邮箱
                mail.setHost("smtp.163.com");
                mail.setUsername("bujifeiyu@163.com");// 用户
                mail.setPassword("hardship");// 密码
                mail.setSubject("[二维码名片]找回您的账户密码");
                mail.setContent(emailContent);
                if (mail.sendMail()) {
                    System.out.println(" 发送成功");
                    //this.getRequest().setAttribute("mesg", "重置密码邮件已经发送，请登陆邮箱进行重置！");
                    response.getWriter().print("重置密码邮件已经发送，请登陆邮箱进行重置！");
                    return null;
                }
            } else {
                //this.getRequest().setAttribute("mesg", "用户名不存在,你不会忘记邮箱了吧?");
            	response.getWriter().print("用户名不存在,你不会忘记邮箱了吧?");
                return null;
            }
        } catch (Exception e) {
            // TODO: handle exception 
            e.printStackTrace();
        }
        return null;
    }
	
	/**
	 * 验证链接是否有效
	 * @return
	 */
	public String checkResetLink() {
        System.out.println("sid>>>" + sid);

        if (sid.equals("")  || user.getUsername().equals("") || null==user.getUsername()) {
            this.getRequest().setAttribute("mesg", "链接不完整,请重新生成");
            System.out.println(">>>>> null");
            return "error";
        }
        u.clear();
        u.add(userService.getById(user.getUserId()));
        System.out.println(u.get(0).toString());
        if (u.size()>0) {
            
            Timestamp outDate = Timestamp.valueOf(u.get(0).getCon3());
            System.out.println("outDate>>>"+outDate);
             if(outDate.getTime() <= System.currentTimeMillis()){ //表示已经过期
                 this.getRequest().setAttribute("mesg", "链接已经过期,请重新申请找回密码.");
                 System.out.println("时间 超时");
                 return "error";
             }
             
             String key = u.get(0).getUsername()+"$"+outDate.getTime()/1000*1000+"$"+u.get(0).getCon4();//数字签名
            
             System.out.println("key link》》"+key);
             //String digitalSignature = Md5.md5(key);// 数字签名
             
             System.out.println("key>>>>"+key);
              if(!key.equals(sid)) {
                  this.getRequest().setAttribute("mesg", "链接不正确,是否已经过期了?重新申请吧.");
                      System.out.println("标示不正确");
                  return "error";
              }else {
                //链接验证通过 转到修改密码页面
                this.getRequest().setAttribute("user", u.get(0));
                return "success";
            }
        }else {
            this.getRequest().setAttribute("mesg", "链接错误,无法找到匹配用户,请重新申请找回密码.");
            System.out.println("用户不存在");
            return "error";
        }
    }
	
	/**
	 * 修改密码
	 * @return
	 * @throws IOException 
	 */
	public String changeKey() throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		u.clear();
		try {
			u.add(userService.getById(user.getUserId()));
			u.get(0).setPassword(user.getPassword());
			System.out.println("修改密码的用户信息："+u.get(0).toString());
			userService.update(u.get(0));
			msg="success";
		} catch (Exception e) {
			// TODO: handle exception
		}
		response.getWriter().print(msg);
		return null;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, Object> getRequestMap() {
		return requestMap;
	}

	public void setRequestMap(Map<String, Object> requestMap) {
		this.requestMap = requestMap;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap=arg0;

	}

	@Override
	public User getModel() {
		user=new User();
		return user;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		 this.request = arg0;		
	}
	private HttpServletRequest getRequest() {
		return request;
	}
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}


}

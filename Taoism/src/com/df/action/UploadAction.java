package com.df.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.df.dao.pojo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


@Controller("uploadAction")
@Scope("prototype")
public class UploadAction extends ActionSupport implements ModelDriven<User>, RequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;   

	//上传用户照片存放的路径
	private final String UPLOADDIR = "/user_img";
	//上传的图片
	private File imgfile;
	//上传的图片名
	private String imgfileFileName; 
	//上传文件图片类型
	private String imgfileContenType;
	//上传结果
	private static String msg = "error";
	//用户
	private User user;
	private Map<String , Object> requestMap;

	public File getImgfile() {
		return imgfile;
	}

	public void setImgfile(File imgfile) {
		this.imgfile = imgfile;
	}

	public String getImgfileFileName() {
		return imgfileFileName;
	}

	public void setImgfileFileName(String imgfileFileName) {
		this.imgfileFileName = imgfileFileName;
	}

	public String getImgfileContenType() {
		return imgfileContenType;
	}

	public void setImgfileContenType(String imgfileContenType) {
		this.imgfileContenType = imgfileContenType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute() throws Exception { 
        uploadFile();   
        return msg;   
    }   
	
	 //执行上传功能   
    @SuppressWarnings({ "deprecation", "resource" })
	private void uploadFile() throws FileNotFoundException, IOException {   
    	System.out.println("-----------进来了");
        try {   
            InputStream in = new FileInputStream(imgfile);   
            String dir = ServletActionContext.getRequest()
            		.getSession()
            		.getServletContext()
            		.getRealPath(UPLOADDIR);  
            File fileLocation = new File(dir);  
           
            //此处也可以在应用根目录手动建立目标上传目录
            if(!fileLocation.exists()){  
                boolean isCreated  = fileLocation.mkdir();  
                //目录创建失败则返回
                if(!isCreated) {  
                    return;  
                }  
            }  
            
            //修改文件名    用户名+当前时间+文件后缀名  避免文件同名
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            //获得文件后缀名
            String suffix = this.getImgfileFileName()
            		.substring(this.getImgfileFileName().lastIndexOf(".")+1);
            this.setImgfileFileName(user.getUsername()+sdf.format(new Date())+"."+suffix);
            
            String fileName=this.getImgfileFileName();
            File uploadFile = new File(dir, fileName);   
            OutputStream out = new FileOutputStream(uploadFile);         
            
            byte[] buffer = new byte[1024 * 1024];   
            int length;   
            while ((length = in.read(buffer)) > 0) {   
                out.write(buffer, 0, length);   
            }   
            in.close();   
            out.close();   
            
            //将图片的路径添加到用户资料中你用request
            user.setPicture(UPLOADDIR+"/"+this.getImgfileFileName());
            System.out.println(user);
            msg = "success";
            requestMap.put("user",user);
            
        } catch (FileNotFoundException ex) {   
            System.out.println("上传失败!");  
            ex.printStackTrace();   
        } catch (IOException ex) {   
            System.out.println("上传失败!");  
            ex.printStackTrace();   
        }   
    }

	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap = arg0;
	}

	@Override
	public User getModel() {
		user = new User();
		return user;
	}
}


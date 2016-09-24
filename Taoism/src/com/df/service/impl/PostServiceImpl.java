package com.df.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.df.dao.idao.IDiscipleAreaDAO;
import com.df.dao.idao.IMyquestionDAO;
import com.df.dao.idao.IPublicAreaDAO;
import com.df.dao.idao.IStudentAreaDAO;
import com.df.dao.pojo.DiscipleArea;
import com.df.dao.pojo.Myquestion;
import com.df.dao.pojo.PublicArea;
import com.df.dao.pojo.QueryCriteria;
import com.df.dao.pojo.StudentArea;
import com.df.service.iservice.IPostService;

@Service("postService")
public class PostServiceImpl<T> implements IPostService<T> {
	@Autowired
	@Qualifier("publicAreaDao")
	private IPublicAreaDAO publicAreaDao;
	
	@Autowired
	@Qualifier("discipleAreaDao")
	private IDiscipleAreaDAO discipleAreaDao;
	
	@Autowired
	@Qualifier("studentAreaDao")
	private IStudentAreaDAO studentAreaDao;
	
	@Autowired
	@Qualifier("myquestionDao")
	private IMyquestionDAO myquestionDao;
	
	@Transactional
	@Override
	public List<Object> findByDynamicData(QueryCriteria t,String userType) throws Exception {
		List<Object> postList = new ArrayList<Object>();
		List<PublicArea> pList = new ArrayList<PublicArea>();
		List<DiscipleArea> dList = new ArrayList<DiscipleArea>();
		List<StudentArea> sList = new ArrayList<StudentArea>();
		List<Myquestion> mList = new ArrayList<Myquestion>();
		if("所有问题".equals(t.getSharezone())){
			System.out.println("进入所有问题");
			if("".equals(userType)){
				pList = publicAreaDao.findByDynamicData(t);
				if(!pList.isEmpty()){postList.add(pList);}
			}
			if("普通".equals(userType)){
				pList = publicAreaDao.findByDynamicData(t);
				mList = myquestionDao.findByDynamicData(t);
				if(mList!=null){postList.add(mList);}
				if(!pList.isEmpty()){postList.add(pList);}
			}
			if("学员".equals(userType)){
				pList = publicAreaDao.findByDynamicData(t);
				mList = myquestionDao.findByDynamicData(t);
				sList = studentAreaDao.findByDynamicData(t);
				if(!mList.isEmpty()){postList.add(mList);}
				if(!pList.isEmpty()){postList.add(pList);}
				if(!sList.isEmpty()){postList.add(sList);}
			}
			if("弟子".equals(userType)||"老先生".equals(userType)){
				pList = publicAreaDao.findByDynamicData(t);
				mList = myquestionDao.findByDynamicData(t);
				sList = studentAreaDao.findByDynamicData(t);
				dList = discipleAreaDao.findByDynamicData(t);
				if(!mList.isEmpty()){postList.add(mList);}
				if(!pList.isEmpty()){postList.add(pList);}
				if(!sList.isEmpty()){postList.add(sList);}
				if(!dList.isEmpty()){postList.add(dList);}
			}	
		}else{
			System.out.println("没有进入所有问题");
			if("公开区".equals(t.getSharezone())){
				System.out.println("进入公开区");
				try{
					pList = publicAreaDao.findByDynamicData(t);
					if(!pList.isEmpty()){postList.add(pList);}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if("弟子区".equals(t.getSharezone())){
				System.out.println("进入弟子区");
				try{
					dList = discipleAreaDao.findByDynamicData(t);
					if(!dList.isEmpty()){postList.add(dList);}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if("学员区".equals(t.getSharezone())){
				System.out.println("进入学员区");
				try{
					sList = studentAreaDao.findByDynamicData(t);
					if(!sList.isEmpty()){postList.add(sList);}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if("我的问题".equals(t.getSharezone())){
				System.out.println("进入我的问题");
				try{
					mList = myquestionDao.findByDynamicData(t);
					if(!mList.isEmpty()){postList.add(mList);}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		System.out.println("1");
		List<Object> list  = new ArrayList<Object>();
		System.out.println("2");
		List<Object> lObject = new ArrayList<Object>();
		System.out.println("3");
		System.out.println("List长度为"+postList.size());
		for(int i=0;i<postList.size();i++){
			list = (List<Object>) postList.get(i);
			System.out.println("内部List长度为"+postList.size());
			for(int j=0;j<list.size();j++){
				lObject.add(list.get(j));
			}
		}
		System.out.println("servicec层"+postList.toString());
		return lObject;
	}
	
}

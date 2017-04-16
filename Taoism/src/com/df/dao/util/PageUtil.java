package com.df.dao.util;

import java.util.ArrayList;
import java.util.List;

import com.df.dao.pojo.ClientPage;
import com.df.dao.pojo.DataPage;
import com.df.dao.pojo.Page;





public class PageUtil {
	private static int sumPage;
	//private static int currentPage;


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  static <T> DataPage<T> paging(List<T> tList,int listSize,int currentPage,int rows) {
		if(sumPage != 0) {
			if(currentPage > sumPage)  //判断输入的页数是否大于总页数
				currentPage = sumPage;
			else if(currentPage < 0)
				currentPage = 1;
		}
		Page page = createPage(rows, listSize, currentPage);
		
		//前台页码显示
		int sum = page.getTotalPage();
		int current = page.getCurrentPage();
		int index;
		List<ClientPage> pageList = getClientPage(current,sum);
		System.out.println("当前页面"+current+pageList);
		System.out.println("获取的记录数："+listSize);
		if(pageList.size()!=0){
			if(sum<=10){
				index=1;
			}else if(sum>10 && current<=5){
				index=1;
			}else if(current+5>=sum){
				index=sum-9;
			}else{
				index=current-4;
			}
			System.out.println(page.getCurrentPage()-index);		
			pageList.get(page.getCurrentPage()-index).setPage(0);
			
		}
		DataPage<T> dp = new DataPage(tList,pageList,page);
		System.out.println("000000000000"+tList.toString());
		System.out.println("111111111111"+pageList.toString());
		System.out.println("222222222222"+page.toString());
		return dp;
	}
	
	
	private static Page createPage(int everyPage,int totalCount,int currentPage){
		everyPage=getEveryPage(everyPage);//设置每页显示记录数
		currentPage=getCurrentPage(currentPage);//设置当前页
		int totalPage=getTotalPage(everyPage,totalCount);
		int beginIndex=getBeginIndex(everyPage,currentPage);
		boolean hasPrePage=getHasPrePage(currentPage);
		boolean hasNextPage=getHasNextPage(totalPage,currentPage);
		                //每页显示数量   总记录数            总页数               当前页                      起始点                      是否有上一页       是否有下一页
		return new Page( everyPage,totalCount, totalPage, currentPage, beginIndex, hasPrePage, hasNextPage);
	}

	private static boolean getHasNextPage(int totalPage, int currentPage) {
		// TODO Auto-generated method stub
		       //当前页                 最后一页
		return currentPage>=totalPage?false:true;
	}

	private static boolean getHasPrePage(int currentPage) {
		// TODO Auto-generated method stub
		       //当前页
		return currentPage==1?false:true;
	}

	//在当前页面之前一共显示过多少条记录，记录条数是从0开始计算的
	private static int getBeginIndex(int everyPage, int currentPage) {
		// TODO Auto-generated method stub
		return (currentPage-1)*everyPage;
	}

	//返回总页数
	private static int getTotalPage(int everyPage, int totalCount) {
		// TODO Auto-generated method stub
		int totalPage=0;
		   //总记录数对每页显示的记录条数进行求余
		if(totalCount%everyPage==0)
			totalPage=totalCount/everyPage;
		else 
			totalPage=totalCount/everyPage+1;
		return totalPage;
	}

	private static int getCurrentPage(int currentPage) {
		// TODO Auto-generated method stub
		return currentPage==0?1:currentPage;
	}

	private static int getEveryPage(int everyPage) {
		// TODO Auto-generated method stub
		return everyPage==0?10:everyPage;
	}
	
	/*
	 * 前台页码显示
	 */
	private static List<ClientPage> getClientPage(int currentPage,int totalCount){
		List<ClientPage> cpList = new ArrayList<ClientPage>();
		if(totalCount<=10){
			for(int i=1;i<=totalCount;i++){
				ClientPage cp = new ClientPage(i);
				cpList.add(cp);
				System.out.println(i);
			}
		}else if(totalCount>10&&currentPage<=5){
			for(int i=1;i<=10;i++){
				ClientPage cp = new ClientPage(i);
				cpList.add(cp);
				System.out.println(i);
			}
		}else if(currentPage+5>=totalCount){
			for(int i=1;i<=10;i++){
				ClientPage cp = new ClientPage(totalCount-10+i);
				cpList.add(cp);
				System.out.println(i);
			}
		}
		else{
			for(int i=currentPage-4;i<=currentPage+5&&currentPage<=totalCount;i++){
				ClientPage cp = new ClientPage(i);
				cpList.add(cp);
				System.out.println(i);
			}
		}
		return cpList;
	}
		/*String pageStr="";
		
		if(totalCount<=10){
			for(int i=1;i<=totalCount;i++){
				if(i==1){
					pageStr = pageStr+"1";
				}else{
					pageStr = pageStr+","+i;
				}
			}
		}else if(totalCount>10 && currentPage<5){
			for(int i=1;i<=currentPage;i++){
				if(i==1){
					pageStr = pageStr+"1";
				}else{
					pageStr = pageStr+","+i;
				}
			}
			pageStr = pageStr+","+(currentPage+1);
			pageStr = pageStr+","+(currentPage+2);
			pageStr = pageStr+",-1";			//用-1代替前台的...
			pageStr = pageStr+","+totalCount;
		}else if(totalCount>10 && currentPage>=5 && currentPage<totalCount-2){
			pageStr = pageStr+"1";
			pageStr = pageStr+",-1";
			pageStr = pageStr+","+(currentPage-2);
			pageStr = pageStr+","+(currentPage-1);
			pageStr = pageStr+","+currentPage;
			pageStr = pageStr+","+(currentPage+1);
			pageStr = pageStr+","+(currentPage+2);
			pageStr = pageStr+",-1";
			pageStr = pageStr+","+totalCount;
		}else if(currentPage>=totalCount-2 && currentPage<totalCount-1){
			pageStr = pageStr+"1";
			pageStr = pageStr+",-1";
			pageStr = pageStr+","+(currentPage-2);
			pageStr = pageStr+","+(currentPage-1);
			pageStr = pageStr+","+currentPage;
			pageStr = pageStr+","+(currentPage+1);
			pageStr = pageStr+","+(currentPage+2);
		}else if(currentPage>=totalCount-1 && currentPage<totalCount){
			pageStr = pageStr+"1";
			pageStr = pageStr+",-1";
			pageStr = pageStr+","+(currentPage-2);
			pageStr = pageStr+","+(currentPage-1);
			pageStr = pageStr+","+currentPage;
			pageStr = pageStr+","+(currentPage+1);
		}else{
			pageStr = pageStr+"1";
			pageStr = pageStr+",-1";
			pageStr = pageStr+","+(currentPage-2);
			pageStr = pageStr+","+(currentPage-1);
			pageStr = pageStr+","+currentPage;
		}
		
		String[] pages;
		List<ClientPage> pageList = new ArrayList<ClientPage>();
		if(!pageStr.equals("")){
			pages = pageStr.split(",");
			int len= pages.length;
			
			for(int index=0;index<len;index++){
				ClientPage page = new ClientPage();
				page.setPage(Integer.parseInt(pages[index]));
				pageList.add(page);
			}
		}
		
		return pageList;
	}*/
	public int getSumPage() {
		return sumPage;
	}


	public void setSumPage(int sumPage) {
		this.sumPage = sumPage;
	}


	/*public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}*/
}

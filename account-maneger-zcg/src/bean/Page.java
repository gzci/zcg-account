package bean;
 
public class Page {
     private int totalPage;
     private int currentPage;
     private int totalRecord;
     private int currentRecord;
     private int pageSize=5;
     //��ú����õ�ǰҳ
     public int getCurrentPage(){
    	 return currentPage;
     }
     public void setCurrentPage(int currentRecord,int pageSize){
    	 if(currentRecord%pageSize==0){
    		 currentPage=currentRecord/pageSize;
    	 }
    	 else{
    		 currentPage=currentRecord/pageSize+1;
    	 }
     }
     //��ú����õ�ǰ��¼
     public int getCurrentRecord(){
    	 return currentRecord;
     }
     public void setCurrentRecord(int currentRecord){
    	 this.currentRecord=currentRecord;
     }
     //��ú�����ÿҳ��¼����
     public int getPageSize(){
    	 return pageSize;
     }
     public void setPageSize(int pageSize){
    	 this.pageSize=pageSize;
     }
     //��ú�������ҳ��
     public int getTotalPage(){
    	 return totalPage;
     }
     public void setTotalPage(int totalRecord,int pageSize){
    	 if(totalRecord%pageSize==0){
    		 totalPage=totalRecord/pageSize;
    	 }
    	 else{
    		 totalPage=totalRecord/pageSize+1;
    	 }
     }
     //��ú������ܼ�¼
     public int getTotalRecord(){
    	 return totalRecord;
     }
     public void setTotalRecord(int totalRecord){
    	 this.totalRecord=totalRecord;
     }
 
     
}

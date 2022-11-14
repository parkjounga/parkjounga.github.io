package org.vision.tboard;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

public class ArticleVO {
	private int level;
	private int articleNO;
	private int parentNO;
	private String title;
	private String content;
	private String imageFileName;
	private String id;
	private Date writeDate;
	private String ids; //추가 ~ 로그인시에만 삭제수정부분을 나타내기위해서 	
	private int hit;  // 
	public ArticleVO() {
		
	}


	public ArticleVO(int level, int articleNO, int parentNO, String title, String content, String imageFileName,
			String id, String ids, int hit) {
		super();
		this.level = level;
		this.articleNO = articleNO;
		this.parentNO = parentNO;
		this.title = title;
		this.content = content;
		this.imageFileName = imageFileName;
		this.id = id;
		this.ids = ids;
		this.hit = hit;
		
	}




	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public int getArticleNO() {
		return articleNO;
	}


	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}


	public int getParentNO() {
		return parentNO;
	}


	public void setParentNO(int parentNO) {
		this.parentNO = parentNO;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	
	public String getImageFileName() {
		try {
			if (imageFileName != null && imageFileName.length() != 0) {
				imageFileName = URLDecoder.decode(imageFileName, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		try {
			if(imageFileName!=null && imageFileName.length()!=0) {
				this.imageFileName = URLEncoder.encode(imageFileName, "UTF-8");  //�����̸��� Ư�����ڰ� ���� ��� ���ڵ��մϴ�.
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Date getWriteDate() {
		return writeDate;
	}


	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	
	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public int getHit() {
		return hit;
	}


	public void setHit(int hit) {
		this.hit = hit;
	}

	

}
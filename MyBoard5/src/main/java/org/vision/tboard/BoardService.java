package org.vision.tboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {
	BoardDAO boardDAO;

	public BoardService() {
		boardDAO = new BoardDAO();
	}

	public Map listArticles(Map<String, Integer> pagingMap) {
		Map articlesMap = new HashMap();
		List<ArticleVO> articlesList = boardDAO.selectAllArticles(pagingMap);
		int totArticles = boardDAO.selectTotArticles();
		articlesMap.put("articlesList", articlesList);
		articlesMap.put("totArticles", totArticles);
		//articlesMap.put("totArticles", 170);
		return articlesMap;
	}

	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}

	public int addArticle(ArticleVO article) {
		return boardDAO.insertNewArticle(article);
	}

	public ArticleVO viewArticle(int articleNO, String sessionID) {
		ArticleVO article = null;
		article = boardDAO.selectArticle(articleNO, sessionID);
		return article;
	}

	public void modArticle(ArticleVO article) {
		boardDAO.updateArticle(article);
	}

	public List<Integer> removeArticle(int articleNO, String id) {
		List<Integer> articleNOList = boardDAO.selectRemovedArticles(articleNO, id);
		boardDAO.deleteArticle(articleNO, id);
		return articleNOList;
	}

	public int addReply(ArticleVO article) {
		
		return boardDAO.insertNewArticle(article);
	}

	public void hitArticle(int articleNO,String sessionID) {
		ArticleVO article = null;
//		article = boardDAO.hitUpdateArticle(articleNO, sessionID);
		boardDAO.hitUpdateArticle(articleNO, sessionID);
		

//		return article;
	}
}
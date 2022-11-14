package org.vision.tboard;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
/**
 * Servlet implementation class BoardController
 */

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
   private static String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";
   BoardService boardService;  
   ArticleVO articleVO;

   /**
    * @see Servlet#init(ServletConfig)
    */
   public void init(ServletConfig config) throws ServletException {
      boardService = new BoardService();
      articleVO = new ArticleVO();
   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
      doHandle(request, response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doHandle(request, response);
   }

   private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String nextPage = "";
      request.setCharacterEncoding("utf-8");
      response.setContentType("text/html; charset=utf-8");
      HttpSession session;
      String action = request.getPathInfo();
      System.out.println("action:" + action);//로그를 위해 콘솔로 출력 
      try {
         List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
         
         if (action==null){   
            String _section=request.getParameter("section");
            String _pageNum=request.getParameter("pageNum");
            int section = Integer.parseInt(((_section==null)? "1":_section) );
            int pageNum = Integer.parseInt(((_pageNum==null)? "1":_pageNum));
            Map<String, Integer> pagingMap = new HashMap<String, Integer>();
            pagingMap.put("section", section);
            pagingMap.put("pageNum", pageNum);
            Map articlesMap=boardService.listArticles(pagingMap);
            articlesMap.put("section", section);
            articlesMap.put("pageNum", pageNum);
            request.setAttribute("articlesMap", articlesMap);
            nextPage = "/tboard/listArticles.jsp";
            
         }else if(action.equals("/listArticles.do")){           
            String _section=request.getParameter("section");
            String _pageNum=request.getParameter("pageNum");
            int section = Integer.parseInt(((_section==null)? "1":_section) );
            int pageNum = Integer.parseInt(((_pageNum==null)? "1":_pageNum));
            Map pagingMap=new HashMap();
            pagingMap.put("section", section);
            pagingMap.put("pageNum", pageNum);
            Map articlesMap=boardService.listArticles(pagingMap);
            articlesMap.put("section", section);
            articlesMap.put("pageNum", pageNum);
            request.setAttribute("articlesMap", articlesMap);
            nextPage = "/tboard/listArticles.jsp";
        
         } else if (action.equals("/articleForm.do")) {
            nextPage = "/tboard/articleForm.jsp";
        
         } else if (action.equals("/addArticle.do")) {
            int articleNO = 0;
            Map<String, String> articleMap = upload(request, response);
            String title = articleMap.get("title");
            String content = articleMap.get("content");
            String imageFileName = articleMap.get("imageFileName");
            String id = (String)request.getSession().getAttribute("login.id");
            
            articleVO.setParentNO(0);
            articleVO.setId(id);
            articleVO.setTitle(title);
            articleVO.setContent(content);
            articleVO.setImageFileName(imageFileName);
            articleNO = boardService.addArticle(articleVO);
            if (imageFileName != null && imageFileName.length() != 0) {
               File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
               File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
               destDir.mkdirs();
               FileUtils.moveFileToDirectory(srcFile, destDir, true);
            }
            PrintWriter pw = response.getWriter();
            pw.print("<script>" + "  alert('추가했습니다.');" + " location.href='" + request.getContextPath()
                  + "/board/listArticles.do';" + "</script>");

            return;
         } else if (action.equals("/viewArticle.do")) { // 보여주는목록
        	
        	String articleNO = request.getParameter("articleNO");
            String sessionID = (String)request.getSession().getAttribute("login.id"); // 세션아이디 ~ 추가 로그인시 삭제되는 부분 
            articleVO = boardService.viewArticle(Integer.parseInt(articleNO), sessionID);
            request.setAttribute("article", articleVO);
            nextPage = "/tboard/viewArticle.jsp";
            
            
         } else if (action.equals("/hitUpdateArticle.do")) { // 좋아요
         	String articleNO = request.getParameter("articleNO");
         	System.out.println(articleNO);
            String sessionID = (String)request.getSession().getAttribute("login.id"); // 세션아이디 ~ 추가 로그인시 삭제되는 부분 
            boardService.hitArticle(Integer.parseInt(articleNO), sessionID);
            PrintWriter pw = response.getWriter();
            pw.print("<script>" + "  alert('좋아요!');" + " location.href='" + request.getContextPath()
            + "/board/viewArticle.do?articleNO=" + articleNO + "';" + "</script>");
            return;
//            request.setAttribute("article", articleVO);

         } else if (action.equals("/modArticle.do")) {
            Map<String, String> articleMap = upload(request, response); //파일업로드요청 
            int articleNO = Integer.parseInt(articleMap.get("articleNO"));
            articleVO.setArticleNO(articleNO);
            String title = articleMap.get("title");
            String content = articleMap.get("content");
            String imageFileName = articleMap.get("imageFileName");
            String id = (String)request.getSession().getAttribute("login.id");
            articleVO.setParentNO(0);
            articleVO.setId(id);
            articleVO.setTitle(title);
            articleVO.setContent(content);
            articleVO.setImageFileName(imageFileName);
            boardService.modArticle(articleVO);
            if (imageFileName != null && imageFileName.length() != 0) {
               String originalFileName = articleMap.get("originalFileName");
               File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
               File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
               destDir.mkdirs();
               FileUtils.moveFileToDirectory(srcFile, destDir, true);
               ;
               File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + originalFileName);
               oldFile.delete();
            }
            PrintWriter pw = response.getWriter();
            pw.print("<script>" + "  alert('글을 수정했습니다.');" + " location.href='" + request.getContextPath()
                  + "/board/viewArticle.do?articleNO=" + articleNO + "';" + "</script>");
            return;
       
         } else if (action.equals("/removeArticle.do")) {
            // 로그인한 회원이 작성한 게시글만 삭제할 수 있도록 수정해야 함 
            int articleNO = Integer.parseInt(request.getParameter("articleNO"));
            String id = (String)request.getSession().getAttribute("login.id"); //삭제 ~ ID로 삭제하기위해
            List<Integer> articleNOList = boardService.removeArticle(articleNO, id);
            for (int _articleNO : articleNOList) {
               File imgDir = new File(ARTICLE_IMAGE_REPO + "\\" + _articleNO);
               if (imgDir.exists()) {
                  FileUtils.deleteDirectory(imgDir);
               }
            }

            PrintWriter pw = response.getWriter();
            pw.print("<script>" + "  alert('글을 삭제했습니다.');" + " location.href='" + request.getContextPath()
                  + "/board/listArticles.do';" + "</script>");
            return;

         } else if (action.equals("/replyForm.do")) {
            session = request.getSession();
            int parentNO = Integer.parseInt(request.getParameter("parentNO"));
            
            String id = (String)request.getSession().getAttribute("login.id");
            session.setAttribute("id", id);
            session.setAttribute("parentNO", parentNO);
            nextPage = "/tboard/replyForm.jsp";
            
         } else if (action.equals("/addReply.do")) { //댓글
            session = request.getSession();
            int parentNO = (Integer) session.getAttribute("parentNO");
            session.removeAttribute("id");
            session.removeAttribute("parentNO");
            Map<String, String> articleMap = upload(request, response);
            String title = articleMap.get("title");
            String content = articleMap.get("content");
            String imageFileName = articleMap.get("imageFileName");
            String id = (String)request.getSession().getAttribute("login.id");
            articleVO.setParentNO(parentNO);
            // 접속한 회원의 아이디로 세팅되게 수정해야 함 
            articleVO.setId(id);
            articleVO.setTitle(title);
            articleVO.setContent(content);
            articleVO.setImageFileName(imageFileName);
            
            int articleNO = boardService.addReply(articleVO);
            if (imageFileName != null && imageFileName.length() != 0) {
               File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
               File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
               destDir.mkdirs();
               FileUtils.moveFileToDirectory(srcFile, destDir, true);
            }
            PrintWriter pw = response.getWriter();
            pw.print("<script>" + "  alert('답글을 추가했습니다.');" + " location.href='" + request.getContextPath()
                  + "/board/viewArticle.do?articleNO="+articleNO+"';" + "</script>");
            return;
         
         }else {
            nextPage = "/tboard/listArticles.jsp";
         }

         RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
         dispatch.forward(request, response);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      Map<String, String> articleMap = new HashMap<String, String>();
      String encoding = "utf-8";
      File currentDirPath = new File(ARTICLE_IMAGE_REPO); //글이미지 저장 폴더에 대해 파일객체를 생성한다
      DiskFileItemFactory factory = new DiskFileItemFactory();
      factory.setRepository(currentDirPath);
      factory.setSizeThreshold(1024 * 1024);
      ServletFileUpload upload = new ServletFileUpload(factory);
      try {
         List items = upload.parseRequest(request);
         for (int i = 0; i < items.size(); i++) {
            FileItem fileItem = (FileItem) items.get(i);
            if (fileItem.isFormField()) {
               System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
               articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
            } else {
               System.out.println("파라미터명:" + fileItem.getFieldName());
               //System.out.println("파일명:" + fileItem.getName());
               System.out.println("파일크기:" + fileItem.getSize() + "bytes");
               //articleMap.put(fileItem.getFieldName(), fileItem.getName());
               if (fileItem.getSize() > 0) {
                  int idx = fileItem.getName().lastIndexOf("\\");
                  if (idx == -1) {
                     idx = fileItem.getName().lastIndexOf("/");
                  }

                  String fileName = fileItem.getName().substring(idx + 1);
                  System.out.println("파일명:" + fileName);
                        articleMap.put(fileItem.getFieldName(), fileName);  //익스플로러에서 업로드 파일의 경로 제거 후 map에 파일명 저장);
                  File uploadFile = new File(currentDirPath + "\\" + fileName);
                  fileItem.write(uploadFile);

               } // end if
            } // end if
         } // end for
      } catch (Exception e) {
         e.printStackTrace();
      }
      return articleMap;
   }
}
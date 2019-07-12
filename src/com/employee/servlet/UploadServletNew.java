package com.employee.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.FileCleaningTracker;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadServletNew
 */
@WebServlet("/UploadServletNew")
public class UploadServletNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private boolean isMultipart;
	   private String filePath = "timesheetPDF";
	   private int maxFileSize = 500 * 1024;
	   private int maxMemSize = 4 * 1024;
	   static String message = null;
	   public String fileName = null;
		static int status = 0;
	   private File file ;
	   /*public void init( ){
		      // Get the file location where it would be stored.
		      filePath = 
		             getServletContext().getInitParameter("fileToUpload");
		   }*/
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServletNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  throw new ServletException("GET method used with " +
	                getClass( ).getName( )+": POST method required.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		 // Check that we have a file upload request
	      isMultipart = ServletFileUpload.isMultipartContent(request);
	      response.setContentType("text/html");
	      java.io.PrintWriter out = response.getWriter( );
	      if( !isMultipart ){
	    	  message = "error happens";
	    		status = 0;
//	         out.println("<html>");
//	         out.println("<head>");
//	         out.println("<title>Servlet upload</title>");  
//	         out.println("</head>");
//	         out.println("<body>");
//	         out.println("<p>No file uploaded</p>"); 
//	         out.println("</body>");
//	         out.println("</html>");
	         return;
	      }
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	      // maximum size that will be stored in memory
	      factory.setSizeThreshold(maxMemSize);

	      //factory.setRepository(new File("C:\\temp"));
		  //factory.setRepository(new File("C:\\PocWeb\\web\\timesheetPDF"));

		/*cleanup of temporary files*/
		ServletContext servletContext = this.getServletConfig().getServletContext();
		FileCleaningTracker tracker = FileCleanerCleanup.getFileCleaningTracker(servletContext);
		factory.setFileCleaningTracker(tracker);

		// Location to save data that is larger than maxMemSize.
		  factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      // maximum file size to be uploaded.
	      upload.setSizeMax( maxFileSize );
		/*String uploadPath = getServletContext().getRealPath("")
				+ File.separator + filePath;*/
		String uploadPath = "C:\\PocWeb\\web\\timesheetPDF";
		System.out.println(filePath);
		System.out.println(uploadPath);
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {

			uploadDir.mkdir();
		}

	      try{ 
	      // Parse the request to get file items.
	      List fileItems = upload.parseRequest(request);
		
	      // Process the uploaded file items
	      Iterator i = fileItems.iterator();
System.out.println("iterator i is " + i);
//	      out.println("<html>");
//	      out.println("<head>");
//	      out.println("<title>Servlet upload</title>");  
//	      out.println("</head>");
//	      out.println("<body>");
	      while ( i.hasNext () ) 
	      {
	         FileItem fi = (FileItem)i.next();
	         if ( !fi.isFormField () )	
	         {
	            // Get the uploaded file parameters
	            String fieldName = fi.getFieldName();
	             fileName = fi.getName();
	            String contentType = fi.getContentType();
	            boolean isInMemory = fi.isInMemory();
	            long sizeInBytes = fi.getSize();
	            // Write the file
	           /* if( fileName.lastIndexOf("\\") >= 0 ){
	               file = new File( filePath + 
	               fileName.substring( fileName.lastIndexOf("\\"))) ;
	            }else{
	               file = new File( filePath + 
	               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
	            }
	            fi.write( file ) ;
	            System.out.println(file);*/
				 String filePath = uploadPath + File.separator + fileName;
				 File storeFile = new File(filePath);

				 // saves the file on disk
				 fi.write(storeFile);
	            message = "Upload Successfully";
	    		status = 1;
//	            out.println("Uploaded Filename: " + fileName + "<br>");
	         }
	      }
//	      out.println("</body>");
//	      out.println("</html>");
	   }catch(Exception ex) {
	       System.out.println(ex);
	   }
	      
//	      response.setContentType("application/json; charset=UTF-8");

			 response.setContentType("text/html; charset=UTF-8"); ///changed
			PrintWriter printout = response.getWriter();
			printout.write(fileName);
//			if (status == 0) {
//				JSONObject JObject = new JSONObject();
//				JObject.put("ResponseChecker", status);
//				JObject.put("Message", message);
//				JObject.put("fileName", fileName);
//				System.out.println(JObject.toString());
//				printout.write(JObject.toString());
	}

}

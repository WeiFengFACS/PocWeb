package com.employee.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadFile
 */
@WebServlet("/UploadFile")
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private String filePath = "C:/temp/";
	 private boolean isMultipart;
	   private int maxFileSize = 50 * 1024;
	   private int maxMemSize = 4 * 1024;
	   private File file ;
//	   public void init( ){
//		      // Get the file location where it would be stored.
//		      filePath = 
//		             getServletContext().getInitParameter("file-upload"); 
//		   }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
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
		// Check that we have a file upload request
		System.out.println("inside servlet~~~");
		 isMultipart = ServletFileUpload.isMultipartContent(request);
//	      response.setContentType("text/html");
//	      java.io.PrintWriter out = response.getWriter( );
	      if( !isMultipart ){
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
	      // Location to save data that is larger than maxMemSize.
	      factory.setRepository(new File("c:\\temp"));

	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      // maximum file size to be uploaded.
	      upload.setSizeMax( maxFileSize );

	      try{ 
	      // Parse the request to get file items.
	      List fileItems = upload.parseRequest(request);
	      System.out.println("get item" + fileItems);
	      // Process the uploaded file items
	      Iterator i = fileItems.iterator();

//	      out.println("<html>");
//	      out.println("<head>");
//	      out.println("<title>Servlet upload</title>");  
//	      out.println("</head>");
//	      out.println("<body>");
	      while ( i.hasNext () ) 
	      {
	         FileItem fi = (FileItem)i.next();
	         System.out.println("iterator get" + fi);
	     	
	         if ( !fi.isFormField () )	
	         {
	            // Get the uploaded file parameters
	            String fieldName = fi.getFieldName();
	            String fileName = fi.getName();
	            String contentType = fi.getContentType();
	            boolean isInMemory = fi.isInMemory();
	            long sizeInBytes = fi.getSize();
	            // Write the file
	            if( fileName.lastIndexOf("\\") >= 0 ){
	               file = new File( filePath + 
	               fileName.substring( fileName.lastIndexOf("\\"))) ;
	            }else{
	               file = new File( filePath + 
	               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
	               System.out.println("file " + file);
	            }
	            fi.write( file ) ;
	            System.out.println("success");
//	            out.println("Uploaded Filename: " + fileName + "<br>");
	         }
	      }
//	      out.println("</body>");
//	      out.println("</html>");
	   }catch(Exception ex) {
	       System.out.println(ex);
	   }
	}

}

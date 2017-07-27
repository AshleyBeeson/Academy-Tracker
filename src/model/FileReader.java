package model;

import dto.TestFileDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Practice")
public class FileReader extends HttpServlet{

    private TestFileDto fileDto;
    private String text;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        text = "Hello world";
        out.println(text);

    }


    public void init() throws ServletException{
        fileDto = new TestFileDto("test-file");
        try{
            text = fileDto.readFile();
        }catch (Exception e){
            getServletContext().log("An exception occurred in FileReader", e);
            throw new ServletException("An exception occurred in FileReader"
                    + e.getMessage());
        }
    }


    public void destroy(){
        super.destroy();
        try{
            fileDto.save(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package house.verve.images;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
 
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;

import org.springframework.stereotype.Component;
 
/**
 * A web service implementation of an endpoint interface.
 * @author www.codejava.net
 *
 */
@Component
 public class ImageFileService implements ImageFileTransferer {
   
    public void upload(String fileName, byte[] imageBytes) {
         
        String filePath =  "/data/images/"  + fileName;
         
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            BufferedOutputStream outputStream = new BufferedOutputStream(fos);
            outputStream.write(imageBytes);
            outputStream.close();
             
               
        } catch (IOException ex) {
            System.err.println(ex);
            throw new WebServiceException(ex);
        }
    }
     
   
    public byte[] download(String fileName) {
        String filePath = "/data/images/" + fileName;
           
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream inputStream = new BufferedInputStream(fis);
            byte[] fileBytes = new byte[(int) file.length()];
            inputStream.read(fileBytes);
            inputStream.close();
             
            return fileBytes;
        } catch (IOException ex) {
            System.err.println(ex);
            throw new WebServiceException(ex);
        }      
    }
}
 



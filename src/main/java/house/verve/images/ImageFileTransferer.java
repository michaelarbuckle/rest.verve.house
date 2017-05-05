package house.verve.images;
 
import javax.jws.WebMethod;
import javax.jws.WebService;
 
/**
 * A web service endpoint interface.
 * @author www.codejava.net
 *
 */
@WebService
public interface ImageFileTransferer {
    @WebMethod
    public void upload(String fileName, byte[] imageBytes);
     
    @WebMethod
    public byte[] download(String fileName);   
}
 

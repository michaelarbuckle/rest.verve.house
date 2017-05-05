package house.verve.web;


 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
 

import house.verve.images.ImageFileService;
import house.verve.model.SpaceRepository;
import house.verve.model.StructureRepository;
import house.verve.model.UserRepository;

import java.io.IOException;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ImageWebController {

	@Autowired
     UserRepository userRepository;

	@Autowired
    SpaceRepository spaceRepository;

	@Autowired
    StructureRepository structureRepository;

	@Autowired    
	ImageFileService imageFileService;
	 
   

   

    @GetMapping("/userPhoto/{subjectname}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String subjectname) {

    	String filename = subjectname;//for now
    	 
       byte[] file = this.imageFileService.download(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.toString()+"\"").body(null);
                //.body(arg0);
    }

    @PostMapping("/userPhoto")
    public String handleImage(@RequestParam("subjectname") String  subjectname,@RequestParam("image") MultipartFile file) {

       
        return "redirect:/";
    }

  /*  
   *    @RequestMapping(value = "/userPhoto", method = RequestMethod.POST)
    @ResponseBody
    public String uploadUserPhoto(final Locale locale, @Valid String name,HttpSession session) {
    	
        String imageUrl ="/todo";
        
        return  imageUrl;
    }
   * 
   * @ExceptionHandler(Exception.class)
    public ResponseEntity handleStorageFileNotFound(Exception exc) {
        return ResponseEntity.notFound().build();
    }
*/
}
package io.hashimati.basiccrud;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.inject.Inject;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Action;

@Controller("/api")
public class BasicCrudController {

   


    @Inject
    private HashSet<Person> personsRepository; 

    @Post("/submitPerson")
    public Single<String> submitPerson(@Body Person person)
    {   
        return Single.just(personsRepository.add(person)? "success": "failed"); 
    
    }
    @Get("/persons")
    public Flowable<Object> persons()
    {
        return Flowable.fromArray(personsRepository.toArray()); 
    }

    @Put("/person/delete")
    public Single<String> deletePerson(@Body Person person)
    {

        return Single.just(personsRepository.remove(person)?"Succefully Deleted": "Failed To Delete");  

    }
    
    @Post(value = "/grey", consumes = MediaType.MULTIPART_FORM_DATA,
     produces = MediaType.IMAGE_PNG)
    public Single<File> greyScale( CompletedFileUpload fileUpload) throws Exception{
        
        System.out.println(fileUpload.getFilename()); 

        BufferedImage source= ImageIO.read(fileUpload.getInputStream() ); 
        BufferedImage target = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_RGB );
        for(int i =0; i < source.getWidth(); i++)
        {
            for(int j = 0; j < source.getHeight(); j++)
            {

                Color c = new Color(source.getRGB(i, j));
                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);
            
                Color newColor = new Color(
                    red + green + blue,
                    red + green + blue,
                    red + green + blue);
                target.setRGB(i, j, newColor.getRGB());
            }
        }
        File result = new File("//tmp//temp"+System.nanoTime()+".png"); 
        result.createNewFile(); 
        ImageIO.write(target, "png", result); 
        return Single.just(result)
        .doOnSuccess(x->{
            System.out.println("The file is delivered"); 
        })
        .doOnError(x->{
            System.out.println("Something is going wrong"); 
        })
        .doFinally(new Action(){
            @Override
            public void run() throws Exception {
                // TODO Auto-generated method stub
                result.delete(); 
                
            }
        }); 
    }
}

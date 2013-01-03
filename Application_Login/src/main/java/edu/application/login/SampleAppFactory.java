package edu.application.login;
import javafx.fxml.FXMLLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.application.login.paneAndController.LoginDialogController;
import edu.application.login.services.LoginService;
import edu.application.login.services.imp.LoginServiceImp;
 
import java.io.IOException;
import java.io.InputStream;
 
@Configuration
public class SampleAppFactory
{
   @Bean
   public LoginService person()
   {
       return new LoginServiceImp();
   }
 
    @Bean
    public LoginDialogController sampleController() throws IOException
    {
        return (LoginDialogController) loadController("loginDialog.fxml");
    }
 
    protected Object loadController(String url) throws IOException
    {
        InputStream fxmlStream = null;
        try
        {
            fxmlStream = getClass().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return loader.getController();
        }
        finally
        {
            if (fxmlStream != null)
            {
                fxmlStream.close();
            }
        }
    }
}
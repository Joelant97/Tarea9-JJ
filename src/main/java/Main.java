import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import modelos.Encuesta;
import com.google.gson.Gson;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;


import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {
    public final static String ACCEPT_TYPE = "application/json";

    public static void main(String[] args) {

        initSpark();
    }

    private static void initSpark() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(Main.class, "/plantillas");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        //Debug
        enableDebugScreen(); // port(80);

        staticFiles.location("/public");


        ArrayList<Encuesta> listadoEncuestaMain = new ArrayList<Encuesta>();
        //listadoEncuestaMain.add(new Encuesta(1,"Joel Antonio", "Prueba", "Universitario"));


        Encuesta.setListadoEncuestados(listadoEncuestaMain);




    }


}

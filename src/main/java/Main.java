import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import com.google.gson.Gson;
import modelos.Encuesta;
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


        get("/", (request, response) -> {
            response.redirect("/inicio");
            return "";
        });

        get("/inicio", (request,response)-> {
            Map<String, Object> model = new HashMap<>();

            String insert = request.queryParams("insertado");

            model.put("encuestados", listadoEncuestaMain);

            if (insert != null) {
                model.put("insertado", "si");
            } else {
                model.put("insertado", "si");
            }

            if (request.queryParams("id") != null) {
                int id = Integer.parseInt(request.queryParams("id"));
                String nombre = request.queryParams("nombre");
                String sector = request.queryParams("sector");
                String niveleducativo = request.queryParams("niveleducativo");
                double latitud = Double.parseDouble(request.queryParams("latitud"));
                double longitud = Double.parseDouble(request.queryParams("longitud"));

                listadoEncuestaMain.add(new Encuesta(id, nombre, sector, niveleducativo, latitud, longitud));

                response.redirect("/inicio");
            }

            return new ModelAndView(model,"home.ftl");
        },freeMarkerEngine);

        get("/eliminar/:id", (request, response) -> {
            int id=Integer.parseInt(request.params("id"));
            for(int i=0;i<listadoEncuestaMain.size();i++) {
                if (listadoEncuestaMain.get(i).getId() == id) {
                    listadoEncuestaMain.remove(listadoEncuestaMain.get(i));
                }
            }
            response.redirect("/inicio");
            return "";
        });


        get("/nuevo", (request,response)-> {
            Map<String, Object> model = new HashMap<>();

            return new ModelAndView(model,"new.ftl");
        },freeMarkerEngine);

        post("/nuevo", Main.ACCEPT_TYPE, (request, response) -> {
            Encuesta encuestado = new Gson().fromJson(request.body(), Encuesta.class);
            listadoEncuestaMain.add(encuestado);

            return "Sincronizado con el servidor";

        },JsonConverter.json());

        get("/geolocalizacion/:id", (request,response)-> {
            Map<String, Object> model = new HashMap<>();
            Encuesta encuestado = new Encuesta();

            for(int i=0;i<listadoEncuestaMain.size();i++){
                if(listadoEncuestaMain.get(i).getId()==Integer.parseInt(request.params("id"))){
                    encuestado=listadoEncuestaMain.get(i);
                }
            }

            model.put("encuestado",encuestado);

            return new ModelAndView(model,"geolocalizacion.ftl");
        },freeMarkerEngine);




    }


}

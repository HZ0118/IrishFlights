package controllers;

import controllers.*;
import models.Aircraft;
import play.api.Environment;
import play.mvc.*;
import play.data.*;
import play.db.ebean.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import views.html.*;

import models.users.User;

public class AircraftController extends Controller{
    private FormFactory formFactory;

    @Inject
    public AircraftController(FormFactory f){
        this.formFactory = f;
    }

    public Result aircrafts() {
        List<Aircraft> aircraftList = Aircraft.findAll();
        return ok(aircraftsView.render(aircraftList, User.getUserById(session().get("email"))));
    }

    public Result addAircraft(){
        Form<Aircraft> addAircraftForm = formFactory.form(Aircraft.class);
        return ok(addAircraft.render(addAircraftForm, User.getUserById(session().get("email"))));
    }

    public Result addAircraftSubmit(){
        Form<Aircraft> newAircraftForm = formFactory.form(Aircraft.class).bindFromRequest();
        if(newAircraftForm.hasErrors()){
            return badRequest(addAircraft.render(newAircraftForm, User.getUserById(session().get("email"))));
        }
        Aircraft newAircraft = newAircraftForm.get();
        if(newAircraft.getAircraftID() == null) {
            newAircraft.save();
        }
        else if(newAircraft.getAircraftID() != null){
            newAircraft.update();
        }
        flash("success", "Aircraft " + newAircraft.getAircraftNumber() + " has been created");
        return redirect(controllers.routes.AircraftController.aircrafts());
    }

    @Security.Authenticated(Secured.class)
    @With(AuthAdmin.class)
    @Transactional
    public Result deleteAircraft(Long id){

        Aircraft.find.ref(id).delete();

        flash("succes", "Aircraft has been deleted");

        return redirect(routes.AircraftController.aircrafts());
    }

    @Security.Authenticated(Secured.class)
    @With(AuthAdmin.class)
    @Transactional
    public Result updateAircraft(Long id) {
        Aircraft a;
        Form<Aircraft> aircraftForm;

        try{
            //find product by id
            a = Aircraft.find.byId(id);

            //Create a form based on the Product class and fill using p
            aircraftForm = formFactory.form(Aircraft.class).fill(a);

        }catch(Exception ex) {
            //Display an error message on page
            return badRequest("error");
        }
        //Render the updateProduct view - pass form as a parameter
        return ok(addAircraft.render(aircraftForm, User.getUserById(session().get("email"))));
    }

}

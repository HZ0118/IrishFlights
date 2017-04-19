package controllers;

import controllers.*;
import controllers.routes;
import play.api.Environment;
import play.mvc.*;
import play.data.*;

import javax.inject.Inject;

import views.html.*;
import models.users.*;

public class RegistrationController extends Controller {

    private FormFactory formFactory;

    @Inject
    public RegistrationController(FormFactory f) {
        this.formFactory = f;
    }

    public Result Registration(){
        Form<User> addUserForm = formFactory.form(User.class);
        return ok(registration.render(addUserForm, User.getUserById(session().get("email"))));
    }

    public Result RegistrationSubmit() {
        Form<User> newSignupForm = formFactory.form(User.class).bindFromRequest();
        if(newSignupForm.hasErrors()){
            return badRequest(registration.render(newSignupForm, User.getUserById(session().get("email"))));
        }
        User newUser = newSignupForm.get();
        newUser.save();
        flash("success", "User " + newUser.getName() + " has been created");
        return redirect(controllers.routes.LoginController.login());
    }

}
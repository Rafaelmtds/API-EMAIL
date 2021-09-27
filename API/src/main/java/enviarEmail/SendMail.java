package enviarEmail;

import enviarEmail.service.SendEmailService;
import io.javalin.Context;
import io.javalin.Handler;
import io.javalin.Javalin;
import Email.Email;
import com.google.gson.Gson;

public class SendMail {

	public static void main(String[] args) {

		Javalin app = Javalin.create().port(8080).start();
		app.get("/email", ctx -> ctx.html("Hello, Javalin!"));
		app.post("/email", new Handler() {
				
			@Override
			public void handle(Context ctx) throws Exception {
				
				
				System.out.println("Request received...");
				
				String jsonRequest = ctx.body();
				
				if(isJson(jsonRequest)) {
					
					Email email = new Gson().fromJson(jsonRequest, Email.class);
					
					SendEmailService sendEmail = new SendEmailService();
					sendEmail.send(email.getEmail(), email.getTitulo(), email.getMensagem());					
					ctx.contentType("application/json");
					ctx.result("{\"message\":\"Email sent successfully!\"}");
					
					
				} else {
					
					ctx.contentType("application/json");
					ctx.status(400);
					ctx.result("{\"message\":\"Error to read JSON request\"}");
					
				}
								

			}

		});

	}
	
	
	public static boolean isJson(String Json) {
	    Gson gson = new Gson();
	    try {
	        gson.fromJson(Json, Object.class);
	        Object jsonObjType = gson.fromJson(Json, Object.class).getClass();
	        if(jsonObjType.equals(String.class)){
	            return false;
	        }
	        return true;
	    } catch (com.google.gson.JsonSyntaxException ex) {
	        return false;
	    }
	}

}
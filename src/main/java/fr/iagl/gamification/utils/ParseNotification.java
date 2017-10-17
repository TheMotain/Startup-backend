//package fr.iagl.gamification.utils;
//import com.google.gson.Gson;
//
//import fr.iagl.gamification.model.ModelItf;
//import fr.iagl.gamification.model.MessageModel;
//import fr.iagl.gamification.model.MessageModel.MessageType;
//import org.json.JSONObject;
//
//public class ParseNotification {
//
//	public Notification<ModelItf> parseResponse(String response) throws Exception {
//		org.json.JSONObject json = new org.json.JSONObject(response);
//	    String table = (String) json.get("table");
//	    String type = (String) json.get("type");
//	    
//	    //object from the model
//	    if (table.equals("message")) {
//	    	JSONObject jsondata = JSONObject.fromObject((String) json.get("data"));
//	    	String content = jsondata.get("content");
//	    	String sender = jsondata.get("sender");
//	    	MessageType typeMessage = MessageType.CHAT;
//	    	MessageModel message = new MessageModel(typeMessage, content, sender);
//	    	return new Notification<>(table, message, type);
//	    }
//	    
//		
//	}
//	
//}

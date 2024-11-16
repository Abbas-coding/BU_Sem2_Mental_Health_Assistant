package source.mentalhealthassistant.core;

import java.util.ArrayList;
import java.util.Map;

public class ChatBot {
    private String botName;
    private Map<String, String> responses;
    private ArrayList<String> commonQuestions;

    public ChatBot(String botName){
        this.botName = botName;
    }

    public String getResponse(String userInput){
        // TODO
        return "";
    }

    public boolean loadCommonResponses(){
        // TODO
        return false;
    }

    public void addResponse(String userInput, String response){
        // TODO
    }

}

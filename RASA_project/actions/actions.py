from typing import Any, Text, Dict, List
from rasa_sdk import Action, Tracker
from rasa_sdk.executor import CollectingDispatcher
import os
from datetime import datetime

class ActionSaveData(Action):

    def name(self) -> Text:
        return "action_save_data"

    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        
        # Get the latest user message and bot response
        user_message = tracker.latest_message.get('text')  # User's input
        intent = tracker.latest_message.get('intent').get('name')  # Detected intent
        bot_response = tracker.latest_bot_utterance.get('text')  # Bot's reply (if any)

        # Format the data to save
        timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        data_to_save = f"Time: {timestamp}\nUser: {user_message}\nIntent: {intent}\nBot: {bot_response}\n\n"

        # Define the file path
        file_path = "chat_data.txt"

        # Append the data to the file
        try:
            with open(file_path, "a") as file:
                file.write(data_to_save)
            dispatcher.utter_message(text="Your data has been saved!")
        except Exception as e:
            dispatcher.utter_message(text="An error occurred while saving data.")
            print(f"Error: {e}")

        return []

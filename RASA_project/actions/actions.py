from typing import Any, Text, Dict, List
from rasa_sdk import Action, Tracker
from rasa_sdk.executor import CollectingDispatcher
from rasa_sdk.events import SlotSet
from rasa_sdk.events import EventType  # Import EventType here
import json

import datetime
from rasa_sdk import Action, Tracker
from rasa_sdk.executor import CollectingDispatcher
from rasa_sdk.events import SlotSet

# Action to log mood
class ActionLogMood(Action):
    def name(self) -> str:
        return "action_log_mood"

    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: dict):
        mood = tracker.get_slot("mood")
        
        # Log the user's mood to the system (this can be saved to a file, database, etc.)
        dispatcher.utter_message(text=f"Got it! I've logged your mood as {mood}. Would you like some suggestions to feel better?")
        return []

# Action to suggest coping mechanisms based on the user's mood
class ActionSuggestCoping(Action):
    def name(self) -> str:
        return "action_suggest_coping"

    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: dict):
        mood = tracker.get_slot("mood")
        
        # Based on the mood, suggest relevant coping mechanisms
        if mood == "anxious":
            dispatcher.utter_message(text="For anxiety, try deep breathing exercises, meditation, or progressive muscle relaxation.")
        elif mood == "stressed":
            dispatcher.utter_message(text="For stress relief, consider trying yoga, journaling, or simply taking a short walk outside.")
        elif mood == "sad":
            dispatcher.utter_message(text="If you're feeling depressed, reaching out to a professional can help. You can also try activities that uplift you, such as engaging in hobbies or spending time with loved ones.")
        elif mood == "lonely":
            dispatcher.utter_message(text="Loneliness can be tough. Try reaching out to friends, family, or a counselor. Sometimes, joining a support group can help.")
        elif mood == "guilty":
            dispatcher.utter_message(text="Feeling guilty can be tough. Try self-compassion exercises, or consider talking to a therapist to process your feelings.")
        else:
            dispatcher.utter_message(text="You could try a mindfulness exercise, some journaling, or even a short walk.")
        return []

# Action to set reminders (e.g., exercise, meditation, etc.)
class ActionSetReminder(Action):
    def name(self) -> str:
        return "action_set_reminder"

    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: dict):
        activity = tracker.get_slot("activity")
        time = tracker.get_slot("time")
        
        # You can integrate with a reminder service or use the time and activity for your application logic.
        dispatcher.utter_message(text=f"Alright! I'll remind you to {activity} at {time}.")
        
        # Here, we just simulate a reminder action.
        return []

# Action to save user data (mood, activity) persistently
class ActionSaveData(Action):
    def name(self) -> str:
        return "action_save_data"

    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: dict):
        # Get mood and activity from slots
        mood = tracker.get_slot("mood")
        activity = tracker.get_slot("activity")

        # Save the data to a file or database
        with open("user_data.txt", "a") as file:
            file.write(f"{datetime.datetime.now()}: Mood: {mood}, Activity: {activity}\n")
        
        dispatcher.utter_message(text=f"Your mood ({mood}) and activity ({activity}) have been saved successfully.")
        return []

# Action to suggest activities (e.g., self-care, hobbies)
class ActionAskForActivities(Action):
    def name(self) -> str:
        return "action_ask_for_activities"

    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: dict):
        dispatcher.utter_message(text="Here are some activities you could try today: going for a walk, reading a book, or practicing yoga.")
        return []

# Action to provide mental health resources (websites, helplines, etc.)
class ActionAskForResources(Action):
    def name(self) -> str:
        return "action_ask_for_resources"

    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: dict):
        dispatcher.utter_message(text="Here are some resources you may find helpful: [Mental Health Resources](https://www.mentalhealth.gov), [Crisis Helplines](https://www.crisistextline.org)")
        return []

# Action to track mood over time (tracking history of moods)
class ActionMoodTracking(Action):
    def name(self) -> str:
        return "action_mood_tracking"

    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: dict):
        # Example: This could fetch past mood data from a database
        dispatcher.utter_message(text="It’s great to track your mood over time. Would you like to see a mood summary or chart?")
        return []

# Action to provide therapy resources
class ActionTherapyResources(Action):
    def name(self) -> str:
        return "action_therapy_resources"

    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: dict):
        dispatcher.utter_message(text="Here are some resources for finding a therapist: [Therapist Locator](https://www.psychologytoday.com/therapists).")
        return []

# Action to provide symptom-based resources for better understanding mental health issues
class ActionSymptomResources(Action):
    def name(self) -> str:
        return "action_symptom_resources"

    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: dict):
        dispatcher.utter_message(text="Here are some resources that can help you understand mental health symptoms: [Mental Health Symptoms](https://www.nami.org/learn-more/mental-health-conditions).")
        return []

# Action to provide specific resources for managing anxiety
class ActionAnxietyRelief(Action):
    def name(self) -> str:
        return "action_anxiety_relief"

    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: dict):
        dispatcher.utter_message(text="For anxiety, try deep breathing exercises, meditation, or progressive muscle relaxation.")
        return []

# Action to provide specific resources for managing stress
class ActionStressRelief(Action):
    def name(self) -> str:
        return "action_stress_relief"

    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: dict):
        dispatcher.utter_message(text="For stress relief, consider trying yoga, journaling, or simply taking a short walk outside.")
        return []

# Action to provide specific resources for managing depression
class ActionDepressionHelp(Action):
    def name(self) -> str:
        return "action_depression_help"

    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: dict):
        dispatcher.utter_message(text="If you're feeling depressed, reaching out to a professional can help. You can also try activities that uplift you, such as engaging in hobbies or spending time with loved ones.")
        return []

# Action to provide specific support for loneliness
class ActionLonelinessSupport(Action):
    def name(self) -> str:
        return "action_loneliness_support"

    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: dict):
        dispatcher.utter_message(text="Loneliness can be tough. Try reaching out to friends, family, or a counselor. Sometimes, joining a support group can help.")
        return []

# Action to help with managing guilt feelings
class ActionGuiltRelief(Action):
    def name(self) -> str:
        return "action_guilt_relief"

    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: dict):
        dispatcher.utter_message(text="Feeling guilty can be tough. Try self-compassion exercises, or consider talking to a therapist to process your feelings.")
        return []



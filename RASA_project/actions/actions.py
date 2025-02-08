from typing import Any, Text, Dict, List
from rasa_sdk import Action, Tracker
from rasa_sdk.executor import CollectingDispatcher
from rasa_sdk.events import SlotSet
from rasa_sdk.events import EventType  # Import EventType here
import json

class ActionLogMood(Action):
    def name(self) -> Text:
        return "action_log_mood"

    async def run(
        self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: Dict[Text, Any]
    ) -> List[Dict[Text, Any]]:
        mood = tracker.get_slot("mood")
        if mood:
            dispatcher.utter_message(text=f"Got it! I've logged your mood as {mood}.")
            # Save mood to a text file (or database in the future)
            with open("mood_logs.txt", "a") as file:
                file.write(f"Mood: {mood}\n")
            return [SlotSet("mood", mood)]
        else:
            dispatcher.utter_message(text="I couldn't detect your mood. Please try again.")
            return []

class ActionSuggestCoping(Action):
    def name(self) -> Text:
        return "action_suggest_coping"

    async def run(
        self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: Dict[Text, Any]
    ) -> List[Dict[Text, Any]]:
        dispatcher.utter_message(
            text="Here are some suggestions: mindfulness exercises, journaling, or a short walk."
        )
        return []

class ActionSetReminder(Action):
    def name(self) -> Text:
        return "action_set_reminder"

    async def run(
        self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: Dict[Text, Any]
    ) -> List[Dict[Text, Any]]:
        activity = tracker.get_slot("activity")
        time = tracker.get_slot("time")
        if activity and time:
            dispatcher.utter_message(
                text=f"Reminder set! I'll remind you to {activity} at {time}."
            )
            # Save reminder to a text file
            with open("reminders.txt", "a") as file:
                file.write(f"Reminder: {activity} at {time}\n")
            return []
        else:
            dispatcher.utter_message(
                text="I couldn't set the reminder. Make sure you specify the activity and time."
            )
            return []

class ActionSaveData(Action):
    def name(self) -> Text:
        return "action_save_data"

    def run(
        self,
        dispatcher: CollectingDispatcher,
        tracker: Tracker,
        domain: Dict[Text, Any],
    ) -> List[EventType]:
        # Retrieve user data
        user_data = {
            "messages": [],
            "intents": [],
            "entities": [],
            "slots": tracker.slots,
        }

        # Iterate over conversation events
        for event in tracker.events:
            if event["event"] == "user":
                user_data["messages"].append({"type": "user", "text": event["text"]})
                user_data["intents"].append(event.get("parse_data", {}).get("intent", {}).get("name", ""))
                user_data["entities"].extend(event.get("parse_data", {}).get("entities", []))
            elif event["event"] == "bot":
                user_data["messages"].append({"type": "bot", "text": event["text"]})

        # Save user data to a file
        with open("conversation_log.json", "w") as file:
            json.dump(user_data, file, indent=4)

        # Inform the user that data has been saved
        dispatcher.utter_message(text="Your data has been saved successfully.")

        return []

class ActionAskForActivities(Action):
    def name(self):
        return "action_ask_for_activities"

    def run(self, dispatcher, tracker, domain):
        dispatcher.utter_message(text="You can try activities like going for a walk, reading a book, or doing some yoga.")
        return []

class ActionAskForResources(Action):
    def name(self):
        return "action_ask_for_resources"

    def run(self, dispatcher, tracker, domain):
        dispatcher.utter_message(text="Here are some links: [Mental Health Resources](https://www.mentalhealth.gov), [Crisis Helplines](https://www.crisistextline.org)")
        return []

class ActionBreakDownTasks(Action):
    def name(self):
        return "action_break_down_tasks"

    def run(self, dispatcher, tracker, domain):
        dispatcher.utter_message(text="Let’s break it down. What’s one small task you can start with?")
        return []

class ActionFinancialAdvice(Action):
    def name(self):
        return "action_financial_advice"

    def run(self, dispatcher, tracker, domain):
        dispatcher.utter_message(text="Try focusing on what you *can* control—small steps like budgeting or seeking scholarships.")
        return []

class ActionConnectPeople(Action):
    def name(self):
        return "action_connect_people"

    def run(self, dispatcher, tracker, domain):
        dispatcher.utter_message(text="Starting small can help—maybe talking to one person at a time or joining an online group first?")
        return []

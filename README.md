# Personal Task Manager - Assignment 03 Mini Project

**Student ID:** 11254  
**Batch:** 8th Batch, Software Engineering  
**Tech Stack:** Android (Kotlin), XML, Material Design

---

## 📱 App Description
The Personal Task Manager is a lightweight, offline-first Android application designed to help users create, view, and manage their daily tasks and notes. The app provides a clean, intuitive interface for users to keep track of their productivity without requiring an internet connection or backend server.

### Core Features:
* **Add Tasks:** Create new tasks with a title and description.
* **View Tasks:** View all saved tasks in a scrollable list.
* **Manage State:** Mark tasks as completed (with a visual strike-through effect) or permanently delete them.
* **Data Persistence:** All tasks are saved locally on the device and persist across application restarts.
* **Lifecycle Aware:** The UI gracefully handles screen rotations without losing unsaved text or list data.

---

## 🏗️ Brief Explanation of Design Choices

To meet the assignment constraints while maintaining clean software engineering principles, the following design choices were made:

### 1. Architecture (MVVM)
The application utilizes the **Model-View-ViewModel (MVVM)** architecture. The UI logic (Activities/Adapters) is strictly separated from the data logic (`TaskRepository`) via the `TaskViewModel`. This ensures that the application is scalable, maintainable, and gracefully handles Android lifecycle events (like screen rotations) without losing data.

### 2. Data Persistence (SharedPreferences & Gson)
For local data storage, I chose `SharedPreferences` combined with the `Gson` library. Instead of implementing a heavy SQLite database (like Room) for a simple list, converting the custom `Task` data class into a serialized JSON string provided a lightweight, fast, and effective persistence mechanism.

### 3. User Interface (Material Design)
The UI was built using XML with a focus on **Material Design Guidelines** to provide "clarity over complexity."
* `MaterialCardView` is used for list items to provide elevation and structure.
* `TextInputLayout` (OutlinedBox) is used for data entry, providing built-in floating hints for a better user experience.
* An `ExtendedFloatingActionButton` is used for the primary call-to-action, ensuring it is highly visible and accessible.

### 4. Secure Coding Awareness
Two specific secure coding practices were implemented and commented on in the source code:
1. **Secure Storage (`Context.MODE_PRIVATE`):** SharedPreferences is instantiated in private mode to prevent other malicious applications on the device from accessing or tampering with the user's personal notes.
2. **Input Validation:** The `AddEditTaskActivity` explicitly validates that the user input is not empty before processing the data, preventing the storage of malformed data that could lead to unexpected crashes or injection vulnerabilities.
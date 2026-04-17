# 📝 Task Manager

A Java-based task management application built to practice **object-oriented programming (OOP)** concepts.  
The project includes both a **console interface** and a **JavaFX GUI (in progress)**.

---

## ✨ Features

### 🧩 Core Functionality
- Create, edit and delete tasks
- Mark tasks as done / undone
- View all tasks or filter open tasks
- Sort and manage tasks

### 🏷️ Task Properties
- Set priority (`LOW`, `MEDIUM`, `HIGH`)
- Assign custom categories (e.g. *Uni, Work, Personal*)
- Automatic creation timestamp (`LocalDateTime`)

### 📋 Subtasks
- Add and manage subtasks
- Track progress based on completed subtasks
- A task is automatically considered **done when all subtasks are done**

### ✅ Validation & Error Handling
- Input validation (empty values, max length, etc.)
- Custom exceptions:
  - `InvalidTitleException`
  - `InvalidPriorityException`
  - `InvalidCategoryException`
  - `InvalidIndexException`

### 💾 Persistence
- Save tasks to a file
- Simple file-based storage (`TaskFileService`)

---

## 🖥️ User Interfaces

### 💻 Console UI
- Interactive menu-based system
- Full CRUD functionality
- Input validation and error messages

### 🖼️ JavaFX GUI (Work in Progress)
- Add and edit tasks
- Priority selection via ComboBox
- Task list visualization
- Error handling with dialog windows

---

## 🛠️ Tech Stack

### Languages & Frameworks
- Java
- JavaFX

### Concepts
- Object-Oriented Programming (OOP)
- Encapsulation
- Collections & Streams
- Exception Handling
- Layered Architecture

### Tools
- IntelliJ IDEA
- Git & GitHub
- Maven

---

## ▶️ Run the Project

Clone the repository:

```bash
git clone https://github.com/Jakoobly/Task-Manager.git

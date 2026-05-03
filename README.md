# 🎓 UFH Student Resource Hub
**Module: CSC 313 - Mobile Application Development**

## 🚨 The Problem Statement
University students constantly need to exchange academic resources—whether that means buying and selling second-hand textbooks, sharing study guides, or finding peer tutors. However, traditional campus notice boards are outdated, and broad social media groups are cluttered and distracting. Students lack a centralized, localized platform that allows them to quickly find specific academic resources on campus and instantly connect with their peers to organize the exchange. 

## 💡 Project Overview
The UFH Student Resource Hub is a lightweight, easy-to-use Android application built to solve this communication gap. It acts as a digital campus board where students can list academic materials or study topics. Instead of relying on complex built-in messaging, the app intelligently bridges the gap by routing users directly to WhatsApp, allowing students to seamlessly arrange meetups or resource exchanges using the tools they already use every day.

---

## 🚀 Key Features
*   **Create Resource Listings:** Students can easily post items, topics, or resources they have available, along with essential details.
*   **Smart Discovery Search:** A built-in search engine allows students to quickly find what they need by searching either the resource name (e.g., "Calculus Textbook") or the student's name.
*   **One-Click WhatsApp Connection:** When a student finds a resource they need, a single tap packages the information and opens a direct WhatsApp chat with the poster to finalize the details.
*   **Offline Reliability (Local Storage):** The app uses a local database (SQLite) to safely save all posts and information directly on the phone, meaning it works quickly and remembers data even if the app is closed.
*   **Easy Inventory Management:** Users can delete or remove listings once an exchange is completed to keep the platform clean and up to date.

---

## 👥 Meet the Development Team

| Name | Role | What They Did |
| :--- | :--- | :--- |
| **Lugayeni** | **Database Lead** | Built the local storage system so the app securely remembers all posts and user data. |
| **Abahle** | **UI & Logic Developer** | Designed the user interface screens and made sure the app handles user input smoothly. |
| **Kamva** | **Integration Lead** | Created the search functionality and built the bridge that connects the app directly to WhatsApp. |
| **Mpilwenhle** | **Quality Assurance (QA)** | Tested the app extensively on real hardware to catch bugs and ensure smooth performance. |
| **Ovayo** | **Documentation Lead** | Wrote the project report, managed the codebase notes, and structured the final presentation. |
| **Sisanda** | **Systems Analyst** | Tracked the assignment rubric to ensure every feature perfectly matched the CSC 313 requirements. |

---

## 🛠️ How It Was Built (The Tech)
*   **Language:** Java
*   **Development Environment:** Android Studio
*   **Data Storage:** SQLite (Local Database)
*   **External Integration:** Android Implicit Intents (for WhatsApp routing)

---

## ⚙️ How to Run the App
1. Download or clone the project folder to your computer.
2. Open the folder using **Android Studio**.
3. Let the system sync and build the files.
4. Press "Run" to launch the application on an Android emulator or a connected physical phone.

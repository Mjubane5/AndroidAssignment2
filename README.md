# 📚 University of Fort Hare Textbook Marketplace
**Module: CSC 313 - Mobile Application Development**

## 📖 Project Overview
A robust, decentralized marketplace application designed to facilitate the exchange of academic resources within the University of Fort Hare (UFH) community. The platform allows students to list, manage, and discover textbooks through a high-performance local database system, ensuring academic resources remain accessible and affordable.

---

## 🛠️ Technical Specifications
*   **Language:** Java (Android SDK)
*   **Database:** SQLite (Relational Data Persistence)
*   **Architecture:** Model-View-Controller (MVC)
*   **Minimum SDK:** Android 10+ (API 29)
*   **Testing Hardware:** Validated on **Samsung Galaxy S24 Ultra** and **Dell Latitude 5330**.

---

## 🚀 Key Features
*   **Permanent Data Persistence:** Utilizes a custom `DatabaseHelper` to manage a local SQLite instance for long-term storage of listings.
*   **Full CRUD Lifecycle:** 
    *   **Create:** Dynamic listing creation via `AddBookActivity`.
    *   **Read:** Intelligent search querying through `SearchActivity`.
    *   **Update/Delete:** Integrated functionality to remove sold listings and update stock.
*   **Inter-App Communication:** Implements **Implicit Intents** to facilitate instant buyer-seller communication via WhatsApp and other system-level sharing tools.

---

## 👥 Team & Role Assignments

| Name | Role | Core Responsibilities |
| :--- | :--- | :--- |
| **Lugayeni** | **Database Architect** | Developed the `DatabaseHelper` schema, managed SQLite versioning, and implemented core CRUD methods. |
| **Abahle** | **UI/UX & Logic Developer** | Designed the activity layouts and implemented the data validation logic for the listing entry system. |
| **Kamva** | **Integration Specialist** | Developed the search filtering algorithms and managed the Android Intent system for external sharing. |
| **Mpilwenhle** | **Quality Assurance (QA)** | Conducted extensive hardware testing on the S24 Ultra and managed unit testing for database operations. |
| **Ovayo** | **Documentation Lead** | Managed technical writing, repository documentation, and structured the final project report. |
| **Sisanda** | **Systems Analyst** | Defined functional requirements and ensured all module-specific rubric criteria were satisfied. |

---

## 📁 Project Roadmap
*   **Phase 1:** UI Prototyping & Package Structuring.
*   **Phase 2:** SQLite Schema Design and Model (`Textbook.java`) implementation.
*   **Phase 3:** Search logic optimization and Intent integration.
*   **Phase 4:** Final QA and performance testing on high-end hardware.

---

## ⚙️ Setup & Installation
1. Clone the repository to your local environment.
2. Open the project in **Android Studio**.
3. Ensure the `src/main/java` pathing is correctly indexed.
4. Build and deploy to a physical device or emulator.

---

## 🛡️ Security & Scalability
Developed with a cybersecurity-conscious mindset, the app ensures local data integrity through controlled database access patterns. Future iterations aim to implement encrypted banking information fields and cloud-based authentication.

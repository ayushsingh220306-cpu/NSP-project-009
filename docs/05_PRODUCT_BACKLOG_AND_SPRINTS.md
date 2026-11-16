# Product Backlog & Agile Sprint Plan
## Student Scholarship & Fee Concession System

**Student Name:** AYUSH SINGH (PIET25CR009)  
**Academic Program:** B.Tech II Year – III Semester, Section 3CSD  
**Institution:** Poornima Institute of Engineering & Technology (PIET)  
**Methodology:** Agile / Scrum (Tailored for Academic Minor Project)  

---

## 1. Product Backlog

The Product Backlog breaks down the overall system requirements into manageable user stories and technical tasks, prioritized according to the PIET evaluation milestones.

| Story ID | Module Category | User Story / Work Item Description | Priority | Estimation (Story Pts) | Status |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **PB-01** | Database | Design normalized relational schema (`users`, `scholarships`, `fee_concessions`) with constraints | High | 5 | Completed |
| **PB-02** | Database | Create `schema.sql` script with table DDL and realistic seed records for demonstration | High | 3 | Completed |
| **PB-03** | JDBC | Develop singleton `DBConnection` manager with exception handling and driver registration | High | 5 | Completed |
| **PB-04** | Login | Design Swing `LoginFrame` with title banner, credentials input, and DB status indicator | High | 5 | Completed |
| **PB-05** | Login | Implement `UserDAO.authenticate` with `PreparedStatement` and role-based dashboard dispatch | High | 5 | Completed |
| **PB-06** | UI Style | Create reusable `UIStyle` class enforcing high-contrast buttons, fonts, and table styling | Medium | 3 | Completed |
| **PB-07** | Dashboard | Build `AdminDashboardFrame` with summary metric cards, module tabs, and action menus | High | 8 | Completed |
| **PB-08** | Dashboard | Build `UserDashboardFrame` providing students with read-only views and institutional policy | Medium | 5 | Completed |
| **PB-09** | Scholarship | Implement `ScholarshipDAO` with full CRUD methods (`insert`, `update`, `delete`, `getAll`) | High | 8 | Completed |
| **PB-10** | Scholarship | Build `ManageScholarshipPanel` combining entry form, `JTable`, and CRUD action buttons | High | 8 | Completed |
| **PB-11** | Scholarship | Implement delete confirmation dialog (`JOptionPane.YES_NO_OPTION`) for scholarship records | High | 3 | Completed |
| **PB-12** | Concession | Implement `FeeConcessionDAO` with full CRUD methods and sum aggregate queries | High | 8 | Completed |
| **PB-13** | Concession | Build `ManageFeeConcessionPanel` with entry form, `JTable`, and CRUD action buttons | High | 8 | Completed |
| **PB-14** | Concession | Implement delete confirmation dialog for fee concession records | High | 3 | Completed |
| **PB-15** | Search | Implement keyword-based search queries across 5 fields for Scholarships & Concessions | High | 5 | Completed |
| **PB-16** | Validation | Implement client-side form validation (empty check, positive numeric amount, date format) | High | 5 | Completed |
| **PB-17** | Exception | Implement comprehensive exception handling for SQL, network, and user input errors | High | 5 | Completed |
| **PB-18** | Security | Implement `ChangePasswordDialog` with old password verification and MySQL persistence | Medium | 5 | Completed |
| **PB-19** | Session | Implement Logout flow with confirmation dialog, window disposal, and login re-launch | Medium | 3 | Completed |
| **PB-20** | Testing | Develop automated `TestRunner` verifying all 19 required test cases against live MySQL | High | 8 | Completed |
| **PB-21** | UI Capture | Implement `ScreenshotGenerator` to produce authentic PNG screenshots for documentation | Medium | 5 | Completed |
| **PB-22** | Docs | Author complete PIET project documentation (SRS, Architecture, Testing, Final Report) | High | 8 | Completed |

---

## 2. Sprint Execution Plan

The project development was structured into 4 practical academic sprints aligned with the PIET evaluation framework:

```
+-----------------------------------------------------------------------------------------+
| Sprint 1: Planning, GUI Prototypes, Login & Dashboard Foundations                       |
| Goal: Establish UI look and feel, Login flow, and Admin/User dashboard wireframes.       |
+-----------------------------------------------------------------------------------------+
                                           |
                                           v
+-----------------------------------------------------------------------------------------+
| Sprint 2: Database Modeling, JDBC Architecture & Scholarship Management                  |
| Goal: MySQL schema creation, DBConnection setup, Scholarship DAO, and CRUD integration. |
+-----------------------------------------------------------------------------------------+
                                           |
                                           v
+-----------------------------------------------------------------------------------------+
| Sprint 3: Fee Concession Module, JTable Integration & Search Subsystem                  |
| Goal: Fee concession CRUD operations, table selection listeners, and keyword search.    |
+-----------------------------------------------------------------------------------------+
                                           |
                                           v
+-----------------------------------------------------------------------------------------+
| Sprint 4: Validation, Exception Handling, Testing & Final Documentation                 |
| Goal: Form checks, error dialogues, 19 automated tests, screenshots, and PIET report.   |
+-----------------------------------------------------------------------------------------+
```

---

### Sprint 1: Project Planning + GUI + Login + Dashboard
- **Sprint Goal:** Finalize functional requirements, design the UI color scheme in `UIStyle`, build `LoginFrame`, and implement the main layout for `AdminDashboardFrame` and `UserDashboardFrame`.
- **User Stories Completed:** PB-04, PB-06, PB-07, PB-08, PB-19.
- **Key Deliverables:**
  - Standardized color palette and high-contrast button styling (`UIStyle.java`).
  - Working Login screen with Username, Password, and Clear buttons.
  - Role-based dashboard wireframes with navigation menus and placeholder statistics cards.
  - Clean Logout mechanism returning user to the Login interface.

---

### Sprint 2: Database + JDBC + Scholarship Module
- **Sprint Goal:** Establish normalized database schema in MySQL, implement JDBC connection handling, and build the full Scholarship Management module.
- **User Stories Completed:** PB-01, PB-02, PB-03, PB-05, PB-09, PB-10, PB-11.
- **Key Deliverables:**
  - `sql/schema.sql` defining `users` and `scholarships` tables with seed records.
  - `DBConnection.java` with MySQL Connector/J driver registration and fail-safe connectivity checks.
  - `UserDAO.java` and `ScholarshipDAO.java` executing SQL via `PreparedStatement`.
  - `ManageScholarshipPanel.java` featuring record entry form, action buttons (Add, Update, Delete), and JTable data rendering.
  - Modal delete confirmation dialog (`JOptionPane.showConfirmDialog`).

---

### Sprint 3: Fee Concession Module + CRUD + Search
- **Sprint Goal:** Develop the Fee Concession module with complete CRUD operations, implement interactive `JTable` row selection, and build the real-time search subsystem.
- **User Stories Completed:** PB-12, PB-13, PB-14, PB-15.
- **Key Deliverables:**
  - `fee_concessions` table creation in MySQL.
  - `FeeConcessionDAO.java` supporting Insert, Update, Delete, Search, and Aggregate queries.
  - `ManageFeeConcessionPanel.java` enabling administrators to manage institutional waivers.
  - Dual-panel UI linking JTable selection listeners with form fields for immediate editing.
  - Search subsystem filtering records by Student ID, Student Name, Type, Academic Year, and Status, accompanied by "Show All" reset buttons.

---

### Sprint 4: Validation + Exception Handling + Testing + Final Integration
- **Sprint Goal:** Implement robust form validation checks, handle database connectivity and SQL errors gracefully, execute comprehensive automated verification, and author all project reports.
- **User Stories Completed:** PB-16, PB-17, PB-18, PB-20, PB-21, PB-22.
- **Key Deliverables:**
  - Form validations enforcing non-empty fields, positive numeric amounts (`BigDecimal > 0`), and ISO date formats (`YYYY-MM-DD`).
  - User-friendly warning and error dialogs preventing application crashes.
  - `ChangePasswordDialog.java` validating existing credentials before updating passwords in MySQL.
  - `TestRunner.java` executing and passing all 19 mandatory test cases against live MySQL.
  - `ScreenshotGenerator.java` capturing authentic high-resolution PNG screenshots.
  - Complete PIET project documentation package and runnable build scripts (`compile_and_run.bat` / `.ps1`).

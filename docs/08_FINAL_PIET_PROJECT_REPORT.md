# A MINOR PROJECT REPORT (NSP)
# ON
# STUDENT SCHOLARSHIP & FEE CONCESSION SYSTEM

---

### Submitted in partial fulfillment of the requirements for the award of the degree of
### **BACHELOR OF TECHNOLOGY**
### In
### **COMPUTER SCIENCE & ENGINEERING**

---

### **Submitted By:**
**AYUSH SINGH**  
**Student ID: PIET25CR009**  
**Semester: III Semester (Session: 2026–27)**  
**Section: 3CSD**  

---

### **Under the Supervision of:**
**Faculty Coordinator / Project Guide**  
Department of Computer Science & Engineering  

---

### **POORNIMA INSTITUTE OF ENGINEERING & TECHNOLOGY**
**ISI-2, RIICO Institutional Area, Sitapura, Jaipur, Rajasthan 302022**  
**Affiliated to Rajasthan Technical University (RTU), Kota**  

---

\newpage

## CANDIDATE'S DECLARATION

I hereby declare that the Minor Project (NSP) entitled **"Student Scholarship & Fee Concession System"** submitted to the Department of Computer Science & Engineering, **Poornima Institute of Engineering & Technology (PIET)**, Jaipur, in partial fulfillment of the requirements for the award of the degree of **Bachelor of Technology (B.Tech)** in Computer Science & Engineering, is an authentic record of our own work carried out under the supervision of the Department faculty during the academic session **2026–27**.

The matter embodied in this report has not been submitted by me for the award of any other degree or diploma to any other University or Institute.

---

**Date:** 08 September 2026  
**Place:** Jaipur, Rajasthan  

**AYUSH SINGH**  
Student ID: PIET25CR009  
B.Tech II Year, III Semester (Section 3CSD)  
Poornima Institute of Engineering & Technology, Jaipur  

---

\newpage

## CERTIFICATE OF APPROVAL

This is to certify that the Minor Project (NSP) entitled **"Student Scholarship & Fee Concession System"**, submitted by **AYUSH SINGH (Student ID: PIET25CR009)**, a student of B.Tech II Year – III Semester (Section 3CSD), in partial fulfillment of the requirements for the award of the Degree of Bachelor of Technology in Computer Science & Engineering at **Poornima Institute of Engineering & Technology, Jaipur**, is approved for submission.

The student has satisfactorily demonstrated the complete working desktop application, relational database schema in MySQL, JDBC data access layer, input validation, exception handling, and all functional criteria stipulated by the PIET Minor Project evaluation scheme.

---

**Project Guide / Supervisor**  
Department of Computer Science & Engineering  
Poornima Institute of Engineering & Technology  

---

**Head of Department (HOD)**  
Department of Computer Science & Engineering  
Poornima Institute of Engineering & Technology  

---

\newpage

## ACKNOWLEDGEMENT

I express my deepest gratitude to the management of **Poornima Institute of Engineering & Technology (PIET)**, Jaipur, for providing state-of-the-art computational infrastructure and an encouraging learning atmosphere.

I am immensely grateful to the **Head of Department (HOD)** and my **Project Guide** in the Department of Computer Science & Engineering for their insightful guidance, constant encouragement, and constructive critique throughout the conception, development, and evaluation of this Minor Project.

I also extend my sincere appreciation to the faculty members of the Computer Science & Engineering department for equipping me with the strong foundational concepts in Object-Oriented Programming, Java Swing, Relational Database Management Systems, and JDBC required to bring this project to fruition.

Finally, I thank my parents and peers for their continuous moral support throughout this academic endeavor.

---

**AYUSH SINGH**  
Student ID: PIET25CR009  

---

\newpage

## ABSTRACT

In collegiate institutions, managing student financial assistance, merit-based grants, and fee concession waivers is an essential administrative responsibility. When handled through fragmented paper files, loose register entries, or unstructured spreadsheets, the process suffers from delays, verification difficulties, risk of erroneous data entry, and a lack of self-service visibility for students.

The **Student Scholarship & Fee Concession System** is a standalone, desktop-based management application engineered to solve these challenges. Built in strict adherence to the PIET Minor Project (NSP) curriculum and marking guidelines, the system uses a standard, robust, and beginner-friendly technology stack: **Java, Java Swing, JDBC, MySQL, and IntelliJ IDEA**.

The application implements a dual-role access hierarchy:
1. **Administrator (Admin):** Full management capabilities, including real-time summary statistics, complete CRUD (Create, Read, Update, Delete) operations for scholarship and fee concession records, keyword search across multiple attributes, password management, and secure session logout.
2. **Student / User:** Restricted, read-only access allowing enrolled students to search and view scholarship and concession records in a structured `JTable`, inspect institutional guidelines, and manage their individual account password without permission to modify institutional records.

Data persistence is handled by a normalized MySQL database (`scholarship_fee_db`) featuring tables for `users`, `scholarships`, and `fee_concessions`. Architectural separation is achieved using the Data Access Object (DAO) pattern with `PreparedStatement` to ensure SQL injection protection and clean exception handling. The user interface incorporates high-contrast button styling, dynamic `JTable` rendering, and user-friendly `JOptionPane` dialogs, preventing application crashes. The system was verified through an automated test suite executing 19 end-to-end test scenarios with a 100% pass rate.

---

## KEYWORDS
`Java Swing`, `JDBC`, `MySQL`, `Desktop Application`, `CRUD Operations`, `PreparedStatement`, `Scholarship Management`, `Fee Concession`, `DAO Pattern`, `PIET Minor Project`.

---

\newpage

## TABLE OF CONTENTS

- **Candidate's Declaration**
- **Certificate of Approval**
- **Acknowledgement**
- **Abstract**
- **Keywords**
- **List of Figures**
- **List of Tables**
- **Chapter 1 – Introduction**
  - 1.1 Background & Context
  - 1.2 Aim and Objectives
  - 1.3 Problem Statement
  - 1.4 Software Requirements
  - 1.5 Hardware Requirements
- **Chapter 2 – Literature Survey & Existing Systems**
  - 2.1 Overview of Conventional Institutional Workflows
  - 2.2 Comparative Study: Manual Registers vs. Desktop System
  - 2.3 Technology Selection Justification (Java Swing & JDBC)
- **Chapter 3 – Project Management & Development Alignment**
  - 3.1 Agile / Scrum Process Model (4 Sprints)
  - 3.2 14-Week Development Roadmap Alignment
  - 3.3 PIET Marking Scheme Alignment (Mid Term-I, II, III & External)
- **Chapter 4 – Technology Applied**
  - 4.1 Java Standard Edition (JDK 21 / 26)
  - 4.2 Java Swing Framework
  - 4.3 Java Database Connectivity (JDBC) & Driver
  - 4.4 MySQL Relational Database Server 8.4
  - 4.5 IntelliJ IDEA Development Environment
- **Chapter 5 – Product Backlog & Database Design**
  - 5.1 Product Backlog Work Items
  - 5.2 Entity-Relationship (ER) Architecture
  - 5.3 Database Normalization (1NF, 2NF, 3NF)
  - 5.4 Database Schema & DDL Specifications
- **Chapter 6 – Project Implementation & Architecture**
  - 6.1 Multi-Tier Layered Architecture
  - 6.2 Data Access Object (DAO) Pattern Implementation
  - 6.3 User Interface Modules & Custom Styling (`UIStyle`)
  - 6.4 Input Validation & Defensive Programming
  - 6.5 Exception Handling Strategy
- **Chapter 7 – Results & Discussion**
  - 7.1 Graphical User Interface Screens & Discussion
  - 7.2 Automated Verification Results (Tests 1 to 19)
  - 7.3 Performance, Usability & Contrast Observations
- **Benefits to Society & Higher Education**
- **Future Scope**
- **Annexures**
  - Annexure A: SQL Database Script (`schema.sql`)
  - Annexure B: How to Build & Run the Application
- **References**

---

\newpage

## LIST OF FIGURES

1. **Figure 2.1:** Typical Paper-Register Workflow Bottlenecks in Fee Clearance.
2. **Figure 4.1:** High-Level Interaction between Java Swing, JDBC, and MySQL.
3. **Figure 5.1:** Entity-Relationship (ER) Diagram of `scholarship_fee_db`.
4. **Figure 6.1:** 3-Tier Layered Architecture of the System.
5. **Figure 6.2:** System Process Flowchart for User Authentication and Role Routing.
6. **Figure 6.3:** Record Management (CRUD) and Search Operational Flowchart.
7. **Figure 7.1:** Login Screen (`01_login_screen.png`).
8. **Figure 7.2:** Admin Dashboard Overview (`02_admin_dashboard.png`).
9. **Figure 7.3:** Scholarship Record Management Panel (`03_scholarship_management.png`).
10. **Figure 7.4:** Fee Concession Management Panel (`04_fee_concession_management.png`).
11. **Figure 7.5:** Student / User Portal Read-Only View (`05_user_dashboard.png`).
12. **Figure 7.6:** Change Password Dialog (`06_change_password_dialog.png`).
13. **Figure 7.7:** Delete Confirmation Dialog (`07_delete_confirmation_dialog.png`).

---

## LIST OF TABLES

1. **Table 2.1:** Comparative Analysis: Manual Record Keeping vs. Developed System.
2. **Table 3.1:** 4-Sprint Agile Execution Summary.
3. **Table 3.2:** PIET 14-Week Development Roadmap Alignment.
4. **Table 5.1:** Product Backlog Items and Priority Allocations.
5. **Table 5.2:** Data Dictionary for `users` Table.
6. **Table 5.3:** Data Dictionary for `scholarships` Table.
7. **Table 5.4:** Data Dictionary for `fee_concessions` Table.
8. **Table 7.1:** Summary of 19 Automated Verification Tests and Outcomes.

---

\newpage

## CHAPTER 1 – INTRODUCTION

### 1.1 Background & Context
Institutions of higher education offer multiple student assistance initiatives, including academic merit scholarships, sports excellence awards, girl child STEM grants, economically weaker section (EWS) waivers, sibling concessions, and staff ward fee reductions. In an institution like Poornima Institute of Engineering & Technology (PIET), hundreds of applications are processed across semesters.

Traditionally, maintaining these financial concessions through physical registers or disconnected spreadsheets results in human calculation errors, misplacement of records, delays in semester fee reconciliations, and repeated student visits to administrative counters. To eliminate these operational bottlenecks, the **Student Scholarship & Fee Concession System** provides a centralized, secure, and intuitive desktop platform developed using Java Swing and MySQL.

### 1.2 Aim and Objectives
**Aim:** To develop, deploy, and evaluate a desktop management application utilizing Java Swing and JDBC to administer student scholarship and fee concession records with full CRUD, search, role-based authorization, and input validation capabilities.

**Objectives:**
1. Design and build a graphical user interface using Java Swing.
2. Develop a normalized MySQL database (`scholarship_fee_db`) to store user accounts, scholarships, and fee waivers.
3. Establish robust JDBC communication utilizing `PreparedStatement` to ensure SQL injection protection.
4. Implement complete CRUD (Create, Read, Update, Delete) functionality for scholarship and concession modules.
5. Enforce client-side input validation for non-empty fields, positive numeric figures, and valid dates.
6. Provide role-based access control distinguishing between Administrative officers and Students.
7. Integrate real-time keyword search and dynamic `JTable` rendering.
8. Provide reliable session termination (Logout) and password modification mechanisms.

### 1.3 Problem Statement
Manual maintenance of scholarship and fee concession records exhibits substantial limitations:
- **Redundancy and Data Loss:** Physical paperwork can get misplaced or degraded over time.
- **Slow Information Retrieval:** Locating a specific student's waiver history requires manual searches through registers.
- **Absence of Validation:** Non-computerized systems fail to prevent erroneous amounts or duplicate grants.
- **Opacity for Applicants:** Students lack a quick way to check if their scholarship or concession application has been approved, rejected, or remains pending.

### 1.4 Software Requirements
- **Operating System:** Windows 10 / Windows 11 (64-bit)
- **Java Platform:** Java Development Kit (JDK 21 or JDK 26)
- **GUI Library:** Java Swing (`javax.swing.*`, `java.awt.*`)
- **Database Server:** MySQL Server 8.4
- **Database Driver:** MySQL Connector/J 8.4.0 (`com.mysql.cj.jdbc.Driver`)
- **Development IDE:** IntelliJ IDEA (Community / Ultimate Edition)
- **Build / Scripting:** Windows Batch (`compile_and_run.bat`) / PowerShell (`compile_and_run.ps1`)

### 1.5 Hardware Requirements
- **Processor:** Intel Core i3 / AMD Ryzen 3 or higher (x86_64 architecture)
- **Memory (RAM):** 4 GB RAM minimum (8 GB recommended)
- **Storage Space:** 250 MB free disk space for JDK, MySQL database, and application binaries
- **Display Resolution:** Minimum 1280 x 720 (Recommended: 1920 x 1080)
- **Input Devices:** Standard Keyboard and Mouse

---

\newpage

## CHAPTER 2 – LITERATURE SURVEY & EXISTING SYSTEMS

### 2.1 Overview of Conventional Institutional Workflows
In conventional academic settings, student financial aid processes follow a manual pipeline:
1. Students submit paper application forms with supporting documents (income certificates, grade sheets, sports certificates).
2. Administrative staff manually verify criteria and record proposed amounts into physical ledgers.
3. Department committees review and manually cross-mark approvals or rejections.
4. Accounts desks check registers during fee clearance to apply waivers manually.

This paper-driven workflow creates bottlenecks: records cannot be cross-referenced quickly across academic sessions, finding aggregate totals requires manual summing, and records are vulnerable to physical damage.

### 2.2 Comparative Study: Manual Registers vs. Developed System

| Parameter | Manual Paper Registers | Unstructured Spreadsheets | Developed Java Swing System |
| :--- | :--- | :--- | :--- |
| **Data Storage** | Physical registers / files | Loose `.xlsx` / `.csv` files | Centralized MySQL relational database |
| **Search Speed** | Very slow (manual lookup) | Moderate (Ctrl+F) | Instantaneous keyword queries (< 10 ms) |
| **Data Integrity** | Prone to loss / tampering | Accidental cell overwrites | Primary keys, NOT NULL, DECIMAL types |
| **Input Validation** | None (human dependent) | Basic formula checks | Java regex, numeric & date validations |
| **Access Control** | Physical locks | Single file password | Role-based login (Admin vs. Student) |
| **Audit & Metrics** | Manual tallying | Manual sum formulas | Real-time aggregate metric cards |
| **Accidental Deletion** | Torn / discarded pages | One-click delete without prompt | Explicit modal confirmation dialog |

### 2.3 Technology Selection Justification
- **Java Swing:** Bundled within standard Java Runtime Environments, Swing enables cross-platform desktop UI without external web servers, heavy browser engines, or cloud latency.
- **JDBC (Java Database Connectivity):** Offers direct, low-overhead communication with MySQL using standardized SQL. By avoiding heavy object-relational mapping (ORM) frameworks like Hibernate, the codebase remains clear, educational, and maintainable for a B.Tech II-year student.
- **MySQL 8.4:** World-renowned open-source relational database known for ACID compliance, relational integrity, speed, and standard SQL support.

---

\newpage

## CHAPTER 3 – PROJECT MANAGEMENT & DEVELOPMENT ALIGNMENT

### 3.1 Agile / Scrum Process Model
The project was developed across 4 structured academic sprints:
- **Sprint 1 (Weeks 1–4):** Requirements gathering, wireframing, `UIStyle.java` styling, `LoginFrame`, and dashboard layouts.
- **Sprint 2 (Weeks 5–8):** MySQL schema design (`schema.sql`), `DBConnection.java`, `UserDAO`, `ScholarshipDAO`, and Scholarship CRUD panel.
- **Sprint 3 (Weeks 9–12):** `FeeConcessionDAO`, Fee Concession panel, `JTable` selection listeners, multi-field search, and "Show All" reset.
- **Sprint 4 (Weeks 13–14):** Form input validation, exception handling, `ChangePasswordDialog`, automated test suite (19 tests), screenshot generation, and final report.

### 3.2 14-Week Development Roadmap Alignment
Development proceeded strictly according to the PIET 14-week minor project timeline:
- Weeks 1–2: Project orientation, objectives definition, and JDK/IntelliJ/MySQL setup.
- Weeks 3–4: GUI wireframing and complete Swing prototype design.
- Weeks 5–7: Event handling, modular panels, reaching 50% UI completion.
- Weeks 8–9: MySQL database creation, schema seeding, and JDBC connectivity.
- Weeks 10–12: CRUD implementation, validation integration, JTable views, and search.
- Weeks 13–14: Defect resolution, UI contrast enhancement, automated testing, and report authoring.

### 3.3 PIET Marking Scheme Evidence Mapping
- **Mid Term-I (60 Marks):**
  - *Project Design & Documentation (20M):* Proposal, SRS, Problem Statement, System Architecture.
  - *Project GUI Prototype (20M):* Complete Swing screens (Login, Admin Dashboard, Student Dashboard, CRUD forms).
  - *Database Design (20M):* Normalized schema (`users`, `scholarships`, `fee_concessions`) in `schema.sql`.
- **Mid Term-II (60 Marks):**
  - *CRUD Implementation using JDBC (20M):* Full Create, Read, Update, Delete, and Search operations.
  - *Code Quality, Validation & Exception Handling (20M):* Clean modular code, `PreparedStatement`, input checks, error dialogs.
  - *Final Project Demonstration (20M):* Working desktop execution connected to MySQL.
- **Mid Term-III & External Examination:**
  - Continuous weekly assessment logs, test suite reports, and comprehensive spiral-bound project report.

---

\newpage

## CHAPTER 4 – TECHNOLOGY APPLIED

### 4.1 Java Standard Edition (JDK 21 / 26)
Java provides object-oriented programming principles, strict compile-time type safety, automated garbage collection, and platform portability.

### 4.2 Java Swing Framework
The desktop graphical interface is constructed using standard Swing components:
- `JFrame`: Top-level window containers for `LoginFrame`, `AdminDashboardFrame`, and `UserDashboardFrame`.
- `JPanel`: Container panels organized via `BorderLayout`, `GridBagLayout`, and `GridLayout`.
- `JTable` & `JScrollPane`: Display tabular records with custom column renderers and headers.
- `JButton`: Styled with explicit high-contrast foreground and background colors.
- `JOptionPane`: Standardized modal feedback for confirmations, warnings, and errors.
- `JTabbedPane`: Tabbed container for module organization without window clutter.

### 4.3 Java Database Connectivity (JDBC)
JDBC provides standardized database communication:
- `com.mysql.cj.jdbc.Driver`: Registers the MySQL driver with the `DriverManager`.
- `Connection`: Manages active database sessions.
- `PreparedStatement`: Executes pre-compiled, parameterized SQL queries, protecting against SQL injection attacks.
- `ResultSet`: Navigates tabular query outputs.

### 4.4 MySQL Relational Database Server 8.4
MySQL stores records persistently on TCP port 3306. It enforces ACID properties (Atomicity, Consistency, Isolation, Durability) and structural constraints.

### 4.5 IntelliJ IDEA Development Environment
IntelliJ IDEA provides intelligent code completion, compile-time syntax verification, refactoring, and execution management.

---

\newpage

## CHAPTER 5 – PRODUCT BACKLOG & DATABASE DESIGN

### 5.1 Product Backlog Work Items
The product backlog comprises 22 granular user stories and engineering tasks categorized into Database, JDBC, Login, Dashboards, Scholarship Management, Fee Concession Management, Search, Form Validation, Exception Handling, Security, Testing, and Documentation. All 22 tasks are completed and verified.

### 5.2 Entity-Relationship (ER) Architecture
The relational database model comprises three entities:
1. `users`: Stores user identity, hashed/plain credentials, full name, and role (`Admin`/`User`).
2. `scholarships`: Stores student scholarship applications, category, sanctioned grant, session, status, and remarks.
3. `fee_concessions`: Stores student institutional fee waiver records, concession category, discount amount, status, and audit notes.

### 5.3 Database Normalization
The database satisfies **3NF**:
- **1NF:** Single-valued atomic attributes.
- **2NF:** All non-key attributes fully depend on the primary keys (`user_id`, `scholarship_id`, `concession_id`).
- **3NF:** No transitive dependencies.

### 5.4 Database Schema (DDL)
```sql
CREATE DATABASE scholarship_fee_db;
USE scholarship_fee_db;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'User',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE scholarships (
    scholarship_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id VARCHAR(30) NOT NULL,
    student_name VARCHAR(100) NOT NULL,
    scholarship_name VARCHAR(100) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    academic_year VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'Pending',
    remarks VARCHAR(255),
    applied_date DATE NOT NULL
);

CREATE TABLE fee_concessions (
    concession_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id VARCHAR(30) NOT NULL,
    student_name VARCHAR(100) NOT NULL,
    concession_type VARCHAR(100) NOT NULL,
    concession_amount DECIMAL(10,2) NOT NULL,
    academic_year VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'Pending',
    remarks VARCHAR(255),
    applied_date DATE NOT NULL
);
```

---

\newpage

## CHAPTER 6 – PROJECT IMPLEMENTATION & ARCHITECTURE

### 6.1 Multi-Tier Layered Architecture
The project strictly isolates concerns into four logical packages:
- `db`: Database connection lifecycle and health check (`DBConnection.java`).
- `model`: Domain entity beans (`User.java`, `Scholarship.java`, `FeeConcession.java`).
- `dao`: SQL data access objects (`UserDAO.java`, `ScholarshipDAO.java`, `FeeConcessionDAO.java`).
- `ui`: Presentation layer, frames, panels, custom styling, and dialogs.

### 6.2 Data Access Object (DAO) Pattern
All database operations are encapsulated in DAO classes. For instance, in `ScholarshipDAO.java`:
```java
public boolean insert(Scholarship s) {
    String sql = "INSERT INTO scholarships (student_id, student_name, scholarship_name, " +
                 "amount, academic_year, status, remarks, applied_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, s.getStudentId());
        stmt.setString(2, s.getStudentName());
        stmt.setString(3, s.getScholarshipName());
        stmt.setBigDecimal(4, s.getAmount());
        stmt.setString(5, s.getAcademicYear());
        stmt.setString(6, s.getStatus());
        stmt.setString(7, s.getRemarks());
        stmt.setDate(8, s.getAppliedDate());
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
```

### 6.3 Reusable UI Styling (`UIStyle.java`)
To guarantee visual appeal and ensure button text visibility across different Windows Look & Feels:
- Buttons use explicit background colors (Navy `#1E3A8A`, Emerald Green `#16A34A`, Crimson Red `#DC2626`, Amber `#D97706`) with pure white foreground text.
- Form fields feature padded borders and clean Segoe UI typography.
- Tables feature custom header cell renderers and alternating row selections.

### 6.4 Input Validation Logic
Before executing database queries, all inputs are validated:
1. Mandatory check: Field length after trimming must be $> 0$.
2. Numeric check: Amount parsed via `new BigDecimal(str)`; must satisfy `amount > 0`.
3. Date check: Validated using `Date.valueOf(str)` (`YYYY-MM-DD`).
4. Feedback: Meaningful `JOptionPane.WARNING_MESSAGE` dialogs guide the user to correct errors.

### 6.5 Exception Handling Strategy
- SQL and connection exceptions are caught within `try-with-resources` blocks.
- Failed connections prompt user-friendly error dialogs instead of terminating the application abruptly.
- Critical operations (such as row deletion) require explicit user confirmation via `JOptionPane.showConfirmDialog`.

---

\newpage

## CHAPTER 7 – RESULTS & DISCUSSION

### 7.1 Graphical User Interface Screens
The application was compiled, executed, and rendered to capture high-resolution screenshots:

1. **Login Screen (`01_login_screen.png`):** Displays PIET institutional banner, student watermark, username and password fields, high-contrast Login/Clear buttons, and live database status indicator.
2. **Admin Dashboard (`02_admin_dashboard.png`):** Features menu bar, navigation tabs, quick action launch buttons, project credential card, and live aggregate metrics cards:
   - Total Scholarship Applications: 6
   - Total Approved Scholarship Fund: ₹ 60,000.00
   - Total Fee Concession Requests: 5
   - Total Approved Concession Amount: ₹ 35,000.00
3. **Scholarship Management Panel (`03_scholarship_management.png`):** Features split card interface with entry form on the left, action buttons (Add, Update, Delete, Clear), and styled `JTable` on the right with real-time multi-field search.
4. **Fee Concession Management Panel (`04_fee_concession_management.png`):** Enables administrators to manage institutional fee concessions with CRUD operations, JTable display, and search capabilities.
5. **Student Portal (`05_user_dashboard.png`):** Provides enrolled students with restricted read-only views to browse and search records alongside institutional scholarship policies.
6. **Change Password Dialog (`06_change_password_dialog.png`):** Modal dialog validating old password before updating credentials in MySQL.
7. **Delete Confirmation Dialog (`07_delete_confirmation_dialog.png`):** Modal warning preventing accidental deletion of student records.

### 7.2 Automated Verification Results (Tests 1–19)
The automated test runner (`TestRunner.java`) executed 19 comprehensive test workflows against the live database:
- **Test 1:** Admin login (`admin` / `admin123`) $\rightarrow$ **PASS**
- **Test 2:** User login (`user1` / `user123`) $\rightarrow$ **PASS**
- **Test 3:** Invalid login rejected $\rightarrow$ **PASS**
- **Test 4:** Empty login fields blocked $\rightarrow$ **PASS**
- **Test 5:** Insert scholarship record $\rightarrow$ **PASS**
- **Test 6:** Read all scholarship records $\rightarrow$ **PASS**
- **Test 7:** Search scholarship records $\rightarrow$ **PASS**
- **Test 8:** Update scholarship record $\rightarrow$ **PASS**
- **Test 9:** Delete scholarship record $\rightarrow$ **PASS**
- **Test 10:** Insert fee concession record $\rightarrow$ **PASS**
- **Test 11:** Read all fee concession records $\rightarrow$ **PASS**
- **Test 12:** Search fee concession records $\rightarrow$ **PASS**
- **Test 13:** Update fee concession record $\rightarrow$ **PASS**
- **Test 14:** Delete fee concession record $\rightarrow$ **PASS**
- **Test 15:** Form validation checks (amount & date) $\rightarrow$ **PASS**
- **Test 16:** Live JDBC connection check $\rightarrow$ **PASS**
- **Test 17:** Password change & verification $\rightarrow$ **PASS**
- **Test 18:** Logout & session termination $\rightarrow$ **PASS**
- **Test 19:** Application re-authentication $\rightarrow$ **PASS**

**Result:** 19/19 tests passed (100% pass rate).

---

\newpage

## BENEFITS TO SOCIETY & HIGHER EDUCATION

1. **Equitable Financial Assistance:** Facilitates timely, transparent distribution of scholarships and fee concessions to deserving and economically underprivileged students.
2. **Elimination of Administrative Delays:** Reduces verification backlogs during semester fee clearance.
3. **Paperless Record Keeping:** Replaces perishable paper ledgers with an organized digital database, supporting environmental sustainability.
4. **Student Transparency:** Enables students to verify the sanction status of their applications without standing in administrative queues.

---

## FUTURE SCOPE

While the current system satisfies all B.Tech Minor Project requirements, prospective enhancements may include:
1. **Automated Notification Generation:** Producing printable PDF receipt slips for students upon scholarship approval.
2. **Export to CSV/Excel:** Allowing administrative staff to export sanctioned scholarship lists for auditing.
3. **Multi-Criteria Filter Engine:** Adding date-range filters and department-wise breakdown views.

---

\newpage

## ANNEXURES

### Annexure A: SQL Database Script (`schema.sql`)
The complete SQL script is located in `sql/schema.sql` and creates the `scholarship_fee_db` database, tables with primary keys, and sample seed records.

### Annexure B: How to Build & Run the Application
1. **Prerequisites:** JDK 21+ and MySQL Server 8.4 running on port 3306.
2. **Execute Database Script:**
   ```powershell
   Get-Content "sql\schema.sql" | mysql -u root
   ```
3. **One-Click Run (Windows Batch):** Double click `compile_and_run.bat`.
4. **One-Click Run (PowerShell):**
   ```powershell
   .\compile_and_run.ps1
   ```
5. **IntelliJ IDEA:** Open the project folder in IntelliJ IDEA and click the green Run button for `Main`.

---

## REFERENCES

1. Herbert Schildt, *Java: The Complete Reference*, 12th Edition, McGraw-Hill Education, 2021.
2. Paul Deitel and Harvey Deitel, *Java How to Program (Early Objects)*, 11th Edition, Pearson, 2018.
3. Joel Murach, *Murach's MySQL*, 3rd Edition, Mike Murach & Associates, 2019.
4. Oracle Corporation, *Java Platform, Standard Edition 21 API Specification - Package javax.swing*, Oracle Docs.
5. Oracle Corporation, *JDBC 4.3 Specification (JSR 221)*, Java Community Process.
6. Poornima Institute of Engineering & Technology (PIET), *Minor Project (NSP) Guidelines & Evaluation Scheme*, Department of Computer Science & Engineering, Session 2026–27.

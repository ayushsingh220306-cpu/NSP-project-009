# B.Tech Minor Project Proposal (NSP)

**Project Title:** Student Scholarship & Fee Concession System  
**Academic Session:** 2026–27  
**Department:** Computer Science & Engineering (Section: 3CSD)  
**Institution:** Poornima Institute of Engineering & Technology (PIET), Jaipur  

---

## Candidate Information
- **Student Name:** AYUSH SINGH
- **Student ID:** PIET25CR009
- **Course:** B.Tech II Year – III Semester
- **Subject / Scheme:** Minor Project (NSP)

---

## 1. Project Title
**STUDENT SCHOLARSHIP & FEE CONCESSION SYSTEM**

---

## 2. Introduction & Background
In higher educational institutions such as engineering colleges, hundreds of students apply annually for various categories of scholarships (merit-based, sports awards, need-based economic relief) and fee concessions (sibling waivers, economically weaker section discounts, staff ward allowances). In many colleges, these applications and approvals are handled through disparate paper forms, loose register entries, or unstructured spreadsheets. This leads to administrative delays, difficulty in tracking sanction status, risk of redundant concessions, and lack of transparency for students.

The **Student Scholarship & Fee Concession System** is conceived as a streamlined, reliable, desktop-based management application developed using standard core technologies—Java Swing, JDBC, and MySQL. It offers a dedicated administrative console for managing and auditing records, while providing students with a read-only portal to view and search the status of their financial support applications.

---

## 3. Problem Statement
The conventional manual and semi-manual workflows for institutional scholarships and fee waivers exhibit key deficiencies:
1. **Inefficient Record Keeping:** Difficulty in retrieving historical concession records and academic year trends.
2. **Lack of Centralized Database:** Information siloed in physical files makes verification time-consuming during semester fee clearance.
3. **Data Entry Errors:** Absence of automated input validation leads to invalid monetary figures or duplicate entries.
4. **Limited Student Visibility:** Students often need to make repeated inquiries at administrative offices to know their application status.

---

## 4. Aim and Objectives
### Aim
To design, implement, and evaluate a desktop management application for recording, processing, searching, and auditing student scholarship and fee concession records at Poornima Institute of Engineering & Technology.

### Objectives
1. **Role-Based Authentication:** Implement secure login interfaces for two distinct roles: Administrator (management permissions) and Student/User (read-only search and view permissions).
2. **Scholarship Lifecycle Management:** Provide complete CRUD (Create, Read, Update, Delete) and search operations for diverse scholarship categories.
3. **Fee Concession Management:** Provide full CRUD and search operations for institutional fee waivers.
4. **Relational Database Design:** Create a normalized MySQL schema (`users`, `scholarships`, `fee_concessions`) enforcing data integrity through primary keys and typed constraints.
5. **JDBC Connectivity & Modular DAO Architecture:** Implement clean data access objects using `PreparedStatement` to prevent SQL injection and decouple GUI logic from persistence.
6. **Input Validation & Exception Handling:** Enforce client-side format checks (non-empty fields, positive numeric amounts, valid dates) and handle SQL/database connectivity exceptions gracefully without application crashes.
7. **JTable & Visual Reporting:** Provide interactive table views with search, row selection, live summary statistics, and clear user action dialogs.

---

## 5. Technology Stack & Tools
- **Programming Language:** Java (JDK 21 / 26)
- **GUI Toolkit:** Java Swing (`JFrame`, `JPanel`, `JTable`, `JScrollPane`, `JTabbedPane`, `JButton`, `JOptionPane`)
- **Database Engine:** MySQL Server 8.4
- **Database Driver:** MySQL Connector/J 8.4.0 (JDBC)
- **Integrated Development Environment (IDE):** IntelliJ IDEA Community / Ultimate
- **Operating System:** Windows 10 / 11

---

## 6. Scope of the System
### In Scope
- Admin portal with summary dashboard cards (Total Applications, Approved Funds, Concession Requests, Total Waivers).
- Complete CRUD operations for Scholarship and Fee Concession records with delete confirmations.
- Real-time search filters matching Student ID, Student Name, Scholarship Type, Year, and Approval Status.
- Student portal providing restricted, read-only table views and guideline documents.
- User password update functionality.
- Session termination (Logout) returning to the login window without restarting the JVM.

### Out of Scope (PIET Strict Guidelines Compliance)
In strict accordance with the PIET Minor Project guidelines, no unnecessary enterprise or web technologies have been included:
- No web frameworks (Spring Boot, Node.js, React).
- No external cloud services or REST APIs.
- No payment gateway integrations.
- No SMS/Email OTP gateways.
- No AI/Blockchain or third-party proprietary dependencies.

---

## 7. Expected Outcomes
1. A fully compiled, error-free Java desktop application runnable from IntelliJ IDEA or command-line scripts.
2. A populated MySQL database script (`sql/schema.sql`) verifiable in MySQL Workbench.
3. Complete evidence for all PIET Minor Project (NSP) evaluation criteria across Mid Term I, Mid Term II, Mid Term III, and External Examination.

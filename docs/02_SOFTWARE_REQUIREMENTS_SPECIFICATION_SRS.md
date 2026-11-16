# Software Requirements Specification (SRS)
## Student Scholarship & Fee Concession System

**Student Name:** AYUSH SINGH  
**Student ID:** PIET25CR009  
**Branch & Section:** B.Tech II Year – III Semester, 3CSD  
**Institution:** Poornima Institute of Engineering & Technology (PIET), Jaipur  
**Academic Session:** 2026–27  
**Document Standard:** IEEE Std 830-1998 Aligned  

---

## 1. Introduction

### 1.1 Purpose
This Software Requirements Specification (SRS) details the functional and non-functional requirements for the desktop-based **Student Scholarship & Fee Concession System**. The document provides an unambiguous specification for implementation, verification, and evaluation under the PIET Minor Project (NSP) scheme.

### 1.2 Scope
The software product is a standalone Java Swing application connected via JDBC to a local MySQL relational database. The system automates institutional tracking of student scholarships and institutional fee waivers, supporting two user roles:
- **Admin**: Authorized college administrative officers with full permissions to record, update, delete, and audit scholarship and fee waiver grants.
- **User (Student)**: Enrolled students with restricted, read-only permissions to query records, verify application statuses, and inspect scholarship guidelines.

### 1.3 Definitions, Acronyms, and Abbreviations
- **PIET:** Poornima Institute of Engineering & Technology
- **NSP:** Minor Project scheme for B.Tech II Year students
- **GUI:** Graphical User Interface
- **JDBC:** Java Database Connectivity
- **DAO:** Data Access Object design pattern
- **CRUD:** Create, Read, Update, Delete
- **SQL:** Structured Query Language
- **EDT:** Event Dispatch Thread (Java Swing GUI thread)

---

## 2. Overall Description

### 2.1 Product Perspective
The system is self-contained and operates entirely on the desktop environment. It replaces physical registers and error-prone flat files with a structured relational database (`scholarship_fee_db`) managed via an intuitive Java Swing graphical interface.

```
+-------------------------------------------------------------+
|                      Client Desktop                         |
|  +-------------------------------------------------------+  |
|  |             Java Swing Desktop Interface              |  |
|  |  (LoginFrame, AdminDashboardFrame, UserDashboardFrame)|  |
|  +---------------------------+---------------------------+  |
|                              |                              |
|                              v                              |
|  +-------------------------------------------------------+  |
|  |                 Data Access Layer (DAO)               |  |
|  |     (UserDAO, ScholarshipDAO, FeeConcessionDAO)       |  |
|  +---------------------------+---------------------------+  |
|                              |                              |
|                              v                              |
|  +-------------------------------------------------------+  |
|  |                   JDBC Connection                     |  |
|  |            (DBConnection / PreparedStatement)          |  |
|  +---------------------------+---------------------------+  |
+------------------------------|------------------------------+
                               |
                               v (TCP Port 3306)
+-------------------------------------------------------------+
|                     MySQL Database Server                   |
|                  Database: scholarship_fee_db               |
|      Tables: users | scholarships | fee_concessions         |
+-------------------------------------------------------------+
```

### 2.2 User Roles & Permissions
1. **Administrator (Admin):**
   - Authenticate with administrator credentials.
   - View aggregated statistics on scholarships and fee waivers.
   - Add, edit, delete, and search scholarship records.
   - Add, edit, delete, and search fee concession records.
   - Update administrator account password.
   - Terminate session via Logout.
2. **Student / User:**
   - Authenticate with assigned student username and password.
   - View published scholarship and fee concession records in JTable.
   - Search records using Student ID, Name, or category keywords.
   - Read institutional policies and scholarship eligibility criteria.
   - Update account password.
   - Terminate session via Logout.
   - *Note:* Students are explicitly restricted from modifying or deleting database records.

### 2.3 Operating Environment
- **Operating System:** Microsoft Windows 10 / 11 (64-bit)
- **Java Runtime:** JDK 21 / JDK 26
- **Database Engine:** MySQL Server 8.4+ running on `localhost:3306`
- **RAM Requirement:** Minimum 2 GB (4 GB recommended)
- **Disk Space:** Minimum 100 MB free space

---

## 3. Specific Functional Requirements

### FR-1: Authentication & Role-Based Routing
- **FR-1.1:** The system shall display a login interface on startup prompting for Username and Password.
- **FR-1.2:** The system shall validate that neither field is submitted empty.
- **FR-1.3:** The system shall verify credentials against the `users` table via `PreparedStatement`.
- **FR-1.4:** If credentials match role `'Admin'`, the system shall route the user to `AdminDashboardFrame`.
- **FR-1.5:** If credentials match role `'User'`, the system shall route the user to `UserDashboardFrame`.
- **FR-1.6:** If authentication fails, a clear error dialog (`JOptionPane.ERROR_MESSAGE`) shall be presented.

### FR-2: Dashboard Metrics & Quick Actions
- **FR-2.1:** The Admin dashboard shall display four live metric cards:
  1. Total Scholarship Applications count.
  2. Total Approved Scholarship Fund (₹ sum).
  3. Total Fee Concession Requests count.
  4. Total Approved Concession Amount (₹ sum).
- **FR-2.2:** The dashboard shall provide quick-launch action buttons to open management modules and refresh live data.

### FR-3: Scholarship Record Management (CRUD)
- **FR-3.1 (Create):** The administrator shall be able to enter Student ID, Student Name, Scholarship Type, Amount, Academic Year, Status, Applied Date, and Remarks, and persist the record.
- **FR-3.2 (Read):** All records shall be rendered in a tabular `JTable` inside a `JScrollPane`.
- **FR-3.3 (Select):** Selecting a row in `JTable` shall populate the entry form for immediate inspection or modification.
- **FR-3.4 (Update):** The administrator shall be able to modify any field of a selected record and persist changes to MySQL.
- **FR-3.5 (Delete):** The system shall prompt the administrator with a modal confirmation dialog (`JOptionPane.YES_NO_OPTION`) prior to deleting any record.
- **FR-3.6 (Search):** The system shall support real-time keyword queries matching Student ID, Student Name, Scholarship Type, Academic Year, and Status.

### FR-4: Fee Concession Management (CRUD)
- **FR-4.1 (Create):** The administrator shall be able to register student fee concession requests including Student ID, Student Name, Concession Type, Concession Amount, Academic Year, Status, Applied Date, and Remarks.
- **FR-4.2 (Read):** All concession records shall be displayed in a formatted `JTable`.
- **FR-4.3 (Update):** Selected concession records can be updated via the entry form.
- **FR-4.4 (Delete):** Deleting a concession record shall require explicit confirmation.
- **FR-4.5 (Search):** The module shall provide instant keyword search and "Show All" reset.

### FR-5: Form Input Validation
- **FR-5.1:** All required fields (marked with `*`) must not be blank or whitespace-only.
- **FR-5.2:** Monetary amounts must be valid numeric values greater than zero (`amount > 0`).
- **FR-5.3:** Applied Date must adhere to the `YYYY-MM-DD` date format.
- **FR-5.4:** In case of validation failure, the application must highlight the issue through a warning dialog and retain user focus on the relevant input field.

### FR-6: Password Change & Security
- **FR-6.1:** Any logged-in user can access the "Change Password" modal dialog.
- **FR-6.2:** The dialog shall require Current Password, New Password, and Confirm New Password.
- **FR-6.3:** The system shall verify the current password against MySQL before updating.
- **FR-6.4:** The new password must be at least 4 characters long and match confirmation input.

### FR-7: Session Logout
- **FR-7.1:** The user can trigger Logout from the top banner or menu bar.
- **FR-7.2:** The system shall confirm intent, terminate the active dashboard window, and safely display a fresh `LoginFrame`.

---

## 4. Non-Functional Requirements

### 4.1 Usability & Visual Accessibility
- **High-Contrast Styling:** Every button must feature explicit background and foreground colors (Navy Blue, Emerald Green, Crimson Red, Amber, Dark Slate) to prevent Windows Look & Feel from rendering button text invisible.
- **Intuitive Layout:** Two-panel layout separating entry forms from data tables allows efficient data entry without modal dialog switching.

### 4.2 Reliability & Fault Tolerance
- **Database Failure Protection:** If the MySQL server is unreachable, the system displays a connection status banner and presents a helpful dialog rather than terminating abruptly.
- **Exception Safety:** All SQL operations utilize `try-with-resources` ensuring connections and statements are automatically closed.

### 4.3 Data Integrity
- Enforce schema constraints: Primary Keys with auto-increment, unique usernames, not-null constraints on mandatory fields, and typed `DECIMAL(10,2)` monetary values.

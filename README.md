# Student Scholarship & Fee Concession System

> **B.Tech II Year – III Semester Minor Project (NSP)**  
> **Poornima Institute of Engineering & Technology (PIET), Jaipur**  
> **Session:** 2026–27 | **Section:** 3CSD  
> **Student Name:** AYUSH SINGH  
> **Student ID:** PIET25CR009  

---

## 📌 Project Overview

The **Student Scholarship & Fee Concession System** is a desktop-based management application developed using **Java Swing, JDBC, and MySQL**. It provides a centralized institutional platform for administering student financial assistance, merit-based scholarships, and fee concession waivers.

The system is designed in strict compliance with the **PIET Minor Project (NSP) syllabus and marking scheme**, demonstrating:
- Modular Object-Oriented Java architecture with DAO separation.
- Relational database normalization (3NF) in MySQL.
- Parameterized JDBC database communication via `PreparedStatement`.
- Complete CRUD (Create, Read, Update, Delete) and multi-field keyword search functionality.
- Client-side input validation and graceful exception handling.
- Clear role-based authorization distinguishing between Administrator and Student/User accounts.

---

## 💻 Mandatory Technology Stack

- **Programming Language:** Java (JDK 21 / 26)
- **GUI Framework:** Java Swing (`JFrame`, `JPanel`, `JTable`, `JScrollPane`, `JTabbedPane`, `JButton`, `JOptionPane`)
- **Database Engine:** MySQL Server 8.4+
- **JDBC Driver:** MySQL Connector/J 8.4.0 (included in `lib/`)
- **IDE:** IntelliJ IDEA Community / Ultimate Edition
- **Build / Execution Scripts:** Windows Command Prompt (`compile_and_run.bat`) / PowerShell (`compile_and_run.ps1`)

---

## 🎯 Key Modules & Features

### 1. Role-Based Login System
- Clean card-based login interface with college banner and student identity watermark.
- Verifies credentials from the MySQL `users` table via `PreparedStatement`.
- Live MySQL connectivity indicator on the login screen.
- Routes automatically to **Admin Dashboard** or **Student Portal** based on assigned role.
- Validates empty fields and reports database connection failures gracefully.

### 2. Admin Dashboard & Metrics Overview
- Aggregated real-time statistic cards:
  - Total Scholarship Applications
  - Total Approved Scholarship Fund (₹)
  - Total Fee Concession Requests
  - Total Approved Concession Amount (₹)
- Quick-action buttons to launch modules or refresh database cache.
- Top navigation bar with **Change Password** and **Logout** actions.
- Menu bar (`Modules`, `Account`, `Help`).

### 3. Scholarship Record Management (Admin CRUD + Search)
- **Create:** Add new student scholarship records with validation.
- **Read:** Display all records in a formatted, zebra-styled `JTable`.
- **Update:** Select any row to populate the form and modify record details.
- **Delete:** Remove records with mandatory confirmation dialog (`JOptionPane.showConfirmDialog`).
- **Search:** Real-time search across Student ID, Name, Scholarship Type, Year, and Status.

### 4. Fee Concession Management (Admin CRUD + Search)
- **Create:** Register institutional fee concession requests (Sibling, EWS, Staff Ward, Merit).
- **Read:** Formatted `JTable` rendering all active concession records.
- **Update:** Modify concession amounts, categories, and approval states.
- **Delete:** Delete with safety confirmation dialog.
- **Search:** Instant keyword query and "Show All" reset.

### 5. Student Portal (Restricted Read-Only Mode)
- Read-only table access for enrolled students to search and verify their financial aid status.
- Entry form modification controls are disabled/hidden to prevent unauthorized edits.
- Dedicated tab displaying PIET institutional scholarship policies and guidelines.

### 6. Password Management & Logout
- Modal `ChangePasswordDialog` requiring old password verification before updating in MySQL.
- Seamless logout flow returning to the login window without restarting the JVM.

---

## 🔑 Demo Credentials

| Role | Username | Password | Full Name | Access Level |
| :--- | :--- | :--- | :--- | :--- |
| **Administrator** | `admin` | `admin123` | Ayush Singh (Admin) | Full CRUD & Management |
| **Student / User**| `user1` | `user123` | Rahul Sharma | Read-Only & Search |
| **Student / User**| `user2` | `user123` | Priya Verma | Read-Only & Search |
| **Student / User**| `user3` | `user123` | Amit Kumar | Read-Only & Search |

---

## 📁 Project Directory Structure

```text
NSP-project-009/
├── .idea/                                  # IntelliJ IDEA project configuration
│   ├── misc.xml
│   ├── modules.xml
│   ├── NSP-project-009.iml
│   └── runConfigurations/Main.xml
├── lib/
│   └── mysql-connector-j-8.4.0.jar         # Official MySQL JDBC Driver
├── src/
│   ├── Main.java                           # Application entry point
│   ├── TestRunner.java                     # Automated test harness (19 test cases)
│   ├── db/
│   │   └── DBConnection.java               # JDBC connection manager
│   ├── model/
│   │   ├── User.java                       # User entity bean
│   │   ├── Scholarship.java                # Scholarship record entity bean
│   │   └── FeeConcession.java              # Fee concession entity bean
│   ├── dao/
│   │   ├── UserDAO.java                    # User authentication & password DAO
│   │   ├── ScholarshipDAO.java             # Scholarship CRUD & Search DAO
│   │   └── FeeConcessionDAO.java           # Fee Concession CRUD & Search DAO
│   └── ui/
│       ├── UIStyle.java                    # Colors, fonts, high-contrast buttons, table styling
│       ├── LoginFrame.java                 # Login screen with live DB status
│       ├── AdminDashboardFrame.java        # Admin dashboard with metrics and tabs
│       ├── UserDashboardFrame.java         # Student portal (read-only view)
│       ├── ManageScholarshipPanel.java     # Scholarship CRUD & JTable panel
│       ├── ManageFeeConcessionPanel.java   # Fee Concession CRUD & JTable panel
│       ├── ChangePasswordDialog.java       # Password change modal dialog
│       └── ScreenshotGenerator.java        # Authentic Swing screen capture utility
├── sql/
│   └── schema.sql                          # MySQL database creation & seed data
├── docs/
│   ├── 01_PROJECT_PROPOSAL.md              # PIET Minor Project proposal
│   ├── 02_SOFTWARE_REQUIREMENTS_SPECIFICATION_SRS.md # IEEE-standard SRS
│   ├── 03_DATABASE_DESIGN_AND_SCHEMA.md    # ER diagram, data dictionary, 3NF
│   ├── 04_FLOWCHARTS_AND_SYSTEM_ARCHITECTURE.md     # Architecture & process flowcharts
│   ├── 05_PRODUCT_BACKLOG_AND_SPRINTS.md   # Agile backlogs across 4 sprints
│   ├── 06_PIET_14_WEEK_DEVELOPMENT_LOG.md  # 14-week developmental timeline
│   ├── 07_TESTING_DOCUMENTATION_AND_TEST_CASES.md   # 19 test execution logs
│   └── 08_FINAL_PIET_PROJECT_REPORT.md     # Spiral-bound ready comprehensive report
├── screenshots/                            # Generated UI screen captures
│   ├── 01_login_screen.png
│   ├── 02_admin_dashboard.png
│   ├── 03_scholarship_management.png
│   ├── 04_fee_concession_management.png
│   ├── 05_user_dashboard.png
│   ├── 06_change_password_dialog.png
│   └── 07_delete_confirmation_dialog.png
├── compile_and_run.bat                     # Windows Batch 1-click execution script
├── compile_and_run.ps1                     # PowerShell 1-click execution script
└── README.md                               # Project documentation
```

---

## ⚙️ How to Setup & Run

### Step 1: Database Setup in MySQL
1. Ensure MySQL Server is running on `localhost:3306`.
2. Open MySQL Workbench or Command Prompt and execute the `sql/schema.sql` script:
   ```bash
   mysql -u root -p < sql/schema.sql
   ```
   *(If your MySQL `root` user has no password, omit the `-p` flag).*
3. Verify that the `scholarship_fee_db` database is created with tables `users`, `scholarships`, and `fee_concessions`.

### Step 2: Running via IntelliJ IDEA
1. Open IntelliJ IDEA.
2. Select **File -> Open** and choose the `NSP-project-009` directory.
3. IntelliJ will automatically recognize the project structure and the `mysql-connector-j-8.4.0.jar` library in `lib/`.
4. Run `Main.java` or click the pre-configured green **Run (Main)** button in the top toolbar.

### Step 3: Running via Command Line (1-Click)
- **Windows Batch:** Double-click `compile_and_run.bat` or run:
  ```cmd
  compile_and_run.bat
  ```
- **PowerShell:**
  ```powershell
  .\compile_and_run.ps1
  ```

---

## 🧪 Automated Testing Verification

The project includes an automated test harness (`TestRunner.java`) that executes all 19 mandatory PIET test scenarios:

```bash
java -cp "bin;lib\mysql-connector-j-8.4.0.jar" TestRunner
```

### Test Suite Execution Output:
```text
=================================================================
 PIET MINOR PROJECT (NSP) - AUTOMATED VERIFICATION TEST SUITE   
 Student: AYUSH SINGH | Student ID: PIET25CR009 | Section: 3CSD 
 Project: Student Scholarship & Fee Concession System           
=================================================================

  [PASS] TEST 16: Database Connection                  ... OK
  [PASS] TEST 1: Admin Login                           ... OK
  [PASS] TEST 2: User Login                            ... OK
  [PASS] TEST 3: Invalid Login                         ... OK
  [PASS] TEST 4: Empty Login Fields                    ... OK
  [PASS] TEST 5: Add Scholarship Record                ... OK
  [PASS] TEST 6: View Scholarship Records              ... OK
  [PASS] TEST 7: Search Scholarship Record             ... OK
  [PASS] TEST 8: Update Scholarship Record             ... OK
  [PASS] TEST 9: Delete Scholarship Record             ... OK
  [PASS] TEST 10: Add Fee Concession Record            ... OK
  [PASS] TEST 11: View Fee Concession Records          ... OK
  [PASS] TEST 12: Search Fee Concession Record         ... OK
  [PASS] TEST 13: Update Fee Concession Record         ... OK
  [PASS] TEST 14: Delete Fee Concession Record         ... OK
  [PASS] TEST 15: Input Validation Checks              ... OK
  [PASS] TEST 17: Change Password Flow                 ... OK
  [PASS] TEST 18: Logout Flow & Session Termination    ... OK
  [PASS] TEST 19: Application Restart & Re-Authentication ... OK

=================================================================
 TEST EXECUTION SUMMARY
 Total Tests Executed : 19
 Passed               : 19
 Failed               : 0
 Status               : ALL 19 TESTS PASSED (100%)
=================================================================
```

---

## 🖼️ Application Screenshots

| Screen | Description | File Path |
| :--- | :--- | :--- |
| **Login Screen** | High-contrast login card with live DB indicator | `screenshots/01_login_screen.png` |
| **Admin Dashboard** | Live metric cards, quick actions, and project card | `screenshots/02_admin_dashboard.png` |
| **Scholarship Records** | Form entry + JTable view + CRUD + Search | `screenshots/03_scholarship_management.png` |
| **Fee Concessions** | Form entry + JTable view + CRUD + Search | `screenshots/04_fee_concession_management.png` |
| **Student Portal** | Restricted read-only view with policy guidelines | `screenshots/05_user_dashboard.png` |
| **Change Password** | Modal old-password verification dialog | `screenshots/06_change_password_dialog.png` |
| **Delete Confirmation** | Safety confirmation dialog preventing data loss | `screenshots/07_delete_confirmation_dialog.png` |

---

## 🎓 Academic Compliance & Evaluation Matrix

- **Mid Term-I:** Complete SRS, Project Proposal, Normalized Schema (`schema.sql`), and GUI Prototype.
- **Mid Term-II:** Complete JDBC CRUD implementation, input validations, error dialogs, and working application demo.
- **Mid Term-III & External:** 14-Week Development Log, automated test suite with 100% pass rate, and full spiral-bound-ready report (`docs/08_FINAL_PIET_PROJECT_REPORT.md`).

---

## 👨‍💻 Author

**AYUSH SINGH**  
Student ID: `PIET25CR009`  
B.Tech II Year – III Semester, Section 3CSD  
Department of Computer Science & Engineering  
Poornima Institute of Engineering & Technology (PIET), Jaipur  
Session: 2026–27  

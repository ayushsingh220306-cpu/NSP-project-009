# PIET 14-Week Development Roadmap & Weekly Progress Log
## Student Scholarship & Fee Concession System

**Student Name:** AYUSH SINGH  
**Student ID:** PIET25CR009  
**Branch & Section:** B.Tech II Year – III Semester, 3CSD  
**Institution:** Poornima Institute of Engineering & Technology (PIET)  
**Academic Session:** 2026–27  
**Evaluation:** Continuous Weekly Assessment & Mid-Term Milestone Tracking  

---

## Weekly Progress & Milestone Alignment

The table below outlines the 14-week progressive development lifecycle aligned strictly with the PIET Minor Project (NSP) syllabus and marking scheme:

| Week No. | Phase / Focus Area | Key Tasks & Technical Activities Planned | Progress & Deliverables Produced | Evaluation Milestone |
| :--- | :--- | :--- | :--- | :--- |
| **Week 1** | Orientation & Requirements | - Study PIET Minor Project guidelines and marking scheme.<br>- Review core Java fundamentals (OOP, collections, Swing basics).<br>- Define problem statement and objectives for Scholarship & Fee Concession System. | - Project Proposal formulated.<br>- Core functional scope locked: Admin & Student roles. | Pre-Milestone |
| **Week 2** | Environment Setup | - Install and configure Java Development Kit (JDK).<br>- Install IntelliJ IDEA Community/Ultimate edition.<br>- Install MySQL Server 8.4 and MySQL Workbench.<br>- Verify path variables and command-line compilation. | - Development environment verified.<br>- Local repository structure established. | Pre-Milestone |
| **Week 3** | Basic GUI & Login Design | - Explore Java Swing components (`JFrame`, `JPanel`, `JLabel`, `JTextField`, `JPasswordField`, `JButton`).<br>- Create preliminary wireframe layout for the Login screen.<br>- Test layout managers (`BorderLayout`, `GridBagLayout`, `FlowLayout`). | - Basic `LoginFrame` prototype designed.<br>- Clean field positioning and color styling explored. | Pre-Milestone |
| **Week 4** | Complete GUI Prototype | - Design full prototype layouts for Admin Dashboard, User Dashboard, and Management Panels.<br>- Establish `UIStyle.java` with explicit button colors and typography to prevent Windows Look & Feel contrast issues. | - Prototype screens for Admin and Student views.<br>- Visual consistency established across all windows. | **MID TERM-I** (GUI Prototype - 20M) |
| **Week 5** | Swing Event Handling | - Implement action listeners (`ActionListener`) on buttons.<br>- Handle window closing and state transitions.<br>- Add keyboard shortcut listener (Enter key triggers login). | - Interactive event handling on Login and Navigation buttons.<br>- Form clear actions implemented. | Mid Term-I Evidence |
| **Week 6** | GUI Modules & Panels | - Develop modular sub-panels: `ManageScholarshipPanel` and `ManageFeeConcessionPanel`.<br>- Create side-by-side card layout: Form on the left, Data Table on the right. | - Reusable management panels instantiated.<br>- Visual layout reached approximately 35% completion. | Mid Term-I Evidence |
| **Week 7** | GUI Refinement (50% Mark) | - Integrate `JTabbedPane` in `AdminDashboardFrame` for seamless tab switching.<br>- Design statistics summary cards for overview metrics.<br>- Build read-only mode for `UserDashboardFrame`. | - Complete front-end prototype reaches 50% milestone.<br>- Navigation between tabs and dialogs fully functional. | Mid Term-I Submission |
| **Week 8** | Database Modeling & Schema | - Design normalized database schema up to 3NF.<br>- Define tables: `users`, `scholarships`, `fee_concessions`.<br>- Assign primary keys, unique constraints, and check constraints.<br>- Author `sql/schema.sql` with sample seed records. | - Database `scholarship_fee_db` created in MySQL.<br>- Seed data successfully executed and verified in Workbench. | **MID TERM-I** (DB Design - 20M) |
| **Week 9** | JDBC Connectivity Layer | - Procure and add `mysql-connector-j-8.4.0.jar` to project classpath.<br>- Create `DBConnection.java` singleton manager.<br>- Implement connection testing and diagnostic reporting on startup. | - Live database connection established via JDBC.<br>- Status indicator on Login screen turns green on connection. | Pre-CRUD Milestone |
| **Week 10** | CRUD Implementation | - Implement `UserDAO`, `ScholarshipDAO`, and `FeeConcessionDAO`.<br>- Write parameterized SQL queries using `PreparedStatement`.<br>- Implement Create (INSERT), Read (SELECT), Update (UPDATE), and Delete (DELETE). | - Full database CRUD operational from Java code.<br>- Verified with initial sample records in MySQL. | **MID TERM-II** (CRUD Implementation - 20M) |
| **Week 11** | GUI-Database Integration | - Connect Swing forms to DAO methods.<br>- Add form-level input validation (non-empty, positive decimal checks, date formatting).<br>- Implement modal delete confirmation dialogs (`JOptionPane.YES_NO_OPTION`). | - Forms persist directly to MySQL.<br>- Data validation prevents invalid inputs from reaching DB. | **MID TERM-II** (Validation - 20M) |
| **Week 12** | JTable & Search Subsystem | - Implement dynamic `DefaultTableModel` data loading.<br>- Attach row selection listeners to auto-fill form fields upon clicking a table row.<br>- Implement keyword search queries and "Show All" reset buttons. | - Real-time JTable updates after every CRUD action.<br>- Multi-field search functioning smoothly. | **MID TERM-II** (Demonstration - 20M) |
| **Week 13** | Exception Handling & Polish | - Add comprehensive `try-catch-finally` blocks for SQL and number parsing errors.<br>- Enhance custom table header rendering in `UIStyle.java` for crisp contrast on all Look & Feels.<br>- Build `ChangePasswordDialog` and verified logout flows. | - Fault-tolerant execution verified (no unexpected crashes).<br>- Polished UI with zero button or header visibility defects. | **MID TERM-III** (Weekly Tasks Log) |
| **Week 14** | Final Verification & Viva Prep | - Execute automated test harness (`TestRunner.java`) passing all 19 test cases.<br>- Capture authentic high-resolution screenshots via `ScreenshotGenerator.java`.<br>- Prepare final academic project report, README, and viva presentation. | - Complete project folder verified and runnable.<br>- Ready for final spiral-bound submission and external demo. | **FINAL EXTERNAL VIVA & SUBMISSION** |

---

## Summary of PIET Evaluation Evidence Captured
1. **Mid Term-I:**
   - Detailed Project Proposal (`01_PROJECT_PROPOSAL.md`) and SRS (`02_SOFTWARE_REQUIREMENTS_SPECIFICATION_SRS.md`) [20 Marks].
   - Complete GUI prototype screenshots in `screenshots/` [20 Marks].
   - Normalized database schema script `sql/schema.sql` and documentation (`03_DATABASE_DESIGN_AND_SCHEMA.md`) [20 Marks].
2. **Mid Term-II:**
   - Fully working JDBC CRUD implementations in `ScholarshipDAO.java` and `FeeConcessionDAO.java` [20 Marks].
   - Client-side form validations and exception handling logic [20 Marks].
   - Working demonstration runnable from IntelliJ IDEA and command scripts [20 Marks].
3. **Mid Term-III & External Viva:**
   - 14-Week Development Log, 19 automated test executions with 100% pass rate, and spiral-bound-ready final project report (`08_FINAL_PIET_PROJECT_REPORT.md`).

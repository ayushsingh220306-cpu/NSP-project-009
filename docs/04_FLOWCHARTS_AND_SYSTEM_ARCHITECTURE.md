# System Architecture & Flowcharts
## Student Scholarship & Fee Concession System

**Author:** AYUSH SINGH (PIET25CR009)  
**Department:** Computer Science & Engineering, 3CSD  
**Institution:** Poornima Institute of Engineering & Technology (PIET)  
**Session:** 2026–27  

---

## 1. Multi-Tier Layered Architecture

The application adopts a clean, modular 3-tier desktop architecture:

```mermaid
graph TD
    subgraph UI_Layer["1. Presentation Layer (Java Swing)"]
        LF[LoginFrame]
        ADF[AdminDashboardFrame]
        UDF[UserDashboardFrame]
        MSP[ManageScholarshipPanel]
        MFP[ManageFeeConcessionPanel]
        CPD[ChangePasswordDialog]
        UIS[UIStyle Custom Components]
    end

    subgraph Logic_Layer["2. Business & Data Access Layer (DAO)"]
        UDAO[UserDAO]
        SDAO[ScholarshipDAO]
        FDAO[FeeConcessionDAO]
        UM[User Model]
        SM[Scholarship Model]
        FM[FeeConcession Model]
    end

    subgraph JDBC_Layer["3. Database Connectivity Layer (JDBC)"]
        DBC[DBConnection Singleton Manager]
        PS[PreparedStatement SQL Queries]
        DRV[com.mysql.cj.jdbc.Driver]
    end

    subgraph Storage_Layer["4. Relational Database Layer (MySQL)"]
        T1[(users Table)]
        T2[(scholarships Table)]
        T3[(fee_concessions Table)]
    end

    LF --> UDAO
    ADF --> SDAO
    ADF --> FDAO
    UDF --> SDAO
    UDF --> FDAO
    MSP --> SDAO
    MFP --> FDAO
    CPD --> UDAO

    UDAO --> DBC
    SDAO --> DBC
    FDAO --> DBC
    DBC --> PS
    PS --> DRV
    DRV --> Storage_Layer
```

---

## 2. Process Flowcharts

### 2.1 Login & Role-Based Routing Flowchart

```mermaid
flowchart TD
    Start([Application Start: Main.java]) --> InitUI[Launch LoginFrame on EDT]
    InitUI --> CheckDB{Is MySQL Online?}
    CheckDB -- No --> ShowDBErr[Display Offline Warning Status]
    CheckDB -- Yes --> ShowGreen[Display Connected Status]
    ShowDBErr --> InputCredentials[User Enters Username & Password]
    ShowGreen --> InputCredentials

    InputCredentials --> ClickLogin[User Clicks 'Login' Button]
    ClickLogin --> CheckEmpty{Are Fields Empty?}
    CheckEmpty -- Yes --> AlertEmpty[Show 'Empty Fields' Warning] --> InputCredentials
    CheckEmpty -- No --> QueryDB[Execute UserDAO.authenticate via PreparedStatement]

    QueryDB --> CheckMatch{Credentials Valid?}
    CheckMatch -- No --> AlertInvalid[Show 'Invalid Credentials' Error] --> InputCredentials
    CheckMatch -- Yes --> CheckRole{User Role?}

    CheckRole -- 'Admin' --> OpenAdmin[Open AdminDashboardFrame]
    CheckRole -- 'User' --> OpenUser[Open UserDashboardFrame]
    OpenAdmin --> DisposeLogin[Dispose LoginFrame]
    OpenUser --> DisposeLogin
    DisposeLogin --> EndLogin([Session Active])
```

---

### 2.2 Record CRUD & Search Flowchart (Scholarship / Concession)

```mermaid
flowchart TD
    StartCRUD([User Operates Management Panel]) --> ActionSelect{Action Selected?}

    %% CREATE
    ActionSelect -- Click 'Add' --> ValidateFields{Form Validated?}
    ValidateFields -- Blank or Amount <= 0 --> ShowValErr[Show Validation Error Dialog]
    ValidateFields -- Valid --> ExecuteInsert[Execute DAO.insert with PreparedStatement]
    ExecuteInsert --> InsertSuccess{Success?}
    InsertSuccess -- Yes --> AlertAdded[Show Success Dialog] --> RefreshTable[Refresh JTable & Reload Data]
    InsertSuccess -- No --> AlertDBErr[Show DB Error Dialog]

    %% READ / SELECT
    ActionSelect -- Row Click on JTable --> PopulateForm[Load Selected Row Data into Form Fields]

    %% UPDATE
    ActionSelect -- Click 'Update' --> CheckRowSel{Row Selected?}
    CheckRowSel -- No --> AlertSelectRow[Show 'Select Row' Warning]
    CheckRowSel -- Yes --> ValidateUpd{Form Validated?}
    ValidateUpd -- Valid --> ExecuteUpdate[Execute DAO.update with PreparedStatement]
    ExecuteUpdate --> RefreshTable

    %% DELETE
    ActionSelect -- Click 'Delete' --> CheckDelRow{Row Selected?}
    CheckDelRow -- No --> AlertSelectRow
    CheckDelRow -- Yes --> ConfirmDel{User Confirms in JOptionPane?}
    ConfirmDel -- No (Cancel) --> AbortDel[No Action Taken]
    ConfirmDel -- Yes --> ExecuteDelete[Execute DAO.delete with PreparedStatement]
    ExecuteDelete --> RefreshTable

    %% SEARCH
    ActionSelect -- Enter Keyword & Click 'Search' --> QuerySearch[Execute DAO.search keyword]
    QuerySearch --> DisplayResults[Render Filtered Records in JTable]
    DisplayResults --> CheckCount{Any Matches?}
    CheckCount -- No --> ShowNoMatch[Show 'No Records Found' Dialog]
    CheckCount -- Yes --> EndAction([Records Displayed])

    %% SHOW ALL
    ActionSelect -- Click 'Show All' --> ResetFilter[Clear Search Field & Load All Records]
    ResetFilter --> RefreshTable
```

---

### 2.3 Password Change Flowchart

```mermaid
flowchart TD
    StartPass([User Clicks 'Change Password']) --> OpenModal[Open ChangePasswordDialog Modal]
    OpenModal --> EnterPass[Enter Current, New, and Confirm Password]
    EnterPass --> ClickSubmit[Click 'Update Password']

    ClickSubmit --> CheckEmptyPass{Any Field Empty?}
    CheckEmptyPass -- Yes --> AlertPassEmpty[Show Warning: All Fields Required] --> EnterPass
    CheckEmptyPass -- No --> CheckLen{New Password >= 4 chars?}
    CheckLen -- No --> AlertPassLen[Show Warning: Minimum 4 Characters] --> EnterPass
    CheckLen -- Yes --> CheckMatchPass{New == Confirm?}
    CheckMatchPass -- No --> AlertPassMismatch[Show Error: Passwords Do Not Match] --> EnterPass
    CheckMatchPass -- Yes --> VerifyOld[UserDAO.verifyCurrentPassword]

    VerifyOld --> OldValid{Current Password Correct?}
    OldValid -- No --> AlertOldWrong[Show Error: Current Password Incorrect] --> EnterPass
    OldValid -- Yes --> UpdateDB[UserDAO.changePassword in MySQL]
    UpdateDB --> ShowPassSuccess[Show Success Dialog] --> CloseDialog[Close Dialog & Return]
```

---

### 2.4 Logout Flowchart

```mermaid
flowchart TD
    StartLogout([User Clicks 'Logout']) --> ShowConfirm{Show Confirm Logout Dialog}
    ShowConfirm -- Cancel (No) --> RetainSession[Remain on Current Dashboard]
    ShowConfirm -- Confirm (Yes) --> CloseDashboard[Dispose Current Dashboard Window]
    CloseDashboard --> OpenLogin[Instantiate & Display LoginFrame on EDT]
    OpenLogin --> ReadyNextUser([System Ready for Next Login])
```

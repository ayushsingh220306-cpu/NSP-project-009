-- ==========================================================
-- PIET Minor Project (NSP) - Session 2026-27
-- Project: Student Scholarship & Fee Concession System
-- Student: AYUSH SINGH (PIET25CR009)
-- College: Poornima Institute of Engineering & Technology
-- Database Script: schema.sql
-- ==========================================================

DROP DATABASE IF EXISTS scholarship_fee_db;
CREATE DATABASE scholarship_fee_db;
USE scholarship_fee_db;

-- ----------------------------------------------------------
-- 1. Table: users
-- Roles: 'Admin' (full management), 'User' (student view)
-- ----------------------------------------------------------
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'User',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ----------------------------------------------------------
-- 2. Table: scholarships
-- Stores student scholarship applications and awards
-- ----------------------------------------------------------
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

-- ----------------------------------------------------------
-- 3. Table: fee_concessions
-- Stores institutional fee concession & waiver records
-- ----------------------------------------------------------
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

-- ==========================================================
-- INSERT DEMO SAMPLE DATA
-- ==========================================================

-- Demo Users
INSERT INTO users (username, password, full_name, role) VALUES
('admin', 'admin123', 'Ayush Singh (Admin)', 'Admin'),
('user1', 'user123', 'Rahul Sharma', 'User'),
('user2', 'user123', 'Priya Verma', 'User'),
('user3', 'user123', 'Amit Kumar', 'User');

-- Demo Scholarship Records
INSERT INTO scholarships (student_id, student_name, scholarship_name, amount, academic_year, status, remarks, applied_date) VALUES
('PIET25CR015', 'Rahul Sharma', 'Merit Scholarship', 25000.00, '2026-27', 'Approved', 'Top 5% in Semester II Examination', '2026-07-15'),
('PIET25CR022', 'Priya Verma', 'Girl Child STEM Grant', 20000.00, '2026-27', 'Approved', 'State STEM Support Scheme', '2026-07-20'),
('PIET25CR034', 'Amit Kumar', 'Need-Based Financial Aid', 18000.00, '2026-27', 'Pending', 'Income certificate under verification', '2026-08-01'),
('PIET25CR041', 'Sneha Patel', 'Sports Excellence Award', 15000.00, '2026-27', 'Approved', 'Gold Medalist - State Badminton', '2026-08-10'),
('PIET25CR058', 'Vikas Meena', 'PIET Star Scholar Scheme', 30000.00, '2026-27', 'Pending', 'Academic review committee pending', '2026-08-18'),
('PIET25CR073', 'Neha Choudhary', 'Need-Based Financial Aid', 15000.00, '2026-27', 'Rejected', 'Annual household income above threshold', '2026-08-25');

-- Demo Fee Concession Records
INSERT INTO fee_concessions (student_id, student_name, concession_type, concession_amount, academic_year, status, remarks, applied_date) VALUES
('PIET25CR015', 'Rahul Sharma', 'Sibling Concession', 10000.00, '2026-27', 'Approved', 'Elder sibling enrolled in IV Year B.Tech', '2026-07-16'),
('PIET25CR022', 'Priya Verma', 'EWS Category Waiver', 15000.00, '2026-27', 'Approved', 'Valid EWS certificate verified', '2026-07-22'),
('PIET25CR034', 'Amit Kumar', 'Staff Ward Concession', 12000.00, '2026-27', 'Pending', 'HR verification submitted', '2026-08-05'),
('PIET25CR062', 'Rohan Gupta', 'Merit Fee Waiver', 10000.00, '2026-27', 'Approved', 'Consistent SGPA > 9.0', '2026-08-12'),
('PIET25CR088', 'Ananya Joshi', 'Special Discretionary Waiver', 8000.00, '2026-27', 'Rejected', 'Incomplete application documents', '2026-08-20');

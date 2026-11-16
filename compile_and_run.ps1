# ==========================================================
# Student Scholarship & Fee Concession System
# Student: AYUSH SINGH (PIET25CR009) | Section: 3CSD
# Poornima Institute of Engineering & Technology (PIET)
# Compile and Run Script for Windows PowerShell
# ==========================================================

Write-Host "[1/3] Creating output directory 'bin'..." -ForegroundColor Cyan
if (-not (Test-Path "bin")) {
    New-Item -ItemType Directory -Path "bin" | Out-Null
}

Write-Host "[2/3] Compiling Java source files..." -ForegroundColor Cyan
javac -encoding UTF-8 -cp "lib\mysql-connector-j-8.4.0.jar" -d bin src\db\*.java src\model\*.java src\dao\*.java src\ui\*.java src\Main.java src\TestRunner.java

if ($LASTEXITCODE -ne 0) {
    Write-Host "[ERROR] Compilation failed!" -ForegroundColor Red
    exit $LASTEXITCODE
}

Write-Host "[3/3] Launching Student Scholarship & Fee Concession System..." -ForegroundColor Green
java -cp "bin;lib\mysql-connector-j-8.4.0.jar" Main

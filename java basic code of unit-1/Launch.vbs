
Set WshShell = CreateObject("WScript.Shell")
Set FSO = CreateObject("Scripting.FileSystemObject")

projectPath = FSO.GetParentFolderName(WScript.ScriptFullName)
batPath = FSO.BuildPath(projectPath, "compile_and_run.bat")

If Not FSO.FileExists(batPath) Then
    MsgBox "compile_and_run.bat file not found!", 16, "Error"
    WScript.Quit
End If

WshShell.CurrentDirectory = projectPath

WshShell.Run """" & batPath & """", 0, False

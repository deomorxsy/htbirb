$homeJava = [System.Environment]::GetEnvironmentVariable('JAVA_HOME', [System.EnvironmentVariableTarget]::Machine)

if ($homeJava -eq 'C:\java\openjdk\jdk-17') {
    Write-Host 'JAVA_HOME = jdk-17'
} else {
    Write-Host 'JAVA_HOME is NOT jdk-17. Setting variable now'
    [System.Environment]::SetEnvironmentVariable('JAVA_HOME', 'C:\java\openjdk\jdk-17', [System.EnvironmentVaribleTarget]::Machine)
}

echo $env:JAVA_HOME

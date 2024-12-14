<?php
// Database connection parameters
$host = "localhost"; // Database host, usually 'localhost'
$dbname = "bustracker"; // Replace with your database name
$username = "root"; // Replace with your database username
$password = ""; // Replace with your database password

// Establishing the connection
$conn = new mysqli($host, $username, $password, $dbname);

// Checking the connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Connection successful
?>

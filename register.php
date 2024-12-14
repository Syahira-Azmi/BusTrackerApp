<?php
// Enable error reporting
error_reporting(E_ALL);
ini_set('display_errors', 1);

// Include database connection
include 'database.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Get input data
    $input = json_decode(file_get_contents('php://input'), true);

    $userID = $input['userID'] ?? '';
    $fullName = $input['fullName'] ?? '';
    $email = $input['email'] ?? '';
    $phoneNumber = $input['phoneNumber'] ?? '';
    $password = $input['password'] ?? '';
    $roleID = $input['roleID'] ?? 103;  // Default roleID for student

    // Validate input
    if (empty($userID) || empty($fullName) || empty($email) || empty($phoneNumber) || empty($password)) {
        echo json_encode(['success' => false, 'message' => 'Invalid input']);
        exit;
    }

    // Check if userID already exists
    $stmt = $conn->prepare("SELECT * FROM appUser WHERE userID = ?");
    $stmt->bind_param("i", $userID);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        echo json_encode(['success' => false, 'message' => 'User ID already exists']);
        exit;
    }

    // Insert new user into the database
    $stmt = $conn->prepare("INSERT INTO appUser (userID, email, password, nickname, phoneNum, roleID) VALUES (?, ?, ?, ?, ?, ?)");
    $stmt->bind_param("issssi", $userID, $email, $password, $fullName, $phoneNumber, $roleID);

    if ($stmt->execute()) {
        echo json_encode(['success' => true, 'message' => 'User registered successfully']);
    } else {
        echo json_encode(['success' => false, 'message' => 'Error registering user']);
    }

    $stmt->close();
    $conn->close();
} else {
    echo json_encode(['success' => false, 'message' => 'Invalid request method']);
}
?>

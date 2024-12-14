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
    $password = $input['password'] ?? '';

    // Validate input
    if (empty($userID) || empty($password)) {
        echo json_encode(['success' => false, 'message' => 'Invalid input']);
        exit;
    }

    // Query database securely
    $stmt = $conn->prepare("SELECT roleID FROM appUser WHERE userID = ? AND password = ?");
    $stmt->bind_param("ss", $userID, $password); // Corrected type to "ss" for string parameters
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        echo json_encode([
            'success' => true,
            'message' => 'Login successful',
            'roleID' => $row['roleID']
        ]);
    } else {
        echo json_encode(['success' => false, 'message' => 'Invalid credentials']);
    }

    // Close the statement and connection
    $stmt->close();
    $conn->close();
} else {
    echo json_encode(['success' => false, 'message' => 'Invalid request method']);
}
?>

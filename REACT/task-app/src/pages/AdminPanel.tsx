import React from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const AdminPanel: React.FC = () => {
  const navigate = useNavigate();

  const handleDeleteUser = async (userId: number) => {
    try {
      await axios.delete(`http://localhost:8080/api/users/${userId}`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      });
      alert("User deleted successfully!");
    } catch (error) {
      alert("Failed to delete user.");
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    navigate("/login");
  };

  return (
    <div>
      <h1>Admin Panel</h1>
      <p>Welcome to the admin panel!</p>
      <button onClick={() => handleDeleteUser(1)}>Delete User (ID: 1)</button>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default AdminPanel;

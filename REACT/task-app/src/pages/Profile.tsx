import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Profile: React.FC = () => {
  const [userData, setUserData] = useState<{ username: string } | null>(null);
  const [error, setError] = useState<string>("");
  const navigate = useNavigate();

  // Pobieranie danych użytkownika po załadowaniu komponentu
  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const token = localStorage.getItem("token");
        const response = await axios.get("http://localhost:8080/api/user/me", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setUserData(response.data);
      } catch (err: any) {
        setError("Failed to load profile data.");
        console.error(err.response?.data || err.message);
      }
    };

    fetchUserData();
  }, []);

  // Funkcja wylogowania
  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    navigate("/login");
  };

  if (error) {
    return <div>{error}</div>;
  }

  if (!userData) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h1>Your Profile</h1>
      <p>Username: {userData.username}</p>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default Profile;

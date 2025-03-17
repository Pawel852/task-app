import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import styles from "./Login.module.css";

const Login: React.FC = () => {
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [message, setMessage] = useState<string>("");
  const navigate = useNavigate();

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8080/api/auth/login",
        {
          username,
          password,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      const { token, authenticated } = response.data;
      const role = authenticated.principal.authorities[0]; // "ROLE_USER" lub "ROLE_ADMIN"

      // Zapisujemy token i rolę w localStorage
      localStorage.setItem("token", token);
      localStorage.setItem("role", role);

      setMessage("Login successful!");

      // Przekierowanie w zależności od roli
      if (role === "ROLE_ADMIN") {
        navigate("/admin");
      } else {
        navigate("/profile");
      }
    } catch (error: any) {
      setMessage("Something went wrong. Please check your login details.");
      console.error(error.response?.data || error.message);
    }
  };

  return (
    <div className={styles.formContainer}>
      <form onSubmit={handleLogin} className={styles.form}>
        <div className={styles.inputGroup}>
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className={styles.input}
          />
        </div>
        <div className={styles.inputGroup}>
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className={styles.input}
          />
        </div>
        <button type="submit" className={styles.button}>
          Log in
        </button>
      </form>
      {message && <p className={styles.message}>{message}</p>}

      <p>
        Do not have an account?{" "}
        <Link to="/register" className={styles.link}>
          Register
        </Link>
      </p>
    </div>
  );
};

export default Login;

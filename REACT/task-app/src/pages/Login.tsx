import React, { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import styles from "./Login.module.css";

const Login: React.FC = () => {
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [message, setMessage] = useState<string>("");

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
    } catch (error: any) {
      setMessage("Something went wrong. Please check your login details. ");

      // setMessage("Error: " + (error.response?.data || error.message));
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
            type="text"
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

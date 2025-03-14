import React, { useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import styles from "./Register.module.css";

const Register: React.FC = () => {
  const [username, setUsername] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [message, setMessage] = useState<string>("");

  const handleRegister = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/api/auth/register",
        {
          username,
          email,
          password,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      const token = response.data.token || "Token not found";
      setMessage("User registered. Token: " + token);
      localStorage.setItem("token", token);
    } catch (error: any) {
      setMessage("Something went wrong. Please check your register details. ");
      // setMessage("Error: " + (error.response?.data || error.message));
    }
  };

  return (
    <div className={styles.formContainer}>
      <form onSubmit={handleRegister} className={styles.form}>
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
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
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
          Register
        </button>
      </form>
      {message && <p className={styles.message}>{message}</p>}
      <p>
        Already have an account?{" "}
        <Link to="/login" className={styles.link}>
          Log in
        </Link>
      </p>
    </div>
  );
};

export default Register;

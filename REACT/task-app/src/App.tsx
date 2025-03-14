import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Register from "./pages/Register";
import Login from "./pages/Login";
import React from "react";
import styles from "./pages/App.module.css";

const App: React.FC = () => {
  return (
    <Router>
      <div className={styles.app}>
        <h1>TASK APP</h1>


        {/* <nav className={styles.nav}>
          <Link to="/login">Log in</Link>
          <p>or register</p>
        </nav> */}
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;

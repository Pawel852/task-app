import React from "react";
import { Navigate, Outlet } from "react-router-dom";

const PrivateRoute: React.FC<{ allowedRole?: string }> = ({ allowedRole }) => {
  const token = localStorage.getItem("token");
  const role = localStorage.getItem("role");

  // Jeśli nie ma tokena, przekieruj na logowanie
  if (!token) {
    return <Navigate to="/login" />;
  }

  // Jeśli podano rolę i nie zgadza się z rolą użytkownika, przekieruj
  if (allowedRole && role !== allowedRole) {
    return <Navigate to="/profile" />;
  }

  // Jeśli wszystko OK, renderuj chronioną zawartość
  return <Outlet />;
};

export default PrivateRoute;

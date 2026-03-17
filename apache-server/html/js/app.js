/**
 * Application client controller
 * Handles user session and dashboard behaviour
 */

document.addEventListener("DOMContentLoaded", () => {

    const username = localStorage.getItem("user");

    const welcomeMessage = document.getElementById("welcomeMessage");
    const logoutButton = document.getElementById("logoutButton");

    if (!username) {
        window.location.href = "login.html";
        return;
    }

    if (welcomeMessage) {
        welcomeMessage.textContent = `Welcome, ${username}!`;
    }

    if (logoutButton) {

        logoutButton.addEventListener("click", () => {

            localStorage.removeItem("user");

            window.location.href = "login.html";

        });

    }

});
const API_BASE = "http://localhost:8080/api";

const loginForm = document.getElementById("loginForm");
const registerForm = document.getElementById("registerForm");
const message = document.getElementById("message");

function showMessage(text, isError = false) {
    message.textContent = text;
    message.style.color = isError ? "red" : "green";
}

loginForm.addEventListener("submit", async (event) => {

    event.preventDefault();

    const username = document.getElementById("loginUsername").value;
    const password = document.getElementById("loginPassword").value;

    try {

        const response = await fetch(`${API_BASE}/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username,
                password
            })
        });

        const data = await response.json();

        if (response.ok) {

            showMessage("Login successful");

            localStorage.setItem("user", username);

            setTimeout(() => {
                window.location.href = "dashboard.html";
            }, 800);

        } else {

            showMessage(data.message || "Login failed", true);

        }

    } catch (error) {

        showMessage("Server error", true);
        console.error(error);

    }

});

registerForm.addEventListener("submit", async (event) => {

    event.preventDefault();

    const username = document.getElementById("registerUsername").value;
    const password = document.getElementById("registerPassword").value;

    try {

        const response = await fetch(`${API_BASE}/register`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username,
                password
            })
        });

        const data = await response.json();

        if (response.ok) {

            showMessage("User registered successfully");

        } else {

            showMessage(data.message || "Registration failed", true);

        }

    } catch (error) {

        showMessage("Server error", true);
        console.error(error);

    }

});
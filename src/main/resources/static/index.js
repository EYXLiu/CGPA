function addRow() {
    let table = document.getElementById("GradesTable");
    let row = table.insertRow();
    row.innerHTML = `
        <td><input type="text" class="courseName" placeholder="Course name"></td>
        <td><input type="text" class="grade" placeholder="Grade"></td>
        <td><button class="remove-btn" onclick="removeRow(this)">Remove</button></td>
    `
}

function addAddRow(name, grade) {
    let table = document.getElementById("GradesTable");
    let row = table.insertRow();
    row.innerHTML = `
        <td><input type="text" class="courseName" placeholder="Course name" value="${name}"></td>
        <td><input type="text" class="grade" placeholder="Grade" value="${grade}"></td>
        <td><button class="remove-btn" onclick="removeRow(this)">Remove</button></td>
    `
}

function removeRow(button) {
    let row = button.parentNode.parentNode;
    row.parentNode.removeChild(row);
}

function calculateCGPA() {
    let grades = document.querySelectorAll(".grade");
    let data = [];
    for (let i = 0; i < grades.length; i++) {
        let grade = parseFloat(grades[i].value);
        if (!isNaN(grade)) {
            data.push(grade);
        }
    }
    
    fetch("/calculate", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data),
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById("result").innerText = "Culmulative GPA: " + data.cgpa;
    })
    .catch(err => console.error("Error: " + err));
}

async function get() {
    if (!await loggedIn()) {
        location.reload();
        return;
    }
    try {
        const response = await fetch("/grades", {
            method: "GET", 
            credentials: "same-origin"
        });
        
        const grades = await response.json();
        for (const g of grades) {
            addAddRow(g.subject, g.score);
        }
    } catch (error) {
        console.error("Fetch error:", error);
    }
}

async function put() {
    if (!await loggedIn()) {
        location.reload();
        return;
    }
    let names = document.querySelectorAll(".courseName");
    let grades = document.querySelectorAll(".grade");
    let data = [];

    for (let i = 0; i < names.length; i++) {
        let name = names[i].value.trim();
        let grade = parseFloat(grades[i].value);
        if (name != "" && !isNaN(grade)) {
            data.push({
                name: name,
                grade: grade,
            })
        }
    }

    await fetch("/api/auth/save", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data),
        credentials: "same-origin",
    })
    .catch(err => console.error("Fetch error: " + err));
}

async function logout() {
    if (!await loggedIn()) {
        location.reload();
        return;
    }
    let names = document.querySelectorAll(".courseName");
    let grades = document.querySelectorAll(".grade");
    let data = [];

    for (let i = 0; i < names.length; i++) {
        let name = names[i].value.trim();
        let grade = parseFloat(grades[i].value);
        if (name != "" && !isNaN(grade)) {
            data.push({
                name: name,
                grade: grade,
            })
        }
    }

    await fetch("/api/auth/logout", {
        method: "POST", 
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data),
        credentials: "same-origin", 
    })
    location.reload();
}

function openModal() {
    document.getElementById("loginModal").style.display = "block";
}

function closeModal() {
    document.getElementById("loginModal").style.display = "none";
}

function login() {
    event.preventDefault();
    let email = document.querySelector("#loginEmail").value;
    let password = document.querySelector("#loginPassword").value;
    fetch("/api/auth/login", {
        method: "POST", 
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({ "email": email, "password": password }),
    })
    .then(response => {
        if (response.ok) {
            closeModal();
            location.reload();
        } else {
            return response.json().then(data => {
                console.error("Login failed:", data.error);
            });
        }
    })
    .catch(err => console.error("Error: " + err));
}

function openRegister() {
    document.getElementById("registerModal").style.display = "block";
}

function closeRegister() {
    document.getElementById("registerModal").style.display = "none";
}

function register() {
    event.preventDefault();
    let name = document.querySelector("#registerName").value;
    let email = document.querySelector("#registerEmail").value;
    let password = document.querySelector("#registerPassword").value;
    fetch("/api/auth/register", {
        method: "POST", 
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({ "name": name, "email": email, "password": password }),
    })
    .then(response => {
        if (response.ok) {
            closeRegister();
            location.reload();
        } else {
            document.getElementById("registerError").innerHTML = `User already exists`;
            return response.json().then(data => {
                console.error("Login failed:", data.error);
            });
        }
    })
    .catch(err => console.error("Error: " + err));
}

document.addEventListener("keydown", function(event) {
    if (event.key === "Escape") {
        closeModal();
        closeRegister();
    }
})

async function loggedIn() {
    const response = await fetch("/api/auth/profile", {
        method: "GET", 
        credentials: "same-origin"
    })
    if (response.ok) {
        return true;
    } else {
        return false;
    }
}

window.onload = async function() {
    if (await loggedIn()) {
        document.getElementById("authButton").innerHTML = `
            <button class="login-btn" onclick="get()">Get</button>
            <button class="register-btn" onclick="put()">Put</button>
            <button class="logout-btn" onclick="logout()">Logout</button>
        `;
    } else {
        document.getElementById("authButton").innerHTML = `
            <button class="login-btn" onclick="openModal()">Login</button>
            <div id="loginModal" class="loginModal">
                <div class="loginModal-content">
                    <span class="close-btn" onclick="closeModal()">&times;</span>
                    <h2>Login</h2>
                    <form>
                        <label for="email">Username:</label>
                        <input class="signInput" type="text" id="loginEmail" name="email" placeholder="email" autocomplete="email" required><br><br>
                        <label for="password">Password:</label>
                        <input class="signInput" type="password" id="loginPassword" name="password" placeholder="password" autocomplete="current-password" required><br><br>
                        <button type="submit" onclick="login()">Submit</button>
                    </form>
                </div>
            </div>

            <button class="register-btn" onclick="openRegister()">Register</button>
            <div id="registerModal" class="registerModal">
                <div class="registerModal-content">
                    <span class="close-btn" onclick="closeRegister()">&times;</span>
                    <h2>Login</h2>
                    <form>
                        <label for="name">Name:</label>
                        <input class="signInput" type="text" id="registerName" name="name" placeholder="name" autocomplete="name" required><br><br>
                        <label for="email">Username:</label>
                        <input class="signInput" type="text" id="registerEmail" name="email" placeholder="email" autocomplete="email" required><br><br>
                        <label for="password">Password:</label>
                        <input class="signInput" type="password" id="registerPassword" name="password" placeholder="password" autocomplete="current-password" required><br><br>
                        <button type="submit" onclick="register()">Submit</button>
                        <div class="registerError" id="registerError"></div>
                    </form>
                </div>
            </div>
        `;
    }
}
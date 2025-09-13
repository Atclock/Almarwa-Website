// scroll effect to navbar
window.addEventListener("scroll", function () {
    let navbar = document.querySelector(".navbar");

    if (window.scrollY > 0) {
        navbar.classList.add("scrolled");
    } else {
        navbar.classList.remove("scrolled");
    }
});

let slideIndex = 0;
const slides = document.querySelectorAll(".slide");
const dots = document.querySelectorAll(".dot");
const prevButton = document.querySelector(".prev");
const nextButton = document.querySelector(".next");

// display a slide
function showSlide(index) {
    slides.forEach((slide, i) => {
        slide.style.opacity = i === index ? "1" : "0";
    });

    dots.forEach((dot, i) => {
        dot.classList.toggle("active", i === index);
    });
}

// move to next slide
function nextSlide() {
    slideIndex = (slideIndex + 1) % slides.length;
    showSlide(slideIndex);
}

// previous slide
function prevSlide() {
    slideIndex = (slideIndex - 1 + slides.length) % slides.length;
    showSlide(slideIndex);
}

// navigate to a slide when dot is clicked
dots.forEach((dot, index) => {
    dot.addEventListener("click", () => {
        slideIndex = index;
        showSlide(slideIndex);
    });
});

// event listeners for navigation arrows
if (prevButton && nextButton) {
    prevButton.addEventListener("click", prevSlide);
    nextButton.addEventListener("click", nextSlide);
}


setInterval(nextSlide, 150000);


showSlide(slideIndex);



// Form submission handling
document.getElementById("quoteForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    // Clear previous error messages
    document.querySelectorAll(".error").forEach(e => e.textContent = "");

    // Grab values
    let firstName = document.getElementById("firstName").value.trim();
    let lastName = document.getElementById("lastName").value.trim();
    let email = document.getElementById("email").value.trim();
    let phoneNumber = document.getElementById("phoneNumber").value.trim();

    let valid = true;

    // Client-side validation
    if (!/^[a-zA-Z]+$/.test(firstName)) {
        document.getElementById("firstNameError").textContent = "First name must only contain letters.";
        valid = false;
    }
    if (!/^[a-zA-Z]+$/.test(lastName)) {
        document.getElementById("lastNameError").textContent = "Last name must only contain letters.";
        valid = false;
    }
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        document.getElementById("emailError").textContent = "Please enter a valid email.";
        valid = false;
    }
    if (!/^\d{7,15}$/.test(phoneNumber)) {
        document.getElementById("phoneError").textContent = "Phone number must be 7–15 digits.";
        valid = false;
    }

    if (!valid) return;

    // Prepare form data
    let formData = {
        firstName,
        lastName,
        company: document.getElementById("company").value.trim(),
        email,
        area: document.getElementById("areaCode").value.trim(),
        phoneNumber,
        productName: document.getElementById("productName").value.trim(),
        gradePurityType: document.getElementById("gradePurityType").value.trim() || "N/A",
        quantityRequired: document.getElementById("quantityRequired").value.trim() || "N/A",
        website: document.getElementById("website").value.trim() || "N/A"
    };

    try {
        let response = await fetch("http://localhost:8080/api/quotes", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(formData)
        });

        let result = await response.json();

        if (result.status === "success") {
            alert(result.message);
            document.getElementById("quoteForm").reset();
        } else if (result.status === "error") {
            // Generic error from backend → show on top OR assign based on message
            let msg = result.message.toLowerCase();

            if (msg.includes("first name")) document.getElementById("firstNameError").textContent = result.message;
            else if (msg.includes("last name")) document.getElementById("lastNameError").textContent = result.message;
            else if (msg.includes("email")) document.getElementById("emailError").textContent = result.message;
            else if (msg.includes("phone")) document.getElementById("phoneError").textContent = result.message;
            else if (msg.includes("product")) document.getElementById("productError").textContent = result.message;
            else document.getElementById("formError").textContent = result.message;

        }
    } catch (error) {
        console.error("Error submitting form:", error);
        alert("There was an error. Please try again later.");
    }
});



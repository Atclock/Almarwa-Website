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


setInterval(nextSlide, 15000);


showSlide(slideIndex);



// Form submission handling
document.getElementById("quoteForm").addEventListener("submit", async function(event) {
    event.preventDefault();
    
    let formData = {
        firstName: document.getElementById("firstName").value,
        lastName: document.getElementById("lastName").value,
        company: document.getElementById("company").value,
        email: document.getElementById("email").value,
        area: document.getElementById("areaCode").value,
        phoneNumber: document.getElementById("phoneNumber").value,
        productName: document.getElementById("productName").value,
        gradePurityType: document.getElementById("gradePurityType").value || "N/A",
        quantityRequired: document.getElementById("quantityRequired").value || "N/A",
        website: document.getElementById("website").value || "N/A"
    };

    try {
        let response = await fetch("http://localhost:8080/api/quotes", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Cache-Control": "no-cache",
                "pragma": "no-cache",
            },
            body: JSON.stringify(formData)
        });

        if (response.ok) {
            alert("Your request has been submitted successfully!");
        } else {
            alert("Something went wrong. Please try again.");
        }
    } catch (error) {
        console.error("Error submitting form:", error);
        alert("There was an error. Please try again later.");
    }
});

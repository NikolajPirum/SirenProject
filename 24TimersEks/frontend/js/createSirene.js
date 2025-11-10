document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("create-siren-form");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const latitude = parseFloat(document.getElementById("latitude").value);
        const longitude = parseFloat(document.getElementById("longitude").value);
        const sirenStatus = document.getElementById("status").value;

        const response = await fetch("http://localhost:8080/sirens/newSiren", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({latitude: latitude, longitude: longitude, status: sirenStatus})
        });

        if (response.ok) {
            alert("Sirene oprettet!");
            window.location.href = "alleSirener.html"; // Tilbage til oversigt
        } else {
            alert("Fejl ved oprettelse af sirene.");
        }
    });
});

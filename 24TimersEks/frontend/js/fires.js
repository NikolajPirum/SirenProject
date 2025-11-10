document.addEventListener("DOMContentLoaded", () => {
    fetchFires();

    // Event listener til opret brand form
    const fireForm = document.getElementById("fireForm");
    fireForm.addEventListener("submit", (event) => {
        event.preventDefault(); // Forhindrer side reload

        const lat = parseFloat(document.getElementById("lat").value);
        const lon = parseFloat(document.getElementById("lon").value);

        const payload = {
            latitude: lat,
            longitude: lon
        };

        fetch("http://localhost:8080/fire/fireAlarm", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(payload)
        })
            .then(res => {
                if (res.ok) {
                    return res.json();
                } else {
                    throw new Error("Kunne ikke oprette brand.");
                }
            })
            .then(newFire => {
                alert("Ny brand oprettet!");
                fireForm.reset();  // Ryd formularfelter
                fetchFires();      // Opdater tabel med ny brand
            })
            .catch(err => {
                alert(err.message);
                console.error(err);
            });
    });
});

function fetchFires() {
    fetch("http://localhost:8080/fire")
        .then(res => res.json())
        .then(fires => {
            const tbody = document.getElementById("fires-tbody");
            tbody.innerHTML = "";

            fires.forEach(fire => {
                const row = document.createElement("tr");

                const sirensList = fire.sirens && fire.sirens.length > 0
                    ? fire.sirens.map(siren => `#${siren.sirenId} (${siren.latitude}, ${siren.longitude})`).join("<br>")
                    : "Ingen";

                row.innerHTML = `
                    <td>${fire.fireId}</td> 
                    <td>${fire.latitude}</td>
                    <td>${fire.longitude}</td>
                    <td>${fire.timestamp}</td>
                    <td>${fire.closed ? "Ja" : "Nej"}</td>
                    <td>
                        ${!fire.closed ? `<button class="action-btn close-btn" onclick="closeFire(${fire.fireId})">Luk brand</button>` : ""}
                    </td>
                    <td>${sirensList}</td>
                `;

                tbody.appendChild(row);
            });
        })
        .catch(err => console.error("Fejl ved hentning af brande:", err));
}

function closeFire(fireId) {
    fetch(`http://localhost:8080/fire/closeFireWith/${fireId}`, {
        method: "PUT"
    })
        .then(res => {
            if (res.ok) {
                alert("Branden er nu lukket.");
                fetchFires(); // Opdater visning
            } else {
                alert("Kunne ikke lukke branden.");
            }
        })
        .catch(() => alert("Der opstod en fejl ved lukning af branden."));
}
